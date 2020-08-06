import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'good.g.dart';

@JsonSerializable(explicitToJson: true)
class GoodBean extends NetBean {
  String imgUrl;
  String title;
  String price;
  int state;

  GoodBean();

  factory GoodBean.fromJson(Map<String, dynamic> json) => _$GoodBeanFromJson(json);

  Map toJson() => _$GoodBeanToJson(this);
}
