import 'package:flutter_ienglish_fine/src/business/mine/bean/mine_buy_list.dart';
import 'package:flutter_ienglish_fine/src/business/mine/comm/interfaces.dart';
import 'package:flutter_ienglish_fine/src/main/home/bean/home.dart';
import 'package:flutter_lib/flutter_lib.dart';

class MineBuyListModel extends BaseModel {
  Future<MineBuyList> getAllBuyList(String pageIndex) async {
    return IEnglishNetClient().get(
      NET_GET_ORDER_LIST,
      queryParameters: {'pageNo': pageIndex, "pageSize": "10"},
    ).then((value) {
      return add(MineBuyList.fromJson(value.data));
    });
  }

  Future<MineBuyList> getWaitPayList(String pageIndex) async {
    return IEnglishNetClient().get(
      NET_GET_ORDER_LIST,
      queryParameters: {'pageNo': pageIndex, "pageSize": "10", "status": "待付款"},
    ).then((value) {
      return add(MineBuyList.fromJson(value.data));
    });
  }

  Future<MineBuyList> getWaitReceivingList(String pageIndex) async {
    return IEnglishNetClient().get(
      NET_GET_ORDER_LIST,
      queryParameters: {'pageNo': pageIndex, "pageSize": "10", "status": "待收货"},
    ).then((value) {
      return add(MineBuyList.fromJson(value.data));
    });
  }

  Future<MineBuyList> getPayList(String pageIndex) async {
    return IEnglishNetClient().get(
      NET_GET_ORDER_LIST,
      queryParameters: {'pageNo': pageIndex, "pageSize": "10", "status": "已完成"},
    ).then((value) {
      return add(MineBuyList.fromJson(value.data));
    });
  }

  Future<MineBuyList> getCancelList(String pageIndex) async {
    return IEnglishNetClient().get(
      NET_GET_ORDER_LIST,
      queryParameters: {'pageNo': pageIndex, "pageSize": "10", "status": "已取消"},
    ).then((value) {
      return add(MineBuyList.fromJson(value.data));
    });
  }

  Future postCancelOrder(String code, String message) async {
    return IEnglishNetClient()
        .post(NET_POST_CANCEL_ORDER, queryParameters: {'numberCode': code, "abolishMsg": message}).then((value) {
      return add(NewsBean.fromJson(value.data));
    });
  }

  @override
  void dispose() {
    super.dispose();
    IEnglishNetClient().cancelRequestAll();
  }
}
