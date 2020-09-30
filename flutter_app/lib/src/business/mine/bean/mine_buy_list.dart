import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'mine_buy_list.g.dart';

@JsonSerializable(explicitToJson: true)
class MineBuyList extends NetBean {
  int pageNo;
  int totalCount;
  int pageSize;
  int totalPage;
  List<MineBuyListItem> items;

  MineBuyList();

  factory MineBuyList.fromJson(Map json) => _$MineBuyListFromJson(json);

  Map<String, dynamic> toJson() => _$MineBuyListToJson(this);
}

@JsonSerializable(explicitToJson: true)
class MineBuyListItem {
  String sellerPhone;
  int userId;
  String numberCode;
  String username;
  int sellerId;
  String sellerUserName;
  String status;
  String createTime;
  String title;
  String imgUrl;
  String abolishMsg;
  String payDesc;
  String numberDesc;
  double postFee;
  double paymentFee;
  int count;
  int sendCount;
  double price;
  String repairStatus;
  String sendStatus;
  bool ifSend;
  bool ifReject;
  bool ifCostHigh;
  String advanceTime;
  bool ifhandAffirmpay;

  MineBuyListItem();

  factory MineBuyListItem.fromJson(Map json) => _$MineBuyListItemFromJson(json);

  Map<String, dynamic> toJson() => _$MineBuyListItemToJson(this);
}
