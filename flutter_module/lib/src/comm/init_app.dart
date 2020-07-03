import 'package:flutter_lib/flutter_lib.dart';
import 'package:fluttermodule/src/config/global.dart';

///app初始化
class AppEnv {
  static Future init() async {
    await netInit();
  }

  static netInit() async {
    NetworkConfig()
        .onlineHost("host")
        .devHost("www.liulongbin.top:3005/api")
        .isOnline(Global.isOnline)
        .init();
  }
}
