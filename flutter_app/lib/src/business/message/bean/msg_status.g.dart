// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'msg_status.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

MsgStatus _$MsgStatusFromJson(Map<String, dynamic> json) {
  return MsgStatus()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..isRead = json['isRead'] as int;
}

Map<String, dynamic> _$MsgStatusToJson(MsgStatus instance) => <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'isRead': instance.isRead,
    };
