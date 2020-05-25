import 'package:flutter/material.dart';

class UIFork {
  final widget;

  UIFork([this.widget]) {
    print(this.widget);
  }

  Widget done() {
    return this.widget;
  }

  UIFork center() {
    var widget = Center(
        child: this.widget,
        );
    return UIFork(widget);
  }

  UIFork container({Color color, double paddingL=0, double paddingR=0, double paddingT=0, double paddingB=0, double marginL=0, double marginR=0, double marginT=0, double marginB=0}) {
    var widget = Container(
        color: color,
        padding: EdgeInsets.only(left: paddingL, right: paddingR, top: paddingT, bottom: paddingB),
        margin: EdgeInsets.only(left: marginL, right: marginR, top: marginT, bottom: marginB),
        child: this.widget);
    return UIFork(widget);
  }

  UIFork tap(GestureTapCallback onTap) {
    var widget = GestureDetector(
        onTap: onTap, 
        child: this.widget,
        );
    return UIFork(widget);
  }

  UIFork button(String text, { TextStyle style, double width = double.infinity, double height, Color color = Colors.white, double fontSize, Color textColor}) {
    if (style == null) {
      style = TextStyle(fontSize: fontSize, color: textColor);
    }
    var w = SizedBox(
        height: height,
        width: width,
        child: Container(
          color: color,
          child: Center(
            child: Text(text, style: style),
            ),
          ),
        );
      return UIFork(w);
  }

  UIFork roundUrlImage(String url, double size, { Color borderColor, double borderWidth = 1.0 }) {
    var widget = Container(
        width: size,
        height: size,
        decoration: new BoxDecoration(
          image: 
            new DecorationImage(
              image: new NetworkImage(url),
              fit: BoxFit.cover,
              ),
            borderRadius: new BorderRadius.all(new Radius.circular(size * 0.5)),
            border: new Border.all(
              color: borderColor,
              width: borderWidth,
            ),
          ),
        );

    return UIFork(widget);
  }

  Widget makePage() {
    return new MaterialApp(
      home: this.widget,
    );
  }
}

Widget makePage(Widget widget) {
  return new MaterialApp(
      home: widget,
    );  
}

Future<Size> whenNotZero(Stream<Size> source) async {
    await for (Size value in source) {
        print("Width:" + value.toString());
        if (value.width > 0) {
            print("Width > 0: " + value.toString());
            return value;
        }
    }
    // stream exited without a true value, maybe return an exception.
}

typedef DeviceSizeAwareBuilder = Widget Function(BuildContext context, Size size);

Widget whenDeviceSizeReady(BuildContext context, DeviceSizeAwareBuilder builder, [WidgetBuilder placeholder]) {
  return FutureBuilder(
    future: whenNotZero(
        Stream<Size>.periodic(Duration(milliseconds: 50),
            (x) => MediaQuery.of(context).size),
    ),
    builder: (BuildContext context, snapshot) {
        if (snapshot.hasData) {
            if (snapshot.data.width > 0) {
              return builder(context, snapshot.data);
            }
        } 
        if (placeholder == null) {
          return Container();
        }
        return placeholder(context);
    }
  );
}
