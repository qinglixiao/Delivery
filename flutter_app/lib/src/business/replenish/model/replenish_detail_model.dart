import 'package:flutter_ienglish_fine/src/business/replenish/bean/replenish_detail.dart';
import 'package:flutter_ienglish_fine/src/business/replenish/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';

class ReplenishDetailModel extends BaseModel {
  Future getReplenishDetail(String numberCode) async {
    return IEnglishNetClient().get(NET_GET_REPLENISH_DETAIL,queryParameters: {'numberCode': numberCode}).then((value) {
      return add(ReplenishDetailBean.fromJson(value.data));
    });
  }

  Future postRejectReplenish(String numberCode) async {
    return IEnglishNetClient().post(NET_GET_REPLENISH_REJECT,queryParameters: {'numberCode': numberCode}).then((value) {
      return add(NetBean.fromJson(value.data));
    });
  }

  Future postReplenishOrder(String numberCode) async {
    return IEnglishNetClient().post(NET_GET_REPLENISH_DETAIL,queryParameters: {'numberCode': numberCode}).then((value) {
      return add(NetBean.fromJson(value.data));
    });
  }


  @override
  void dispose() {
    super.dispose();
    IEnglishNetClient().cancelRequestAll();
    // TODO: implement dispose
  }
}