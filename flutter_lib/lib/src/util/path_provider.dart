import 'package:path_provider/path_provider.dart';

///
///create by lx
///date 2020/9/30
///
class PathProvider {
  ///"data/user/0/com.ienglish.flutter.iagent/cache"
  static tempDirectory() async {
    return getTemporaryDirectory();
  }

  ///"/data/user/0/com.ienglish.flutter.iagent/app_flutter"
  static applicationDocumentsDirectory() async {
    return getApplicationDocumentsDirectory();
  }

  ///"data/user/0/com.ienglish.flutter.iagent/files"
  static applicationSupportDirectory() async {
    return getApplicationSupportDirectory();
  }

  /// IOS/macOs
  static libraryDirectory() async {
    return getLibraryDirectory();
  }

  ///"/storage/emulated/0/Android/data/com.ienglish.flutter.iagent/files"
  static externalStorageDirectory() async {
    return getExternalStorageDirectory();
  }

  ///"/storage/emulated/0/Android/data/com.ienglish.flutter.iagent/cache"
  static externalCacheDirectories() async {
    return getExternalCacheDirectories();
  }
}
