import 'package:flutter_lib/src/base/model.dart';
import 'package:flutter_lib/src/base/root_page_state.dart';
import 'package:flutter_lib/src/base/view_model.dart';

mixin RootViewModel on BaseViewModel {
  RootModel _rootModel = RootModel();

  Stream<RequestState> get stateStream => _rootModel.stateStream;

  void request() {
    _rootModel.load();
  }

  @override
  void dispose() {
    _rootModel?.close();
  }
}

class RootModel extends BaseModel<RequestState> {

  void load() {
    Future.delayed(Duration(seconds: 3), () {
      print("加载数据...");
    }).then((value) {
      add(RequestState());
    });
  }

  @override
  void dispose() {
    // TODO: implement dispose
  }
}
