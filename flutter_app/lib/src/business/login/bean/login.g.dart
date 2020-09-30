// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'login.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

SmsBean _$SmsBeanFromJson(Map<String, dynamic> json) {
  return SmsBean()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String;
}

Map<String, dynamic> _$SmsBeanToJson(SmsBean instance) => <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
    };

LoginBean _$LoginBeanFromJson(Map<String, dynamic> json) {
  return LoginBean()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..assistant = json['assistant'] as String
    ..token = json['token'] as String
    ..ifNewUser = json['ifNewUser'] as int;
}

Map<String, dynamic> _$LoginBeanToJson(LoginBean instance) => <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'assistant': instance.assistant,
      'token': instance.token,
      'ifNewUser': instance.ifNewUser,
    };

UserInfoBean _$UserInfoBeanFromJson(Map<String, dynamic> json) {
  return UserInfoBean()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..capacityId = json['capacityId'] as int
    ..code = json['code'] as String
    ..defaultPwdFlg = json['defaultPwdFlg'] as String
    ..extId = json['extId'] as int
    ..extImgUrl = json['extImgUrl'] as String
    ..extUsername = json['extUsername'] as String
    ..imgUrl = json['imgUrl'] as String
    ..mobile = json['mobile'] as String
    ..numberCode = json['numberCode'] as String
    ..numberDesc = json['numberDesc'] as String
    ..ramarkJSON = json['ramarkJSON'] as String
    ..rankNum = json['rankNum'] as int
    ..uplevelTime = json['uplevelTime'] as String
    ..userDesc = json['userDesc'] as String
    ..userEpithetId = json['userEpithetId'] as int
    ..userId = json['userId'] as int
    ..username = json['username'] as String;
}

Map<String, dynamic> _$UserInfoBeanToJson(UserInfoBean instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'capacityId': instance.capacityId,
      'code': instance.code,
      'defaultPwdFlg': instance.defaultPwdFlg,
      'extId': instance.extId,
      'extImgUrl': instance.extImgUrl,
      'extUsername': instance.extUsername,
      'imgUrl': instance.imgUrl,
      'mobile': instance.mobile,
      'numberCode': instance.numberCode,
      'numberDesc': instance.numberDesc,
      'ramarkJSON': instance.ramarkJSON,
      'rankNum': instance.rankNum,
      'uplevelTime': instance.uplevelTime,
      'userDesc': instance.userDesc,
      'userEpithetId': instance.userEpithetId,
      'userId': instance.userId,
      'username': instance.username,
    };
