import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'company_account_info.g.dart';

@JsonSerializable(explicitToJson: true)
class CompanyAccountInfo extends NetBean {
  String walletNumberCode;
  String merchantBankName;
  String merchantNo;
  String accountType;
  String realName;
  String cardNo;
  String phone;
  String bankCode;
  String headBankCode;
  String bankName;
  String bankProvince;
  String bankCity;
  String branchName;
  String branchCode;
  String idCardFront;
  String idCardBack;
  String settleBankCard;
  String handIdCard;
  bool ifNotBlank;
  String enterpriseName;
  String enterpriseHeadBankCode;
  String enterpriseBankCode;
  String enterpriseCardCode;
  String enterprisePhone;
  String enterpriseBankName;

  CompanyAccountInfo();

  factory CompanyAccountInfo.fromJson(Map json) => _$CompanyAccountInfoFromJson(json);

  Map<String, dynamic> toJson() => _$CompanyAccountInfoToJson(this);
}