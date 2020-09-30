import 'package:flutter_ienglish_fine/src/business/mine/model/mine_buy_detail_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

class MineBuyDetailViewModel extends BaseViewModel {
  MineBuyDetailModel _mineBuyDetailModel = MineBuyDetailModel();

  Stream<NetBean> get streamCancelOrder =>
      _mineBuyDetailModel.getStream<NetBean>();

  Future loadCancelOrder(String code,String message,Function(NetBean dataInfo) callback) async {
    return _mineBuyDetailModel.postCancelOrder(code,message).then((value) {
      callback(value);
    }).catchError((e) {
      error(e);
    });
  }

  @override
  void dispose() {
    super.dispose();
    _mineBuyDetailModel?.close();
  }
}
