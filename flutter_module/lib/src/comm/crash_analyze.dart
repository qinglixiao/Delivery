import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';

///
///create by lx
///date 2020/8/27
///

class AppCrashHandler {
  AppCrashHandler(void target()) {
    FlutterError.onError = (FlutterErrorDetails details) async {
      Zone.current.handleUncaughtError(details.exception, details.stack);
    };

    ErrorWidget.builder = (FlutterErrorDetails details) {
      return RootPageWidget(
          body: Center(
        child: Text("widget error"),
      ));
    };

    runZonedGuarded(
      () {
        target.call();
      },
      (error, stack) {
        _report(error, stack);
      },
    );
  }
}

_report(dynamic error, dynamic stack) async {
  //
}
