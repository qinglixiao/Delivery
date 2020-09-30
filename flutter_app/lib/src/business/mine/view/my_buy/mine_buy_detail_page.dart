import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/src/business/budget/bean/good_confirm.dart';
import 'package:flutter_ienglish_fine/src/business/mine/viewmodel/mine_buy_detail_view_model.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter_ienglish_fine/src/business/dispatch/bean/dispatch_detail.dart';
import 'package:flutter_ienglish_fine/src/business/receiving/viewmodel/receiving_detail_view_model.dart';
import 'package:flutter_ienglish_fine/src/business/mine/view/my_buy/dialog_buy_order.dart';
import 'package:url_launcher/url_launcher.dart';

class MineBuyDeatilPage extends StatefulWidget {
  @override
  _MineBuyDeatilPagePageState createState() => _MineBuyDeatilPagePageState();
}

class _MineBuyDeatilPagePageState extends State<MineBuyDeatilPage> with PageBridge {
  ReceivingDetailViewModel _receivingDetailViewModel =
      ReceivingDetailViewModel();
  MineBuyDetailViewModel _buyDetailViewModel = MineBuyDetailViewModel();

  String _orderId = '';
  DispatchOrderDetail _orderDetail;
  bool _needRefresh = false;

  _init() async {
    getInitArg(context).then((params) {
      _orderId = params['orderId'];
      _receivingDetailViewModel.getReceivingDetail(_orderId);
    });
  }

  void payAction() {
    BuyCollocationInfo buyCollocationInfo = BuyCollocationInfo();
    buyCollocationInfo.numberCode = _orderDetail.numberCode;
    buyCollocationInfo.numberDesc = _orderDetail.numberDesc;
    buyCollocationInfo.payDesc = _orderDetail.payDesc;
    buyCollocationInfo.payFee = _orderDetail.paymentFee;
    open(RouterName.pay_affirm_page, argument: buyCollocationInfo);
  }

  void cancelAction(String message) {
    _buyDetailViewModel.loadCancelOrder(_orderDetail.numberCode, message,
        (dataInfo) {
          _needRefresh = true;
      setState(() {});
    });
  }

  void showCancelDialog() {
    showModalBottomSheet(
        context: context,
        builder: (BuildContext context) {
          return DialogBuyOrder(onTap: (String message) {
            cancelAction(message);
          });
        });
  }

  void _makePhoneCall(String url) async {
    if (await canLaunch(url)) {
      await launch(url);
    } else {
      throw 'Could not launch $url';
    }
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
          title: S.of(context).order_goods_title,
          leftOnTap: () {
            pop(data: _needRefresh);
          },
        ),
        viewModel: _receivingDetailViewModel,
        body: ScrollWidget());
  }

  Widget ScrollWidget() {
    return StreamBuilder(
        stream: _receivingDetailViewModel.streamOrderDetail,
        builder: (BuildContext context,
            AsyncSnapshot<DispatchOrderDetail> snapshot) {
          if (snapshot.data != null) {
            _orderDetail = snapshot.data;
          }
          if (_orderDetail == null) {
            return Container();
          }
          return Column(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: <Widget>[
              Flexible(
                  child: CustomScrollView(
                slivers: <Widget>[
                  SliverToBoxAdapter(child: OrderInfoWidget()),
                ],
              )),
              BottomWidget()
            ],
          );
        });
  }

  Widget OrderInfoWidget() {
    return Container(
      color: Values.of(context).c_grey_background_f8,
      child: Column(
        children: <Widget>[
          OrderHeaderWidget(),
          OrderShippingCountWidget(),
          PlaceWidget(),
          GoodInfoWidget(),
          OrderDetailWidget(),
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
            child: _orderDetail.status == S.of(context).order_status_4
                ? Image.asset('assets/images/dispatch_pay_bg.png',
                    fit: BoxFit.fill)
                : Image.asset('assets/images/dispatch_cancel_bg.png',
                    fit: BoxFit.fill),
          ),
          AspectRatio(
            aspectRatio: 375 / 110,
            child: Container(
              padding: EdgeInsets.only(left: Values.d_44),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Text(StringUtil.getNotNullString(_orderDetail.status),
                      style: TextStyle(
                          color: Values.of(context).c_white_front,
                          fontWeight: Values.font_Weight_medium,
                          fontSize: Values.s_text_20)),
                  SizedBox(height: Values.d_5),
                  _orderDetail.status == S.of(context).order_status_4
                      ? Row(
                          children: <Widget>[
                            Text(S.of(context).countDown_title + '：',
                                style: TextStyle(
                                    color: Values.of(context).c_grey_front_cc,
                                    fontWeight: Values.font_Weight_normal,
                                    fontSize: Values.s_text_12)),
                            TimeCountDownSinceDate(
                              bgColor: Values.c_translucent,
                              finishTitleColor:
                                  Values.of(context).c_white_front,
                              fontSize: Values.s_text_14,
                              finishTitle: '00:00',
                              countDownTitleColor:
                                  Values.of(context).c_white_front,
                              finishDate: _orderDetail.advanceAbolishTime,
                              separateType: false,
                              separateTitleColor:
                                  Values.of(context).c_white_front,
                              finishCallBack: () {
                                setState(() {});
                              },
                            ),
                          ],
                        )
                      : Text(
                          StringUtil.getNotNullString(_orderDetail.abolishMsg),
                          style: TextStyle(
                              color: Values.of(context).c_white_front,
                              fontWeight: Values.font_Weight_normal,
                              fontSize: Values.s_text_14)),
                ],
              ),
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
                  S.of(context).receiving_detail_shipping_status1 +
                      '（${_orderDetail.count - _orderDetail.sendCount}）',
                  style: TextStyle(
                      color: Values.of(context).c_black_front_66,
                      fontWeight: Values.font_Weight_normal,
                      fontSize: Values.s_text_14),
                  textAlign: TextAlign.center)),
          Expanded(
              flex: 1,
              child: Text(
                  S.of(context).receiving_detail_shipping_status2 +
                      '（${_orderDetail.sendCount}）',
                  style: TextStyle(
                      color: Values.of(context).c_black_front_66,
                      fontWeight: Values.font_Weight_normal,
                      fontSize: Values.s_text_14),
                  textAlign: TextAlign.center)),
          Expanded(
              flex: 1,
              child: Text(
                  S.of(context).dispatch_detail_shipping_status3 +
                      '（${_orderDetail.count}）',
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
            padding: EdgeInsets.only(
                left: Values.d_15, top: Values.d_15, bottom: Values.d_15),
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
                          margin: EdgeInsets.only(
                              left: Values.d_15, right: Values.d_50),
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
                      margin:
                          EdgeInsets.only(top: Values.d_5, right: Values.d_15),
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
                  errorWidget:
                      (BuildContext context, String url, dynamic error) {
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
                              '¥' +
                                  _orderDetail.price.toString() +
                                  S.of(context).dispatch_detail_price_unit,
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

  Widget OrderDetailWidget() {
    String _orderNum = _orderDetail.numberCode;
    String _orderPerson = _orderDetail.username;
    String _ordeTime = _orderDetail.createTime;
    String _userName = _orderDetail.sellname;
    return Container(
      color: Values.of(context).c_white_background,
      margin: EdgeInsets.only(top: Values.d_12),
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
                  style: TextStyle(
                      fontSize: Values.s_text_12,
                      color: Values.of(context).c_black_front_99)),
              SizedBox(
                width: Values.d_5,
              ),
              (!StringUtil.isEmpty(_userName) && _userName!='公司' && _userName!='悦多丰' && _orderDetail.status !="待付款" && _orderDetail.status !="已取消" )?
              GestureDetector(
                child: Image.asset('assets/images/tel.png'),
                onTap: () {
                  _makePhoneCall('tel:'+_orderDetail.sellerPhone);
                },
              ):Container()
            ],
          ),
          SizedBox(height: Values.d_10),
        ],
      ),
    );
  }

  Widget BottomWidget() {
    if (_orderDetail.status == S.of(context).order_status_4) {
      return Container(
        color: Values.of(context).c_white_background,
        child: SafeArea(
            child: Container(
          height: 44,
          margin: EdgeInsets.only(right: Values.d_15),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.end,
            children: <Widget>[
              Container(
                  height: 28,
                  margin: EdgeInsets.only(right: Values.d_10),
                  child: OutlineButton(
                      highlightColor: Values.c_translucent,
                      splashColor: Values.c_translucent,
                      color: Values.of(context).c_white_background,
                      child: Text(
                        S.of(context).cancel_order,
                        style: TextStyle(
                            fontSize: Values.s_text_14,
                            fontWeight: Values.font_Weight_normal,
                            color: Values.of(context).c_orange_front_0b,
                            decoration: TextDecoration.none),
                      ),
                      shape: new RoundedRectangleBorder(
                          borderRadius: new BorderRadius.circular(Values.d_36)),
                      borderSide: new BorderSide(
                          color: Values.of(context).c_orange_background_0b),
                      highlightedBorderColor:
                          Values.of(context).c_orange_background_0b,
                      onPressed: () {
                        showCancelDialog();
                      })),
              SizedBox(width: Values.d_10),
              Container(
                height: 28,
                child: FlatButton(
                    onPressed: () {
                      payAction();
                    },
                    color: Values.of(context).c_orange_background_0b,
                    disabledColor: Values.of(context).c_grey_background_cc,
                    highlightColor: Values.c_translucent,
                    splashColor: Values.c_translucent,
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(14.0)),
                    child: Text(
                      S.of(context).go_pay,
                      style: TextStyle(
                          fontSize: Values.s_text_14,
                          color: Values.of(context).c_white_front),
                    )),
              )
            ],
          ),
        )),
      );
    } else {
      return Container();
    }
  }
}
