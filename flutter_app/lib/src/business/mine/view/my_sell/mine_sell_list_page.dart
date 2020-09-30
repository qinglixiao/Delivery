import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'mine_all_sell_list_page.dart';
import 'mine_sell_finish_list_page.dart';
import 'mine_sell_gathering_list_page.dart';
import 'mine_sell_replenish_list_page.dart';

class MineSellListPage extends StatefulWidget {
  @override
  _MineSellListPageState createState() => _MineSellListPageState();
}

class _MineSellListPageState extends State<MineSellListPage> with PageBridge {
  int initialIndex = 0;

  @override
  Widget build(BuildContext context) {
    SellAllListPage allListPage = SellAllListPage();
    SellGatheringListPage waitGatheringListPage = SellGatheringListPage();
    SellReplenishListPage waitReplenishListPage = SellReplenishListPage();
    SellFinishListPage finishListPage = SellFinishListPage();

    return FutureBuilder(
        future: _initPageParams(context),
        builder: (BuildContext context, AsyncSnapshot<Object> snapshot) {
          if (snapshot?.data == null) {
            return Container();
          }
          initialIndex = snapshot.data;
          return RootPageWidget(
              appBar: IsAppBar(
                title: S.of(context).my_sell_order,
              ),
              body: TopTabBar(
                bgColor: Values.of(context).c_white_background,
                tabBarModelList: [
                  TopTabBarModel(title: S.of(context).order_status_7, contentWidget: allListPage),
                  TopTabBarModel(title: S.of(context).order_status_1, contentWidget: waitGatheringListPage),
                  TopTabBarModel(title: S.of(context).order_status_2, contentWidget: waitReplenishListPage),
                  TopTabBarModel(title: S.of(context).order_status_3, contentWidget: finishListPage),
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
