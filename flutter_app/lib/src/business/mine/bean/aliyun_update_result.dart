import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

///  created by：sunyuancun
/// 2020/9/14 
///desc:


part 'aliyun_update_result.g.dart';

@JsonSerializable(explicitToJson: true)
class AliyunUpdateResult extends NetBean{
  String responseBody;

  AliyunUpdateResult();

  factory AliyunUpdateResult.fromJson(Map json) => _$AliyunUpdateResultFromJson(json);

  Map<String, dynamic> toJson() => _$AliyunUpdateResultToJson(this);

}