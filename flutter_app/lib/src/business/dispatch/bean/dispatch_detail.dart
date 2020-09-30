import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'dispatch_detail.g.dart';

@JsonSerializable(explicitToJson: true)

class DispatchOrderDetail extends NetBean {

  String abolishMsg; ///取消理由
  int advanceAbolishTime;///预计取消时间
  int advanceTime;///预计补单时间
  int count;///订单数量
  double postFee ;///邮费
  double price ;///单价
  int sellerId;///供货用户编号
  int sendCount; ///发货数量
  double paymentFee;///总价
  String createTime;/// 订单创建时间
  String imgUrl;///产品标题
  String memo;///备忘
  String numberCode;///订单编码
  String numberDesc;///用户签名
  String payDesc;/// 支付加密串
  String paymentTime;///订单付款时间
  String receiveAddress;///收货地址
  String receiveMobile;///收货电话
  String receiveName;///收货人名称
  String requestNo;
  String sellerPhone;///卖家手机号
  String sellname;///供货用户编号
  String status;///订单状态
  String title;///产品标题
  String username;/// 供货用户编号

  DispatchOrderDetail();

  factory DispatchOrderDetail.fromJson(Map json) => _$DispatchOrderDetailFromJson(json);

  Map<String, dynamic> toJson() => _$DispatchOrderDetailToJson(this);

}
@JsonSerializable(explicitToJson: true)

class ExpressInfoData extends NetBean {

  ExpressInfo data;

  ExpressInfoData();

  factory ExpressInfoData.fromJson(Map json) => _$ExpressInfoDataFromJson(json);

  Map<String, dynamic> toJson() => _$ExpressInfoDataToJson(this);

}

@JsonSerializable(explicitToJson: true)

class ExpressInfo extends NetBean {

  String receiveName;
  String receiveAddress;
  int unusedCount;
  double affirmCount;
  int totalCount;
  int usedCount;
  String receiveMobile;
  List <ExpressItemInfo>logistics;

  ExpressInfo();

  factory ExpressInfo.fromJson(Map json) => _$ExpressInfoFromJson(json);

  Map<String, dynamic> toJson() => _$ExpressInfoToJson(this);

}

@JsonSerializable(explicitToJson: true)

class ExpressItemInfo extends NetBean {

  String orderNO;
  int quantity;
  int gmtModify;
  int gmtCreated;
  String receiveAddress;
  bool ifSelfTaking;
  int userId;
  int version;
  String numberCode;
  String logisticsName;
  String receiveName;
  int sellerId;
  String logisticsNo;
  int id;
  String outTime;
  String receiveMobile;
  String status;

  ExpressItemInfo();

  factory ExpressItemInfo.fromJson(Map json) => _$ExpressItemInfoFromJson(json);

  Map<String, dynamic> toJson() => _$ExpressItemInfoToJson(this);
}

@JsonSerializable(explicitToJson: true)

class ExpressResultData extends NetBean {

  ExpressResultData();

  factory ExpressResultData.fromJson(Map json) => _$ExpressResultDataFromJson(json);

  Map<String, dynamic> toJson() => _$ExpressResultDataToJson(this);

}


