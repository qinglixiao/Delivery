import 'dart:async';

import 'package:dio/dio.dart';
import 'package:flutter_lib/flutter_lib.dart';

login(String user, {String pwd = "123456"}) async {
  return IEnglishNetClient().get("//member/password_login",
      queryParameters: {"username": user, "password": pwd});
}

Future<Response> top() async {
  return IEnglishNetClient().get("/getlunbo");
}
