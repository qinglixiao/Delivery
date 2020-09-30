// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'logistics_list.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

LogisticsList _$LogisticsListFromJson(Map<String, dynamic> json) {
  return LogisticsList()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..data = json['data'] == null
        ? null
        : LogisticsInfo.fromJson(json['data'] as Map<String, dynamic>);
}

Map<String, dynamic> _$LogisticsListToJson(LogisticsList instance) =>
    <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'data': instance.data?.toJson(),
    };

LogisticsInfo _$LogisticsInfoFromJson(Map<String, dynamic> json) {
  return LogisticsInfo()
    ..number = json['number'] as String
    ..type = json['type'] as String
    ..deliverystatus = json['deliverystatus'] as String
    ..issign = json['issign'] as String
    ..expName = json['expName'] as String
    ..expSite = json['expSite'] as String
    ..expPhone = json['expPhone'] as String
    ..logo = json['logo'] as String
    ..updateTime = json['updateTime'] as String
    ..takeTime = json['takeTime'] as String
    ..list = (json['list'] as List)
        ?.map((e) => e == null
            ? null
            : LogisticsItem.fromJson(e as Map<String, dynamic>))
        ?.toList();
}

Map<String, dynamic> _$LogisticsInfoToJson(LogisticsInfo instance) =>
    <String, dynamic>{
      'number': instance.number,
      'type': instance.type,
      'deliverystatus': instance.deliverystatus,
      'issign': instance.issign,
      'expName': instance.expName,
      'expSite': instance.expSite,
      'expPhone': instance.expPhone,
      'logo': instance.logo,
      'updateTime': instance.updateTime,
      'takeTime': instance.takeTime,
      'list': instance.list?.map((e) => e?.toJson())?.toList(),
    };

LogisticsItem _$LogisticsItemFromJson(Map<String, dynamic> json) {
  return LogisticsItem()
    ..status = json['status'] as String
    ..time = json['time'] as String;
}

Map<String, dynamic> _$LogisticsItemToJson(LogisticsItem instance) =>
    <String, dynamic>{
      'status': instance.status,
      'time': instance.time,
    };
