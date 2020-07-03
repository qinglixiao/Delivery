import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_lib/flutter_lib.dart';

mixin UIWrap on Widget {
  Widget wrap() {
    return Scaffold(
      body: this,
    );
  }
}

mixin PageBridge {
  STBridge bridge = STBridge();

  Future getInitArg(BuildContext context) {
    return bridge.getInitArgs(context);
  }

  Future openFlutter(BuildContext context, String route, {Object argument}) {
    return bridge.open(context, route, parameters: argument);
  }

  Future<Map> pop(BuildContext context, {Map data}) {
    return bridge.pop(context, data: data);
  }
}
