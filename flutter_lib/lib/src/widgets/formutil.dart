import 'package:flutter/material.dart';

class CYForm {
  TextStyle titleStyle;
  TextStyle valueStyle;

  CYForm({TextStyle titleStyle, TextStyle valueStyle}) {
    this.valueStyle = valueStyle;
    this.titleStyle = titleStyle;
  }

  SizedBox space(double height) {
    return SizedBox(
        height: height,
        width: double.infinity,
        child: const DecoratedBox(
            decoration: const BoxDecoration(
              color: Color(0xfff1f0ee),
              ),
          ),
  );
  }

  // TODO: add title size, title color, value size, value color, separator color
  Container divider({double left = 0, double right = 0, double height = 1, Color color }) {
    if (color == null) {
      color = Color(0xffe7e7e7);
    }
    return new Container(
        child: new Divider(height: height, color: color),
        padding: EdgeInsets.only(left: 15, right: right),
        color: Colors.white,
        );
  }

  Widget row(String title, { String value = "", bool showArrow = true, String icon = null, GestureTapCallback onTap = null}) {
    var c = new Container(
        decoration: new BoxDecoration(color: Colors.white),
        child: Row(
          children: rowItems(icon, title, value, showArrow),
          ),
        padding: EdgeInsets.all(15.0),
      );

    if (onTap == null) {
      return c;
    } else {
      return GestureDetector(
        onTap: onTap, 
        child: c,
        );
    }
  }

  List<Widget> rowItems(String icon, String title, String value, bool showArrow) {
    List<Widget> items = [];

    if (icon != null) {
      items.add(
          Container(
            child: new Image.asset(icon),
            padding: EdgeInsets.only(right: 5),
            ));
    }

    if (title != null) {
      items.add(Text(title, style: titleStyle,));
    }

    if (value != null) {
      items.add(
          Expanded(
            child: Container(
              padding: EdgeInsets.all(5.0),
              child: Text(
                value, 
                textAlign: TextAlign.right,
                style: valueStyle,
                ),
            )
            )
          );
    }

    if (showArrow) {
      items.add(Container(
            child: new Image.asset('assets/arrow-right.png'),
            padding: EdgeInsets.only(left: 8.0),
            )
            );
    }

    return items;
  }
}

