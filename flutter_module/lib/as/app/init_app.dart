///app初始化，异步加载数据
class InitApp {
  static Future init() async {
    return Future.delayed(Duration(microseconds: 1000), () {
      print("app init...");
    });
  }
}
