import 'package:flutter_ienglish_fine/src/business/dispatch/bean/dispatch_detail.dart';
import 'package:flutter_ienglish_fine/src/business/dispatch/bean/logistics_list.dart';
import 'package:flutter_ienglish_fine/src/business/receiving/bean/receiving_detail.dart';
import 'package:flutter_ienglish_fine/src/business/dispatch/comm/interfaces.dart';
import 'package:flutter_ienglish_fine/src/business/receiving/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';

class ReceivingDetailModel extends BaseModel {
  Future getDispatchDetail(String orderId) async {
    return IEnglishNetClient().get(NET_GET_ORDER_DETAIL,
        queryParameters: {'numberCode': orderId}).then((value) {
      return add(DispatchOrderDetail.fromJson(value.data));
    });
  }

  Future getOrderShippingData(String orderId) async {
    return IEnglishNetClient().get(NET_GET_ORDER_SHIPPING_LIST,
        queryParameters: {'numberCode': orderId}).then((value) {
      return add(ExpressInfoData.fromJson(value.data));
    });
  }

  Future loadOrderReceivingAffirm(String orderId) async {
    return IEnglishNetClient().post(NET_GET_RECEIVING_AFFIRM,
        queryParameters: {'numberCode': orderId}).then((value) {
      return add(ReceivingDetailBean.fromJson(value.data));
    });
  }

  Future getOrderLogistics(String logisticsNo) async {
    return IEnglishNetClient().get(NET_GET_LOGISTICS, queryParameters: {"logisticsNo": logisticsNo}).then((value) {
      return add(LogisticsList.fromJson(value.data));
    });
  }


  @override
  void dispose() {
    super.dispose();
    IEnglishNetClient().cancelRequestAll();
    // TODO: implement dispose
  }
}