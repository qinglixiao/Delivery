import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_lib/flutter_lib.dart';

///
///create by lx
///date 2020/8/11
///

typedef AddressCallBack = void Function(List<String> city);

class AddressWidget extends StatefulWidget {
  AddressCallBack callBack;
  VoidCallback close;

  AddressWidget(this.callBack, {this.close});

  @override
  _AddressWidgetState createState() => _AddressWidgetState();
}

class _AddressWidgetState extends State<AddressWidget> {
  var HEADSUFFIX;

  AddressModel _model = AddressModel();

  List<String> selected = [];

  var _cur_code = "100000";

  set currentCode(String code) {
    _cur_code = code;
  }

  var _cur_city;

  set currentCity(String name) {
    _cur_city = name;
  }

  get currentCity => _cur_city;

  Map<String, List<CityBean>> _citys;

  @override
  void initState() {
    super.initState();
    load();
  }

  load() {
    _model.getCityList(_cur_code).then((value) {
      setState(() {
        _citys = value;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    HEADSUFFIX = S.of(context).address_select_tag;
    if (_citys == null) {
      return Container();
    }

    List<Widget> headerItems = [];
    _citys.forEach((header, items) {
      headerItems.add(AddressHeader(
        header: header,
        items: items,
      ));
    });
    return Container(
      height: MediaQuery.of(context).size.height,
      padding: EdgeInsets.only(left: 10,right: 10),
//      decoration: BoxDecoration(
//          borderRadius: BorderRadius.all(Radius.circular(3))),
      color: Values.of(context).c_white_background,
      child: DefaultStickyHeaderController(
          child: Column(
        children: <Widget>[
          Container(
              width: double.maxFinite,
              height: 40,
              child: Stack(
                alignment: Alignment.center,
                children: <Widget>[
                  Positioned(
                    left: 0,
                    child: Text(
                      S.of(context).address_widget_title,
                      style: TextStyle(
                          fontSize: 18,
                          color: Values.of(context).c_black_front_33,
                          fontWeight: FontWeight.w600),
                    ),
                  ),
                  Positioned(
                      right: 0,
                      child: GestureDetector(
                        onTap: () {
                          if (widget.close != null) {
                            widget.close();
                          }
                        },
                        child: Image.asset("assets/images/close_grey.png"),
                      )),
                ],
              )),
          Container(
              height: selected.length == 0 ? 0 : 30,
              child: ListView(
                scrollDirection: Axis.horizontal,
                children: getSelectedCityWidget(),
              )),
          Expanded(
            child: CustomScrollView(
              slivers: headerItems,
            ),
          )
        ],
      )),
    );
  }

  addCity(String name) {
    if (selected.length == 0) {
      selected.add(HEADSUFFIX);
    }
    if (currentCity == name) {
      return;
    }
    if (name != currentCity && selected.length > 1) {
      selected.removeRange(selected.indexOf(currentCity), selected.length - 1);
    }

    selected.insert(selected.length - 1, name);

    if (selected.length == 4 && widget.callBack != null) {
      widget.callBack(selected.sublist(0, selected.length - 1));
    }
    _headerClick(HEADSUFFIX);
  }

  List<Widget> getSelectedCityWidget() {
    return selected.map((name) {
      return Container(
          child: Row(
        children: <Widget>[
          GestureDetector(
              onTap: () {
                _headerClick(name);
              },
              child: Text(
                name,
                style: TextStyle(
                    color: currentCity == name
                        ? Values.of(context).c_orange_front_0b
                        : Values.of(context).c_black_front_33),
              )),
          SizedBox(
            width: 10,
          )
        ],
      ));
    }).toList();
  }

  _headerClick(String name) async {
    if (showNext(name)) {
      _citys = await _model.getChildCityList(selected[selected.length - 2]);
    } else {
      _citys = await _model.getCityFlat(name);
    }

    setState(() {
      currentCity = name;
    });
  }

  bool showNext(String focus) {
    return focus == HEADSUFFIX;
  }
}

class AddressHeader extends StatefulWidget {
  String header;
  List<CityBean> items;

  AddressHeader({this.header, this.items});

  @override
  State<StatefulWidget> createState() {
    return _AddressHeaderState();
  }
}

class _AddressHeaderState extends State<AddressHeader> {
  @override
  Widget build(BuildContext context) {
    var fix = context.findAncestorStateOfType<_AddressWidgetState>();

    return SliverStickyHeader(
        header: Container(
            color: Values.of(context).c_white_background,
            height: 30,
            child: Stack(
              alignment: Alignment.centerLeft,
              children: <Widget>[
                Positioned(
                  child: Text(
                    widget.header,
                    style: TextStyle(
                        fontSize: 14,
                        fontWeight: FontWeight.w700,
                        color: Values.of(context).c_black_front_33),
                  ),
                ),
                Positioned(
                    bottom: 0,
                    child: Container(
                      width: double.maxFinite,
                      height: 0.2,
                      color: Values.of(context).c_grey_front_cc,
                    )),
              ],
            )),
        sliver: SliverFixedExtentList(
          delegate: SliverChildBuilderDelegate((context, index) {
            return Container(
              color: Values.of(context).c_white_background,
              child: Row(
                children: <Widget>[
                  Visibility(
                    visible: fix.currentCity == widget.items[index].name,
                    child: Image.asset("assets/images/orange_ok.png"),
                  ),
                  SizedBox(
                    width: fix.currentCity == widget.items[index].name ? 5 : 0,
                  ),
                  GestureDetector(
                    onTap: () {
                      fix.addCity(widget.items[index].name);
                    },
                    child: Text(
                      widget.items[index].name,
                      style: TextStyle(
                          fontSize: 14,
                          color: Values.of(context).c_black_front_33),
                    ),
                  )
                ],
              ),
            );
          }, childCount: widget.items.length),
          itemExtent: 30,
        ));
  }
}

class AddressModel {
  Future<Map<String, List<CityBean>>> getCityList(String parentCode) async {
    List<Map> data = await IsDb().rawQuery(
        "select * from city where parentcode='$parentCode' and pinyin not null order by pinyin");
    return _group(data);
  }

  Future<Map<String, List<CityBean>>> getChildCityList(String city) async {
    var sql =
        " select * from 'city' where parentcode = (select code from city where name = '$city') and pinyin not null order by  pinyin;";
    List<Map> data = await IsDb().rawQuery(sql);
    return _group(data);
  }

  Future<Map<String, List<CityBean>>> getCityFlat(String city) async {
    var sql =
        " select * from 'city' where parentcode = (select parentcode from city where name = '$city') and pinyin not null order by  pinyin;";
    List<Map> data = await IsDb().rawQuery(sql);
    return _group(data);
  }

  Map<String, List<CityBean>> _group(List<Map> data) {
    Map<String, List<CityBean>> _city = Map<String, List<CityBean>>();

    data.forEach((element) {
      var data = CityBean.fromJson(element);
      if (!_city.containsKey(data.pinyin)) {
        _city[data.pinyin] = List();
      }
      _city[data.pinyin].add(data);
    });

    return _city;
  }
}

class CityBean {
  String code;
  String name;
  String parentcode;
  String pinyin;

  CityBean();

  factory CityBean.fromJson(Map json) {
    return CityBean()
      ..code = json['code']
      ..name = json['name']
      ..parentcode = json['parentcode']
      ..pinyin = json['pinyin']?.toString()?.substring(0, 1);
  }
}
