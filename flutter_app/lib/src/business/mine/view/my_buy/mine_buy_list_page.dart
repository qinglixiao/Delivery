import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/src/business/mine/view/my_buy/mine_all_buy_list_page.dart';
import 'package:flutter_ienglish_fine/src/business/mine/view/my_buy/mine_cancel_list_page.dart';
import 'package:flutter_ienglish_fine/src/business/mine/view/my_buy/mine_pay_list_page.dart';
import 'package:flutter_ienglish_fine/src/business/mine/view/my_buy/mine_wait_pay_list_page.dart';
import 'package:flutter_ienglish_fine/src/business/mine/view/my_buy/mine_wait_receiving_list_page.dart';

class MineBuyListPage extends StatefulWidget {
  @override
  _MineBuyListPageState createState() => _MineBuyListPageState();
}

class _MineBuyListPageState extends State<MineBuyListPage> with PageBridge {

  int initialIndex = 0;

  @override
  Widget build(BuildContext context) {
    AllListPage allListPage = AllListPage();
    WaitPayListPage waitPayListPage = WaitPayListPage();
    WaitReceivingListPage waitReceivingListPage = WaitReceivingListPage();
    PayListPage payListPage = PayListPage();
    CancelListPage cancelListPage = CancelListPage();

    return FutureBuilder(
        future: _initPageParams(context),
        builder: (BuildContext context, AsyncSnapshot<Object> snapshot) {
          if (snapshot?.data == null) {
            return Container();
          }
          initialIndex = snapshot.data;
          return RootPageWidget(
              appBar: IsAppBar(
                title: S.of(context).my_buy_order,
              ),
              body: TopTabBar(
                bgColor: Values.of(context).c_white_background,
                tabBarModelList: [
                  TopTabBarModel(title: S.of(context).order_status_7, contentWidget: allListPage),
                  TopTabBarModel(title: S.of(context).order_status_4, contentWidget: waitPayListPage),
                  TopTabBarModel(title: S.of(context).order_status_5, contentWidget: waitReceivingListPage),
                  TopTabBarModel(title: S.of(context).order_status_3, contentWidget: payListPage),
                  TopTabBarModel(title: S.of(context).order_status_6, contentWidget: cancelListPage)
                ],
                indicatorColor: Values.of(context).c_orange_background_0b,
                indicatorHeight: Values.d_2,
                labelColor: Values.of(context).c_orange_front_0b,
                unselectedLabelColor: Values.of(context).c_black_front_66,
                initialIndex: initialIndex,
              ));
        });
  }

  Future<int> _initPageParams(BuildContext context) async {
    var args = await getInitArg(context);
    if (args == null) return 0;
    if (args is Map && args.containsKey('type')) {
      return args['type'];
    }
    return 0;
  }
}
