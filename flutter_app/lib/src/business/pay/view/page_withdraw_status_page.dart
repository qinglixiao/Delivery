import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';

class PayWithdrawStatusPage extends StatefulWidget {
  @override
  _PayWithdrawStatusPageState createState() => _PayWithdrawStatusPageState();
}

class _PayWithdrawStatusPageState extends State<PayWithdrawStatusPage> with PageBridge {

  bool _isSuccess = false;
  String content ;

  @override
  Widget build(BuildContext context) {

    _isSuccess =  ModalRoute.of(context).settings.arguments;

    return RootPageWidget(
        appBar: IsAppBar(
          title: S.of(context).pay_withdraw_title,
          leftOnTap: () {
            popUtil(RouterName.my_balance);
          },
        ),
        body: Container(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            children: <Widget>[
              SizedBox(height: Values.d_60),
              Container(
                child: _isSuccess?Image.asset('assets/images/status_success.png'):Image.asset('assets/images/status_error.png'),
              ),
              Container(
                margin: EdgeInsets.only(top: Values.d_15),
                child: Text(_isSuccess?S.of(context).pag_withdraw_success_warn:S.of(context).pag_withdraw_error_warn,
                    maxLines: 2,
                    style: TextStyle(
                        fontSize: Values.s_text_20,
                        fontWeight: Values.font_Weight_medium,
                        color: Values.of(context).c_black_front_33,
                        decoration: TextDecoration.none),
                    textAlign: TextAlign.center),
              ),
              SizedBox(height: Values.d_50),
              Container(
                height: Values.d_44,
                margin: EdgeInsets.only(top: Values.d_28, left: Values.d_28, right: Values.d_28),
                child: new SizedBox.expand(
                  child: new FlatButton(
                    onPressed: () {
                      popUtil(RouterName.my_balance);
                    },
                    color:Values.of(context).c_orange_background_0b,
                    splashColor: Values.c_translucent,
                    highlightColor: Values.c_translucent,
                    disabledColor: Values.of(context).c_grey_background_cc,
                    child: new Text(
                      S.of(context).pay_affirm_success_button1,
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
}
