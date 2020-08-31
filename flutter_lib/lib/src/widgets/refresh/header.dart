import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'defs.dart';

// When the scroll ends, the duration of the progress indicator's animation
// to the LiquidPullToRefresh's displacement.
const Duration _kIndicatorSnapDuration = Duration(milliseconds: 150);

// The duration of the ScaleTransitionIn of box that starts when the
// refresh action has completed.
const Duration _kIndicatorScaleDuration = Duration(milliseconds: 200);

class RefreshHeader {
  PullToRefreshMode mode;

  PositionController positionController;

  Color txtColor;
  Color backgroundColor;

  RefreshHeader({this.txtColor, this.backgroundColor});

  void setup({PositionController positionController}) {
    this.positionController = positionController;
  }

  void update(PullToRefreshMode mode, double offset) {
    this.mode = mode;
  }

  Future<void> dismiss() async {
    return null;
  }

  void show() {}

  void start() {}

  void dispose() {}

  // 自定义的header，重写这个方法
  List<Widget> build(BuildContext ctxt) {
    print("refresh control: $mode");

    String hint = "下拉刷新";
    if (mode == PullToRefreshMode.armed) {
      hint = "松开刷新数据";
    } else if (mode == PullToRefreshMode.refresh) {
      hint = "加载中";
    }

    return [
      AnimatedBuilder(
        animation: positionController.controller,
        builder: (BuildContext buildContext, Widget child) {
          return Container(
            constraints: BoxConstraints.expand(
              height:
              positionController.value.value * positionController.height,
            ),
            color: backgroundColor ?? Colors.transparent,
            child: Center(
              child: Row(
                mainAxisAlignment: MainAxisAlignment.center,
                mainAxisSize: MainAxisSize.min,
                children: <Widget>[
                  Visibility(
                    visible: mode == PullToRefreshMode.refresh,
                    child: CupertinoActivityIndicator(),
                  ),
                  Text(
                    hint,
                    style: TextStyle(
                        color: txtColor ?? Theme.of(ctxt).primaryColor),
                  ),
                ],
              ),
            ),
          );
        },
      ),
    ];
  }
}

class PositionController {
  AnimationController controller;
  Animation<double> value;
  TickerProvider tp;

  final double height;
  final int springAnimationDurationInMilliseconds;

  static final Animatable<double> _threeQuarterTween =
      Tween<double>(begin: 0.0, end: 1.0);

  PositionController(
      {this.height = 100, this.springAnimationDurationInMilliseconds = 1000}) {}

  void setup({TickerProvider tp}) {
    this.tp = tp;

    controller = AnimationController(vsync: tp);

    value = controller.drive(_threeQuarterTween);
  }

  bool isArmed() {
    return controller.value > 0.6;
  }

  void update(double offset) {
    double newValue = offset / height;

    newValue = newValue.clamp(0.0, 1.0); // this triggers various rebuilds

    controller.value = newValue;
  }

  void show() {
    controller.animateTo(1.0,
        duration: Duration(milliseconds: springAnimationDurationInMilliseconds),
        curve: Curves.linear);
  }

  void dispose() {
    controller.dispose();
  }

  void dismiss() async {
    await controller.animateTo(0.0,
        duration:
            Duration(milliseconds: _kIndicatorSnapDuration.inMilliseconds * 2));
  }

  void cancel() async {
    await controller.animateTo(0.0, duration: _kIndicatorScaleDuration);
  }
}

class PullPadding {
  final PositionController positionController;
  final double height;

  PullPadding({this.positionController, this.height}) {}

  Widget build(BuildContext ctxt) {
    return SliverToBoxAdapter(
      child: AnimatedBuilder(
        animation: positionController.controller,
        builder: (BuildContext buildContext, Widget child) {
          return Container(
            height: positionController.value.value * height,
          );
        },
      ),
    );
  }
}
