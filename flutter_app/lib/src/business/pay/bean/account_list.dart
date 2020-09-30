import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'account_list.g.dart';

@JsonSerializable(explicitToJson: true)
class AccountListInfo extends NetBean {
  List<AccountListItemInfo> data;

  AccountListInfo();

  factory AccountListInfo.fromJson(Map json) => _$AccountListInfoFromJson(json);

  Map<String, dynamic> toJson() => _$AccountListInfoToJson(this);
}

@JsonSerializable(explicitToJson: true)
class AccountListItemInfo extends NetBean {

  String bankCode;
  String merNetInOutStatus;
  String enterpriseCardCode;
  String bankName;
  String enterpriseName;
  String enterpriseBankCode;

  AccountListItemInfo();

  factory AccountListItemInfo.fromJson(Map json) => _$AccountListItemInfoFromJson(json);

  Map<String, dynamic> toJson() => _$AccountListItemInfoToJson(this);
}

