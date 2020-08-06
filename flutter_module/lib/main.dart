import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:fluttermodule/src/comm/init_app.dart';
import 'package:fluttermodule/src/demo/animation_muti.dart';

import 'generated/l10n.dart';
import 'src/config/app_theme.dart';
import 'src/config/name_router.dart';
import 'src/demo/future_use.dart';
import 'src/demo/webview_demo.dart';

class App extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    STBridge().setContext(context);
    return StateProvider(
      viewModel: null,
      child: MaterialApp(
        theme: AppTheme.theme,
        darkTheme: AppTheme.darkTheme,
        routes: routers,
        home: WebViewExample(),
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
        localizationsDelegates: const [
          S.delegate,
          GlobalMaterialLocalizations.delegate,
          GlobalWidgetsLocalizations.delegate
        ],
        supportedLocales: S.delegate.supportedLocales,
      ),
    );
  }
}

void main() async {
  debugProfileBuildsEnabled = true;
  runApp(App());
  await AppEnv.init();
}
