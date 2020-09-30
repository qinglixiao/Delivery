import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_lib/flutter_lib.dart';

class DialogOpenAccountResult extends StatefulWidget {
  final Function() onTap;
  final String statusImage;
  final String buttonContent;
  final String statusTitle;
  final String normalContent;
  final String result;


  const DialogOpenAccountResult({
    Key key,
    @required this.onTap,
    @required this.statusImage,
    @required this.buttonContent,
    @required this.statusTitle,
    @required this.normalContent,
    @required this.result,
  }) : super(key: key);

  @override
  _DialogOpenAccountResultState createState() => _DialogOpenAccountResultState();
}

class _DialogOpenAccountResultState extends State<DialogOpenAccountResult> {
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
                            margin: EdgeInsets.only(
                                right: Values.d_12, top: Values.d_12),
                            child:
                            Image.asset('assets/images/button_close.png'),
                          ),
                        )
                      ],
                    ),
                    Container(
                      child: Image.asset(widget.statusImage),
                    ),
                    Container(
                      margin: EdgeInsets.only(top: Values.d_15),
                      child: Text(widget.statusTitle,
                          style: TextStyle(
                              fontSize: Values.s_text_18,
                              fontWeight: Values.font_Weight_medium,
                              color: Values.of(context).c_black_front_33,
                              decoration: TextDecoration.none),
                          textAlign: TextAlign.left),
                    ),
                    Container(
                      margin: EdgeInsets.only(top: Values.d_5,left: Values.d_26,right: Values.d_26),
                      child: Text(widget.normalContent,
                          style: TextStyle(
                              fontSize: Values.s_text_15,
                              fontWeight: Values.font_Weight_normal,
                              color: Values.of(context).c_black_front_33,
                              decoration: TextDecoration.none),
                          textAlign: TextAlign.left),
                    ),
                    !StringUtil.isEmpty(widget.result)?Container(
                      margin: EdgeInsets.only(top: Values.d_5,left: Values.d_26,right: Values.d_26),
                      child: Text(widget.result,
                          style: TextStyle(
                              fontSize: Values.s_text_15,
                              fontWeight: Values.font_Weight_normal,
                              color: Values.of(context).c_red_front_68,
                              decoration: TextDecoration.none),
                          textAlign: TextAlign.left),
                    ):Container(),
                    Container(
                      height: 36,
                      margin:
                      EdgeInsets.only(left: 65, right: 65, bottom: 26,top: 26),
                      child: new SizedBox.expand(
                        child: FlatButton(
                            highlightColor: Values.c_translucent,
                            splashColor: Values.c_translucent,
                            color: Values.of(context).c_orange_background_0b,
                            child: Text(
                              widget.buttonContent,
                              style: TextStyle(
                                  fontSize: Values.d_15,
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
