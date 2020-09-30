import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/src/business/replenish/bean/replenish_list.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_ienglish_fine/src/business/mine/bean/mine_sell_list.dart';
import 'package:flutter_ienglish_fine/src/business/mine/viewmodel/mine_sell_list_view_model.dart';
import 'mine_sell_list_item_widget.dart';

class SellReplenishListPage extends StatefulWidget {
  @override
  _SellReplenishListPageState createState() => _SellReplenishListPageState();
}

class _SellReplenishListPageState extends State<SellReplenishListPage> with PageBridge, AutomaticKeepAliveClientMixin {

  MineSellListViewModel _viewModel = MineSellListViewModel();

  SimpleLoadMoreController _loadMoreController;
  List <MineSellListItem> _items = List();

  @override
  bool get wantKeepAlive => true;

  void loadMore() {
    _viewModel.loadWaitReplenishyList(isFirst: false, isRefresh: false);
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
        task: _viewModel.loadWaitReplenishyList(isFirst: true,isRefresh: true),
        body: StreamBuilder<MineSellList>(
            stream: _viewModel.streamWaitReplenishList,
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
                child: SListView(_buildItemView, itemAction: _itemAction).build(context, snapshot.data.items),
                onRefresh: () {
                  return _viewModel.loadWaitReplenishyList(isFirst: false,isRefresh: true);
                },
              );
            }));
  }

  Widget _buildItemView(BuildContext context, Object itemData) {
    return SellListItemWidget(context, itemData, (int type){
      if(type == 1){
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

}