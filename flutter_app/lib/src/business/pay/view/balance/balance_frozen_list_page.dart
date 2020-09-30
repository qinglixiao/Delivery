import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/balance_detail.dart';
import 'package:flutter_ienglish_fine/src/business/pay/viewmodel/balance_detail_view_model.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'balance_detail_Item_widget.dart';

class BalanceFrozenListPage extends StatefulWidget {
  @override
  _BalanceFrozenListPageState createState() => _BalanceFrozenListPageState();
}

class _BalanceFrozenListPageState extends State<BalanceFrozenListPage> with AutomaticKeepAliveClientMixin {

  BalanceDetailViewModel _viewModel = BalanceDetailViewModel();
  List <BalanceDetailItemBean> _items = List();
  SimpleLoadMoreController _loadMoreController;
  @override
  bool get wantKeepAlive => true;

  void loadMore() {
    _viewModel.getBalanceDetail(isFirst: false, isRefresh: false);
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
        task: _viewModel.getBalanceDetail(isFirst: true,isRefresh: true),
        body: StreamBuilder<BalanceDetailBean>(
            stream: _viewModel.streamFlows,
            builder:
                (BuildContext context, AsyncSnapshot<BalanceDetailBean> snapshot) {
              if (snapshot.data == null || snapshot.data?.items == null) {
                return CommonWidget.emptyWidget();
              }
              if(_viewModel.pageIndex == 1){
                _items.clear();
              }
              for(BalanceDetailItemBean itemBean in snapshot.data.items){
                if(itemBean.transType =='收入'){
                  _items.add(itemBean);
                }
              }
              _loadMoreController.hasMore = _viewModel.hasMoreList;
              return PullToRefresh(
                child: SListView(_buildItemView, itemAction: _itemAction,moreController: _loadMoreController).build(context, _items),
                onRefresh: () {
                  return _viewModel.getBalanceDetail(isFirst: false,isRefresh: true);
                },
              );
            }));
  }

  Widget _buildItemView(BuildContext context, Object itemData) {
    return BalanceDetailItemWidget(context, itemData);
  }

  void _itemAction(Widget widget, Object itemData){
  }
}