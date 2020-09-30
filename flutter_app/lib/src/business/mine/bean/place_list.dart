import 'package:flutter_ienglish_fine/src/business/budget/bean/good_confirm.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:json_annotation/json_annotation.dart';

part 'place_list.g.dart';

@JsonSerializable(explicitToJson: true)
class PlaceList extends NetBean {
  int pageNo;
  int totalCount;
  int pageSize;
  int totalPage;
  List<Place> items;

  PlaceList();

  factory PlaceList.fromJson(Map json) => _$PlaceListFromJson(json);

  Map<String, dynamic> toJson() => _$PlaceListToJson(this);
}
