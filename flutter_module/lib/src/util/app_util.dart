import 'dart:io';

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
