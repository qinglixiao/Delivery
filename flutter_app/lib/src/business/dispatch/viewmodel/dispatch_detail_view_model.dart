import 'package:flutter_ienglish_fine/src/business/dispatch/bean/dispatch_detail.dart';
import 'package:flutter_ienglish_fine/src/business/dispatch/model/dispatch_detail_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

class DisPatchDetailViewModel extends BaseViewModel {
  DisPatchDetailViewModel() : super();

  DispatchDetailModel _dispatchDetailModel = DispatchDetailModel();

  Stream<DispatchOrderDetail> get streamOrderDetail=> _dispatchDetailModel.getStream<DispatchOrderDetail>();
  Stream<ExpressInfoData> get streamExpressInfoData => _dispatchDetailModel.getStream<ExpressInfoData>();

  Future getDispatchDetail(String orderId,bool isRefresh) async{
    if(isRefresh){
      showLoadding();
    }
    else{
      ProgressHUD.showLoading();
    }
    return Future.wait([loadOrderDetail(orderId),loadOrderExpressInfoData(orderId)]).then((value){
      if(isRefresh){
        hideLoadding();
      }
      else{
        ProgressHUD.hiddenHUD();
      }
    });
  }

  Future loadOrderDetail(String orderId) {
    return _dispatchDetailModel.getDispatchDetail(orderId).then((value) {
    }).catchError((e) {
      error(e);
    });
  }

  Future loadOrderExpressInfoData(String orderId) {
    return _dispatchDetailModel.getOrderShippingData(orderId).then((value) {
    }).catchError((e) {
      error(e);
    });
  }

  Future loadShipmentsData(
      String count,
      String ifSelfTaking,
      String logisticsName,
      String logisticsNo,
      String orderNO,
      String receiveAddress,
      String receiveMobile,
      String receiveName,
      Function(ExpressResultData statusInfo) callback) async{
    ProgressHUD.showLoading();
    return _dispatchDetailModel.postOrderShipping(count, ifSelfTaking, logisticsName, logisticsNo, orderNO, receiveAddress, receiveMobile, receiveName).then((value) {
      callback(value);
      ProgressHUD.hiddenHUD();
    }).catchError((e) {
      error(e);
    });
  }

  Future loadOrderLogistics(String logisticsNo) async{
    return _dispatchDetailModel.getOrderLogistics(logisticsNo);
  }

  @override
  void dispose() {
    super.dispose();
    _dispatchDetailModel?.close();
  }
}
