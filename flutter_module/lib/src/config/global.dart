import 'package:flutter/widgets.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:fluttermodule/l10n/values.dart';

///全局配置信息
///
///create by lx
///date 2020/6/8
class Global {
  //发布版本时设置-> isOnline = true
  static bool isOnline = false;

  static bool get isLogin => SpUtil.getToken() != null;

  static const dbVersion = 1; //数据库版本

  static BuildContext appContext;

  Values values;

  Global(BuildContext context) {
    appContext = context;
    values = Values();
  }

  static Global of(BuildContext context) {
    return StateProvider.of<Global>(context);
  }
}