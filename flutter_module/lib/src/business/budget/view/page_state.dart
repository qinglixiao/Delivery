import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';

typedef ErrorWidgetCallback = void Function();

class CYErrorWidget extends StatelessWidget {
  final String icon;
  final String title;
  final String subtitle;
  final TextStyle style;
  final ErrorWidgetCallback callback;

  CYErrorWidget({
    Key key, 
    this.icon, 
    this.title, 
    this.subtitle, 
    this.callback, 
    this.style
    }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Center(
        child: GestureDetector(
          child: buildContent(),
          onTap: (){
            if (callback != null) {
              callback();
            }
          },
        )
      ),
    );
  }

  Widget buildContent() {
    var l = List<Widget>();
    if (icon != null) {
      l.add(Image.asset(icon));
    }
    l.add(Text(title, style: style ?? TextStyle(fontSize: 16, color: Color(0xff999999))));

    if (subtitle != null) {
      l.add(Text(subtitle, style: style ?? TextStyle(fontSize: 16, color: Color(0xff999999))));
    }

    return Container(
      padding: EdgeInsets.only(bottom: 50),
      child:Center(
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        mainAxisAlignment: MainAxisAlignment.center, 
        children: l,
      )
    ),
    );
  }
}

class PageStateUI extends LoaderState {
  Widget showError(BuildContext context, {ErrorWidgetCallback callback}) {
    if (isLastLoadFailed()) {
      print("handle error");
      // 加载失败
      if (isLoaded()) {
        FlutterError("加载错误");

//        Fluttertoast.showToast(
//          msg: "${error ?? "加载错误"}",
//          toastLength: Toast.LENGTH_SHORT,
//          gravity: ToastGravity.CENTER,
//          timeInSecForIos: 1,
//          backgroundColor: Colors.red,
//          textColor: Colors.white,
//          fontSize: 16.0
//        );
      } else {
        return CYErrorWidget(
          title: "${ error ?? '加载失败' }",
          subtitle: '点击重试',
          callback: callback,
        );
      }
    }
    return null;
  }

  Widget showLoading(BuildContext context) {
    if (isNew()) {
      return Container(
        padding: EdgeInsets.only(bottom: 50),
        child: Center(
          child: CircularProgressIndicator(
            valueColor: new AlwaysStoppedAnimation<Color>(Color(0xff4dd363)),
          ),
        )
      ) ; 
    }

    return null;
  }

  Widget showEmpty(BuildContext context, bool empty, {String title, ErrorWidgetCallback callback}) {
    if (!isLastLoadFailed() && isLoaded() && empty) {
      return CYErrorWidget(
          title: "${title ?? '暂无数据'}",
          subtitle: '点击重新加载',
          callback: callback,
        );
    }
    return null;
  }
}