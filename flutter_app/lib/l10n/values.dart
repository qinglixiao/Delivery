import 'dart:ui';
import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/src/config/global.dart';
import 'package:flutter_lib/flutter_lib.dart';

class Values {
  //颜色
  static const Color c_light_theme = Color(0xff6E78C7);
  static const Color c_dark_theme = Color(0xff313872);
  static const List<Color> c_gradient = [c_light_theme, c_dark_theme];

  static const Color c_red_r1 = Color(0xfffc6868);
  static const Color c_blue_b1 = Color(0xff229EF3);
  static const Color c_blue_b2 = Color(0xff8a9bf8);
  static const Color c_blue_b3 = Color(0xff455bd4);
  static const Color c_orange_o1 = Color(0xffff9b0b);
  static const Color c_orange_o2 = Color(0x19FFB834);
  static const Color c_orange_o3 = Color(0xffFFFAF4);

  static const Color c_black_b1 = Color(0xff333333);
  static const Color c_black_b2 = Color(0xff666666);
  static const Color c_black_b3 = Color(0xff999999);
  static const Color c_black_alpha_42 = Color(0x6b000000);

  static const Color c_grey_g1 = Color(0xffcccccc);
  static const Color c_grey_g2 = Color(0xffeaeaea);
  static const Color c_grey_g3 = Color(0xffeeeeee);
  static const Color c_grey_g4 = Color(0xfff8f8f8);

  static const Color c_white_w1 = Color(0xffffffff);

  static const Color c_translucent = Color(0x00ffffff);
  static const Color c_translucent_black = Color(0x20000000);


  //深色模式
  static const Color c_light_theme_d = Color(0x806E78C7);
  static const Color c_dark_theme_d = Color(0x80313872);
  static const List<Color> c_gradient_d = [c_light_theme, c_dark_theme];

  static const Color c_red_r1_d = Color(0xfffc6868);
  static const Color c_blue_b1_d = Color(0xff229EF3);
  static const Color c_orange_o1_d = Color(0xffff9b0b);
  static const Color c_orange_o2_d = Color(0x19FFB834);
  static const Color c_orange_o3_d = Color(0xffFFFAF4);

  static const Color c_black_b1_d = Color(0xCCffffff);
  static const Color c_black_b2_d = Color(0x99ffffff);
  static const Color c_black_b3_d = Color(0x66ffffff);

  static const Color c_grey_g1_d = Color(0x33ffffff);
  static const Color c_grey_g2_d = Color(0x26ffffff);
  static const Color c_grey_g3_d = Color(0x33ffffff);
  static const Color c_grey_g4_d = Color(0xff1f2025);

  static const Color c_white_w1_d = Color(0xff35363B);
  static const Color c_white_w1_d1 = Color(0xCCffffff);

  //尺寸、距离
  static const double d_0_5 = 0.5;
  static const double d_1 = 1;
  static const double d_2 = 2;
  static const double d_5 = 5;
  static const double d_8 = 8;
  static const double d_10 = 10;
  static const double d_11 = 11;
  static const double d_12 = 12;
  static const double d_15 = 15;
  static const double d_18 = 18;
  static const double d_20 = 20;
  static const double d_22 = 22;
  static const double d_26 = 26;
  static const double d_28 = 28;
  static const double d_30 = 30;
  static const double d_36 = 36;
  static const double d_44 = 44;
  static const double d_49 = 49;
  static const double d_50 = 50;
  static const double d_52 = 52;
  static const double d_60 = 60;
  static const double d_70 = 70;
  static const double d_80 = 80;
  static const double d_88 = 88;
  static const double d_97 = 97;
  static const double d_100 = 100;
  static const double d_135 = 135;
  static const double d_150 = 150;

  //字体

  static const double s_text_8 = 8;
  static const double s_text_11 = 11;
  static const double s_text_12 = 12;
  static const double s_text_13 = 13;
  static const double s_text_14 = 14;
  static const double s_text_15 = 15;
  static const double s_text_16 = 16;
  static const double s_text_18 = 18;
  static const double s_text_20 = 20;
  static const double s_text_24 = 24;
  static const double s_text_30 = 30;
  static const double s_text_40 = 40;

  static const FontWeight font_Weight_normal = FontWeight.w400;
  static const FontWeight font_Weight_medium = FontWeight.w500;

  BuildContext _context;

  static Values of(BuildContext context) {
    return StateProvider.of<Global>(context).values.._context = context;
  }

  ///================================================================
  ///以下为跟随主题变化的色值
  ///业务模块应从这里取值

  /// 字体色
  Color get c_red_front_68 {
    return Theme.of(_context).highlightColor;
  }

  Color get c_orange_front_0b {
    return Theme.of(_context).accentColor;
  }

  Color get c_blue_front_f3 {
    return Theme.of(_context).cardColor;
  }

  Color get c_black_front_33 {
    return Theme.of(_context).primaryColorDark;
  }

  Color get c_black_front_66 {
    return Theme.of(_context).primaryColor;
  }

  Color get c_black_front_99 {
    return Theme.of(_context).canvasColor;
  }

  Color get c_grey_front_cc {
    return Theme.of(_context).unselectedWidgetColor;
  }

  Color get c_white_front {
    return Theme.of(_context).primaryColorLight;
  }

  /// 分割线
  Color get c_grey_ea {
    return Theme.of(_context).dividerColor;
  }

  /// 背景色
  Color get c_blue_background_f3 {
    return Theme.of(_context).indicatorColor;
  }

  Color get c_orange_background_0b {
    return Theme.of(_context).buttonColor;
  }

  Color get c_orange_background_34 {
    return Theme.of(_context).hoverColor;
  }

  Color get c_orange_background_f4 {
    return Theme.of(_context).selectedRowColor;
  }

  Color get c_red_background_68 {
    return Theme.of(_context).toggleableActiveColor;
  }

  Color get c_grey_background_cc {
    return Theme.of(_context).splashColor;
  }

  Color get c_grey_background_ee {
    return Theme.of(_context).disabledColor;
  }

  Color get c_grey_background_f8 {
    return Theme.of(_context).scaffoldBackgroundColor;
  }

  Color get c_white_background {
    return Theme.of(_context).backgroundColor;
  }

  Color get c_blue_background_light {
    return Theme.of(_context).cursorColor;
  }

  Color get c_blue_background_dark {
    return Theme.of(_context).secondaryHeaderColor;
  }

  Color get c_translucent_f8_dark {
    return Theme.of(_context).hintColor;
  }

}
