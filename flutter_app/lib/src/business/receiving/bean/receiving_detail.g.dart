// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'receiving_detail.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

ReceivingDetailBean _$ReceivingDetailBeanFromJson(Map<String, dynamic> json) {
  return ReceivingDetailBean()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String;
}

Map<String, dynamic> _$ReceivingDetailBeanToJson(
        ReceivingDetailBean instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
    };
