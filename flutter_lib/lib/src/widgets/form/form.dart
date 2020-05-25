import 'package:flutter/material.dart';

class ZZLineConfig {
  final double left;
  final double right;
  final double height;
  final Color color;
  final Color backColor; 

  ZZLineConfig({this.left=0, this.right=0, this.height=1, this.color, this.backColor=Colors.transparent});
}

class ZZRowConfig {
  final TextStyle titleStyle;
  final TextStyle valueStyle;
  final Color rowColor;
  final double iconRightSpace;
  final double arrowLeftSpace;
  final double titleWidth;
  final TextAlign valueAlign;
  final ZZLineConfig bottomLine;

  EdgeInsets rowPadding =EdgeInsets.all(15);

  ZZRowConfig({
    this.titleStyle, 
    this.valueStyle, 
    this.rowPadding, 
    this.rowColor=Colors.white, 
    this.iconRightSpace=5,
    this.arrowLeftSpace=5,
    this.titleWidth=0,
    this.valueAlign=TextAlign.right,
    this.bottomLine,
    });
}

/*
 * form row: [<icon> - <title> - <value> - <arrow>]
 */
class ZZRow extends StatelessWidget {
  const ZZRow({
    this.config, 
    this.icon, 
    this.title, 
    this.value, 
    this.showArrow =true, 
    this.bottomLine,
    this.onTap,
    });

  final String icon;

  final String title;

  final String value;

  final bool showArrow;

  final ZZRowConfig config;

  final GestureTapCallback onTap;

  final ZZLineConfig bottomLine;

  @override
  Widget build(BuildContext context) {
    return row(icon: icon, title: title, value: value, showArrow: showArrow, onTap: onTap, bottomLine: bottomLine);
  }

  Widget row({String title, String value = "", bool showArrow = true, String icon, GestureTapCallback onTap, ZZLineConfig bottomLine}) {
    Widget c = Container(
        decoration: BoxDecoration(color: config.rowColor),
        child: Row(
          children: rowItems(config, icon, title, value, showArrow),
          ),
        padding: config.rowPadding,
      );

    if (bottomLine !=null) {
      c =Column(
        children: <Widget>[
          c,
          ZZRowLine(bottomLine),
        ],
      );
    }

    if (onTap != null) {
      c = GestureDetector(
        onTap: onTap, 
        child: c,
        );
    }
    return c;
  }

  List<Widget> rowItems(ZZRowConfig config, String icon, String title, String value, bool showArrow) {
    List<Widget> items = [];

    if (icon != null) {
      items.add(
          Container(
            child: new Image.asset(icon),
            padding: EdgeInsets.only(right: config.iconRightSpace),
            ));
    }

    if (title != null) {
      if (config.titleWidth > 0) {
        items.add(Container(
          child: Text(title, style: config.titleStyle,),
          width: config.titleWidth,
        ));
      } else {
        items.add(Text(title, style: config.titleStyle,));
      }
    }

    if (value != null) {
      items.add(
          Expanded(
            child: Text(
              value, 
              textAlign: config.valueAlign,
              style: config.valueStyle,
            ),
          )
      );
    }

    if (showArrow) {
      items.add(Container(
            child: new Image.asset('assets/arrow-right.png'),
            padding: EdgeInsets.only(left: config.arrowLeftSpace),
            )
            );
    }

    return items;
  }
}

class ZZRowSpace extends StatelessWidget {
  final double height;
  final Color color;

  ZZRowSpace({this.height, this.color});

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: height,
      width: double.infinity,
      child: DecoratedBox(
        decoration: BoxDecoration(
          color: color,
        ),
      ),
    );
  }
}

class ZZRowLine extends StatelessWidget {
  final ZZLineConfig config;

  ZZRowLine(this.config);

  @override
  Widget build(BuildContext context) {
    Color c =config.color ==null ? Color(0xffe7e7e7) :config.color;
    return new Container(
      child: new Divider(height: config.height, color: c),
      padding: EdgeInsets.only(left: config.left, right: config.right),
      color: config.backColor,
    );
  }
}
