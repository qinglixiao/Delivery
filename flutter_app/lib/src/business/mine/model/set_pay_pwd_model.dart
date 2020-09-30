
import 'package:flutter_ienglish_fine/src/business/mine/bean/pay_pwd_manager.dart';
import 'package:flutter_ienglish_fine/src/business/mine/bean/place_list.dart';
import 'package:flutter_ienglish_fine/src/business/mine/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';

class PayPwdModel extends BaseModel {

///payaccount/tfs/password?
//numberDesc=3C4B5D8502F90EBD073BBB2544D3FD21DBEC9CDC8196883DD813C93C8BA7F01B04CCC2D993188D4D2712664A9762B3C7732BDA4EF62D3DE1&
//bizType=1&
//returnUrl=https%3A%2F%2Fqa-agent-material.tope365.com%2FdealLoginView&
//token=223830Q1598407820433_5175a3a705274425b9e5aaee2b481769&xxl_sso_sessionid=223830Q1598407820433_5175a3a705274425b9e5aaee2b481769



  Future<PayPwdManager> getPayPwdModify(String numberDesc,String bizType,String url) async {
    return IEnglishNetClient().get(
        NET_GET_PAY_PASSWORD_MODIFY,queryParameters: {
      "numberDesc": numberDesc,
      "bizType": bizType,
      "returnUrl": url,
    }).then((value) {
      return add(PayPwdManager.fromJson(value.data));
    });
  }

  Future<PayPwdManager> getResetPayPwd(String numberDesc,String bizType,String url) async {
    return IEnglishNetClient().get(
        NET_GET_PAY_PASSWORD_MODIFY,queryParameters: {
      "numberDesc": numberDesc,
      "bizType": bizType,
      "returnUrl": url,
    }).then((value) {
      return add(PayPwdManager.fromJson(value.data));
    });
  }

  @override
  void dispose() {
    super.dispose();
    IEnglishNetClient().cancelRequestAll();
  }
}