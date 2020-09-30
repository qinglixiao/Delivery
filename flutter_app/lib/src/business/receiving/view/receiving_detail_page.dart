import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/src/business/dispatch/bean/logistics_list.dart';
import 'package:flutter_ienglish_fine/src/comm/event/user_info_event.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter_ienglish_fine/src/business/dispatch/bean/dispatch_detail.dart';
import 'package:flutter_ienglish_fine/src/business/receiving/viewmodel/receiving_detail_view_model.dart';
import 'package:url_launcher/url_launcher.dart';

class ReceivingDeatilPage extends StatefulWidget {
  @override
  _ReceivingDeatilPageState createState() => _ReceivingDeatilPageState();
}

class _ReceivingDeatilPageState extends State<ReceivingDeatilPage> with PageBridge {
  ReceivingDetailViewModel _receivingDetailViewModel = ReceivingDetailViewModel();

  String _orderId = '';
  DispatchOrderDetail _orderDetail;
  ExpressInfo _expressInfo;

  _init() async {
    getInitArg(context).then((params) {
      _orderId = params['orderId'];
      _receivingDetailViewModel.getReceivingDetail(_orderId);
    });
  }

  void loadOrderReceivingAffirm(String numberCode) {
    emitUserInfoEvent();
    ProgressHUD.showLoading();
    _receivingDetailViewModel.loadOrderReceivingAffirm(numberCode, (statusInfo) {
      if (statusInfo.error_code == '1') {
        ProgressHUD.hiddenHUD();
        setState(() {});
      } else {
        ProgressHUD.showError(warnText: statusInfo.message);
      }
    });
  }

  ///收货成功后,发送UserInfoEvent,更新个人信息
  emitUserInfoEvent() {
    eventCenter.emit(UserInfoEvent(type: USER_INFO_EVENT_TYPE_2));
  }

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    _init();
    return RootPageWidget(
        appBar: IsAppBar(
          title: S.of(context).dispatch_detail_title,
          leftOnTap: () {
            pop();
          },
        ),
        viewModel: _receivingDetailViewModel,
        body: ScrollWidget());
  }

  Widget ScrollWidget() {
    return StreamBuilder(
        stream: _receivingDetailViewModel.streamOrderDetail,
        builder: (BuildContext context, AsyncSnapshot<DispatchOrderDetail> snapshot) {
          if (snapshot.data != null) {
            _orderDetail = snapshot.data;
          }
          return StreamBuilder(
              stream: context
                  .findRootAncestorStateOfType<_ReceivingDeatilPageState>()
                  ._receivingDetailViewModel
                  .streamExpressInfoData,
              builder: (BuildContext context, AsyncSnapshot<ExpressInfoData> snapshot) {
                if (snapshot.data != null) {
                  _expressInfo = snapshot.data.data;
                }
                return CustomScrollView(
                  slivers: <Widget>[
                    SliverToBoxAdapter(child: OrderInfoWidget()),
                    ExpressInfoWidget(),
                  ],
                );
              });
        });
  }

  Widget OrderInfoWidget() {
    if (_orderDetail == null || _expressInfo == null) {
      return Container();
    }
    return Container(
      color: Values.of(context).c_grey_background_f8,
      child: Column(
        children: <Widget>[
          OrderHeaderWidget(),
          OrderShippingCountWidget(),
          PlaceWidget(),
          GoodInfoWidget(),
        ],
      ),
    );
  }

  Widget OrderHeaderWidget() {
    final size = MediaQuery.of(context).size;
    final width = size.width;
    return Container(
      child: Stack(
        children: <Widget>[
          Container(
            width: width,
            child: Image.asset('assets/images/dispatch_bg.png', fit: BoxFit.fill),
          ),
          AspectRatio(
            aspectRatio: 375 / 110,
            child: Row(
              crossAxisAlignment: CrossAxisAlignment.center,
              children: <Widget>[
                Container(
                  padding: EdgeInsets.only(left: Values.d_44),
                  child: Text(_orderDetail.status,
                      style: TextStyle(
                          color: Values.of(context).c_white_front,
                          fontWeight: Values.font_Weight_medium,
                          fontSize: Values.s_text_20)),
                )
              ],
            ),
          ),
        ],
      ),
    );
  }

  Widget OrderShippingCountWidget() {
    return Container(
      color: Values.of(context).c_white_background,
      height: Values.d_50,
      child: Row(
        children: <Widget>[
          Expanded(
              flex: 1,
              child: Text(
                  S.of(context).receiving_detail_shipping_status1 + '（${_expressInfo.totalCount - _expressInfo.affirmCount.toInt()}）',
                  style: TextStyle(
                      color: Values.of(context).c_black_front_66,
                      fontWeight: Values.font_Weight_normal,
                      fontSize: Values.s_text_14),
                  textAlign: TextAlign.center)),
          Expanded(
              flex: 1,
              child: Text(S.of(context).receiving_detail_shipping_status2 + '（${_expressInfo.affirmCount.toInt()}）',
                  style: TextStyle(
                      color: Values.of(context).c_black_front_66,
                      fontWeight: Values.font_Weight_normal,
                      fontSize: Values.s_text_14),
                  textAlign: TextAlign.center)),
          Expanded(
              flex: 1,
              child: Text(S.of(context).dispatch_detail_shipping_status3 + '（${_expressInfo.totalCount}）',
                  style: TextStyle(
                      color: Values.of(context).c_black_front_66,
                      fontWeight: Values.font_Weight_normal,
                      fontSize: Values.s_text_14),
                  textAlign: TextAlign.center)),
        ],
      ),
    );
  }

  Widget PlaceWidget() {
    return Container(
      margin: EdgeInsets.only(
        top: Values.d_12,
      ),
      color: Values.of(context).c_white_background,
      child: Column(
        children: <Widget>[
          Container(
            padding: EdgeInsets.only(left: Values.d_15, top: Values.d_15, bottom: Values.d_15),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: <Widget>[
                Expanded(
                    child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: <Widget>[
                    Row(
                      children: <Widget>[
                        Container(
                          child: Text(_orderDetail.receiveName,
                              style: TextStyle(
                                  color: Values.of(context).c_black_front_33,
                                  fontWeight: Values.font_Weight_medium,
                                  fontSize: Values.s_text_18)),
                        ),
                        Container(
                          margin: EdgeInsets.only(left: Values.d_15, right: Values.d_50),
                          child: Text(_orderDetail.receiveMobile,
                              style: TextStyle(
                                  color: Values.of(context).c_black_front_33,
                                  fontWeight: Values.font_Weight_medium,
                                  fontSize: Values.s_text_18),
                              maxLines: 1,
                              overflow: TextOverflow.ellipsis),
                        )
                      ],
                    ),
                    Container(
                      margin: EdgeInsets.only(top: Values.d_5, right: Values.d_15),
                      child: Text(
                        _orderDetail.receiveAddress,
                        style: TextStyle(
                            color: Values.of(context).c_black_front_33,
                            fontWeight: Values.font_Weight_normal,
                            fontSize: Values.s_text_13),
                        maxLines: 2,
                        overflow: TextOverflow.ellipsis,
                      ),
                    ),
                  ],
                )),
              ],
            ),
          ),
          Row(
            children: <Widget>[
              Expanded(
                  child: Image.asset(
                'assets/images/place_line.png',
                fit: BoxFit.fill,
              ))
            ],
          ),
        ],
      ),
    );
  }

  Widget GoodInfoWidget() {
    return Container(
      color: Values.of(context).c_white_background,
      padding: EdgeInsets.all(Values.d_15),
      child: Column(
        children: <Widget>[
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Container(
                margin: EdgeInsets.only(right: Values.d_8),
                width: 75,
                height: 75,
                child: CachedNetworkImage(
                  imageUrl: _orderDetail.imgUrl,
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
              Expanded(
                child: Container(
                    height: 75,
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: <Widget>[
                        Container(
                          child: Text(
                            _orderDetail.title,
                            style: TextStyle(
                                color: Values.of(context).c_black_front_33,
                                fontWeight: Values.font_Weight_medium,
                                fontSize: Values.s_text_15),
                            maxLines: 2,
                            overflow: TextOverflow.ellipsis,
                          ),
                        ),
                        Container(
                            child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: <Widget>[
                            Text(
                              '¥' + _orderDetail.price.toString() + S.of(context).dispatch_detail_price_unit,
                              style: TextStyle(
                                  color: Values.of(context).c_black_front_66,
                                  fontWeight: Values.font_Weight_normal,
                                  fontSize: Values.s_text_14),
                            ),
                            Text(
                              'X' + _orderDetail.count.toString(),
                              style: TextStyle(
                                  color: Values.of(context).c_black_front_66,
                                  fontWeight: Values.font_Weight_normal,
                                  fontSize: Values.s_text_14),
                            ),
                          ],
                        )),
                      ],
                    )),
              ),
            ],
          ),
          SizedBox(
            height: Values.d_15,
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.end,
            children: <Widget>[
              Text.rich(TextSpan(children: [
                TextSpan(text: "${S.of(context).receiving_list_total} "),
                TextSpan(
                  text: '￥' + _orderDetail.paymentFee.toStringAsFixed(2),
                  style: TextStyle(color: Values.of(context).c_red_front_68),
                ),
              ]))
            ],
          )
        ],
      ),
    );
  }
}

/// 物流信息
/// 可点击确认收货
class ExpressInfoWidget extends StatefulWidget {
//  final Key key;

//  const ExpressInfoWidget(this.key);

  @override
  _ExpressInfoWidgetState createState() => _ExpressInfoWidgetState();
}

class _ExpressInfoWidgetState extends State<ExpressInfoWidget> {
  ExpressInfo _expressInfo;
  List _extentList = List();
  bool _showExpressList = false;
  int _showIndex = -1;
  List<LogisticsItem> _extentDetailList = List();

  void updateOrderInfo() {
    Future.delayed(Duration(milliseconds: 300)).then((e) {
      setState(() {});
    });
  }

  void _makePhoneCall(String url) async {
    if (await canLaunch(url)) {
      await launch(url);
    } else {
      throw 'Could not launch $url';
    }
  }

  void showReceivingDialog(Function() callback) {
    showDialog(
        context: context,
        child: DialogTool(
          bgColor: Values.of(context).c_white_background,
          title: S.of(context).receiving_detail_warn_title,
          titleFontSize: Values.s_text_18,
          titleColor: Values.of(context).c_black_front_33,
          content: S.of(context).receiving_list_submit_button,
          contentColor: Values.of(context).c_black_front_33,
          leftTitle: S.of(context).warn_cancel,
          leftColor: Values.of(context).c_orange_front_0b,
          leftBgColor: Values.of(context).c_white_background,
          rightTitle: S.of(context).receiving_detail_warn_sure,
          rightColor: Values.of(context).c_white_front,
          rightBgColor: Values.of(context).c_orange_background_0b,
          onTap: (int index) {
            if (index == 1) {
              callback();
            }
          },
        ));
  }

  void getExpressDetailData(String logisticsName, String logisticsNo, int index) {
    ProgressHUD.showLoading();
    context
        .findRootAncestorStateOfType<_ReceivingDeatilPageState>()
        ._receivingDetailViewModel
        .loadOrderLogistics(logisticsNo)
        .then((value) {
      ProgressHUD.hiddenHUD();
      LogisticsList logistics = value;
      if (logistics.data != null) {
        _showExpressList = true;
        _showIndex = index;
        _extentDetailList = logistics.data.list;
        setState(() {});
      }
    });
  }

  void colseExpressList() {
    _showExpressList = false;
    _showIndex = -1;
    _extentDetailList.clear();
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    _expressInfo = context.findRootAncestorStateOfType<_ReceivingDeatilPageState>()._expressInfo;
    if(_expressInfo!=null){
      _extentList = _expressInfo?.logistics;
    }
    return SliverList(
      delegate: SliverChildBuilderDelegate(
        ExtentItem,
        childCount: _extentList.length +
            (context.findRootAncestorStateOfType<_ReceivingDeatilPageState>()._orderDetail != null ? 2 : 0),
      ),
    );
  }

  Widget ExtentItem(BuildContext context, int index) {
    if (index == 0) {
      return Container(
          height: Values.d_44,
          alignment: Alignment.center,
          child: Text(S.of(context).dispatch_detail_express_info,
              style: TextStyle(
                  color: Values.of(context).c_black_front_99,
                  fontWeight: Values.font_Weight_normal,
                  fontSize: Values.s_text_14)));
    } else if (index == _extentList.length + 1) {
      String _orderNum = context.findRootAncestorStateOfType<_ReceivingDeatilPageState>()._orderDetail.numberCode;
      String _orderPerson = context.findRootAncestorStateOfType<_ReceivingDeatilPageState>()._orderDetail.username;
      String _ordeTime = context.findRootAncestorStateOfType<_ReceivingDeatilPageState>()._orderDetail.createTime;
      String _userName = context.findRootAncestorStateOfType<_ReceivingDeatilPageState>()._orderDetail.sellname;
      String _phone = context.findRootAncestorStateOfType<_ReceivingDeatilPageState>()._orderDetail.sellerPhone;

      return Container(
        color: Values.of(context).c_white_background,
        margin: EdgeInsets.only(bottom: Values.d_12),
        padding: EdgeInsets.all(Values.d_12),
        child: Column(
          children: <Widget>[
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: <Widget>[
                Text(
                  S.of(context).dispatch_detail_order_num + _orderNum,
                  style: TextStyle(
                      color: Values.of(context).c_black_front_99,
                      fontWeight: Values.font_Weight_normal,
                      fontSize: Values.s_text_12),
                ),
              ],
            ),
            SizedBox(height: Values.d_10),
            Row(
              children: <Widget>[
                Text(
                  S.of(context).dispatch_detail_order_person + _orderPerson,
                  style: TextStyle(
                      color: Values.of(context).c_black_front_99,
                      fontWeight: Values.font_Weight_normal,
                      fontSize: Values.s_text_12),
                ),
              ],
            ),
            SizedBox(height: Values.d_10),
            Row(
              children: <Widget>[
                Text(
                  S.of(context).dispatch_detail_order_time + _ordeTime,
                  style: TextStyle(
                      color: Values.of(context).c_black_front_99,
                      fontWeight: Values.font_Weight_normal,
                      fontSize: Values.s_text_12),
                ),
              ],
            ),
            SizedBox(height: Values.d_10),
            Row(
              mainAxisAlignment: MainAxisAlignment.start,
              children: <Widget>[
                Text(S.of(context).receiving_list_sender + _userName,
                    style: TextStyle(fontSize: Values.s_text_12, color: Values.of(context).c_black_front_99)),
                SizedBox(
                  width: Values.d_5,
                ),
                (_userName != '公司' && _userName != '悦多丰' && !StringUtil.isEmpty(_userName))
                    ? GestureDetector(
                        child: Image.asset('assets/images/tel.png'),
                        onTap: () {
                          _makePhoneCall('tel:' + _phone);
                        },
                      )
                    : Container()
              ],
            ),
            SizedBox(height: Values.d_10),
          ],
        ),
      );
    } else {
      ExpressItemInfo _itemInfo = _extentList[index - 1];
      return Container(
        color: Values.of(context).c_white_background,
        margin: EdgeInsets.only(bottom: Values.d_12),
        padding: EdgeInsets.only(left: Values.d_12, right: Values.d_12, top: Values.d_26),
        child: Column(
          children: <Widget>[
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: <Widget>[
                Row(
                  children: <Widget>[
                    Text(
                      S.of(context).dispatch_detail_express_name,
                      style: TextStyle(
                          color: Values.of(context).c_black_front_33,
                          fontWeight: Values.font_Weight_normal,
                          fontSize: Values.s_text_15),
                    ),
                    Text(
                      _itemInfo.receiveName,
                      style: TextStyle(
                          color: Values.of(context).c_black_front_33,
                          fontWeight: Values.font_Weight_medium,
                          fontSize: Values.s_text_15),
                    ),
                  ],
                ),
                Text(
                  _itemInfo.ifSelfTaking ? S.of(context).dispatch_detail_express_selftaking : _itemInfo.status,
                  style: TextStyle(
                      color: Values.of(context).c_red_front_68,
                      fontWeight: Values.font_Weight_normal,
                      fontSize: Values.s_text_15),
                ),
              ],
            ),
            SizedBox(height: Values.d_10),
            Row(
              children: <Widget>[
                Text(
                  S.of(context).dispatch_detail_express_tel,
                  style: TextStyle(
                      color: Values.of(context).c_black_front_33,
                      fontWeight: Values.font_Weight_normal,
                      fontSize: Values.s_text_15),
                ),
                Text(
                  _itemInfo.receiveMobile.toString(),
                  style: TextStyle(
                      color: Values.of(context).c_black_front_33,
                      fontWeight: Values.font_Weight_medium,
                      fontSize: Values.s_text_15),
                ),
              ],
            ),
            SizedBox(height: Values.d_10),
            Row(
              children: <Widget>[
                Text(
                  S.of(context).dispatch_detail_express_time,
                  style: TextStyle(
                      color: Values.of(context).c_black_front_33,
                      fontWeight: Values.font_Weight_normal,
                      fontSize: Values.s_text_15),
                ),
                Text(
                  _itemInfo.outTime.toString(),
                  style: TextStyle(
                      color: Values.of(context).c_black_front_33,
                      fontWeight: Values.font_Weight_medium,
                      fontSize: Values.s_text_15),
                ),
              ],
            ),
            SizedBox(height: Values.d_10),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              crossAxisAlignment: CrossAxisAlignment.end,
              children: <Widget>[
                Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: <Widget>[
                    Row(
                      children: <Widget>[
                        Text(
                          S.of(context).receiving_detail_shipping_type + '：',
                          style: TextStyle(
                              color: Values.of(context).c_black_front_33,
                              fontWeight: Values.font_Weight_normal,
                              fontSize: Values.s_text_15),
                        ),
                        Text(
                          _itemInfo.ifSelfTaking
                              ? S.of(context).dispatch_detail_express_selftaking
                              : _itemInfo.logisticsName.toString(),
                          style: TextStyle(
                              color: Values.of(context).c_black_front_33,
                              fontWeight: Values.font_Weight_medium,
                              fontSize: Values.s_text_15),
                        ),
                      ],
                    ),
                    SizedBox(height: Values.d_10),
                    Row(
                      children: <Widget>[
                        Text(
                          S.of(context).dispatch_detail_express_count + '：',
                          style: TextStyle(
                              color: Values.of(context).c_black_front_33,
                              fontWeight: Values.font_Weight_normal,
                              fontSize: Values.s_text_15),
                        ),
                        Text(
                          _itemInfo.quantity.toString(),
                          style: TextStyle(
                              color: Values.of(context).c_black_front_33,
                              fontWeight: Values.font_Weight_medium,
                              fontSize: Values.s_text_15),
                        ),
                      ],
                    ),
                  ],
                ),
                _itemInfo.status != '已确认'
                    ? Container(
                        child: FlatButton(
                            onPressed: () {
                              showReceivingDialog(() {
                                context
                                    .findRootAncestorStateOfType<_ReceivingDeatilPageState>()
                                    .loadOrderReceivingAffirm(_itemInfo.numberCode);
                              });
                            },
                            color: Values.of(context).c_orange_background_0b,
                            disabledColor: Values.of(context).c_grey_background_cc,
                            highlightColor: Values.c_translucent,
                            splashColor: Values.c_translucent,
                            shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20.0)),
                            child: Text(
                              S.of(context).receiving_list_submit_button,
                              style: TextStyle(fontSize: Values.s_text_14, color: Values.of(context).c_white_front),
                            )),
                      )
                    : Container()
              ],
            ),
            SizedBox(height: Values.d_10),
            _itemInfo.ifSelfTaking
                ? Container()
                : (_showExpressList && _showIndex == index)
                    ? ExtentDetailList()
                    : Container(
                        height: Values.d_50,
                        child: FlatButton(
                            onPressed: () {
                              getExpressDetailData(_itemInfo.logisticsName, _itemInfo.logisticsNo, index);
                            },
                            highlightColor: Values.c_translucent,
                            splashColor: Values.c_translucent,
                            child: Row(
                              mainAxisAlignment: MainAxisAlignment.center,
                              children: <Widget>[
                                Text(S.of(context).dispatch_detail_express_detail,
                                    style: TextStyle(
                                        color: Values.of(context).c_black_front_99,
                                        fontWeight: Values.font_Weight_normal,
                                        fontSize: Values.s_text_13)),
                                SizedBox(width: Values.d_5),
                                Image.asset('assets/images/down_icon.png')
                              ],
                            )),
                      )
          ],
        ),
      );
    }
  }

  Widget ExtentDetailList() {
    return ListView.builder(
      shrinkWrap: true,
      physics: NeverScrollableScrollPhysics(),
      itemBuilder: (context, index) {
        return ExpressDetailListItem(context, index);
      },
      itemCount: _extentDetailList.length + 2,
    );
  }

  Widget ExpressDetailListItem(BuildContext context, int index) {
    if (index == 0) {
      ExpressItemInfo _itemInfo = _extentList[_showIndex - 1];
      return Container(
          color: Values.of(context).c_white_background,
          padding: EdgeInsets.only(top: Values.d_12, bottom: Values.d_12),
          child: Text(_itemInfo.logisticsName + '：' + _itemInfo.logisticsNo,
              style: TextStyle(
                  color: Values.of(context).c_black_front_33,
                  fontWeight: Values.font_Weight_normal,
                  fontSize: Values.s_text_14)));
    } else if (index == _extentDetailList.length + 1) {
      return Container(
        color: Values.of(context).c_white_background,
        height: Values.d_44,
        child: FlatButton(
            onPressed: () {
              colseExpressList();
            },
            highlightColor: Values.c_translucent,
            splashColor: Values.c_translucent,
            child: Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Text(S.of(context).pack_up,
                    style: TextStyle(
                        color: Values.of(context).c_black_front_99,
                        fontWeight: Values.font_Weight_normal,
                        fontSize: Values.s_text_12)),
                SizedBox(width: Values.d_5),
                Image.asset('assets/images/pull_up.png')
              ],
            )),
      );
    } else {
      LogisticsItem item = _extentDetailList[index - 1];
      return Column(
        children: <Widget>[
          Container(
            margin: EdgeInsets.all(0),
            color: Values.of(context).c_white_background,
            height: Values.d_50,
            child: Row(
              mainAxisAlignment: MainAxisAlignment.start,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                Column(
                  children: <Widget>[
                    Image.asset(index == 1 ? 'assets/images/status_select.png' : 'assets/images/status_normal.png'),
                    Expanded(
                        child: Container(
                      width: 1,
                      color: Values.of(context).c_grey_ea,
                    ))
                  ],
                ),
                SizedBox(width: Values.d_12),
                Expanded(
                    child: Container(
                  child: Text(item.status,
                      style: TextStyle(
                          color: Values.of(context).c_black_front_99,
                          fontWeight: Values.font_Weight_normal,
                          fontSize: Values.s_text_12),
                      maxLines: 5,
                      overflow: TextOverflow.ellipsis),
                )),
              ],
            ),
          )
        ],
      );
    }
  }
}
