import 'package:flutter_lib/src/base/root_page_state.dart';
import 'package:flutter_lib/src/base/root_view_model.dart';

import '../../flutter_lib.dart';

abstract class BaseViewModel {
  RootModel _parentModel = RootModel();

  Stream<RequestState> get parentState =>
      _parentModel.getStream<RequestState>();

  void showLoadding({txt}) {
    Future(() => _parentModel.showLoadding(txt: txt));
  }

  void hideLoadding() {
    _parentModel.hideLoadding();
  }

  void showEmptyView() {
    Future(() => _parentModel.showEmptyView());
  }

  void hideEmptyView() {
    _parentModel.hideEmptyView();
  }

  void netError() {
    _parentModel.error(NetworkError());
  }

  void error(Exception e) {
    _parentModel.error(e);
  }

  void unBindStream() {
    _parentModel.unBindStream();
    onUnbindStream();
  }

  void onUnbindStream() {}

  void dispose() {
    _parentModel.close();
  }
}
