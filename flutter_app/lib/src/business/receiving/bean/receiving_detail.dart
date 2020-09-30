import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'receiving_detail.g.dart';

@JsonSerializable(explicitToJson: true)
class ReceivingDetailBean extends NetBean {

  ReceivingDetailBean();

  factory ReceivingDetailBean.fromJson(Map<String, dynamic> json)=>_$ReceivingDetailBeanFromJson(json);
  toJson()=>_$ReceivingDetailBeanToJson(this);
}
