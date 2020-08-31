import 'package:shared_preferences/shared_preferences.dart';

import '../../flutter_lib.dart';

class SpUtil {
  static SharedPreferences _sp;

  static init() async {
    _sp = await SharedPreferences.getInstance();
  }

  static Future<bool> setInt(String key, int value) async {
    return _sp.setInt(key, value);
  }

  static Future<bool> setBool(String key, bool value) async {
    return _sp.setBool(key, value);
  }

  static Future<bool> setDouble(String key, double value) async {
    return _sp.setDouble(key, value);
  }

  static Future<bool> setString(String key, String value) async {
    return _sp.setString(key, value);
  }

  static Future<bool> setStringList(String key, List<String> value) {
    return _sp.setStringList(key, value);
  }

  static Future<bool> remove(String key) {
    return _sp.remove(key);
  }

  static String getString(String key) {
    return getValue(key);
  }

  static dynamic getValue(String key) {
    return _safeValue(key);
  }

  static dynamic _safeValue(String key) {
    try {
      return _sp.get(key);
    } catch (e) {
      Logger.print(e);
      return null;
    }
  }

  static Future<bool> clearAll() {
    return _sp.clear();
  }

  ///===========常用===========
  static Future<bool> setToken(String value) async {
    return _sp.setString("token", value);
  }

  static String getToken() {
    return getValue("token");
  }

  static Future<bool> removeToken() {
    return _sp.remove("token");
  }

  static Future<bool> setUser(String value) async {
    return _sp.setString("user", value);
  }

  static String getUser() {
    return getValue("user");
  }

  static Future<bool> removeUser() {
    return _sp.remove("user");
  }

  static Future<bool> setSessionId(String value) async {
    return _sp.setString("sessionid", value);
  }

  static String getSessionId() {
    return getValue("sessionid");
  }

  static Future<bool> removeSessionId() {
    return _sp.remove("sessionid");
  }

  static Future<bool> setUserId(String value) async {
    return _sp.setString("userid", value);
  }

  static String getUserId() {
    return getValue("userid");
  }

  static Future<bool> removeUserId() {
    return _sp.remove("userid");
  }

  static Future<bool> setUserName(String value) async {
    return _sp.setString("username", value);
  }

  static String getUserName() {
    return getValue("username");
  }

  static Future<bool> removeUserName() {
    return _sp.remove("username");
  }

  static Future<bool> setUserImage(String value) async {
    return _sp.setString("userimage", value);
  }

  static String getUserImage() {
    return getValue("userimage");
  }

  static Future<bool> removeUserImage() {
    return _sp.remove("userimage");
  }

  static Future<bool> setUserMobile(String value) async {
    return _sp.setString("usermobile", value);
  }

  static String getUserMobile() {
    return getValue("usermobile");
  }

  static Future<bool> removeUserMobile() {
    return _sp.remove("usermobile");
  }

  static Future<bool> setUserCapacityId(String value) async {
    return _sp.setString("usercapacityid", value);
  }

  static String getUserCapacityId() {
    return getValue("usercapacityid");
  }

  static Future<bool> removeUserCapacityId() {
    return _sp.remove("usercapacityid");
  }

  static Future<bool> setUserNumberDesc(String value) async {
    return _sp.setString("userNumberDesc", value);
  }

  static String getUserNumberDesc() {
    return getValue("userNumberDesc");
  }

  static Future<bool> removeUserNumberDesc() {
    return _sp.remove("userNumberDesc");
  }

  static Future<bool> setUserRankNum(int value) async {
    return _sp.setString("userRankNum", value.toString());
  }

  static int getUseRankNum() {
    return int.parse(getValue("userRankNum"));
  }

  static Future<bool> removeUserRankNum() {
    return _sp.remove("userRankNum");
  }
}
