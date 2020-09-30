import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/earnings.dart';
import 'package:flutter_ienglish_fine/src/business/pay/viewmodel/earnings_view_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

import 'earnings_detail_item_widget.dart';

class EarningsAllListPage extends StatefulWidget {
  @override
  _EarningsAllListPageState createState() => _EarningsAllListPageState();
}

class _EarningsAllListPageState extends State<EarningsAllListPage>
    with AutomaticKeepAliveClientMixin {
  EarningsViewModel _viewModel = EarningsViewModel();

  @override
  bool get wantKeepAlive => true;

  @override
  Widget build(BuildContext context) {
    super.build(context);
    return RootPageWidget(
        viewModel: _viewModel,
        task: _viewModel.getEarningsStatusData(isFirst: true,status: ""),
        body: StreamBuilder<EarningsBean>(
            stream: _viewModel.streamEarningsStatus,
            builder: (BuildContext context,
                AsyncSnapshot<EarningsBean> snapshot) {
              if (snapshot.data == null || snapshot.data?.items == null) {
                return CommonWidget.emptyWidget();
              }
              return PullToRefresh(
                child: SListView(_buildItemView, itemAction: _itemAction)
                    .build(context, snapshot.data.items),
                onRefresh: () {
                  return _viewModel.getEarningsStatusData(isFirst: false,status: "");
                },
              );
            }));
  }

  Widget _buildItemView(BuildContext context, Object itemData) {
    return EarningsDetailItemWidget(context, itemData);
  }

  void _itemAction(Widget widget, Object itemData) {}
}
