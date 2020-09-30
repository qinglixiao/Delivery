import 'package:flutter_ienglish_fine/src/business/budget/bean/good.dart';
import 'package:flutter_ienglish_fine/src/business/budget/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';

class GoodListModel extends BaseModel {
  Future<Good> getGoodList() async {
    return IEnglishNetClient().get(
      NET_GET_GOOD_LIST,
      queryParameters: {"rightsName": "订货显示权"},
    ).then((value) {
      return add(Good.fromJson(value.data));
    });
  }

  @override
  void dispose() {
    super.dispose();
    IEnglishNetClient().cancelRequestAll();
  }
}
