import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:fluttermodule/generated/l10n.dart';

class MePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return MePageState();
  }
}

class MePageState extends State<MePage> {
  @override
  Widget build(BuildContext context) {
    return RootPageWidget(
      body: Center(
        child: Column(
          children: <Widget>[
            Text(S.of(context).tab_me),
          ],
        ),
      ),
    );
  }
}
