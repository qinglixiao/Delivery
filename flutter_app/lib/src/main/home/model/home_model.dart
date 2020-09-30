import 'package:flutter_ienglish_fine/src/business/message/comm/interfaces.dart';
import 'package:flutter_ienglish_fine/src/main/home/bean/home.dart';
import 'package:flutter_ienglish_fine/src/main/home/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';

class HomeModel extends BaseModel {
  Future getAdvertises() async {
    return IEnglishNetClient()
        .get(NET_GET_ADVERTISE)
        .then((value) => {add(BannerBean.fromJson(value.data))});
  }

  Future getByTypeGroup() async {
    return IEnglishNetClient()
        .get(NET_GET_GROUP)
        .then((value) => {add(FunctionBean.fromJson(value.data))});
  }

  Future getOrderSize() async {
    return IEnglishNetClient()
        .get(NET_GET_SIZE)
        .then((value) => {add(OrderNumBean.fromJson(value.data))});
  }

  Future getMessage() async {
    return IEnglishNetClient()
        .get(NET_GET_NEWS)
        .then((value) => {add(NewsBean.fromJson(value.data))});
  }

  Future<NetBean> readMsg(int messageReceiverId) async {
    return IEnglishNetClient().post(NET_POST_READ,queryParameters: {'messageReceiverId':messageReceiverId}).then((value) {
      return add(NetBean.fromJson(value.data));
    });
  }

}
