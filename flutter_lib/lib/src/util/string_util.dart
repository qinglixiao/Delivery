///
///create by lx
///date 2020/7/23
///

class StringUtil {
  static bool isEmpty(String txt) {
    return txt == null || txt == "";
  }

  static String getNotNullString(String txt) {
    return txt ?? "";
  }
}
