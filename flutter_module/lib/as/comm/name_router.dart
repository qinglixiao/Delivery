import 'package:fluttermodule/as/business/budget/view/good_list_page.dart';
import 'package:fluttermodule/as/business/budget/view/order_detail.dart';

class RouterName {
  static const String good_list = "good_list_page";
  static const String order_detail = "order_detail";
}

var routers = {
  RouterName.good_list: (context) => GoodListPage(),
  RouterName.order_detail: (context) => OrderDetail(),
};
