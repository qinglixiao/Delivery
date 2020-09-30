
import 'package:flutter_ienglish_fine/src/business/mine/bean/place_list.dart';
import 'package:flutter_ienglish_fine/src/business/mine/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';

class PlaceListModel extends BaseModel {

  Future<PlaceList> getPlaceList() async {
    return IEnglishNetClient().get(
        NET_GET_PLACE_LIST
    ).then((value) {
      return add(PlaceList.fromJson(value.data));
    });
  }

  @override
  void dispose() {
    super.dispose();
    IEnglishNetClient().cancelRequestAll();
  }
}