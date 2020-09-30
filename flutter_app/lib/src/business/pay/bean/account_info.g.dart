// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'account_info.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

AccountInfo _$AccountInfoFromJson(Map<String, dynamic> json) {
  return AccountInfo()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..status = json['status'] as bool
    ..channel = json['channel'] as String
    ..ifIdCardType = json['ifIdCardType'] as String
    ..merchantBankName = json['merchantBankName'] as String
    ..merchantNo = json['merchantNo'] as String
    ..virtualCardNo = json['virtualCardNo'] as String
    ..realName = json['realName'] as String
    ..ifSuccess = json['ifSuccess'] as bool
    ..merchantStatus = json['merchantStatus'] as String;
}

Map<String, dynamic> _$AccountInfoToJson(AccountInfo instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'status': instance.status,
      'channel': instance.channel,
      'ifIdCardType': instance.ifIdCardType,
      'merchantBankName': instance.merchantBankName,
      'merchantNo': instance.merchantNo,
      'virtualCardNo': instance.virtualCardNo,
      'realName': instance.realName,
      'ifSuccess': instance.ifSuccess,
      'merchantStatus': instance.merchantStatus,
    };
