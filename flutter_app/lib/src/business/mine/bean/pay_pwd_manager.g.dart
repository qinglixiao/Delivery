// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'pay_pwd_manager.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

PayPwdManager _$PayPwdManagerFromJson(Map<String, dynamic> json) {
  return PayPwdManager()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..data = json['data'] == null
        ? null
        : PayPwdManagerData.fromJson(json['data'] as Map<String, dynamic>);
}

Map<String, dynamic> _$PayPwdManagerToJson(PayPwdManager instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'data': instance.data?.toJson(),
    };

PayPwdManagerData _$PayPwdManagerDataFromJson(Map<String, dynamic> json) {
  return PayPwdManagerData()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..retUrl = json['retUrl'] as String;
}

Map<String, dynamic> _$PayPwdManagerDataToJson(PayPwdManagerData instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'retUrl': instance.retUrl,
    };
