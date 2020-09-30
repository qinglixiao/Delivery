import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/pay_method.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/pay_offline.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/pay_platform.dart';
import 'package:flutter_ienglish_fine/src/business/budget/bean/good_confirm.dart';
import 'package:flutter_ienglish_fine/src/business/pay/viewmodel/pay_affirm_view_model.dart';

class PayAffirmPage extends StatefulWidget {
  @override
  _PayAffirmPageState createState() => _PayAffirmPageState();
}

class _PayAffirmPageState extends State<PayAffirmPage> with PageBridge {
  PayAffirmViewModel _payAffirmViewModel = PayAffirmViewModel();
  BuyCollocationInfo _buyCollocationInfo;
  bool _isEnough = true;
  bool _isOnlinePay = true;

  @override
  Widget build(BuildContext context) {
    _buyCollocationInfo = ModalRoute.of(context).settings.arguments;
    FundQueryDetail _queryDetail;

    ///余额信息
    double _balance = 0.0;
    return RootPageWidget(
        appBar: IsAppBar(
          title: S.of(context).pay_affirm_title,
          leftOnTap: () {
            showPayDialog();
          },
        ),
        viewModel: _payAffirmViewModel,
        task: _payAffirmViewModel.getPayAffirmData(),
        body: StreamBuilder(
            stream: _payAffirmViewModel.streamPayPayMethod,
            builder: (BuildContext context, AsyncSnapshot<PayMethod> payMethodSnapshot) {
              if (payMethodSnapshot.data == null) {
                return Container();
              } else {
               if(payMethodSnapshot.data.payMethod != null && payMethodSnapshot.data.payMethod.contains('线下支付')){
                 _isOnlinePay = false;
                 return Container(
                   child: Column(
                     mainAxisAlignment: MainAxisAlignment.spaceBetween,
                     children: <Widget>[
                       Container(
                         margin: EdgeInsets.only(top: Values.d_44),
                         child: Column(
                           children: <Widget>[
                             Container(
                               margin: EdgeInsets.only(top: Values.d_10),
                               child: Text(
                                 _buyCollocationInfo != null
                                     ? ('￥' +
                                     _buyCollocationInfo.payFee
                                         .toStringAsFixed(2))
                                     : '',
                                 style: TextStyle(
                                     color: Values.of(context).c_black_front_33,
                                     fontWeight: Values.font_Weight_medium,
                                     fontSize: Values.s_text_20),
                               ),
                             ),
                             Container(
                               margin: EdgeInsets.only(top: Values.d_10),
                               child: Text(
                                 _buyCollocationInfo != null
                                     ? (S.of(context).pay_affirm_order_title +
                                     _buyCollocationInfo.numberCode)
                                     : '',
                                 style: TextStyle(
                                     color: Values.of(context).c_black_front_66,
                                     fontWeight: Values.font_Weight_medium,
                                     fontSize: Values.s_text_12),
                               ),
                             ),
                             PayMethodWidget(),
                           ],
                         ),
                       ),
                       BottomWidget(),
                     ],
                   ),
                 );
               }
               else{
                 _isOnlinePay = true;
                 _payAffirmViewModel.loadFundQuery();
                 return  StreamBuilder(
                     stream: _payAffirmViewModel.streamFundQuery,
                     builder: (BuildContext context, AsyncSnapshot<FundQuery> snapshot) {
                       if (snapshot.data != null) {
                         _queryDetail = snapshot.data.data;
                         _balance = _queryDetail.bal;
                         if (_balance >= _buyCollocationInfo.payFee) {
                           _isEnough = true;
                         } else {
                           _isEnough = false;
                         }
                       }
                       if (snapshot.data == null) {
                         return Container();
                       } else {
                         return Container(
                           child: Column(
                             mainAxisAlignment: MainAxisAlignment.spaceBetween,
                             children: <Widget>[
                               Container(
                                 margin: EdgeInsets.only(top: Values.d_44),
                                 child: Column(
                                   children: <Widget>[
                                     Container(
                                       margin: EdgeInsets.only(top: Values.d_10),
                                       child: Text(
                                         _buyCollocationInfo != null
                                             ? ('￥' +
                                             _buyCollocationInfo.payFee
                                                 .toStringAsFixed(2))
                                             : '',
                                         style: TextStyle(
                                             color: Values.of(context).c_black_front_33,
                                             fontWeight: Values.font_Weight_medium,
                                             fontSize: Values.s_text_20),
                                       ),
                                     ),
                                     Container(
                                       margin: EdgeInsets.only(top: Values.d_10),
                                       child: Text(
                                         _buyCollocationInfo != null
                                             ? (S.of(context).pay_affirm_order_title +
                                             _buyCollocationInfo.numberCode)
                                             : '',
                                         style: TextStyle(
                                             color: Values.of(context).c_black_front_66,
                                             fontWeight: Values.font_Weight_medium,
                                             fontSize: Values.s_text_12),
                                       ),
                                     ),
                                     BalanceInfoWidget(_queryDetail, _balance),
                                     _isEnough ? Container() : InsufficientWidget(),
                                   ],
                                 ),
                               ),
                               BottomWidget(),
                             ],
                           ),
                         );
                       }
                     });
               }
              }
            }));
  }

  Widget PayMethodWidget() {
    return Container(
      decoration: BoxDecoration(
          color: Values.of(context).c_white_background,
          borderRadius: BorderRadius.all(Radius.circular(5))),
      margin: EdgeInsets.only(
          top: Values.d_70, left: Values.d_12, right: Values.d_12),
      padding: EdgeInsets.all(Values.d_15),
      height: Values.d_70,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Expanded(
            child: Row(
              children: <Widget>[
                Container(
                  width: Values.d_22,
                  height: Values.d_22,
                  child: Image.asset('assets/images/balance.png'),
                ),
                SizedBox(width: 6),
                Text(
                  S.of(context).pay_affirm_offline_title,
                  style: TextStyle(
                      color: Values.of(context).c_black_front_33,
                      fontWeight: Values.font_Weight_medium,
                      fontSize: Values.s_text_16),
                ),
              ],
            ),
            flex: 9,
          ),
        ],
      ),
    );
  }

  Widget BalanceInfoWidget(FundQueryDetail queryDetail, double balance) {
    FundQueryDetail _queryDetail = queryDetail;

    ///余额信息
    double _balance = balance;

    return Container(
      decoration: BoxDecoration(
          color: Values.of(context).c_white_background,
          borderRadius: BorderRadius.all(Radius.circular(5))),
      margin: EdgeInsets.only(
          top: Values.d_70, left: Values.d_12, right: Values.d_12),
      padding: EdgeInsets.all(Values.d_15),
      height: Values.d_135,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Expanded(
            child: Row(
              children: <Widget>[
                Container(
                  width: Values.d_22,
                  height: Values.d_22,
                  child: Image.asset('assets/images/balance.png'),
                ),
                SizedBox(width: 6),
                Text(
                  S.of(context).pay_affirm_balance_title,
                  style: TextStyle(
                      color: Values.of(context).c_black_front_33,
                      fontWeight: Values.font_Weight_medium,
                      fontSize: Values.s_text_16),
                ),
              ],
            ),
            flex: 9,
          ),
          Container(
            color: Values.of(context).c_grey_ea,
            height: 1,
            margin: EdgeInsets.only(top: Values.d_12, bottom: Values.d_12),
          ),
          Expanded(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                Container(
                  margin: EdgeInsets.only(left: Values.d_44),
                  child: Text(
                    S.of(context).pay_affirm_balance_title1 +
                        '￥' +
                        _balance.toStringAsFixed(2),
                    style: TextStyle(
                        color: Values.of(context).c_black_front_99,
                        fontWeight: Values.font_Weight_medium,
                        fontSize: Values.s_text_16),
                  ),
                ),
                Container(
                  margin: EdgeInsets.only(left: Values.d_44),
                  decoration: BoxDecoration(
                      border: Border.all(
                          color: Values.of(context).c_orange_background_0b,
                          width: 0.5),
                      color: Values.of(context).c_white_background,
                      borderRadius:
                          BorderRadius.all(Radius.circular(Values.d_5))),
                  child: Text(
                    S.of(context).pay_affirm_balance_warn,
                    style: TextStyle(
                        color: Values.of(context).c_orange_front_0b,
                        fontWeight: Values.font_Weight_normal,
                        fontSize: Values.s_text_12),
                  ),
                )
              ],
            ),
            flex: 10,
          ),
        ],
      ),
    );
  }

  Widget InsufficientWidget() {
    return Container(
      alignment: Alignment.centerLeft,
      margin: EdgeInsets.only(
          left: (Values.d_44 + Values.d_12 + Values.d_15), top: Values.d_15),
      child: Text(
        S.of(context).pay_affirm_insufficient_warn,
        style: TextStyle(
            color: Values.of(context).c_red_front_68,
            fontWeight: Values.font_Weight_normal,
            fontSize: Values.s_text_15),
      ),
    );
  }

  Widget BottomWidget() {
    return Row(
      children: <Widget>[
        Expanded(
          child: Container(
            color: Values.of(context).c_white_background,
            child: SafeArea(
                child: Container(
              color: Values.of(context).c_orange_background_0b,
              child: FlatButton(
                  splashColor: Values.c_translucent,
                  highlightColor: Values.c_translucent,
                  onPressed: () {
                    if(_isOnlinePay){
                      _isEnough ? payAction() : open(RouterName.pay_save);
                    }
                    else{
                      payOfflineAction();
                    }
                  },
                  child: Text(
                    _isEnough
                        ? S.of(context).pay_affirm_button
                        : S.of(context).pay_affirm_insufficient_button,
                    style: TextStyle(
                        color: Values.of(context).c_white_front,
                        fontWeight: Values.font_Weight_medium,
                        fontSize: Values.s_text_16),
                  )),
            )),
          ),
          flex: 1,
        )
      ],
    );
  }

  void payAction() {
    ProgressHUD.showLoading();
    _payAffirmViewModel.loadPayPlatform(
        '分账通余额支付',
        _buyCollocationInfo.payDesc,
        'https://www.ienglish.cn/app/pay?orderNo='+_buyCollocationInfo.numberCode,
        '服务商',
        _buyCollocationInfo.numberDesc, (info) {
      ProgressHUD.hiddenHUD();
      PayPlatform payPlatform = info as PayPlatform;
      if (payPlatform.error_code == '1') {
        open(RouterName.web_view,
            argument: WebParams(
                url: payPlatform.payurl, route: RouterName.pay_success, routeParams: {"orderNo":_buyCollocationInfo.numberCode}));
      }
      Logger.print('');
    });
  }

  void payOfflineAction(){
    ProgressHUD.showLoading();
    _payAffirmViewModel.loadOfflinePay(_buyCollocationInfo.numberCode).then((value){
      ProgressHUD.hiddenHUD();
      PayOffline bean =value as PayOffline;
      if(bean.data == 'OK'){
        open(RouterName.pay_success,argument: {"orderNo":''});
      }
      else{
        ProgressHUD.showText(warnText: StringUtil.getNotNullString(bean.errors));
      }

    });
  }

  void showPayDialog() {
    showDialog(
        context: context,
        child: DialogTool(
          bgColor: Values.of(context).c_white_background,
          title: S.of(context).pay_affirm_warn_title,
          titleFontSize: Values.s_text_18,
          titleColor: Values.of(context).c_black_front_33,
          content: S.of(context).pay_affirm_warn_content,
          contentColor: Values.of(context).c_black_front_33,
          leftTitle: S.of(context).pay_affirm_warn_cancel,
          leftColor: Values.of(context).c_orange_front_0b,
          leftBgColor: Values.of(context).c_white_background,
          rightTitle: S.of(context).pay_affirm_warn_sure,
          rightColor: Values.of(context).c_white_front,
          rightBgColor: Values.of(context).c_orange_background_0b,
          onTap: (int index) {
            if (index == 1) {
              pop();
            }
          },
        ));
  }
}
