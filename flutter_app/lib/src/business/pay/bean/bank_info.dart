import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'bank_info.g.dart';

@JsonSerializable(explicitToJson: true)
class BankInfoBean extends NetBean {
  String walletNumberCode;
  String merchantBankName;
  String merchantNo;
  String realName;
  String cardNo;
  String phone;
  String bankCode;
  String headBankCode;
  String bankName;
  double bankProvince;
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

  BankInfoBean();

  factory BankInfoBean.fromJson(Map json) => _$BankInfoBeanFromJson(json);

  Map<String, dynamic> toJson() => _$BankInfoBeanToJson(this);
}