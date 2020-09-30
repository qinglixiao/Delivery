import 'package:flutter_ienglish_fine/src/business/mine/comm/interfaces.dart';
import 'package:flutter_ienglish_fine/src/main/home/bean/home.dart';
import 'package:flutter_lib/flutter_lib.dart';

class MineBuyDetailModel extends BaseModel {

  Future postCancelOrder(String code,String message) async {
    return IEnglishNetClient().post(NET_POST_CANCEL_ORDER,
        queryParameters: {'numberCode': code,"abolishMsg":message}).then((value) {
      return add(NewsBean.fromJson(value.data));
    });
  }

  @override
  void dispose() {
    super.dispose();
    IEnglishNetClient().cancelRequestAll();
  }
}
