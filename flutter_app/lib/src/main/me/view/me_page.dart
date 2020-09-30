import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/src/business/budget/bean/good_confirm.dart';
import 'package:flutter_ienglish_fine/src/business/message/bean/msg_status.dart';
import 'package:flutter_ienglish_fine/src/business/pay/view/account/dialog_open_account.dart';
import 'package:flutter_ienglish_fine/src/comm/constant.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter_ienglish_fine/src/main/me/bean/me.dart';
import 'package:flutter_ienglish_fine/src/main/me/viewmodel/me_view_model.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_ienglish_fine/src/comm/event/user_info_event.dart';

import '../../index_tab.dart';

class MePage extends StatefulWidget with OnTabChangedMixin {
  _MePageState _mePageState = _MePageState();

  @override
  _MePageState createState() => _mePageState;

  @override
  onTabChanged(int index) {
    _mePageState.onTabChanged(index);
  }
}

class _MePageState extends State<MePage> with PageBridge, AutomaticKeepAliveClientMixin {
  List _ordersList;
  List _toolList;

  ///企业账户
  bool _isEnterpriseAccount;

  ///账户有钱
  bool _isHaveBalance;

  bool _isNeedRead = false;

  MeViewModel _viewModel = MeViewModel();

  StreamSubscription _userInfoSubscription;

  @override
  bool get wantKeepAlive => true;

  @override
  void initState() {
    super.initState();
    _userInfoSubscription = eventCenter.listen<UserInfoEvent>((event) {
      _viewModel?.updateUserInfo = true;
    });
  }

  @override
  void dispose() {
    _userInfoSubscription?.cancel();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    _ordersList = [
      {'image': 'assets/images/me_order_icon_1.png', 'name': S.of(context).my_buy_order},
      {'image': 'assets/images/me_order_icon_2.png', 'name': S.of(context).my_sell_order}
    ];
    _toolList = [
      {'image': 'assets/images/me_tool_icon_1.png', 'id': '1', 'name': S.of(context).my_friend},
      {'image': 'assets/images/me_tool_icon_2.png', 'id': '2', 'name': S.of(context).my_data_center},
      {'image': 'assets/images/me_tool_icon_3.png', 'id': '3', 'name': S.of(context).my_store},
      {'image': 'assets/images/me_tool_icon_4.png', 'id': '4', 'name': S.of(context).my_firm},
      {'image': 'assets/images/me_tool_icon_7.png', 'id': '7', 'name': S.of(context).my_message},
      {'image': 'assets/images/me_tool_icon_5.png', 'id': '5', 'name': S.of(context).my_assistant},
      {'image': 'assets/images/me_tool_icon_8.png', 'id': '8', 'name': S.of(context).my_setting},
      {'image': 'assets/images/me_tool_icon_6.png', 'id': '6', 'name': S.of(context).my_service}
    ];

    return RootPageWidget(
        viewModel: _viewModel,
        task: _viewModel.getUserInfo(),
        pageChangedCallBack: (routerName) {
          pageChangedCallBack(routerName);
        },
        body: Container(
            child: PullToRefresh(
                child: CustomScrollView(
                  slivers: <Widget>[
                    SliverToBoxAdapter(
                      child: StreamBuilder<MsgStatus>(
                          stream: _viewModel.streamMessageStatus,
                          builder: (BuildContext context, AsyncSnapshot<MsgStatus> snapshot) {
                            if (snapshot.data != null) {
                              _isNeedRead = snapshot.data?.isRead == 1;
                            }
                            return Column(
                              children: <Widget>[
                                HeaderInfoWidget(),
                                OrderInfoWidget(),
                                ToolsInfoWidget(),
                              ],
                            );
                          }),
                    )
                  ],
                ),
                onRefresh: () {
                  return _viewModel.getUserInfo();
                })));
  }

  Widget HeaderInfoWidget() {
    return Container(
      child: Stack(
        children: <Widget>[
          AspectRatio(
            aspectRatio: 375 / 221,
            child: Image.asset('assets/images/mine_bg.png', fit: BoxFit.fill),
          ),
          Positioned(
            top: 20,
            left: 0,
            right: 0,
            height: Values.d_50,
            child: NavigationBarWidget(),
          ),
          Positioned(
            top: 70,
            left: 0,
            right: 0,
            child: UserInfoWidget(),
          ),
          Positioned(
            bottom: 0,
            left: 0,
            right: 0,
            height: Values.d_80,
            child: BalanceInfoWidget(),
          ),
        ],
      ),
    );
  }

  Widget NavigationBarWidget() {
    return Container(
      margin: EdgeInsets.only(right: Values.d_5),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.end,
        children: <Widget>[
          Stack(
            children: <Widget>[
              IconButton(
                  icon: Image.asset('assets/images/message_icon.png'),
                  onPressed: () {
                    open(RouterName.msg_center);
                  }),
              _isNeedRead?Positioned(
                  right: 10,
                  top: 10,
                  child: ClipOval(
                    child: Container(
                      width: 5,
                      height: 5,
                      color: Values.of(context).c_red_background_68,
                    ),
                  )):Positioned(
                  right: 0,
                  child: Container()
              ),
            ],
          ),
          IconButton(
              icon: Image.asset('assets/images/setting_icon.png'),
              onPressed: () {
                openSettingPage();
              })
        ],
      ),
    );
  }

  Widget UserInfoWidget() {
    String levelImage = 'assets/images/level_icon_' + SpUtil.getUseRankNum().toString() + '.png';
    final size = MediaQuery.of(context).size;
    final width = size.width;
    return GestureDetector(
      onTap: () {
        openUserInfoPage();
      },
      child: Container(
        color: Values.c_translucent,
        child: Row(
          children: <Widget>[
            Container(
              margin: EdgeInsets.only(left: Values.d_20, right: Values.d_20),
              decoration: BoxDecoration(
                border: Border.all(color: Values.of(context).c_white_background, width: 1),
                borderRadius: new BorderRadius.circular((Values.d_50 / 2)), // 圆角度
              ),
              width: Values.d_50,
              height: Values.d_50,
              child: ClipOval(
                child: CachedNetworkImage(
                  imageUrl: StringUtil.getNotNullString(SpUtil.getUserImage()),
                  fit: BoxFit.cover,
                  width: Values.d_50,
                  height: Values.d_50,
                  placeholder: (BuildContext context, String url) {
                    return Container(
                      color: Values.of(context).c_grey_background_cc,
                    );
                  },
                ),
              ),
            ),
            Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                Text(StringUtil.getNotNullString(SpUtil.getUserName()),
                    style: TextStyle(color: Values.of(context).c_white_front, fontSize: Values.s_text_15)),
                Container(
                  margin: EdgeInsets.only(top: Values.d_5),
                  child: Image.asset(levelImage),
                ),
              ],
            )
          ],
        ),
      ),
    );
  }

  Widget BalanceInfoWidget() {
    String _total = '0.00';
    String _bal = '0.00';
    String _account = '0';
    return Container(
      color: Values.c_translucent_black,
      child: Row(
        children: <Widget>[
          Expanded(
              flex: 1,
              child: StreamBuilder<MySettleAccounts>(
                  stream: _viewModel.streamSettleAccounts,
                  builder: (BuildContext context, AsyncSnapshot<MySettleAccounts> snapshot) {
                    if (snapshot.data != null) {
                      _total = snapshot.data?.total?.toStringAsFixed(2);
                    }
                    return Container(
                      child: GestureDetector(
                        behavior: HitTestBehavior.opaque,
                        onTap: () {
                          open(RouterName.my_earnings);
                        },
                        child: Column(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: <Widget>[
                            Text.rich(
                              TextSpan(
                                  text: _total,
                                  style: TextStyle(color: Values.of(context).c_white_front, fontSize: Values.s_text_18),
                                  children: [
                                    TextSpan(
                                        text: S.of(context).rise_in_price_until,
                                        style: TextStyle(
                                            color: Values.of(context).c_white_front, fontSize: Values.s_text_11)),
                                  ]),
                            ),
                            SizedBox(
                              height: Values.d_10,
                            ),
                            Text(S.of(context).my_earnings + ' >',
                                style:
                                    TextStyle(color: Values.of(context).c_grey_front_cc, fontSize: Values.s_text_12)),
                          ],
                        ),
                      ),
                    );
                  })),
          Container(
            width: 1,
            height: Values.d_15,
            color: Values.of(context).c_grey_background_cc,
          ),
          Expanded(
              flex: 1,
              child: StreamBuilder<FundQuery>(
                  stream: _viewModel.streamFundQuery,
                  builder: (BuildContext context, AsyncSnapshot<FundQuery> snapshot) {
                    if (snapshot.data != null) {
                      _bal = snapshot.data?.data?.bal?.toStringAsFixed(2);
                    }
                    return Container(
                      child: GestureDetector(
                        behavior: HitTestBehavior.opaque,
                        onTap: () {
                          open(RouterName.my_balance);
                        },
                        child: Column(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: <Widget>[
                            Text.rich(
                              TextSpan(
                                  text: _bal,
                                  style: TextStyle(color: Values.of(context).c_white_front, fontSize: Values.s_text_18),
                                  children: [
                                    TextSpan(
                                        text: S.of(context).rise_in_price_until,
                                        style: TextStyle(
                                            color: Values.of(context).c_white_front, fontSize: Values.s_text_11)),
                                  ]),
                            ),
                            SizedBox(
                              height: Values.d_10,
                            ),
                            Text(S.of(context).my_balance + ' >',
                                style:
                                    TextStyle(color: Values.of(context).c_grey_front_cc, fontSize: Values.s_text_12)),
                          ],
                        ),
                      ),
                    );
                  })),
          Container(
            width: 1,
            height: Values.d_15,
            color: Values.of(context).c_grey_background_cc,
          ),
          Expanded(
              flex: 1,
              child: StreamBuilder<AccountCountBean>(
                  stream: _viewModel.streamAccountCount,
                  builder: (BuildContext context, AsyncSnapshot<AccountCountBean> snapshot) {
                    if (snapshot.data != null) {
                      _account = snapshot.data?.data?.countTotalAccount.toString();
                    }
                    return Container(
                      child: GestureDetector(
                        behavior: HitTestBehavior.opaque,
                        onTap: () {
                          open(RouterName.web_view,
                              argument: WebParams(
                                  url:
                                      "https://qa-agent.tope365.com/#/iToAgent?type=3&showNav=false&mobile=${SpUtil.getUserMobile()}",
                                  title: S.of(context).my_account));
                        },
                        child: Column(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: <Widget>[
                            Text(_account + ' ',
                                style: TextStyle(color: Values.of(context).c_white_front, fontSize: Values.s_text_18)),
                            SizedBox(
                              height: Values.d_10,
                            ),
                            Text(S.of(context).my_account + ' >',
                                style:
                                    TextStyle(color: Values.of(context).c_grey_front_cc, fontSize: Values.s_text_12)),
                          ],
                        ),
                      ),
                    );
                  }))
        ],
      ),
    );
  }

  Widget OrderInfoWidget() {
    return Container(
      padding: EdgeInsets.all(Values.d_15),
      margin: EdgeInsets.only(
        left: Values.d_12,
        top: Values.d_12,
        right: Values.d_12,
      ),
      decoration:
          BoxDecoration(borderRadius: BorderRadius.circular(Values.d_5), color: Values.of(context).c_white_background),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        mainAxisAlignment: MainAxisAlignment.start,
        children: <Widget>[
          Text(S.of(context).my_order_info,
              style: TextStyle(
                  color: Values.of(context).c_black_front_33,
                  fontWeight: Values.font_Weight_medium,
                  fontSize: Values.s_text_16)),
          Container(
            height: 80,
            child: GridView.builder(
                padding: EdgeInsets.only(top: Values.d_20),
                itemCount: _ordersList.length,
                physics: NeverScrollableScrollPhysics(),
                gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                  //横轴元素个数
                  crossAxisCount: 4,
                  //横轴间距

                  crossAxisSpacing:
                      (MediaQuery.of(context).size.width - 60 * 4 - Values.d_12 * 2 - Values.d_15 * 2) / 3,
                ),
                itemBuilder: (BuildContext context, int index) {
                  return GestureDetector(
                    behavior: HitTestBehavior.opaque,
                    onTap: () {
                      if (index == 0) {
                        open(RouterName.my_buy_list);
                      } else if (index == 1) {
                        open(RouterName.my_sell_List);
                      }
                    },
                    child: FunctionItemWidget(_ordersList[index], false),
                  );
                }),
          )
        ],
      ),
    );
  }

  Widget ToolsInfoWidget() {
    return StreamBuilder<MyAccount>(
        stream: _viewModel.streamAccount,
        builder: (BuildContext context, AsyncSnapshot<MyAccount> snapshot) {
          if (snapshot.data != null) {
            _isEnterpriseAccount = snapshot.data.account;
            _isHaveBalance = snapshot.data.status;
          }
          return Container(
            padding: EdgeInsets.all(Values.d_15),
            margin: EdgeInsets.only(
              left: Values.d_12,
              top: Values.d_12,
              right: Values.d_12,
            ),
            decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(Values.d_5), color: Values.of(context).c_white_background),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              mainAxisAlignment: MainAxisAlignment.start,
              children: <Widget>[
                Text(S.of(context).my_tool_info,
                    style: TextStyle(
                        color: Values.of(context).c_black_front_33,
                        fontWeight: Values.font_Weight_medium,
                        fontSize: Values.s_text_16)),
                Container(
                  height: 151,
                  child: GridView.builder(
                      padding: EdgeInsets.only(top: Values.d_20),
                      itemCount: _toolList.length,
                      physics: NeverScrollableScrollPhysics(),
                      gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                        //横轴元素个数
                        crossAxisCount: 4,
                        //纵轴间距
                        mainAxisSpacing: 11,
                        //横轴间距

                        crossAxisSpacing:
                            (MediaQuery.of(context).size.width - 60 * 4 - Values.d_12 * 2 - Values.d_15 * 2) / 3,
                      ),
                      itemBuilder: (BuildContext context, int index) {
                        return GestureDetector(
                          behavior: HitTestBehavior.opaque,
                          onTap: () {
                            if (index == 0) {
                              openInviteFriendPage();
                            } else if (index == 3) {
                              if (_isHaveBalance && !_isEnterpriseAccount) {
                                showWarnDialog(
                                    S.of(context).open_business_account_warn, () => {open(RouterName.my_balance)});
                              } else if (!_isEnterpriseAccount) {
                                showWarnDialog(S.of(context).open_company_account_warn,
                                    () => {open(RouterName.open_company_account)});
                              } else {
                                open(RouterName.company_account_list);
                              }
                            } else if (index == 2) {
                              showOpenAccountDialog();
                            } else if (index == 4) {
                              openMessageCenterPage();
                            } else if (index == 6) {
                              openSettingPage();
                            } else if (index == 7) {
                              openCustomerCenterPage();
                            }
                          },
                          child: FunctionItemWidget(_toolList[index], true),
                        );
                      }),
                )
              ],
            ),
          );
        });
  }

  Widget FunctionItemWidget(Map item, bool changeColor) {
    return Container(
      child: Column(
        children: <Widget>[
          Stack(
            children: <Widget>[
              changeColor
                  ? Image.asset(
                      item['image'],
                      color: Values.of(context).c_black_front_33,
                    )
                  : Image.asset(item['image']),
              (_isNeedRead && item['id']=='7')?Positioned(
                  right: 0,
                  child: ClipOval(
                    child: Container(
                      width: 5,
                      height: 5,
                      color: Values.of(context).c_red_background_68,
                    ),
                  )):Positioned(
                  right: 0,
                  child: Container()
              ),
            ],
          ),
          SizedBox(height: Values.d_5),
          Text(item['name'], style: TextStyle(color: Values.of(context).c_black_front_33, fontSize: Values.s_text_12))
        ],
      ),
    );
  }

  void showWarnDialog(String content, Function() callback) {
    showDialog(
        context: context,
        child: DialogTool(
          bgColor: Values.of(context).c_white_background,
          title: S.of(context).warn_title,
          titleFontSize: Values.s_text_18,
          titleColor: Values.of(context).c_black_front_33,
          content: content,
          contentColor: Values.of(context).c_black_front_33,
          leftTitle: S.of(context).warn_cancel,
          leftColor: Values.of(context).c_orange_front_0b,
          leftBgColor: Values.of(context).c_white_background,
          rightTitle: S.of(context).warn_sure,
          rightColor: Values.of(context).c_white_front,
          rightBgColor: Values.of(context).c_orange_background_0b,
          onTap: (int index) {
            if (index == 1) {
              callback();
            }
          },
        ));
  }

  void showOpenAccountDialog() {
    showDialog(
        context: context,
        child: DialogOpenAccount(onTap: (int type) {
          if (type == 0) {
            open(RouterName.open_person_account);
          } else {
            open(RouterName.open_company_account);
          }
        }));
  }

  void openUserInfoPage() {
    open(RouterName.set_user_info_page);
  }

  void openSettingPage() {
    open(RouterName.setting_page);
  }

  void openCustomerCenterPage() {
    open(
      RouterName.web_view,
      argument: WebParams(
          url: '$INDEX_PAGE_CUSTOMER_HELP_URL${SpUtil.getUserMobile()}',
          showTitle: true,
          title: S.of(context).customer_help_title),
    );
  }

  void openMessageCenterPage() {
    open(RouterName.msg_center);
  }

  void openInviteFriendPage() {
    open(RouterName.invite_friend);
  }

  pageChangedCallBack(routerName) {
    if (routerName == RouterName.index_page &&
        IndexPage.of(context).currentIndex == INDEX_PAGE_ME_TAB &&
        _viewModel?.updateUserInfo) {
      _viewModel?.updateUserInfo = false;
      setState(() {});
    }
  }

  onTabChanged(int index) {
    if (INDEX_PAGE_ME_TAB == index && _viewModel?.updateUserInfo) {
      _viewModel?.updateUserInfo = false;
      setState(() {});
    }
  }
}
