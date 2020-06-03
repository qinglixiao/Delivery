import 'package:flutter_lib/flutter_lib.dart';
import 'package:fluttermodule/as/business/budget/bean/good.dart';
import 'package:fluttermodule/as/business/budget/model/good_list_model.dart';

class GoodListViewModel extends BaseViewModel {
  GoodListModel _goodListModel = GoodListModel();

  Stream<GoodBean> get streamGood => _goodListModel.stateStream;

  void loadGoods() {
    _goodListModel.params = Object();
    showLoadding();
    _goodListModel.load().catchError((error) {
      error(error);
    });
  }

  @override
  void dispose() {
    _goodListModel?.close();
  }
}
