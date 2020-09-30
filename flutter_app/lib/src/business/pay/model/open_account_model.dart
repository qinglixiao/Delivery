import 'package:flutter_ienglish_fine/src/business/pay/bean/account_info.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/bank_name_info.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/company_account_info.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/open_account_info.dart';
import 'package:flutter_ienglish_fine/src/business/pay/comm/interfaces.dart';
import 'package:flutter_ienglish_fine/src/main/home/bean/home.dart';
import 'package:flutter_lib/flutter_lib.dart';

class OpenAccountModel extends BaseModel {
  Future getOpenAccountInfo(
      String payChannel, String platform, String numberDesc) async {
    return IEnglishNetClient().get(NET_GET_OPEN_ACCOUNT_INFO, queryParameters: {
      'payChannel': payChannel,
      'platform': platform,
      'numberDesc': numberDesc,
    }).then((value) {
      return add(AccountInfo.fromJson(value.data));
    });
  }

  Future getOpenCompanyAccountInfo(String numberDesc) async {
    return IEnglishNetClient()
        .get(NET_GET_OPEN_COMPANY_ACCOUNT_INFO, queryParameters: {
      'numberDesc': numberDesc,
    }).then((value) {
      return add(CompanyAccountInfo.fromJson(value.data));
    });
  }

  Future postOpenAccount(
      String payChannel,
      String platform,
      String numberDesc,
      String realName,
      String cardNo,
      String phone,
      String headBankCode,
      String bankCode,
      String url) async {
    return IEnglishNetClient().post(NET_POST_OPEN_ACCOUNT, queryParameters: {
      'realName': realName,
      'cardNo': cardNo,
      'phone': phone,
      'headBankCode': headBankCode,
      'bankCode': bankCode,
      'paychannel': payChannel,
      'platform': platform,
      'numberDesc': numberDesc,
      'url': url,
    }).then((value) {
      return add(OpenAccountInfoBean.fromJson(value.data));
    });
  }

  Future postOpenCompanyAccount(
    String payChannel,
    String numberDesc,
    String custName,
    String orgLicense,
    String idNo,
    String acctNo,
    String openBank,
    String openBankName,
    String mobileNo,
    String legalName,
    String legalGender,
    String legalIdNo,
    String url,
  ) async {
    return IEnglishNetClient()
        .post(NET_POST_OPEN_COMPANY_ACCOUNT, queryParameters: {
      'custName': custName,
      'orgLicense': orgLicense,
      'idNo': idNo,
      'acctNo': acctNo,
      'openBank': openBank,
      'openBankName': openBankName,
      'mobileNo': mobileNo,
      'legalName': legalName,
      'legalGender': legalGender,
      'legalIdNo': legalIdNo,
      'paychannel': payChannel,
      'numberDesc': numberDesc,
      'url': url,
    }).then((value) {
      return add(OpenAccountInfoBean.fromJson(value.data));
    });
  }

  Future getBankInfoList() async {
    return IEnglishNetClient()
        .get(NET_GET_BANK_LIST).then((value) {
      return add(BankNameInfo.fromJson(value.data));
    });
  }

}
