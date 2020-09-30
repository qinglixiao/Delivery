import 'package:flutter_ienglish_fine/src/business/pay/bean/pay_platform.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/pay_withdraw_config.dart';
import 'package:flutter_ienglish_fine/src/business/pay/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';


class PayWithdrawModel extends BaseModel {

  Future getWithdrawConfig(String configId, String defVal) async {
    return IEnglishNetClient().get(NET_GET_PAY_WITHDRAW_CONFIG, queryParameters: {
      'configId': configId,
      'defVal': defVal,
    }).then((value) {
      return add(PayWithdrawConfig.fromJson(value.data));
    });
  }

  Future getWithdrawData(String drawFee, String numberDesc) async {
    return IEnglishNetClient().get(NET_GET_PAY_WITHDRAW, queryParameters: {
      'drawFee': drawFee,
      'numberDesc': numberDesc,
    }).then((value) {
      return add(NetBean.fromJson(value.data));
    });
  }
}