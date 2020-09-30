import 'dart:io';

///
///create by lx
///date 2020/6/30
class AppUtil {
  static bool isAndroid() {
    return Platform.isAndroid;
  }

  static bool isIOS() {
    return Platform.isIOS;
  }

  static bool isRelease() {
    return bool.fromEnvironment("dart.vm.product");
  }
}
