// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'me.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

MyAccount _$MyAccountFromJson(Map<String, dynamic> json) {
  return MyAccount()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..status = json['status'] as bool
    ..account = json['account'] as bool;
}

Map<String, dynamic> _$MyAccountToJson(MyAccount instance) => <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'status': instance.status,
      'account': instance.account,
    };

MySettleAccounts _$MySettleAccountsFromJson(Map<String, dynamic> json) {
  return MySettleAccounts()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..ifLastPage = json['ifLastPage'] as bool
    ..totalPage = json['totalPage'] as int
    ..unused = (json['unused'] as num)?.toDouble()
    ..used = (json['used'] as num)?.toDouble()
    ..total = (json['total'] as num)?.toDouble()
    ..pageSize = json['pageSize'] as int
    ..totalCount = json['totalCount'] as int
    ..pageNo = json['pageNo'] as int
    ..items = (json['items'] as List)
        ?.map((e) => e == null
            ? null
            : MySettleAccountsItem.fromJson(e as Map<String, dynamic>))
        ?.toList();
}

Map<String, dynamic> _$MySettleAccountsToJson(MySettleAccounts instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'ifLastPage': instance.ifLastPage,
      'totalPage': instance.totalPage,
      'unused': instance.unused,
      'used': instance.used,
      'total': instance.total,
      'pageSize': instance.pageSize,
      'totalCount': instance.totalCount,
      'pageNo': instance.pageNo,
      'items': instance.items?.map((e) => e?.toJson())?.toList(),
    };

MySettleAccountsItem _$MySettleAccountsItemFromJson(Map<String, dynamic> json) {
  return MySettleAccountsItem()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..amount = (json['amount'] as num)?.toDouble()
    ..clearTime = json['clearTime'] as String
    ..number = json['number'] as String
    ..remark = json['remark'] as String
    ..status = json['status'] as String
    ..uniqueNo = json['uniqueNo'] as String;
}

Map<String, dynamic> _$MySettleAccountsItemToJson(
        MySettleAccountsItem instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'amount': instance.amount,
      'clearTime': instance.clearTime,
      'number': instance.number,
      'remark': instance.remark,
      'status': instance.status,
      'uniqueNo': instance.uniqueNo,
    };
