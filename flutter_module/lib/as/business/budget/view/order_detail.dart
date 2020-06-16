import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';

// ignore: must_be_immutable
class OrderDetail extends StatelessWidget with PageIteractor {
  OrderDetail() {
    STBridge().getInitArgs().then(
          (value) => Logger.print(value.toString()),
        );
  }

  @override
  Widget build(BuildContext context) {
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
                  STBridge()
                      .pop(data: {"order_id": "x_id_01", "price": "35.4"});
                }),
            RaisedButton(
                child: Text("打开原生"),
                onPressed: () {
                  STBridge().openNative("ienglish://me/main").then((value) => {
                        Logger.print(value),
                      });
                }),
          ],
        ));
  }
}
