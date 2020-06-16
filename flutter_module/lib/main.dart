import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:fluttermodule/as/app/app_theme.dart';

import 'as/comm/name_router.dart';

class App extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return StateProvider(
      viewModel: null,
      child: MaterialApp(
        theme: AppTheme.theme,
        routes: routers,
//        initialRoute: RouterName.good_list,
        onUnknownRoute: (routerSetting) {
          return PageRouteBuilder(pageBuilder: (BuildContext context,
              Animation<double> animation,
              Animation<double> secondaryAnimation) {
            return Scaffold(
                body: Center(
              child: Text(
                "'${routerSetting.name}' is not define !",
                style: TextStyle(color: Colors.red),
              ),
            ));
          });
        },
      ),
    );
  }
}

void main() {
  debugProfileBuildsEnabled = true;
  runApp(App());
}
