import 'package:flutter_ienglish_fine/src/business/login/bean/login.dart';
import 'package:flutter_ienglish_fine/src/business/login/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';

class LoginModel extends BaseModel {
  Future getSmsCode(String phone) async {
    return IEnglishNetClient().post(NET_POST_SENGSMS + phone).then((value) {
      return add(SmsBean.fromJson(value.data));
    });
  }

  Future getLoginWithSms(String phone, String code) async {
    return IEnglishNetClient()
        .post(NET_POST_LOGINWITHSMS + phone + '&verifyCode=' + code)
        .then((value) {
       return add(LoginBean.fromJson(value.data));
    });
  }

  Future getUserInfo() async {
    return IEnglishNetClient().post(NET_GET_USERINFO).then((value) {
      return add(UserInfoBean.fromJson(value.data));
    });
  }

  @override
  void dispose() {
    // TODO: implement dispose
  }
}
