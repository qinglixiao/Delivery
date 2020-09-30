import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/src/config/global.dart';
import 'package:flutter_ienglish_fine/src/trial/cloud/auth_login_page.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_localizations/flutter_localizations.dart';

import 'generated/l10n.dart';
import 'src/business/login/view/cupertino_localizations.dart';
import 'src/comm/init_app.dart';
import 'src/config/app_theme.dart';
import 'src/config/global.dart';
import 'src/config/name_router.dart';

class App extends StatelessWidget with WidgetsBindingObserver {
  @override
  Widget build(BuildContext context) {
    return OKToast(
        child: StateProvider(
      data: Global(context),
      child: MaterialApp(
        theme: AppTheme.theme,
        darkTheme: AppTheme.darkTheme,
        navigatorKey: GlobalNavigator,
        routes: routers,
        navigatorObservers: [routeObserver],
        builder: (context, widget) {
          return MediaQuery(
            //设置文字大小不随系统设置改变
            data: MediaQuery.of(context).copyWith(textScaleFactor: 1.0),
            child: widget,
          );
        },
        home: AuthLoginPage(),
        onUnknownRoute: (routerSetting) {
          return PageRouteBuilder(
              pageBuilder: (BuildContext context, Animation<double> animation, Animation<double> secondaryAnimation) {
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
          CupertinoLocalizationsDelegate(),
          GlobalMaterialLocalizations.delegate,
          GlobalWidgetsLocalizations.delegate
        ],
        supportedLocales: S.delegate.supportedLocales,
      ),
    ));
  }

  @override
  void didChangeAppLifecycleState(AppLifecycleState state) {
    super.didChangeAppLifecycleState(state);
    switch (state) {
      case AppLifecycleState.paused:
        //后台
        break;
      case AppLifecycleState.resumed:
        //前台
        break;
      default:
        break;
    }
  }
}

void main() async {
  AppCrashHandler(() async {
    WidgetsFlutterBinding.ensureInitialized();
    await AppEnv.init();
    App _app = App();
    WidgetsBinding.instance.addObserver(_app);
    runApp(_app);
  });
}
