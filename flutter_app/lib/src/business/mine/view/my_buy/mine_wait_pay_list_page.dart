import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/src/business/budget/bean/good_confirm.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_ienglish_fine/src/business/mine/bean/mine_buy_list.dart';
import 'package:flutter_ienglish_fine/src/business/mine/viewmodel/mine_buy_list_view_model.dart';
import 'package:flutter_ienglish_fine/src/business/mine/view/my_buy/mine_buy_list_item_widget.dart';

import 'dialog_buy_order.dart';

class WaitPayListPage extends StatefulWidget {
  @override
  _WaitPayListPageState createState() => _WaitPayListPageState();
}

class _WaitPayListPageState extends State<WaitPayListPage> with PageBridge, AutomaticKeepAliveClientMixin {

  MineBuyListViewModel _viewModel = MineBuyListViewModel();

  SimpleLoadMoreController _loadMoreController;
  List <MineBuyListItem> _items = List();

  @override
  bool get wantKeepAlive => true;

  void loadMore() {
    _viewModel.loadWaitPayList(isFirst: false, isRefresh: false);
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
        task: _viewModel.loadWaitPayList(isFirst: true,isRefresh: true),
        body: StreamBuilder<MineBuyList>(
            stream: _viewModel.streamWaitPayList,
            builder:
                (BuildContext context, AsyncSnapshot<MineBuyList> snapshot) {
              if (snapshot.data == null || snapshot.data?.items == null) {
                return CommonWidget.emptyWidget();
              }
              if(_viewModel.pageIndex == 1){
                _items.clear();
              }
              _items.addAll(snapshot.data.items);

              _loadMoreController.hasMore = _viewModel.hasMoreList;
              return PullToRefresh(
                child: SListView(_buildItemView, itemAction: _itemAction,moreController: _loadMoreController)
                    .build(context, _items),
                onRefresh: () {
                  return _viewModel.loadWaitPayList(isFirst: false,isRefresh: true);
                },
              );
            }));
  }

  Widget _buildItemView(BuildContext context, Object itemData) {
    return BuyListItemWidget(context, itemData, (int type){
      MineBuyListItem data = itemData as MineBuyListItem;
      if(type==0){
        BuyCollocationInfo buyCollocationInfo = BuyCollocationInfo();
        buyCollocationInfo.numberCode = data.numberCode;
        buyCollocationInfo.numberDesc = data.numberDesc;
        buyCollocationInfo.payDesc = data.payDesc;
        buyCollocationInfo.payFee = data.paymentFee;
        open(RouterName.pay_affirm_page, argument: buyCollocationInfo);

      }
      else if(type==1){
        _itemAction(null, itemData);
      }
      else if(type==2){
        showCancelDialog(data.numberCode);
      }
    });
  }

  void _itemAction(Widget widget, Object itemData){
    MineBuyListItem data = itemData as MineBuyListItem;
    if(data.status == S.of(context).order_status_6||data.status == S.of(context).order_status_4){
      open(RouterName.my_buy_Detail,
          argument: {'orderId': data.numberCode}).then((value){
        if(value){
          _viewModel.loadAllList(isFirst: false,isRefresh: true);
        }
      });
    }
    else{
      open(RouterName.receiving_detail,
          argument: {'orderId': data.numberCode});
    }
  }

  void cancelAction(String numberCode,String message) {
    ProgressHUD.showLoading();
    _viewModel.loadCancelOrder(numberCode, message).then((value){
      ProgressHUD.hiddenHUD();
      _viewModel.loadAllList(isFirst: false,isRefresh: true);
    });
  }

  void showCancelDialog(String numberCode) {
    showModalBottomSheet(
        context: context,
        builder: (BuildContext context) {
          return DialogBuyOrder(onTap: (String message) {
            cancelAction(numberCode,message);
          });
        });
  }

}