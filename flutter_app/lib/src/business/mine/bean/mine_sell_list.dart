import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'mine_sell_list.g.dart';

@JsonSerializable(explicitToJson: true)
class MineSellList extends NetBean {
  int pageNo;
  int totalCount;
  int pageSize;
  int totalPage;
  List<MineSellListItem> items;

  MineSellList();

  factory MineSellList.fromJson(Map json) => _$MineSellListFromJson(json);

  Map<String, dynamic> toJson() => _$MineSellListToJson(this);
}

@JsonSerializable(explicitToJson: true)
class MineSellListItem {
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
  int advanceTime;
  bool ifhandAffirmpay;

  MineSellListItem();

  factory MineSellListItem.fromJson(Map json) => _$MineSellListItemFromJson(json);

  Map<String, dynamic> toJson() => _$MineSellListItemToJson(this);
}
