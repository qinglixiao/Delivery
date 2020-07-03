import 'package:flutter_lib/flutter_lib.dart';
import 'package:fluttermodule/src/business/budget/bean/good.dart';
import 'package:fluttermodule/src/business/budget/model/good_list_model.dart';

class GoodListViewModel extends BaseViewModel {
  GoodListViewModel() : super();
  GoodListModel _goodListModel = GoodListModel();

  Stream<GoodBean> get streamGood => _goodListModel.stateStream;

  void loadGoods() {
    showLoadding();
    _goodListModel.load(10).then((value) {
      hideLoadding();
    });
  }

  @override
  void dispose() {
    _goodListModel?.close();
  }
}
