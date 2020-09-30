// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'bank_info.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

BankInfoBean _$BankInfoBeanFromJson(Map<String, dynamic> json) {
  return BankInfoBean()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..walletNumberCode = json['walletNumberCode'] as String
    ..merchantBankName = json['merchantBankName'] as String
    ..merchantNo = json['merchantNo'] as String
    ..realName = json['realName'] as String
    ..cardNo = json['cardNo'] as String
    ..phone = json['phone'] as String
    ..bankCode = json['bankCode'] as String
    ..headBankCode = json['headBankCode'] as String
    ..bankName = json['bankName'] as String
    ..bankProvince = (json['bankProvince'] as num)?.toDouble()
    ..bankCity = json['bankCity'] as String
    ..branchName = json['branchName'] as String
    ..branchCode = json['branchCode'] as String
    ..idCardFront = json['idCardFront'] as String
    ..idCardBack = json['idCardBack'] as String
    ..settleBankCard = json['settleBankCard'] as String
    ..handIdCard = json['handIdCard'] as String
    ..ifNotBlank = json['ifNotBlank'] as bool
    ..enterpriseName = json['enterpriseName'] as String
    ..enterpriseHeadBankCode = json['enterpriseHeadBankCode'] as String
    ..enterpriseBankCode = json['enterpriseBankCode'] as String
    ..enterpriseCardCode = json['enterpriseCardCode'] as String
    ..enterprisePhone = json['enterprisePhone'] as String
    ..enterpriseBankName = json['enterpriseBankName'] as String;
}

Map<String, dynamic> _$BankInfoBeanToJson(BankInfoBean instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'walletNumberCode': instance.walletNumberCode,
      'merchantBankName': instance.merchantBankName,
      'merchantNo': instance.merchantNo,
      'realName': instance.realName,
      'cardNo': instance.cardNo,
      'phone': instance.phone,
      'bankCode': instance.bankCode,
      'headBankCode': instance.headBankCode,
      'bankName': instance.bankName,
      'bankProvince': instance.bankProvince,
      'bankCity': instance.bankCity,
      'branchName': instance.branchName,
      'branchCode': instance.branchCode,
      'idCardFront': instance.idCardFront,
      'idCardBack': instance.idCardBack,
      'settleBankCard': instance.settleBankCard,
      'handIdCard': instance.handIdCard,
      'ifNotBlank': instance.ifNotBlank,
      'enterpriseName': instance.enterpriseName,
      'enterpriseHeadBankCode': instance.enterpriseHeadBankCode,
      'enterpriseBankCode': instance.enterpriseBankCode,
      'enterpriseCardCode': instance.enterpriseCardCode,
      'enterprisePhone': instance.enterprisePhone,
      'enterpriseBankName': instance.enterpriseBankName,
    };
