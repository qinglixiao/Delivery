import 'package:flutter_ienglish_fine/src/business/replenish/bean/replenish_detail.dart';
import 'package:flutter_ienglish_fine/src/business/replenish/model/replenish_detail_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

class ReplenishDetailViewModel extends BaseViewModel {

  ReplenishDetailViewModel() : super();

  ReplenishDetailModel _replenishDetailModel = ReplenishDetailModel();

  Stream<ReplenishDetailBean> get streamReplenishDetail=> _replenishDetailModel.getStream<ReplenishDetailBean>();

  Future loadReplenishDetail({bool isFirst , String numberCode}) async {
    if (isFirst) {
      showLoadding();
    }
    return _replenishDetailModel.getReplenishDetail(numberCode).then((value) {
      if (value == null) {
        showEmptyView();
      } else {
        hideLoadding();
      }
    }).catchError((e) {
      error(e);
    });
  }

  Future loadRejectOrder(String numberCode, Function(NetBean statusInfo) callback) async{
    return _replenishDetailModel.postRejectReplenish(numberCode).then((value) {
      callback(value);
    }).catchError((e) {
      error(e);
    });
  }

  Future loadReplenishOrder(String numberCode, Function(NetBean statusInfo) callback) async{
    return _replenishDetailModel.postReplenishOrder(numberCode).then((value) {
      callback(value);
    }).catchError((e) {
      error(e);
    });
  }

  @override
  void dispose() {
    super.dispose();
    _replenishDetailModel?.close();
  }
}
