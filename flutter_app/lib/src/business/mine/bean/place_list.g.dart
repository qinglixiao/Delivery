// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'place_list.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

PlaceList _$PlaceListFromJson(Map<String, dynamic> json) {
  return PlaceList()
    ..error_code = json['error_code'] as String
    ..requestNo = json['requestNo'] as String
    ..message = json['message'] as String
    ..pageNo = json['pageNo'] as int
    ..totalCount = json['totalCount'] as int
    ..pageSize = json['pageSize'] as int
    ..totalPage = json['totalPage'] as int
    ..items = (json['items'] as List)
        ?.map(
            (e) => e == null ? null : Place.fromJson(e as Map<String, dynamic>))
        ?.toList();
}

Map<String, dynamic> _$PlaceListToJson(PlaceList instance) => <String, dynamic>{
      'error_code': instance.error_code,
      'requestNo': instance.requestNo,
      'message': instance.message,
      'pageNo': instance.pageNo,
      'totalCount': instance.totalCount,
      'pageSize': instance.pageSize,
      'totalPage': instance.totalPage,
      'items': instance.items?.map((e) => e?.toJson())?.toList(),
    };
