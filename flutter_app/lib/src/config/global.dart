import 'package:flutter/cupertino.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_lib/flutter_lib.dart';

///全局配置信息
///
///create by lx
///date 2020/6/8
class Global {
  //发布版本时设置-> isOnline = true
  static bool isOnline = false;

  bool get isLogin => SpUtil.getToken() != null;

  static const dbVersion = 1; //数据库版本

  static BuildContext appContext;

  Values values;

  Global(BuildContext context) {
    appContext = context;
    values = Values();
    AppContext().init();
  }

  static Global of(BuildContext context) {
    return StateProvider.of<Global>(context);
  }
}
