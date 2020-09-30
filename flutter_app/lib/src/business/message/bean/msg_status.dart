import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'msg_status.g.dart';

@JsonSerializable(explicitToJson: true)
class MsgStatus extends NetBean {
  int isRead;

  MsgStatus();

  factory MsgStatus.fromJson(Map json) => _$MsgStatusFromJson(json);

  Map<String, dynamic> toJson() => _$MsgStatusToJson(this);
}
