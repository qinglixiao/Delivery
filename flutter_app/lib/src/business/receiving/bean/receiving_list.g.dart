// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'receiving_list.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

ReceivingListBean _$ReceivingListBeanFromJson(Map<String, dynamic> json) {
  return ReceivingListBean()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..items = (json['items'] as List)
        ?.map((e) => e == null
            ? null
            : ReceivingListItemBean.fromJson(e as Map<String, dynamic>))
        ?.toList()
    ..pageNo = json['pageNo'] as int
    ..totalCount = json['totalCount'] as int
    ..pageSize = json['pageSize'] as int
    ..totalPage = json['totalPage'] as int;
}

Map<String, dynamic> _$ReceivingListBeanToJson(ReceivingListBean instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'items': instance.items?.map((e) => e?.toJson())?.toList(),
      'pageNo': instance.pageNo,
      'totalCount': instance.totalCount,
      'pageSize': instance.pageSize,
      'totalPage': instance.totalPage,
    };

ReceivingListItemBean _$ReceivingListItemBeanFromJson(
    Map<String, dynamic> json) {
  return ReceivingListItemBean()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..sellerPhone = json['sellerPhone'] as String
    ..numberCode = json['numberCode'] as String
    ..username = json['username'] as String
    ..sellerUserName = json['sellerUserName'] as String
    ..status = json['status'] as String
    ..createTime = json['createTime'] as String
    ..title = json['title'] as String
    ..imgUrl = json['imgUrl'] as String
    ..sendStatus = json['sendStatus'] as String
    ..repairStatus = json['repairStatus'] as String
    ..advanceTime = json['advanceTime'] as String
    ..abolishMsg = json['abolishMsg'] as String
    ..payDesc = json['payDesc'] as String
    ..numberDesc = json['numberDesc'] as String
    ..userId = json['userId'] as int
    ..sellerId = json['sellerId'] as int
    ..postFee = (json['postFee'] as num)?.toDouble()
    ..paymentFee = (json['paymentFee'] as num)?.toDouble()
    ..count = json['count'] as int
    ..sendCount = json['sendCount'] as int
    ..price = (json['price'] as num)?.toDouble()
    ..ifSend = json['ifSend'] as bool
    ..ifReject = json['ifReject'] as bool
    ..ifCostHigh = json['ifCostHigh'] as bool
    ..ifhandAffirmpay = json['ifhandAffirmpay'] as bool;
}

Map<String, dynamic> _$ReceivingListItemBeanToJson(
        ReceivingListItemBean instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'sellerPhone': instance.sellerPhone,
      'numberCode': instance.numberCode,
      'username': instance.username,
      'sellerUserName': instance.sellerUserName,
      'status': instance.status,
      'createTime': instance.createTime,
      'title': instance.title,
      'imgUrl': instance.imgUrl,
      'sendStatus': instance.sendStatus,
      'repairStatus': instance.repairStatus,
      'advanceTime': instance.advanceTime,
      'abolishMsg': instance.abolishMsg,
      'payDesc': instance.payDesc,
      'numberDesc': instance.numberDesc,
      'userId': instance.userId,
      'sellerId': instance.sellerId,
      'postFee': instance.postFee,
      'paymentFee': instance.paymentFee,
      'count': instance.count,
      'sendCount': instance.sendCount,
      'price': instance.price,
      'ifSend': instance.ifSend,
      'ifReject': instance.ifReject,
      'ifCostHigh': instance.ifCostHigh,
      'ifhandAffirmpay': instance.ifhandAffirmpay,
    };
