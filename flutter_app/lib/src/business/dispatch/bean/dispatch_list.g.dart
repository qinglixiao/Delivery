// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'dispatch_list.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

DispatchListBean _$DispatchListBeanFromJson(Map<String, dynamic> json) {
  return DispatchListBean()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..items = (json['items'] as List)
        ?.map((e) => e == null
            ? null
            : DispatchListItem.fromJson(e as Map<String, dynamic>))
        ?.toList()
    ..pageNo = json['pageNo'] as int
    ..totalCount = json['totalCount'] as int
    ..pageSize = json['pageSize'] as int
    ..totalPage = json['totalPage'] as int
    ..count = json['count'] as int
    ..frozenCount = json['frozenCount'] as int;
}

Map<String, dynamic> _$DispatchListBeanToJson(DispatchListBean instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'items': instance.items?.map((e) => e?.toJson())?.toList(),
      'pageNo': instance.pageNo,
      'totalCount': instance.totalCount,
      'pageSize': instance.pageSize,
      'totalPage': instance.totalPage,
      'count': instance.count,
      'frozenCount': instance.frozenCount,
    };

DispatchListItem _$DispatchListItemFromJson(Map<String, dynamic> json) {
  return DispatchListItem()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..abolishMsg = json['abolishMsg'] as String
    ..advanceAbolishTime = json['advanceAbolishTime'] as int
    ..advanceTime = json['advanceTime'] as int
    ..count = json['count'] as int
    ..ifCostHigh = json['ifCostHigh'] as bool
    ..ifReject = json['ifReject'] as bool
    ..ifSend = json['ifSend'] as bool
    ..ifhandAffirmpay = json['ifhandAffirmpay'] as bool
    ..postFee = (json['postFee'] as num)?.toDouble()
    ..price = (json['price'] as num)?.toDouble()
    ..sellerId = json['sellerId'] as int
    ..sendCount = json['sendCount'] as int
    ..userId = json['userId'] as int
    ..paymentFee = (json['paymentFee'] as num)?.toDouble()
    ..createTime = json['createTime'] as String
    ..imgUrl = json['imgUrl'] as String
    ..memo = json['memo'] as String
    ..numberCode = json['numberCode'] as String
    ..numberDesc = json['numberDesc'] as String
    ..payDesc = json['payDesc'] as String
    ..paymentTime = json['paymentTime'] as String
    ..repairStatus = json['repairStatus'] as String
    ..sellerPhone = json['sellerPhone'] as String
    ..sellerUserName = json['sellerUserName'] as String
    ..sendStatus = json['sendStatus'] as String
    ..status = json['status'] as String
    ..title = json['title'] as String
    ..username = json['username'] as String;
}

Map<String, dynamic> _$DispatchListItemToJson(DispatchListItem instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'abolishMsg': instance.abolishMsg,
      'advanceAbolishTime': instance.advanceAbolishTime,
      'advanceTime': instance.advanceTime,
      'count': instance.count,
      'ifCostHigh': instance.ifCostHigh,
      'ifReject': instance.ifReject,
      'ifSend': instance.ifSend,
      'ifhandAffirmpay': instance.ifhandAffirmpay,
      'postFee': instance.postFee,
      'price': instance.price,
      'sellerId': instance.sellerId,
      'sendCount': instance.sendCount,
      'userId': instance.userId,
      'paymentFee': instance.paymentFee,
      'createTime': instance.createTime,
      'imgUrl': instance.imgUrl,
      'memo': instance.memo,
      'numberCode': instance.numberCode,
      'numberDesc': instance.numberDesc,
      'payDesc': instance.payDesc,
      'paymentTime': instance.paymentTime,
      'repairStatus': instance.repairStatus,
      'sellerPhone': instance.sellerPhone,
      'sellerUserName': instance.sellerUserName,
      'sendStatus': instance.sendStatus,
      'status': instance.status,
      'title': instance.title,
      'username': instance.username,
    };
