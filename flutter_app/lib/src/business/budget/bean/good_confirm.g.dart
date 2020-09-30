// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'good_confirm.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

Place _$PlaceFromJson(Map<String, dynamic> json) {
  return Place()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..id = json['id'] as String
    ..username = json['username'] as String
    ..province = json['province'] as String
    ..city = json['city'] as String
    ..area = json['area'] as String
    ..detailaddress = json['detailaddress'] as String
    ..tel = json['tel'] as String
    ..priority = json['priority'] as bool;
}

Map<String, dynamic> _$PlaceToJson(Place instance) => <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'id': instance.id,
      'username': instance.username,
      'province': instance.province,
      'city': instance.city,
      'area': instance.area,
      'detailaddress': instance.detailaddress,
      'tel': instance.tel,
      'priority': instance.priority,
    };

ServiceProviderLevel _$ServiceProviderLevelFromJson(Map<String, dynamic> json) {
  return ServiceProviderLevel()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..rankNum = json['rankNum'] as int
    ..rankName = json['rankName'] as String
    ..startCount = json['startCount'] as int
    ..price = (json['price'] as num)?.toDouble()
    ..nextCount = json['nextCount'] as int
    ..nextRum = json['nextRum'] as int
    ..nextName = json['nextName'] as String
    ..nextPrice = (json['nextPrice'] as num)?.toDouble()
    ..repairCount = json['repairCount'] as int
    ..numberDesc = json['numberDesc'] as String
    ..title = json['title'] as String
    ..imgUrl = json['imgUrl'] as String;
}

Map<String, dynamic> _$ServiceProviderLevelToJson(
        ServiceProviderLevel instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'rankNum': instance.rankNum,
      'rankName': instance.rankName,
      'startCount': instance.startCount,
      'price': instance.price,
      'nextCount': instance.nextCount,
      'nextRum': instance.nextRum,
      'nextName': instance.nextName,
      'nextPrice': instance.nextPrice,
      'repairCount': instance.repairCount,
      'numberDesc': instance.numberDesc,
      'title': instance.title,
      'imgUrl': instance.imgUrl,
    };

FundQuery _$FundQueryFromJson(Map<String, dynamic> json) {
  return FundQuery()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..data = json['data'] == null
        ? null
        : FundQueryDetail.fromJson(json['data'] as Map<String, dynamic>);
}

Map<String, dynamic> _$FundQueryToJson(FundQuery instance) => <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'data': instance.data?.toJson(),
    };

CollocationInfo _$CollocationInfoFromJson(Map<String, dynamic> json) {
  return CollocationInfo()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..collocationId = json['collocationId'] as int
    ..description = json['description'] as String
    ..directEpithets = json['directEpithets'] as int
    ..directRankNum = json['directRankNum'] as int
    ..extId = json['extId'] as int
    ..ifPlain = json['ifPlain'] as bool
    ..imgUrl = json['imgUrl'] as String
    ..price = (json['price'] as num)?.toDouble()
    ..quantity = (json['quantity'] as num)?.toDouble()
    ..rankNum = json['rankNum'] as int
    ..relayDesc = json['relayDesc'] as String
    ..relayTitle = json['relayTitle'] as String
    ..text = json['text'] as String
    ..title = json['title'] as String
    ..totalPrice = (json['totalPrice'] as num)?.toDouble();
}

Map<String, dynamic> _$CollocationInfoToJson(CollocationInfo instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'collocationId': instance.collocationId,
      'description': instance.description,
      'directEpithets': instance.directEpithets,
      'directRankNum': instance.directRankNum,
      'extId': instance.extId,
      'ifPlain': instance.ifPlain,
      'imgUrl': instance.imgUrl,
      'price': instance.price,
      'quantity': instance.quantity,
      'rankNum': instance.rankNum,
      'relayDesc': instance.relayDesc,
      'relayTitle': instance.relayTitle,
      'text': instance.text,
      'title': instance.title,
      'totalPrice': instance.totalPrice,
    };

PersionStatusInfo _$PersionStatusInfoFromJson(Map<String, dynamic> json) {
  return PersionStatusInfo()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..extraFee = (json['extraFee'] as num)?.toDouble()
    ..ifOpenAcct = json['ifOpenAcct'] as bool
    ..ifaffirm = json['ifaffirm'] as bool
    ..ifextraFee = json['ifextraFee'] as bool
    ..ifpersion = json['ifpersion'] as bool;
}

Map<String, dynamic> _$PersionStatusInfoToJson(PersionStatusInfo instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'extraFee': instance.extraFee,
      'ifOpenAcct': instance.ifOpenAcct,
      'ifaffirm': instance.ifaffirm,
      'ifextraFee': instance.ifextraFee,
      'ifpersion': instance.ifpersion,
    };

BuyCollocationInfo _$BuyCollocationInfoFromJson(Map<String, dynamic> json) {
  return BuyCollocationInfo()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..numberCode = json['numberCode'] as String
    ..numberDesc = json['numberDesc'] as String
    ..payDesc = json['payDesc'] as String
    ..payFee = (json['payFee'] as num)?.toDouble();
}

Map<String, dynamic> _$BuyCollocationInfoToJson(BuyCollocationInfo instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'numberCode': instance.numberCode,
      'numberDesc': instance.numberDesc,
      'payDesc': instance.payDesc,
      'payFee': instance.payFee,
    };

AccountCountBean _$AccountCountBeanFromJson(Map<String, dynamic> json) {
  return AccountCountBean()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..data = json['data'] == null
        ? null
        : AccountCountInfoBean.fromJson(json['data'] as Map<String, dynamic>);
}

Map<String, dynamic> _$AccountCountBeanToJson(AccountCountBean instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'data': instance.data?.toJson(),
    };

AccountCountInfoBean _$AccountCountInfoBeanFromJson(Map<String, dynamic> json) {
  return AccountCountInfoBean()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..countDistributeAccount = json['countDistributeAccount'] as int
    ..countFreezingAccount = json['countFreezingAccount'] as int
    ..countTotalAccount = json['countTotalAccount'] as int;
}

Map<String, dynamic> _$AccountCountInfoBeanToJson(
        AccountCountInfoBean instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'countDistributeAccount': instance.countDistributeAccount,
      'countFreezingAccount': instance.countFreezingAccount,
      'countTotalAccount': instance.countTotalAccount,
    };
