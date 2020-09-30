import 'package:flutter_ienglish_fine/src/business/budget/bean/good_confirm.dart';
import 'package:flutter_ienglish_fine/src/business/pay/model/pay_withdraw_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

class PayWithdrawViewModel extends BaseViewModel {
  PayWithdrawViewModel() : super();

  PayWithdrawModel _model = PayWithdrawModel();

  Stream<FundQuery> get streamFundQuery => _model.getStream<FundQuery>();

  Future loadWithdrawConfig() async {
    return _model.getWithdrawConfig('提现限额','50000');
  }

  Future loadWithdrawData(String drawFee) async {
    String numberDesc = SpUtil.getUserNumberDesc();
    return _model.getWithdrawData(drawFee, numberDesc);
  }

  @override
  void dispose() {
    super.dispose();
    _model?.close();
  }
}