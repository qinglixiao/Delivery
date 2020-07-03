//Json与实体类之间的转换关系可以参考本例
//实体 <-> Json

// 自动生成类
// 运行命令：flutter pub run build_runner build

part 'sample_bean.g.dart'; //文件名.g.dart

@JsonSerializable(explicitToJson: true)
class SampleBean extends NetBean {
  String name;
  String email;
  Address address;

  SampleBean();

  @JsonKey(
      name: 'date-of-birth',
      nullable: false,
      fromJson: _dateTimeFromEpochUs,
      toJson: _dateTimeToEpochUs)
  DateTime dateOfBirth;

  static DateTime _dateTimeFromEpochUs(int us) =>
      us == null ? null : DateTime.fromMicrosecondsSinceEpoch(us);

  static int _dateTimeToEpochUs(DateTime dateTime) =>
      dateTime?.microsecondsSinceEpoch;

  factory SampleBean.fromJson(Map<String, dynamic> json) =>
      _$SampleBeanFromJson(json);

  Map<String, dynamic> toJson() => _$SampleBeanToJson(this);
}

@JsonSerializable()
class Address {
  String country;
  String city;

  Address();

  factory Address.fromJson(Map<String, dynamic> json) =>
      _$AddressFromJson(json);

  Map<String, dynamic> toJson() => _$AddressToJson(this);
}
