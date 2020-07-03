import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:fluttermodule/src/config/name_router.dart';

// ignore: must_be_immutable
class OrderDetail extends StatelessWidget with PageBridge {

  @override
  Widget build(BuildContext context) {
    getInitArg(context).then((value) => Logger.print("recevie arg:${value}"));
    return RootPageWidget(
        viewModel: null,
        appBar: IsAppBar(
          title: '订单详情',
        ),
        body: Row(
          children: <Widget>[
            RaisedButton(
                child: Text("关闭当前页返回值"),
                onPressed: () {
                  pop(context, data: {"order_id": "x_id_01", "price": "35.4"});
                }),
            RaisedButton(
                child: Text("打开原生"),
                onPressed: () {
                  bridge.openNative("ienglish://me/main").then((value) => {});
                }),
            RaisedButton(
                child: Text("打开列表"),
                onPressed: () {
                  bridge.openFlutter(RouterName.good_list,
                      parameters: {"from": "detail"}).then((value) => {});
                }),
          ],
        ));
  }
}
