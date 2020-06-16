import 'package:flutter/material.dart';
import 'package:flutter_lib/src/core/navigator_wrapper.dart';

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

const double app_bar_height = 45;
var app_bar_txt_color = Color(0xFF333333);

class _IsAppBarState extends State<IsAppBar> {
  Color _color;

  @override
  Widget build(BuildContext context) {
    final AppBarTheme appBarTheme = AppBarTheme.of(context);
    Widget _leftWidget = widget.leftWidget ?? Container();
    Widget _rightWidget = widget.rightWidget ?? Container();
    if (widget.canBack && widget.leftWidget == null) {
      _leftWidget = GestureDetector(
        onTap: widget.leftOnTap ??
            () {
              NavigatorWrapper.of(context).pop();
            },
        child: Icon(Icons.arrow_back_ios),
      );
    }

    return new Container(
      color: appBarTheme.color,
      child: SafeArea(
        top: true,
        child: new Container(
//            decoration: new UnderlineTabIndicator(
//              borderSide: BorderSide(width: 1.0, color: Color(0xFFeeeeee)),
//            ),
            height: app_bar_height,
            child: new Stack(
              alignment: Alignment.center,
              children: <Widget>[
                Positioned(left: 10, child: _leftWidget),
                new Container(
                  child: new Text(widget.title,
                      style: new TextStyle(
                          fontWeight: FontWeight.w600,
                          fontSize: 17,
                          color: app_bar_txt_color)),
                ),
                Positioned(right: 10, child: _rightWidget),
              ],
            )),
      ),
    );
  }
}
