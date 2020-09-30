// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'account_list.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

AccountListInfo _$AccountListInfoFromJson(Map<String, dynamic> json) {
  return AccountListInfo()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..data = (json['data'] as List)
        ?.map((e) => e == null
            ? null
            : AccountListItemInfo.fromJson(e as Map<String, dynamic>))
        ?.toList();
}

Map<String, dynamic> _$AccountListInfoToJson(AccountListInfo instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'data': instance.data?.map((e) => e?.toJson())?.toList(),
    };

AccountListItemInfo _$AccountListItemInfoFromJson(Map<String, dynamic> json) {
  return AccountListItemInfo()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..bankCode = json['bankCode'] as String
    ..merNetInOutStatus = json['merNetInOutStatus'] as String
    ..enterpriseCardCode = json['enterpriseCardCode'] as String
    ..bankName = json['bankName'] as String
    ..enterpriseName = json['enterpriseName'] as String
    ..enterpriseBankCode = json['enterpriseBankCode'] as String;
}

Map<String, dynamic> _$AccountListItemInfoToJson(
        AccountListItemInfo instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'bankCode': instance.bankCode,
      'merNetInOutStatus': instance.merNetInOutStatus,
      'enterpriseCardCode': instance.enterpriseCardCode,
      'bankName': instance.bankName,
      'enterpriseName': instance.enterpriseName,
      'enterpriseBankCode': instance.enterpriseBankCode,
    };
