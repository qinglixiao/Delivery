import 'package:flutter_ienglish_fine/src/business/budget/bean/good_confirm.dart';
import 'package:flutter_ienglish_fine/src/business/mine/bean/place_list.dart';
import 'package:flutter_ienglish_fine/src/business/mine/model/place_detail_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

class PlaceDetailViewModel extends BaseViewModel {
  PlaceDetailModel _placeDetailModel = PlaceDetailModel();

  Stream<Place> get streamPlaceDetail => _placeDetailModel.getStream<Place>();

  Future loadPlaceDetail(String placeId) async {
    showLoadding();
    return _placeDetailModel
        .getPlaceDetail(placeId)
        .then((value) {
          hideLoadding();
    })
        .catchError((e) {
      error(e);
    });
  }

  Future loadPlaceEdit(
      String id,
      String detailaddress,
      String province,
      String city,
      String area,
      String tel,
      String username,
      bool priority,
      Function(NetBean info) callback) async {
    ProgressHUD.showLoading();
    return _placeDetailModel
        .postPlaceEditDetail(
            id, detailaddress, province, city, area, tel, username, priority)
        .then((value) {
          ProgressHUD.hiddenHUD();
          callback(value);
    }).catchError((e) {
      ProgressHUD.hiddenHUD();
      error(e);
    });
  }

  Future loadPlaceAdd(
      String detailaddress,
      String province,
      String city,
      String area,
      String tel,
      String username,
      bool priority,
      Function(NetBean info) callback) async {
    ProgressHUD.showLoading();
    return _placeDetailModel
        .postPlaceAddDetail(
            detailaddress, province, city, area, tel, username, priority)
        .then((value) {
      ProgressHUD.hiddenHUD();
      callback(value);
    }).catchError((e) {
      ProgressHUD.hiddenHUD();
      error(e);
    });
  }

  Future loadPlaceDelete(String id, Function(NetBean info) callback) async {
    ProgressHUD.showLoading();
    return _placeDetailModel.postPlaceDelete(id).then((value) {
      ProgressHUD.hiddenHUD();
      callback(value);
    }).catchError((e) {
      ProgressHUD.hiddenHUD();
      error(e);
    });
  }

  @override
  void dispose() {
    super.dispose();
    _placeDetailModel?.close();
  }
}
