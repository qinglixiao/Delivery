import 'package:flutter/cupertino.dart';
import 'package:flutter_lib/flutter_lib.dart';

class RootPageWidget extends StatefulWidget {
  Widget body;
  Widget errorWidget;
  Widget dialogWidget;
  BaseViewModel viewModel;

  RootPageWidget({
    @required this.body,
    @required this.viewModel,
    this.errorWidget,
    this.dialogWidget,
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
    return Container(
        child: Stack(
      alignment: Alignment.center,
      fit: StackFit.expand,
      children: <Widget>[
        widget.body,
        StreamBuilder<RequestState>(
            stream: widget.viewModel.parentState,
            builder: (BuildContext context, AsyncSnapshot<RequestState> data) {
              Logger.print(
                  "rootWidget data=${data.data} \n error=${data.error}");
              if (data.hasError) {
                if (data.error is NetConnectError) {
                  return _buildNetErrorWidget();
                } else if (data.error is ServerError) {}
              }

              if (data.connectionState == ConnectionState.waiting) {
                return _buildLoadingWidget();
              } else if (data.connectionState == ConnectionState.active) {}

              return Container();
            }),
      ],
    ));
  }

  _buildNetErrorWidget() {
    return widget.errorWidget != null ? widget.errorWidget : Container();
  }

  _buildLoadingWidget() {
    return widget.dialogWidget != null ? widget.dialogWidget : Container();
  }
}
