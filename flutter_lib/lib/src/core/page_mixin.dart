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

  Future open(String route, {Object argument}) {
    return bridge.open(route, arguments: argument);
  }

  Future popAndOpen(String route, {Object argument}) {
    return bridge.popAndOpen(route, arguments: argument);
  }

  Future openAndRemoveUtil(String newRoute,
      {String historyRoute, Object arguments}) {
    return bridge.openAndRemoveUtil(newRoute,
        historyRoute: historyRoute, arguments: arguments);
  }

  popUtil(String route) {
    bridge.popUtil(route);
  }

  Future<Map> pop({Object data}) {
    return bridge.pop(data: data);
  }
}
