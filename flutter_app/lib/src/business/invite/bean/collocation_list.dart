import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'collocation_list.g.dart';

@JsonSerializable(explicitToJson: true)
class CollocationList extends NetBean {
  List<CollocationListItem> items;

  CollocationList();

  factory CollocationList.fromJson(Map json) => _$CollocationListFromJson(json);

  Map<String, dynamic> toJson() => _$CollocationListToJson(this);
}

@JsonSerializable(explicitToJson: true)
class CollocationListItem {
  int id;
  String title;
  String coverImgUrl;
  String relayDesc;
  String directRankNums;
  double totalPrice;
  double price;
  int quantity;
  bool ifPlain;

  CollocationListItem();

  factory CollocationListItem.fromJson(Map json) => _$CollocationListItemFromJson(json);

  Map<String, dynamic> toJson() => _$CollocationListItemToJson(this);
}
