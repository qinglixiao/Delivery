import 'package:flutter_ienglish_fine/src/business/pay/bean/account_info.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/bank_name_info.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/company_account_info.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/open_account_info.dart';
import 'package:flutter_ienglish_fine/src/business/pay/model/open_account_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

class OpenAccountViewModel extends BaseViewModel {
  OpenAccountViewModel() : super();

  OpenAccountModel _openAccountModel = OpenAccountModel();

  Stream<AccountInfo> get streamAccountInfo =>
      _openAccountModel.getStream<AccountInfo>();

  Stream<BankNameInfo> get streamBankInfo =>
      _openAccountModel.getStream<BankNameInfo>();

  Stream<CompanyAccountInfo> get streamCompanyAccountInfo =>
      _openAccountModel.getStream<CompanyAccountInfo>();

  Future getOpenAccountInfo() async {
    String code = SpUtil.getUserNumberDesc();
    return _openAccountModel
        .getOpenAccountInfo('分账通分账版', '服务商', code)
        .then((value) {})
        .catchError((e) {
      error(e);
    });
  }

  Future getOpenCompanyAccountInfo() async {
    String code = SpUtil.getUserNumberDesc();
    return _openAccountModel
        .getOpenCompanyAccountInfo(code)
        .then((value) {})
        .catchError((e) {
      error(e);
    });
  }

  Future postOpenAccount(
      String realName,
      String cardNo,
      String phone,
      String headBankCode,
      String bankCode,
      Function(OpenAccountInfoBean info) callback) async {
    String code = SpUtil.getUserNumberDesc();
    return _openAccountModel
        .postOpenAccount('分账通分账版', '服务商', code, realName, cardNo, phone,
            headBankCode, bankCode,'https://www.ienglish.cn/app/open_person')
        .then((value) {
      callback(value);
    }).catchError((e) {
      error(e);
    });
  }

  Future postOpenCompanyAccount(
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
      Function(OpenAccountInfoBean info) callback) async {
    String code = SpUtil.getUserNumberDesc();
    return _openAccountModel
        .postOpenCompanyAccount(
            '分账通分账版',
            code,
            custName,
            orgLicense,
            idNo,
            acctNo,
            openBank,
            openBankName,
            mobileNo,
            legalName,
            legalGender,
            legalIdNo,
      'https://www.ienglish.cn/app/open_company')
        .then((value) {
      callback(value);
    }).catchError((e) {
      error(e);
    });
  }

  Future getBankListInfo() async {
    return _openAccountModel
        .getBankInfoList()
        .then((value) {})
        .catchError((e) {
      error(e);
    });
  }

  @override
  void dispose() {
    super.dispose();
    _openAccountModel?.close();
  }
}
