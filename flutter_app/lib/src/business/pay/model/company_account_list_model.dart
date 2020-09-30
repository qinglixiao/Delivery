import 'package:flutter_ienglish_fine/src/business/pay/bean/account_list.dart';
import 'package:flutter_ienglish_fine/src/business/pay/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';

class CompanyAccountListModel extends BaseModel {
  Future getCompanyAccountListInfo(String numberDesc) async {
    return IEnglishNetClient()
        .get(NET_GET_COMPANY_ACCOUNT_LIST, queryParameters: {
      'numberDesc': numberDesc,
    }).then((value) {
      return add(AccountListInfo.fromJson(value.data));
    });
  }
}