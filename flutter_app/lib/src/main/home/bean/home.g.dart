// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'home.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

BannerBean _$BannerBeanFromJson(Map<String, dynamic> json) {
  return BannerBean()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..items = (json['items'] as List)
        ?.map((e) => e == null
            ? null
            : BannerItemBean.fromJson(e as Map<String, dynamic>))
        ?.toList();
}

Map<String, dynamic> _$BannerBeanToJson(BannerBean instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'items': instance.items?.map((e) => e?.toJson())?.toList(),
    };

BannerItemBean _$BannerItemBeanFromJson(Map<String, dynamic> json) {
  return BannerItemBean()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..imgUrl = json['imgUrl'] as String
    ..name = json['name'] as String
    ..redirectUrl = json['redirectUrl'] as String
    ..priority = json['priority'] as int
    ..redirectType = json['redirectType'] as int;
}

Map<String, dynamic> _$BannerItemBeanToJson(BannerItemBean instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'imgUrl': instance.imgUrl,
      'name': instance.name,
      'redirectUrl': instance.redirectUrl,
      'priority': instance.priority,
      'redirectType': instance.redirectType,
    };

FunctionBean _$FunctionBeanFromJson(Map<String, dynamic> json) {
  return FunctionBean()
    ..requestNo = json['requestNo'] as String
    ..error_code = json['error_code'] as String
    ..message = json['message'] as String
    ..data = json['data'] == null
        ? null
        : FunctionDataBean.fromJson(json['data'] as Map<String, dynamic>)
    ..bigmenu = (json['bigmenu'] as List)
        ?.map((e) => e == null
            ? null
            : FunctionItemBean.fromJson(e as Map<String, dynamic>))
        ?.toList()
    ..smallmenu = (json['smallmenu'] as List)
        ?.map((e) => e == null
            ? null
            : FunctionItemBean.fromJson(e as Map<String, dynamic>))
        ?.toList();
}

Map<String, dynamic> _$FunctionBeanToJson(FunctionBean instance) =>
    <String, dynamic>{
      'requestNo': instance.requestNo,
      'error_code': instance.error_code,
      'message': instance.message,
      'data': instance.data?.toJson(),
      'bigmenu': instance.bigmenu?.map((e) => e?.toJson())?.toList(),
      'smallmenu': instance.smallmenu?.map((e) => e?.toJson())?.toList(),
    };

FunctionDataBean _$FunctionDataBeanFromJson(Map<String, dynamic> json) {
  return FunctionDataBean()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..bigmenu = (json['bigmenu'] as List)
        ?.map((e) => e == null
            ? null
            : FunctionItemBean.fromJson(e as Map<String, dynamic>))
        ?.toList()
    ..smallmenu = (json['smallmenu'] as List)
        ?.map((e) => e == null
            ? null
            : FunctionItemBean.fromJson(e as Map<String, dynamic>))
        ?.toList();
}

Map<String, dynamic> _$FunctionDataBeanToJson(FunctionDataBean instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'bigmenu': instance.bigmenu?.map((e) => e?.toJson())?.toList(),
      'smallmenu': instance.smallmenu?.map((e) => e?.toJson())?.toList(),
    };

FunctionItemBean _$FunctionItemBeanFromJson(Map<String, dynamic> json) {
  return FunctionItemBean()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..id = json['id'] as int
    ..name = json['name'] as String
    ..description = json['description'] as String
    ..code = json['code'] as String
    ..url = json['url'] as String
    ..type = json['type'] as int
    ..isValid = json['isValid'] as String
    ..bankName = json['bankName'] as String
    ..headBankCode = json['headBankCode'] as String
    ..bigUrl = json['bigUrl'] as String;
}

Map<String, dynamic> _$FunctionItemBeanToJson(FunctionItemBean instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'id': instance.id,
      'name': instance.name,
      'description': instance.description,
      'code': instance.code,
      'url': instance.url,
      'type': instance.type,
      'isValid': instance.isValid,
      'bankName': instance.bankName,
      'headBankCode': instance.headBankCode,
      'bigUrl': instance.bigUrl,
    };

OrderNumBean _$OrderNumBeanFromJson(Map<String, dynamic> json) {
  return OrderNumBean()
    ..clearingCount = json['clearingCount'] as int
    ..receivCount = json['receivCount'] as int
    ..shippingCount = json['shippingCount'] as int
    ..error_code = json['error_code'] as String
    ..message = json['message'] as String
    ..requestNo = json['requestNo'] as String;
}

Map<String, dynamic> _$OrderNumBeanToJson(OrderNumBean instance) =>
    <String, dynamic>{
      'clearingCount': instance.clearingCount,
      'receivCount': instance.receivCount,
      'shippingCount': instance.shippingCount,
      'error_code': instance.error_code,
      'message': instance.message,
      'requestNo': instance.requestNo,
    };

NewsBean _$NewsBeanFromJson(Map<String, dynamic> json) {
  return NewsBean()
    ..requestNo = json['requestNo'] as String
    ..error_code = json['error_code'] as String
    ..message = json['message'] as String
    ..pageNo = json['pageNo'] as int
    ..totalCount = json['totalCount'] as int
    ..pageSize = json['pageSize'] as int
    ..totalPage = json['totalPage'] as int
    ..items = (json['items'] as List)
        ?.map((e) =>
            e == null ? null : NewsItemBean.fromJson(e as Map<String, dynamic>))
        ?.toList();
}

Map<String, dynamic> _$NewsBeanToJson(NewsBean instance) => <String, dynamic>{
      'requestNo': instance.requestNo,
      'error_code': instance.error_code,
      'message': instance.message,
      'pageNo': instance.pageNo,
      'totalCount': instance.totalCount,
      'pageSize': instance.pageSize,
      'totalPage': instance.totalPage,
      'items': instance.items?.map((e) => e?.toJson())?.toList(),
    };

NewsItemBean _$NewsItemBeanFromJson(Map<String, dynamic> json) {
  return NewsItemBean()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..messageReceiverId = json['messageReceiverId'] as int
    ..status = json['status'] as String
    ..title = json['title'] as String
    ..subTitle = json['subTitle'] as String
    ..remark = json['remark'] as String
    ..afficheId = json['afficheId'] as String
    ..content = json['content'] as String
    ..createTime = json['createTime'] as String;
}

Map<String, dynamic> _$NewsItemBeanToJson(NewsItemBean instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'messageReceiverId': instance.messageReceiverId,
      'status': instance.status,
      'title': instance.title,
      'subTitle': instance.subTitle,
      'remark': instance.remark,
      'afficheId': instance.afficheId,
      'content': instance.content,
      'createTime': instance.createTime,
    };
