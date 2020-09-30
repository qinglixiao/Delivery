import 'package:flutter_ienglish_fine/src/business/pay/bean/balance_detail.dart';
import 'package:flutter_ienglish_fine/src/business/pay/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';

class BalanceDetailModel extends BaseModel {
  Future getPayAccountFolws(String code,String pageIndex) async {
    return IEnglishNetClient().post(NET_POST_PAYACCOUNT_WALLET_FOLWS,queryParameters: {'numberDesc': code,'pageNo': pageIndex,'type':'全部'}).then((value) {
      return add(BalanceDetailBean.fromJson(value.data));
    });
  }

  Future getPayAccountBalance(String code,String pageIndex) async {
    return IEnglishNetClient().post(NET_POST_PAYACCOUNT_BALANCE_DETAIL,queryParameters: {'numberDesc': code,'pageNo': pageIndex}).then((value) {
      return add(BalanceDetailBean.fromJson(value.data));
    });
  }


}
