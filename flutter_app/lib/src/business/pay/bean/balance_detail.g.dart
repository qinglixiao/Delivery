// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'balance_detail.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

BalanceDetailBean _$BalanceDetailBeanFromJson(Map<String, dynamic> json) {
  return BalanceDetailBean()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..pageNo = json['pageNo'] as int
    ..totalCount = json['totalCount'] as int
    ..pageSize = json['pageSize'] as int
    ..totalPage = json['totalPage'] as int
    ..items = (json['items'] as List)
        ?.map((e) => e == null
            ? null
            : BalanceDetailItemBean.fromJson(e as Map<String, dynamic>))
        ?.toList()
    ..ifLasgPage = json['ifLasgPage'] as bool;
}

Map<String, dynamic> _$BalanceDetailBeanToJson(BalanceDetailBean instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'pageNo': instance.pageNo,
      'totalCount': instance.totalCount,
      'pageSize': instance.pageSize,
      'totalPage': instance.totalPage,
      'items': instance.items?.map((e) => e?.toJson())?.toList(),
      'ifLasgPage': instance.ifLasgPage,
    };

BalanceDetailItemBean _$BalanceDetailItemBeanFromJson(
    Map<String, dynamic> json) {
  return BalanceDetailItemBean()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..flowId = json['flowId'] as int
    ..type = json['type'] as String
    ..clearingNo = json['clearingNo'] as String
    ..externalNo = json['externalNo'] as String
    ..createTime = json['createTime'] as String
    ..changeFee = (json['changeFee'] as num)?.toDouble()
    ..remainderFee = (json['remainderFee'] as num)?.toDouble()
    ..transId = json['transId'] as String
    ..transType = json['transType'] as String
    ..transAmt = json['transAmt'] as String
    ..bal = json['bal'] as String
    ..transTime = json['transTime'] as String
    ..payTime = json['payTime'] as String
    ..remark = json['remark'] as String
    ..businessName = json['businessName'] as String;
}

Map<String, dynamic> _$BalanceDetailItemBeanToJson(
        BalanceDetailItemBean instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'flowId': instance.flowId,
      'type': instance.type,
      'clearingNo': instance.clearingNo,
      'externalNo': instance.externalNo,
      'createTime': instance.createTime,
      'changeFee': instance.changeFee,
      'remainderFee': instance.remainderFee,
      'transId': instance.transId,
      'transType': instance.transType,
      'transAmt': instance.transAmt,
      'bal': instance.bal,
      'transTime': instance.transTime,
      'payTime': instance.payTime,
      'remark': instance.remark,
      'businessName': instance.businessName,
    };
