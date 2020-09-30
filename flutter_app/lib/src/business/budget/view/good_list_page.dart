import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/src/business/budget/bean/good.dart';
import 'package:flutter_ienglish_fine/src/business/budget/viewmodel/good_list_view_model.dart';
import 'package:flutter_ienglish_fine/src/comm/event/index_page_event.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_ienglish_fine/src/main/index_tab.dart';
import 'package:flutter_lib/flutter_lib.dart';

class GoodListPage extends StatefulWidget with UIWrap {
  @override
  State<StatefulWidget> createState() {
    return _GoodListState();
  }
}

class _GoodListState extends State<GoodListPage> with PageBridge {
  GoodListViewModel _goodListViewModel;

  @override
  void initState() {
    super.initState();
    _goodListViewModel = GoodListViewModel();
  }

  Widget _buildItemView(BuildContext context, Object itemData) {
    GoodItem data = itemData as GoodItem;
    Widget _image = CachedNetworkImage(
      width: 100,
      height: 100,
      imageUrl: data?.coverImgUrl ?? "",
    );

    Widget _title = Text(
      StringUtil.getNotNullString(data?.title),
      overflow: TextOverflow.ellipsis,
      style: TextStyle(
          fontSize: Values.s_text_15,
          fontWeight: Values.font_Weight_medium,
          color: Values.of(context).c_black_front_33),
    );

    Widget _subTitle = Text(
      StringUtil.getNotNullString(data.relayDesc),
      overflow: TextOverflow.ellipsis,
      style: TextStyle(
          fontSize: Values.s_text_14,
          fontWeight: Values.font_Weight_normal,
          color: Values.of(context).c_black_front_66),
    );

    Widget _banNumber = Text(
      'X ' + data.quantity.toString(),
      style: TextStyle(fontSize: Values.s_text_14, color: Values.of(context).c_black_front_66),
    );

    Widget _price = Text.rich(TextSpan(children: [
      TextSpan(
          text: "${S.of(context).total_price} ",
          style: TextStyle(fontSize: Values.s_text_12, color: Values.of(context).c_black_front_66)),
      TextSpan(
          text: "¥",
          style: TextStyle(
              fontSize: Values.s_text_12,
              color: Values.of(context).c_red_front_68,
              fontWeight: Values.font_Weight_medium)),
      TextSpan(
        text: "${data.totalPrice.toStringAsFixed(2)}",
        style: TextStyle(
            fontSize: Values.s_text_16,
            color: Values.of(context).c_red_front_68,
            fontWeight: Values.font_Weight_medium),
      ),
    ]));

    Widget _order = Container(
      height: Values.d_28,
      width: Values.d_80,
      child: FlatButton(
          onPressed: () {
            orderAction(data);
          },
          color: Values.of(context).c_orange_background_0b,
          disabledColor: Values.of(context).c_grey_background_cc,
          shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20.0)),
          child: Text(
            S.of(context).go_order,
            style: TextStyle(
                fontSize: Values.s_text_14,
                fontWeight: Values.font_Weight_normal,
                color: Values.of(context).c_white_front),
          )),
    );

    return Container(
      padding: EdgeInsets.all(Values.d_12),
      margin: EdgeInsets.only(
        left: Values.d_10,
        top: Values.d_10,
        right: Values.d_10,
      ),
      decoration:
          BoxDecoration(borderRadius: BorderRadius.circular(Values.d_5), color: Theme.of(context).backgroundColor),
      child: Row(
        children: <Widget>[
          Container(margin: EdgeInsets.only(right: Values.d_10), child: _image),
          Expanded(
            child: Container(
              height: 100,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: <Widget>[
                  _title,
                  SizedBox(height: Values.d_5),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: <Widget>[
                      _subTitle,
                      _banNumber,
                    ],
                  ),
                  Spacer(
                    flex: 1,
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: <Widget>[
                      _price,
                      Spacer(
                        flex: 1,
                      ),
                      _order,
                    ],
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }

  void _itemAction(Widget widget, Object itemData) {
    GoodItem data = itemData as GoodItem;
    open(RouterName.good_detail, argument: {"collocationId": data.id});
  }

  void orderAction(GoodItem data) {
    open(RouterName.good_confirm_page, argument: {'id': data.id.toString(), 'isCombo': !data.ifPlain});
  }

  @override
  Widget build(BuildContext context) {
    return RootPageWidget(
      appBar: IsAppBar(
        title: S.of(context).order_good,
        titleColor: Values.of(context).c_white_front,
        color: [Values.of(context).c_blue_background_light, Values.of(context).c_blue_background_dark],
      ),
      viewModel: _goodListViewModel,
      feedback: () {
        _goodListViewModel.loadGoods(isFirst: false);
      },
      task: _goodListViewModel.loadGoods(isFirst: true),
      body: StreamBuilder<Good>(
        stream: _goodListViewModel.streamGood,
        builder: (BuildContext context, AsyncSnapshot<Good> snapshot) {
          print("build");
          print("data = ${snapshot.data}");
          if (snapshot.data == null) {
            return CommonWidget.noPreparedWidget();
          }
          print("${snapshot.data}");
          return PullToRefresh(
            child: SListView(_buildItemView, itemAction: _itemAction).build(context, snapshot.data.items),
            onRefresh: () {
              return _goodListViewModel.loadGoods(isFirst: false);
            },
          );
        },
      ),
    );
  }

  @override
  void dispose() {
    _goodListViewModel.dispose();
    super.dispose();
  }
}
