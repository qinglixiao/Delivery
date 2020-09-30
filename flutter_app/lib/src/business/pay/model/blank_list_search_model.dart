import 'package:flutter_ienglish_fine/src/business/pay/bean/bank_name_info.dart';
import 'package:flutter_ienglish_fine/src/business/pay/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';

///  created by：sunyuancun
/// 2020/9/23 
///desc:

class BlankListSearchModel extends BaseModel {

  Future<BankNameInfo> getBankInfoList() async {
    return IEnglishNetClient()
        .get(NET_GET_BANK_LIST).then((value) {
      return add(BankNameInfo.fromJson(value.data));
    });
  }

}