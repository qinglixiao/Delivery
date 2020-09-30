// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'good_detail.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

GoodDetail _$GoodDetailFromJson(Map<String, dynamic> json) {
  return GoodDetail()
    ..error_code = json['error_code'] as String
    ..collocationId = (json['collocationId'] as num)?.toDouble()
    ..description = json['description'] as String
    ..directEpithets = (json['directEpithets'] as num)?.toDouble()
    ..directRankNum = (json['directRankNum'] as num)?.toDouble()
    ..errorCode = json['errorCode'] as String
    ..extId = (json['extId'] as num)?.toDouble()
    ..ifPlain = json['ifPlain'] as bool
    ..imgUrl = json['imgUrl'] as String
    ..message = json['message'] as String
    ..price = (json['price'] as num)?.toDouble()
    ..quantity = (json['quantity'] as num)?.toDouble()
    ..rankNum = (json['rankNum'] as num)?.toDouble()
    ..relayDesc = json['relayDesc'] as String
    ..relayTitle = json['relayTitle'] as String
    ..requestNo = json['requestNo'] as String
    ..text = json['text'] as String
    ..title = json['title'] as String
    ..totalPrice = (json['totalPrice'] as num)?.toDouble();
}

Map<String, dynamic> _$GoodDetailToJson(GoodDetail instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'collocationId': instance.collocationId,
      'description': instance.description,
      'directEpithets': instance.directEpithets,
      'directRankNum': instance.directRankNum,
      'errorCode': instance.errorCode,
      'extId': instance.extId,
      'ifPlain': instance.ifPlain,
      'imgUrl': instance.imgUrl,
      'message': instance.message,
      'price': instance.price,
      'quantity': instance.quantity,
      'rankNum': instance.rankNum,
      'relayDesc': instance.relayDesc,
      'relayTitle': instance.relayTitle,
      'requestNo': instance.requestNo,
      'text': instance.text,
      'title': instance.title,
      'totalPrice': instance.totalPrice,
    };
