import 'package:flutter/material.dart';
import 'package:fluttermodule/l10n/values.dart';

///系统主题
class AppTheme {
  static ThemeData get theme {
    return ThemeData(
        backgroundColor: Values.c_black,
        scaffoldBackgroundColor: Values.c_white,
        primaryColor: Values.c_black,
        primaryColorDark: Values.c_black,
        appBarTheme: AppBarTheme(
          color: Values.c_blue,
          textTheme: TextTheme(
            headline1: TextStyle(
              fontWeight: FontWeight.w700,
            ),
          ),
        ),
        pageTransitionsTheme: PageTransitionsTheme(builders: {
          TargetPlatform.android: CupertinoPageTransitionsBuilder(),
          TargetPlatform.iOS: CupertinoPageTransitionsBuilder(),
        }));
  }

  static ThemeData get darkTheme {
    return ThemeData(
        brightness: Brightness.dark,
        appBarTheme: AppBarTheme(
          textTheme: TextTheme(
            headline1: TextStyle(
              color: Values.c_white,
              fontWeight: FontWeight.w700,
            ),
          ),
        ),
        pageTransitionsTheme: PageTransitionsTheme(builders: {
          TargetPlatform.android: CupertinoPageTransitionsBuilder(),
          TargetPlatform.iOS: CupertinoPageTransitionsBuilder(),
        }));
  }
}

PageTransitionsBuilder switchTransition() {
  return MyPageTransitionsBuilder();
}

class MyPageTransitionsBuilder extends PageTransitionsBuilder {
  @override
  Widget buildTransitions<T>(
      PageRoute<T> route,
      BuildContext context,
      Animation<double> animation,
      Animation<double> secondaryAnimation,
      Widget child) {
    var curveTween = CurveTween(curve: Curves.ease);
    var tween = Tween<Offset>(begin: Offset(0.0, 1.0), end: Offset.zero)
        .chain(curveTween);

    return SlideTransition(position: animation.drive(tween), child: child);
  }
}
