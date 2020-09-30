// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'bank_name_info.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

BankNameInfo _$BankNameInfoFromJson(Map<String, dynamic> json) {
  return BankNameInfo()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..data = (json['data'] as List)
        ?.map((e) => e == null
            ? null
            : BankNameInfoItem.fromJson(e as Map<String, dynamic>))
        ?.toList();
}

Map<String, dynamic> _$BankNameInfoToJson(BankNameInfo instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'data': instance.data?.map((e) => e?.toJson())?.toList(),
    };

BankNameInfoItem _$BankNameInfoItemFromJson(Map<String, dynamic> json) {
  return BankNameInfoItem()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..name = json['name'] as String
    ..headBankCode = json['headBankCode'] as String;
}

Map<String, dynamic> _$BankNameInfoItemToJson(BankNameInfoItem instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'name': instance.name,
      'headBankCode': instance.headBankCode,
    };
