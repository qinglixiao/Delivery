import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/src/business/budget/comm/interfaces.dart';

class DialogProtocol extends StatefulWidget {
  final Function() onTap;
  final Function(String path) linkOnTap;

  const DialogProtocol({
    Key key,
    @required this.onTap,
    @required this.linkOnTap,
  }) : super(key: key);

  @override
  _DialogProtocolState createState() => _DialogProtocolState();
}

class _DialogProtocolState extends State<DialogProtocol> {
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
                padding: EdgeInsets.symmetric(horizontal: Values.d_36),
                child: Stack(children: <Widget>[
                  Container(
                    decoration: BoxDecoration(
                        color: Values.of(context).c_white_background,
                        borderRadius: BorderRadius.all(Radius.circular(5))),
                    child: Column(children: <Widget>[
                      SizedBox(
                        height: 14,
                      ),
                      Text(
                        S.of(context).warn_protocol_title,
                        style: TextStyle(
                            fontSize: Values.s_text_16,
                            fontWeight: Values.font_Weight_medium,
                            color: Values.of(context).c_black_front_33,
                            decoration: TextDecoration.none),
                      ),
                      Container(
                        height: 1,
                        color: Values.of(context).c_grey_ea,
                        margin: EdgeInsets.only(top: Values.d_15, left: Values.d_11, right: Values.d_11),
                      ),
                      Container(
                        margin: EdgeInsets.only(top: Values.d_20, left: Values.d_26, right: Values.d_26),
                        alignment: Alignment.centerLeft,
                        child: Text(
                          S.of(context).warn_protocol_contnet,
                          style: TextStyle(
                              fontSize: Values.s_text_12,
                              fontWeight: Values.font_Weight_medium,
                              color: Values.of(context).c_black_front_33,
                              decoration: TextDecoration.none),
                        ),
                      ),
                      Container(
                        alignment: Alignment.centerLeft,
                        margin: EdgeInsets.only(top: Values.d_5, left: Values.d_26, right: Values.d_26),
                        child: GestureDetector(
                          onTap: () {
                            widget.linkOnTap(NET_WEB_ORDER_GOODS);
                          },
                          child: new Text(
                            '·' + S.of(context).warn_protocol_1,
                            softWrap: true,
                            maxLines: 2,
                            overflow: TextOverflow.ellipsis,
                            style: TextStyle(
                                fontSize: Values.s_text_12,
                                fontWeight: Values.font_Weight_normal,
                                color: Values.of(context).c_blue_background_f3,
                                decoration: TextDecoration.none),
                          ),
                        ),
                      ),
                      Container(
                        alignment: Alignment.centerLeft,
                        margin: EdgeInsets.only(top: Values.d_5, left: Values.d_26, right: Values.d_26),
                        child: GestureDetector(
                          onTap: () {
                            widget.linkOnTap(NET_WEB_DISCLAIMER);
                          },
                          child: new Text(
                            '·' + S.of(context).warn_protocol_2,
                            softWrap: true,
                            maxLines: 2,
                            overflow: TextOverflow.ellipsis,
                            style: TextStyle(
                                fontSize: Values.s_text_12,
                                fontWeight: Values.font_Weight_normal,
                                color: Values.of(context).c_blue_background_f3,
                                decoration: TextDecoration.none),
                          ),
                        ),
                      ),
                      Container(
                        alignment: Alignment.centerLeft,
                        margin: EdgeInsets.only(top: Values.d_5, left: Values.d_26, right: Values.d_26),
                        child: GestureDetector(
                          onTap: () {
                            widget.linkOnTap(NET_WEB_SERVICE_AGREEMENT);
                          },
                          child: new Text(
                            '·' + S.of(context).warn_protocol_3,
                            softWrap: true,
                            maxLines: 2,
                            overflow: TextOverflow.ellipsis,
                            style: TextStyle(
                                fontSize: Values.s_text_12,
                                fontWeight: Values.font_Weight_normal,
                                color: Values.of(context).c_blue_background_f3,
                                decoration: TextDecoration.none),
                          ),
                        ),
                      ),
                      Container(
                        margin: EdgeInsets.only(top: Values.d_15, left: Values.d_20, right: Values.d_20),
                        alignment: Alignment.centerLeft,
                        child: Text(
                          S.of(context).warn_protocol_other_title,
                          style: TextStyle(
                              fontSize: Values.s_text_12,
                              fontWeight: Values.font_Weight_medium,
                              color: Values.of(context).c_black_front_33,
                              decoration: TextDecoration.none),
                        ),
                      ),
                      Container(
                        margin: EdgeInsets.only(top: Values.d_8, left: Values.d_26, right: Values.d_26),
                        alignment: Alignment.centerLeft,
                        child: Text(
                          S.of(context).warn_protocol_other,
                          style: TextStyle(
                              fontSize: Values.s_text_11,
                              fontWeight: Values.font_Weight_normal,
                              color: Values.of(context).c_black_front_66,
                              decoration: TextDecoration.none),
                        ),
                      ),
                      Container(
                        margin: EdgeInsets.only(top: Values.d_11, left: Values.d_26, right: Values.d_26),
                        alignment: Alignment.centerLeft,
                        child: Text(
                          S.of(context).warn_protocol_stress,
                          style: TextStyle(
                              fontSize: Values.s_text_12,
                              fontWeight: Values.font_Weight_medium,
                              color: Values.of(context).c_black_front_33,
                              decorationColor: Values.of(context).c_black_front_33,
                              decoration: TextDecoration.underline,
                              decorationStyle: TextDecorationStyle.solid),
                        ),
                      ),
                      Container(
                        height: 1,
                        color: Values.of(context).c_grey_ea,
                        margin: EdgeInsets.only(top: Values.d_26, left: Values.d_11, right: Values.d_11),
                      ),
                      Container(
                        margin: EdgeInsets.only(left: Values.d_26, right: Values.d_26, top: Values.d_18),
                        child: Row(
                          children: <Widget>[
                            GestureDetector(
                                onTap: () {
                                  setState(() {
                                    _isAgree = !_isAgree;
                                  });
                                },
                                child: Container(
                                  margin: EdgeInsets.only(right: Values.d_5),
                                  width: 30,
                                  height: 30,
                                  child: _isAgree
                                      ? Image.asset(
                                          'assets/images/button_select.png',
                                        )
                                      : Image.asset(
                                          'assets/images/button_unSelect.png',
                                        ),
                                )),
                            Expanded(
                              child: Text(
                                S.of(context).warn_protocol_agree,
                                style: TextStyle(
                                    fontSize: Values.s_text_11,
                                    fontWeight: Values.font_Weight_normal,
                                    color: Values.of(context).c_black_front_99,
                                    decoration: TextDecoration.none),
                              ),
                            )
                          ],
                        ),
                      ),
                      Container(
                        height: Values.d_44,
                        margin: EdgeInsets.only(
                            left: Values.d_30, right: Values.d_30, bottom: Values.d_15, top: Values.d_15),
                        child: new SizedBox.expand(
                          child: FlatButton(
                              highlightColor: Values.c_translucent,
                              splashColor: Values.c_translucent,
                              color: _isAgree
                                  ? Values.of(context).c_orange_background_0b
                                  : Values.of(context).c_grey_background_cc,
                              child: Text(
                                S.of(context).warn_protocol_button,
                                style: TextStyle(
                                    fontSize: Values.s_text_18,
                                    fontWeight: Values.font_Weight_normal,
                                    color: Values.of(context).c_white_front,
                                    decoration: TextDecoration.none),
                              ),
                              shape: new RoundedRectangleBorder(borderRadius: new BorderRadius.circular(Values.d_44)),
                              onPressed: () {
                                if (_isAgree) {
                                  Navigator.pop(context);
                                  widget.onTap();
                                }
                              }),
                        ),
                      )
                    ]),
                  ),
                  Container(
                    alignment: Alignment.centerRight,
                    margin: EdgeInsets.only(right: 0),
                    child: GestureDetector(
                        onTap: () {
                          Navigator.pop(context);
                        },
                        child: Container(
                            margin: EdgeInsets.only(right: Values.d_12, top: Values.d_12),
                            child: Image.asset(
                              'assets/images/button_close.png',
                            ))),
                  )
                ])),
          ],
        ));
  }
}
