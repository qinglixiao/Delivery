import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'pay_method.g.dart';

@JsonSerializable(explicitToJson: true)
class PayMethod extends NetBean {
  String configValue;
  List  <String> payMethod;
  PayMethod();

  factory PayMethod.fromJson(Map json) => _$PayMethodFromJson(json);

  Map<String, dynamic> toJson() => _$PayMethodToJson(this);
}