import 'package:flutter_ienglish_fine/src/business/mine/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';

class ForgetPwdModel extends BaseModel {
  Future<NetBean> sendCheckCode(String account) async {
    return IEnglishNetClient().post(NET_POST_SEND_MESSAGE, queryParameters: {
      "phone": account,
    }).then((value) {
      return NetBean.fromJson(value.data);
    });
  }

  Future<NetBean> saveNewPwd(String account,String code,String newPwd) async {
    return IEnglishNetClient().post(NET_POST_FORGET_PASSWORD, queryParameters: {
      "phone": account,
      "authCode": code,
      "password": newPwd,
    }).then((value) {
      return NetBean.fromJson(value.data);
    });
  }

}
