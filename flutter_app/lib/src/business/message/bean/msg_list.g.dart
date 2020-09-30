// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'msg_list.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

MsgList _$MsgListFromJson(Map<String, dynamic> json) {
  return MsgList()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..pageNo = json['pageNo'] as int
    ..totalCount = json['totalCount'] as int
    ..pageSize = json['pageSize'] as int
    ..totalPage = json['totalPage'] as int
    ..items = (json['items'] as List)
        ?.map((e) =>
            e == null ? null : MsgListItem.fromJson(e as Map<String, dynamic>))
        ?.toList();
}

Map<String, dynamic> _$MsgListToJson(MsgList instance) => <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'pageNo': instance.pageNo,
      'totalCount': instance.totalCount,
      'pageSize': instance.pageSize,
      'totalPage': instance.totalPage,
      'items': instance.items?.map((e) => e?.toJson())?.toList(),
    };

MsgListItem _$MsgListItemFromJson(Map<String, dynamic> json) {
  return MsgListItem()
    ..messageReceiverAllId = json['messageReceiverAllId'] as int
    ..messageReceiverId = json['messageReceiverId'] as int
    ..status = json['status'] as String
    ..title = json['title'] as String
    ..subTitle = json['subTitle'] as String
    ..remark = json['remark'] as String
    ..content = json['content'] as String
    ..createTime = json['createTime'] as String
    ..type = json['type'] as String;
}

Map<String, dynamic> _$MsgListItemToJson(MsgListItem instance) =>
    <String, dynamic>{
      'messageReceiverAllId': instance.messageReceiverAllId,
      'messageReceiverId': instance.messageReceiverId,
      'status': instance.status,
      'title': instance.title,
      'subTitle': instance.subTitle,
      'remark': instance.remark,
      'content': instance.content,
      'createTime': instance.createTime,
      'type': instance.type,
    };
