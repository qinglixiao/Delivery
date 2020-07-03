import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:fluttermodule/generated/l10n.dart';
import 'package:provider/provider.dart';

class MePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return MePageState();
  }
}

class MePageState extends State<MePage> {
  @override
  Widget build(BuildContext context) {
    return Provider<DataModel>(
        create: (_) => DataModel(),
        child: Consumer<DataModel>(
          builder: (_,a,child){
            return Text(a.name);
          },
        ),
//        builder: (context, child) {
//          return Text(context.watch<DataModel>().name);
//        },
//        child: RootPageWidget(
//          body: Center(
//            child: Column(
//              children: <Widget>[
//                Text(S.of(context).tab_me),
//                RaisedButton(onPressed: () {
////                  DataModel().age = 10;
//                }),
////                 Text(context.watch<DataModel>().name),
//                Consumer<DataModel>(builder: (context, data, child) {})
//              ],
//            ),
//          ),
        );
  }
}

class DataModel extends ChangeNotifier {
  var _name = "";
  var _age = 0;

  get name {
    return _name + "${_age}";
  }

//  DataModel({this.age});

  set age(int age) {
    this._age = age;
    notifyListeners();
  }
}
