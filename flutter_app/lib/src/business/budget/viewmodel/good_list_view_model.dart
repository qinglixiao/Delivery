import 'package:flutter_ienglish_fine/src/business/budget/bean/good.dart';
import 'package:flutter_ienglish_fine/src/business/budget/model/good_list_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

class GoodListViewModel extends BaseViewModel {
  GoodListModel _goodListModel = GoodListModel();

  GoodListModel get model => _goodListModel;

  Stream<Good> get streamGood => _goodListModel.getStream<Good>();

  Future loadGoods({bool isFirst}) async {
    if (isFirst) {
      showLoadding();
    }
    return _goodListModel.getGoodList().then((value) {
      if (value?.items?.length == 0) {
        showEmptyView();
      } else {
        hideLoadding();
      }
    }).catchError((e) {
      error(e);
    });
  }

  @override
  void dispose() {
    super.dispose();
    _goodListModel?.close();
  }
}
