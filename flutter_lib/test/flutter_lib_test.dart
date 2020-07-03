import 'package:cookie_jar/cookie_jar.dart';
import 'package:dio/dio.dart';
import 'package:dio_cookie_manager/dio_cookie_manager.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_test/flutter_test.dart';

void main() {
  test('adds one to input values', () {
//    getHttp();
    cookie();
  });
}

void getHttp() async {
  var dio = Dio();
  dio.interceptors.add(LogInterceptor(responseBody: true));
  dio.options.baseUrl = "http://httpbin.org";
  //dio.options.baseUrl = "http://localhost:3000";
  dio.options.receiveDataWhenStatusError = false;
  dio.interceptors.add(InterceptorsWrapper(onResponse: (r) {
    //throw DioError(...); or
    return dio.reject("xxx");
  }));
  try {
    Future.wait([
      dio.get("/get", queryParameters: {"id": 1}),
      dio.get("/get", queryParameters: {"id": 2})
    ]).then(
      (value) => print(value),
    );
  } catch (e) {
    print(e);
  }
}

void cookie() async {
  var dio = Dio();
  var cookieJar = CookieJar();
  dio.interceptors..add(LogInterceptor())..add(CookieManager(cookieJar));
  await dio.get("https://baidu.com/");
  // Print cookies
  print(cookieJar.loadForRequest(Uri.parse("https://baidu.com/")));
  // second request with the cookie
  await dio.get("https://baidu.com/");
}
