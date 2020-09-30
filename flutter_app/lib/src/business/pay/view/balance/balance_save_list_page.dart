import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/balance_detail.dart';
import 'package:flutter_ienglish_fine/src/business/pay/viewmodel/balance_detail_view_model.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'balance_detail_Item_widget.dart';

class BalanceSaveListPage extends StatefulWidget {
  @override
  _BalanceSaveListPageState createState() => _BalanceSaveListPageState();
}

class _BalanceSaveListPageState extends State<BalanceSaveListPage> with AutomaticKeepAliveClientMixin {

  BalanceDetailViewModel _viewModel = BalanceDetailViewModel();

  SimpleLoadMoreController _loadMoreController;

  List <BalanceDetailItemBean> _items = List();

  @override
  bool get wantKeepAlive => true;

  void loadMore() {
    _viewModel.getBalanceFlowsData(isFirst: false, isRefresh: false);
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
        task: _viewModel.getBalanceFlowsData(isFirst: true, isRefresh: false),
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
              _items.addAll(snapshot.data.items);
              return PullToRefresh(
                child: SListView(_buildItemView, itemAction: _itemAction,moreController: _loadMoreController).build(context, _items),
                onRefresh: () {
                  return _viewModel.getBalanceFlowsData(isFirst: false, isRefresh: false);
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