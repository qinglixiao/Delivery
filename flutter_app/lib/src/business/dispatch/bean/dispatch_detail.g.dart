// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'dispatch_detail.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

DispatchOrderDetail _$DispatchOrderDetailFromJson(Map<String, dynamic> json) {
  return DispatchOrderDetail()
    ..error_code = json['error_code'] as String
    ..message = json['message'] as String
    ..abolishMsg = json['abolishMsg'] as String
    ..advanceAbolishTime = json['advanceAbolishTime'] as int
    ..advanceTime = json['advanceTime'] as int
    ..count = json['count'] as int
    ..postFee = (json['postFee'] as num)?.toDouble()
    ..price = (json['price'] as num)?.toDouble()
    ..sellerId = json['sellerId'] as int
    ..sendCount = json['sendCount'] as int
    ..paymentFee = (json['paymentFee'] as num)?.toDouble()
    ..createTime = json['createTime'] as String
    ..imgUrl = json['imgUrl'] as String
    ..memo = json['memo'] as String
    ..numberCode = json['numberCode'] as String
    ..numberDesc = json['numberDesc'] as String
    ..payDesc = json['payDesc'] as String
    ..paymentTime = json['paymentTime'] as String
    ..receiveAddress = json['receiveAddress'] as String
    ..receiveMobile = json['receiveMobile'] as String
    ..receiveName = json['receiveName'] as String
    ..requestNo = json['requestNo'] as String
    ..sellerPhone = json['sellerPhone'] as String
    ..sellname = json['sellname'] as String
    ..status = json['status'] as String
    ..title = json['title'] as String
    ..username = json['username'] as String;
}

Map<String, dynamic> _$DispatchOrderDetailToJson(
        DispatchOrderDetail instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'message': instance.message,
      'abolishMsg': instance.abolishMsg,
      'advanceAbolishTime': instance.advanceAbolishTime,
      'advanceTime': instance.advanceTime,
      'count': instance.count,
      'postFee': instance.postFee,
      'price': instance.price,
      'sellerId': instance.sellerId,
      'sendCount': instance.sendCount,
      'paymentFee': instance.paymentFee,
      'createTime': instance.createTime,
      'imgUrl': instance.imgUrl,
      'memo': instance.memo,
      'numberCode': instance.numberCode,
      'numberDesc': instance.numberDesc,
      'payDesc': instance.payDesc,
      'paymentTime': instance.paymentTime,
      'receiveAddress': instance.receiveAddress,
      'receiveMobile': instance.receiveMobile,
      'receiveName': instance.receiveName,
      'requestNo': instance.requestNo,
      'sellerPhone': instance.sellerPhone,
      'sellname': instance.sellname,
      'status': instance.status,
      'title': instance.title,
      'username': instance.username,
    };

ExpressInfoData _$ExpressInfoDataFromJson(Map<String, dynamic> json) {
  return ExpressInfoData()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..data = json['data'] == null
        ? null
        : ExpressInfo.fromJson(json['data'] as Map<String, dynamic>);
}

Map<String, dynamic> _$ExpressInfoDataToJson(ExpressInfoData instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'data': instance.data?.toJson(),
    };

ExpressInfo _$ExpressInfoFromJson(Map<String, dynamic> json) {
  return ExpressInfo()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..receiveName = json['receiveName'] as String
    ..receiveAddress = json['receiveAddress'] as String
    ..unusedCount = json['unusedCount'] as int
    ..affirmCount = (json['affirmCount'] as num)?.toDouble()
    ..totalCount = json['totalCount'] as int
    ..usedCount = json['usedCount'] as int
    ..receiveMobile = json['receiveMobile'] as String
    ..logistics = (json['logistics'] as List)
        ?.map((e) => e == null
            ? null
            : ExpressItemInfo.fromJson(e as Map<String, dynamic>))
        ?.toList();
}

Map<String, dynamic> _$ExpressInfoToJson(ExpressInfo instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'receiveName': instance.receiveName,
      'receiveAddress': instance.receiveAddress,
      'unusedCount': instance.unusedCount,
      'affirmCount': instance.affirmCount,
      'totalCount': instance.totalCount,
      'usedCount': instance.usedCount,
      'receiveMobile': instance.receiveMobile,
      'logistics': instance.logistics?.map((e) => e?.toJson())?.toList(),
    };

ExpressItemInfo _$ExpressItemInfoFromJson(Map<String, dynamic> json) {
  return ExpressItemInfo()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..orderNO = json['orderNO'] as String
    ..quantity = json['quantity'] as int
    ..gmtModify = json['gmtModify'] as int
    ..gmtCreated = json['gmtCreated'] as int
    ..receiveAddress = json['receiveAddress'] as String
    ..ifSelfTaking = json['ifSelfTaking'] as bool
    ..userId = json['userId'] as int
    ..version = json['version'] as int
    ..numberCode = json['numberCode'] as String
    ..logisticsName = json['logisticsName'] as String
    ..receiveName = json['receiveName'] as String
    ..sellerId = json['sellerId'] as int
    ..logisticsNo = json['logisticsNo'] as String
    ..id = json['id'] as int
    ..outTime = json['outTime'] as String
    ..receiveMobile = json['receiveMobile'] as String
    ..status = json['status'] as String;
}

Map<String, dynamic> _$ExpressItemInfoToJson(ExpressItemInfo instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'orderNO': instance.orderNO,
      'quantity': instance.quantity,
      'gmtModify': instance.gmtModify,
      'gmtCreated': instance.gmtCreated,
      'receiveAddress': instance.receiveAddress,
      'ifSelfTaking': instance.ifSelfTaking,
      'userId': instance.userId,
      'version': instance.version,
      'numberCode': instance.numberCode,
      'logisticsName': instance.logisticsName,
      'receiveName': instance.receiveName,
      'sellerId': instance.sellerId,
      'logisticsNo': instance.logisticsNo,
      'id': instance.id,
      'outTime': instance.outTime,
      'receiveMobile': instance.receiveMobile,
      'status': instance.status,
    };

ExpressResultData _$ExpressResultDataFromJson(Map<String, dynamic> json) {
  return ExpressResultData()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String;
}

Map<String, dynamic> _$ExpressResultDataToJson(ExpressResultData instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
    };
