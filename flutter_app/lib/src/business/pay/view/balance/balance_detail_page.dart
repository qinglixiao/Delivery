import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'balance_all_list_page.dart';
import 'balance_fetch_list_page.dart';
import 'balance_frozen_list_page.dart';
import 'balance_save_list_page.dart';

class BalanceDetailPage extends StatefulWidget {
  @override
  _BalanceDetailPageState createState() => _BalanceDetailPageState();
}

class _BalanceDetailPageState extends State<BalanceDetailPage> with PageBridge {
  @override
  Widget build(BuildContext context) {
    BalanceAllListPage allListPage = BalanceAllListPage();
    BalanceFetchListPage fetchListPage = BalanceFetchListPage();
    BalanceFrozenListPage frozenListPage = BalanceFrozenListPage();
    BalanceSaveListPage saveListPage = BalanceSaveListPage();
    int _index = 0;

    _index = ModalRoute.of(context).settings.arguments;

    Logger.print('===========================1');
    return RootPageWidget(
        appBar: IsAppBar(
          title: S.of(context).balance_detail_title,
        ),
        body: TopTabBar(
          bgColor: Values.of(context).c_white_background,
          initialIndex: _index,
          tabBarModelList: [
            TopTabBarModel(title: S.of(context).order_status_7, contentWidget: allListPage),
            TopTabBarModel(title: S.of(context).balance_detail_fetch, contentWidget: fetchListPage),
            TopTabBarModel(title: S.of(context).balance_detail_save, contentWidget: frozenListPage),
            TopTabBarModel(title: S.of(context).balance_detail_frozen, contentWidget: saveListPage),
          ],
          indicatorColor: Values.of(context).c_orange_background_0b,
          indicatorHeight: Values.d_2,
          labelColor: Values.of(context).c_orange_front_0b,
          unselectedLabelColor:Values.of(context).c_black_front_66,
        ));
  }
}