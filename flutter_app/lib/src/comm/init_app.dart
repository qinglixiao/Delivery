import 'package:flutter/services.dart';
import 'package:flutter_ienglish_fine/src/config/global.dart';
import 'package:flutter_lib/flutter_lib.dart';

///app初始化
///create by lx
///date 2020/6/30
class AppEnv {
  static Future init() async {
    await netInit();
    await sPUtilInit();
    await dbInit();
    // await systemOrientationsInit();
  }

  static netInit() async {
    return NetworkConfig()
        .onlineDomain("qa-agent-material.tope365.com")
        .devDomain("qa-agent-material.tope365.com")
        .isOnline(Global.isOnline)
        .init();
  }

  static sPUtilInit() async {
    return SpUtil.init();
  }

  static dbInit() async {
    await DbUtil.copyFromto("assets/db/iagent", "iagent.db");
    DbConfig.init(
      TableContainer([]),
      version: Global.dbVersion,
      dbName: "iagent.db",
    );
  }

  ///设置屏幕纵向
  static systemOrientationsInit() async {
    await SystemChrome.setPreferredOrientations([DeviceOrientation.portraitUp]);
  }

}
