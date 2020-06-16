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

mixin PageIteractor {
  STBridge bridge;

  void initPage(BuildContext context) {
    bridge = STBridge();
    bridge.setContext(context);
  }

  Future<Map> getInitArg(BuildContext context) {
    return bridge.getInitArgs();
  }

  void close() {}
}
