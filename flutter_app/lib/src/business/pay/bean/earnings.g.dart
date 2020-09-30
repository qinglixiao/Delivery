// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'earnings.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

EarningsBean _$EarningsBeanFromJson(Map<String, dynamic> json) {
  return EarningsBean()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..pageNo = json['pageNo'] as int
    ..totalCount = json['totalCount'] as int
    ..pageSize = json['pageSize'] as int
    ..totalPage = json['totalPage'] as int
    ..used = (json['used'] as num)?.toDouble()
    ..unused = (json['unused'] as num)?.toDouble()
    ..total = (json['total'] as num)?.toDouble()
    ..items = (json['items'] as List)
        ?.map((e) => e == null
            ? null
            : EarningsItemBean.fromJson(e as Map<String, dynamic>))
        ?.toList();
}

Map<String, dynamic> _$EarningsBeanToJson(EarningsBean instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'pageNo': instance.pageNo,
      'totalCount': instance.totalCount,
      'pageSize': instance.pageSize,
      'totalPage': instance.totalPage,
      'used': instance.used,
      'unused': instance.unused,
      'total': instance.total,
      'items': instance.items?.map((e) => e?.toJson())?.toList(),
    };

EarningsItemBean _$EarningsItemBeanFromJson(Map<String, dynamic> json) {
  return EarningsItemBean()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..amount = (json['amount'] as num)?.toDouble()
    ..number = json['number'] as String
    ..uniqueNo = json['uniqueNo'] as String
    ..remark = json['remark'] as String
    ..status = json['status'] as String
    ..clearTime = json['clearTime'] as String;
}

Map<String, dynamic> _$EarningsItemBeanToJson(EarningsItemBean instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'amount': instance.amount,
      'number': instance.number,
      'uniqueNo': instance.uniqueNo,
      'remark': instance.remark,
      'status': instance.status,
      'clearTime': instance.clearTime,
    };
