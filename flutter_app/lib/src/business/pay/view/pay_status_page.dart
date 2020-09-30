import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';

class PayStatusPage extends StatefulWidget {
  @override
  _PayStatusPageState createState() => _PayStatusPageState();
}

class _PayStatusPageState extends State<PayStatusPage> with PageBridge {

  bool _isSuccess = false;
  String content;
  String actionStr;
  String router;
  String title;

  @override
  Widget build(BuildContext context) {

    Map map =  ModalRoute.of(context).settings.arguments;
    _isSuccess = map['status'];
    title = map['title'];
    content = map['content'];
    router = map['router'];
    actionStr = map['action'];

    return RootPageWidget(
        appBar: IsAppBar(
          title: title,
          leftOnTap: () {
            popUtil(router);
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
                child: Text(content,
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
                      popUtil(router);
                    },
                    color:Values.of(context).c_orange_background_0b,
                    splashColor: Values.c_translucent,
                    highlightColor: Values.c_translucent,
                    disabledColor: Values.of(context).c_grey_background_cc,
                    child: new Text(
                      actionStr,
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
