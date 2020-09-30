import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'good_detail.g.dart';

///
///create by lx
///date 2020/7/27
///

@JsonSerializable(explicitToJson: true)
class GoodDetail extends NetBean {
  double collocationId;
  String description;
  double directEpithets;
  double directRankNum;
  String errorCode;
  double extId;
  bool ifPlain;
  String imgUrl;
  String message;
  double price;
  double quantity;
  double rankNum;
  String relayDesc;
  String relayTitle;
  String requestNo;
  String text;
  String title;
  double totalPrice;

  GoodDetail();

  factory GoodDetail.fromJson(Map json) => _$GoodDetailFromJson(json);

  @override
  Map toJson() {
    return _$GoodDetailToJson(this);
  }
}
