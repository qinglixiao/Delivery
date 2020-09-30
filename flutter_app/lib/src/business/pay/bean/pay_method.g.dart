// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'pay_method.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

PayMethod _$PayMethodFromJson(Map<String, dynamic> json) {
  return PayMethod()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..configValue = json['configValue'] as String
    ..payMethod =
        (json['payMethod'] as List)?.map((e) => e as String)?.toList();
}

Map<String, dynamic> _$PayMethodToJson(PayMethod instance) => <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'configValue': instance.configValue,
      'payMethod': instance.payMethod,
    };
