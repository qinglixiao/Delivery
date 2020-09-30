import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'pay_platform.g.dart';

@JsonSerializable(explicitToJson: true)
class PayPlatform extends NetBean {
  String payOrderId;
  String payurl;
  String numberCode;
  String totalPage;
  String externalNo;
  String responseText;
  String redirectUrl;
  String posDesc;
  String orderNo;
  double paymentFee;

  PayPlatform();

  factory PayPlatform.fromJson(Map json) => _$PayPlatformFromJson(json);

  Map<String, dynamic> toJson() => _$PayPlatformToJson(this);
}