import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'msg_list.g.dart';

@JsonSerializable(explicitToJson: true)
class MsgList extends NetBean {
  int pageNo;
  int totalCount;
  int pageSize;
  int totalPage;
  List<MsgListItem> items;

  MsgList();

  factory MsgList.fromJson(Map json) => _$MsgListFromJson(json);

  Map<String, dynamic> toJson() => _$MsgListToJson(this);
}

@JsonSerializable(explicitToJson: true)
class MsgListItem {
  int messageReceiverAllId;
  int messageReceiverId;
  String status;
  String title;
  String subTitle;
  String remark;
  String content;
  String createTime;
  String type;

  // int afficheId;

  MsgListItem();

  factory MsgListItem.fromJson(Map json) => _$MsgListItemFromJson(json);

  Map<String, dynamic> toJson() => _$MsgListItemToJson(this);
}
