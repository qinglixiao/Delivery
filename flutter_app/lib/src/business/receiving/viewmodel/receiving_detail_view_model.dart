import 'package:flutter_ienglish_fine/src/business/dispatch/bean/dispatch_detail.dart';
import 'package:flutter_ienglish_fine/src/business/receiving/model/receiving_detail_model.dart';
import 'package:flutter_ienglish_fine/src/business/receiving/bean/receiving_detail.dart';
import 'package:flutter_lib/flutter_lib.dart';

class ReceivingDetailViewModel extends BaseViewModel {
  ReceivingDetailViewModel() : super();

  ReceivingDetailModel _receivingDetailModel = ReceivingDetailModel();

  Stream<DispatchOrderDetail> get streamOrderDetail=> _receivingDetailModel.getStream<DispatchOrderDetail>();
  Stream<ExpressInfoData> get streamExpressInfoData => _receivingDetailModel.getStream<ExpressInfoData>();

  Future getReceivingDetail(String orderId) async{
    loadOrderDetail(orderId);
    loadOrderExpressInfoData(orderId);
  }

  Future loadOrderDetail(String orderId) async {
    return _receivingDetailModel.getDispatchDetail(orderId).then((value) {
    }).catchError((e) {
      error(e);
    });
  }

  Future loadOrderExpressInfoData(String orderId) async {
    return _receivingDetailModel.getOrderShippingData(orderId).then((value) {
    }).catchError((e) {
      error(e);
    });
  }

  Future loadOrderReceivingAffirm(
      String numberCode,
      Function(ReceivingDetailBean statusInfo) callback) async{
    return _receivingDetailModel.loadOrderReceivingAffirm(numberCode).then((value) {
      callback(value);
    }).catchError((e) {
      error(e);
    });
  }

  Future loadOrderLogistics(String logisticsNo) async{
    return _receivingDetailModel.getOrderLogistics(logisticsNo);
  }


  @override
  void dispose() {
    super.dispose();
    _receivingDetailModel?.close();
  }
}
