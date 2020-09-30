import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/src/business/budget/bean/good_confirm.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/account_info.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/bank_info.dart';
import 'package:flutter_ienglish_fine/src/business/pay/view/account/dialog_open_account.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_ienglish_fine/src/business/pay/viewmodel/balance_view_model.dart';

class BalancePage extends StatefulWidget {
  @override
  _BalancePageState createState() => _BalancePageState();
}

class _BalancePageState extends State<BalancePage> with PageBridge {
  BalanceViewModel _viewModel = BalanceViewModel();
  FundQueryDetail _queryDetail;

  @override
  Widget build(BuildContext context) {
    return RootPageWidget(
      appBar: IsAppBar(
        title: S.of(context).my_balance,
      ),
      viewModel: _viewModel,
      task: _viewModel.getBalanceData(),
      body: PullToRefresh(
          child: CustomScrollView(slivers: <Widget>[
            SliverToBoxAdapter(
              child: BalanceInfo(),
            )
          ]),
          onRefresh: () {
            return _viewModel.getBalanceData();
          }),
    );
  }
}

class BalanceInfo extends StatefulWidget {
  @override
  _BalanceInfoState createState() => _BalanceInfoState();
}

class _BalanceInfoState extends State<BalanceInfo> with PageBridge {
  FundQueryDetail _queryDetail;
  BankInfoBean _bankInfoBean;
  AccountInfo _accountInfo;
  String _bankInfo = '';
  bool _isShow = false;

  @override
  Widget build(BuildContext context) {
    return StreamBuilder(
        stream: context.findRootAncestorStateOfType<_BalancePageState>()._viewModel.streamBankInfo,
        builder: (BuildContext context, AsyncSnapshot<BankInfoBean> snapshot) {
          if (snapshot.data != null) {
            _bankInfoBean = snapshot.data;
            if (!StringUtil.isEmpty(_bankInfoBean.enterpriseBankName) &&
                !(StringUtil.isEmpty(_bankInfoBean.enterpriseBankCode))) {
              _bankInfo = _bankInfoBean.enterpriseBankName;
              String cardId = _bankInfoBean.enterpriseBankCode;
              cardId = _bankInfoBean.enterpriseBankCode.length > 4
                  ? cardId.substring(_bankInfoBean.enterpriseBankCode.length - 4)
                  : _bankInfoBean.enterpriseBankCode;
              _bankInfo = _bankInfo + '($cardId)';
            } else if (!StringUtil.isEmpty(_bankInfoBean.bankName) && !(StringUtil.isEmpty(_bankInfoBean.bankCode))) {
              _bankInfo = _bankInfoBean.bankName;
              String cardId = _bankInfoBean.bankCode;
              cardId = _bankInfoBean.bankCode.length > 4
                  ? cardId.substring(_bankInfoBean.bankCode.length - 4)
                  : _bankInfoBean.bankCode;
              _bankInfo = _bankInfo + '($cardId)';
            }
          }
          return StreamBuilder(
              stream: context.findRootAncestorStateOfType<_BalancePageState>()._viewModel.streamAccountInfo,
              builder: (BuildContext context, AsyncSnapshot<AccountInfo> snapshot) {
                if (snapshot.data != null) {
                  _accountInfo = snapshot.data;
                }
                return Column(
                  children: <Widget>[
                    StreamBuilder(
                        stream: context.findRootAncestorStateOfType<_BalancePageState>()._viewModel.streamFundQuery,
                        builder: (BuildContext context, AsyncSnapshot<FundQuery> snapshot) {
                          if (snapshot.data != null) {
                            _queryDetail = snapshot.data.data;
                          }
                          return Container(
                            color: Values.of(context).c_white_background,
                            padding: EdgeInsets.only(
                                left: Values.d_12, right: Values.d_12, top: Values.d_12, bottom: Values.d_22),
                            child: Stack(
                              children: <Widget>[
                                SizedBox(
                                  width: MediaQuery.of(context).size.width - Values.d_12 * 2,
                                  height: (MediaQuery.of(context).size.width - Values.d_12 * 2) * 180 / 351,
                                  child: Image.asset('assets/images/balance_bg.png', fit: BoxFit.fill),
                                ),
                                SizedBox(
                                  width: MediaQuery.of(context).size.width - Values.d_12 * 2,
                                  height: (MediaQuery.of(context).size.width - Values.d_12 * 2) * 180 / 351,
                                  child: Container(
                                    padding: EdgeInsets.only(left: Values.d_26, top: Values.d_20, bottom: Values.d_20),
                                    child: Column(
                                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                                      children: <Widget>[
                                        Container(
                                          child: Row(
                                            mainAxisAlignment: MainAxisAlignment.spaceBetween,
                                            crossAxisAlignment: CrossAxisAlignment.start,
                                            children: <Widget>[
                                              Column(
                                                crossAxisAlignment: CrossAxisAlignment.start,
                                                children: <Widget>[
                                                  Row(
                                                    children: <Widget>[
                                                      Text(
                                                        S.of(context).balance_title,
                                                        style: TextStyle(
                                                            fontSize: Values.s_text_12,
                                                            fontWeight: Values.font_Weight_normal,
                                                            color: Values.of(context).c_white_front,
                                                            decoration: TextDecoration.none),
                                                        textAlign: TextAlign.left,
                                                      ),
                                                      SizedBox(
                                                        width: Values.d_5,
                                                      ),
                                                      GestureDetector(
                                                        onTap: () {
                                                          setState(() {
                                                            _isShow = !_isShow;
                                                          });
                                                        },
                                                        child: Image.asset(_isShow
                                                            ? 'assets/images/show_icon.png'
                                                            : 'assets/images/hidden_icon.png'),
                                                      )
                                                    ],
                                                  ),
                                                  SizedBox(height: Values.d_10),
                                                  GestureDetector(
                                                    onTap: () {
                                                      open(RouterName.my_balance_detail, argument: 0);
                                                    },
                                                    child: Row(
                                                      crossAxisAlignment: CrossAxisAlignment.center,
                                                      children: <Widget>[
                                                        Text(
                                                          _isShow
                                                              ? (_queryDetail != null
                                                                  ? _queryDetail?.bal?.toStringAsFixed(2)
                                                                  : '0.00')
                                                              : '******',
                                                          style: TextStyle(
                                                              fontSize: Values.s_text_40,
                                                              fontWeight: Values.font_Weight_normal,
                                                              color: Values.of(context).c_white_front,
                                                              decoration: TextDecoration.none),
                                                          textAlign: TextAlign.center,
                                                        ),
                                                        SizedBox(width: Values.d_5),
                                                        Container(
                                                          padding: EdgeInsets.only(bottom: _isShow ? 0 : 12),
                                                          child: Image.asset('assets/images/right_icon.png'),
                                                        )
                                                      ],
                                                    ),
                                                  )
                                                ],
                                              ),
                                              _bankInfoBean != null
                                                  ? (!StringUtil.isEmpty(_bankInfoBean.enterpriseName)
                                                      ? Image.asset('assets/images/enterprise_icon.png')
                                                      : Image.asset('assets/images/persion_icon.png'))
                                                  : Container(),
                                            ],
                                          ),
                                        ),
                                        Container(
                                          child: Row(
                                            children: <Widget>[
                                              Expanded(
                                                child: Column(
                                                  crossAxisAlignment: CrossAxisAlignment.start,
                                                  children: <Widget>[
                                                    GestureDetector(
                                                      onTap: () {
                                                        showDialogInfo(S.of(context).balance_frozen_warn_title,
                                                            S.of(context).balance_frozen_warn_content);
                                                      },
                                                      child: Row(
                                                        children: <Widget>[
                                                          Text(
                                                            S.of(context).balance_frozen,
                                                            style: TextStyle(
                                                                fontSize: Values.s_text_12,
                                                                fontWeight: Values.font_Weight_normal,
                                                                color: Values.of(context).c_white_front,
                                                                decoration: TextDecoration.none),
                                                            textAlign: TextAlign.left,
                                                          ),
                                                          SizedBox(
                                                            width: Values.d_5,
                                                          ),
                                                          Image.asset('assets/images/warn_white_icon.png'),
                                                        ],
                                                      ),
                                                    ),
                                                    SizedBox(height: Values.d_5),
                                                    GestureDetector(
                                                      onTap: () {
                                                        open(RouterName.my_balance_detail, argument: 3);
                                                      },
                                                      child: Row(
                                                        children: <Widget>[
                                                          Text(
                                                            _isShow
                                                                ? (_queryDetail != null
                                                                    ? ('￥' + _queryDetail?.frozen?.toStringAsFixed(2))
                                                                    : '0.00')
                                                                : '******',
                                                            style: TextStyle(
                                                                fontSize: _isShow ? Values.s_text_20 : 23.5,
                                                                fontWeight: Values.font_Weight_normal,
                                                                color: Values.of(context).c_white_front,
                                                                decoration: TextDecoration.none),
                                                            textAlign: TextAlign.center,
                                                          ),
                                                          SizedBox(width: Values.d_5),
                                                          Container(
                                                            padding: EdgeInsets.only(bottom: _isShow ? 0 : 8),
                                                            child: Image.asset('assets/images/right_icon.png'),
                                                          )
                                                        ],
                                                      ),
                                                    )
                                                  ],
                                                ),
                                                flex: 1,
                                              ),
                                              Expanded(
                                                child: Column(
                                                  crossAxisAlignment: CrossAxisAlignment.start,
                                                  children: <Widget>[
                                                    GestureDetector(
                                                      onTap: () {
                                                        showDialogInfo(S.of(context).balance_cash_warn_title,
                                                            S.of(context).balance_cash_warn_content);
                                                      },
                                                      child: Row(
                                                        children: <Widget>[
                                                          Text(
                                                            S.of(context).balance_cash,
                                                            style: TextStyle(
                                                                fontSize: Values.s_text_12,
                                                                fontWeight: Values.font_Weight_normal,
                                                                color: Values.of(context).c_white_front,
                                                                decoration: TextDecoration.none),
                                                            textAlign: TextAlign.left,
                                                          ),
                                                          SizedBox(
                                                            width: Values.d_5,
                                                          ),
                                                          Image.asset('assets/images/warn_white_icon.png'),
                                                        ],
                                                      ),
                                                    ),
                                                    SizedBox(height: Values.d_5),
                                                    GestureDetector(
                                                      onTap: () {
                                                        open(RouterName.my_balance_detail, argument: 2);
                                                      },
                                                      child: Row(
                                                        children: <Widget>[
                                                          Text(
                                                            _isShow
                                                                ? (_queryDetail != null
                                                                    ? ('￥' + _queryDetail?.cash?.toStringAsFixed(2))
                                                                    : '0.00')
                                                                : '******',
                                                            style: TextStyle(
                                                                fontSize: _isShow ? Values.s_text_20 : 23.5,
                                                                fontWeight: Values.font_Weight_normal,
                                                                color: Values.of(context).c_white_front,
                                                                decoration: TextDecoration.none),
                                                            textAlign: TextAlign.center,
                                                          ),
                                                          SizedBox(width: Values.d_5),
                                                          Container(
                                                            padding: EdgeInsets.only(bottom: _isShow ? 0 : 8),
                                                            child: Image.asset('assets/images/right_icon.png'),
                                                          )
                                                        ],
                                                      ),
                                                    )
                                                  ],
                                                ),
                                                flex: 1,
                                              ),
                                            ],
                                          ),
                                        )
                                      ],
                                    ),
                                  ),
                                )
                              ],
                            ),
                          );
                        }),
                    itemWidget('assets/images/balance_icon_1.png', S.of(context).balance_save, '', () {
                      if (_accountInfo.ifSuccess) {
                        open(RouterName.pay_save, argument: {'bank': _bankInfoBean, 'query': _queryDetail});
                      } else {
                        showOpenAccountDialog();
                      }
                    }),
                    Container(
                      padding: EdgeInsets.only(left: Values.d_15, right: Values.d_15),
                      color: Values.of(context).c_white_background,
                      height: 1,
                      child: Container(
                        color: Values.of(context).c_grey_ea,
                      ),
                    ),
                    itemWidget('assets/images/balance_icon_2.png', S.of(context).balance_fetch, '', () {
                      if (_accountInfo.ifSuccess) {
                        open(RouterName.pay_withdraw, argument: {'bank': _bankInfoBean, 'query': _queryDetail});
                      } else {
                        showOpenAccountDialog();
                      }
                    }),
                    SizedBox(height: Values.d_10),
                    itemWidget('assets/images/balance_icon_3.png', S.of(context).balance_bank_info, _bankInfo, () {}),
                    SizedBox(height: Values.d_10),
                    Container(
                      padding: EdgeInsets.only(left: Values.d_15, right: Values.d_15),
                      child: Text(
                        S.of(context).balance_warn,
                        maxLines: 10,
                        textAlign: TextAlign.left,
                        style: TextStyle(
                            fontSize: Values.s_text_12,
                            fontWeight: Values.font_Weight_normal,
                            color: Values.of(context).c_black_front_99,
                            decoration: TextDecoration.none),
                      ),
                    )
                  ],
                );
              });
        });
  }

  Widget itemWidget(String image, String title, String detail, Function() callback) {
    return GestureDetector(
      child: Container(
        color: Values.of(context).c_white_background,
        height: Values.d_50,
        padding: EdgeInsets.only(left: Values.d_12, right: Values.d_12),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            Row(
              children: <Widget>[
                Image.asset(image),
                SizedBox(width: Values.d_8),
                Text(
                  title,
                  style: TextStyle(
                      fontSize: Values.s_text_14,
                      fontWeight: Values.font_Weight_normal,
                      color: Values.of(context).c_black_front_33,
                      decoration: TextDecoration.none),
                ),
              ],
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.end,
              children: <Widget>[
                Text(
                  detail,
                  style: TextStyle(
                      fontSize: Values.s_text_14,
                      fontWeight: Values.font_Weight_medium,
                      color: Values.of(context).c_black_front_33,
                      decoration: TextDecoration.none),
                ),
                Image.asset('assets/images/right_icon.png')
              ],
            )
          ],
        ),
      ),
      onTap: () {
        callback();
      },
    );
  }

  /// 普通消息提示框
  void showDialogInfo(String title, String message) {
    showDialog(
        context: context,
        child: DialogTool(
          bgColor: Values.of(context).c_white_background,
          title: title,
          titleFontSize: Values.s_text_18,
          titleColor: Values.of(context).c_black_front_33,
          content: message,
          middleTitle: S.of(context).warn_button_title,
          middleColor: Values.of(context).c_white_front,
          middleBgColor: Values.of(context).c_orange_background_0b,
          contentColor: Values.of(context).c_black_front_33,
          onTap: (int index) {},
        ));
  }

  void showOpenAccountDialog() {
    showDialog(
        context: context,
        child: DialogOpenAccount(onTap: (int type) {
          if (type == 0) {
            open(RouterName.open_person_account);
          } else {
            open(RouterName.open_company_account);
          }
        }));
  }
}
