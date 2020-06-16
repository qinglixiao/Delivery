import 'package:flutter_lib/flutter_lib.dart';
import 'package:fluttermodule/as/business/budget/bean/good.dart';
import 'package:fluttermodule/as/business/budget/model/good_list_model.dart';

class GoodListViewModel extends BaseViewModel {
  GoodListViewModel() : super();
  GoodListModel _goodListModel = GoodListModel();

  Stream<GoodBean> get streamGood => _goodListModel.stateStream;

  void loadGoods() {
    _goodListModel.params = Object();
    showLoadding();
    _goodListModel
        .load()
        .then((value) => {
              hideLoadding(),
            })
        .catchError((e) {
      error(e);
    });
  }

  @override
  void dispose() {
    _goodListModel?.close();
  }
}
