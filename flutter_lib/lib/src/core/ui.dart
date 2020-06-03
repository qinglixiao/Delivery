import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

mixin UIWrap on Widget {
  Widget wrap() {
    return Scaffold(
      body: this,
    );
  }

  Widget makePage() {
    return new MaterialApp(
      home: this,
    );
  }
}
