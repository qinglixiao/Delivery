import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'good.g.dart';

@JsonSerializable(explicitToJson: true)
class Good extends NetBean {
  List<GoodItem> items;

  Good();

  factory Good.fromJson(Map json) => _$GoodFromJson(json);

  Map<String, dynamic> toJson() => _$GoodToJson(this);
}

@JsonSerializable(explicitToJson: true)
class GoodItem {
  String coverImgUrl; //封面图
  String directRankNums = ""; //直升等级
  int id; //搭配ID
  bool ifPlain; //普通套组
  double price; //套组价格
  int quantity; //套组数量
  String relayDesc = ""; //简述
  String title = ""; //搭配名称
  double totalPrice; //套组总价格

  GoodItem();

  factory GoodItem.fromJson(Map json) => _$GoodItemFromJson(json);

  Map<String, dynamic> toJson() => _$GoodItemToJson(this);
}
