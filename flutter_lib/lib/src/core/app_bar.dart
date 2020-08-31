import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_lib/flutter_lib.dart';

const double app_bar_height = 45; //标题栏高度

///系统标题栏
class IsAppBar extends StatefulWidget implements PreferredSizeWidget {
  String title;
  Color titleColor;
  Widget leftWidget;
  Widget rightWidget;
  VoidCallback leftOnTap;
  bool canBack;
  var color;
  bool showBottomLine;

  IsAppBar({
    Key key,
    this.title,
    this.leftWidget,
    this.rightWidget,
    this.leftOnTap, //当不显示设定按钮点击动作时会默认执行退栈操作
    this.canBack = true, //当为false，不会显示左侧返回按钮
    this.color, //渐变色
    this.titleColor, //标题颜色
    this.showBottomLine = false, //显示底部分割线
  }) : super(key: key);

  @override
  State<StatefulWidget> createState() {
    return _IsAppBarState();
  }

  @override
  Size get preferredSize => Size.fromHeight(app_bar_height);
}

SystemUiOverlayStyle lightStyle = SystemUiOverlayStyle.light.copyWith(
  statusBarColor: Colors.transparent,
);

SystemUiOverlayStyle darkStyle = SystemUiOverlayStyle.dark.copyWith(
  statusBarColor: Colors.transparent,
);

class _IsAppBarState extends State<IsAppBar> {
  Color _titleColor;

  dynamic _isSingleColor(var color) {
    return color == null
        ? Theme.of(context).appBarTheme.color
        : color is Color ? color : null;
  }

  @override
  Widget build(BuildContext context) {
    SystemChrome.setSystemUIOverlayStyle(
        Theme.of(context).brightness == Brightness.light
            ? darkStyle
            : lightStyle);

    final AppBarTheme appBarTheme = Theme.of(context).appBarTheme;

    _titleColor = widget.titleColor ?? appBarTheme.textTheme.headline1.color;
    Widget _leftWidget = widget.leftWidget ?? Container();
    Widget _rightWidget = widget.rightWidget ?? Container();

    if (widget.canBack && widget.leftWidget == null) {
      _leftWidget = GestureDetector(
        onTap: widget.leftOnTap ??
                () {
              STBridge().pop();
            },
        child: Image.asset(
          "assets/images/icon_back.png",
          color: _titleColor,
        ),
      );
    }

    return Container(
      decoration: _isSingleColor(widget.color) != null
          ? BoxDecoration(
          color: _isSingleColor(widget.color),
          border: widget.showBottomLine
              ? BorderDirectional(
              bottom: BorderSide(
                  width: 0.5, color: Theme.of(context).dividerColor))
              : null)
          : BoxDecoration(
          gradient: LinearGradient(
            colors: widget.color,
          ),
          border: widget.showBottomLine
              ? BorderDirectional(
              bottom: BorderSide(
                  width: 0.5, color: Theme.of(context).dividerColor))
              : null),
      child: SafeArea(
        child: Container(
          height: app_bar_height,
          child: Stack(
            alignment: Alignment.center,
            children: <Widget>[
              Positioned(left: 10, child: _leftWidget),
              new Container(
                child: new Text(widget.title,
                    style: TextStyle(
                        color: _titleColor,
                        fontSize: appBarTheme.textTheme.headline1.fontSize,
                        fontWeight:
                        appBarTheme.textTheme.headline1.fontWeight)),
              ),
              Positioned(right: 10, child: _rightWidget),
            ],
          ),
        ),
      ),
    );
  }
}