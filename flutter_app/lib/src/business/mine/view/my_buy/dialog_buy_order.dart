import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';

class DialogBuyOrder extends StatefulWidget {
  final Function(String path) onTap;

  const DialogBuyOrder({
    Key key,
    @required this.onTap,
  }) : super(key: key);

  @override
  _DialogBuyOrderState createState() => _DialogBuyOrderState();
}

class _DialogBuyOrderState extends State<DialogBuyOrder> {
  int _selectIndex = 0;
  String _selectMessage;

  @override
  Widget build(BuildContext context) {
    _selectMessage = S.of(context).cancel_reason_1;
    return Container(
      height: 302,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.end,
        children: <Widget>[
          Container(
            decoration: BoxDecoration(
                color: Values.of(context).c_white_background,
                borderRadius: BorderRadius.all(Radius.circular(5))),
            child: Column(children: <Widget>[
              SizedBox(
                height: Values.d_15,
              ),
              Container(
                height: Values.d_26,
                child: Text(
                  S.of(context).cancel_order,
                  style: TextStyle(
                      fontSize: Values.s_text_18,
                      fontWeight: Values.font_Weight_medium,
                      color: Values.of(context).c_black_front_33,
                      decoration: TextDecoration.none),
                ),
              ),
              Container(
                margin: EdgeInsets.only(top: Values.d_10, left: Values.d_18),
                alignment: Alignment.centerLeft,
                height: Values.d_15,
                child: Text(
                  S.of(context).cancel_warn_content,
                  style: TextStyle(
                      fontSize: Values.s_text_12,
                      fontWeight: Values.font_Weight_normal,
                      color: Values.of(context).c_black_front_66,
                      decoration: TextDecoration.none),
                ),
              ),
              SizedBox(height: Values.d_20),
              Container(
                  decoration: new BoxDecoration(
                      border: new Border(
                          bottom: BorderSide(
                              color: Values.of(context).c_grey_ea,
                              width: 1.0))),
                  height: Values.d_44,
                  margin:
                      EdgeInsets.only(left: Values.d_15, right: Values.d_15),
                  child: GestureDetector(
                    onTap: () {
                      setState(() {
                        _selectMessage = S.of(context).cancel_reason_1;
                        _selectIndex = 0;
                      });
                    },
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: <Widget>[
                        Text(
                          S.of(context).cancel_reason_1,
                          style: TextStyle(
                              fontSize: Values.s_text_14,
                              fontWeight: Values.font_Weight_normal,
                              color: Values.of(context).c_black_front_33,
                              decoration: TextDecoration.none),
                        ),
                        _selectIndex == 0
                            ? Image.asset(
                                'assets/images/button_select.png',
                              )
                            : Image.asset(
                                'assets/images/button_unSelect.png',
                              )
                      ],
                    ),
                  )),
              Container(
                  decoration: new BoxDecoration(
                      border: new Border(
                          bottom: BorderSide(
                              color: Values.of(context).c_grey_ea,
                              width: 1.0))),
                  height: Values.d_44,
                  margin:
                      EdgeInsets.only(left: Values.d_15, right: Values.d_15),
                  child: GestureDetector(
                    onTap: () {
                      setState(() {
                        _selectMessage = S.of(context).cancel_reason_2;
                        _selectIndex = 1;
                      });
                    },
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: <Widget>[
                        Text(
                          S.of(context).cancel_reason_2,
                          style: TextStyle(
                              fontSize: Values.s_text_14,
                              fontWeight: Values.font_Weight_normal,
                              color: Values.of(context).c_black_front_33,
                              decoration: TextDecoration.none),
                        ),
                        _selectIndex == 1
                            ? Image.asset(
                                'assets/images/button_select.png',
                              )
                            : Image.asset(
                                'assets/images/button_unSelect.png',
                              )
                      ],
                    ),
                  )),
              Container(
                  decoration: new BoxDecoration(
                      border: new Border(
                          bottom: BorderSide(
                              color: Values.of(context).c_grey_ea,
                              width: 1.0))),
                  height: Values.d_44,
                  margin:
                      EdgeInsets.only(left: Values.d_15, right: Values.d_15),
                  child: GestureDetector(
                    onTap: () {
                      setState(() {
                        _selectMessage = S.of(context).cancel_reason_3;
                        _selectIndex = 2;
                      });
                    },
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: <Widget>[
                        Text(
                          S.of(context).cancel_reason_3,
                          style: TextStyle(
                              fontSize: Values.s_text_14,
                              fontWeight: Values.font_Weight_normal,
                              color: Values.of(context).c_black_front_33,
                              decoration: TextDecoration.none),
                        ),
                        _selectIndex == 2
                            ? Image.asset(
                                'assets/images/button_select.png',
                              )
                            : Image.asset(
                                'assets/images/button_unSelect.png',
                              )
                      ],
                    ),
                  )),
              Container(
                height: Values.d_44,
                margin: EdgeInsets.only(
                    left: Values.d_15,
                    right: Values.d_15,
                    bottom: Values.d_20,
                    top: Values.d_20),
                child: Row(
                  children: <Widget>[
                    Expanded(
                      flex: 1,
                      child: FlatButton(
                          highlightColor: Values.c_translucent,
                          splashColor: Values.c_translucent,
                          color: Values.of(context).c_grey_background_ee,
                          child: Text(
                            S.of(context).cancel_title,
                            style: TextStyle(
                                fontSize: Values.s_text_16,
                                fontWeight: Values.font_Weight_normal,
                                color: Values.of(context).c_black_front_33,
                                decoration: TextDecoration.none),
                          ),
                          shape: new RoundedRectangleBorder(
                              borderRadius: BorderRadius.only(
                                  topLeft: Radius.circular(22),
                                  bottomLeft: Radius.circular(22))),
                          onPressed: () {
                            Navigator.pop(context);
                          }),
                    ),
                    Expanded(
                      flex: 1,
                      child: FlatButton(
                          highlightColor: Values.c_translucent,
                          splashColor: Values.c_translucent,
                          color: Values.of(context).c_orange_background_0b,
                          child: Text(
                            S.of(context).cancel_make_sure_title,
                            style: TextStyle(
                                fontSize: Values.s_text_16,
                                fontWeight: Values.font_Weight_normal,
                                color: Values.of(context).c_white_front,
                                decoration: TextDecoration.none),
                          ),
                          shape: new RoundedRectangleBorder(
                              borderRadius: BorderRadius.only(
                                  bottomRight: Radius.circular(22),
                                  topRight: Radius.circular(22))),
                          onPressed: () {
                            Navigator.pop(context);
                            widget.onTap(_selectMessage);
                          }),
                    )
                  ],
                ),
              )
            ]),
          ),
        ],
      ),
//    )
    );
  }
}
