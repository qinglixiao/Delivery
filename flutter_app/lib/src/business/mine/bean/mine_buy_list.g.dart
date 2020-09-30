// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'mine_buy_list.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

MineBuyList _$MineBuyListFromJson(Map<String, dynamic> json) {
  return MineBuyList()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..pageNo = json['pageNo'] as int
    ..totalCount = json['totalCount'] as int
    ..pageSize = json['pageSize'] as int
    ..totalPage = json['totalPage'] as int
    ..items = (json['items'] as List)
        ?.map((e) => e == null
            ? null
            : MineBuyListItem.fromJson(e as Map<String, dynamic>))
        ?.toList();
}

Map<String, dynamic> _$MineBuyListToJson(MineBuyList instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'pageNo': instance.pageNo,
      'totalCount': instance.totalCount,
      'pageSize': instance.pageSize,
      'totalPage': instance.totalPage,
      'items': instance.items?.map((e) => e?.toJson())?.toList(),
    };

MineBuyListItem _$MineBuyListItemFromJson(Map<String, dynamic> json) {
  return MineBuyListItem()
    ..sellerPhone = json['sellerPhone'] as String
    ..userId = json['userId'] as int
    ..numberCode = json['numberCode'] as String
    ..username = json['username'] as String
    ..sellerId = json['sellerId'] as int
    ..sellerUserName = json['sellerUserName'] as String
    ..status = json['status'] as String
    ..createTime = json['createTime'] as String
    ..title = json['title'] as String
    ..imgUrl = json['imgUrl'] as String
    ..abolishMsg = json['abolishMsg'] as String
    ..payDesc = json['payDesc'] as String
    ..numberDesc = json['numberDesc'] as String
    ..postFee = (json['postFee'] as num)?.toDouble()
    ..paymentFee = (json['paymentFee'] as num)?.toDouble()
    ..count = json['count'] as int
    ..sendCount = json['sendCount'] as int
    ..price = (json['price'] as num)?.toDouble()
    ..repairStatus = json['repairStatus'] as String
    ..sendStatus = json['sendStatus'] as String
    ..ifSend = json['ifSend'] as bool
    ..ifReject = json['ifReject'] as bool
    ..ifCostHigh = json['ifCostHigh'] as bool
    ..advanceTime = json['advanceTime'] as String
    ..ifhandAffirmpay = json['ifhandAffirmpay'] as bool;
}

Map<String, dynamic> _$MineBuyListItemToJson(MineBuyListItem instance) =>
    <String, dynamic>{
      'sellerPhone': instance.sellerPhone,
      'userId': instance.userId,
      'numberCode': instance.numberCode,
      'username': instance.username,
      'sellerId': instance.sellerId,
      'sellerUserName': instance.sellerUserName,
      'status': instance.status,
      'createTime': instance.createTime,
      'title': instance.title,
      'imgUrl': instance.imgUrl,
      'abolishMsg': instance.abolishMsg,
      'payDesc': instance.payDesc,
      'numberDesc': instance.numberDesc,
      'postFee': instance.postFee,
      'paymentFee': instance.paymentFee,
      'count': instance.count,
      'sendCount': instance.sendCount,
      'price': instance.price,
      'repairStatus': instance.repairStatus,
      'sendStatus': instance.sendStatus,
      'ifSend': instance.ifSend,
      'ifReject': instance.ifReject,
      'ifCostHigh': instance.ifCostHigh,
      'advanceTime': instance.advanceTime,
      'ifhandAffirmpay': instance.ifhandAffirmpay,
    };
