import 'package:flutter_ienglish_fine/src/business/budget/comm/provider.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/bank_info.dart';
import 'package:flutter_ienglish_fine/src/business/pay/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';

class BalanceModel extends BaseModel {
  Future getBankInfo(String code) async {
    return IEnglishNetClient().get(NET_GET_PAYACCOUNT_WALLET,queryParameters: {'numberDesc': code}).then((value) {
      return add(BankInfoBean.fromJson(value.data));
    });
  }
}
