import 'package:flutter_ienglish_fine/src/business/budget/bean/good_confirm.dart';
import 'package:flutter_ienglish_fine/src/business/budget/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';

class GoodConfirmModel extends BaseModel {
  Future getPlaceDetail(String placeId) async {
    return IEnglishNetClient().post(NET_GET_PLACE_DETAIL,
        queryParameters: {'id': placeId}).then((value) {
      return add(Place.fromJson(value.data));
    });
  }

  Future getNormalPlaceDetail() async {
    return IEnglishNetClient().get(NET_GET_NORMAL_PLACE_DETAIL).then((value) {
      return add(Place.fromJson(value.data));
    });
  }

  Future getGoodConfirmInfo(List<String> codes) async {
    String _params = '';
    if(codes != null){
      for(String code in codes){
        if(_params == null || _params.length == 0){
          _params = '?codes='+code;
        }
        else{
          _params = _params + '&codes='+code;
        }
      }
    }
    return IEnglishNetClient().post(NET_GET_ORDER_INTRO+_params).then((value) {
      return add(ServiceProviderLevel.fromJson(value.data));
    });
  }

  Future getFundQuery(String code) async {
    return IEnglishNetClient().post(NET_GET_FUND_QUERY,
        queryParameters: {'numberDesc': code}).then((value) {
      return add(FundQuery.fromJson(value.data));
    });
  }

  Future getCollocationInfo(String code) async {
    return IEnglishNetClient().post(NET_GET_COLLOCATION_INFO,
        queryParameters: {'collocationId': code}).then((value) {
      return add(CollocationInfo.fromJson(value.data));
    });
  }

  Future getPersionStatus(String code) async {
    return IEnglishNetClient().post(NET_GET_CHECK_PERSION_STATUS,
        queryParameters: {'riseRankNum': code}).then((value) {
      return add(PersionStatusInfo.fromJson(value.data));
    });
  }

  Future getBuyCollocation(String count, String receiveAddress,
      String receiveMobile, String receiveName, String collocationId) async {
    return IEnglishNetClient().post(NET_GET_BUY_COLLOCATION, queryParameters: {
      'count': count,
      'receiveAddress': receiveAddress,
      'receiveMobile': receiveMobile,
      'receiveName': receiveName,
      'collocationId': collocationId
    }).then((value) {
      return add(BuyCollocationInfo.fromJson(value.data));
    });
  }

  Future getBuySingleCollocation(
      String count,
      String ifUpgrade,
      String receiveAddress,
      String receiveMobile,
      String receiveName,
      String collocationId) async {
    return IEnglishNetClient().post(NET_GET_BUY_SINGLE, queryParameters: {
      'count': count,
      'ifUpgrade': ifUpgrade,
      'receiveAddress': receiveAddress,
      'receiveMobile': receiveMobile,
      'receiveName': receiveName,
      'collocationId': collocationId
    }).then((value) {
      return add(BuyCollocationInfo.fromJson(value.data));
    });
  }

  Future getAccountCountInfo() async {
    return IEnglishNetClient().get(NET_GET_ACCOUNT_COUNT).then((value) {
      return add(AccountCountBean.fromJson(value.data));
    });
  }

  @override
  void dispose() {
    // TODO: implement dispose
  }
}
