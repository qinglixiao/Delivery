import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'replenish_detail.g.dart';

@JsonSerializable(explicitToJson: true)
class ReplenishDetailBean extends NetBean {

  String sellerPhone; ///卖家手机号
  String numberCode;  ///订单编码
  String username;    ///买家会员名
  String sellname;  ///卖家会员名
  String status;  ///订单状态
  String createTime;  ///订创建时间
  String title; ///产品标题
  String imgUrl;  ///产品图片
  String sendStatus;  ///发货状态
  String repairStatus;  ///补单状态
  int advanceAbolishTime; ///预计补单时间
  String abolishMsg;  ///取消理由
  String payDesc; ///支付编码
  String numberDesc; ///账户编码
  String receiveName;
  String receiveAddress;
  String receiveMobile;
  String paymentTime;
  String memo;

  int userId; ///买家用户编号
  int sellerId; ///卖家用户编号
  double postFee;  ///邮费
  double paymentFee; ///付款金额
  int count;  ///产品数量
  int sendCount;  ///已经发货数量
  double price;  ///产品价格

  bool ifSend;  ///是否能发货
  bool ifReject;  ///是否能拒绝
  bool ifCostHigh;  ///成本过高
  bool ifhandAffirmpay;  ///是否能手动确认付款

  ReplenishDetailBean();

  factory ReplenishDetailBean.fromJson(Map<String, dynamic> json)=>_$ReplenishDetailBeanFromJson(json);
  toJson()=>_$ReplenishDetailBeanToJson(this);
}