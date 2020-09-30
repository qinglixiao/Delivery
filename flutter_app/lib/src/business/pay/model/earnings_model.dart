import 'package:flutter_ienglish_fine/src/business/pay/bean/earnings.dart';
import 'package:flutter_ienglish_fine/src/business/pay/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';

class EarningsModel extends BaseModel {
  Future getEarningsInfo(String code) async {
    return IEnglishNetClient().get(NET_GET_EARNINGS,queryParameters: {'numberDesc': code}).then((value) {
      return add(EarningsBean.fromJson(value.data));
    });
  }

  Future getEarningsStatusInfo(String code,String status) async {
    return IEnglishNetClient().get(NET_GET_EARNINGS,queryParameters: {'numberDesc': code,"status":status,"platform":"服务商"}).then((value) {
      return add(EarningsBean.fromJson(value.data));
    });
  }
}

