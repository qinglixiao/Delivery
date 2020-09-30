import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'earnings.g.dart';

@JsonSerializable(explicitToJson: true)
class EarningsBean extends NetBean {
  int pageNo;
  int totalCount;
  int pageSize;
  int totalPage;
  double used;
  double unused;
  double total;

  List<EarningsItemBean> items;

  EarningsBean();

  factory EarningsBean.fromJson(Map json) => _$EarningsBeanFromJson(json);

  Map<String, dynamic> toJson() => _$EarningsBeanToJson(this);
}

@JsonSerializable(explicitToJson: true)
class EarningsItemBean extends NetBean {
  double amount;
  String number;
  String uniqueNo;
  String remark;
  String status;
  String clearTime;

  EarningsItemBean();

  factory EarningsItemBean.fromJson(Map json) => _$EarningsItemBeanFromJson(json);

  Map<String, dynamic> toJson() => _$EarningsItemBeanToJson(this);
}