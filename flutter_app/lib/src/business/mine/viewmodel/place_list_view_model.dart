import 'package:flutter_ienglish_fine/src/business/budget/bean/good_confirm.dart';
import 'package:flutter_ienglish_fine/src/business/mine/bean/place_list.dart';
import 'package:flutter_ienglish_fine/src/business/mine/model/place_detail_model.dart';
import 'package:flutter_ienglish_fine/src/business/mine/model/place_list_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

class PlaceListViewModel extends BaseViewModel {
  PlaceListModel _placeListModel = PlaceListModel();
  PlaceDetailModel _detailModel = PlaceDetailModel();

  Stream<PlaceList> get streamPlaceList =>
      _placeListModel.getStream<PlaceList>();

  Future loadPlaceList({bool isFirst}) async {
    if (isFirst) {
      showLoadding();
    }
    return _placeListModel.getPlaceList().then((value) {
      if (value?.items?.length == 0) {
        showEmptyView();
      } else {
        hideLoadding();
      }
    }).catchError((e) {
      error(e);
    });
  }

  Future<bool> setDefaultPlace(Place place) async {
    return _detailModel
        .postPlaceEditDetail(place.id, place.detailaddress, place.province,
            place.city, place.area, place.tel, place.username, true)
        .then((value) {
      if (!value.isSuccess()) {
        ProgressHUD.showText(warnText: value.message);
      }
      return value.isSuccess();
    });
  }

  Future<bool> deletePlace(String id) async {
    return _detailModel.postPlaceDelete(id).then((result) {
      if (!result.isSuccess()) {
        ProgressHUD.showText(warnText: result.message);
      }
      return result.isSuccess();
    });
  }

  @override
  void dispose() {
    super.dispose();
    _placeListModel?.close();
  }
}
