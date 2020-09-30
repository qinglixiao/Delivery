import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'account_info.g.dart';

@JsonSerializable(explicitToJson: true)
class AccountInfo extends NetBean {
  bool  status;
  String channel;
  String ifIdCardType;
  String merchantBankName;
  String merchantNo;
  String virtualCardNo;
  String realName;
  bool ifSuccess;
  String merchantStatus;

  AccountInfo();

  factory AccountInfo.fromJson(Map json) => _$AccountInfoFromJson(json);

  Map<String, dynamic> toJson() => _$AccountInfoToJson(this);
}

