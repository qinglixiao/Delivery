import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'pay_withdraw_config.g.dart';

@JsonSerializable(explicitToJson: true)
class PayWithdrawConfig extends NetBean {
  String configValue;

  PayWithdrawConfig();

  factory PayWithdrawConfig.fromJson(Map json) => _$PayWithdrawConfigFromJson(json);

  Map<String, dynamic> toJson() => _$PayWithdrawConfigToJson(this);
}