
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_ienglish_fine/src/business/replenish/bean/replenish_list.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_ienglish_fine/src/business/replenish/bean/replenish_detail.dart';
import 'package:flutter_ienglish_fine/src/business/replenish/viewmodel/replenish_detail_view_model.dart';

class ReplenishDetailPage extends StatefulWidget {
  @override
  _ReplenishDetailPageState createState() => _ReplenishDetailPageState();
}

class _ReplenishDetailPageState extends State<ReplenishDetailPage>
    with PageBridge {
  String _numberCode;
  ReplenishDetailBean _detailBean;
  ReplenishListItemBean _itemBean;
  ReplenishDetailViewModel _replenishDetailViewModel =
      ReplenishDetailViewModel();

  void orderAction(){
    if(_itemBean.ifCostHigh){
      showWarnDialog(S.of(context).replenish_detail_costhigh_warn,(){
        loadReplenishOrder();
      });
    }
    else {
      showWarnDialog(S.of(context).replenish_dialog_make_sure,(){
        loadReplenishOrder();
      });
    }
  }

  void rejectAction(){
    showWarnDialog(S.of(context).replenish_detail_reject_warn,(){
      loadRejectOrder();
    });
  }

  void loadRejectOrder(){
    ProgressHUD.showLoading();
    _replenishDetailViewModel.loadRejectOrder(_itemBean.numberCode, (statusInfo){
      ProgressHUD.hiddenHUD();
      if(statusInfo.error_code == '1'){
        showDialogInfo(S.of(context).replenish_list_reject_success,(){
          pop(data: true);
        });
      }
      else{
        showDialogInfo(statusInfo.message??S.of(context).warn_fail,(){
        });
      }
    });
  }

  void loadReplenishOrder(){
    open(RouterName.good_confirm_page,argument: {'isCombo':false,'isReplenish':true,'codes':[_numberCode]});
  }

  void showWarnDialog(String content,Function() callback){
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
            if(index == 1){
              callback();
            }
          },
        ));
  }

  void showDialogInfo(String message ,Function()callback) {
    showDialog(
        context: context,
        child: DialogTool(
          bgColor: Values.of(context).c_white_background,
          title: S.of(context).warn_title,
          titleFontSize: Values.s_text_18,
          titleColor: Values.of(context).c_black_front_33,
          content: message,
          middleTitle: S.of(context).warn_sure,
          middleColor: Values.of(context).c_white_front,
          middleBgColor: Values.of(context).c_orange_front_0b,
          contentColor: Values.of(context).c_black_front_33,
          onTap: (int index) {
            callback();
          },
        ));
  }

  Widget OrderHeaderWidget() {
    final size = MediaQuery.of(context).size;
    final width = size.width;
    return Container(
      child: Stack(
        children: <Widget>[
          Container(
            width: width,
            child: Image.asset('assets/images/replenish_header.png',
                fit: BoxFit.fill),
          ),
          Container(
            margin: EdgeInsets.only(left: Values.d_44, top: Values.d_36),
            child: Column(
              children: <Widget>[
                Container(
                  child: Row(
                    crossAxisAlignment: CrossAxisAlignment.end,
                    children: <Widget>[
                      Text(_itemBean.repairStatus,
                          style: TextStyle(
                              color: Values.of(context).c_white_front,
                              fontWeight: Values.font_Weight_medium,
                              fontSize: Values.s_text_20)),
                      Container(
                        margin: EdgeInsets.only(left: Values.d_10),
                        padding: EdgeInsets.only(bottom: Values.d_2),
                        child: Text(
                            S
                                .of(context)
                                .replenish_detail_count_info(_detailBean.count),
                            style: TextStyle(
                                color: Values.of(context).c_grey_front_cc,
                                fontWeight: Values.font_Weight_normal,
                                fontSize: Values.s_text_12)),
                      )
                    ],
                  ),
                ),
                (_itemBean.advanceTime>0 && _itemBean.repairStatus=='待补单')?
                Container(
                  margin: EdgeInsets.only(top: Values.d_8),
                  child: Row(
                    children: <Widget>[
                      Container(
                        padding: EdgeInsets.only(top: Values.d_2),
                        child: TimeCountDownSinceDate(
                          bgColor: Values.of(context).c_white_background,
                          finishTitleColor:
                          Values.of(context).c_red_front_68,
                          fontSize: Values.s_text_12,
                          finishTitle: '00:00',
                          countDownTitleColor:
                          Values.of(context).c_red_front_68,
                          finishDate: _itemBean.advanceTime,
                          separateType: true,
                          separateTitleColor: Values.of(context).c_grey_front_cc,
                          finishCallBack: () {
                            setState(() {});
                          },
                        ),
                      ),
                      SizedBox(width: Values.d_5),
                      Text(S.of(context).replenish_detail_countdown_title,
                          style: TextStyle(
                              color: Values.of(context).c_grey_front_cc,
                              fontWeight: Values.font_Weight_medium,
                              fontSize: Values.s_text_12)),
                    ],
                  ),
                ):Container()
              ],
            ),
          )
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
        bottom: Values.d_12,
        left: Values.d_12,
        right: Values.d_12,
      ),
      padding: EdgeInsets.all(Values.d_15),
      child: Column(
        children: <Widget>[
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Container(
                margin: EdgeInsets.only(right: Values.d_8),
                width: Values.d_88,
                height: Values.d_88,
                child: CachedNetworkImage(
                  imageUrl: _detailBean.imgUrl,
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
                    height: Values.d_88,
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: <Widget>[
                        Container(
                          child: Text(
                            _detailBean.title,
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
                                  _detailBean.price.toString() +
                                  S.of(context).dispatch_detail_price_unit,
                              style: TextStyle(
                                  color: Values.of(context).c_black_front_33,
                                  fontWeight: Values.font_Weight_medium,
                                  fontSize: Values.s_text_14),
                            ),
                            Text(
                              'X' + _detailBean.count.toString(),
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
          SizedBox(height: Values.d_20),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: <Widget>[
              Container(
                child: Text(
                  _itemBean.ifCostHigh
                      ? S.of(context).dispatch_list_cost_high
                      : '',
                  overflow: TextOverflow.ellipsis,
                  style: TextStyle(
                      fontSize: Values.s_text_12,
                      color: Values.of(context).c_red_front_68),
                ),
              ),
              Container(
                child: Row(
                  children: <Widget>[
                    _itemBean.ifReject?Container(
                        height: Values.d_30,
                        child: OutlineButton(
                          onPressed: () {
                            rejectAction();
                          },
                          color: Values.of(context).c_white_background,
                          splashColor: Values.c_translucent,
                          highlightColor: Values.c_translucent,
                          child: Text(
                            S.of(context).replenish_detail_reject_button,
                            style: TextStyle(
                                fontSize: Values.s_text_14,
                                color: Values.of(context).c_black_front_99,
                                fontWeight: Values.font_Weight_normal),
                          ),
                          borderSide: new BorderSide(
                              color: Values.of(context).c_black_front_99),
                          highlightedBorderColor:
                              Values.of(context).c_black_front_99,
                          shape: RoundedRectangleBorder(
                            borderRadius:
                                new BorderRadius.circular(Values.d_30),
                          ),
                        )):Container(),
                    SizedBox(width: Values.d_10,),
                    Container(
                      height: Values.d_30,
                      child: FlatButton(
                          onPressed: () {
                            orderAction();
                          },
                          color: Values.of(context).c_orange_background_0b,
                          disabledColor:
                              Values.of(context).c_grey_background_cc,
                          highlightColor: Values.c_translucent,
                          splashColor: Values.c_translucent,
                          shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(Values.d_30)),
                          child: Text(
                            S.of(context).replenish_detail_replenish_button,
                            style: TextStyle(
                                fontSize: Values.s_text_14,
                                color: Values.of(context).c_white_front),
                          )),
                    )
                  ],
                ),
              )
            ],
          ),
        ],
      ),
    );
  }

  Widget OrderInfoWidget(){
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
                S.of(context).dispatch_detail_order_num + _itemBean.numberCode,
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
                S.of(context).dispatch_detail_order_person + _itemBean.username,
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
                S.of(context).dispatch_detail_order_time + _itemBean.createTime,
                style: TextStyle(
                    color: Values.of(context).c_black_front_99,
                    fontWeight: Values.font_Weight_normal,
                    fontSize: Values.s_text_12),
              ),
            ],
          ),
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    getInitArg(context).then((value) {
      _numberCode = value['numberCode'];
      _itemBean = value['itemInfo'];
      _replenishDetailViewModel.loadReplenishDetail(
          isFirst: true, numberCode: _numberCode);
    });
    return RootPageWidget(
      appBar: IsAppBar(
        title: S.of(context).replenish_detail_title,
      ),
      viewModel: _replenishDetailViewModel,
      body: Scaffold(
        body: StreamBuilder<ReplenishDetailBean>(
            stream: _replenishDetailViewModel.streamReplenishDetail,
            builder: (BuildContext context,
                AsyncSnapshot<ReplenishDetailBean> snapshot) {
              if (snapshot.data == null) {
                return CommonWidget.emptyWidget();
              }
              _detailBean = snapshot.data;
              return Column(
                children: <Widget>[OrderHeaderWidget(), GoodInfoWidget(),OrderInfoWidget()],
              );
            }),
      ),
    );
  }
}
