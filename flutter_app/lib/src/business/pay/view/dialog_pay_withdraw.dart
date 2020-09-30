import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';

class DialogPayWithdraw extends StatefulWidget {
  final String withdrawAmountTitle;
  final String serviceCharge;
  final String withdrawReality;
  final Function() onTap;

  const DialogPayWithdraw({
    Key key,
    @required this.withdrawAmountTitle,
    @required this.serviceCharge,
    @required this.withdrawReality,
    @required this.onTap,
  }) : super(key: key);

  @override
  _DialogPayWithdrawState createState() => _DialogPayWithdrawState();
}

class _DialogPayWithdrawState extends State<DialogPayWithdraw> {
  @override
  Widget build(BuildContext context) {
    return Padding(
        padding: EdgeInsets.all(0.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Container(
              padding: EdgeInsets.symmetric(horizontal: Values.d_50),
              child: Container(
                padding: EdgeInsets.only(left: Values.d_26,right: Values.d_26,bottom: Values.d_26),
                decoration: BoxDecoration(
                    color: Values.of(context).c_white_background,
                    borderRadius: BorderRadius.all(Radius.circular(5))),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: <Widget>[
                    Row(
                      mainAxisAlignment: MainAxisAlignment.end,
                      children: <Widget>[
                        GestureDetector(
                          onTap: () {
                            Navigator.pop(context);
                          },
                          child: Container(
                            margin: EdgeInsets.only(top: Values.d_12),
                            child:
                            Image.asset('assets/images/button_close.png'),
                          ),
                        )
                      ],
                    ),
                    Container(
                      margin: EdgeInsets.only(left: Values.d_20),
                      child: Text(S.of(context).warn_title,
                          style: TextStyle(
                              fontSize: Values.s_text_18,
                              fontWeight: Values.font_Weight_medium,
                              color: Values.of(context).c_black_front_33,
                              decoration: TextDecoration.none),
                          textAlign: TextAlign.center),
                    ),
                    SizedBox(height: Values.d_12),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: <Widget>[
                        Text(S.of(context).pag_withdraw_amount_title,
                            style: TextStyle(
                                fontSize: Values.s_text_14,
                                fontWeight: Values.font_Weight_medium,
                                color: Values.of(context).c_black_front_33,
                                decoration: TextDecoration.none),
                            textAlign: TextAlign.center),
                        Text(widget.withdrawAmountTitle,
                            style: TextStyle(
                                fontSize: Values.s_text_14,
                                fontWeight: Values.font_Weight_medium,
                                color: Values.of(context).c_black_front_33,
                                decoration: TextDecoration.none),
                            textAlign: TextAlign.center),
                      ],
                    ),
                    SizedBox(height: Values.d_12),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: <Widget>[
                        Text(S.of(context).pag_withdraw_service_charge,
                            style: TextStyle(
                                fontSize: Values.s_text_14,
                                fontWeight: Values.font_Weight_medium,
                                color: Values.of(context).c_black_front_33,
                                decoration: TextDecoration.none),
                            textAlign: TextAlign.center),
                        Text(widget.serviceCharge,
                            style: TextStyle(
                                fontSize: Values.s_text_14,
                                fontWeight: Values.font_Weight_medium,
                                color: Values.of(context).c_black_front_33,
                                decoration: TextDecoration.none),
                            textAlign: TextAlign.center),
                      ],
                    ),
                    SizedBox(height: Values.d_12),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: <Widget>[
                        Text(S.of(context).pag_withdraw_reality,
                            style: TextStyle(
                                fontSize: Values.s_text_14,
                                fontWeight: Values.font_Weight_medium,
                                color: Values.of(context).c_black_front_33,
                                decoration: TextDecoration.none),
                            textAlign: TextAlign.center),
                        Text(widget.withdrawReality,
                            style: TextStyle(
                                fontSize: Values.s_text_14,
                                fontWeight: Values.font_Weight_medium,
                                color: Values.of(context).c_black_front_33,
                                decoration: TextDecoration.none),
                            textAlign: TextAlign.center),
                      ],
                    ),
                    SizedBox(height: Values.d_26),
                    Container(
                      height: 36,
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: <Widget>[
                          Expanded(
                            child: Container(
                              padding: EdgeInsets.symmetric(horizontal: 10),
                              child: OutlineButton(
                                  highlightColor: Values.c_translucent,
                                  splashColor: Values.c_translucent,
                                  color: Values.of(context).c_white_background,
                                  child: Text(
                                    S.of(context).warn_cancel,
                                    style: TextStyle(
                                        fontSize: Values.s_text_14,
                                        fontWeight: Values.font_Weight_normal,
                                        color: Values.of(context).c_orange_front_0b,
                                        decoration: TextDecoration.none),
                                  ),
                                  shape: new RoundedRectangleBorder(
                                      borderRadius:
                                      new BorderRadius.circular(36.0)),
                                  borderSide: new BorderSide(
                                      color: Values.of(context).c_orange_front_0b),
                                  highlightedBorderColor: Values.of(context).c_white_front,
                                  onPressed: () {
                                    Navigator.pop(context);
                                  }),
                            ),
                          ),
                          Expanded(
                              child: Container(
                                padding: EdgeInsets.symmetric(horizontal: 10),
                                child: FlatButton(
                                    highlightColor: Values.c_translucent,
                                    splashColor: Values.c_translucent,
                                    color: Values.of(context).c_orange_front_0b,
                                    child: Text(
                                      S.of(context).warn_sure,
                                      style: TextStyle(
                                          fontSize: Values.s_text_14,
                                          fontWeight: Values.font_Weight_normal,
                                          color: Values.of(context).c_white_front,
                                          decoration: TextDecoration.none),
                                    ),
                                    shape: new RoundedRectangleBorder(
                                        borderRadius:
                                        new BorderRadius.circular(36.0)),
                                    onPressed: () {
                                      Navigator.pop(context);
                                      widget.onTap();
                                    }),
                              ))
                        ],
                      ),
                    )
                  ],
                ),
              ),
            )
          ],
        ));
  }
}
