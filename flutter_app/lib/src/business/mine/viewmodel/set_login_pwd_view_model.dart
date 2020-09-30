import 'package:flutter_ienglish_fine/src/business/mine/model/set_login_pwd_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

///
///create by lx
///date 2020/8/19
///

class LoginPwdViewModel extends BaseViewModel {
  LoginPwdModel _model = LoginPwdModel();

  Future<NetBean> save(String old, String newPwd) async {
    return _model.savePwd(old, newPwd);
  }
}
