import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'good_confirm.g.dart';

@JsonSerializable(explicitToJson: true)

class Place extends NetBean {
  String id;
  String username;
  String province;
  String city;
  String area;
  String detailaddress;
  String tel;
  bool priority;

  Place();

  factory Place.fromJson(Map json) => _$PlaceFromJson(json);

  Map<String, dynamic> toJson() => _$PlaceToJson(this);

}

@JsonSerializable(explicitToJson: true)
class ServiceProviderLevel extends NetBean{
  int rankNum;
  String rankName;
  int startCount;
  double price;
  int nextCount;
  int nextRum;
  String nextName;
  double nextPrice;
  int repairCount;
  String numberDesc;
  String title;
  String imgUrl;

  ServiceProviderLevel();

  factory ServiceProviderLevel.fromJson(Map json) => _$ServiceProviderLevelFromJson(json);

  Map<String, dynamic> toJson() => _$ServiceProviderLevelToJson(this);
}

@JsonSerializable(explicitToJson: true)
class FundQuery extends NetBean{

  FundQueryDetail data;

  FundQuery();

  factory FundQuery.fromJson(Map json) => _$FundQueryFromJson(json);

  Map<String, dynamic> toJson() => _$FundQueryToJson(this);
}

//@JsonSerializable(explicitToJson: true)
class FundQueryDetail extends NetBean{
  double frozen;
  double bal;
  double cash;

  FundQueryDetail();

  factory FundQueryDetail.fromJson(Map json) => _$normalFundQueryDetailFromJson(json);

  Map<String, dynamic> toJson() => _$normalFundQueryDetailToJson(this);
}

FundQueryDetail _$normalFundQueryDetailFromJson(Map<String, dynamic> json) {
  return FundQueryDetail()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..frozen = (json['frozen'] is num) ? (json['frozen'] as num).toDouble() : double.parse(json['frozen'] as String)
    ..bal = (json['bal'] is num) ? (json['bal'] as num).toDouble() : double.parse(json['bal'] as String)
    ..cash = (json['cash'] is num) ? (json['cash'] as num).toDouble() : double.parse(json['cash'] as String);
}

Map<String, dynamic> _$normalFundQueryDetailToJson(FundQueryDetail instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'frozen': instance.frozen,
      'bal': instance.bal,
      'cash': instance.cash,
    };


@JsonSerializable(explicitToJson: true)
class CollocationInfo extends NetBean{
  int collocationId;
  String description;
  int directEpithets;
  int directRankNum;
  int extId;
  bool ifPlain;
  String imgUrl;
  double price;
  double quantity;
  int rankNum;
  String relayDesc;
  String relayTitle;
  String text;
  String title;
  double totalPrice;
  CollocationInfo();

  factory CollocationInfo.fromJson(Map json) => _$CollocationInfoFromJson(json);

  Map<String, dynamic> toJson() => _$CollocationInfoToJson(this);

}

@JsonSerializable(explicitToJson: true)
class PersionStatusInfo extends NetBean{
  double extraFee;  ///平台代理手续费
  bool ifOpenAcct;  ///是否开户
  bool ifaffirm;    ///是否弹框提示
  bool ifextraFee;  ///是否有附加费
  bool ifpersion;   ///是否个人主体
  PersionStatusInfo();

  factory PersionStatusInfo.fromJson(Map json) => _$PersionStatusInfoFromJson(json);

  Map<String, dynamic> toJson() => _$PersionStatusInfoToJson(this);
}

@JsonSerializable(explicitToJson: true)
class BuyCollocationInfo extends NetBean{
  String numberCode;    ///订单编码
  String numberDesc;    ///调用收银台授权编码
  String payDesc;       ///调用收银台加密数据
  double payFee;        ///支付金额
  BuyCollocationInfo();

  factory BuyCollocationInfo.fromJson(Map json) => _$BuyCollocationInfoFromJson(json);

  Map<String, dynamic> toJson() => _$BuyCollocationInfoToJson(this);
}

@JsonSerializable(explicitToJson: true)
class AccountCountBean extends NetBean{
  AccountCountInfoBean data;
  AccountCountBean();

  factory AccountCountBean.fromJson(Map json) => _$AccountCountBeanFromJson(json);

  Map<String, dynamic> toJson() => _$AccountCountBeanToJson(this);
}

@JsonSerializable(explicitToJson: true)
class AccountCountInfoBean extends NetBean{
  int countDistributeAccount;
  int countFreezingAccount;
  int countTotalAccount;
  AccountCountInfoBean();

  factory AccountCountInfoBean.fromJson(Map json) => _$AccountCountInfoBeanFromJson(json);

  Map<String, dynamic> toJson() => _$AccountCountInfoBeanToJson(this);
}




