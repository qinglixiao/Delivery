import 'package:flutter_ienglish_fine/src/business/budget/bean/good_confirm.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'pay_pwd_manager.g.dart';

@JsonSerializable(explicitToJson: true)
class PayPwdManager extends NetBean {
  PayPwdManagerData data;

  PayPwdManager();

  factory PayPwdManager.fromJson(Map json) => _$PayPwdManagerFromJson(json);

  Map<String, dynamic> toJson() => _$PayPwdManagerToJson(this);
}

@JsonSerializable(explicitToJson: true)
class PayPwdManagerData extends NetBean {
  String retUrl;
  PayPwdManagerData();

  factory PayPwdManagerData.fromJson(Map json) => _$PayPwdManagerDataFromJson(json);

  Map<String, dynamic> toJson() => _$PayPwdManagerDataToJson(this);
}