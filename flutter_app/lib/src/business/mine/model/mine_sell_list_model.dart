import 'package:flutter_ienglish_fine/src/business/mine/bean/mine_sell_list.dart';
import 'package:flutter_ienglish_fine/src/business/mine/comm/interfaces.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/pay_offline.dart';
import 'package:flutter_lib/flutter_lib.dart';

class MineSellListModel extends BaseModel {
  Future<MineSellList> getAllSellList(String pageIndex) async {
    return IEnglishNetClient().get(
      NET_GET_SELL_ORDER_LIST,
      queryParameters: {'pageNo': pageIndex, "pageSize": "10"},
    ).then((value) {
      return add(MineSellList.fromJson(value.data));
    });
  }

  Future<MineSellList> getWaitGatheringList(String pageIndex) async {
    return IEnglishNetClient().get(
      NET_GET_SELL_ORDER_LIST,
      queryParameters: {'pageNo': pageIndex, "pageSize": "10", "status": "待付款"},
    ).then((value) {
      return add(MineSellList.fromJson(value.data));
    });
  }

  Future<MineSellList> getWaitReplenishList(String pageIndex) async {
    return IEnglishNetClient().get(
      NET_GET_SELL_ORDER_LIST,
      queryParameters: {'pageNo': pageIndex, "pageSize": "10", "status": "待发货"},
    ).then((value) {
      return add(MineSellList.fromJson(value.data));
    });
  }

  Future<MineSellList> getFinishList(String pageIndex) async {
    return IEnglishNetClient().get(
      NET_GET_SELL_ORDER_LIST,
      queryParameters: {'pageNo': pageIndex, "pageSize": "10", "status": "已完成"},
    ).then((value) {
      return add(MineSellList.fromJson(value.data));
    });
  }

  Future<PayOffline> getAffirmPay(String numberCode) async {
    return IEnglishNetClient().get(
      NET_GET_AFFIRMPAY_ORDER,
      queryParameters: {"numberCode": numberCode},
    ).then((value) {
      return add(PayOffline.fromJson(value.data));
    });
  }

  @override
  void dispose() {
    super.dispose();
    IEnglishNetClient().cancelRequestAll();
  }
}
