// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'pay_withdraw_config.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

PayWithdrawConfig _$PayWithdrawConfigFromJson(Map<String, dynamic> json) {
  return PayWithdrawConfig()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..configValue = json['configValue'] as String;
}

Map<String, dynamic> _$PayWithdrawConfigToJson(PayWithdrawConfig instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'configValue': instance.configValue,
    };
