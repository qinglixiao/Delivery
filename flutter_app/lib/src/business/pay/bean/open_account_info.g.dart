// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'open_account_info.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

OpenAccountInfoBean _$OpenAccountInfoBeanFromJson(Map<String, dynamic> json) {
  return OpenAccountInfoBean()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..ret = json['ret'] as String
    ..retUrl = json['retUrl'] as String;
}

Map<String, dynamic> _$OpenAccountInfoBeanToJson(
        OpenAccountInfoBean instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'ret': instance.ret,
      'retUrl': instance.retUrl,
    };
