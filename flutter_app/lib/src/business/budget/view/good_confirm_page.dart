import 'dart:math';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_ienglish_fine/src/business/budget/comm/interfaces.dart';
import 'package:flutter_ienglish_fine/src/business/pay/view/account/dialog_open_account.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_ienglish_fine/src/business/budget/bean/good_confirm.dart';
import 'package:flutter_ienglish_fine/src/business/budget/viewmodel/good_confirm_view_model.dart';

import 'dialog_rise_in_price.dart';

class GoodConfirmPage extends StatefulWidget {
  @override
  _GoodConfirmPageState createState() => _GoodConfirmPageState();
}

class _GoodConfirmPageState extends State<GoodConfirmPage> with PageBridge, WidgetsBindingObserver {
  int _buyNum = 0;
  bool _isUpgrade = false;
  Place _place;
  ServiceProviderLevel _providerLevel;
  List _codes; //补单时参数
  bool _isReplenish = false; //是否补单
  String _placeId;

  ///套组id
  String _collocationId;

  ///是否是升级套组
  bool _isCollocation;

  GoodConfirmViewModel _confirmViewModel = GoodConfirmViewModel();
  CollocationInfo _collocationInfo;
  PersionStatusInfo _persionStatusInfo;

  GlobalKey<_GoodInfoWidgetState> goodInfoKey = GlobalKey();
  GlobalKey<_UpdateGradeWidgetState> updateGradeKey = GlobalKey();
  GlobalKey<_BalanceInfoWidgetState> balanceInfoKey = GlobalKey();
  GlobalKey<_UpdateInfoWidgetState> updateInfoKey = GlobalKey();
  GlobalKey<_PriceInfoWidgetState> priceInfoKey = GlobalKey();

//  @override
//  void didChangeMetrics() {
//    super.didChangeMetrics();
//    WidgetsBinding.instance.addPostFrameCallback((_) {
//        if (MediaQuery.of(context).viewInsets.bottom == 0) {
//          //关闭键盘
//          if(_isShowKeyboard){
//            FocusScope.of(context).requestFocus(FocusNode());
//            goodInfoKey.currentState.finishEdit();
//            _isShowKeyboard = false;
//          }
//        } else {
//          //显示键盘
//          _isShowKeyboard = true;
//        }
//    });
//  }

  void changeBuyNum(int num, bool isUpgrade) {
    _buyNum = IntUtil.getNotNullInt(num);
    _isUpgrade = isUpgrade;
  }

  void getPlace(Place place) {
    _place = place;
  }

  void getProviderLevel(ServiceProviderLevel providerLevel) {
    _providerLevel = providerLevel;
  }

  ///获取搭配详情，目前用于获取directEpithets,提供给（获取订单判断接口）
  void getCollocationInfo() {
    if (_collocationInfo == null) {
      _confirmViewModel.loadCollocationInfo(_collocationId, (success, collocationInfo) {
        _collocationInfo = collocationInfo;
      });
    }
  }

  ///获取订单判断接口（是否开户、是否需要涨价）
  ///升级套组、普通套组参数不同
  ///升级套组 riseRankNum参数依赖前置接口
  ///普通套组 riseRankNum参数 1.升级 传当前的上一级  2.不升级 传当前等级
  void getPersionStatusInfo() {
    if (_place == null) {
      ProgressHUD.showText(warnText: S.of(context).good_confirm_no_place);
      return;
    }

    String code = '';
    if (_isCollocation) {
      if (_collocationInfo != null) {
        code = _collocationInfo.directEpithets != null ? _collocationInfo.directEpithets.toString() : '';
      }
    } else {
      if (_isUpgrade) {
        code = (_providerLevel.rankNum - 1).toString();
      } else {
        code = _providerLevel.rankNum.toString();
      }
    }
    if (!StringUtil.isEmpty(code)) {
      ProgressHUD.showLoading();
      _confirmViewModel.loadPersionStatusInfo(code, (success, persionStatusInfo) {
        ProgressHUD.hiddenHUD();
        _persionStatusInfo = persionStatusInfo;
        handleCheckInfo();
      });
    }
  }

  ///处理下订单前判读
  ///1.判读是否已开户=>未开户、弹出开户提示框、去开户
  ///2.判读是否需要弹出涨价提示框，提示去开企业户
  void handleCheckInfo() {
    if (!_persionStatusInfo.ifOpenAcct) {
      ///判读是否已开户=>未开户、弹出开户提示框、去开户
      showDialogOpenAccount();
    } else if (_persionStatusInfo.ifaffirm) {
      /// 判读是否需要弹出涨价提示框，提示去开企业户'
      showDialog(
          context: context,
          child: DialogRiseInPrice(
              statusInfo: _persionStatusInfo,
              buyNum: _buyNum,
              price: _isUpgrade ? _providerLevel.nextPrice : _providerLevel.price,
              onTap: (int type) {
                if (type == 0) {
                  ///去支付
                  submitOrder();
                } else {
                  open(RouterName.open_company_account);
                }
              },
              linkOnTap: (String path) {
                open(RouterName.web_view,
                    argument:
                        WebParams(title: S.of(context).agreement_user, url: NetworkConfig().getHostForWeb() + path));
              }));
    } else {
      /// 下订单
      submitOrder();
    }
  }

  void submitOrder() {
    if (_isCollocation) {
      buyCollocation();
    } else {
      buySingleCollocation();
    }
  }

  ///升级套组下单
  void buyCollocation() {
    String count = _buyNum.toString();
    String receiveAddress = _place.province + _place.city + _place.detailaddress;
    String receiveMobile = _place.tel;
    String receiveName = _place.username;
    String collocationId = _collocationId;
    ProgressHUD.showLoading();
    _confirmViewModel.loadBuyCollocation(count, receiveAddress, receiveMobile, receiveName, collocationId,
        (success, buyCollocationInfo) {
      ProgressHUD.hiddenHUD();
      if (buyCollocationInfo.error_code == '1') {
        open(RouterName.pay_affirm_page, argument: buyCollocationInfo);
      } else {
        showDialogExistOrder(buyCollocationInfo.message);
      }
    });
  }

  ///普通套组下单
  void buySingleCollocation() {
    String count = _buyNum.toString();
    String receiveAddress = _place.province + _place.city + _place.detailaddress;
    String receiveMobile = _place.tel;
    String receiveName = _place.username;
    String collocationId = StringUtil.getNotNullString(_collocationId);
    String ifUpgrade = _isUpgrade.toString();
    ProgressHUD.showLoading();
    _confirmViewModel.loadBuySingleCollocation(
        count, ifUpgrade, receiveAddress, receiveMobile, receiveName, collocationId, (success, buyCollocationInfo) {
      ProgressHUD.hiddenHUD();
      if (buyCollocationInfo.error_code == '1') {
        open(RouterName.pay_affirm_page, argument: buyCollocationInfo);
      } else {
        showDialogExistOrder(buyCollocationInfo.message);
      }
    });
  }

  /// 开户弹框
  void showDialogOpenAccount() {
    showDialog(
        context: context,
        child: DialogTool(
          bgColor: Values.of(context).c_white_background,
          title: S.of(context).warn_title,
          titleFontSize: Values.s_text_18,
          titleColor: Values.of(context).c_black_front_33,
          content: S.of(context).good_confirm_open_account_2,
          contentColor: Values.of(context).c_black_front_33,
          leftTitle: S.of(context).warn_button_title,
          leftColor: Values.of(context).c_orange_front_0b,
          leftBgColor: Values.of(context).c_white_background,
          rightTitle: S.of(context).good_confirm_open_account_button_2,
          rightColor: Values.of(context).c_white_front,
          rightBgColor: Values.of(context).c_orange_background_0b,
          onTap: (int index) {
            if (index == 0) {
            } else if (index == 1) {
              showOpenAccountDialog();
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

  /// 存在未支付订单弹框
  void showDialogExistOrder(String message) {
    showDialog(
        context: context,
        child: DialogTool(
          bgColor: Values.of(context).c_white_background,
          title: S.of(context).warn_title,
          titleFontSize: Values.s_text_18,
          titleColor: Values.of(context).c_black_front_33,
          content: message,
          contentColor: Values.of(context).c_black_front_33,
          leftTitle: S.of(context).warn_cancel,
          leftColor: Values.of(context).c_orange_front_0b,
          leftBgColor: Values.of(context).c_white_background,
          rightTitle: S.of(context).good_confirm_exist_order_button_1,
          rightColor: Values.of(context).c_white_front,
          rightBgColor: Values.of(context).c_orange_background_0b,
          onTap: (int index) {
            if (index == 1) {
              open(RouterName.my_buy_list);
            }
          },
        ));
  }

  /// 普通消息提示框
  void showDialogInfo(String message) {
    showDialog(
        context: context,
        child: DialogTool(
          bgColor: Values.of(context).c_white_background,
          title: S.of(context).warn_title,
          titleFontSize: Values.s_text_18,
          titleColor: Values.of(context).c_black_front_33,
          content: message,
          middleTitle: S.of(context).warn_button_title,
          middleColor: Values.of(context).c_white_front,
          middleBgColor: Values.of(context).c_orange_background_0b,
          contentColor: Values.of(context).c_black_front_33,
          onTap: (int index) {},
        ));
  }

  void setPlaceId(String placeId) {
    _placeId = placeId;
    _confirmViewModel.loadPlaceDetail(_placeId);
  }

  @override
  Widget build(BuildContext context) {
    getInitArg(context).then((params) {
      _collocationId = params['id'];
      _isCollocation = params['isCombo'];
      _codes = params['codes'] != null ? params['codes'].cast<String>() : List<String>();
      _isReplenish = params['isReplenish'] ?? false;
      if (_isCollocation) {
        getCollocationInfo();
      }
      _confirmViewModel.getConfirmata(_codes, _placeId);
    });
    return RootPageWidget(
        appBar: IsAppBar(
          title: S.of(context).good_confirm_title,
          leftOnTap: () {
            pop();
          },
        ),
        viewModel: _confirmViewModel,
        closeKeyboardCallBack: () {
          goodInfoKey.currentState.finishEdit();
        },
        body: ScrollWidget());
  }

  Widget ScrollWidget() {
    return GestureDetector(
        onTap: () {
          FocusScope.of(context).requestFocus(FocusNode());
          goodInfoKey.currentState.finishEdit();
        },
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: <Widget>[
            Flexible(
              child: SingleChildScrollView(
                  child: Container(
                margin: EdgeInsets.only(left: Values.d_12, right: Values.d_12),
                child: Column(
                  mainAxisSize: MainAxisSize.min,
                  children: <Widget>[
                    PlaceWidget(),
                    GoodInfoWidget(goodInfoKey),
                    UpdateGradeWidget(updateGradeKey),
                    BalanceInfoWidget(balanceInfoKey),
                  ],
                ),
              )),
            ),
            BottomWidget(),
          ],
        ));
  }

  Widget BottomWidget() {
    return Container(
      color: Values.of(context).c_white_background,
      child: SafeArea(
          child: Column(
        children: <Widget>[
          UpdateInfoWidget(updateInfoKey),
          PriceInfoWidget(priceInfoKey),
        ],
      )),
    );
  }
}

/// 地址信息
/// 1.读取默认地址，当默认地址未空显示站位
/// 2.当选择了某一地址，根据地址ID读取响应地址
class PlaceWidget extends StatefulWidget {
  @override
  _PlaceWidgetState createState() => _PlaceWidgetState();
}

class _PlaceWidgetState extends State<PlaceWidget> with PageBridge {
  @override
  Widget build(BuildContext context) {
    Place _place;
    return StreamBuilder(
        stream: context.findRootAncestorStateOfType<_GoodConfirmPageState>()._confirmViewModel.streamPlace,
        builder: (BuildContext context, AsyncSnapshot<Place> snapshot) {
          if (snapshot.data != null && snapshot.data?.id != null) {
            _place = snapshot.data;
            context.findRootAncestorStateOfType<_GoodConfirmPageState>().getPlace(_place);
          }
          return Container(
              decoration: BoxDecoration(
                  color: Values.of(context).c_white_background, borderRadius: BorderRadius.all(Radius.circular(5))),
              margin: EdgeInsets.only(
                top: Values.d_12,
              ),
              height: Values.d_100,
              child: Container(
                child: Column(
                  children: <Widget>[
                    Container(
                      height: Values.d_97,
                      padding: EdgeInsets.only(left: Values.d_15, top: Values.d_15),
                      child: GestureDetector(
                          behavior: HitTestBehavior.opaque,
                          onTap: () {
                            PlaceListAction();
                          },
                          child: (_place == null || _place.error_code != '1')
                              ? Row(
                                  crossAxisAlignment: CrossAxisAlignment.center,
                                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                                  children: <Widget>[
                                    Container(
                                        alignment: Alignment.centerLeft,
                                        child: Text(
                                          S.of(context).place_add_title,
                                          style: TextStyle(
                                              color: Values.of(context).c_black_front_33,
                                              fontWeight: Values.font_Weight_normal,
                                              fontSize: Values.s_text_15),
                                        )),
                                    Container(
                                      margin: EdgeInsets.only(right: Values.d_15),
                                      child: Image.asset('assets/images/right_icon.png'),
                                    ),
                                  ],
                                )
                              : Row(
                                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                                  children: <Widget>[
                                    Expanded(
                                        child: Column(
                                      crossAxisAlignment: CrossAxisAlignment.start,
                                      mainAxisAlignment: MainAxisAlignment.start,
                                      children: <Widget>[
                                        Row(
                                          children: <Widget>[
                                            Container(
                                              child: Text(_place.username,
                                                  style: TextStyle(
                                                      color: Values.of(context).c_black_front_33,
                                                      fontWeight: Values.font_Weight_medium,
                                                      fontSize: Values.s_text_18)),
                                            ),
                                            Container(
                                              margin: EdgeInsets.only(left: Values.d_15, right: Values.d_50),
                                              child: Text(_place.tel,
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
                                            _place.province + _place.city + _place.area + _place.detailaddress,
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
                                )),
                    ),
                    Container(
                      child: Image.asset('assets/images/place_line.png'),
                    ),
                  ],
                ),
              ));
        });
  }

  void PlaceListAction() {
    open(RouterName.place_list, argument: {'choose': true}).then((value) {
      context.findRootAncestorStateOfType<_GoodConfirmPageState>().setPlaceId(value['id']);
    });
  }
}

/// 商品信息模块
/// 显示商品基本信息、当前和上级的进货价、编辑购买数量
/// 1.编辑数量时、余额模块、总价模块、会跟随联动
/// 2.当编辑数量满足升级条件、会弹出弹框询问是否升级、界面显示升级选择模块
class GoodInfoWidget extends StatefulWidget {
  final Key key;

  const GoodInfoWidget(this.key);

  @override
  _GoodInfoWidgetState createState() => _GoodInfoWidgetState();
}

class _GoodInfoWidgetState extends State<GoodInfoWidget> {
  ///购买数量
  int _buyNum = 0;

  ///是否升级
  bool _isUpgrade = false;

  ServiceProviderLevel _providerLevel;
  TextEditingController _numController = new TextEditingController();
  bool _isShow = false;

  /// 提示升级弹框
  void showDialogUpgrade() {
    showDialog(
        context: context,
        child: DialogTool(
          bgColor: Values.of(context).c_white_background,
          title: S.of(context).warn_title,
          titleFontSize: Values.s_text_18,
          titleColor: Values.of(context).c_black_front_33,
          content: S.of(context).good_confirm_update_warn_1 +
              _providerLevel.nextName +
              S.of(context).good_confirm_update_warn_2 +
              _providerLevel.nextName +
              S.of(context).good_confirm_update_warn_3,
          contentColor: Values.of(context).c_black_front_33,
          leftTitle: S.of(context).good_confirm_update_button_1,
          leftColor: Values.of(context).c_orange_front_0b,
          leftBgColor: Values.of(context).c_white_background,
          rightTitle: S.of(context).good_confirm_update_button_2,
          rightColor: Values.of(context).c_white_front,
          rightBgColor: Values.of(context).c_orange_background_0b,
          onTap: (int index) {
            if (index == 0) {
              _isUpgrade = false;
              context
                  .findRootAncestorStateOfType<_GoodConfirmPageState>()
                  .updateGradeKey
                  .currentState
                  .changeLevel(_isUpgrade);
            } else {
              _isUpgrade = true;
              updateLevel();
            }
          },
        ));
  }

  /// 更新升级状态、单价会跟等级联动
  void changeLevel(bool isUpgrade) {
    setState(() {
      _isUpgrade = isUpgrade;
    });
    handleBuyNumByUpgrade();
    updateLevel();
  }

  /// 当编辑数量小于最低可定量时，更新显示最低可定量
  void handleBuyNumByUpgrade() {
    if (_buyNum < _providerLevel.nextCount) {
      _buyNum = _providerLevel.nextCount;
      _numController.text = _buyNum.toString();
    }
  }

  /// 处理购买数量
  /// 1.当是'升级套组'=>购买数量显示上级最低可定量
  /// 2.当是'普通套组'时
  /// 2.1已选择升级时，购买数量小于最低可定量时，进行不升级处理，同步更新'商品信息模块'单价、'升级选择模块'状态、'余额模块'、'总价模块'
  /// 2.2未选择升级时，购买数量小于最低可定量时，显示最低可定量
  /// 2.3选择升级时，购买数量大于升级可定量时，显示升级弹框，询问升级，（只弹一次），选择升级，会同步更新各个模块
  void handleBuyNum(int num) {
    if (context.findRootAncestorStateOfType<_GoodConfirmPageState>()._isCollocation) {
      _buyNum = num;
      if (num < _providerLevel.nextCount) {
        _buyNum = _providerLevel.nextCount;
      }
    } else {
      if (_isUpgrade) {
        _buyNum = num;
        if (num < _providerLevel.nextCount) {
          _isUpgrade = false;
          context
              .findRootAncestorStateOfType<_GoodConfirmPageState>()
              .updateGradeKey
              .currentState
              .changeLevel(_isUpgrade);
        }
      } else {
        if (num < _providerLevel.startCount) {
          ProgressHUD.showText(warnText: S.of(context).start_count_title + '${_providerLevel.startCount}');
          _buyNum = _providerLevel.startCount;
        } else {
          _buyNum = num;
        }
        if (_providerLevel.rankNum > 1 && num >= _providerLevel.nextCount && !_isUpgrade) {
          if (!_isShow) {
            _isShow = true;
            showDialogUpgrade();
          }
        }
      }
    }

    _numController.text = _buyNum.toString();
    updateBuyNum(_buyNum);
  }

  ///更新 是否升级
  void updateLevel() {
    context.findRootAncestorStateOfType<_GoodConfirmPageState>().updateGradeKey.currentState.changeLevel(_isUpgrade);
    updateBuyNum(_buyNum);
  }

  ///更新 购买数量 同步更新'商品信息模块'单价、'升级选择模块'状态、'余额模块'、'总价模块'
  void updateBuyNum(int count) {
    bool _isNextPrice = (_isUpgrade || context.findRootAncestorStateOfType<_GoodConfirmPageState>()._isCollocation);
    context
        .findRootAncestorStateOfType<_GoodConfirmPageState>()
        .balanceInfoKey
        .currentState
        .changeBuyNum(count, _isNextPrice ? _providerLevel.nextPrice : _providerLevel.price);
    context
        .findRootAncestorStateOfType<_GoodConfirmPageState>()
        .priceInfoKey
        .currentState
        .changeBuyNum(count, _isNextPrice ? _providerLevel.nextPrice : _providerLevel.price);
    context
        .findRootAncestorStateOfType<_GoodConfirmPageState>()
        .updateInfoKey
        .currentState
        .changeBuyNum(count, _isUpgrade);
    context.findRootAncestorStateOfType<_GoodConfirmPageState>().changeBuyNum(count, _isUpgrade);
  }

  /// 更新服务商基础信息
  void setProviderLevel(ServiceProviderLevel providerLevel) {
    context.findRootAncestorStateOfType<_GoodConfirmPageState>().getProviderLevel(providerLevel);
    context
        .findRootAncestorStateOfType<_GoodConfirmPageState>()
        .updateGradeKey
        .currentState
        .getProviderLevel(providerLevel);
    context
        .findRootAncestorStateOfType<_GoodConfirmPageState>()
        .updateInfoKey
        .currentState
        .getProviderLevel(providerLevel);
  }

  void finishEdit() {
    handleBuyNum(StringUtil.isEmpty(_numController.text.toString()) ? 0 : int.parse(_numController.text.toString()));
  }

  @override
  Widget build(BuildContext context) {
    bool isReplenish = context.findRootAncestorStateOfType<_GoodConfirmPageState>()._isReplenish;
    if (isReplenish) {
      return StreamBuilder(
          stream: context.findRootAncestorStateOfType<_GoodConfirmPageState>()._confirmViewModel.streamGoodConfirmInfo,
          builder: (BuildContext context, AsyncSnapshot<ServiceProviderLevel> snapshot) {
            return StreamBuilder(
                stream:
                    context.findRootAncestorStateOfType<_GoodConfirmPageState>()._confirmViewModel.streamAccountCount,
                builder: (BuildContext context, AsyncSnapshot<AccountCountBean> isnapshot) {
                  if (snapshot.data == null || isnapshot.data == null) {
                    return Container();
                  }
                  if (snapshot.data != null) {
                    _providerLevel = snapshot.data;
                  }
                  if (_providerLevel == null || _providerLevel.error_code != '1') {
                    return Container();
                  }
                  if (_buyNum == 0) {
                    if (context.findRootAncestorStateOfType<_GoodConfirmPageState>()._isCollocation) {
                      _buyNum = _providerLevel.nextCount;
                    } else {
                      if (_providerLevel.startCount < IntUtil.getNotNullInt(_providerLevel.repairCount)) {
                        _buyNum = _providerLevel.repairCount;
                      } else {
                        _buyNum = _providerLevel.startCount;
                      }
                      if (IntUtil.getNotNullInt(isnapshot.data.data.countDistributeAccount) > 0) {
                        _buyNum -= IntUtil.getNotNullInt(isnapshot.data.data.countDistributeAccount);
                      }
                    }
                    _numController.text = _buyNum.toString();
                    updateBuyNum(_buyNum);
                  }
                  setProviderLevel(_providerLevel);
                  return Container(
                      decoration: BoxDecoration(
                          color: Values.of(context).c_white_background,
                          borderRadius: BorderRadius.all(Radius.circular(5))),
                      margin: EdgeInsets.only(
                        top: Values.d_12,
                      ),
                      padding: EdgeInsets.only(left: Values.d_15, right: Values.d_15, top: Values.d_15),
                      child: Container(
                        child: Column(
                          children: <Widget>[
                            Container(
                              child: Row(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: <Widget>[
                                  Container(
                                    margin: EdgeInsets.only(right: Values.d_8),
                                    width: Values.d_88,
                                    height: Values.d_88,
                                    child: CachedNetworkImage(
                                      imageUrl: _providerLevel.imgUrl,
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
                                                _providerLevel.title,
                                                style: TextStyle(
                                                    color: Values.of(context).c_black_front_33,
                                                    fontWeight: Values.font_Weight_medium,
                                                    fontSize: Values.s_text_15),
                                                maxLines: 2,
                                                overflow: TextOverflow.ellipsis,
                                              ),
                                            ),
                                            Container(
                                              child: Text(
                                                '¥' +
                                                    (_isUpgrade
                                                        ? _providerLevel.nextPrice.toString()
                                                        : _providerLevel.price.toString()),
                                                style: TextStyle(
                                                    color: Values.of(context).c_black_front_33,
                                                    fontWeight: Values.font_Weight_medium,
                                                    fontSize: Values.s_text_15),
                                              ),
                                            )
                                          ],
                                        )),
                                  ),
                                ],
                              ),
                            ),
                            Container(
                              color: Values.of(context).c_grey_ea,
                              height: 1,
                              margin: EdgeInsets.only(top: Values.d_15, bottom: Values.d_5),
                            ),
                            Container(
                              margin: EdgeInsets.only(bottom: Values.d_10),
                              child: Row(
                                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                                children: <Widget>[
                                  Container(
                                      child: Text(
                                    S.of(context).good_confirm_num,
                                    style: TextStyle(
                                        color: Values.of(context).c_black_front_33,
                                        fontWeight: Values.font_Weight_normal,
                                        fontSize: Values.s_text_15),
                                  )),
                                  Container(
                                      child: Row(
                                    children: <Widget>[
                                      GestureDetector(
                                        onTap: () {
                                          FocusScope.of(context).requestFocus(FocusNode());
                                          int value = int.parse(_numController.text.toString());
                                          value -= 1;
                                          handleBuyNum(value);
                                        },
                                        child: Container(
                                            width: Values.d_18,
                                            height: Values.d_18,
                                            child: Image.asset('assets/images/count_subtract.png')),
                                      ),
                                      Container(
                                        margin: EdgeInsets.only(left: Values.d_5, right: Values.d_5),
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
                                              borderSide: BorderSide(color: Values.c_translucent),
                                            ),
                                            focusedBorder: UnderlineInputBorder(
                                              borderSide: BorderSide(color: Values.c_translucent),
                                            ),
                                          ),
                                          onChanged: (value) {},
                                          onSubmitted: (value) {
                                            handleBuyNum(int.parse(_numController.text.toString()));
                                          },
                                          inputFormatters: [LengthLimitingTextInputFormatter(3)],
                                          keyboardType: TextInputType.number,
                                          cursorColor: Values.of(context).c_black_front_33,
                                        ),
                                      ),
                                      GestureDetector(
                                          onTap: () {
                                            FocusScope.of(context).requestFocus(FocusNode());
                                            int value = int.parse(_numController.text.toString());
                                            value += 1;
                                            handleBuyNum(value);
                                          },
                                          child: Container(
                                            decoration: BoxDecoration(
                                                color: Values.of(context).c_orange_background_0b,
                                                borderRadius: BorderRadius.all(Radius.circular(3))),
                                            width: Values.d_18,
                                            height: Values.d_18,
                                            child: Image.asset('assets/images/count_add.png'),
                                          ))
                                    ],
                                  )),
                                ],
                              ),
                            ),
                            Container(
                              margin: EdgeInsets.only(top: Values.d_10, bottom: Values.d_12),
                              child: Column(
                                children: <Widget>[
                                  Row(
                                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                                    children: <Widget>[
                                      Container(
                                        margin: EdgeInsets.only(bottom: Values.d_12),
                                        child: Text(
                                          _providerLevel.rankName +
                                              S.of(context).good_confirm_price +
                                              _providerLevel.startCount.toString() +
                                              S.of(context).good_confirm_price_end,
                                          style: TextStyle(
                                              color: Values.of(context).c_black_front_33,
                                              fontWeight: Values.font_Weight_normal,
                                              fontSize: Values.s_text_12),
                                        ),
                                      ),
                                      Container(
                                        margin: EdgeInsets.only(bottom: Values.d_12),
                                        child: Text(
                                          '¥' + _providerLevel.price.toString(),
                                          style: TextStyle(
                                              color: Values.of(context).c_black_front_33,
                                              fontWeight: Values.font_Weight_normal,
                                              fontSize: Values.s_text_12),
                                        ),
                                      ),
                                    ],
                                  ),
                                  _providerLevel.rankNum <= 1
                                      ? Container()
                                      : Row(
                                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                                          children: <Widget>[
                                            Container(
                                              child: Text(
                                                _providerLevel.nextName +
                                                    S.of(context).good_confirm_price +
                                                    _providerLevel.nextCount.toString() +
                                                    S.of(context).good_confirm_price_end,
                                                style: TextStyle(
                                                    color: Values.of(context).c_orange_front_0b,
                                                    fontWeight: Values.font_Weight_normal,
                                                    fontSize: Values.s_text_12),
                                              ),
                                            ),
                                            Container(
                                              child: Text(
                                                '¥' + _providerLevel.nextPrice.toString(),
                                                style: TextStyle(
                                                    color: Values.of(context).c_orange_front_0b,
                                                    fontWeight: Values.font_Weight_normal,
                                                    fontSize: Values.s_text_12),
                                              ),
                                            ),
                                          ],
                                        ),
                                ],
                              ),
                            )
                          ],
                        ),
                      ));
                });
          });
    } else {
      return StreamBuilder(
          stream: context.findRootAncestorStateOfType<_GoodConfirmPageState>()._confirmViewModel.streamGoodConfirmInfo,
          builder: (BuildContext context, AsyncSnapshot<ServiceProviderLevel> snapshot) {
            if (snapshot.data != null) {
              _providerLevel = snapshot.data;
              if (_buyNum == 0) {
                if (context.findRootAncestorStateOfType<_GoodConfirmPageState>()._isCollocation) {
                  _buyNum = IntUtil.getNotNullInt(_providerLevel.nextCount);
                  _isUpgrade = true;
                } else {
                  if (_providerLevel.startCount < _providerLevel.repairCount) {
                    _buyNum = IntUtil.getNotNullInt(_providerLevel.repairCount);
                  } else {
                    _buyNum = IntUtil.getNotNullInt(_providerLevel.startCount);
                  }
                }
                _numController.text = _buyNum.toString();
                updateBuyNum(_buyNum);
              }
              setProviderLevel(_providerLevel);
            }
            if (_providerLevel == null || _providerLevel.error_code != '1') {
              return Container();
            }
            return Container(
                decoration: BoxDecoration(
                    color: Values.of(context).c_white_background, borderRadius: BorderRadius.all(Radius.circular(5))),
                margin: EdgeInsets.only(
                  top: Values.d_12,
                ),
                padding: EdgeInsets.only(left: Values.d_15, right: Values.d_15, top: Values.d_15),
                child: Container(
                  child: Column(
                    children: <Widget>[
                      Container(
                        child: Row(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: <Widget>[
                            Container(
                              margin: EdgeInsets.only(right: Values.d_8),
                              width: Values.d_88,
                              height: Values.d_88,
                              child: CachedNetworkImage(
                                imageUrl: _providerLevel.imgUrl,
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
                                          _providerLevel.title,
                                          style: TextStyle(
                                              color: Values.of(context).c_black_front_33,
                                              fontWeight: Values.font_Weight_medium,
                                              fontSize: Values.s_text_15),
                                          maxLines: 2,
                                          overflow: TextOverflow.ellipsis,
                                        ),
                                      ),
                                      Container(
                                        child: Text(
                                          '¥' +
                                              ((_isUpgrade && _providerLevel.rankNum > 1)
                                                  ? _providerLevel.nextPrice.toStringAsFixed(2)
                                                  : _providerLevel.price.toStringAsFixed(2)),
                                          style: TextStyle(
                                              color: Values.of(context).c_black_front_33,
                                              fontWeight: Values.font_Weight_medium,
                                              fontSize: Values.s_text_15),
                                        ),
                                      )
                                    ],
                                  )),
                            ),
                          ],
                        ),
                      ),
                      Container(
                        color: Values.of(context).c_grey_ea,
                        height: 1,
                        margin: EdgeInsets.only(top: Values.d_15, bottom: Values.d_5),
                      ),
                      Container(
                        margin: EdgeInsets.only(bottom: Values.d_10),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: <Widget>[
                            Container(
                                child: Text(
                              S.of(context).good_confirm_num,
                              style: TextStyle(
                                  color: Values.of(context).c_black_front_33,
                                  fontWeight: Values.font_Weight_normal,
                                  fontSize: Values.s_text_15),
                            )),
                            Container(
                                child: Row(
                              children: <Widget>[
                                GestureDetector(
                                  onTap: () {
                                    FocusScope.of(context).requestFocus(FocusNode());
                                    int value = int.parse(_numController.text.toString());
                                    value -= 1;
                                    handleBuyNum(value);
                                  },
                                  child: Container(
                                      width: Values.d_18,
                                      height: Values.d_18,
                                      child: Image.asset('assets/images/count_subtract.png')),
                                ),
                                Container(
                                  margin: EdgeInsets.only(left: Values.d_5, right: Values.d_5),
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
                                        borderSide: BorderSide(color: Values.c_translucent),
                                      ),
                                      focusedBorder: UnderlineInputBorder(
                                        borderSide: BorderSide(color: Values.c_translucent),
                                      ),
                                    ),
                                    onChanged: (value) {},
                                    onSubmitted: (value) {
                                      handleBuyNum(int.parse(_numController.text.toString()));
                                    },
                                    inputFormatters: [LengthLimitingTextInputFormatter(3)],
                                    keyboardType: TextInputType.number,
                                    cursorColor: Values.of(context).c_black_front_33,
                                  ),
                                ),
                                GestureDetector(
                                    onTap: () {
                                      FocusScope.of(context).requestFocus(FocusNode());
                                      int value = int.parse(_numController.text.toString());
                                      value += 1;
                                      handleBuyNum(value);
                                    },
                                    child: Container(
                                      decoration: BoxDecoration(
                                          color: Values.of(context).c_orange_background_0b,
                                          borderRadius: BorderRadius.all(Radius.circular(3))),
                                      width: Values.d_18,
                                      height: Values.d_18,
                                      child: Image.asset('assets/images/count_add.png'),
                                    ))
                              ],
                            )),
                          ],
                        ),
                      ),
                      Container(
                        margin: EdgeInsets.only(top: Values.d_10, bottom: Values.d_12),
                        child: Column(
                          children: <Widget>[
                            Row(
                              mainAxisAlignment: MainAxisAlignment.spaceBetween,
                              children: <Widget>[
                                Container(
                                  margin: EdgeInsets.only(bottom: Values.d_12),
                                  child: Text(
                                    _providerLevel.rankName +
                                        S.of(context).good_confirm_price +
                                        _providerLevel.startCount.toString() +
                                        S.of(context).good_confirm_price_end,
                                    style: TextStyle(
                                        color: Values.of(context).c_black_front_33,
                                        fontWeight: Values.font_Weight_normal,
                                        fontSize: Values.s_text_12),
                                  ),
                                ),
                                Container(
                                  margin: EdgeInsets.only(bottom: Values.d_12),
                                  child: Text(
                                    '¥' + _providerLevel.price.toStringAsFixed(2),
                                    style: TextStyle(
                                        color: Values.of(context).c_black_front_33,
                                        fontWeight: Values.font_Weight_normal,
                                        fontSize: Values.s_text_12),
                                  ),
                                ),
                              ],
                            ),
                            _providerLevel.rankNum <= 1
                                ? Container()
                                : Row(
                                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                                    children: <Widget>[
                                      Container(
                                        child: Text(
                                          _providerLevel.nextName +
                                              S.of(context).good_confirm_price +
                                              _providerLevel.nextCount.toString() +
                                              S.of(context).good_confirm_price_end,
                                          style: TextStyle(
                                              color: Values.of(context).c_orange_front_0b,
                                              fontWeight: Values.font_Weight_normal,
                                              fontSize: Values.s_text_12),
                                        ),
                                      ),
                                      Container(
                                        child: Text(
                                          '¥' + _providerLevel.nextPrice.toStringAsFixed(2),
                                          style: TextStyle(
                                              color: Values.of(context).c_orange_front_0b,
                                              fontWeight: Values.font_Weight_normal,
                                              fontSize: Values.s_text_12),
                                        ),
                                      ),
                                    ],
                                  ),
                          ],
                        ),
                      )
                    ],
                  ),
                ));
          });
    }
  }
}

/// 升级选择模块（默认不显示）
/// 1.当用户满足升级条件后，会自动显示
/// 2.当选择升级、不升级、需同步更新'商品信息模块'、'余额模块'、'总价模块'
/// 3.当已升级后，选择为不升级，弹出询问框询问，选择不升级后，需同步更新'商品信息模块'、'余额模块'、'总价模块'
class UpdateGradeWidget extends StatefulWidget {
  final Key key;

  const UpdateGradeWidget(this.key);

  @override
  _UpdateGradeWidgetState createState() => _UpdateGradeWidgetState();
}

class _UpdateGradeWidgetState extends State<UpdateGradeWidget> {
  ServiceProviderLevel _providerLevel;

  ///服务商等级信息
  bool _isUpgrade = false;

  ///是否升级
  bool _isGiveUp = false;

  ///是否放弃升级
  bool _isgetValue = false;
  bool _isShow = false;

  void showDialogCurrentLevel() {
    showDialog(
        context: context,
        child: DialogTool(
          bgColor: Values.of(context).c_white_background,
          title: S.of(context).warn_title,
          titleFontSize: Values.s_text_18,
          titleColor: Values.of(context).c_black_front_33,
          content: S.of(context).good_confirm_currentLevel_warn_1 +
              _providerLevel.nextName +
              S.of(context).good_confirm_currentLevel_warn_2 +
              _providerLevel.nextName +
              S.of(context).good_confirm_currentLevel_warn_3,
          contentColor: Values.of(context).c_black_front_33,
          leftTitle: S.of(context).good_confirm_currentLevel_button_1,
          leftColor: Values.of(context).c_orange_front_0b,
          leftBgColor: Values.of(context).c_white_background,
          rightTitle: S.of(context).good_confirm_currentLevel_button_2,
          rightColor: Values.of(context).c_white_front,
          rightBgColor: Values.of(context).c_orange_background_0b,
          onTap: (int index) {
            if (index == 0) {
            } else if (index == 1) {
              _isGiveUp = true;
              _isUpgrade = false;
              _isShow = true;
              changeGiveUp();
            }
          },
        ));
  }

  void changeLevel(bool isUpgrade) {
    setState(() {
      _isgetValue = true;
      _isUpgrade = isUpgrade;
    });
  }

  void getProviderLevel(ServiceProviderLevel providerLevel) {
    _providerLevel = providerLevel;
  }

  void changeGiveUp() {
    context.findRootAncestorStateOfType<_GoodConfirmPageState>().goodInfoKey.currentState.changeLevel(false);
  }

  void changeNextLevel() {
    context.findRootAncestorStateOfType<_GoodConfirmPageState>().goodInfoKey.currentState.changeLevel(true);
  }

  @override
  Widget build(BuildContext context) {
    if ((!_isgetValue) ||
        _providerLevel == null ||
        _providerLevel.rankNum <= 1 ||
        context.findRootAncestorStateOfType<_GoodConfirmPageState>()._isCollocation) {
      return Container();
    }
    return Container(
      decoration: BoxDecoration(
          color: Values.of(context).c_white_background, borderRadius: BorderRadius.all(Radius.circular(5))),
      margin: EdgeInsets.only(top: Values.d_12),
      padding: EdgeInsets.only(top: Values.d_12, left: Values.d_15, right: Values.d_15),
      height: Values.d_100,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Container(
            margin: EdgeInsets.only(bottom: Values.d_12),
            child: Text(
              S.of(context).good_confirm_upgrade_title +
                  '【' +
                  _providerLevel.nextName +
                  '】' +
                  S.of(context).good_confirm_upgrade_title_end,
              style: TextStyle(
                  color: Values.of(context).c_black_front_33,
                  fontWeight: Values.font_Weight_normal,
                  fontSize: Values.s_text_15),
            ),
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            mainAxisSize: MainAxisSize.max,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: <Widget>[
              Expanded(
                flex: 1,
                child: OutlineButton(
                  onPressed: () {
                    _isUpgrade = true;
                    changeNextLevel();
                  },
                  color: Values.of(context).c_white_background,
                  splashColor: Values.c_translucent,
                  highlightColor: Values.c_translucent,
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: <Widget>[
                      Text(
                        S.of(context).good_confirm_upgrade_choose_up +
                            '【' +
                            _providerLevel.nextName +
                            '】' +
                            S.of(context).good_confirm_upgrade_choose_up_end,
                        style: TextStyle(
                            fontSize: Values.s_text_12,
                            color: Values.of(context).c_black_front_33,
                            fontWeight: Values.font_Weight_normal),
                      ),
                      _isUpgrade ? Image.asset('assets/images/button_select.png') : Container(),
                    ],
                  ),
                  borderSide: new BorderSide(
                      color: _isUpgrade
                          ? Values.of(context).c_orange_background_0b
                          : Values.of(context).c_grey_background_cc),
                  highlightedBorderColor: Values.of(context).c_grey_background_cc,
                  shape: RoundedRectangleBorder(
                    borderRadius: new BorderRadius.circular(5.0),
                  ),
                ),
              ),
              SizedBox(
                width: Values.d_11,
              ),
              Expanded(
                flex: 1,
                child: OutlineButton(
                  onPressed: () {
                    if (!_isShow) {
                      showDialogCurrentLevel();
                    } else {
                      _isGiveUp = true;
                      _isUpgrade = false;
                      changeGiveUp();
                    }
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
                        S.of(context).good_confirm_upgrade_choose_current,
                        style: TextStyle(
                            fontSize: Values.s_text_12,
                            color: Values.of(context).c_black_front_33,
                            fontWeight: Values.font_Weight_normal),
                      ),
                      !_isUpgrade ? Image.asset('assets/images/button_select.png') : Container(),
                    ],
                  ),
                  borderSide: new BorderSide(
                      color: _isUpgrade
                          ? Values.of(context).c_grey_background_cc
                          : Values.of(context).c_orange_background_0b),
                  highlightedBorderColor: Values.of(context).c_orange_background_0b,
                  shape: RoundedRectangleBorder(
                    borderRadius: new BorderRadius.circular(5.0),
                  ),
                ),
              )
            ],
          )
        ],
      ),
    );
  }
}

/// 余额模块
/// 1.显示升级金额
/// 2.显示剩余金额可购买设配数量
/// 3.数据会随'数量'、'等级'变化
class BalanceInfoWidget extends StatefulWidget {
  final Key key;

  const BalanceInfoWidget(this.key);

  @override
  _BalanceInfoWidgetState createState() => _BalanceInfoWidgetState();
}

class _BalanceInfoWidgetState extends State<BalanceInfoWidget> {
  FundQueryDetail _queryDetail;

  ///余额信息
  double _balance = 0.0;

  ///余额
  int _balanceForPad = 0;

  ///余额可定量
  int _buyNum = 0;
  double _price = 0.0;

  void changeBuyNum(int num, double price) {
    Future.delayed(Duration(milliseconds: 300)).then((e) {
      _buyNum = IntUtil.getNotNullInt(num);
      _price = DoubleUtil.getNotNullInt(price);
      if (_queryDetail != null) {
        handleBalance(true);
      }
    });
  }

  void handleBalance(bool needSet) {
    double total = _buyNum * _price;
    double curBalance = _queryDetail.bal - total;
    double balance = curBalance > 0 ? curBalance : 0;
    int padNum = (balance / _price).toInt();

    if (needSet) {
      setState(() {
        _balance = balance;
        _balanceForPad = padNum;
      });
    } else {
      _balance = balance;
      _balanceForPad = padNum;
    }
  }

  @override
  Widget build(BuildContext context) {
    return StreamBuilder(
        stream: context.findRootAncestorStateOfType<_GoodConfirmPageState>()._confirmViewModel.streamFundQuery,
        builder: (BuildContext context, AsyncSnapshot<FundQuery> snapshot) {
          if (snapshot.data != null) {
            _queryDetail = snapshot.data.data;
            if (_price > 0 && _buyNum > 0) {
              handleBalance(false);
            }
          }
          return Container(
            decoration: BoxDecoration(
                color: Values.of(context).c_white_background, borderRadius: BorderRadius.all(Radius.circular(5))),
            margin: EdgeInsets.only(top: Values.d_12),
            padding: EdgeInsets.all(Values.d_15),
            height: Values.d_100,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: <Widget>[
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: <Widget>[
                    Text(
                      S.of(context).good_confirm_balance_money,
                      style: TextStyle(
                          color: Values.of(context).c_black_front_33,
                          fontWeight: Values.font_Weight_normal,
                          fontSize: Values.s_text_15),
                    ),
                    Text(
                      '¥' + _balance.toStringAsFixed(2),
                      style: TextStyle(
                          color: Values.of(context).c_black_front_33,
                          fontWeight: Values.font_Weight_normal,
                          fontSize: Values.s_text_15),
                    ),
                  ],
                ),
                Container(
                  color: Values.of(context).c_grey_ea,
                  height: 1,
                  margin: EdgeInsets.only(top: Values.d_12, bottom: Values.d_12),
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: <Widget>[
                    Text(
                      S.of(context).good_confirm_balance_facility,
                      style: TextStyle(
                          color: Values.of(context).c_black_front_33,
                          fontWeight: Values.font_Weight_normal,
                          fontSize: Values.s_text_15),
                    ),
                    Text(
                      _balanceForPad.toString(),
                      style: TextStyle(
                          color: Values.of(context).c_black_front_33,
                          fontWeight: Values.font_Weight_normal,
                          fontSize: Values.s_text_15),
                    ),
                  ],
                ),
              ],
            ),
          );
        });
  }
}

/// 升级提示信息模块
/// 1.未升级时，显示据升级还差多少设备
/// 2.升级后，显示可逐级升到最高
/// 3.当升到最高，显示同意订货协议（协议可点击查看）
class UpdateInfoWidget extends StatefulWidget {
  final Key key;

  const UpdateInfoWidget(this.key);

  @override
  _UpdateInfoWidgetState createState() => _UpdateInfoWidgetState();
}

class _UpdateInfoWidgetState extends State<UpdateInfoWidget> with PageBridge {
  ///服务商等级信息
  ServiceProviderLevel _providerLevel;

  ///购买数量
  int _buyNum = 0;

  ///是否升级
  bool _isUpgrade = false;

  bool _isAgree = false;

  void changeLevel(bool isUpgrade) {
    setState(() {
      _isUpgrade = isUpgrade;
    });
  }

  void changeBuyNum(int num, bool isUpgrade) {
    Future.delayed(Duration(milliseconds: 10)).then((e) {
      setState(() {
        _buyNum = IntUtil.getNotNullInt(num);
        _isUpgrade = isUpgrade ?? false;
      });
    });
  }

  void getProviderLevel(ServiceProviderLevel providerLevel) {
    Future.delayed(Duration(milliseconds: 10)).then((e) {
      setState(() {
        _providerLevel = providerLevel;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    if (_providerLevel == null) {
      return Container();
    }
    if (_providerLevel.nextRum == 1 &&
        (_isUpgrade || context.findRootAncestorStateOfType<_GoodConfirmPageState>()._isCollocation)) {
      return Container(
        color: Values.of(context).c_grey_background_f8,
        padding: EdgeInsets.only(bottom: Values.d_10, left: Values.d_28),
        alignment: Alignment.centerLeft,
        child: Row(
          children: <Widget>[
            Container(
              alignment: Alignment.centerLeft,
              padding: EdgeInsets.all(0),
              child: GestureDetector(
                onTap: () {
                  setState(() {
                    _isAgree = !_isAgree;
                  });
                },
                child: _isAgree
                    ? Image.asset(
                        'assets/images/button_select.png',
                      )
                    : Image.asset(
                        'assets/images/button_unSelect.png',
                      ),
              ),
            ),
            SizedBox(width: Values.d_5),
            Expanded(
              child: Text.rich(
                TextSpan(
                  text: S.of(context).good_confirm_upgrade_info,
                  style: TextStyle(
                    fontSize: Values.s_text_12,
                    color: Values.of(context).c_black_front_99,
                    fontWeight: Values.font_Weight_normal,
                  ),
                  children: [
                    TextSpan(
                        recognizer: TapGestureRecognizer()
                          ..onTap = () {
                            open(RouterName.web_view,
                                argument: WebParams(
                                    title: S.of(context).good_confirm_upgrade_protocol,
                                    url: NetworkConfig().getHostForWeb() + NET_WEB_SERVICE_AGREEMENT));
                          },
                        text: S.of(context).good_confirm_upgrade_protocol,
                        style: TextStyle(
                          color: Values.of(context).c_orange_front_0b,
                          decoration: TextDecoration.none,
                        )),
                  ],
                ),
              ),
            )
          ],
        ),
      );
    } else {
      int count = 0;
      count = IntUtil.getNotNullInt(_providerLevel.nextCount) - _buyNum;
      return Container(
          color: Values.of(context).c_grey_background_f8,
          padding: EdgeInsets.only(bottom: Values.d_10, left: Values.d_28),
          alignment: Alignment.centerLeft,
          child: Row(
            children: <Widget>[
              Image.asset('assets/images/warn_icon.png'),
              SizedBox(width: Values.d_5),
              _providerLevel.rankNum == 1
                  ? Text(
                      S.of(context).good_confirm_upgrade_up_info1 +
                          StringUtil.getNotNullString(_providerLevel.rankName) +
                          S.of(context).good_confirm_upgrade_buy_info3,
                      style: TextStyle(
                          color: Values.of(context).c_black_front_99,
                          fontWeight: Values.font_Weight_normal,
                          fontSize: Values.s_text_12),
                    )
                  : Text(
                      _isUpgrade
                          ? S.of(context).good_confirm_upgrade_up_info1 +
                              StringUtil.getNotNullString(_providerLevel.nextName) +
                              S.of(context).good_confirm_upgrade_up_info2
                          : S.of(context).good_confirm_upgrade_buy_info1 +
                              '${count > 0 ? count : 0}' +
                              S.of(context).good_confirm_upgrade_buy_info2 +
                              StringUtil.getNotNullString(_providerLevel.nextName) +
                              S.of(context).good_confirm_upgrade_buy_info3,
                      style: TextStyle(
                          color: Values.of(context).c_black_front_99,
                          fontWeight: Values.font_Weight_normal,
                          fontSize: Values.s_text_12),
                    ),
            ],
          ));
    }
  }
}

/// 总价模块
/// 显示总价、提交按钮
/// 总价会随'数量'、'等级'联动
class PriceInfoWidget extends StatefulWidget {
  final Key key;

  const PriceInfoWidget(this.key);

  @override
  _PriceInfoWidgetState createState() => _PriceInfoWidgetState();
}

class _PriceInfoWidgetState extends State<PriceInfoWidget> {
  double _total = 0.0;

  void changeBuyNum(int num, double price) {
    Future.delayed(Duration(milliseconds: 300)).then((e) {
      setState(() {
        _total = num * price;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Values.of(context).c_white_background,
      height: Values.d_50,
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: <Widget>[
          Container(
            margin: EdgeInsets.only(left: Values.d_28),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.start,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: <Widget>[
                Text(
                  S.of(context).good_confirm_total,
                  style: TextStyle(
                      color: Values.of(context).c_black_front_33,
                      fontWeight: Values.font_Weight_normal,
                      fontSize: Values.s_text_18),
                ),
                Text(
                  '￥' + _total.toStringAsFixed(2),
                  style: TextStyle(
                      color: Values.of(context).c_orange_front_0b,
                      fontWeight: Values.font_Weight_normal,
                      fontSize: Values.s_text_18),
                ),
              ],
            ),
          ),
          Container(
            height: Values.d_50,
            width: Values.d_150,
            color: Colors.yellow,
            child: FlatButton(
              onPressed: () {
                if (context
                        .findRootAncestorStateOfType<_GoodConfirmPageState>()
                        .updateInfoKey
                        .currentState
                        ._isUpgrade &&
                    context
                            .findRootAncestorStateOfType<_GoodConfirmPageState>()
                            .updateInfoKey
                            .currentState
                            ._providerLevel
                            .nextRum ==
                        1) {
                  if (context
                      .findRootAncestorStateOfType<_GoodConfirmPageState>()
                      .updateInfoKey
                      .currentState
                      ._isAgree) {
                    context.findRootAncestorStateOfType<_GoodConfirmPageState>().getPersionStatusInfo();
                  } else {
                    ProgressHUD.showText(
                        warnText: S.of(context).need_agree_title + S.of(context).good_confirm_upgrade_protocol);
                  }
                } else {
                  context.findRootAncestorStateOfType<_GoodConfirmPageState>().getPersionStatusInfo();
                }
              },
              color: Values.of(context).c_orange_background_0b,
              splashColor: Values.c_translucent,
              highlightColor: Values.c_translucent,
              child: new Text(
                S.of(context).good_confirm_submit,
                style: TextStyle(
                    fontSize: Values.s_text_16,
                    color: Values.of(context).c_white_front,
                    fontWeight: Values.font_Weight_medium),
              ),
            ),
          )
        ],
      ),
    );
  }
}
