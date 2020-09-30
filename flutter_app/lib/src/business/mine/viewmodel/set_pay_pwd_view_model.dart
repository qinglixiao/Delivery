import 'package:flutter_ienglish_fine/src/business/mine/bean/pay_pwd_manager.dart';
import 'package:flutter_ienglish_fine/src/business/mine/model/set_pay_pwd_model.dart';
import 'package:flutter_lib/flutter_lib.dart';


class SetPayPwdViewModel extends BaseViewModel {
  PayPwdModel _model = PayPwdModel();

  Future<PayPwdManager> getPayPwdModify() async {
    String numberDesc = SpUtil.getUserNumberDesc();
    return _model.getPayPwdModify(numberDesc, '1', 'https://www.ienglish.cn/app/pay_pwd_modify');
  }

  Future<PayPwdManager> getResetPayPwd() async {
    String numberDesc = SpUtil.getUserNumberDesc();
    return _model.getResetPayPwd(numberDesc, '2', 'https://www.ienglish.cn/app/reset_pay_pwd');
  }
}
