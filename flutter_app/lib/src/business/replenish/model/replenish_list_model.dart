import 'package:flutter_ienglish_fine/src/business/replenish/bean/replenish_list.dart';
import 'package:flutter_ienglish_fine/src/business/replenish/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';

class ReplenishListModel extends BaseModel {
  Future getReplenishList(String pageIndex) async {
    return IEnglishNetClient().get(NET_GET_REPLENISH_LIST,queryParameters: {"pageNo":pageIndex,'pageSize': '10'}).then((value) {
      return add(ReplenishListBean.fromJson(value.data));
    });
  }
  @override
  void dispose() {
    super.dispose();
    IEnglishNetClient().cancelRequestAll();
    // TODO: implement dispose
  }
}