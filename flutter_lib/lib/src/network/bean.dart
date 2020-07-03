abstract class NetBean {
  String error_code;
  String requestNo;
  String message;

  bool isSuccess() {
    return error_code == "";
  }
}
