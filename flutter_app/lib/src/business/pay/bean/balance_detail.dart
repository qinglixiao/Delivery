import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'balance_detail.g.dart';

@JsonSerializable(explicitToJson: true)
class BalanceDetailBean extends NetBean {
  int pageNo;
  int totalCount;
  int pageSize;
  int totalPage;
  List<BalanceDetailItemBean> items;
  bool ifLasgPage;

  BalanceDetailBean();

  factory BalanceDetailBean.fromJson(Map json) => _$BalanceDetailBeanFromJson(json);

  Map<String, dynamic> toJson() => _$BalanceDetailBeanToJson(this);
}

@JsonSerializable(explicitToJson: true)
class BalanceDetailItemBean extends NetBean {
  int flowId;
  String type;
  String clearingNo;
  String externalNo;
  String createTime;
  double changeFee;
  double remainderFee;

  String transId;
  String transType;
  String transAmt;
  String bal;
  String transTime;
  String payTime;
  String remark;
  String businessName;

  BalanceDetailItemBean();

  factory BalanceDetailItemBean.fromJson(Map json) => _$BalanceDetailItemBeanFromJson(json);

  Map<String, dynamic> toJson() => _$BalanceDetailItemBeanToJson(this);
}