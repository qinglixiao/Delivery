import 'package:flutter_lib/src/base/root_page_state.dart';
import 'package:flutter_lib/src/base/root_view_model.dart';

abstract class BaseViewModel {
  RootModel _parentModel = RootModel();

  Stream<RequestState> get parentState => _parentModel.stateStream;

  void showLoadding({txt}) {
    _parentModel.showLoadding(txt: txt);
  }

  void hideLoadding() {
    _parentModel.hideLoadding();
  }

  void error(Error error) {
    _parentModel.error(error);
  }

  void dispose() {
    _parentModel.close();
  }
}
