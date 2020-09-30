import 'package:flutter/services.dart';
import 'package:flutter_ienglish_fine/src/business/budget/bean/good_confirm.dart';
import 'package:flutter_ienglish_fine/src/business/budget/model/good_confirm_model.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/account_info.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/bank_info.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/pay_platform.dart';
import 'package:flutter_ienglish_fine/src/business/pay/model/balance_model.dart';
import 'package:flutter_ienglish_fine/src/business/pay/model/open_account_model.dart';
import 'package:flutter_ienglish_fine/src/business/pay/model/pay_affirm_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

class BalanceViewModel extends BaseViewModel {
  BalanceViewModel() : super();

  BalanceModel _model = BalanceModel();

  GoodConfirmModel _placeDetailModel = GoodConfirmModel();

  OpenAccountModel _openAccountModel = OpenAccountModel();

  Stream<FundQuery> get streamFundQuery => _placeDetailModel.getStream<FundQuery>();
  Stream<BankInfoBean> get streamBankInfo=> _model.getStream<BankInfoBean>();
  Stream<AccountInfo> get streamAccountInfo => _openAccountModel.getStream<AccountInfo>();

  Future getBalanceData() async{
    String code = SpUtil.getUserNumberDesc();
    return Future.wait([loadFundQuery(code),loadBankInfo(code),loadOpenAccountInfo(code)]).then((value){});
  }

  Future loadFundQuery(String numberDesc) async {
    return _placeDetailModel.getFundQuery(numberDesc).then((value) {
    }).catchError((e) {
      error(e);
    });
  }

  Future loadBankInfo(String numberDesc) async {
    return _model.getBankInfo(numberDesc).then((value){
    }).catchError((e) {
      error(e);
    });
  }

  Future loadOpenAccountInfo(String numberDesc) async {
    return _openAccountModel.getOpenAccountInfo('分账通分账版', '服务商', numberDesc)
        .then((value) {})
        .catchError((e) {
      error(e);
    });
  }

  @override
  void dispose() {
    super.dispose();
    _model?.close();
    _placeDetailModel?.close();
  }
}
