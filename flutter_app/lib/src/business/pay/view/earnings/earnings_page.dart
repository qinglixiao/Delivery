import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/earnings.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/pay_platform.dart';
import 'package:flutter_ienglish_fine/src/business/pay/viewmodel/earnings_view_model.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_ienglish_fine/src/business/budget/bean/good_confirm.dart';
import 'package:flutter_ienglish_fine/src/business/pay/viewmodel/pay_affirm_view_model.dart';

import '../balance/balance_all_list_page.dart';
import '../balance/balance_fetch_list_page.dart';
import '../balance/balance_frozen_list_page.dart';
import 'earnings_detail_all_page.dart';
import 'earnings_detail_finish_page.dart';
import 'earnings_detail_wait_page.dart';

class EarningsPage extends StatefulWidget {
  @override
  _EarningsPageState createState() => _EarningsPageState();
}

class _EarningsPageState extends State<EarningsPage> with PageBridge {
  EarningsViewModel _earningsViewModel = EarningsViewModel();
  EarningsBean _bean;

  @override
  Widget build(BuildContext context) {
    return RootPageWidget(
        appBar: IsAppBar(
          title: S.of(context).my_earnings,
          leftOnTap: () {
            pop();
          },
        ),
        viewModel: _earningsViewModel,
        task: _earningsViewModel.getEarningsData(),
        body: Container(
          color: Values.of(context).c_white_background,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              BalanceInfo(),
              Container(
                margin: EdgeInsets.only(left: Values.d_15,top: Values.d_8,bottom: Values.d_15),
                child: Text(
                  S.of(context).earnings_detail,
                  style: TextStyle(
                      fontSize:
                      Values.s_text_16,
                      fontWeight: Values
                          .font_Weight_medium,
                      color: Values.of(context)
                          .c_black_front_33,
                      decoration:
                      TextDecoration.none),
                  textAlign: TextAlign.left,
                ),
              ),
              DetailList()
            ],
          ),
        ));
  }

  Widget BalanceInfo() {
    return StreamBuilder(
        stream: _earningsViewModel.streamEarnings,
        builder: (BuildContext context, AsyncSnapshot<EarningsBean> snapshot) {
          if (snapshot.data != null) {
            _bean = snapshot.data;
          }
          return Container(
            color: Values.of(context).c_white_background,
            padding: EdgeInsets.only(
                left: Values.d_12,
                right: Values.d_12,
                top: Values.d_12,
                bottom: Values.d_22),
            child: Stack(
              children: <Widget>[
                SizedBox(
                  width: MediaQuery.of(context).size.width -
                      Values.d_12 * 2,
                  height: (MediaQuery.of(context).size.width -
                      Values.d_12 * 2) *
                      141 /
                      351,
                  child: Image.asset('assets/images/earnings_bg.png',
                      fit: BoxFit.fill),
                ),
                SizedBox(
                  width: MediaQuery.of(context).size.width -
                      Values.d_12 * 2,
                  height: (MediaQuery.of(context).size.width -
                      Values.d_12 * 2) *
                      141 /
                      351,
                  child: Container(
                    padding: EdgeInsets.only(
                        left: Values.d_26,
                        right: Values.d_26,
                        top: Values.d_20,
                        bottom: Values.d_20),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      mainAxisAlignment:
                      MainAxisAlignment.spaceBetween,
                      children: <Widget>[
                        Text(
                          S.of(context).earnings_title,
                          style: TextStyle(
                              fontSize:
                              Values.s_text_12,
                              fontWeight: Values
                                  .font_Weight_normal,
                              color: Values.of(context)
                                  .c_white_front,
                              decoration:
                              TextDecoration.none),
                          textAlign: TextAlign.left,
                        ),
                        Text(
                          _bean!=null?_bean.total.toStringAsFixed(2):'',
                          style: TextStyle(
                              fontSize:
                              Values.s_text_30,
                              fontWeight: Values
                                  .font_Weight_normal,
                              color: Values.of(context)
                                  .c_white_front,
                              decoration:
                              TextDecoration.none),
                          textAlign: TextAlign.left,
                        ),
                        Container(
                          color: Values.of(context).c_grey_ea,
                          height: 1,
                        ),
                        Text(
                          S.of(context).earnings_warn,
                          style: TextStyle(
                              fontSize:
                              Values.s_text_12,
                              fontWeight: Values
                                  .font_Weight_normal,
                              color: Values.of(context)
                                  .c_grey_front_cc,
                              decoration:
                              TextDecoration.none),
                          textAlign: TextAlign.left,
                        ),
                      ],
                    ),
                  ),
                )
              ],
            ),
          );
        });
  }


  Widget DetailList() {
    EarningsAllListPage allListPage = EarningsAllListPage();
    EarningsWaitListPage waitListPage = EarningsWaitListPage();
    EarningsFinishListPage finishListPage = EarningsFinishListPage();
    return Expanded(child: TopTabBar(
      bgColor: Values.of(context).c_white_background,
      tabBarModelList: [
        TopTabBarModel(title: S.of(context).order_status_7, contentWidget: allListPage),
        TopTabBarModel(title: S.of(context).earnings_wait_title, contentWidget: waitListPage),
        TopTabBarModel(title: S.of(context).earnings_finish_title, contentWidget: finishListPage),
      ],
      indicatorColor: Values.of(context).c_orange_background_0b,
      indicatorHeight: Values.d_2,
      labelColor: Values.of(context).c_orange_front_0b,
      unselectedLabelColor:Values.of(context).c_black_front_66,
    ));
  }


}
