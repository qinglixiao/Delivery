import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/src/business/message/bean/msg_list.dart';
import 'package:flutter_ienglish_fine/src/business/message/comm/message_provider.dart';
import 'package:flutter_ienglish_fine/src/comm/constant.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_ienglish_fine/src/main/home/bean/home.dart';
import 'package:flutter_ienglish_fine/src/main/home/viewmodel/home_view_model.dart';
import 'package:flutter_ienglish_fine/src/business/budget/view/dialog_protocol.dart';
import 'package:flutter_ienglish_fine/src/comm/event/message_center.dart';
import '../../index_tab.dart';

class HomePage extends StatefulWidget with OnTabChangedMixin {
  HomePageState _homePageState = HomePageState();

  @override
  State<StatefulWidget> createState() {
    return _homePageState;
  }

  @override
  onTabChanged(int index) {
    _homePageState.onTabChanged(index);
  }
}

class HomePageState extends State<HomePage> with PageBridge, AutomaticKeepAliveClientMixin {
  HomeViewModel _homeViewModel;
  List _bannerList = new List();
  List<NewsItemBean> _newsList = new List();
  FunctionBean _functionBean;
  OrderNumBean _orderNumBean;
  StreamSubscription _subscription;

  @override
  bool get wantKeepAlive => true;

  @override
  void initState() {
    super.initState();
    _homeViewModel = HomeViewModel();
    _subscription = eventCenter.listen<MessageReadEvent>((event) {
      _homeViewModel.hasRead = true;
    });
  }

  List handelBanner(BannerBean bannerBean) {
    if (bannerBean == null || bannerBean.items == null) {
      return [];
    }
    List itemList = [];
    for (BannerItemBean item in bannerBean.items) {
      itemList.add(item.imgUrl);
    }
    return itemList;
  }

  @override
  Widget build(BuildContext context) {
    return RootPageWidget(
        appBar: IsAppBar(
            title: S.of(context).home_title,
            titleColor: Values.of(context).c_white_front,
            color: [Values.of(context).c_blue_background_light, Values.of(context).c_blue_background_dark],
            canBack: false,
            rightWidget: Container(
                child: GestureDetector(
              onTap: () {
                openCustomerCenterPage();
              },
              child: Image.asset('assets/images/appBar_setting.png'),
            ))),
        viewModel: _homeViewModel,
        feedback: () {
          _homeViewModel.getHomeData();
        },
        pageChangedCallBack: (routerName) {
          pageChangedCallBack(routerName);
        },
        task: _homeViewModel.getHomeData(),
        body: ScrollWidget(context));
  }

  Widget ScrollWidget(BuildContext context) {
    return Container(
        child: Stack(
      children: <Widget>[
        Container(
          padding: EdgeInsets.only(left: 0, right: 0, top: 0),
          height: 200,
          decoration: BoxDecoration(
            gradient: LinearGradient(
              colors: [Values.of(context).c_blue_background_light, Values.of(context).c_blue_background_dark],
            ),
          ),
        ),
        PullToRefresh(
            refreshHeader: RefreshHeader(txtColor: Values.of(context).c_white_front),
            child: CustomScrollView(
              slivers: <Widget>[
                SliverToBoxAdapter(
                  child: Container(
                    decoration: BoxDecoration(
                      gradient: LinearGradient(
                        begin: Alignment.topCenter,
                        end: Alignment.bottomCenter,
                        colors: [
                          Values.of(context).c_translucent_f8_dark,
                          Values.of(context).c_grey_background_f8,
                          Values.of(context).c_grey_background_f8,
                          Values.of(context).c_grey_background_f8,
                          Values.of(context).c_grey_background_f8,
                        ],
                      ),
                    ),
                    child: Column(
                      children: <Widget>[
                        TopWidget(),
                        Container(
                          color: Values.of(context).c_grey_background_f8,
                          child: Column(
                            children: <Widget>[
                              FunctionWidget(),
                              NewsListHeaderWidget(),
                            ],
                          ),
                        )
                      ],
                    ),
                  ),
                ),
                NewsListWidget(),
              ],
            ),
            onRefresh: () {
              return _homeViewModel.getHomeData();
            })
      ],
    ));
  }

  Widget TopWidget() {
    final _size = MediaQuery.of(context).size;
    return Container(
        child: Stack(
      children: <Widget>[
        Column(
          children: <Widget>[
            Container(
              padding: EdgeInsets.only(left: 0, right: 0, top: 0),
              height: 47,
              decoration: BoxDecoration(
                gradient: LinearGradient(
                  begin: Alignment.centerLeft,
                  end: Alignment.centerRight,
                  colors: [Values.of(context).c_blue_background_light, Values.of(context).c_blue_background_dark],
                ),
              ),
            ),
            Container(
              padding: EdgeInsets.only(left: 0, right: 0, top: 0, bottom: 0),
              height: _size.width * 154 / 375 - 47,
              color: Values.of(context).c_grey_background_f8,
            )
          ],
        ),
        BannerViewWidget(),
      ],
    ));
  }

  Widget BannerViewWidget() {
    return StreamBuilder<BannerBean>(
        stream: _homeViewModel.streamHomeBanner,
        builder: (BuildContext context, AsyncSnapshot<BannerBean> snapshot) {
          if (snapshot.data != null) {
            _bannerList = handelBanner(snapshot.data);
          }
          if (_bannerList.length == 0) {
            return Container();
          }
          return AspectRatio(
            aspectRatio: 375 / 154,
            child: Container(
              padding: EdgeInsets.only(left: 12, right: 12),
              child: BannerWidget(
                  list: _bannerList,
                  callback: (int index) {
                    BannerItemBean item = snapshot.data.items[index];
                    if (!StringUtil.isEmpty(item.redirectUrl)) {
                      open(RouterName.web_view, argument: WebParams(url: item.redirectUrl, title: item.name));
                    }
                  }),
            ),
          );
        });
  }

  Widget FunctionWidget() {
    final size = MediaQuery.of(context).size;
    final width = size.width;
    return StreamBuilder<FunctionBean>(
        stream: _homeViewModel.streamHomeFunction,
        builder: (BuildContext context, AsyncSnapshot<FunctionBean> snapshot) {
          if (snapshot.data != null) {
            _functionBean = snapshot.data;
          }
          if (_functionBean == null) {
            return Container();
          }
          int count = (_functionBean.data?.bigmenu == null) ? 0 : _functionBean.data?.bigmenu.length;
          final height = ((width - 36) / 2 * 85 / 170 + 12) * count / 2.ceil() + 8;
          int midcount = (_functionBean.data?.smallmenu == null) ? 0 : _functionBean.data?.smallmenu.length;
          final midheight = ((width - 48) / 3 * 90 / 110 + 12) * (count / 3.ceil());
          return Column(
            children: <Widget>[
              Container(
                  color: Values.of(context).c_grey_background_f8,
                  padding: EdgeInsets.only(top: 20, left: 12, right: 12),
                  height: height,
                  child: GridView.builder(
                      itemCount: count,
                      physics: NeverScrollableScrollPhysics(),
                      gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                        //横轴元素个数
                        crossAxisCount: 2,
                        //纵轴间距
                        mainAxisSpacing: 11,
                        //横轴间距
                        crossAxisSpacing: 11,
                        //子组件宽高长度比例
                        childAspectRatio: 170 / 85,
                      ),
                      itemBuilder: (BuildContext context, int index) {
                        return ClipRRect(
                          borderRadius: BorderRadius.circular(5.0),
                          child: Container(
                            child: FunctionItem(_functionBean.data?.bigmenu[index]),
                          ),
                        );
                      })),
              SizedBox(
                height: 18,
              ),
//              Container(
//                  color: Values.of(context).c_grey_background_f8,
//                  padding: EdgeInsets.only(top: 12, left: 12, right: 12),
//                  height: midheight,
//                  child: GridView.builder(
//                    itemCount: midcount,
//                    physics: NeverScrollableScrollPhysics(),
//                    gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
//                      //横轴元素个数
//                      crossAxisCount: 3,
//                      //纵轴间距
//                      mainAxisSpacing: 12,
//                      //横轴间距
//                      crossAxisSpacing: 12,
//                      //子组件宽高长度比例
//                      childAspectRatio: 110 / 90,
//                    ),
//                    itemBuilder: (BuildContext context, int index) {
//                      return ClipRRect(
//                        borderRadius: BorderRadius.circular(5.0),
//                        child: Container(
//                          child: MiddleFunctionItem(
//                              _functionBean.data?.smallmenu[index]),
//                        ),
//                      );
//                    },
//                  ))
            ],
          );
        });
  }

  Widget FunctionItem(FunctionItemBean itemBean) {
    return GestureDetector(
        onTap: () {
          if (itemBean.code == "001") {
            showDialog(
                context: context,
                child: DialogProtocol(onTap: () {
                  open(RouterName.good_list);
                }, linkOnTap: (String path) {
                  open(RouterName.web_view, argument: WebParams(url: NetworkConfig().getHostForWeb() + path));
                }));
          } else if (itemBean.code == "002") {
            open(RouterName.dispatch_list);
          } else if (itemBean.code == "003") {
            open(RouterName.receiving_list);
          } else {
            open(RouterName.replenish_list);
          }
        },
        child: Container(
          child: Stack(
            children: <Widget>[
              Container(
                padding: EdgeInsets.all(0),
                child: CachedNetworkImage(
                  imageUrl: itemBean.url,
                  placeholder: (BuildContext context, String url) {
                    return Container(
                      color: Values.of(context).c_grey_background_cc,
                    );
                  },
                ),
              ),
              Container(
                  padding: EdgeInsets.only(left: Values.d_12, top: Values.d_12),
                  alignment: Alignment.centerLeft,
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: <Widget>[
                      Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: <Widget>[
                          Container(
                            child: Text(itemBean.name,
                                style: TextStyle(
                                    fontSize: Values.s_text_20,
                                    fontWeight: Values.font_Weight_medium,
                                    color: Values.of(context).c_white_front)),
                          ),
                          Container(
                            margin: EdgeInsets.only(top: Values.d_2),
                            child: Text(
                              itemBean.description,
                              style: TextStyle(
                                  fontSize: Values.s_text_12,
                                  fontWeight: Values.font_Weight_normal,
                                  color: Values.of(context).c_white_front),
                            ),
                          ),
                        ],
                      ),
                      Container(
                        margin: EdgeInsets.only(right: 7),
                        padding: EdgeInsets.only(right: 7),
                        alignment: Alignment.topCenter,
                        child: BadgeWidget(itemBean.code),
                      )
                    ],
                  ))
            ],
          ),
        ));
  }

  Widget BadgeWidget(String _codeType) {
    return StreamBuilder<OrderNumBean>(
        stream: _homeViewModel.streamHomeOrderNum,
        builder: (BuildContext context, AsyncSnapshot<OrderNumBean> snapshot) {
          if (snapshot.data != null) {
            _orderNumBean = snapshot.data;
          }
          if (_orderNumBean == null) {
            return Container();
          }
          int badgecount = 0;
          if (snapshot.data != null) {
            if (_codeType == '002') {
              badgecount = _orderNumBean.shippingCount;
            } else if (_codeType == '003') {
              badgecount = _orderNumBean.receivCount;
            } else if (_codeType == '004') {
              badgecount = _orderNumBean.clearingCount;
            }
          }
          if (badgecount == 0) {
            return Container();
          } else {
            return Container(
                height: 16,
                alignment: Alignment.center,
                child: ClipRRect(
                  borderRadius: BorderRadius.circular(8.0),
                  child: Container(
                    color: Values.of(context).c_white_background,
                    child: Text(' ${badgecount} ',
                        style: TextStyle(
                            fontSize: Values.s_text_13,
                            fontWeight: Values.font_Weight_normal,
                            color: Values.of(context).c_red_front_68)),
                  ),
                ));
          }
        });
  }

  Widget MiddleFunctionItem(FunctionItemBean itemBean) {
    return GestureDetector(
      onTap: () {
        ProgressHUD.showText(warnText: '点击事件');
      },
      child: Stack(children: <Widget>[
        ClipRRect(
          borderRadius: BorderRadius.circular(5.0),
          child: Container(
            padding: EdgeInsets.all(0),
            margin: EdgeInsets.only(top: 25),
            child: CachedNetworkImage(
              imageUrl: itemBean.bigUrl != null ? itemBean.bigUrl : '',
              fit: BoxFit.fill,
              placeholder: (BuildContext context, String url) {
                return Container(
                  color: Values.of(context).c_grey_background_cc,
                );
              },
              errorWidget: (BuildContext context, String url, dynamic error) {
                return Container(
                  color: Values.of(context).c_grey_background_cc,
                );
              },
            ),
          ),
        ),
        Container(
          alignment: Alignment.center,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: <Widget>[
              Container(
                child: CachedNetworkImage(
                  imageUrl: itemBean.url,
                  placeholder: (BuildContext context, String url) {
                    return Container(
                      color: Values.of(context).c_grey_background_cc,
                    );
                  },
                  errorWidget: (BuildContext context, String url, dynamic error) {
                    return Container(
                      color: Values.of(context).c_grey_background_cc,
                    );
                  },
                ),
                width: 55,
                height: 55,
              ),
              Container(
                margin: EdgeInsets.only(bottom: Values.d_10),
                child: Text(
                  itemBean.name,
                  style: TextStyle(
                      fontSize: Values.s_text_14,
                      fontWeight: Values.font_Weight_medium,
                      color: Values.of(context).c_black_front_33),
                ),
              )
            ],
          ),
        )
      ]),
    );
  }

  Widget NewsListHeaderWidget() {
    return Container(
      color: Values.of(context).c_grey_background_f8,
      padding: EdgeInsets.only(left: 12, right: 12, top: 0),
      child: ClipRRect(
        borderRadius: BorderRadius.only(topLeft: Radius.circular(5.0), topRight: Radius.circular(5.0)),
        child: Container(
            color: Values.of(context).c_white_background,
//            height: Values.d_50,
            child: Column(
              children: <Widget>[
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: <Widget>[
                    Container(
                      alignment: Alignment.center,
                      margin: EdgeInsets.only(left: 12),
                      height: Values.d_50 - 1,
                      child: Text(
                        '消息通知',
                        style: TextStyle(
                            fontSize: Values.s_text_18,
                            fontWeight: Values.font_Weight_medium,
                            color: Values.of(context).c_black_front_33),
                      ),
                    ),
                    Container(
                      height: Values.d_50 - 1,
                      alignment: Alignment.centerRight,
                      child: CupertinoButton(
                          child: Container(
                            child: Row(
                              children: <Widget>[
                                Text(
                                  '查看全部',
                                  style: TextStyle(
                                      fontSize: Values.s_text_12,
                                      fontWeight: Values.font_Weight_normal,
                                      color: Values.of(context).c_black_front_99),
                                ),
                                Image.asset('assets/images/more_right.png'),
                              ],
                            ),
                          ),
                          onPressed: () {
                            open(RouterName.msg_center);
                          }),
                    ),
                  ],
                ),
                Container(
                  color: Values.of(context).c_grey_ea,
                  margin: EdgeInsets.only(left: 12, right: 12, bottom: 0),
                  height: 1,
                )
              ],
            )),
      ),
    );
  }

  Widget NewsListWidget() {
    return StreamBuilder<NewsBean>(
        stream: _homeViewModel.streamHomeNews,
        builder: (BuildContext context, AsyncSnapshot<NewsBean> snapshot) {
          if (snapshot.data != null) {
            _newsList = snapshot.data?.items;
          }
          if (_newsList == null) {
            _newsList = new List();
          }
          return SliverList(
              delegate: SliverChildBuilderDelegate(NewsItem, childCount: _newsList.length > 5 ? 5 : _newsList.length));
        });
  }

  Widget NewsItem(BuildContext context, int index) {
    NewsItemBean itemBean = _newsList[index];
    return GestureDetector(
        onTap: () {
          MsgListItem msgListItem = MsgListItem();
          msgListItem
            ..messageReceiverId = itemBean.messageReceiverId
            ..status = itemBean.status
            ..title = itemBean.title
            ..content = itemBean.content
            ..type = null;
          if (msgListItem.status != "已读") {
            _homeViewModel.readMsg(msgListItem.messageReceiverId, (info) {
              eventCenter.emit(MessageReadEvent());
            });
          }
          MessageProvider().openMsgDetailPage(msgListItem);
        },
        child: Container(
            color: Values.of(context).c_grey_background_f8,
            padding: EdgeInsets.only(left: Values.d_12, right: Values.d_12),
            child: Container(
                padding: EdgeInsets.only(left: Values.d_15, right: Values.d_15, top: Values.d_10,bottom: Values.d_5),
                color: Values.of(context).c_white_background,
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: <Widget>[
                    Container(
                      child: Row(
                        children: <Widget>[
                          itemBean.status != S.of(context).msg_read_title
                              ? Container(
                                  color: Values.of(context).c_red_background_68,
                                  padding: EdgeInsets.only(left: 3, top: 2, right: 3, bottom: 2),
                                  margin: EdgeInsets.only(right: 5),
                                  child: Text(
                                    itemBean.status,
                                    style: TextStyle(
                                        fontSize: Values.s_text_8,
                                        fontWeight: Values.font_Weight_normal,
                                        color: Values.of(context).c_white_front),
                                  ),
                                )
                              : Container(
                                  color: Values.of(context).c_grey_background_ee,
                                  padding: EdgeInsets.only(left: 3, top: 2, right: 3, bottom: 2),
                                  margin: EdgeInsets.only(right: 5),
                                  child: Text(
                                    itemBean.status,
                                    style: TextStyle(
                                        fontSize: Values.s_text_8,
                                        fontWeight: Values.font_Weight_normal,
                                        color: Values.of(context).c_black_front_33),
                                  ),
                                ),
                          Expanded(
                            child: Text(
                              itemBean.title,
                              style: TextStyle(
                                  fontSize: Values.s_text_15,
                                  fontWeight: Values.font_Weight_medium,
                                  color: Values.of(context).c_black_front_33),
                              softWrap: false,
                              maxLines: 1,
                              overflow: TextOverflow.ellipsis,
                            ),
                          )
                        ],
                      ),
                    ),
                    Container(
                      margin: EdgeInsets.only(top: 11),
                      child: Container(
                        child: Text(
                          itemBean.createTime,
                          style: TextStyle(
                              fontSize: Values.s_text_12,
                              fontWeight: Values.font_Weight_normal,
                              color: Values.of(context).c_black_front_99),
                        ),
                      ),
                    )
                  ],
                ))));
  }

  pageChangedCallBack(String routerName) {
    if (routerName != null &&
        routerName == RouterName.index_page &&
        IndexPage.of(context).currentIndex == INDEX_PAGE_HOME_TAB &&
        _homeViewModel.hasRead) {
      _homeViewModel.hasRead = false;
      _homeViewModel.getNews();
    }
  }

  onTabChanged(int index) {
    if (INDEX_PAGE_HOME_TAB == index && _homeViewModel.hasRead) {
      _homeViewModel.hasRead = false;
      _homeViewModel.getNews();
    }
  }

  void openCustomerCenterPage(){
    open(RouterName.web_view, argument: WebParams(url: '$INDEX_PAGE_CUSTOMER_HELP_URL${SpUtil.getUserMobile()}',showTitle: true,title:S.of(context).customer_help_title),);
  }

  @override
  void dispose() {
    _homeViewModel.dispose();
    _subscription.cancel();
    super.dispose();
  }
}
