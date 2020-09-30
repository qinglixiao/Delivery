import 'package:flutter_ienglish_fine/src/business/dispatch/bean/dispatch_list.dart';
import 'package:flutter_ienglish_fine/src/business/dispatch/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';

class DispatchListModel extends BaseModel {
  Future getDispatchList(String pageIndex) async {
    return IEnglishNetClient().get(NET_GET_ORDER_List,queryParameters: {"pageNo":pageIndex}).then((value) {
      return add(DispatchListBean.fromJson(value.data));
    });
  }

  Future postRejectOrder(String numberCode) async {
    return IEnglishNetClient().get(NET_GET_ORDER_REJECT,queryParameters: {'numberCode': numberCode}).then((value) {
      return add(DispatchListBean.fromJson(value.data));
    });
  }

  @override
  void dispose() {
    super.dispose();
    IEnglishNetClient().cancelRequestAll();
    // TODO: implement dispose
  }
}