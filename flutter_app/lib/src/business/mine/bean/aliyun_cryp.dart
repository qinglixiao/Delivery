import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

///  created by：sunyuancun
/// 2020/9/14
///desc:

part 'aliyun_cryp.g.dart';

@JsonSerializable(explicitToJson: true)
class AliyunCryp {
  String policyBase64;
  String accessId;
  String filename;
  String signature;
  String host;
  String callback;

  AliyunCryp();

  factory AliyunCryp.fromJson(Map json) => _$AliyunCrypFromJson(json);

  Map<String, dynamic> toJson() => _$AliyunCrypToJson(this);
}
