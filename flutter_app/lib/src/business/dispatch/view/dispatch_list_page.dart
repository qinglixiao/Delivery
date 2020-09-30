import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/src/business/dispatch/bean/dispatch_list.dart';
import 'package:flutter_ienglish_fine/src/business/dispatch/comm/interfaces.dart';
import 'package:flutter_ienglish_fine/src/business/dispatch/viewmodel/dispatch_list_view_model.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_lib/flutter_lib.dart';

class DispatchListPage extends StatefulWidget with UIWrap {
  @override
  State<StatefulWidget> createState() {
    return _DispatchListPageState();
  }
}

class _DispatchListPageState extends State<DispatchListPage> with PageBridge {
  DispatchListViewModel _dispatchListViewModel;

  List<DispatchListItem> _itemList = List();
  int _pageIndex = 1;

  DispatchListBean _dispatchListBean;

  SimpleLoadMoreController _loadMoreController;

  @override
  void initState() {
    super.initState();
    _dispatchListViewModel = DispatchListViewModel();
    _loadMoreController = SimpleLoadMoreController((){
      loadMore();
    });
  }

  Widget _buildItemView(BuildContext context, Object itemData) {
    DispatchListItem data = itemData as DispatchListItem;

    Widget _senderInfo = Container(
      decoration: new BoxDecoration(
          border: new Border(
              bottom:
                  BorderSide(color: Values.of(context).c_grey_ea, width: 1.0))),
      padding: EdgeInsets.only(bottom: Values.d_15),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: <Widget>[
          Row(
            mainAxisAlignment: MainAxisAlignment.start,
            children: <Widget>[
              Text(S.of(context).dispatch_list_next_name + data.username,
                  style: TextStyle(
                      fontSize: Values.s_text_14,
                      color: Values.of(context).c_black_front_33)),
              SizedBox(
                width: Values.d_10,
              ),
              Text(
                  S.of(context).dispatch_detail_shipping_status1 +
                      '：' +
                      '${data.count - data.sendCount}',
                  style: TextStyle(
                      fontSize: Values.s_text_14,
                      color: Values.of(context).c_black_front_33)),
            ],
          ),
          Text(data.sendStatus,
              style: TextStyle(
                  fontSize: Values.s_text_14,
                  color: Values.of(context).c_red_front_68)),
        ],
      ),
    );

    Widget _image = CachedNetworkImage(
      width: 88,
      height: 88,
      imageUrl: data?.imgUrl ?? "",
    );

    Widget _title = Text(
      StringUtil.getNotNullString(data?.title),
      overflow: TextOverflow.ellipsis,
      maxLines: 2,
      style: TextStyle(
          fontSize: Values.s_text_15,
          fontWeight: Values.font_Weight_medium,
          color: Values.of(context).c_black_front_33),
    );

    Widget _price = Text(
      '￥' + data.price.toString(),
      overflow: TextOverflow.ellipsis,
      style: TextStyle(
          fontSize: Values.s_text_15,
          fontWeight: Values.font_Weight_medium,
          color: Values.of(context).c_black_front_33),
    );

    Widget _number = Text(
      'X' + data.count.toString(),
      overflow: TextOverflow.ellipsis,
      style: TextStyle(
          fontSize: Values.s_text_12,
          color: Values.of(context).c_black_front_99),
    );

    Widget _warn = Text(
      data.ifCostHigh ? S.of(context).dispatch_list_cost_high : '',
      overflow: TextOverflow.ellipsis,
      style: TextStyle(
          fontSize: Values.s_text_12, color: Values.of(context).c_red_front_68),
    );

    Widget _total = Text.rich(TextSpan(
        style: TextStyle(
          fontSize: Values.s_text_14,
          color: Values.of(context).c_black_front_66,
          fontWeight: Values.font_Weight_normal,
        ),
        children: [
          TextSpan(text: "${S.of(context).dispatch_list_total} "),
          TextSpan(
            text: '￥' + data.paymentFee.toStringAsFixed(2),
            style: TextStyle(
                color: Values.of(context).c_red_front_68,
                fontWeight: Values.font_Weight_medium),
          ),
        ]));

    Widget _rejectButton = Container(
        height: Values.d_30,
        child: OutlineButton(
          onPressed: () {
            rejectAction(data);
          },
          color: Values.of(context).c_white_background,
          splashColor: Values.c_translucent,
          highlightColor: Values.c_translucent,
          child: Text(
            S.of(context).dispatch_list_reject,
            style: TextStyle(
                fontSize: Values.s_text_14,
                color: Values.of(context).c_black_front_99,
                fontWeight: Values.font_Weight_normal),
          ),
          borderSide:
              new BorderSide(color: Values.of(context).c_black_front_99),
          highlightedBorderColor: Values.of(context).c_black_front_99,
          shape: RoundedRectangleBorder(
            borderRadius: new BorderRadius.circular(Values.d_30),
          ),
        ));

    Widget _sendButton = Container(
      height: Values.d_30,
      child: FlatButton(
          onPressed: () {
            orderAction(data);
          },
          color: Values.of(context).c_orange_background_0b,
          disabledColor: Values.of(context).c_grey_background_cc,
          highlightColor: Values.c_translucent,
          splashColor: Values.c_translucent,
          shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(Values.d_30)),
          child: Text(
            S.of(context).dispatch_list_sure,
            style: TextStyle(
                fontSize: Values.s_text_14,
                color: Values.of(context).c_white_front),
          )),
    );

    return Container(
      padding: EdgeInsets.all(Values.d_15),
      margin: EdgeInsets.only(
        left: Values.d_12,
        top: Values.d_12,
        right: Values.d_12,
      ),
      decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(Values.d_5),
          color: Values.of(context).c_white_background),
      child: Column(
        children: <Widget>[
          _senderInfo,
          SizedBox(
            height: Values.d_15,
          ),
          Container(
            decoration: new BoxDecoration(
                border: new Border(
                    bottom: BorderSide(
                        color: Values.of(context).c_grey_ea, width: 1.0))),
            child: Row(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                Container(
                    margin: EdgeInsets.only(
                        right: Values.d_10, bottom: Values.d_15),
                    child: _image),
                Expanded(
                  child: Container(
                    child: Column(
//                      crossAxisAlignment: CrossAxisAlignment.start,
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: <Widget>[
                        _title,
                        SizedBox(height: Values.d_26),
                        Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          crossAxisAlignment: CrossAxisAlignment.end,
                          children: <Widget>[
                            _price,
                            _number,
                          ],
                        ),
                      ],
                    ),
                  ),
                ),
              ],
            ),
          ),
          SizedBox(height: Values.d_10),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: <Widget>[
              _warn,
              _total,
            ],
          ),
          SizedBox(height: Values.d_20),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: <Widget>[
              data.ifReject
                  ? Row(
                      children: <Widget>[
                        Text(
                          '倒计时:',
                          style: TextStyle(
                              color: Values.of(context).c_red_front_68,
                              fontSize: Values.s_text_14),
                        ),
                        Container(
                          margin: EdgeInsets.only(left: Values.d_5),
                          child: TimeCountDownSinceDate(
                            bgColor: Values.of(context).c_white_background,
                            finishTitleColor: Values.of(context).c_red_front_68,
                            fontSize: Values.s_text_14,
                            finishTitle: '00:00',
                            countDownTitleColor:
                                Values.of(context).c_red_front_68,
                            finishDate: data.advanceTime,
                            finishCallBack: () {
                              setState(() {});
                            },
                          ),
                        ),
                      ],
                    )
                  : Container(),
              Row(
                mainAxisAlignment: MainAxisAlignment.end,
                children: <Widget>[
                  data.ifReject ? _rejectButton : Container(),
                  SizedBox(width: Values.d_10),
                  _sendButton
                ],
              ),
            ],
          )
        ],
      ),
    );
  }

  Widget AccountInfoWidget() {
    return Container(
      color: Values.of(context).c_grey_background_f8,
      child: Stack(
        children: <Widget>[
          Container(
            padding: EdgeInsets.only(left: 0, right: 0, top: 0),
            height: Values.d_36,
            decoration: BoxDecoration(
              gradient: LinearGradient(
                colors: [
                  Values.of(context).c_blue_background_light,
                  Values.of(context).c_blue_background_dark
                ],
              ),
            ),
          ),
          Container(
            height: Values.d_50,
            padding: EdgeInsets.only(
              left: Values.d_44,
              right: Values.d_44,
            ),
            margin: EdgeInsets.only(
              top: Values.d_18,
              bottom: Values.d_5,
              left: Values.d_12,
              right: Values.d_12,
            ),
            decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(Values.d_5),
                color: Values.of(context).c_white_background,
                boxShadow: [
                  BoxShadow(
                      color: Values.of(context).c_grey_background_cc,
                      offset: Offset(0.0, 3.0),
                      blurRadius: Values.d_5,
                      spreadRadius: 1)
                ]),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: <Widget>[
                Text.rich(TextSpan(
                    style: TextStyle(
                      fontSize: Values.s_text_15,
                      color: Values.of(context).c_black_front_33,
                      fontWeight: Values.font_Weight_normal,
                    ),
                    children: [
                      TextSpan(
                          text: S.of(context).dispatch_list_usable_account),
                      TextSpan(
                        text: _dispatchListBean.count.toString(),
                        style: TextStyle(
                            color: Values.of(context).c_red_front_68,
                            fontWeight: Values.font_Weight_medium),
                      ),
                    ])),
                Text.rich(TextSpan(
                    style: TextStyle(
                      fontSize: Values.s_text_15,
                      color: Values.of(context).c_black_front_33,
                      fontWeight: Values.font_Weight_normal,
                    ),
                    children: [
                      TextSpan(
                          text: S.of(context).dispatch_list_freeze_account),
                      TextSpan(
                        text: _dispatchListBean.frozenCount.toString(),
                        style: TextStyle(
                            color: Values.of(context).c_red_front_68,
                            fontWeight: Values.font_Weight_medium),
                      ),
                    ])),
              ],
            ),
          )
        ],
      ),
    );
  }

  void _itemAction(Widget widget, Object itemData) {
    orderAction(itemData as DispatchListItem);
  }

  void orderAction(DispatchListItem data) {
    open(RouterName.dispatch_detail, argument: {'orderId': data.numberCode}).then((value){
      if(value){
        _dispatchListViewModel.loadDispatchList(isFirst: false,isRefresh: true);
      }
    });
  }

  void rejectAction(DispatchListItem data) {
    showRejectDialog(data.numberCode);
  }

  void postRejectAction(String numberCode) {
    _dispatchListViewModel.loadRejectOrder(numberCode, (statusInfo) {
      if (statusInfo.error_code == '1') {
        setState(() {});
      } else {}
    });
  }

  void AccountExplain() {
    open(RouterName.web_view,
        argument: WebParams(
            url: NetworkConfig().getHostForWeb() + NET_WEB_ACCOUNT_GUIDE,
            title: S.of(context).dispatch_list_account_explain));
  }

  void showRejectDialog(String numberCode) {
    showDialog(
        context: context,
        child: DialogTool(
          bgColor: Values.of(context).c_white_background,
          title: S.of(context).warn_title,
          titleFontSize: Values.s_text_18,
          titleColor: Values.of(context).c_black_front_33,
          content: S.of(context).dispatch_list_reject_content,
          contentColor: Values.of(context).c_black_front_33,
          leftTitle: S.of(context).dispatch_list_reject_sure,
          leftColor: Values.of(context).c_orange_front_0b,
          leftBgColor: Values.of(context).c_white_background,
          rightTitle: S.of(context).dispatch_list_reject_cancel,
          rightColor: Values.of(context).c_white_front,
          rightBgColor: Values.of(context).c_orange_background_0b,
          onTap: (int index) {
            if (index == 0) {
              postRejectAction(numberCode);
            }
          },
        ));
  }

  void loadMore(){
    _dispatchListViewModel.loadDispatchList(isFirst: false,isRefresh: false);
  }

  @override
  Widget build(BuildContext context) {
    return RootPageWidget(
      appBar: IsAppBar(
        title: S.of(context).dispatch_list_title,
        titleColor: Values.of(context).c_white_front,
        color: [
          Values.of(context).c_blue_background_light,
          Values.of(context).c_blue_background_dark
        ],
        rightWidget: FlatButton(
            onPressed: AccountExplain,
            highlightColor: Values.c_translucent,
            splashColor: Values.c_translucent,
            child: Text(
              S.of(context).dispatch_list_account_explain,
              style: TextStyle(
                  fontSize: Values.s_text_15,
                  color: Values.of(context).c_white_front,
                  fontWeight: Values.font_Weight_normal),
            )),
      ),
      viewModel: _dispatchListViewModel,
      task: _dispatchListViewModel.loadDispatchList(isFirst: true,isRefresh: true),
      body: Scaffold(
        body: StreamBuilder<DispatchListBean>(
            stream: _dispatchListViewModel.streamDispatchList,
            builder: (BuildContext context,
                AsyncSnapshot<DispatchListBean> snapshot) {
              if (snapshot.data == null || snapshot.data?.items == null) {
                return CommonWidget.emptyWidget();
              }
              _dispatchListBean = snapshot.data;
              if(_dispatchListViewModel.pageIndex == 1){
                _itemList.clear();
              }
              _itemList.addAll(snapshot.data.items);
              _loadMoreController.hasMore = _dispatchListViewModel.hasMoreList;
              return Column(
                children: <Widget>[
                  AccountInfoWidget(),
                  Expanded(
                      child: PullToRefresh(
                    child: SListView(_buildItemView, itemAction: _itemAction,moreController: _loadMoreController)
                        .build(context, _itemList),
                    onRefresh: () {
                      return _dispatchListViewModel.loadDispatchList(
                          isFirst: false,isRefresh: true);
                    },
                  )),
                ],
              );
            }),
      ),
    );
  }

  @override
  void dispose() {
    _dispatchListViewModel.dispose();
    super.dispose();
  }
}
