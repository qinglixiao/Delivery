import 'package:flutter_ienglish_fine/src/business/budget/bean/good_detail.dart';
import 'package:flutter_ienglish_fine/src/business/budget/model/good_detail_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

///
///create by lx
///date 2020/7/24
///

class GoodDetailViewModel extends BaseViewModel {
  GoodDetailModel _model = GoodDetailModel();

  Stream<GoodDetail> get detailStream => _model.getStream<GoodDetail>();

  Future getGoodDetail(int collocationId) async {
    showLoadding();
    return _model.getGoodDetail(collocationId).then((value) {
      if (value == null) {
        showEmptyView();
      } else {
        hideLoadding();
      }
    }).catchError((e) {
      error(e);
    });
  }
}
