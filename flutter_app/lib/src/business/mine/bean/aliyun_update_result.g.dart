// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'aliyun_update_result.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

AliyunUpdateResult _$AliyunUpdateResultFromJson(Map<String, dynamic> json) {
  return AliyunUpdateResult()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..responseBody = json['responseBody'] as String;
}

Map<String, dynamic> _$AliyunUpdateResultToJson(AliyunUpdateResult instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'responseBody': instance.responseBody,
    };
