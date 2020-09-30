import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'open_account_info.g.dart';

@JsonSerializable(explicitToJson: true)
class OpenAccountInfoBean extends NetBean {
  String ret;
  String retUrl;
  OpenAccountInfoBean();

  factory OpenAccountInfoBean.fromJson(Map json) => _$OpenAccountInfoBeanFromJson(json);

  Map<String, dynamic> toJson() => _$OpenAccountInfoBeanToJson(this);
}