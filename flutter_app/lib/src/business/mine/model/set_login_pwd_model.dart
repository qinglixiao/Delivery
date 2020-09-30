import 'package:flutter_ienglish_fine/src/business/mine/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';

///
///create by lx
///date 2020/8/19
///

class LoginPwdModel extends BaseModel {
  Future<NetBean> savePwd(String old, String newPwd) async {
    return IEnglishNetClient().post(NET_POST_MODIFY_PWD, queryParameters: {
      "oldPassword": old,
      "password": newPwd
    }).then((value) {
      return NetBean.fromJson(value.data);
    });
  }
}
