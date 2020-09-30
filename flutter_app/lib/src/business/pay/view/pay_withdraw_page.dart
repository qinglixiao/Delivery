import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/bank_info.dart';
import 'package:flutter_ienglish_fine/src/business/pay/viewmodel/pay_withdraw_view_model.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_ienglish_fine/src/business/budget/bean/good_confirm.dart';

import 'dialog_pay_withdraw.dart';

class PayWithdrawPage extends StatefulWidget {
  @override
  _PayWithdrawPageState createState() => _PayWithdrawPageState();
}

class _PayWithdrawPageState extends State<PayWithdrawPage> with PageBridge {
  PayWithdrawViewModel _viewModel = PayWithdrawViewModel();
  FundQueryDetail _query;
  BankInfoBean _bankInfo;
  String _bankName;
  String _cardId;
  bool _isCanWithdraw = false;
  double _withdrawConfig = 0;
  String _warnInfo = '';

  TextEditingController _numController = new TextEditingController();

  void getWithdrawConfig(){
    if(_withdrawConfig == 0){
      _viewModel.loadWithdrawConfig().then((value) {
        if (value.isSuccess()) {
          _withdrawConfig = double.parse(value.configValue);
        }
      });
    }
  }

  void handleArguments(Map argumnets){
    _query = argumnets["query"];
    _bankInfo = argumnets["bank"];

    if (!StringUtil.isEmpty(_bankInfo?.enterpriseBankName) &&
        !(StringUtil.isEmpty(_bankInfo?.enterpriseBankCode))) {
      _bankName = _bankInfo?.bankName;
      _cardId = _bankInfo?.enterpriseBankCode;
      _cardId = _cardId.substring(_bankInfo.enterpriseBankCode.length - 4);
    } else if (!StringUtil.isEmpty(_bankInfo?.bankName) &&
        !(StringUtil.isEmpty(_bankInfo?.bankCode))) {
      _bankName = _bankInfo?.bankName;
      _cardId = _bankInfo?.bankCode;
      _cardId = _cardId.substring(_bankInfo.bankCode.length - 4);
    }
  }

  void handleWithdrawNum(String value){
    if(double.parse(value) > 0 && double.parse(value) <= 50000){
      setState(() {
        _isCanWithdraw = true;
        _warnInfo = '';
      });
    }
    else{
      if(double.parse(value) > 50000){
        setState(() {
          _warnInfo = S.of(context).pag_withdraw_error_1;
          _isCanWithdraw = false;
        });
      }
      else if(double.parse(value) > _withdrawConfig){
        setState(() {
          _warnInfo = S.of(context).pag_withdraw_error_2;
          _isCanWithdraw = false;
        });
      }
      else{
        setState(() {
          _isCanWithdraw = false;
        });
      }
    }
  }

  void submit(){
    showDialog(context: context,child: DialogPayWithdraw(withdrawAmountTitle:'￥'+_numController.text, serviceCharge: '￥0.5', withdrawReality: "￥"+(double.parse(_numController.text)-0.5).toString(), onTap: (){
      if(_isCanWithdraw){
        _viewModel.loadWithdrawData(_numController.text).then((value){
          if (value.isSuccess()) {
            open(RouterName.pay_status,argument: {'status':true,'title':S.of(context).pay_withdraw_title,'content':S.of(context).pag_withdraw_success_warn,'router':RouterName.my_balance,'action':S.of(context).pay_affirm_success_button1});
          }
          else {
            open(RouterName.pay_status,argument: {'status':false,'title':S.of(context).pay_withdraw_title,'content':S.of(context).pag_withdraw_error_warn,'router':RouterName.my_balance,'action':S.of(context).pay_affirm_success_button1});
          }
        });
      }
    }));
  }


  @override
  Widget build(BuildContext context) {

     getWithdrawConfig();

    handleArguments(ModalRoute.of(context).settings.arguments);

    return RootPageWidget(
        appBar: IsAppBar(
          title: S.of(context).pay_withdraw_title,
          leftOnTap: () {
            pop();
          },
        ),
        body: Container(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Container(
                  margin:
                      EdgeInsets.only(top: Values.d_12, bottom: Values.d_12),
                  padding: EdgeInsets.only(
                      left: Values.d_15,
                      right: Values.d_15,
                      top: Values.d_12,
                      bottom: Values.d_12),
                  color: Values.of(context).c_white_background,
                  child: Row(
                    children: <Widget>[
                      Image.asset(''),
                      Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: <Widget>[
                          Text(
                            StringUtil.getNotNullString(_bankName),
                            style: TextStyle(
                                color: Values.of(context).c_black_front_33,
                                fontWeight: Values.font_Weight_medium,
                                fontSize: Values.s_text_15),
                          ),
                          Row(
                            children: <Widget>[
                              Text(
                                S.of(context).pay_bank_card_type +
                                    ' | ' +
                                    S.of(context).pay_bank_card_tail_number,
                                style: TextStyle(
                                    color: Values.of(context).c_black_front_99,
                                    fontWeight: Values.font_Weight_normal,
                                    fontSize: Values.s_text_14),
                              ),
                              Text(
                                StringUtil.getNotNullString(_cardId),
                                style: TextStyle(
                                    color: Values.of(context).c_black_front_99,
                                    fontWeight: Values.font_Weight_normal,
                                    fontSize: Values.s_text_14),
                              ),
                            ],
                          )
                        ],
                      )
                    ],
                  )),
              Container(
                padding: EdgeInsets.only(
                    left: Values.d_15,
                    right: Values.d_15,
                    top: Values.d_15,
                    bottom: Values.d_15),
                color: Values.of(context).c_white_background,
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: <Widget>[
                    Text(
                      S.of(context).pay_withdraw_amount_title,
                      style: TextStyle(
                          color: Values.of(context).c_black_front_33,
                          fontWeight: Values.font_Weight_normal,
                          fontSize: Values.s_text_15),
                    ),
                    SizedBox(height: Values.d_20),
                    Row(
                      crossAxisAlignment: CrossAxisAlignment.end,
                      children: <Widget>[
                        Container(
                          margin: EdgeInsets.only(bottom: Values.d_5),
                          child: Text(
                            '￥',
                            style: TextStyle(
                                color: Values.of(context).c_black_front_99,
                                fontWeight: Values.font_Weight_normal,
                                fontSize: Values.s_text_18),
                          ),
                        ),
                        Expanded(
                          child: TextField(
                            controller: _numController,
                            textAlign: TextAlign.left,
                            keyboardType: TextInputType.number,
                            style: TextStyle(
                                color: Values.of(context).c_black_front_33,
                                fontWeight: Values.font_Weight_medium,
                                fontSize: Values.s_text_40,
                                textBaseline: TextBaseline.ideographic),
                            decoration: InputDecoration(
                                enabledBorder: UnderlineInputBorder(
                                  borderSide:
                                      BorderSide(color: Values.c_translucent),
                                ),
                                focusedBorder: UnderlineInputBorder(
                                  borderSide:
                                      BorderSide(color: Values.c_translucent),
                                ),
                                contentPadding: EdgeInsets.only(bottom: Values.d_2),
                                hintText:
                                    S.of(context).pay_withdraw_amount_hint,
                                hintStyle: TextStyle(
                                    color: Values.of(context).c_black_front_99,
                                    fontWeight: Values.font_Weight_normal,
                                    fontSize: Values.s_text_14)),
                            onSubmitted: (value) {},
                            onChanged: (value){
                              handleWithdrawNum(value);
                            },
                            cursorColor: Values.of(context).c_black_front_33,
                          ),
                        ),
                      ],
                    ),
                    Container(
                      color: Values.of(context).c_grey_ea,
                      height: 1,
                    ),
                    SizedBox(height: Values.d_15),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: <Widget>[
                        Row(
                          children: <Widget>[
                            Image.asset('assets/images/warn_icon_orange.png'),
                            SizedBox(width: Values.d_5),
                            Text(
                              S.of(context).pay_withdraw_warn,
                              style: TextStyle(
                                  color: Values.of(context).c_black_front_99,
                                  fontWeight: Values.font_Weight_normal,
                                  fontSize: Values.s_text_12),
                            ),
                          ],
                        ),
                        Row(
                          children: <Widget>[
                            Text(
                              S.of(context).pay_withdraw_amount +
                                  _query?.bal?.toStringAsFixed(2),
                              style: TextStyle(
                                  color: Values.of(context).c_black_front_99,
                                  fontWeight: Values.font_Weight_normal,
                                  fontSize: Values.s_text_14),
                            ),
                            SizedBox(width: Values.d_12),
                            GestureDetector(
                              onTap: () {
                                _numController.text =
                                    _query?.bal?.toStringAsFixed(2);
                                handleWithdrawNum(_query?.bal?.toStringAsFixed(2));
                              },
                              child: Text(
                                S.of(context).pay_withdraw_amount_all,
                                style: TextStyle(
                                    color: Values.of(context).c_blue_front_f3,
                                    fontWeight: Values.font_Weight_normal,
                                    fontSize: Values.s_text_14),
                              ),
                            )
                          ],
                        )
                      ],
                    ),
                  ],
                ),
              ),
              Container(
                margin: EdgeInsets.only(top: Values.d_5,left: Values.d_15),
                child: Text(
                  StringUtil.getNotNullString(_warnInfo),
                  style: TextStyle(
                      color: Values.of(context).c_red_front_68,
                      fontWeight: Values.font_Weight_normal,
                      fontSize: Values.s_text_12),
                ),
              ),
              Container(
                height: Values.d_44,
                margin: EdgeInsets.only(top: Values.d_28, left: Values.d_28, right: Values.d_28),
                child: new SizedBox.expand(
                  child: new FlatButton(
                    onPressed: () {
                      submit();
                    },
                    color: _isCanWithdraw
                        ? Values.of(context).c_orange_background_0b
                        : Values.of(context).c_grey_background_cc,
                    splashColor: Values.c_translucent,
                    highlightColor: Values.c_translucent,
                    disabledColor: Values.of(context).c_grey_background_cc,
                    child: new Text(
                      S.of(context).pay_withdraw_amount_action,
                      style: TextStyle(
                        fontSize: Values.s_text_16,
                        color: Values.of(context).c_white_front,
                      ),
                    ),
                    shape: new RoundedRectangleBorder(
                        borderRadius: new BorderRadius.circular(44.0)),
                  ),
                ),
              )
            ],
          ),
        ));
  }

  @override
  void dispose() {
    _viewModel.dispose();
    super.dispose();
  }

}
