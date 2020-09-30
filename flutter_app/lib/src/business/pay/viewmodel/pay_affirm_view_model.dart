import 'package:flutter_ienglish_fine/src/business/budget/bean/good_confirm.dart';
import 'package:flutter_ienglish_fine/src/business/budget/model/good_confirm_model.dart';
import 'package:flutter_ienglish_fine/src/business/dispatch/bean/dispatch_detail.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/pay_method.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/pay_platform.dart';
import 'package:flutter_ienglish_fine/src/business/pay/model/pay_affirm_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

class PayAffirmViewModel extends BaseViewModel {
  PayAffirmViewModel() : super();

  PayAffirmModel _model = PayAffirmModel();

  GoodConfirmModel _goodConfirmModel = GoodConfirmModel();

  Stream<FundQuery> get streamFundQuery => _goodConfirmModel.getStream<FundQuery>();

  Stream<PayMethod> get streamPayPayMethod => _model.getStream<PayMethod>();

  Stream<DispatchOrderDetail> get streamOrderDetail=> _model.getStream<DispatchOrderDetail>();

  Future getPayAffirmData() async{
    loadPayMethod();
  }

  Future loadFundQuery() async {
    String code = SpUtil.getUserNumberDesc();
    showLoadding();
    return _goodConfirmModel.getFundQuery(code).then((value) {
      hideLoadding();
    }).catchError((e) {
      error(e);
    });
  }

  Future loadPayMethod() async {
    String code = SpUtil.getUserNumberDesc();
    return _model.getPayMethod(code).then((value) {
    }).catchError((e) {
      error(e);
    });
  }

  Future loadPayPlatform(String paymethod, String orderPayDesc,
      String returnUrl, String platform, String numberDesc,Function(PayPlatform info) callback) async {
    return _model.getPayplatform(paymethod, orderPayDesc, returnUrl, platform, numberDesc).then((value){
      callback(value);
    }).catchError((e) {
      error(e);
    });
  }

  Future loadOrderQueryPay(String externalNo) async {
    return _model.getOrderQueryPay(externalNo);
  }

  Future loadOrderDetail(String orderId) {
    return _model.getDispatchDetail(orderId).then((value) {
    }).catchError((e) {
      error(e);
    });
  }

  Future loadOfflinePay(String orderId) {
    return _model.getOfflinePay(orderId);
  }

  @override
  void dispose() {
    super.dispose();
    _model?.close();
    _goodConfirmModel?.close();
  }
}
