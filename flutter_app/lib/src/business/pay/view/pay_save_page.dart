 import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/bank_info.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/pay_platform.dart';
import 'package:flutter_ienglish_fine/src/business/pay/comm/interfaces.dart';
import 'package:flutter_ienglish_fine/src/business/pay/viewmodel/balance_view_model.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_ienglish_fine/src/business/budget/bean/good_confirm.dart';
import 'package:flutter_ienglish_fine/src/business/pay/viewmodel/pay_affirm_view_model.dart';

class PaySavePage extends StatefulWidget {
  @override
  _PaySavePageState createState() => _PaySavePageState();
}

class _PaySavePageState extends State<PaySavePage> with PageBridge {
  FundQueryDetail _query;
  BankInfoBean _bankInfo;
  String _bankInfoStr='';
  BalanceViewModel _viewModel = BalanceViewModel();

  @override
  void initState() {
    // TODO: implement initState
    super.initState();

  }

  @override
  Widget build(BuildContext context) {
    Map argumnets = ModalRoute.of(context).settings.arguments;
    if(argumnets != null){
      _query = argumnets["query"];
      _bankInfo = argumnets["bank"];
    }
    if(_bankInfo == null && _query == null){
      _viewModel.getBalanceData();
    }
    return RootPageWidget(
        appBar: IsAppBar(
          title: S.of(context).balance_save,
          leftOnTap: () {
            pop();
          },
        ),
        viewModel: _viewModel,
        body:_bankInfo == null ? StreamBuilder(
            stream: _viewModel.streamBankInfo,
            builder: (BuildContext context, AsyncSnapshot<BankInfoBean> snapshot) {
              if (snapshot?.data != null) {
                _bankInfo = snapshot.data;
                if(!StringUtil.isEmpty(_bankInfo?.enterpriseBankName)&&!(StringUtil.isEmpty(_bankInfo?.enterpriseBankCode))){
                  _bankInfoStr = _bankInfo?.enterpriseBankName;
                  String cardId = _bankInfo?.enterpriseBankCode;
                  cardId = cardId.substring(_bankInfo?.enterpriseBankCode.length-4);
                  _bankInfoStr = _bankInfoStr+'($cardId)';
                }
                else if(!StringUtil.isEmpty(_bankInfo?.bankName)&&!(StringUtil.isEmpty(_bankInfo?.bankCode))){
                  _bankInfoStr = _bankInfo?.bankName;
                  String cardId = _bankInfo?.bankCode;
                  cardId = cardId.substring(_bankInfo.bankCode.length-4);
                  _bankInfoStr = _bankInfoStr+'($cardId)';
                }
              }
              if(_bankInfo == null){
                return Container();
              }
              else{
                return StreamBuilder(
                    stream:_viewModel.streamFundQuery,
                    builder: (BuildContext context,
                        AsyncSnapshot<FundQuery> snapshot) {
                      if (snapshot?.data != null) {
                        _query = snapshot?.data?.data;
                      }
                      if(_query == null){
                        return Container();
                      }
                      return ContentWidget();
                    });
              }
            }): ContentWidget());
  }

  Widget ContentWidget(){
    return Container(
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          SizedBox(height: Values.d_12),
          itemWidget(S.of(context).pay_save_name, _bankInfo?.realName, '', '', () {}),
          lineWidget(),
          itemWidget(S.of(context).pay_save_type, S.of(context).pay_save_type_content, S.of(context).pay_save_type_detail, '', () {}),
          SizedBox(height: Values.d_12),
          itemWidget(S.of(context).pay_save_open_name, _bankInfo?.realName, '', '', () {}),
          lineWidget(),
          itemWidget(S.of(context).pay_save_open_bank, _bankInfo?.merchantBankName, '', '', () {}),
          lineWidget(),
          itemWidget(S.of(context).pay_save_bank_cardId, _bankInfo?.cardNo, '', S.of(context).copy_title, () {
            Clipboard.setData(ClipboardData(text: _bankInfo?.cardNo));
            ProgressHUD.showText(warnText: S.of(context).copy_success_title);
          }),
          SizedBox(height: Values.d_12),
          itemWidget(S.of(context).pay_save_balance, '¥'+_query?.bal?.toStringAsFixed(2), '', '', () {}),
          SizedBox(height: Values.d_12),
          GestureDetector(
            onTap: (){
              open(RouterName.web_view,argument: WebParams(url: NetworkConfig().getHostForWeb() + NET_WEB_PAY_TRANSFER ,title: S.of(context).pay_save_detail_warn));
            },
            child: Container(
              padding: EdgeInsets.only(top: Values.d_18, bottom: Values.d_18),
              color: Values.of(context).c_white_background,
              child: Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: <Widget>[
                  Text(
                    S.of(context).pay_save_detail_warn,
                    style: TextStyle(
                        color: Values.of(context).c_red_front_68,
                        fontWeight: Values.font_Weight_medium,
                        fontSize: Values.s_text_16),
                  )
                ],
              ),
            ),
          ),
          Container(
            margin: EdgeInsets.only(top: Values.d_18, left: Values.d_10),
            child: Text(S.of(context).pay_save_warn,
                maxLines: 2,
                textAlign: TextAlign.start,
                style: TextStyle(
                    color: Values.of(context).c_black_front_99,
                    fontWeight: Values.font_Weight_normal,
                    fontSize: Values.s_text_12)),
          )
        ],
      ),
    );
  }

  Widget lineWidget(){
    return Container(
      padding: EdgeInsets.only(left: Values.d_15, right: Values.d_15),
      color: Values.of(context).c_white_background,
      height: 1,
      child: Container(
        color: Values.of(context).c_grey_ea,
      ),
    );
  }

  Widget itemWidget(String title, String content, String detail, String action,
      Function() callBack) {
    return Container(
      color: Values.of(context).c_white_background,
      padding: EdgeInsets.only(left: Values.d_15, right: Values.d_15),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: <Widget>[
          Container(
            padding: EdgeInsets.only(top: Values.d_18, bottom: Values.d_18),
            child: Text(
              title,
              style: TextStyle(
                  color: Values.of(context).c_black_front_33,
                  fontWeight: Values.font_Weight_normal,
                  fontSize: Values.s_text_15),
            ),
          ),
          Column(
            crossAxisAlignment: CrossAxisAlignment.end,
            children: <Widget>[
              GestureDetector(
                onTap: () {
                  callBack();
                },
                child: Row(
                  children: <Widget>[
                    Text(
                      StringUtil.getNotNullString(content),
                      style: TextStyle(
                          color: Values.of(context).c_black_front_33,
                          fontWeight: Values.font_Weight_medium,
                          fontSize: Values.s_text_15),
                    ),
                    Container(
                      margin: EdgeInsets.only(left: Values.d_5),
                      child: !StringUtil.isEmpty(action)
                          ? Text(
                              action,
                              style: TextStyle(
                                  color: Values.of(context).c_blue_front_f3,
                                  fontWeight: Values.font_Weight_normal,
                                  fontSize: Values.s_text_15),
                            )
                          : Container(),
                    ),
                  ],
                ),
              ),
              !StringUtil.isEmpty(detail)
                  ? Text(
                      detail,
                      style: TextStyle(
                          color: Values.of(context).c_red_front_68,
                          fontWeight: Values.font_Weight_normal,
                          fontSize: Values.s_text_8),
                    )
                  : Container()
            ],
          )
        ],
      ),
    );
  }
}
