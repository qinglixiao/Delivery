import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:fluttermodule/src/comm/init_app.dart';

import 'generated/l10n.dart';
import 'src/comm/crash_analyze.dart';
import 'src/config/app_theme.dart';
import 'src/config/global.dart';
import 'src/config/name_router.dart';
import 'src/demo/slide_page.dart';

class App extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return StateProvider(
      data: Global(context),
      child: MaterialApp(
        theme: AppTheme.theme,
        darkTheme: AppTheme.darkTheme,
        routes: routers,
        home: SlidePage(),
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
  AppCrashHandler(() async {
    WidgetsFlutterBinding.ensureInitialized();
    await AppEnv.init();
    runApp(App());
  });
}
