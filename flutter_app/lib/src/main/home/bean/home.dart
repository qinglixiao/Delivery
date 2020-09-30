import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'home.g.dart';

@JsonSerializable(explicitToJson: true)
class BannerBean extends NetBean {

  List<BannerItemBean> items;

  BannerBean();

  factory BannerBean.fromJson(Map<String, dynamic> json)=>_$BannerBeanFromJson(json);
  toJson()=>_$BannerBeanToJson(this);

}

@JsonSerializable(explicitToJson: true)
class BannerItemBean extends NetBean {
  String imgUrl;
  String name;
  String redirectUrl;
  int priority;
  int redirectType;

  BannerItemBean();

  factory BannerItemBean.fromJson(Map<String, dynamic> json)=>_$BannerItemBeanFromJson(json);
  toJson()=>_$BannerItemBeanToJson(this);

}

@JsonSerializable(explicitToJson: true)
class FunctionBean extends NetBean {
  String requestNo;
  String error_code;
  String message;
  FunctionDataBean data;
  List<FunctionItemBean> bigmenu;
  List<FunctionItemBean> smallmenu;

  FunctionBean();

  factory FunctionBean.fromJson(Map<String, dynamic> json)=>_$FunctionBeanFromJson(json);
  toJson()=>_$FunctionBeanToJson(this);

}

@JsonSerializable(explicitToJson: true)
class FunctionDataBean extends NetBean {
  List<FunctionItemBean> bigmenu;
  List<FunctionItemBean> smallmenu;

  FunctionDataBean();

  factory FunctionDataBean.fromJson(Map<String, dynamic> json)=>_$FunctionDataBeanFromJson(json);
  toJson()=>_$FunctionDataBeanToJson(this);

}

@JsonSerializable(explicitToJson: true)
class FunctionItemBean extends NetBean {
  int id;
  String name;
  String description;
  String code;
  String url;
  int type;
  String isValid;
  String bankName;
  String headBankCode;
  String bigUrl;

  FunctionItemBean();

  factory FunctionItemBean.fromJson(Map<String, dynamic> json)=>_$FunctionItemBeanFromJson(json);
  toJson()=>_$FunctionItemBeanToJson(this);

}

@JsonSerializable(explicitToJson: true)
class OrderNumBean extends NetBean {
  int clearingCount; //补单数量
  int receivCount; //收货数量
  int shippingCount; //发货数量
  String error_code; //返回码
  String message; //错误消息
  String requestNo;

  OrderNumBean();

  factory OrderNumBean.fromJson(Map<String, dynamic> json)=>_$OrderNumBeanFromJson(json);
  toJson()=>_$OrderNumBeanToJson(this);
}

@JsonSerializable(explicitToJson: true)
class NewsBean extends NetBean {
  String requestNo;
  String error_code;
  String message;
  int pageNo;
  int totalCount;
  int pageSize;
  int totalPage;
  List<NewsItemBean> items;

  NewsBean();

  factory NewsBean.fromJson(Map<String, dynamic> json)=>_$NewsBeanFromJson(json);
  toJson()=>_$NewsBeanToJson(this);

}

@JsonSerializable(explicitToJson: true)
class NewsItemBean extends NetBean {
  int messageReceiverId;
  String status;
  String title;
  String subTitle;
  String remark;
  String afficheId;
  String content;
  String createTime;

  NewsItemBean();

  factory NewsItemBean.fromJson(Map<String, dynamic> json)=>_$NewsItemBeanFromJson(json);
  toJson()=>_$NewsItemBeanToJson(this);

}

