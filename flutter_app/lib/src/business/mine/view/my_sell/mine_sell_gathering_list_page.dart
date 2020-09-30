import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/src/business/replenish/bean/replenish_list.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_ienglish_fine/src/business/mine/bean/mine_sell_list.dart';
import 'package:flutter_ienglish_fine/src/business/mine/viewmodel/mine_sell_list_view_model.dart';
import 'mine_sell_list_item_widget.dart';

class SellGatheringListPage extends StatefulWidget {
  @override
  _SellGatheringListPageState createState() => _SellGatheringListPageState();
}

class _SellGatheringListPageState extends State<SellGatheringListPage> with PageBridge, AutomaticKeepAliveClientMixin {

  MineSellListViewModel _viewModel = MineSellListViewModel();

  SimpleLoadMoreController _loadMoreController;
  List <MineSellListItem> _items = List();

  @override
  bool get wantKeepAlive => true;

  void loadMore() {
    _viewModel.loadWaitGatheringList(isFirst: false, isRefresh: false);
  }

  @override
  Widget build(BuildContext context) {
    super.build(context);
    if (_loadMoreController == null) {
      _loadMoreController = SimpleLoadMoreController(() {
        loadMore();
      });
    }
    return RootPageWidget(
        viewModel: _viewModel,
        task: _viewModel.loadWaitGatheringList(isFirst: true,isRefresh: true),
        body: StreamBuilder<MineSellList>(
            stream: _viewModel.streamFinishList,
            builder:
                (BuildContext context, AsyncSnapshot<MineSellList> snapshot) {
              if (snapshot.data == null || snapshot.data?.items == null) {
                return CommonWidget.emptyWidget();
              }
              if(_viewModel.pageIndex == 1){
                _items.clear();
              }
              _items.addAll(snapshot.data.items);

              _loadMoreController.hasMore = _viewModel.hasMoreList;

              return PullToRefresh(
                child: SListView(_buildItemView, itemAction: _itemAction,moreController: _loadMoreController).build(context,_items),
                onRefresh: () {
                  return _viewModel.loadWaitGatheringList(isFirst: false,isRefresh: true);
                },
              );
            }));
  }

  Widget _buildItemView(BuildContext context, Object itemData) {
    return SellListItemWidget(context, itemData, (int type){
      if(type == 1){
        makeSureAction(itemData);
      }
      else{
        openDetail(itemData);
      }
    });
  }

  void _itemAction(Widget widget, Object itemData){
    openDetail(itemData);
  }

  void openDetail(Object itemData){
    MineSellListItem data = itemData as MineSellListItem;
    open(RouterName.dispatch_detail, argument: {'orderId': data.numberCode});
  }

  void makeSureAction(Object itemData){
    showWarnDialog((){
      loadMakSure(itemData);
    });
  }

  void loadMakSure(Object itemData){
    ProgressHUD.showLoading();
    MineSellListItem data = itemData as MineSellListItem;
    _viewModel.loadAffirmPay(numberCode: data.numberCode).then((value){
      ProgressHUD.hiddenHUD();
      if(value.isSuccess){
        _viewModel.loadAllList(isFirst: false);
      }
    });
  }

  void showWarnDialog(Function callback) {
    showDialog(
        context: context,
        child: DialogTool(
          bgColor: Values.of(context).c_white_background,
          title: S.of(context).warn_title,
          titleFontSize: Values.s_text_18,
          titleColor: Values.of(context).c_black_front_33,
          content: S.of(context).confirm_receipt_warn_title,
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

}