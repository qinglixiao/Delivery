import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/src/business/budget/comm/interfaces.dart';

const Color _titleColor = Color(0xFF333333);
const Color _contentColor = Color(0xFF999999);
const Color _leftColor = Color(0xFFeeeeee);
const Color _rightColor = Color(0xFF999999);
const Color _middlecolor = Color(0xFF999999);
const Color _middleBgColor = Color(0xFFffffff);
const Color _translucentColor = Color(0x00ffffff);

const double _titleSize = 18.0;
const double _contentSize = 16.0;
const double _buttonSize = 16.0;

class ReplenishDialog extends StatefulWidget {
  final String title; //标题
  final String content; //内容
  final String leftTitle; //左侧按钮名称
  final String rightTitle; //右侧按钮名称

  final Color titleColor; //标题颜色
  final Color contentColor;
  final Color leftColor;
  final Color leftBgColor;
  final Color rightColor;
  final Color rightBgColor;

  final double titleFontSize;
  final double contentFontSize;
  final double buttonFontSize;
  final Function(int) onTap;
  final Function(String title , String urlPath) linkOnTap;

  const ReplenishDialog({
    Key key,
    this.title,
    this.content,
    this.leftTitle,
    this.rightTitle,
    this.titleColor = _titleColor,
    this.contentColor = _contentColor,
    this.leftColor = _leftColor,
    this.leftBgColor = _middleBgColor,
    this.rightColor = _rightColor,
    this.rightBgColor = _middleBgColor,
    this.titleFontSize = _titleSize,
    this.contentFontSize = _contentSize,
    this.buttonFontSize = _buttonSize,
    this.onTap,
    this.linkOnTap,
  }) : super(key: key);

  @override
  _ReplenishDialogState createState() => _ReplenishDialogState();
}

class _ReplenishDialogState extends State<ReplenishDialog> {
  bool _isAgree = true;
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
            padding: EdgeInsets.symmetric(horizontal: 48),
            child: Container(
              decoration: BoxDecoration(
                  color: Colors.white,
                  borderRadius: BorderRadius.all(Radius.circular(5))),
              child: Column(
                children: <Widget>[
                  SizedBox(
                    height: 24,
                  ),
                  Text(
                    widget.title,
                    style: TextStyle(
                        fontSize: widget.titleFontSize,
                        fontWeight: FontWeight.w500,
                        color: widget.titleColor,
                        decoration: TextDecoration.none),
                  ),
                  SizedBox(
                    height: 10,
                  ),
                   Container(
                    padding: EdgeInsets.symmetric(horizontal: 21),
                    child: Text(
                      widget.content,
                      textAlign: TextAlign.center,
                      style: TextStyle(
                          fontSize: widget.contentFontSize,
                          fontWeight: FontWeight.w400,
                          color: widget.contentColor,
                          decoration: TextDecoration.none),
                    ),
                  ),
                  SizedBox(
                    height: 26,
                  ),
                  Container(
                margin: EdgeInsets.only(left: 18, right: 18, top: 0),
                child: Row(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: <Widget>[
                    GestureDetector(
                      onTap: (){
                        setState(() {
                          _isAgree = !_isAgree;
                        });
                      },
                      child:  Container(
                        alignment: Alignment.centerLeft,
                        padding: EdgeInsets.all(0),
                        width: 34,
                        height: 34,
                        child: _isAgree
                            ? Image.asset(
                          'assets/images/button_select.png',
                        )
                            : Image.asset(
                          'assets/images/button_unSelect.png',
                        ),
                      ),
                    ),
                    Expanded(
                      child: Text.rich(
                        TextSpan(
                          text: S.of(context).replenish_dialog_protocol_title,
                          style: TextStyle(
                            fontSize: Values.s_text_12,
                            color: Values.of(context).c_black_front_99,
                            fontWeight: Values.font_Weight_normal,
                              decoration: TextDecoration.none,
                          ),
                          children: [
                            TextSpan(
                                recognizer: TapGestureRecognizer()
                                  ..onTap = () {
                                    widget.linkOnTap(S.of(context).warn_protocol_1,NET_WEB_ORDER_GOODS);
                                  },
                                text: S.of(context).warn_protocol_1,
                                style: TextStyle(
                                  color: Values.of(context).c_blue_front_f3,
                                )),
                            TextSpan(
                              text: '、'
                            ),
                            TextSpan(
                                recognizer: TapGestureRecognizer()
                                  ..onTap = () {
                                    widget.linkOnTap(S.of(context).warn_protocol_2,NET_WEB_DISCLAIMER);
                                  },
                                text: S.of(context).warn_protocol_2,
                                style: TextStyle(
                                  color: Values.of(context).c_blue_front_f3,
                                )),
                          ],
                        ),
                      ),
                    )
                  ],
                ),
              ),
                  SizedBox(
                    height: 25,
                  ),
                  Container(
                    height: 36,
                    margin:
                    EdgeInsets.only(left: 16, right: 16, bottom: 26),
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: <Widget>[
                        Expanded(
                          child: Container(
                            padding: EdgeInsets.symmetric(horizontal: 10),
                            child: OutlineButton(
                                highlightColor: _translucentColor,
                                splashColor: _translucentColor,
                                color: widget.leftBgColor,
                                child: Text(
                                  widget.leftTitle,
                                  style: TextStyle(
                                      fontSize: widget.buttonFontSize,
                                      fontWeight: FontWeight.w500,
                                      color: widget.leftColor,
                                      decoration: TextDecoration.none),
                                ),
                                shape: new RoundedRectangleBorder(
                                    borderRadius:
                                    new BorderRadius.circular(36.0)),
                                borderSide: new BorderSide(
                                    color: widget.rightBgColor),
                                highlightedBorderColor: widget.rightBgColor,
                                onPressed: () {
                                  Navigator.pop(context);
                                  widget.onTap(0);
                                }),
                          ),
                        ),
                        Expanded(
                            child: Container(
                              padding: EdgeInsets.symmetric(horizontal: 10),
                              child: FlatButton(
                                  highlightColor: _translucentColor,
                                  splashColor: _translucentColor,
                                  color: widget.rightBgColor,
                                  child: Text(
                                    widget.rightTitle,
                                    style: TextStyle(
                                        fontSize: widget.buttonFontSize,
                                        fontWeight: FontWeight.w500,
                                        color: widget.rightColor,
                                        decoration: TextDecoration.none),
                                  ),
                                  shape: new RoundedRectangleBorder(
                                      borderRadius:
                                      new BorderRadius.circular(36.0)),
                                  onPressed: () {
                                    if(_isAgree){
                                      Navigator.pop(context);
                                      widget.onTap(1);
                                    }
                                    else{
                                      widget.onTap(-1);
                                    }
                                  }),
                            ))
                      ],
                    ),
                  ),
                ],
              ),
            ),
          )
        ],
      ),
    );
  }
}
