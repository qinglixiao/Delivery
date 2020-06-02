import 'package:fluttermodule/as/comm/name_router.dart';

class InitApp {
  static Future init() async {
    return Future.delayed(Duration(microseconds: 1000), () {
      print("app init...");
    });
  }
}
