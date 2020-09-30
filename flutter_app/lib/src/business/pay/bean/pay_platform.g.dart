// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'pay_platform.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

PayPlatform _$PayPlatformFromJson(Map<String, dynamic> json) {
  return PayPlatform()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..payOrderId = json['payOrderId'] as String
    ..payurl = json['payurl'] as String
    ..numberCode = json['numberCode'] as String
    ..totalPage = json['totalPage'] as String
    ..externalNo = json['externalNo'] as String
    ..responseText = json['responseText'] as String
    ..redirectUrl = json['redirectUrl'] as String
    ..posDesc = json['posDesc'] as String
    ..orderNo = json['orderNo'] as String
    ..paymentFee = (json['paymentFee'] as num)?.toDouble();
}

Map<String, dynamic> _$PayPlatformToJson(PayPlatform instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'payOrderId': instance.payOrderId,
      'payurl': instance.payurl,
      'numberCode': instance.numberCode,
      'totalPage': instance.totalPage,
      'externalNo': instance.externalNo,
      'responseText': instance.responseText,
      'redirectUrl': instance.redirectUrl,
      'posDesc': instance.posDesc,
      'orderNo': instance.orderNo,
      'paymentFee': instance.paymentFee,
    };
