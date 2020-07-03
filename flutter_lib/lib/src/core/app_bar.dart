import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_lib/flutter_lib.dart';

const double app_bar_height = 45; //标题栏高度

///系统标题栏
class IsAppBar extends StatefulWidget implements PreferredSizeWidget {
  String title;
  Widget leftWidget;
  Widget rightWidget;
  VoidCallback leftOnTap;
  bool canBack;

  IsAppBar({
    Key key,
    this.title,
    this.leftWidget,
    this.rightWidget,
    this.leftOnTap, //当不显示设定按钮点击动作时会默认执行退栈操作
    this.canBack = true, //当为false，不会显示左侧返回按钮
  }) : super(key: key);

  @override
  State<StatefulWidget> createState() {
    return _IsAppBarState();
  }

  @override
  Size get preferredSize => Size.fromHeight(app_bar_height);
}

class _IsAppBarState extends State<IsAppBar> {
  Color _bg;
  Color _status_bar;

  SystemUiOverlayStyle uiStyle = SystemUiOverlayStyle.light.copyWith(
    statusBarColor: Colors.transparent,
  );

  @override
  Widget build(BuildContext context) {
    final AppBarTheme appBarTheme = AppBarTheme.of(context);

    _bg = appBarTheme.color;
    _status_bar = _bg;
    SystemUiOverlayStyle.light.copyWith(
      statusBarColor: _status_bar,
    );
    SystemChrome.setSystemUIOverlayStyle(uiStyle);

    Widget _leftWidget = widget.leftWidget ?? Container();
    Widget _rightWidget = widget.rightWidget ?? Container();
    if (widget.canBack && widget.leftWidget == null) {
      _leftWidget = GestureDetector(
        onTap: widget.leftOnTap ??
            () {
              STBridge().pop(context);
            },
        child: Icon(Icons.arrow_back_ios),
      );
    }

    return new Container(
      color: _bg,
      child: SafeArea(
        top: true,
        child: new Container(
            height: app_bar_height,
            child: new Stack(
              alignment: Alignment.center,
              children: <Widget>[
                Positioned(left: 10, child: _leftWidget),
                new Container(
                  child: new Text(widget.title,
                      style: appBarTheme.textTheme.headline1),
                ),
                Positioned(right: 10, child: _rightWidget),
              ],
            )),
      ),
    );
  }
}
