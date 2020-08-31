import 'package:flutter/material.dart';

///页面在出错情况下展示的页面
///
///create by lx
///date 2020/6/8
class AsErrorWidget extends StatelessWidget {
  VoidCallback _callback;
  var image;
  var tip;

  AsErrorWidget(this.image, this.tip, VoidCallback callback)
      : _callback = callback;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Center(
            child: UnconstrainedBox(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: <Widget>[
                    Image.asset(image),
                    SizedBox(height: 5,),
                    Text(tip ?? "出现错误~",
                        style:
                        TextStyle(fontSize: 14, color: Theme.of(context).canvasColor)),
                    SizedBox(height: 15,),
                    FlatButton(
                      color: Theme.of(context).buttonColor,
                      disabledColor: Theme.of(context).buttonColor,
                      shape:
                      RoundedRectangleBorder(borderRadius: BorderRadius.circular(20.0)),
                      onPressed: _callback,
                      child: Text("重新加载",style: TextStyle(fontSize: 14,color: Theme.of(context).primaryColorLight),),
                    )
                  ],
                ))));
  }
}

///页面加载数据时展示的loadding窗
class AsLoaddingWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    var warn = Container(
      margin: const EdgeInsets.all(50.0),
      padding: EdgeInsets.symmetric(horizontal: 24.0, vertical: 16.0),
      decoration: BoxDecoration(
          color: Colors.black87, borderRadius: BorderRadius.circular(3)),
      child: ClipRect(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: <Widget>[
            Container(
              width: 40.0,
              height: 40.0,
              margin: EdgeInsets.only(bottom: 8.0),
              padding: EdgeInsets.all(4.0),
              child: CircularProgressIndicator(
                strokeWidth: 3.0,
                valueColor: AlwaysStoppedAnimation<Color>(Colors.white),
              ),
            ),
            Text(
              "加载中...",
              textAlign: TextAlign.center,
              style: TextStyle(fontSize: 16,color: Theme.of(context).primaryColorLight),
            )
          ],
        ),
      ),
    );

    return Container(
        width: 2000,
        height: 2000,
        color: Colors.transparent,
        child: Center(child: warn));
  }
}

class AsEmptyWidget extends StatelessWidget {
  VoidCallback _callback;
  var image;
  var tip;

  AsEmptyWidget(this.image, this.tip, VoidCallback callback)
      : _callback = callback;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: GestureDetector(
            onTap: _callback,
            child: Center(
                child: UnconstrainedBox(
                    child: Column(
                      children: <Widget>[
                        Image.asset(image),
                        Text(
                          tip ?? "无数据",
                          style: TextStyle(
                              fontSize: 14, color: Theme.of(context).canvasColor),
                        ),
                      ],
                    )))));
  }
}

class CommonWidget {
  static Widget noPreparedWidget({VoidCallback feedback}) {
    return Container();
  }

  static Widget emptyWidget({VoidCallback feedback}) {
    return AsEmptyWidget("assets/images/bg_no_data.png", "暂无内容~", feedback);
  }

  static Widget errorWidget({VoidCallback feedback}) {
    return AsErrorWidget("", "", feedback);
  }

  static Widget netErrorWidget({VoidCallback feedback}) {
    return AsErrorWidget("assets/images/bg_no_net.png", "暂无网络~", feedback);
  }

  static Widget loaddingWidget() {
    return AsLoaddingWidget();
  }
}
