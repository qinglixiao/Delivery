import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_lib/src/core/app_bar.dart';

class RootPageWidget extends StatefulWidget {
  Widget body;
  Widget errorWidget;
  Widget dialogWidget;
  BaseViewModel viewModel;
  IsAppBar appBar;
  WillPopCallback onWillBack; //返回按键拦截，true:允许返回，false:不允许

  RootPageWidget({
    @required this.body,
    this.viewModel,
    this.appBar,
    this.errorWidget,
    this.dialogWidget,
    this.onWillBack,
  });

  @override
  State<StatefulWidget> createState() {
    return RootPageWidgetState();
  }
}

class RootPageWidgetState extends State<RootPageWidget> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
        onWillPop: widget.onWillBack,
        child: Container(
            child: Stack(
          alignment: Alignment.center,
          fit: StackFit.expand,
          children: <Widget>[
            Scaffold(
              appBar: widget.appBar,
              body: widget.body,
            ),
            StreamBuilder<RequestState>(
                stream: widget.viewModel?.parentState,
                builder:
                    (BuildContext context, AsyncSnapshot<RequestState> state) {
                  Logger.print(
                      "rootWidget data=${state.data} error=${state.error}");
                  if (state.data == null) {
                    return Container();
                  }

                  if (state.data.hasError) {
                    return _buildNetErrorWidget();
                  } else if (state.data.state == ConnectionState.waiting) {
                    return _buildLoadingWidget();
                  }

                  return Container();
                }),
          ],
        )));
  }

  _buildNetErrorWidget() {
    return widget.errorWidget != null ? widget.errorWidget : AsErrorWidget();
  }

  _buildLoadingWidget() {
    return widget.dialogWidget != null ? widget.dialogWidget : LoaddingWidget();
  }
}
