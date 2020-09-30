import 'package:flutter_ienglish_fine/src/business/budget/bean/good_detail.dart';
import 'package:flutter_ienglish_fine/src/business/budget/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';

///
///create by lx
///date 2020/7/25
///

class GoodDetailModel extends BaseModel {
  Future getGoodDetail(int collocationId) async {
    return IEnglishNetClient().get(NET_GET_GOOD_DETAIL,
        queryParameters: {"collocationId": collocationId}).then((value) {
      return add(GoodDetail.fromJson(value.data));
    });
  }
}
