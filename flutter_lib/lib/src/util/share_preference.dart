import 'package:shared_preferences/shared_preferences.dart';

class SpUtil {
  static SharedPreferences _sp;

  static init() async {
    _sp = await SharedPreferences.getInstance();
  }

  static Future<bool> setInt(String key, int value) async {
    return _sp.setInt(key, value);
  }

  static Future<bool> setString(String key, String value) async {
    return _sp.setString(key, value);
  }

  static Future<bool> setStringList(String key, List<String> value) {
    return _sp.setStringList(key, value);
  }

  static String getString(String key) {
    return _sp.getString(key);
  }

  ///===========常用===========
  static Future<bool> setToken(String value) async {
    return _sp.setString("token", value);
  }

  static String getToken() {
    return _sp.get("token");
  }

  static Future<bool> setUser(String value) async {
    return _sp.setString("user", value);
  }

  static String getUser() {
    return _sp.get("user");
  }

  static Future<bool> setSessionId(String value) async {
    return _sp.setString("sessionid", value);
  }

  static String getSessionId() {
    return _sp.get("sessionid");
  }
}
