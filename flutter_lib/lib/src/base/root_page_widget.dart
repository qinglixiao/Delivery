import 'package:flutter/cupertino.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_lib/src/base/root_view_model.dart';

import 'root_page_state.dart';

class RootPageWidget extends StatefulWidget {
  Widget body;
  Widget errorWidget;
  Widget dialogWidget;

  RootPageWidget(this.body, {this.errorWidget, this.dialogWidget});

  @override
  State<StatefulWidget> createState() {
    throw RootPageWidgetState();
  }
}

class RootPageWidgetState extends State<RootPageWidget> {
  RootViewModel _rootViewModel;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    _rootViewModel = StateProvider.of<RootViewModel>(context);
  }

  @override
  Widget build(BuildContext context) {
    return Stack(
      children: <Widget>[
        widget.body,
        StreamBuilder(
            stream: _rootViewModel.stateStream,
            builder: (BuildContext context, AsyncSnapshot<RequestState> data) {
              if (data.hasError) {
                if (data.error is NetConnectError) {
                  return _buildNetErrorWidget();
                } else if (data.error is ServerError) {

                }
              }

              if (data.connectionState == ConnectionState.waiting) {
                return _buildLoadingWidget();
              } else if (data.connectionState == ConnectionState.active) {}

              return null;
            }),
      ],
    );
  }

  _buildNetErrorWidget() {
    return widget.errorWidget != null ? widget.errorWidget : Container();
  }

  _buildLoadingWidget() {
    return widget.dialogWidget != null ? widget.dialogWidget : Container();
  }
}
