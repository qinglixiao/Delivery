// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'good.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

Good _$GoodFromJson(Map<String, dynamic> json) {
  return Good()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..items = (json['items'] as List)
        ?.map((e) =>
            e == null ? null : GoodItem.fromJson(e as Map<String, dynamic>))
        ?.toList();
}

Map<String, dynamic> _$GoodToJson(Good instance) => <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'items': instance.items?.map((e) => e?.toJson())?.toList(),
    };

GoodItem _$GoodItemFromJson(Map<String, dynamic> json) {
  return GoodItem()
    ..coverImgUrl = json['coverImgUrl'] as String
    ..directRankNums = json['directRankNums'] as String
    ..id = json['id'] as int
    ..ifPlain = json['ifPlain'] as bool
    ..price = (json['price'] as num)?.toDouble()
    ..quantity = json['quantity'] as int
    ..relayDesc = json['relayDesc'] as String
    ..title = json['title'] as String
    ..totalPrice = (json['totalPrice'] as num)?.toDouble();
}

Map<String, dynamic> _$GoodItemToJson(GoodItem instance) => <String, dynamic>{
      'coverImgUrl': instance.coverImgUrl,
      'directRankNums': instance.directRankNums,
      'id': instance.id,
      'ifPlain': instance.ifPlain,
      'price': instance.price,
      'quantity': instance.quantity,
      'relayDesc': instance.relayDesc,
      'title': instance.title,
      'totalPrice': instance.totalPrice,
    };
