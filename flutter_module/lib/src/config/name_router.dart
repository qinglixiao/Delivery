import 'package:fluttermodule/src/main/index_tab.dart';

class RouterName {
  static const String home = "\\";
  static const String good_list = "good_list_page";
  static const String order_detail = "order_detail";
}

var routers = {
//  RouterName.good_list: (context) => GoodListPage(),
//  RouterName.order_detail: (context) => OrderDetail(),
  RouterName.home: (context) => IndexPage()
};
