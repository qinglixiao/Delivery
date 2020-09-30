import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_ienglish_fine/src/business/dispatch/bean/logistics_list.dart';
import 'package:flutter_ienglish_fine/src/comm/event/user_info_event.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_ienglish_fine/src/business/dispatch/bean/dispatch_detail.dart';
import 'package:flutter_ienglish_fine/src/business/dispatch/view/dialog_dispatch.dart';
import 'package:flutter_ienglish_fine/src/business/dispatch/viewmodel/dispatch_detail_view_model.dart';
import 'package:keyboard_avoider/keyboard_avoider.dart';

class DispathDeatilPage extends StatefulWidget {
  @override
  _DispathDeatilPageState createState() => _DispathDeatilPageState();
}

class _DispathDeatilPageState extends State<DispathDeatilPage> with PageBridge {
  bool _needRefresh = true;
  bool _isRefresh = true;
  bool _isDispatch = false;

  DisPatchDetailViewModel _disPatchDetailViewModel = DisPatchDetailViewModel();

  GlobalKey<_ExpressInfoWidgetState> expressKey = GlobalKey();
  GlobalKey<_ReceiveInfoWidgetState> receiveKey = GlobalKey();

  final ScrollController _scrollController = ScrollController();

  String _orderId = '';
  DispatchOrderDetail _orderDetail;

  _init() async {
    getInitArg(context).then((params) {
      _orderId = params['orderId'];
      if (_needRefresh) {
        _disPatchDetailViewModel.getDispatchDetail(_orderId, _isRefresh);
      }
      _isRefresh = true;
      _needRefresh = false;
    });
  }

  void submitAction(bool _isTakeTheir, String logisticsName, String logisticsNo,
      String count) {
    if (_isTakeTheir) {
      showTakeTheirDialog(count, () {
        loadTakeTheir(_isTakeTheir, logisticsName, logisticsNo, count);
      });
    } else {
      showExpressDialog(logisticsName, logisticsNo, count, () {
        loadTakeTheir(_isTakeTheir, logisticsName, logisticsNo, count);
      });
    }
  }

  void loadTakeTheir(bool _isTakeTheir, String logisticsName,
      String logisticsNo, String count) {
    String orderNO = _orderDetail.numberCode;
    String receiveAddress = _orderDetail.receiveAddress;
    String receiveMobile = _orderDetail.receiveMobile;
    String receiveName = _orderDetail.receiveName;
    _disPatchDetailViewModel.loadShipmentsData(
        count,
        _isTakeTheir.toString(),
        logisticsName,
        logisticsNo,
        orderNO,
        receiveAddress,
        receiveMobile,
        receiveName, (statusInfo) {
      if (statusInfo.error_code == '1') {
        emitUserInfoEvent();
        showErrorDialog(S.of(context).dispatch_success);
        _isRefresh = false;
        _needRefresh = true;
        _isDispatch = true;
        setState(() {});

      } else {
        showErrorDialog(statusInfo.message);
      }
    });
  }

  ///发货成功后,发送UserInfoEvent,更新个人信息
  emitUserInfoEvent() {
    eventCenter.emit(UserInfoEvent(type: USER_INFO_EVENT_TYPE_2));
  }

  showErrorDialog(String error) async {
    showDialog(
        context: context,
        child: DialogTool(
          bgColor: Values.of(context).c_white_background,
          title: S.of(context).dispatch_detail_selftaking_title,
          titleFontSize: Values.s_text_18,
          titleColor: Values.of(context).c_black_front_33,
          content: error,
          middleTitle: S.of(context).warn_sure,
          middleColor: Values.of(context).c_white_front,
          middleBgColor: Values.of(context).c_orange_background_0b,
          contentColor: Values.of(context).c_black_front_33,
          onTap: (int index) {},
        ));
  }

  void showTakeTheirDialog(String count, Function() callback) {
    showDialog(
        context: context,
        child: DialogTool(
          bgColor: Values.of(context).c_white_background,
          title: S.of(context).dispatch_detail_selftaking_title,
          titleFontSize: Values.s_text_18,
          titleColor: Values.of(context).c_black_front_33,
          content: S.of(context).dispatch_detail_selftaking_content1 +
              count +
              S.of(context).dispatch_detail_selftaking_content2,
          middleTitle: S.of(context).dispatch_detail_submit_button,
          middleColor: Values.of(context).c_white_front,
          middleBgColor: Values.of(context).c_orange_background_0b,
          contentColor: Values.of(context).c_black_front_33,
          onTap: (int index) {
            callback();
          },
        ));
  }

  void showExpressDialog(String logisticsName, String logisticsNo, String count,
      Function() callback) {
    String receiveAddress = _orderDetail.receiveAddress;
    String receiveMobile = _orderDetail.receiveMobile;
    String receiveName = _orderDetail.receiveName;
    showDialog(
        context: context,
        child: DialogDispatch(
          title: S.of(context).dispatch_detail_selftaking_title,
          titleFontSize: Values.s_text_18,
          titleColor: Values.c_black_b1,
          listContent: hanleExpress([
            count,
            receiveName,
            receiveMobile,
            receiveAddress,
            S.of(context).dispatch_detail_express_content_value,
            logisticsName,
            logisticsNo
          ]),
          middleTitle: S.of(context).dispatch_detail_submit_button,
          middleColor: Values.c_white_w1,
          middleBgColor: Values.c_orange_o1,
          contentColor: Values.c_black_b1,
          onTap: (int index) {
            callback();
          },
        ));
  }

  String hanleExpress(List list) {
    if (list == null) {
      return null;
    }
    String result;
    list.forEach((string) =>
        {if (result == null) result = string else result = '$result#*$string'});
    return result.toString();
  }

  void closeKeyboardAction(){
    receiveKey.currentState.handleBuyNum(receiveKey.currentState._number);
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
            pop(data: _isDispatch);
          },
        ),
        viewModel: _disPatchDetailViewModel,
        closeKeyboardCallBack:(){
          closeKeyboardAction();
        },
        body: ScrollWidget());
  }

  Widget ScrollWidget() {
    return GestureDetector(
        onTap: () {
          FocusScope.of(context).requestFocus(FocusNode());
          closeKeyboardAction();
        },
        child: KeyboardAvoider(
          child: CustomScrollView(
            controller: _scrollController,
            slivers: <Widget>[
              SliverToBoxAdapter(child: OrderInfoWidget()),
              ExpressInfoWidget(expressKey),
            ],
          ),
        ));
  }

  Widget OrderInfoWidget() {
    return StreamBuilder(
        stream: _disPatchDetailViewModel.streamOrderDetail,
        builder: (BuildContext context,
            AsyncSnapshot<DispatchOrderDetail> snapshot) {
          if (snapshot.data != null) {
            _orderDetail = snapshot.data;
            expressKey.currentState.updateOrderInfo();
          }
          if (_orderDetail == null) {
            return Container();
          }
          return Container(
            color: Values.of(context).c_grey_background_f8,
            child: Column(
              children: <Widget>[
                OrderHeaderWidget(),
                OrderShippingCountWidget(),
                GoodInfoWidget(),
                _orderDetail.status == '待付款' ?Container():(_orderDetail.sendCount < _orderDetail.count
                    ? ReceiveInfoWidget(receiveKey)
                    : Container()),
              ],
            ),
          );
        });
  }

  Widget OrderHeaderWidget() {
    final size = MediaQuery.of(context).size;
    final width = size.width;
    return Container(
      child: Stack(
        children: <Widget>[
          Container(
            width: width,
            child:
                Image.asset('assets/images/dispatch_bg.png', fit: BoxFit.fill),
          ),
          Container(
            margin: EdgeInsets.only(left: Values.d_44, top: Values.d_36),
            child: Column(
              children: <Widget>[
                Container(
                  child: Row(
                    crossAxisAlignment: CrossAxisAlignment.end,
                    children: <Widget>[
                      Text(_orderDetail.status,
                          style: TextStyle(
                              color: Values.of(context).c_white_front,
                              fontWeight: Values.font_Weight_medium,
                              fontSize: Values.s_text_20)),
                      Container(
                        margin: EdgeInsets.only(left: Values.d_10),
                        padding: EdgeInsets.only(bottom: Values.d_2),
                        child: Text(
                            S.of(context).dispatch_detail_count_title1 +
                                _orderDetail.count.toString() +
                                S.of(context).dispatch_detail_count_title2,
                            style: TextStyle(
                                color: Values.of(context).c_white_front,
                                fontWeight: Values.font_Weight_normal,
                                fontSize: Values.s_text_12)),
                      )
                    ],
                  ),
                ),
                Container(
                  margin: EdgeInsets.only(top: Values.d_8),
                  child: Row(
                    children: <Widget>[
                      Text(S.of(context).dispatch_detail_total_price,
                          style: TextStyle(
                              color: Values.of(context).c_white_front,
                              fontWeight: Values.font_Weight_medium,
                              fontSize: Values.s_text_12)),
                      SizedBox(width: Values.d_5),
                      Text(_orderDetail.paymentFee.toStringAsFixed(2),
                          style: TextStyle(
                              color: Values.of(context).c_white_front,
                              fontWeight: Values.font_Weight_medium,
                              fontSize: Values.s_text_12)),
                    ],
                  ),
                )
              ],
            ),
          )
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
                  S.of(context).dispatch_detail_shipping_status1 +
                      '（${_orderDetail.count - _orderDetail.sendCount}）',
                  style: TextStyle(
                      color: Values.of(context).c_black_front_66,
                      fontWeight: Values.font_Weight_normal,
                      fontSize: Values.s_text_14),
                  textAlign: TextAlign.center)),
          Expanded(
              flex: 1,
              child: Text(
                  S.of(context).dispatch_detail_shipping_status2 +
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

  Widget GoodInfoWidget() {
    return Container(
      decoration: BoxDecoration(
          color: Values.of(context).c_white_background,
          borderRadius: BorderRadius.all(Radius.circular(5))),
      margin: EdgeInsets.only(
        top: Values.d_12,
        left: Values.d_12,
        right: Values.d_12,
      ),
      padding: EdgeInsets.all(Values.d_15),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Container(
            margin: EdgeInsets.only(right: Values.d_8),
            width: Values.d_88,
            height: Values.d_88,
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
                height: Values.d_88,
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
                              color: Values.of(context).c_black_front_33,
                              fontWeight: Values.font_Weight_medium,
                              fontSize: Values.s_text_14),
                        ),
                        Text(
                          'X' + _orderDetail.count.toString(),
                          style: TextStyle(
                              color: Values.of(context).c_black_front_99,
                              fontWeight: Values.font_Weight_normal,
                              fontSize: Values.s_text_14),
                        ),
                      ],
                    ))
                  ],
                )),
          ),
        ],
      ),
    );
  }
}

class ReceiveInfoWidget extends StatefulWidget {
  final Key key;

  const ReceiveInfoWidget(this.key);

  @override
  _ReceiveInfoWidgetState createState() => _ReceiveInfoWidgetState();
}

class _ReceiveInfoWidgetState extends State<ReceiveInfoWidget> with PageBridge {
  DispatchOrderDetail _orderDetail;
  bool _isTakeTheir = false;
  TextEditingController _numController;
  TextEditingController _nameController = new TextEditingController();
  TextEditingController _codeController = new TextEditingController();

  int _number = 0;

  void handleBuyNum(int num) {
    if (num <= 0) {
      num = 1;
      ProgressHUD.showText(warnText: S.of(context).dispatch_count_warn);
    } else if (num > (_orderDetail.count - _orderDetail.sendCount)) {
      num = _orderDetail.count - _orderDetail.sendCount;
      ProgressHUD.showText(warnText: S.of(context).dispatch_count_warn);
    }
    _number = num;
    _numController.text = num.toString();
  }

  @override
  Widget build(BuildContext context) {
    _orderDetail = context
        .findRootAncestorStateOfType<_DispathDeatilPageState>()
        ._orderDetail;
    if (_numController == null || _numController.text == null) {
      _numController = TextEditingController(
          text: (_orderDetail.count - _orderDetail.sendCount).toString());
      _number = _orderDetail.count - _orderDetail.sendCount;
    }
    return Container(
      child: Column(
        children: <Widget>[
          ReceiveInfoWidget(),
          SubmitWidget(),
        ],
      ),
    );
  }

  ///选择发货方式
  ///当选择物流时，需显示物流公司、订单、输入模块，否则隐藏
  ///可以分批发货、编辑发货数量
  Widget ReceiveInfoWidget() {
    return Container(
        decoration: BoxDecoration(
            color: Values.of(context).c_white_background,
            borderRadius: BorderRadius.all(Radius.circular(5))),
        margin: EdgeInsets.only(
          top: Values.d_12,
          left: Values.d_12,
          right: Values.d_12,
        ),
        child: Column(
          children: <Widget>[
            Container(
              padding: EdgeInsets.only(left: Values.d_15, top: Values.d_15),
              height: Values.d_97,
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
                        margin: EdgeInsets.only(
                            top: Values.d_5, right: Values.d_15),
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
                  Container(
                    margin: EdgeInsets.only(right: Values.d_15),
                    child: Image.asset('assets/images/right_icon.png'),
                  ),
                ],
              ),
            ),
            Container(
              child: Image.asset('assets/images/place_line.png'),
            ),
            Container(
              margin: EdgeInsets.only(top: Values.d_20, bottom: Values.d_20),
              padding: EdgeInsets.only(left: Values.d_15, right: Values.d_15),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                mainAxisSize: MainAxisSize.max,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: <Widget>[
                  Expanded(
                    flex: 1,
                    child: OutlineButton(
                      onPressed: () {
                        setState(() {
                          _isTakeTheir = false;
                        });
                      },
                      color: Values.of(context).c_white_background,
                      splashColor: Values.c_translucent,
                      highlightColor: Values.c_translucent,
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: <Widget>[
                          Text(
                            S.of(context).dispatch_detail_express,
                            style: TextStyle(
                                fontSize: Values.s_text_16,
                                color: Values.of(context).c_black_front_33,
                                fontWeight: Values.font_Weight_normal),
                          ),
                          !_isTakeTheir
                              ? Image.asset('assets/images/button_select.png')
                              : Container(),
                        ],
                      ),
                      borderSide: new BorderSide(
                          color: !_isTakeTheir
                              ? Values.of(context).c_orange_background_0b
                              : Values.of(context).c_grey_background_cc),
                      highlightedBorderColor:
                          Values.of(context).c_grey_background_cc,
                      shape: RoundedRectangleBorder(
                        borderRadius: new BorderRadius.circular(5.0),
                      ),
                    ),
                  ),
                  SizedBox(
                    width: Values.d_22,
                  ),
                  Expanded(
                    flex: 1,
                    child: OutlineButton(
                      onPressed: () {
                        setState(() {
                          _isTakeTheir = true;
                        });
                      },
                      color: Values.of(context).c_orange_background_0b,
                      splashColor: Values.c_translucent,
                      highlightColor: Values.c_translucent,
                      focusColor: Colors.yellow,
                      hoverColor: Colors.red,
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: <Widget>[
                          Text(
                            S.of(context).dispatch_detail_take_their,
                            style: TextStyle(
                                fontSize: Values.s_text_16,
                                color: Values.of(context).c_black_front_33,
                                fontWeight: Values.font_Weight_normal),
                          ),
                          _isTakeTheir
                              ? Image.asset('assets/images/button_select.png')
                              : Container(),
                        ],
                      ),
                      borderSide: new BorderSide(
                          color: !_isTakeTheir
                              ? Values.of(context).c_grey_background_cc
                              : Values.of(context).c_orange_background_0b),
                      highlightedBorderColor:
                          Values.of(context).c_orange_background_0b,
                      shape: RoundedRectangleBorder(
                        borderRadius: new BorderRadius.circular(5.0),
                      ),
                    ),
                  )
                ],
              ),
            ),
            _isTakeTheir
                ? Container()
                : Container(
                    padding: EdgeInsets.only(
                        left: Values.d_15,
                        right: Values.d_15,
                        bottom: Values.d_15),
                    height: 90,
                    child: Column(
                      children: <Widget>[
                        Expanded(
                          flex: 1,
                          child: Row(
                            children: <Widget>[
                              Text(S.of(context).dispatch_detail_express_title,
                                  style: TextStyle(
                                      color:
                                          Values.of(context).c_black_front_33,
                                      fontWeight: Values.font_Weight_normal,
                                      fontSize: Values.s_text_15)),
                              SizedBox(width: Values.d_15),
                              Expanded(
                                child: TextField(
                                  controller: _nameController,
                                  textAlign: TextAlign.left,
                                  style: TextStyle(
                                      color:
                                          Values.of(context).c_black_front_33,
                                      fontWeight: Values.font_Weight_medium,
                                      fontSize: Values.s_text_18),
                                  decoration: InputDecoration(
                                      enabledBorder: UnderlineInputBorder(
                                        borderSide: BorderSide(
                                            color: Values.c_translucent),
                                      ),
                                      focusedBorder: UnderlineInputBorder(
                                        borderSide: BorderSide(
                                            color: Values.c_translucent),
                                      ),
                                      hintText: S
                                          .of(context)
                                          .dispatch_detail_express_hint_title,
                                      hintStyle: TextStyle(
                                          color: Values.of(context)
                                              .c_black_front_99,
                                          fontWeight: Values.font_Weight_medium,
                                          fontSize: Values.s_text_14)),
                                  onSubmitted: (value) {},
                                  cursorColor:
                                      Values.of(context).c_black_front_33,
                                ),
                              ),
                            ],
                          ),
                        ),
                        SizedBox(height: Values.d_10),
                        Expanded(
                          flex: 1,
                          child: Row(
                            children: <Widget>[
                              Text(S.of(context).dispatch_detail_express_num,
                                  style: TextStyle(
                                      color:
                                          Values.of(context).c_black_front_33,
                                      fontWeight: Values.font_Weight_normal,
                                      fontSize: Values.s_text_15)),
                              SizedBox(width: Values.d_15),
                              Expanded(
                                child: TextField(
                                  controller: _codeController,
                                  textAlign: TextAlign.left,
                                  style: TextStyle(
                                      color:
                                          Values.of(context).c_black_front_33,
                                      fontWeight: Values.font_Weight_medium,
                                      fontSize: Values.s_text_18),
                                  decoration: InputDecoration(
                                      enabledBorder: UnderlineInputBorder(
                                        borderSide: BorderSide(
                                            color: Values.c_translucent),
                                      ),
                                      focusedBorder: UnderlineInputBorder(
                                        borderSide: BorderSide(
                                            color: Values.c_translucent),
                                      ),
                                      hintText: S
                                          .of(context)
                                          .dispatch_detail_express_hint_num,
                                      hintStyle: TextStyle(
                                          color: Values.of(context)
                                              .c_black_front_99,
                                          fontWeight: Values.font_Weight_medium,
                                          fontSize: Values.s_text_14)),
                                  onSubmitted: (value) {
                                    handleBuyNum(int.parse(value));
                                  },
                                  cursorColor:
                                      Values.of(context).c_black_front_33,
                                ),
                              ),
                            ],
                          ),
                        ),
                      ],
                    ),
                  ),
            Container(
                decoration: BoxDecoration(
                    color: Values.of(context).c_white_background,
                    borderRadius: BorderRadius.only(
                        bottomLeft: Radius.circular(5),
                        bottomRight: Radius.circular(5))),
                padding: EdgeInsets.only(left: Values.d_15, right: Values.d_15),
                height: Values.d_50,
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: <Widget>[
                    Text(S.of(context).dispatch_detail_express_count,
                        style: TextStyle(
                            color: Values.of(context).c_black_front_33,
                            fontWeight: Values.font_Weight_normal,
                            fontSize: Values.s_text_15)),
                    Container(
                        child: Row(
                      children: <Widget>[
                        GestureDetector(
                          onTap: () {
                            FocusScope.of(context).requestFocus(FocusNode());
                            int value =
                                int.parse(_numController.text.toString());
                            value -= 1;
                            handleBuyNum(value);
                          },
                          child: Container(
                              width: Values.d_18,
                              height: Values.d_18,
                              child: Image.asset(
                                  'assets/images/count_subtract.png')),
                        ),
                        Container(
                          margin: EdgeInsets.only(
                              left: Values.d_5, right: Values.d_5),
                          width: Values.d_36,
                          child: TextField(
                            controller: _numController,
                            textAlign: TextAlign.center,
                            style: TextStyle(
                                color: Values.of(context).c_black_front_33,
                                fontWeight: Values.font_Weight_medium,
                                fontSize: Values.s_text_18),
                            decoration: InputDecoration(
                              enabledBorder: UnderlineInputBorder(
                                borderSide:
                                    BorderSide(color: Values.c_translucent),
                              ),
                              focusedBorder: UnderlineInputBorder(
                                borderSide:
                                    BorderSide(color: Values.c_translucent),
                              ),
                            ),
                            onChanged: (value) {
                              _number = int.parse(value);
                            },
                            onSubmitted: (value) {},
                            inputFormatters: [
                              LengthLimitingTextInputFormatter(3)
                            ],
                            keyboardType: TextInputType.phone,
                            cursorColor: Values.of(context).c_black_front_33,
                          ),
                        ),
                        GestureDetector(
                            onTap: () {
                              FocusScope.of(context).requestFocus(FocusNode());
                              int value =
                                  int.parse(_numController.text.toString());
                              value += 1;
                              handleBuyNum(value);
                            },
                            child: Container(
                              decoration: BoxDecoration(
                                  color:
                                      Values.of(context).c_orange_background_0b,
                                  borderRadius:
                                      BorderRadius.all(Radius.circular(3))),
                              width: Values.d_18,
                              height: Values.d_18,
                              child: Image.asset('assets/images/count_add.png'),
                            ))
                      ],
                    )),
                  ],
                )),
          ],
        ));
  }

  Widget SubmitWidget() {
    return Row(
      children: <Widget>[
        Expanded(
          child: Container(
            decoration: BoxDecoration(
                color: Values.of(context).c_orange_background_0b,
                borderRadius: BorderRadius.all(Radius.circular(5))),
            margin: EdgeInsets.only(
                left: Values.d_15, right: Values.d_15, top: Values.d_20),
            child: FlatButton(
                onPressed: () {
                  String logisticsName = '';
                  String logisticsNo = '';
                  String count = '1';

                  if (!_isTakeTheir) {
                    if (_nameController.text.length == 0) {
                      ProgressHUD.showError(
                          warnText:
                              S.of(context).dispatch_detail_express_hint_title);
                      return;
                    }
                    if (_codeController.text.length == 0) {
                      ProgressHUD.showError(
                          warnText: S.of(context).dispatch_detail_express_num);
                      return;
                    }
                  }

                  if (_nameController != null && !_isTakeTheir) {
                    logisticsName = _nameController.text;
                  }
                  if (_codeController != null && !_isTakeTheir) {
                    logisticsNo = _codeController.text;
                  }
                  if (_numController != null) {
                    count = _numController.text;
                  }

                  context
                      .findRootAncestorStateOfType<_DispathDeatilPageState>()
                      .submitAction(
                          _isTakeTheir, logisticsName, logisticsNo, count);
                },
                child: Text(
                  S.of(context).dispatch_detail_submit_button,
                  style: TextStyle(
                      color: Values.of(context).c_white_front,
                      fontWeight: Values.font_Weight_medium,
                      fontSize: Values.s_text_16),
                )),
          ),
          flex: 1,
        )
      ],
    );
  }
}

/// 发货信息
/// 可展开查看物流详情
class ExpressInfoWidget extends StatefulWidget {
  final Key key;

  const ExpressInfoWidget(this.key);

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

  void getExpressDetailData(String logisticsName, String logisticsNo,int index) {
    ProgressHUD.showLoading();
    context.findRootAncestorStateOfType<_DispathDeatilPageState>()._disPatchDetailViewModel.loadOrderLogistics(logisticsNo).then((value){
      ProgressHUD.hiddenHUD();
      LogisticsList logistics = value;
      if(logistics.data!=null){
        _showExpressList = true;
        _showIndex = index;
        _extentDetailList = logistics.data.list;
        setState(() {});
      }
    });
  }

  void colseExpressList(){
    _showExpressList = false;
    _showIndex = -1;
    _extentDetailList.clear();
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    return StreamBuilder(
        stream: context
            .findRootAncestorStateOfType<_DispathDeatilPageState>()
            ._disPatchDetailViewModel
            .streamExpressInfoData,
        builder:
            (BuildContext context, AsyncSnapshot<ExpressInfoData> snapshot) {
          if (snapshot.data != null) {
            _expressInfo = snapshot.data.data;
            _extentList = _expressInfo.logistics;
          }
          return SliverList(
            delegate: SliverChildBuilderDelegate(
              ExtentItem,
              childCount: _extentList.length +
                  (context.findRootAncestorStateOfType<_DispathDeatilPageState>()._orderDetail != null ? 2 : 0),
            ),
          );
        });
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
      String _orderNum = context
          .findRootAncestorStateOfType<_DispathDeatilPageState>()
          ._orderDetail
          .numberCode;
      String _orderPerson = context
          .findRootAncestorStateOfType<_DispathDeatilPageState>()
          ._orderDetail
          .username;
      String _ordeTime = context
          .findRootAncestorStateOfType<_DispathDeatilPageState>()
          ._orderDetail
          .createTime;

      return Container(
        color: Values.of(context).c_white_background,
        margin: EdgeInsets.only(bottom: Values.d_12,top: Values.d_12),
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
          ],
        ),
      );
    }
    else {
      ExpressItemInfo _itemInfo = _extentList[index - 1];
      return Container(
        color: Values.of(context).c_white_background,
        margin: EdgeInsets.only(bottom: Values.d_12),
        padding: EdgeInsets.only(
            left: Values.d_12, right: Values.d_12, top: Values.d_26),
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
                          fontSize: Values.s_text_14),
                    ),
                    Text(
                      _itemInfo.receiveName,
                      style: TextStyle(
                          color: Values.of(context).c_black_front_33,
                          fontWeight: Values.font_Weight_medium,
                          fontSize: Values.s_text_14),
                    ),
                  ],
                ),
                Text(
                  _itemInfo.ifSelfTaking
                      ? S.of(context).dispatch_detail_express_selftaking
                      : _itemInfo.status,
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
                  S.of(context).dispatch_detail_express_count + '：',
                  style: TextStyle(
                      color: Values.of(context).c_black_front_33,
                      fontWeight: Values.font_Weight_normal,
                      fontSize: Values.s_text_14),
                ),
                Text(
                  _itemInfo.quantity.toString(),
                  style: TextStyle(
                      color: Values.of(context).c_black_front_33,
                      fontWeight: Values.font_Weight_medium,
                      fontSize: Values.s_text_14),
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
                      fontSize: Values.s_text_14),
                ),
                Text(
                  _itemInfo.receiveMobile.toString(),
                  style: TextStyle(
                      color: Values.of(context).c_black_front_33,
                      fontWeight: Values.font_Weight_medium,
                      fontSize: Values.s_text_14),
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
                      fontSize: Values.s_text_14),
                ),
                Text(
                  _itemInfo.outTime.toString(),
                  style: TextStyle(
                      color: Values.of(context).c_black_front_33,
                      fontWeight: Values.font_Weight_medium,
                      fontSize: Values.s_text_14),
                ),
              ],
            ),
            SizedBox(height: Values.d_10),
            (_itemInfo.ifSelfTaking)
                ? Container()
                : (_showExpressList && _showIndex == index) ? ExtentDetailList() : Container(
                    child: Column(
                      children: <Widget>[
                        Container(
                          height: 1,
                          color: Values.of(context).c_grey_ea,
                        ),
                        Container(
                          height: Values.d_50,
                          child: FlatButton(
                              onPressed: () {
                                int selectIndex = (_showExpressList && index > _showIndex + 1 )? index - 1 - _extentDetailList.length : index;
                                getExpressDetailData(_itemInfo.logisticsName,
                                    _itemInfo.logisticsNo,selectIndex);
                              },
                              highlightColor: Values.c_translucent,
                              splashColor: Values.c_translucent,
                              child: Row(
                                mainAxisAlignment: MainAxisAlignment.center,
                                children: <Widget>[
                                  Text(S.of(context).dispatch_detail_express_detail,
                                      style: TextStyle(
                                          color: Values.of(context)
                                              .c_black_front_99,
                                          fontWeight: Values.font_Weight_normal,
                                          fontSize: Values.s_text_13)),
                                  SizedBox(width: Values.d_5),
                                  Image.asset('assets/images/down_icon.png')
                                ],
                              )),
                        )
                      ],
                    ),
                  )
          ],
        ),
      );
    }
  }

  Widget ExtentDetailList(){
    return ListView.builder(
      shrinkWrap: true,
      physics: NeverScrollableScrollPhysics(),
      itemBuilder: (context, index) {
        return ExpressDetailListItem(context, index);
      },
      itemCount: _extentDetailList.length +2,
    );
  }

  Widget ExpressDetailListItem(BuildContext context, int index) {
    if(index == 0){
      ExpressItemInfo _itemInfo = _extentList[_showIndex - 1];
      return Container(
          color: Values.of(context).c_white_background,
          padding: EdgeInsets.only(top:Values.d_12,bottom: Values.d_12),
          child:  Text(_itemInfo.logisticsName+'：'+_itemInfo.logisticsNo,
              style: TextStyle(
                  color: Values.of(context).c_black_front_33,
                  fontWeight: Values.font_Weight_normal,
                  fontSize: Values.s_text_14)));
    }
    else if(index == _extentDetailList.length + 1){
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
                        color: Values.of(context)
                            .c_black_front_99,
                        fontWeight: Values.font_Weight_normal,
                        fontSize: Values.s_text_12)),
                SizedBox(width: Values.d_5),
                Image.asset('assets/images/pull_up.png')
              ],
            )),
      );
    }
    else{
      LogisticsItem item = _extentDetailList[index-1];
      return Column(children: <Widget>[
        Container(
          margin: EdgeInsets.all(0),
          color: Values.of(context).c_white_background,
          height: Values.d_50,
          child:Row(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Column(
                children: <Widget>[
                  Image.asset(index == 1 ? 'assets/images/status_select.png' : 'assets/images/status_normal.png'),
                  Expanded(child: Container(
                    width: 1,
                    color: Values.of(context).c_grey_ea,
                  ))
                ],
              ),
              SizedBox(width: Values.d_12),
              Expanded(child:Container(
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
