import 'package:flutter/cupertino.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_lib/src/base/model.dart';
import 'package:flutter_lib/src/base/root_page_state.dart';

class RootModel extends BaseModel<RequestState> {
  RootModel() : super();

  void error(Error error) {
    add(RequestState.error(error));
  }

  void showLoadding({String txt}) {
    add(RequestState.state(ConnectionState.waiting, data: txt));
  }

  void hideLoadding() {
    add(RequestState.state(ConnectionState.done));
  }

  @override
  void dispose() {}
}
