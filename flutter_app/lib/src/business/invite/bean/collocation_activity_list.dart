import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'collocation_activity_list.g.dart';

@JsonSerializable(explicitToJson: true)
class CollocationActivityList extends NetBean {
  List<CollocationActivityListItem> items;

  CollocationActivityList();

  factory CollocationActivityList.fromJson(Map json) => _$CollocationActivityListFromJson(json);

  Map<String, dynamic> toJson() => _$CollocationActivityListToJson(this);
}

@JsonSerializable(explicitToJson: true)
class CollocationActivityListItem {
  int id;
  String title;
  String coverImgUrl;
  String relayDesc;
  String directRankNums;
  double totalPrice;
  double price;
  int quantity;
  bool ifPlain;

  CollocationActivityListItem();

  factory CollocationActivityListItem.fromJson(Map json) => _$CollocationActivityListItemFromJson(json);

  Map<String, dynamic> toJson() => _$CollocationActivityListItemToJson(this);
}
