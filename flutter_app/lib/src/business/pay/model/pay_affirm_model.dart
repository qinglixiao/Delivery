import 'package:flutter_ienglish_fine/src/business/budget/comm/provider.dart';
import 'package:flutter_ienglish_fine/src/business/dispatch/bean/dispatch_detail.dart';
import 'package:flutter_ienglish_fine/src/business/dispatch/comm/interfaces.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/pay_method.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/pay_offline.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/pay_platform.dart';
import 'package:flutter_ienglish_fine/src/business/pay/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';

///
///create by lx
///date 2020/7/23
///

class PayAffirmModel extends BaseModel {
  getGoodFuns(String code) async {
    var data = await BudgetProvider().getGoodFuns(code);
    return add(data);
  }

  Future getPayplatform(String paymethod, String orderPayDesc,
      String returnUrl, String platform, String numberDesc) async {
    return IEnglishNetClient().get(NET_GET_PAY_PLATFORM, queryParameters: {
      'registerWallet': true,
      'paymethod': paymethod,
      'orderPayDesc': orderPayDesc,
      'returnUrl': returnUrl,
      'platform': platform,
      'numberDesc': numberDesc,
    }).then((value) {
      return add(PayPlatform.fromJson(value.data));
    });
  }

  Future getOrderQueryPay(String externalNo) async {
    return IEnglishNetClient().get(NET_GET_QUERYPAY_ORDER, queryParameters: {'externalNo': externalNo,}).then((value) {
      return add(NetBean.fromJson(value.data));
    });
  }

  Future getDispatchDetail(String orderId) async {
    return IEnglishNetClient().get(NET_GET_ORDER_DETAIL,
        queryParameters: {'numberCode': orderId}).then((value) {
      return add(DispatchOrderDetail.fromJson(value.data));
    });
  }

  Future getPayMethod(String numberDesc) async {
    return IEnglishNetClient().get(NET_GET_PAY_METHOD, queryParameters: {
      'configId': numberDesc,
      'platform': '服务商',
    }).then((value) {
      return add(PayMethod.fromJson(value.data));
    });
  }

  Future getOfflinePay(String code) async {
    return IEnglishNetClient().get(NET_GET_OFFLINE_PAY, queryParameters: {
      'numberCode': code,
    }).then((value) {
      return add(PayOffline.fromJson(value.data));
    });
  }




}
