import 'package:flutter_ienglish_fine/src/business/mine/model/forget_login_pwd_model.dart';
import 'package:flutter_lib/flutter_lib.dart';


class ForgetLoginPwdViewModel extends BaseViewModel {
  ForgetPwdModel _model = ForgetPwdModel();

  Future<NetBean> sendMessage(String account) async {
    return _model.sendCheckCode(account);
  }

  Future<NetBean> savePwd(String account,String code,String newPwd) async {
    return _model.saveNewPwd(account, code, newPwd);
  }
}
