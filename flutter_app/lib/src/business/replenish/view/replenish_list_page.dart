import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_ienglish_fine/src/business/replenish/comm/interfaces.dart';
import 'package:flutter_ienglish_fine/src/business/replenish/bean/replenish_list.dart';
import 'package:flutter_ienglish_fine/src/business/replenish/viewmodel/replenish_list_view_model.dart';
import 'package:flutter_ienglish_fine/src/business/replenish/provider/replenish_list_provider.dart';
import 'package:flutter_ienglish_fine/src/business/replenish/view/dialog_replenish.dart';

class ReplenishListPage extends StatelessWidget with PageBridge {
  ReplenishListViewModel _replenishListViewModel = ReplenishListViewModel();

  ReplenishListBean _replenishListBean;

  final _replenishListProvider = ReplenishListProvider();

  SimpleLoadMoreController _loadMoreController ;

  List<ReplenishListItemBean> _itemList = List();

  void orderAction(BuildContext context, ReplenishListItemBean data) {
    showWarnDialog(context, S.of(context).replenish_dialog_make_sure,(){
      postReplenishAction(data.numberCode);
    });
  }

  void postReplenishAction(String numberCode) {
    open(RouterName.good_confirm_page,argument: {'isCombo':false,'isReplenish':true,'codes':[numberCode]});
  }

  void postReplenishAllAction(List<ReplenishListItemBean>selectList) {

    List _numberCodes = List();

    for (ReplenishListItemBean item in selectList){
      _numberCodes.add(item.numberCode);
    }

    open(RouterName.good_confirm_page,argument: {'isCombo':false,'isReplenish':true,'codes':_numberCodes});
  }

  void AccountExplain(String title,String url) {
    open(RouterName.web_view,argument:WebParams(title: title,url: url));
  }

  void showWarnDialog(BuildContext context,String content,Function() callback){
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

  void loadReplenishAction() {}

  void loadMore(){
    _replenishListViewModel.loadReplenishList(isFirst: false,isRefresh: false);
  }

  @override
  Widget build(BuildContext context) {

    _loadMoreController = SimpleLoadMoreController((){
      loadMore();
    });

    return ChangeNotifierProvider.value(
        value: _replenishListProvider,
        child: RootPageWidget(
          appBar: IsAppBar(
            title: S.of(context).replenish_list_title,
            rightWidget: FlatButton(
                onPressed: (){
                  AccountExplain(S.of(context).replenish_list_explain, NetworkConfig().getHostForWeb() +
                      NET_WEB_REPLENISH_ACCOUNT_EXPLAIN);
                },
                highlightColor: Values.c_translucent,
                splashColor: Values.c_translucent,
                child: Text(
                  S.of(context).replenish_list_explain,
                  style: TextStyle(
                      fontSize: Values.s_text_15,
                      color: Values.of(context).c_black_front_33,
                      fontWeight: Values.font_Weight_normal),
                ),
            ),
          ),
          viewModel: _replenishListViewModel,
          task: _replenishListViewModel.loadReplenishList(isFirst: true,isRefresh: true),
          body: Scaffold(
            body: StreamBuilder<ReplenishListBean>(
                stream: _replenishListViewModel.streamReplenishList,
                builder: (BuildContext context,
                    AsyncSnapshot<ReplenishListBean> snapshot) {
                  if (snapshot.data == null || snapshot.data?.items == null) {
                    return CommonWidget.emptyWidget();
                  }
                  _replenishListBean = snapshot.data;
                  if(_replenishListViewModel.pageIndex == 1){
                    _itemList.clear();
                  }
                  _itemList.addAll(snapshot.data.items);
                  _loadMoreController.hasMore = _replenishListViewModel.hasMoreList;

                  Provider.of<ReplenishListProvider>(context, listen: false).initSelectList(_itemList);
                  return Column(
                    children: <Widget>[
                      ListWidget(),
                      Container(
                        child: BottomView(),
                      )
                    ],
                  );
                }),
          ),
        ));
  }
}

class ListWidget extends StatelessWidget with PageBridge {

  Widget _buildItemView(BuildContext context, Object itemData) {

    ReplenishListItemBean data = itemData as ReplenishListItemBean;

    bool _isSelect = Provider.of<ReplenishListProvider>(context, listen: false).isSelect(data);

    Widget _senderInfo = Container(
      decoration: new BoxDecoration(
          border: new Border(
              bottom:
              BorderSide(color: Values.of(context).c_grey_ea, width: 1.0))),
      padding: EdgeInsets.only(bottom: Values.d_15),
      margin: EdgeInsets.only(left: Values.d_50),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: <Widget>[
          Text(S.of(context).replenish_list_orderer + '：' + data.username,
              style: TextStyle(
                  fontSize: Values.s_text_14,
                  color: Values.of(context).c_black_front_33)),
          Text(StringUtil.getNotNullString(data.repairStatus),
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

    Widget _selectButton = Container(
        height: Values.d_36,
        width: Values.d_36,
        margin: EdgeInsets.only(right: Values.d_15),
        child: IconButton(
          icon: Image.asset(_isSelect
              ? 'assets/images/button_select.png'
              : 'assets/images/button_unSelect.png'),
          onPressed: () {
            if (_isSelect) {
              Provider.of<ReplenishListProvider>(context, listen: false)
                  .reduce(data);
            } else {
              Provider.of<ReplenishListProvider>(context, listen: false)
                  .increment(data);
            }
          },
          color: Values.of(context).c_orange_background_0b,
          disabledColor: Values.of(context).c_grey_background_cc,
          highlightColor: Values.c_translucent,
          splashColor: Values.c_translucent,
//        Image.asset('assets/images/button_select.png'),
        ));

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

    Widget _sendButton = Container(
      height: Values.d_30,
      child: FlatButton(
          onPressed: () {
            context.findAncestorWidgetOfExactType<ReplenishListPage>().orderAction(context, data);
          },
          color: Values.of(context).c_orange_background_0b,
          disabledColor: Values.of(context).c_grey_background_cc,
          highlightColor: Values.c_translucent,
          splashColor: Values.c_translucent,
          shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(Values.d_30)),
          child: Text(
            S.of(context).replenish_list_item_button,
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
              crossAxisAlignment: CrossAxisAlignment.center,
              children: <Widget>[
                Container(
                  child: _selectButton,
                ),
                Container(
                    alignment: Alignment.center,
                    margin: EdgeInsets.only(
                        right: Values.d_10, bottom: Values.d_15),
                    child: _image),
                Expanded(
                  child: Container(
                    child: Column(
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
          Container(
            margin: EdgeInsets.only(left: Values.d_50),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: <Widget>[
                _warn,
                _total,
              ],
            ),
          ),
          SizedBox(height: Values.d_20),
          Container(
              margin: EdgeInsets.only(left: Values.d_50),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: <Widget>[
                  data.ifReject
                      ? Row(
                    children: <Widget>[
                      Text(
                        S.of(context).replenish_list_count_down,
                        style: TextStyle(
                            color: Values.of(context).c_black_front_33,
                            fontSize: Values.s_text_14),
                      ),
                      Container(
                        child: TimeCountDownSinceDate(
                          bgColor: Values.of(context).c_white_background,
                          finishTitleColor:
                          Values.of(context).c_red_front_68,
                          fontSize: Values.s_text_14,
                          finishTitle: '00:00',
                          countDownTitleColor:
                          Values.of(context).c_red_front_68,
                          finishDate: data.advanceTime,
                          finishCallBack: () {},
                        ),
                      ),
                    ],
                  )
                      : Container(),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.end,
                    children: <Widget>[_sendButton],
                  ),
                ],
              )),
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {

    List _itemList = context.findAncestorWidgetOfExactType<ReplenishListPage>()._itemList;

    List<ReplenishListItemBean>_selectList = Provider.of<ReplenishListProvider>(context, listen: true).selectList;

    void _itemAction(Widget widget, Object itemData) {
      ReplenishListItemBean data = itemData as ReplenishListItemBean;
      open(RouterName.replenish_detail,
          argument: {'numberCode': data.numberCode, 'itemInfo': data}).then((value){
        if(value){
          context.findAncestorWidgetOfExactType<ReplenishListPage>()._replenishListViewModel.loadReplenishList(
              isFirst: false,isRefresh: true);
        }
      });
    }

    return Expanded(child: PullToRefresh(
      child:
      SListView(_buildItemView, itemAction: _itemAction,moreController: context.findAncestorWidgetOfExactType<ReplenishListPage>()._loadMoreController).build(context, _itemList),
      onRefresh: () {
        return context.findAncestorWidgetOfExactType<ReplenishListPage>()._replenishListViewModel.loadReplenishList(
            isFirst: false,isRefresh: true);
      },
    ));
  }
}


class BottomView extends StatelessWidget with PageBridge {
  void showReplenishDialog(BuildContext context,List<ReplenishListItemBean>selectList) {
    showDialog(
        context: context,
        child: ReplenishDialog(
          title: S.of(context).warn_title,
          titleFontSize: Values.s_text_18,
          titleColor: Values.c_black_b1,
          content: S.of(context).replenish_dialog_content,
          contentColor: Values.of(context).c_black_front_33,
          leftTitle: S.of(context).warn_cancel,
          leftColor: Values.of(context).c_orange_front_0b,
          leftBgColor: Values.of(context).c_white_background,
          rightTitle: S.of(context).warn_sure,
          rightColor: Values.of(context).c_white_front,
          rightBgColor: Values.of(context).c_orange_background_0b,
          onTap: (int index) {
            if (index == 1) {
                context.findAncestorWidgetOfExactType<ReplenishListPage>().postReplenishAllAction(selectList);
            } else if (index == -1) {
              ProgressHUD.showText(warnText: S.of(context).need_agree_title+S.of(context).warn_protocol_title);
            }
          },
          linkOnTap: (String title, String urlPath) {
            open(RouterName.web_view,argument: WebParams(title: title,url: NetworkConfig().getHostForWeb() + urlPath));
          },
        ));
  }

  void showErrorDialog(BuildContext context,String error) {
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
          onTap: (int index) {

          },
        ));
  }

  @override
  Widget build(BuildContext context) {
    List _list = context
        .findAncestorWidgetOfExactType<ReplenishListPage>()
        ._itemList;

    List<ReplenishListItemBean>_selectList = Provider.of<ReplenishListProvider>(context, listen: true).selectList;
    int _selectCount =
        Provider.of<ReplenishListProvider>(context, listen: true).count;
    bool _isSelectAll = _list.length == _selectCount;
    return Container(
      color: Values.of(context).c_white_background,
      child: SafeArea(
          child: Container(
              height: Values.d_50,
              child: Row(
                children: <Widget>[
                  Container(
                    padding:
                        EdgeInsets.only(left: Values.d_10, right: Values.d_10),
                    child: FlatButton(
                        onPressed: () {
                          if (_isSelectAll) {
                            Provider.of<ReplenishListProvider>(context,
                                    listen: false)
                                .unSelectAll();
                          } else {
                            Provider.of<ReplenishListProvider>(context,
                                    listen: false)
                                .selectAll(context
                                    .findAncestorWidgetOfExactType<
                                        ReplenishListPage>()
                                    ._itemList);
                          }
                        },
                        highlightColor: Values.c_translucent,
                        splashColor: Values.c_translucent,
                        child: Row(
                          children: <Widget>[
                            _isSelectAll
                                ? Image.asset('assets/images/button_select.png')
                                : Image.asset(
                                    'assets/images/button_unSelect.png'),
                            SizedBox(
                              width: Values.d_10,
                            ),
                            Text(
                              S.of(context).replenish_list_select_all,
                              style: TextStyle(
                                  color: Values.of(context).c_black_front_33,
                                  fontSize: Values.d_18,
                                  fontWeight: Values.font_Weight_normal),
                            )
                          ],
                        )),
                  ),
                  Expanded(
                    child: Container(
                      color: Values.of(context).c_orange_background_0b,
                      child: FlatButton(
                          onPressed: () {
                            if (_selectCount > 0) {
                              showReplenishDialog(context,_selectList);
                            } else {
                              showErrorDialog(context, S.of(context).replenish_dialog_no_select);
                            }
                          },
                          highlightColor: Values.c_translucent,
                          splashColor: Values.c_translucent,
                          child: Text(
                            S
                                .of(context)
                                .replenish_list_select_all_button(_selectCount),
                            style: TextStyle(
                                fontSize: Values.s_text_16,
                                color: Values.of(context).c_white_front),
                          )),
                    ),
                  )
                ],
              ))),
    );
  }
}
