import 'package:flutter/material.dart';

///系统主题
class AppTheme {
  static ThemeData get theme {
    return ThemeData(
      backgroundColor: Color(0xfff4f4f4),
      scaffoldBackgroundColor: Color(0xfff4f4f4),
      primaryColor: Color(0xffffffff),
      appBarTheme: AppBarTheme(color: Color(0xffffffff)),
    );
  }
}
