import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'bank_name_info.g.dart';

@JsonSerializable(explicitToJson: true)
class BankNameInfo extends NetBean {

  List<BankNameInfoItem> data;

  BankNameInfo();

  factory BankNameInfo.fromJson(Map json) => _$BankNameInfoFromJson(json);

  Map<String, dynamic> toJson() => _$BankNameInfoToJson(this);
}

@JsonSerializable(explicitToJson: true)
class BankNameInfoItem extends NetBean {
  String name;
  String headBankCode;

  BankNameInfoItem();

  factory BankNameInfoItem.fromJson(Map json) => _$BankNameInfoItemFromJson(json);

  Map<String, dynamic> toJson() => _$BankNameInfoItemToJson(this);
}

