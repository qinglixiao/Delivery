import 'package:flutter/material.dart';

mixin UIHelper on Widget {
  Widget wrap() {
    return Container(child: this,);
  }

  Widget makePage() {
    return new MaterialApp(home: this,);
  }
}