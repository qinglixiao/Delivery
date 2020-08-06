import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

///
///create by lx
///date 2020/7/21
///

class AnimationList extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _AnimationState();
  }
}

class _AnimationState extends State<AnimationList>
    with TickerProviderStateMixin {
  AnimationController _controller;
  Animation _animation;

  @override
  void initState() {
    super.initState();
    _controller =
        AnimationController(duration: Duration(seconds: 3), vsync: this);
    final _ani = CurvedAnimation(
        parent: _controller, curve: Curves.fastLinearToSlowEaseIn);
    _animation = Tween(begin: 0.0, end: 300.0).animate(_ani);
    _controller.reset();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(),
      body: Column(
        children: <Widget>[
          RaisedButton(
            onPressed: () {
              _controller.reset();
              _controller.forward();
            },
          ),
//          _widthChanged(),
//          _OpaChanged(),
          _colorChanged(),
        ],
      ),
    );
  }

  Widget _widthChanged() {
    return Center(
      child: new AnimatedBuilder(
          animation: _animation,
          builder: (BuildContext context, Widget child) {
            return new Container(
                height: _animation.value,
                width: _animation.value,
                child: child);
          },
          child: Container(
            color: Colors.blue,
          )),
    );
  }

  Widget _OpaChanged() {
    _animation = Tween(begin: 1.0, end: 0.0).animate(_controller);
//    _controller.repeat(reverse: true);
    _controller.addStatusListener((status) {
      print("$status");
    });

    return Center(
      child: new AnimatedBuilder(
          animation: _animation,
          builder: (BuildContext context, Widget child) {
            return Opacity(opacity: _animation.value, child: child);
          },
          child: Container(
            width: 100,
            height: 100,
            color: Colors.blue,
          )),
    );
  }

  Widget _colorChanged() {
    _animation =
        ColorTween(begin: Colors.red, end: Colors.blue).animate(_controller);
    _controller.repeat(reverse: true);
    _controller.addStatusListener((status) {
      print("$status");
    });

    return Center(
      child: new AnimatedBuilder(
          animation: _animation,
          builder: (BuildContext context, Widget child) {
            return Container(
                width: 100, height: 100, color: _animation.value, child: child);
          },
          child: Container(
            width: 50,
            height: 10,
            color: Colors.transparent,
          )),
    );
  }
}
