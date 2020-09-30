import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';

class DialogOpenAccount extends StatefulWidget {
  final Function(int type) onTap;

  const DialogOpenAccount({
    Key key,
    @required this.onTap,
  }) : super(key: key);

  @override
  _DialogOpenAccountState createState() => _DialogOpenAccountState();
}

class _DialogOpenAccountState extends State<DialogOpenAccount> {
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
                  crossAxisAlignment: CrossAxisAlignment.start,
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
                      margin: EdgeInsets.only(left: Values.d_20),
                      child: Text(S.of(context).open_account_type,
                          style: TextStyle(
                              fontSize: Values.s_text_24,
                              fontWeight: Values.font_Weight_medium,
                              color: Values.of(context).c_black_front_33,
                              decoration: TextDecoration.none),
                          textAlign: TextAlign.left),
                    ),
                    GestureDetector(
                      onTap: () {
                        Navigator.pop(context);
                        widget.onTap(0);
                      },
                      child: Container(
                        margin: EdgeInsets.only(
                            left: Values.d_10,
                            right: Values.d_10,
                            top: Values.d_26),
                        child: AspectRatio(
                            aspectRatio: 524 / 224,
                            child: Image.asset(
                              'assets/images/person_account.png',
                              fit: BoxFit.fill,
                            )),
                      ),
                    ),
                    GestureDetector(
                        onTap: () {
                          Navigator.pop(context);
                          widget.onTap(1);
                        },
                        child: Container(
                            margin: EdgeInsets.only(
                                left: Values.d_10,
                                right: Values.d_10,
                                bottom: Values.d_10),
                            child: AspectRatio(
                                aspectRatio: 524 / 224,
                                child: Image.asset(
                                  'assets/images/company_account.png',
                                  fit: BoxFit.fill,
                                )))),
                  ],
                ),
              ),
            )
          ],
        ));
  }
}
