import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'me.g.dart';

@JsonSerializable(explicitToJson: true)
class MyAccount extends NetBean {

  bool status;  ///是否有资金
  bool account; ///是否开过企业户

  MyAccount();

  factory MyAccount.fromJson(Map json) => _$MyAccountFromJson(json);

  Map<String, dynamic> toJson() => _$MyAccountToJson(this);
}

@JsonSerializable(explicitToJson: true)
class MySettleAccounts extends NetBean {

  bool ifLastPage;///是否最后一页
  int totalPage;///总页数
  double unused;///未发放金额
  double used ;///已发放金额
  double total;///总金额
  int pageSize;///总记录数
  int totalCount;///总页数
  int pageNo;///当前页
  List<MySettleAccountsItem>items;///订单列表

  MySettleAccounts();

  factory MySettleAccounts.fromJson(Map json) => _$MySettleAccountsFromJson(json);

  Map<String, dynamic> toJson() => _$MySettleAccountsToJson(this);
}

@JsonSerializable(explicitToJson: true)
class MySettleAccountsItem extends NetBean {

  double amount;///金额
  String clearTime ;///结算时间
  String number;///交易单号
  String remark;///备注
  String status;///状态
  String uniqueNo;///唯一编号

  MySettleAccountsItem();

  factory MySettleAccountsItem.fromJson(Map json) => _$MySettleAccountsItemFromJson(json);

  Map<String, dynamic> toJson() => _$MySettleAccountsItemToJson(this);
}

