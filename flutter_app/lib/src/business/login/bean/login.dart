import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'login.g.dart';

@JsonSerializable(explicitToJson: true)
class SmsBean extends NetBean {

  SmsBean();

  factory SmsBean.fromJson(Map<String, dynamic> json)=>_$SmsBeanFromJson(json);
  toJson()=>_$SmsBeanToJson(this);

}

@JsonSerializable(explicitToJson: true)
class LoginBean extends NetBean {

  String assistant; //校董助理json对象
  String token; //登录授权码
  int ifNewUser;  //是否注册
  LoginBean();

  factory LoginBean.fromJson(Map<String, dynamic> json)=>_$LoginBeanFromJson(json);
  toJson()=>_$LoginBeanToJson(this);
}

@JsonSerializable(explicitToJson: true)
class UserInfoBean extends NetBean {
  int capacityId; //代理商级别是数字 1（校董）2（会长）3（合伙人）4（超级会员）5(普通会员)
  String code;  //邀请码
  String defaultPwdFlg; //当前是否默认密码
  int extId; //上阶
  String extImgUrl; //上级头像
  String extUsername; //上级昵称
  String imgUrl;  //头像
  String mobile;  //手机号
  String numberCode;  //经销商编号
  String numberDesc;  //加密用户code
  String ramarkJSON;  //邀请备注
  int rankNum;  //代理商级别是数字 1（校董）2（会长）3（合伙人）4（超级会员）5(普通会员)
  String uplevelTime; //升级时间
  String userDesc;  //加密串
  int userEpithetId;  //代理商级别是数字 1（校董）2（会长）3（合伙人）4（超级会员）5(普通会员)
  int userId; //用户编号
  String username;  //昵称

  UserInfoBean();

  factory UserInfoBean.fromJson(Map<String, dynamic> json)=>_$UserInfoBeanFromJson(json);
  toJson()=>_$UserInfoBeanToJson(this);

}