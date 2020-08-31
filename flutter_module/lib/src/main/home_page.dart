import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:fluttermodule/generated/l10n.dart';
import 'package:fluttermodule/src/config/name_router.dart';

class HomePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return HomePageState();
  }
}

class HomePageState extends State<HomePage> with PageBridge {
  @override
  Widget build(BuildContext context) {
    return RootPageWidget(
      body: Center(
          child: Row(
        children: <Widget>[
          RaisedButton(
            child: Text(S.of(context).tab_home),
            onPressed: () {},
          ),
          RaisedButton(
            child: Text("订货"),
            onPressed: () {
              open(RouterName.good_list);
            },
          ),
          RaisedButton(
            child: Text("get"),
            onPressed: () {
              top().then(
                (value) => {
                  Logger.print(value),
                },
              );
            },
          ),
        ],
      )),
    );
  }
}
