// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'good.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

GoodBean _$GoodBeanFromJson(Map<String, dynamic> json) {
  return GoodBean()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..imgUrl = json['imgUrl'] as String
    ..title = json['title'] as String
    ..price = json['price'] as String
    ..state = json['state'] as int;
}

Map<String, dynamic> _$GoodBeanToJson(GoodBean instance) => <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'imgUrl': instance.imgUrl,
      'title': instance.title,
      'price': instance.price,
      'state': instance.state,
    };
