// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'collocation_activity_list.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

CollocationActivityList _$CollocationActivityListFromJson(
    Map<String, dynamic> json) {
  return CollocationActivityList()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..items = (json['items'] as List)
        ?.map((e) => e == null
            ? null
            : CollocationActivityListItem.fromJson(e as Map<String, dynamic>))
        ?.toList();
}

Map<String, dynamic> _$CollocationActivityListToJson(
        CollocationActivityList instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'items': instance.items?.map((e) => e?.toJson())?.toList(),
    };

CollocationActivityListItem _$CollocationActivityListItemFromJson(
    Map<String, dynamic> json) {
  return CollocationActivityListItem()
    ..id = json['id'] as int
    ..title = json['title'] as String
    ..coverImgUrl = json['coverImgUrl'] as String
    ..relayDesc = json['relayDesc'] as String
    ..directRankNums = json['directRankNums'] as String
    ..totalPrice = (json['totalPrice'] as num)?.toDouble()
    ..price = (json['price'] as num)?.toDouble()
    ..quantity = json['quantity'] as int
    ..ifPlain = json['ifPlain'] as bool;
}

Map<String, dynamic> _$CollocationActivityListItemToJson(
        CollocationActivityListItem instance) =>
    <String, dynamic>{
      'id': instance.id,
      'title': instance.title,
      'coverImgUrl': instance.coverImgUrl,
      'relayDesc': instance.relayDesc,
      'directRankNums': instance.directRankNums,
      'totalPrice': instance.totalPrice,
      'price': instance.price,
      'quantity': instance.quantity,
      'ifPlain': instance.ifPlain,
    };
