import 'package:flutter_lib/flutter_lib.dart';
import 'package:fluttermodule/as/business/budget/bean/good.dart';

class GoodListModel extends BaseModel<GoodBean> {
  dynamic params;

  GoodListModel({this.params});

  Future load() async {
    return Future.delayed(Duration(seconds: 3), () {
      Logger.print("获取订货列表...");
      add(GoodBean());
    });
  }

  @override
  void dispose() {}
}
