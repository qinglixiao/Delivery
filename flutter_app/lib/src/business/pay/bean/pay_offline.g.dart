// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'pay_offline.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

PayOffline _$PayOfflineFromJson(Map<String, dynamic> json) {
  return PayOffline()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..data = json['data'] as String
    ..errors = json['errors'] as String;
}

Map<String, dynamic> _$PayOfflineToJson(PayOffline instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'data': instance.data,
      'errors': instance.errors,
    };
