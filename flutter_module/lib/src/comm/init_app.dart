import 'package:flutter_lib/flutter_lib.dart';
import 'package:fluttermodule/src/config/global.dart';

///app初始化
class AppEnv {
  static Future init() async {
    await netInit();
    await sPUtilInit();
    await dbInit();
  }

  static netInit() async {
    return NetworkConfig()
        .onlineHost("qa-agent-material.tope365.com")
        .devHost("qa-agent-material.tope365.com")
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
}
