import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';

///系统主题
///create by lx
///date 2020/6/30
class AppTheme {
  static ThemeData get theme {
    return ThemeData(
        brightness: Brightness.light,

        primaryColorBrightness: Brightness.light,

        backgroundColor: Values.c_white_w1,
        //  背景色 #ffffff
        scaffoldBackgroundColor: Values.c_grey_g4,
        // 背景色 #f8f8f8
        splashColor: Values.c_grey_g1,
        //  背景色 #cccccc
        disabledColor: Values.c_grey_g3,
        //  背景色 #eeeeee
        buttonColor: Values.c_orange_o1,

        hoverColor:Values.c_orange_o2,

        selectedRowColor:Values.c_orange_o3,
        // 背景色 #ff9b0b
        indicatorColor: Values.c_blue_b1,
        // 背景色 #229EF3
        toggleableActiveColor: Values.c_red_r1,
        // 背景色 #fc6868
        cursorColor: Values.c_light_theme,
        //背景色 #6E78C7
        secondaryHeaderColor: Values.c_dark_theme,
        //背景色 #313872

        dividerColor: Values.c_grey_g2,
        //  分割线 #eaeaea

        primaryColorLight: Values.c_white_w1,
        //  字体色 #ffffff
        primaryColorDark: Values.c_black_b1,
        //  字体色 #333333
        primaryColor: Values.c_black_b2,
        // 字体色 #666666
        canvasColor: Values.c_black_b3,
        // 字体色 #999999
        unselectedWidgetColor: Values.c_grey_g1,
        // 字体色 #cccccc
        accentColor: Values.c_orange_o1,
        //  字体色 #ff9b0b
        highlightColor: Values.c_red_r1,
        //  字体色 #fc6868
        cardColor: Values.c_blue_b1,
        //  字体色 #229EF3
      hintColor:Values.c_translucent,

        appBarTheme: AppBarTheme(
          color: Values.c_white_w1,
          textTheme: TextTheme(
            headline1: TextStyle(
              color: Values.c_black_b1,
              fontWeight: Values.font_Weight_medium,
              fontSize: Values.s_text_18,
            ),
            headline2: TextStyle(
              color: Values.c_black_b1,
              fontWeight: Values.font_Weight_medium,
              fontSize: Values.s_text_18,
            ),
            headline3: TextStyle(
              color: Values.c_white_w1,
              fontWeight: Values.font_Weight_medium,
              fontSize: Values.s_text_16,
            ),
            headline4: TextStyle(
              color: Values.c_black_b2,
              fontWeight: Values.font_Weight_medium,
              fontSize: Values.s_text_15,
            ),
            subtitle1: TextStyle(
              color: Values.c_black_b3,
              fontWeight: Values.font_Weight_medium,
              fontSize: Values.s_text_14,
            ),
            subtitle2: TextStyle(
              color: Values.c_black_b3,
              fontWeight: Values.font_Weight_medium,
              fontSize: Values.s_text_13,
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

        primaryColorBrightness: Brightness.dark,

        backgroundColor: Values.c_white_w1_d,
        //  背景色
        scaffoldBackgroundColor: Values.c_grey_g4_d,
        // 背景色
        splashColor: Values.c_grey_g1_d,
        //  背景色
        disabledColor: Values.c_grey_g3_d,
        //  背景色
        buttonColor: Values.c_orange_o1_d,
        hoverColor:Values.c_orange_o2_d,
        selectedRowColor:Values.c_orange_o3_d,
        // 背景色
        indicatorColor: Values.c_blue_b1_d,
        // 背景色
        toggleableActiveColor: Values.c_red_r1_d,
        // 背景色
        cursorColor: Values.c_light_theme_d,
        //背景色
        secondaryHeaderColor: Values.c_dark_theme_d,
        //背景色

        dividerColor: Values.c_grey_g2_d,
        //  分割线

        primaryColorLight: Values.c_white_w1_d1,
        //  字体色
        primaryColorDark: Values.c_black_b1_d,
        //  字体色
        primaryColor: Values.c_black_b2_d,
        // 字体色
        canvasColor: Values.c_black_b3_d,
        // 字体色
        unselectedWidgetColor: Values.c_grey_g1_d,
        // 字体色
        accentColor: Values.c_orange_o1_d,
        //  字体色
        highlightColor: Values.c_red_r1_d,
        //  字体色
        cardColor: Values.c_blue_b1_d,
        //  字体色
        hintColor:Values.c_grey_g4_d,

        appBarTheme: AppBarTheme(
          color: Values.c_white_w1_d,
          textTheme: TextTheme(
            headline1: TextStyle(
              color: Values.c_black_b1_d,
              fontWeight: Values.font_Weight_medium,
              fontSize: Values.s_text_18,
            ),
          ),
        ),
        pageTransitionsTheme: PageTransitionsTheme(builders: {
          TargetPlatform.android: CupertinoPageTransitionsBuilder(),
          TargetPlatform.iOS: CupertinoPageTransitionsBuilder(),
        }));
  }
}
