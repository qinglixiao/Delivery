import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/src/business/budget/bean/good_confirm.dart';
import 'package:flutter_ienglish_fine/src/business/login/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';

class DialogRiseInPrice extends StatefulWidget {
  final PersionStatusInfo statusInfo;
  final int buyNum;
  final double price;
  final Function(int type) onTap;
  final Function(String path) linkOnTap;

  const DialogRiseInPrice({
    Key key,
    @required this.statusInfo,
    @required this.onTap,
    @required this.buyNum,
    @required this.price,
    @required this.linkOnTap,
  }) : super(key: key);

  @override
  _DialogRiseInPriceState createState() => _DialogRiseInPriceState();
}

class _DialogRiseInPriceState extends State<DialogRiseInPrice> {
  bool _isAgree = false;

  @override
  Widget build(BuildContext context) {
    return Padding(
        padding: EdgeInsets.all(0.0),
        child: Column(
          textDirection: TextDirection.ltr,
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            Container(
              padding: EdgeInsets.symmetric(horizontal: Values.d_50),
              child: Column(children: <Widget>[
                Container(
                    child: Stack(
                  children: <Widget>[
                    Container(
                      color: Values.of(context).c_white_background,
                      margin: EdgeInsets.only(top: Values.d_30),
                      height: 220,
                    ),
                    Row(
                      children: <Widget>[
                        Expanded(
                          child: Image.asset(
                            'assets/images/rise_in_price.png',
                            fit: BoxFit.fill,
                          ),
                        ),
                      ],
                    ),
                    Row(
                      children: <Widget>[
                        Expanded(
                            child: Container(
                          decoration: BoxDecoration(
                              color: Values.of(context).c_white_background,
                              borderRadius:
                                  BorderRadius.all(Radius.circular(Values.d_5)),
                              boxShadow: [
                                BoxShadow(
                                    color: Values.of(context).c_orange_background_34,
                                    offset: Offset(0.0, 0.0),
                                    blurRadius: Values.d_5,
                                    spreadRadius: Values.d_2)
                              ]),
                          margin: EdgeInsets.only(
                              left: Values.d_26, right: Values.d_26, top: Values.d_100),
                          padding: EdgeInsets.all(Values.d_12),
//                          height: 134,
                          child: Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: <Widget>[
                              Text(
                                S.of(context).rise_in_price_title,
                                style: TextStyle(
                                    fontSize: Values.s_text_14,
                                    fontWeight: Values.font_Weight_normal,
                                    color: Values.of(context).c_black_front_33,
                                    decoration: TextDecoration.none),
                              ),
                              SizedBox(height: Values.d_5),
                              Row(
                                mainAxisAlignment: MainAxisAlignment.end,
                                crossAxisAlignment: CrossAxisAlignment.end,
                                children: <Widget>[
                                  Text(
                                    (widget.buyNum*widget.statusInfo.extraFee.toInt()).toString(),
                                    style: TextStyle(
                                        fontSize: Values.s_text_30,
                                        fontWeight: Values.font_Weight_medium,
                                        color: Values.of(context)
                                            .c_orange_front_0b,
                                        decoration: TextDecoration.none),
                                  ),
                                  Container(
                                    padding:
                                        EdgeInsets.only(bottom: Values.d_2),
                                    child: Text(
                                      S.of(context).rise_in_price_until,
                                      style: TextStyle(
                                          fontSize: Values.s_text_12,
                                          fontWeight: Values.font_Weight_medium,
                                          color: Values.of(context)
                                              .c_orange_front_0b,
                                          decoration: TextDecoration.none),
                                    ),
                                  )
                                ],
                              ),
                              SizedBox(height: Values.d_10),
                              Text.rich(
                                TextSpan(
                                  text: S.of(context).rise_in_price_warn1,
                                  style: TextStyle(
                                    fontSize: Values.s_text_12,
                                    color: Values.of(context).c_black_front_66,
                                    fontWeight: Values.font_Weight_normal,
                                    decoration: TextDecoration.none,
                                  ),
                                  children: [
                                    TextSpan(
                                        text: (widget.statusInfo.extraFee.toInt()).toString(),
                                        style: TextStyle(
                                          color: Values.of(context)
                                              .c_orange_front_0b,
//                                          decoration: TextDecoration.underline,
                                        )),
                                    TextSpan(
                                      text: S.of(context).rise_in_price_warn2,
                                    ),
                                  ],
                                ),
                              ),
                            ],
                          ),
                        ))
                      ],
                    ),
                  ],
                )),
                !widget.statusInfo.ifextraFee
                    ? Container(
                        padding: EdgeInsets.only(
                            left: Values.d_26, right: Values.d_26),
                        color: Values.of(context).c_white_background,
                        height: Values.d_70,
                        child: Row(
                          children: <Widget>[
                            Expanded(
                                child: Image.asset(
                                    'assets/images/rise_in_price_status.png',
                                    fit: BoxFit.fill))
                          ],
                        ))
                    : Container(),
                widget.statusInfo.ifextraFee
                    ? Container(
                        height: Values.d_30,
                        padding: EdgeInsets.only(
                            left: Values.d_26, right: Values.d_26),
                        color: Values.of(context).c_white_background,
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: <Widget>[
                            Text(
                              S.of(context).rise_in_price_status1,
                              style: TextStyle(
                                  fontSize: Values.s_text_12,
                                  fontWeight: Values.font_Weight_normal,
                                  color: Values.of(context).c_black_front_99,
                                  decoration: TextDecoration.lineThrough,
                                  decorationStyle: TextDecorationStyle.solid,
                                  decorationColor:
                                      Values.of(context).c_black_front_99),
                            ),
                            Text('￥'+ (widget.buyNum*widget.price).toStringAsFixed(2),
                                style: TextStyle(
                                    fontSize: Values.s_text_12,
                                    fontWeight: Values.font_Weight_normal,
                                    color: Values.of(context).c_black_front_99,
                                    decoration: TextDecoration.lineThrough,
                                    decorationStyle: TextDecorationStyle.solid,
                                    decorationColor:
                                        Values.of(context).c_black_front_99)),
                          ],
                        ),
                      )
                    : Container(
                        height: Values.d_30,
                        padding: EdgeInsets.only(
                            left: Values.d_26, right: Values.d_26),
                        color: Values.of(context).c_white_background,
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: <Widget>[
                            Text(
                              S.of(context).rise_in_price_status2,
                              style: TextStyle(
                                  fontSize: Values.s_text_12,
                                  fontWeight: Values.font_Weight_normal,
                                  color: Values.of(context).c_black_front_99,
                                  decoration: TextDecoration.lineThrough,
                                  decorationStyle: TextDecorationStyle.solid,
                                  decorationColor:
                                      Values.of(context).c_black_front_99),
                            ),
                            Text('￥'+(widget.buyNum*(widget.statusInfo.extraFee.toInt()+widget.price)).toStringAsFixed(2),
                                style: TextStyle(
                                    fontSize: Values.s_text_12,
                                    fontWeight: Values.font_Weight_normal,
                                    color: Values.of(context).c_black_front_99,
                                    decoration: TextDecoration.lineThrough,
                                    decorationStyle: TextDecorationStyle.solid,
                                    decorationColor:
                                        Values.of(context).c_black_front_99)),
                          ],
                        ),
                      ),
                widget.statusInfo.ifextraFee
                    ? Container(
                        height: Values.d_30,
                        padding: EdgeInsets.only(
                            left: Values.d_26, right: Values.d_26),
                        color: Values.of(context).c_white_background,
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: <Widget>[
                            Text(
                              S.of(context).rise_in_price_status2,
                              style: TextStyle(
                                  fontSize: Values.s_text_12,
                                  fontWeight: Values.font_Weight_normal,
                                  color: Values.of(context).c_black_front_33,
                                  decoration: TextDecoration.none),
                            ),
                            Row(
                              children: <Widget>[
                                Text(
                                  '￥',
                                  style: TextStyle(
                                      fontSize: Values.s_text_12,
                                      fontWeight: Values.font_Weight_normal,
                                      color:
                                          Values.of(context).c_black_front_33,
                                      decoration: TextDecoration.none),
                                ),
                                Text(
                                  (widget.buyNum*(widget.statusInfo.extraFee.toInt()+widget.price)).toString(),
                                  style: TextStyle(
                                      fontSize: Values.s_text_20,
                                      fontWeight: Values.font_Weight_normal,
                                      color:
                                          Values.of(context).c_black_front_33,
                                      decoration: TextDecoration.none),
                                ),
                              ],
                            )
                          ],
                        ),
                      )
                    : Container(
                        height: Values.d_30,
                        padding: EdgeInsets.only(
                            left: Values.d_26, right: Values.d_26),
                        color: Values.of(context).c_white_background,
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: <Widget>[
                            Text(
                              S.of(context).rise_in_price_status1,
                              style: TextStyle(
                                  fontSize: Values.s_text_12,
                                  fontWeight: Values.font_Weight_normal,
                                  color: Values.of(context).c_black_front_33,
                                  decoration: TextDecoration.none),
                            ),
                            Row(
                              children: <Widget>[
                                Text(
                                  '￥',
                                  style: TextStyle(
                                      fontSize: Values.s_text_12,
                                      fontWeight: Values.font_Weight_normal,
                                      color:
                                          Values.of(context).c_black_front_33,
                                      decoration: TextDecoration.none),
                                ),
                                Text(
                                  (widget.buyNum*widget.price).toString(),
                                  style: TextStyle(
                                      fontSize: Values.s_text_20,
                                      fontWeight: Values.font_Weight_normal,
                                      color:
                                          Values.of(context).c_black_front_33,
                                      decoration: TextDecoration.none),
                                ),
                              ],
                            )
                          ],
                        ),
                      ),
                Container(
                  height: Values.d_44,
                  color: Values.of(context).c_white_background,
                  padding: EdgeInsets.only(
                      left: Values.d_26, right: Values.d_26, top: Values.d_5),
                  child: Row(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget>[
                      GestureDetector(
                          onTap: () {
                            setState(() {
                              _isAgree = !_isAgree;
                            });
                          },
                          child: Container(
                            margin: EdgeInsets.only(right: Values.d_5,top: Values.d_2),
                            child: _isAgree
                                ? Image.asset(
                                    'assets/images/button_select.png',
                                  )
                                : Image.asset(
                                    'assets/images/button_unSelect.png',
                                  ),
                          )),
                      Expanded(
                        child: Text.rich(
                          TextSpan(
                            text: S.of(context).rise_in_price_protocol1,
                            style: TextStyle(
                              fontSize: Values.s_text_11,
                              color: Values.of(context).c_black_front_99,
                              fontWeight: Values.font_Weight_normal,
                              decoration: TextDecoration.none,
                            ),
                            children: [
                              TextSpan(
                                  recognizer: TapGestureRecognizer()
                                    ..onTap = () {
                                      widget.linkOnTap(NET_WEB_AGREMENT);
                                    },
                                  text: S.of(context).agreement_user,
                                  style: TextStyle(
                                    color: Values.of(context).c_blue_front_f3,
//                                          decoration: TextDecoration.underline,
                                  )),
                            ],
                          ),
                        ),
                      )
                    ],
                  ),
                ),
                Container(
                  decoration: BoxDecoration(
                      color: Values.of(context).c_white_background,
                      borderRadius: BorderRadius.only(
                          bottomLeft: Radius.circular(Values.d_5),
                          bottomRight: Radius.circular(Values.d_5))),
                  height: Values.d_70,
                  padding: EdgeInsets.only(
                      left: Values.d_22,
                      right: Values.d_22,
                      bottom: Values.d_26),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: <Widget>[
                      Expanded(
                        child: Container(
                          padding: EdgeInsets.symmetric(horizontal: Values.d_10),
                          child: OutlineButton(
                              highlightColor: Values.c_translucent,
                              splashColor: Values.c_translucent,
                              color: Values.of(context).c_white_background,
                              child: Text(
                                S
                                    .of(context)
                                    .good_confirm_open_account_button_1,
                                style: TextStyle(
                                    fontSize: Values.s_text_14,
                                    fontWeight: Values.font_Weight_normal,
                                    color: Values.of(context)
                                        .c_orange_background_0b,
                                    decoration: TextDecoration.none),
                              ),
                              shape: new RoundedRectangleBorder(
                                  borderRadius:
                                      new BorderRadius.circular(Values.d_36)),
                              borderSide: new BorderSide(
                                  color: Values.of(context)
                                      .c_orange_background_0b),
                              highlightedBorderColor:
                                  Values.of(context).c_orange_background_0b,
                              onPressed: () {
                                if (_isAgree) {
                                  Navigator.pop(context);
                                  widget.onTap(0);
                                }
                                else{
                                  ProgressHUD.showText(warnText: S.of(context).need_agree_title+S.of(context).agreement_user);
                                }
                              }),
                        ),
                      ),
                      Expanded(
                          child: Container(
                        padding: EdgeInsets.symmetric(horizontal: Values.d_10),
                        child: FlatButton(
                            highlightColor: Values.c_translucent,
                            splashColor: Values.c_translucent,
                            color: Values.of(context).c_orange_background_0b,
                            child: Text(
                              S.of(context).good_confirm_open_account_button_3,
                              style: TextStyle(
                                  fontSize: Values.s_text_14,
                                  fontWeight: Values.font_Weight_normal,
                                  color: Values.of(context).c_white_front,
                                  decoration: TextDecoration.none),
                            ),
                            shape: new RoundedRectangleBorder(
                                borderRadius: new BorderRadius.circular(Values.d_36)),
                            onPressed: () {
//                              if (_isAgree) {
                                Navigator.pop(context);
                                widget.onTap(1);
//                              }
//                              else{
//                                ProgressHUD.showText(warnText: S.of(context).need_agree_title+S.of(context).agreement_user);
//                              }
                            }),
                      ))
                    ],
                  ),
                ),
              ]),
            ),
          ],
        ));
  }
}
