import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:fluttermodule/generated/l10n.dart';
import 'package:fluttermodule/src/business/budget/bean/good.dart';
import 'package:fluttermodule/src/business/budget/viewmodel/good_list_viewmodel.dart';
import 'package:fluttermodule/src/widgets/comm_views.dart';

class GoodListPage extends StatefulWidget with UIWrap {
  @override
  State<StatefulWidget> createState() {
    return _GoodListState();
  }
}

class _GoodListState extends State<GoodListPage> with PageBridge {
  GoodListViewModel _goodListViewModel;
  SimpleLoadMoreController loadMore;

  @override
  void initState() {
    super.initState();
    _goodListViewModel = GoodListViewModel();
    loadMore = SimpleLoadMoreController(() {
      loadData(refresh: false);
    });
  }

  Widget _buildItem(BuildContext context, Object item) {
    GoodBean v = item as GoodBean;

    return Row(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Padding(
          padding: EdgeInsets.only(left: 12),
          child: CachedNetworkImage(
            imageUrl: v.imgUrl,
            width: 100,
            height: 100,
          ),
        ),
        Column(
          children: <Widget>[Text(v.title)],
        )
      ],
    );
  }

  _itemClick(Widget widget, Object itemData) {}

  Future loadData({refresh = true}) async {
    return _goodListViewModel
        .load(refresh)
        .then((value) => loadMore.hasMore = value);
  }

  @override
  Widget build(BuildContext context) {
    getInitArg(context)
        .then((value) => {Logger.print("init args ${value.toString()}")});

    return RootPageWidget(
      viewModel: _goodListViewModel,
      appBar: IsAppBar(
        title: S.of(context).order_good,
      ),
      body: StreamBuilder<List<GoodBean>>(
          stream: _goodListViewModel.streamGood,
          builder: (BuildContext context, AsyncSnapshot<List<GoodBean>> state) {
            if (state.data == null) {
              return EmptyWidget();
            }
            return PullToRefresh(
              child: SListView(
                _buildItem,
                itemAction: _itemClick,
                moreController: loadMore,
              ).build(context, state.data),
              onRefresh: () {
                return loadData();
              },
            );
          }),
    );
  }
}
