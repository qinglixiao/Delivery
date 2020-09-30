// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'replenish_detail.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

ReplenishDetailBean _$ReplenishDetailBeanFromJson(Map<String, dynamic> json) {
  return ReplenishDetailBean()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..sellerPhone = json['sellerPhone'] as String
    ..numberCode = json['numberCode'] as String
    ..username = json['username'] as String
    ..sellname = json['sellname'] as String
    ..status = json['status'] as String
    ..createTime = json['createTime'] as String
    ..title = json['title'] as String
    ..imgUrl = json['imgUrl'] as String
    ..sendStatus = json['sendStatus'] as String
    ..repairStatus = json['repairStatus'] as String
    ..advanceAbolishTime = json['advanceAbolishTime'] as int
    ..abolishMsg = json['abolishMsg'] as String
    ..payDesc = json['payDesc'] as String
    ..numberDesc = json['numberDesc'] as String
    ..receiveName = json['receiveName'] as String
    ..receiveAddress = json['receiveAddress'] as String
    ..receiveMobile = json['receiveMobile'] as String
    ..paymentTime = json['paymentTime'] as String
    ..memo = json['memo'] as String
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

Map<String, dynamic> _$ReplenishDetailBeanToJson(
        ReplenishDetailBean instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'sellerPhone': instance.sellerPhone,
      'numberCode': instance.numberCode,
      'username': instance.username,
      'sellname': instance.sellname,
      'status': instance.status,
      'createTime': instance.createTime,
      'title': instance.title,
      'imgUrl': instance.imgUrl,
      'sendStatus': instance.sendStatus,
      'repairStatus': instance.repairStatus,
      'advanceAbolishTime': instance.advanceAbolishTime,
      'abolishMsg': instance.abolishMsg,
      'payDesc': instance.payDesc,
      'numberDesc': instance.numberDesc,
      'receiveName': instance.receiveName,
      'receiveAddress': instance.receiveAddress,
      'receiveMobile': instance.receiveMobile,
      'paymentTime': instance.paymentTime,
      'memo': instance.memo,
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
