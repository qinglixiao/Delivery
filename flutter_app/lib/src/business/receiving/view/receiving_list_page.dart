import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/src/business/receiving/viewmodel/receiving_list_view_model.dart';
import 'package:flutter_ienglish_fine/src/business/receiving/bean/receiving_list.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:url_launcher/url_launcher.dart';

class ReceivingListPage extends StatefulWidget with UIWrap {
  @override
  State<StatefulWidget> createState() {
    return _ReceivingListState();
  }
}

class _ReceivingListState extends State<ReceivingListPage> with PageBridge {
  ReceivingListViewModel _receivingListViewModel;

  @override
  void initState() {
    super.initState();
    _receivingListViewModel = ReceivingListViewModel();
  }

  void _makePhoneCall(String url) async {
    if (await canLaunch(url)) {
      await launch(url);
    } else {
      throw 'Could not launch $url';
    }
  }

  SimpleLoadMoreController _loadMoreController ;

  List<ReceivingListItemBean> _itemList = List();

  Widget _buildItemView(BuildContext context, Object itemData) {
    ReceivingListItemBean data = itemData as ReceivingListItemBean;

    Widget _senderInfo = Container(
      decoration: new BoxDecoration(
          border: new Border(
              bottom:
                  BorderSide(color: Values.of(context).c_grey_ea, width: 1.0))),
      padding: EdgeInsets.only(
          left: Values.d_10, top: Values.d_5, bottom: Values.d_10),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.start,
        children: <Widget>[
          Text(S.of(context).receiving_list_sender + data.sellerUserName,
              style: TextStyle(
                  fontSize: Values.s_text_14,
                  color: Values.of(context).c_black_front_33)),
          SizedBox(
            width: Values.d_5,
          ),
          (!StringUtil.isEmpty(data.sellerUserName) && data.sellerUserName!='公司' && data.sellerUserName!='悦多丰')?
          GestureDetector(
            child: Image.asset('assets/images/tel.png'),
            onTap: () {
              _makePhoneCall('tel:'+data.sellerPhone);
            },
          ):Container()
        ],
      ),
    );

    Widget _image = CachedNetworkImage(
      width: 75,
      height: 75,
      imageUrl: data?.imgUrl ?? "",
    );

    Widget _title = Text(
      StringUtil.getNotNullString(data?.title),
      overflow: TextOverflow.ellipsis,
      maxLines: 2,
      style: TextStyle(
          fontSize: Values.s_text_15,
          color: Values.of(context).c_black_front_33),
    );

    Widget _price = Text(
      '￥' + data.price.toString() + S.of(context).dispatch_detail_price_unit,
      overflow: TextOverflow.ellipsis,
      style: TextStyle(
          fontSize: Values.s_text_14,
          color: Values.of(context).c_black_front_66),
    );

    Widget _number = Text(
      'X' + data.count.toString(),
      overflow: TextOverflow.ellipsis,
      style: TextStyle(
          fontSize: Values.s_text_14,
          color: Values.of(context).c_black_front_66),
    );

    Widget _total = Text.rich(TextSpan(children: [
      TextSpan(text: "${S.of(context).receiving_list_total} "),
      TextSpan(
        text: '￥' + data.paymentFee.toStringAsFixed(2),
        style: TextStyle(color: Values.of(context).c_red_front_68),
      ),
    ]));

    Widget _order = FlatButton(
        onPressed: () {
          orderAction(data);
        },
        color: Values.of(context).c_orange_background_0b,
        disabledColor: Values.of(context).c_grey_background_cc,
        highlightColor: Values.c_translucent,
        splashColor: Values.c_translucent,
        shape:
            RoundedRectangleBorder(borderRadius: BorderRadius.circular(20.0)),
        child: Text(
          S.of(context).receiving_list_submit_button,
          style: TextStyle(
              fontSize: Values.s_text_14,
              color: Values.of(context).c_white_front),
        ));

    return Container(
      padding: EdgeInsets.all(Values.d_12),
      margin: EdgeInsets.only(
        left: Values.d_10,
        top: Values.d_10,
        right: Values.d_10,
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
          Row(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Container(
                  margin: EdgeInsets.only(right: Values.d_10), child: _image),
              Expanded(
                child: Container(
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: <Widget>[
                      _title,
                      SizedBox(height: Values.d_15),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: <Widget>[
                          _price,
                          _number,
                        ],
                      ),
                      SizedBox(height: Values.d_15),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.end,
                        children: <Widget>[
                          _total,
                        ],
                      ),
                      SizedBox(height: Values.d_10),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.end,
                        children: <Widget>[
                          _order,
                        ],
                      ),
                    ],
                  ),
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }

  void _itemAction(Widget widget, Object itemData) {
    ReceivingListItemBean data = itemData as ReceivingListItemBean;
    open(RouterName.receiving_detail,
        argument: {'orderId': data.numberCode});
  }

  void orderAction(ReceivingListItemBean data) {
    open(RouterName.receiving_detail, argument: {'orderId': data.numberCode});
  }

  void loadMore(){
    _receivingListViewModel.loadReceivingList(isFirst: false,isRefresh: false);
  }

  @override
  Widget build(BuildContext context) {

    _loadMoreController = SimpleLoadMoreController((){
      loadMore();
    });

    return RootPageWidget(
      appBar: IsAppBar(
        title: S.of(context).receiving_list_title,
        titleColor: Values.of(context).c_white_front,
        color: [
          Values.of(context).c_blue_background_light,
          Values.of(context).c_blue_background_dark
        ],
      ),
      viewModel: _receivingListViewModel,
      task: _receivingListViewModel.loadReceivingList(isFirst: true,isRefresh: true),
      body: Scaffold(
        body: StreamBuilder<ReceivingListBean>(
            stream: _receivingListViewModel.streamReceivingList,
            builder: (BuildContext context,
                AsyncSnapshot<ReceivingListBean> snapshot) {
              if (snapshot.data == null) {
                return CommonWidget.emptyWidget();
              }
              if(_receivingListViewModel.pageIndex == 1){
                _itemList.clear();
              }
              _itemList.addAll(snapshot.data.items);
              _loadMoreController.hasMore = _receivingListViewModel.hasMoreList;

              if (_itemList == null) {
                return CommonWidget.emptyWidget();
              }
              return PullToRefresh(
                child: SListView(_buildItemView, itemAction: _itemAction,moreController: _loadMoreController)
                    .build(context, _itemList),
                onRefresh: () {
                  return _receivingListViewModel.loadReceivingList(
                      isFirst: false,isRefresh: true);
                },
              );
            }),
      ),
    );
  }

  @override
  void dispose() {
    _receivingListViewModel.dispose();
    super.dispose();
  }
}
