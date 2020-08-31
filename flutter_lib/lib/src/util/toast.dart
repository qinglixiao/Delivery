import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';

///
///create by lx
///date 2020/7/20
///
class Toast {
  static show(String message) {
    OverlayEntry _entry = OverlayEntry(builder: (context) {
      return UnconstrainedBox(
        alignment: Alignment.bottomCenter,
        child: Container(
            alignment: Alignment.center,
            margin: EdgeInsets.only(bottom: 80),
            padding: EdgeInsets.all(10),
            decoration: BoxDecoration(
              color: Colors.black87,
              borderRadius: BorderRadius.circular(3),
            ),
            child: Text(
              message,
              style: Theme.of(context).textTheme.headline1.merge(TextStyle(
                  fontSize: 16, color: Theme.of(context).primaryColorLight)),
            )),
      );
    });

    GlobalNavigator.currentState.overlay.insert(_entry);
    Future.delayed(Duration(seconds: 2), () {
      _entry.remove();
    });
  }
}

class _Entry {
  List<OverlayEntry> _list = List();

  OverlayEntry create(WidgetBuilder builder) {
    var e = OverlayEntry(builder: builder);
    _list.add(e);
    return e;
  }
}
