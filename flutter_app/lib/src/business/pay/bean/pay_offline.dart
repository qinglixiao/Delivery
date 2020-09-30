import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'pay_offline.g.dart';

@JsonSerializable(explicitToJson: true)
class PayOffline extends NetBean {
  String data;
  String errors;
  PayOffline();

  factory PayOffline.fromJson(Map json) => _$PayOfflineFromJson(json);

  Map<String, dynamic> toJson() => _$PayOfflineToJson(this);
}