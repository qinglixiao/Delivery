// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'aliyun_cryp.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

AliyunCryp _$AliyunCrypFromJson(Map<String, dynamic> json) {
  return AliyunCryp()
    ..policyBase64 = json['policyBase64'] as String
    ..accessId = json['accessId'] as String
    ..filename = json['filename'] as String
    ..signature = json['signature'] as String
    ..host = json['host'] as String
    ..callback = json['callback'] as String;
}

Map<String, dynamic> _$AliyunCrypToJson(AliyunCryp instance) =>
    <String, dynamic>{
      'policyBase64': instance.policyBase64,
      'accessId': instance.accessId,
      'filename': instance.filename,
      'signature': instance.signature,
      'host': instance.host,
      'callback': instance.callback,
    };
