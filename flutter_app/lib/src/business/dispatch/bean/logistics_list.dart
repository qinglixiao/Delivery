import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'logistics_list.g.dart';

@JsonSerializable(explicitToJson: true)
class LogisticsList extends NetBean {
  LogisticsInfo data;

  LogisticsList();

  factory LogisticsList.fromJson(Map json) => _$LogisticsListFromJson(json);

  Map<String, dynamic> toJson() => _$LogisticsListToJson(this);
}

@JsonSerializable(explicitToJson: true)
class LogisticsInfo {
  String number;
  String type ;
  String deliverystatus;
  String issign;
  String expName;
  String expSite;
  String expPhone;
  String logo;
  String updateTime;
  String takeTime;
  List<LogisticsItem> list;

  LogisticsInfo();

  factory LogisticsInfo.fromJson(Map json) => _$LogisticsInfoFromJson(json);

  Map<String, dynamic> toJson() => _$LogisticsInfoToJson(this);
}

@JsonSerializable(explicitToJson: true)
class LogisticsItem {
  String status;
  String time;

  LogisticsItem();

  factory LogisticsItem.fromJson(Map json) => _$LogisticsItemFromJson(json);

  Map<String, dynamic> toJson() => _$LogisticsItemToJson(this);
}
