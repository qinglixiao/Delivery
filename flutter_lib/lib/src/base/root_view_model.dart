import 'package:flutter/cupertino.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_lib/src/base/model.dart';
import 'package:flutter_lib/src/base/root_page_state.dart';

class RootModel extends BaseModel {
  RootModel() : super();

  void error(Exception error) {
    add(RequestState.error(error));
  }

  void showLoadding({dynamic txt}) {
    add(RequestState.state(ConnectionState.waiting, data: txt));
  }

  void hideLoadding() {
    add(RequestState.state(ConnectionState.none));
  }

  void showEmptyView() {
    add(RequestState.state(ConnectionState.done, data: null));
  }

  void hideEmptyView() {
    add(RequestState.state(ConnectionState.none, data: Object()));
  }
}