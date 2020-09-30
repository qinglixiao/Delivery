import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'dispatch_list.g.dart';

@JsonSerializable(explicitToJson: true)

class DispatchListBean extends NetBean {

  List<DispatchListItem> items;
  int pageNo;
  int totalCount;
  int pageSize;
  int totalPage;
  int count; ///总数
  int frozenCount; ///冻结
  DispatchListBean();

  factory DispatchListBean.fromJson(Map json) => _$DispatchListBeanFromJson(json);

  Map<String, dynamic> toJson() => _$DispatchListBeanToJson(this);

}

@JsonSerializable(explicitToJson: true)
class DispatchListItem extends NetBean {

  String abolishMsg; ///取消理由
  int advanceAbolishTime;///预计取消时间
  int advanceTime;///预计补单时间
  int count;///产品数量
  bool ifCostHigh;///成本过高
  bool ifReject;///是否能拒绝
  bool ifSend;///是否能发货
  bool ifhandAffirmpay;///是否能手动确认付款
  double postFee ;///邮费
  double price ;///单价
  int sellerId;///供货用户编号
  int sendCount; ///发货数量
  int userId;///买家用户编号
  double paymentFee;///付款金额
  String createTime;/// 订单创建时间
  String imgUrl;///产品标题
  String memo;///备忘
  String numberCode;///订单编码
  String numberDesc;///用户签名
  String payDesc;/// 支付加密串
  String paymentTime;///订单付款时间
  String repairStatus;///补单状态
  String sellerPhone;///卖家手机号
  String sellerUserName;///卖家会员名
  String sendStatus;///发货状态
  String status;///订单状态
  String title;///产品标题
  String username;/// 供货用户编号

  DispatchListItem();

  factory DispatchListItem.fromJson(Map json) => _$DispatchListItemFromJson(json);

  Map<String, dynamic> toJson() => _$DispatchListItemToJson(this);

}