import 'package:flutter_ienglish_fine/src/business/login/bean/login.dart';
import 'package:flutter_ienglish_fine/src/business/login/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'dart:convert';

class LoginModel extends BaseModel {
  Future getLoginWithPassword(String phone, String code) async {
    return IEnglishNetClient()
        .post(NET_POST_LOGINWITHPASSWORD + phone + '&password=' + code)
        .then((value) {
      return add(LoginBean.fromJson(value.data));
    });
  }

  Future getUserInfo() async {
    return IEnglishNetClient()
        .post(NET_GET_USERINFO)
        .then((value) {
      return add(UserInfoBean.fromJson(value.data));
    });
  }
}

