import 'package:flutter_ienglish_fine/src/business/budget/bean/good_confirm.dart';
import 'package:flutter_ienglish_fine/src/business/mine/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';

class PlaceDetailModel extends BaseModel {
  Future<Place> getPlaceDetail(String id) async {
    return IEnglishNetClient()
        .get(NET_GET_PLACE_DETAIL, queryParameters: {"id": id}).then((value) {
      return add(Place.fromJson(value.data));
    });
  }

  Future<NetBean> postPlaceEditDetail(
      String id,
      String detailaddress,
      String province,
      String city,
      String area,
      String tel,
      String username,
      bool priority) async {
    return IEnglishNetClient().post(NET_POST_PLACE_EDIT, queryParameters: {
      "id": id,
      "detailaddress": detailaddress,
      "province": province,
      "city": city,
      "area": area,
      "tel": tel,
      "username": username,
      "priority": priority,
    }).then((value) {
      return add(NetBean.fromJson(value.data));
    });
  }

  Future<NetBean> postPlaceAddDetail(
      String detailaddress,
      String province,
      String city,
      String area,
      String tel,
      String username,
      bool priority) async {
    return IEnglishNetClient().post(NET_POST_PLACE_ADD, queryParameters: {
      "detailaddress": detailaddress,
      "province": province,
      "city": city,
      "area": area,
      "tel": tel,
      "username": username,
      "priority": priority,
    }).then((value) {
      return add(NetBean.fromJson(value.data));
    });
  }

  Future<NetBean> postPlaceDelete(String id) async {
    return IEnglishNetClient()
        .post(NET_POST_PLACE_DELETE, queryParameters: {"id": id}).then((value) {
      return add(NetBean.fromJson(value.data));
    });
  }

  @override
  void dispose() {
    super.dispose();
    IEnglishNetClient().cancelRequestAll();
  }
}
