import 'dart:io';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_lib/src/core/app_bar.dart';
import 'package:provider/provider.dart';

///describe：原则上所有页面根结点都应该是这个，方便统一处理
///create by lx
///date 2020/6/8
@immutable
// ignore: must_be_immutable
class RootPageWidget extends StatefulWidget {
  Widget body;
  Widget errorWidget;
  Widget emptyWidget;
  BaseViewModel viewModel;
  IsAppBar appBar;
  WillPopCallback onWillBack; //返回按键拦截，true:允许返回，false:不允许
  Future task; //异步任务
  VoidCallback feedback; //异常、空页面的处理回调

  RootPageWidget({
    @required this.body,
    this.viewModel,
    this.appBar,
    this.errorWidget,
    this.emptyWidget,
    this.onWillBack,
    this.feedback,
    this.task,
  });

  @override
  State<StatefulWidget> createState() {
    return RootPageWidgetState();
  }
}

class RootPageWidgetState extends State<RootPageWidget> {
  @override
  void deactivate() {
    super.deactivate();
  }

  @override
  void dispose() {
    widget.viewModel?.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
        onWillPop: Platform.isIOS ? null : widget.onWillBack,
        child: FutureProvider.value(
          value: widget.task,
          child: Stack(
              alignment: Alignment.center,
              fit: StackFit.expand,
              children: <Widget>[
                Scaffold(
                  appBar: widget.appBar,
                  body: widget.body,
                  resizeToAvoidBottomPadding: false,
                ),
                StreamBuilder<RequestState>(
                    stream: widget.viewModel?.parentState,
                    builder: (BuildContext context,
                        AsyncSnapshot<RequestState> state) {
                      return _rootWidget(state);
                    }),
              ]),
        ));
  }

  Widget _rootWidget(AsyncSnapshot<RequestState> state) {
    if (state.data == null) {
      return Container();
    }

    var _w;
    if (state.data.hasError) {
      if (state.data.exception is NetworkError) {
        _w = _buildNetErrorWidget();
      }
    } else if (state.data.state == ConnectionState.waiting) {
      _w = _buildLoadingWidget();
    } else if (state.data.state == ConnectionState.done &&
        state.data.data == null) {
      _w = _buildEmptyWidget();
    } else {
      return Container();
    }

    return Scaffold(appBar: widget.appBar, body: _w);
  }

  _buildNetErrorWidget() {
    return widget.errorWidget != null
        ? widget.errorWidget
        : CommonWidget.netErrorWidget(feedback: widget.feedback);
  }

  _buildEmptyWidget() {
    return widget.emptyWidget != null
        ? widget.emptyWidget
        : CommonWidget.emptyWidget(feedback: widget.feedback);
  }

  _buildLoadingWidget() {
    return CommonWidget.loaddingWidget();
  }
}
