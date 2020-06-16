import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:fluttermodule/as/business/budget/bean/good.dart';
import 'package:fluttermodule/as/business/budget/viewmodel/good_list_view_model.dart';
import 'package:fluttermodule/as/comm/name_router.dart';

class GoodListPage extends StatefulWidget with UIWrap {
  @override
  State<StatefulWidget> createState() {
    return _GoodListState();
  }
}

class _GoodListState extends State<GoodListPage> with PageIteractor {
  GoodListViewModel _goodListViewModel;

  @override
  void initState() {
    super.initState();
    _goodListViewModel = GoodListViewModel();
    initPage(context);

    getInitArg(context)
        .then((value) => {Logger.print("init args ${value.toString()}")});
  }

  @override
  Widget build(BuildContext context) {
    return RootPageWidget(
      viewModel: _goodListViewModel,
      body: Scaffold(
        appBar: IsAppBar(
          title: '订货',
        ),
        body: StreamBuilder<GoodBean>(
            stream: _goodListViewModel.streamGood,
            builder: (BuildContext context, AsyncSnapshot<GoodBean> snapshot) {
              var title = snapshot.data?.title;
              Logger.print("title ${title}");
              return Row(
                children: <Widget>[
                  Text(title ?? ""),
                  RaisedButton(
                      child: Text("获取订货单"),
                      onPressed: () {
                        _goodListViewModel.loadGoods();
                      }),
                  RaisedButton(
                      child: Text("转到订单详情页"),
                      onPressed: () {
                        STBridge()
                            .openFlutter(RouterName.order_detail,
                                parameters: {
                                  "order_id": "x_id_01",
                                  "price": "35.4"
                                },
                                title: "订单详情",
                                replaceTop: false)
                            .then(
                              (value) => Logger.print(value.toString()),
                            );
                      }),
                  RaisedButton(
                      child: Text("关闭当前页返回值"),
                      onPressed: () {
                        STBridge().close(
                            result: "ok",
                            data: {"order_id": "x_id_01", "price": "35.4"});
                      }),
                ],
              );
            }),
      ),
    );
  }
}
