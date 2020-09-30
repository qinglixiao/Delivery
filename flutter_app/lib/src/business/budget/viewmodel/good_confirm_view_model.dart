import 'package:flutter_ienglish_fine/src/business/budget/bean/good_confirm.dart';
import 'package:flutter_ienglish_fine/src/business/budget/model/good_confirm_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

class GoodConfirmViewModel extends BaseViewModel {
  GoodConfirmViewModel() : super();

  GoodConfirmModel _placeDetailModel = GoodConfirmModel();

  Stream<Place> get streamPlace =>  _placeDetailModel.getStream<Place>();
  Stream<ServiceProviderLevel> get streamGoodConfirmInfo => _placeDetailModel.getStream<ServiceProviderLevel>();
  Stream<FundQuery> get streamFundQuery => _placeDetailModel.getStream<FundQuery>();
  Stream<AccountCountBean> get streamAccountCount => _placeDetailModel.getStream<AccountCountBean>();


  Future getConfirmata(List<String> codes,String placeId) async{
    loadPlaceDetail(placeId);
    loadGoodConfirmInfo(codes);
    loadFundQuery(SpUtil.getUserNumberDesc());
    if(codes!=null&&codes.length>0){
      loadAccountCountInfo();
    }
  }


  Future loadPlaceDetail(String placeId) async {
    if(placeId != null){
      return _placeDetailModel.getPlaceDetail(placeId).then((value) {

      }).catchError((e) {
        error(e);
      });
    }
    else{
      return _placeDetailModel.getNormalPlaceDetail().then((value) {

      }).catchError((e) {
        error(e);
      });
    }
  }

  Future loadGoodConfirmInfo(List<String> codes) async {
    return _placeDetailModel.getGoodConfirmInfo(codes).then((value) {
    }).catchError((e) {
      error(e);
    });
  }

  Future loadFundQuery(String code) async {
    return _placeDetailModel.getFundQuery(code).then((value) {

    }).catchError((e) {
      error(e);
    });
  }

  Future loadCollocationInfo(String collocationId,Function(bool success, CollocationInfo collocationInfo) callback) async {
    return _placeDetailModel.getCollocationInfo(collocationId).then((value) {
      callback(true,value);
    }).catchError((e) {
      error(e);
    });
  }

  Future loadPersionStatusInfo(String code, Function(bool success, PersionStatusInfo persionStatusInfo) callback) async {
    return _placeDetailModel.getPersionStatus(code).then((value) {
      callback(true,value);
    }).catchError((e) {
      error(e);
    });
  }

  Future loadBuyCollocation(String count,String receiveAddress,String receiveMobile,String receiveName,String collocationId,Function(bool success, BuyCollocationInfo buyCollocationInfo) callback){
    return _placeDetailModel.getBuyCollocation(count, receiveAddress, receiveMobile, receiveName, collocationId).then((value) {
      callback(true,value);
    }).catchError((e) {
      error(e);
    });
  }

  Future loadBuySingleCollocation(String count,String ifUpgrade,String receiveAddress,String receiveMobile,String receiveName,String collocationId,Function(bool success, BuyCollocationInfo buyCollocationInfo) callback){
    return _placeDetailModel.getBuySingleCollocation(count, ifUpgrade, receiveAddress, receiveMobile, receiveName, collocationId).then((value) {
      callback(true,value);
    }).catchError((e) {
      error(e);
    });
  }

  Future loadAccountCountInfo(){
    return _placeDetailModel.getAccountCountInfo().then((value) {
    }).catchError((e) {
      error(e);
    });
  }

  @override
  void dispose() {
    super.dispose();
    _placeDetailModel?.close();
  }
}
