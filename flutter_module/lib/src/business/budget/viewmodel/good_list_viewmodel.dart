import 'package:flutter_lib/flutter_lib.dart';
import 'package:fluttermodule/src/business/budget/bean/good.dart';
import 'package:fluttermodule/src/business/budget/model/good_list_model.dart';

class GoodListViewModel extends BaseViewModel {
  GoodListViewModel() : super();
  GoodListModel _goodListModel = GoodListModel();

  Stream<List<GoodBean>> get streamGood => _goodListModel.stateStream;

  Future load(bool refresh) async {
    return _goodListModel.load(refresh);
  }

  Future<List<GoodBean>> loadGoods() async {
    showLoadding();
    return load(true).then((value) {
      hideLoadding();
      return value;
    });
  }

  @override
  void dispose() {
    _goodListModel?.close();
  }
}
