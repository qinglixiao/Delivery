import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_ienglish_fine/src/widget/address_widget.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_ienglish_fine/src/business/mine/bean/place_list.dart';
import 'package:flutter_ienglish_fine/src/business/budget/bean/good_confirm.dart';
import 'package:flutter_ienglish_fine/src/business/mine/viewmodel/place_detail_view_model.dart';

class PlaceDetailPage extends StatefulWidget {
  @override
  _PlaceDetailPageState createState() => _PlaceDetailPageState();
}

class _PlaceDetailPageState extends State<PlaceDetailPage> with PageBridge {
  PlaceDetailViewModel _viewModel = PlaceDetailViewModel();

  TextEditingController _nameController = new TextEditingController();
  TextEditingController _telController = new TextEditingController();
  TextEditingController _placeController = new TextEditingController();

  String _placeId;
  Place _place;
  String _province;
  String _city;
  String _area;
  bool _priority = false;

  @override
  Widget build(BuildContext context) {
    getInitArg(context).then((params) {
      _placeId = params['id'];
      if (!StringUtil.isEmpty(_placeId) && _place == null) {
        _viewModel.loadPlaceDetail(_placeId);
      }
    });
    return RootPageWidget(
        appBar: IsAppBar(
          title: S.of(context).place_title,
        ),
        viewModel: _viewModel,
        body: GestureDetector(
            behavior: HitTestBehavior.translucent,
            onTap: () {
              FocusScope.of(context).requestFocus(FocusNode());
            },
            child: StreamBuilder<Place>(
                stream: _viewModel.streamPlaceDetail,
                builder: (BuildContext context, AsyncSnapshot<Place> snapshot) {
                  if (snapshot.data == null) {
                    return CommonWidget.emptyWidget();
                  }
                  if (_place == null) {
                    _place = snapshot.data;
                    _nameController =
                        new TextEditingController(text: _place.username);
                    _telController =
                        new TextEditingController(text: _place.tel);
                    _placeController = new TextEditingController.fromValue(
                        TextEditingValue(
                            text: _place.detailaddress,
                            selection: TextSelection.fromPosition(TextPosition(
                                affinity: TextAffinity.downstream,
                                offset: _place.detailaddress.length))));

                    _province = StringUtil.getNotNullString(_place.province);
                    _city = StringUtil.getNotNullString(_place.city);
                    _area = StringUtil.getNotNullString(_place.area);

                    _priority = _place.priority;
                  }

                  return ContentWidget();
                })));
  }

  Widget ContentWidget() {
    return Column(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: <Widget>[PlaceInfoWidget(), BottomWidget()],
    );
  }

  Widget PlaceInfoWidget() {
    return Container(
      child: Column(
        children: <Widget>[
          Container(
            decoration: new BoxDecoration(
                color: Values.of(context).c_white_background,
                border: new Border(
                    bottom: BorderSide(
                        color: Values.of(context).c_grey_ea, width: 1.0))),
            padding: EdgeInsets.only(
                left: Values.d_18,
                right: Values.d_18,
                top: Values.d_5,
                bottom: Values.d_5),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: <Widget>[
                Text(
                  S.of(context).dispatch_detail_express_name,
                  style: TextStyle(
                      fontSize: Values.s_text_15,
                      fontWeight: Values.font_Weight_normal,
                      color: Values.of(context).c_black_front_33,
                      decoration: TextDecoration.none),
                ),
                Expanded(
                  child: TextField(
                    controller: _nameController,
                    textAlign: TextAlign.right,
                    style: TextStyle(
                        color: Values.of(context).c_black_front_33,
                        fontWeight: Values.font_Weight_medium,
                        fontSize: Values.s_text_14),
                    decoration: InputDecoration(
                        enabledBorder: UnderlineInputBorder(
                          borderSide: BorderSide(color: Values.c_translucent),
                        ),
                        focusedBorder: UnderlineInputBorder(
                          borderSide: BorderSide(color: Values.c_translucent),
                        ),
                        hintText: S.of(context).place_name_hint,
                        hintStyle: TextStyle(
                            color: Values.of(context).c_grey_front_cc,
                            fontWeight: Values.font_Weight_medium,
                            fontSize: Values.s_text_14)),
                    onSubmitted: (value) {},
                    cursorColor: Values.of(context).c_black_front_33,
                  ),
                ),
              ],
            ),
          ),
          Container(
            decoration: new BoxDecoration(
                color: Values.of(context).c_white_background,
                border: new Border(
                    bottom: BorderSide(
                        color: Values.of(context).c_grey_ea, width: 1.0))),
            padding: EdgeInsets.only(
                left: Values.d_18,
                right: Values.d_18,
                top: Values.d_5,
                bottom: Values.d_5),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: <Widget>[
                Text(
                  S.of(context).set_user_info_tel + '：',
                  style: TextStyle(
                      fontSize: Values.s_text_15,
                      fontWeight: Values.font_Weight_normal,
                      color: Values.of(context).c_black_front_33,
                      decoration: TextDecoration.none),
                ),
                Expanded(
                  child: TextField(
                    controller: _telController,
                    textAlign: TextAlign.right,
                    style: TextStyle(
                        color: Values.of(context).c_black_front_33,
                        fontWeight: Values.font_Weight_medium,
                        fontSize: Values.s_text_14),
                    decoration: InputDecoration(
                        enabledBorder: UnderlineInputBorder(
                          borderSide: BorderSide(color: Values.c_translucent),
                        ),
                        focusedBorder: UnderlineInputBorder(
                          borderSide: BorderSide(color: Values.c_translucent),
                        ),
                        hintText: S.of(context).place_tel_hint,
                        hintStyle: TextStyle(
                            color: Values.of(context).c_grey_front_cc,
                            fontWeight: Values.font_Weight_medium,
                            fontSize: Values.s_text_14)),
                    inputFormatters: [LengthLimitingTextInputFormatter(11)],
                    onSubmitted: (value) {},
                    cursorColor: Values.of(context).c_black_front_33,
                  ),
                ),
              ],
            ),
          ),
          PlaceChooseWidget(province: _province, city: _city, area: _area),
          Container(
            decoration: new BoxDecoration(
                color: Values.of(context).c_white_background,
                border: new Border(
                    bottom: BorderSide(
                        color: Values.of(context).c_grey_ea, width: 1.0))),
            padding: EdgeInsets.only(
                left: Values.d_18,
                right: Values.d_18,
                top: Values.d_5,
                bottom: Values.d_5),
            child: Row(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                Expanded(
                  child: TextField(
                    controller: _placeController,
                    textAlign: TextAlign.left,
                    keyboardType: TextInputType.multiline,
                    maxLines: 5,
                    style: TextStyle(
                        color: Values.of(context).c_black_front_33,
                        fontWeight: Values.font_Weight_medium,
                        fontSize: Values.s_text_14),
                    decoration: InputDecoration(
                        enabledBorder: UnderlineInputBorder(
                          borderSide: BorderSide(color: Values.c_translucent),
                        ),
                        focusedBorder: UnderlineInputBorder(
                          borderSide: BorderSide(color: Values.c_translucent),
                        ),
                        hintText: S.of(context).place_detail_hint,
                        hintStyle: TextStyle(
                            color: Values.of(context).c_grey_front_cc,
                            fontWeight: Values.font_Weight_medium,
                            fontSize: Values.s_text_14)),
                    onSubmitted: (value) {},
                    cursorColor: Values.of(context).c_black_front_33,
                  ),
                ),
              ],
            ),
          ),
          SwitchWidget(priority: _priority),
          Container(
              decoration: new BoxDecoration(
                  color: Values.of(context).c_white_background,
                  border: new Border(
                      bottom: BorderSide(
                          color: Values.of(context).c_grey_ea, width: 1.0))),
              padding: EdgeInsets.only(
                  left: Values.d_18,
                  right: Values.d_18,
                  top: Values.d_5,
                  bottom: Values.d_5),
              height: Values.d_60,
              child: GestureDetector(
                onTap: () {
                  showWarnDialog();
                },
                behavior: HitTestBehavior.opaque,
                child: Row(
                  children: <Widget>[
                    Text(
                      S.of(context).place_delete_title,
                      style: TextStyle(
                          fontSize: Values.s_text_15,
                          fontWeight: Values.font_Weight_normal,
                          color: Values.of(context).c_red_front_68,
                          decoration: TextDecoration.none),
                      textAlign: TextAlign.left,
                    )
                  ],
                ),
              ))
        ],
      ),
    );
  }

  Widget BottomWidget() {
    return Container(
        color: Values.of(context).c_white_background,
        child: SafeArea(
            child: Container(
                color: Values.of(context).c_orange_background_0b,
                height: Values.d_50,
                width: MediaQuery.of(context).size.width,
                child: FlatButton(
                    onPressed: () {
                      submit();
                    },
                    color: Values.of(context).c_orange_background_0b,
                    disabledColor: Values.of(context).c_grey_background_cc,
                    highlightColor: Values.c_translucent,
                    splashColor: Values.c_translucent,
                    child: Text(
                      S.of(context).place_save,
                      style: TextStyle(
                          fontSize: Values.s_text_16,
                          color: Values.of(context).c_white_front),
                    )))));
  }

  void showWarnDialog() {
    showDialog(
        context: context,
        child: DialogTool(
          bgColor: Values.of(context).c_white_background,
          title: S.of(context).warn_title,
          titleFontSize: Values.s_text_18,
          titleColor: Values.of(context).c_black_front_33,
          content: S.of(context).place_delete_warn,
          contentColor: Values.of(context).c_black_front_33,
          leftTitle: S.of(context).warn_cancel,
          leftColor: Values.of(context).c_orange_front_0b,
          leftBgColor: Values.of(context).c_white_background,
          rightTitle: S.of(context).warn_sure,
          rightColor: Values.of(context).c_white_front,
          rightBgColor: Values.of(context).c_orange_background_0b,
          onTap: (int index) {
            if (index == 1) {
              delete();
            }
          },
        ));
  }

  void updatePlace(String province, String city, String area) {
    _province = province;
    _city = city;
    _area = area;
  }

  void delete() {
    _viewModel.loadPlaceDelete(_placeId, (NetBean info) {
      if (info.error_code == '1') {
        pop();
      }
    });
  }

  void submit() {
    String username = _nameController.text;
    String tel = _telController.text;
    String province = _province;
    String city = _city;
    String area = _area;
    String detailaddress = _placeController.text;

    if (StringUtil.isEmpty(username)) {
      ProgressHUD.showText(warnText: S.of(context).place_name_hint);
      return;
    }
    if (StringUtil.isEmpty(tel)) {
      ProgressHUD.showText(warnText: S.of(context).sms_warn1);
      return;
    }
    if (tel.length != 11) {
      ProgressHUD.showText(warnText: S.of(context).sms_warn2);
      return;
    }
    if (StringUtil.isEmpty(province) ||
        StringUtil.isEmpty(city) ||
        StringUtil.isEmpty(area)) {
      ProgressHUD.showText(warnText: S.of(context).place_city_warn);
      return;
    }
    if (StringUtil.isEmpty(detailaddress)) {
      ProgressHUD.showText(warnText: S.of(context).place_detail_warn);
      return;
    }

    _viewModel.loadPlaceEdit(
        _placeId, detailaddress, province, city, area, tel, username, _priority,
        (NetBean info) {
      if (info.error_code == '1') {
        pop(data: true);
      }
    });
  }
}

class PlaceChooseWidget extends StatefulWidget {
  final String province;
  final String city;
  final String area;

  const PlaceChooseWidget({
    this.province,
    this.city,
    this.area,
  });

  @override
  _PlaceChooseWidgetState createState() => _PlaceChooseWidgetState();
}

class _PlaceChooseWidgetState extends State<PlaceChooseWidget> {
  String _province;
  String _city;
  String _area;
  String _citycontent;
  bool _isChange = false;

  @override
  Widget build(BuildContext context) {
    if (!_isChange) {
      _province = widget.province;
      _city = widget.city;
      _area = widget.area;
      _citycontent = _province + '|' + _city + '|' + _area;
    }
    return Container(
      decoration: new BoxDecoration(
          color: Values.of(context).c_white_background,
          border: new Border(
              bottom:
                  BorderSide(color: Values.of(context).c_grey_ea, width: 1.0))),
      padding: EdgeInsets.only(
          left: Values.d_18,
          right: Values.d_18,
          top: Values.d_5,
          bottom: Values.d_5),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: <Widget>[
          Text(
            S.of(context).place_city_title,
            style: TextStyle(
                fontSize: Values.s_text_15,
                fontWeight: Values.font_Weight_normal,
                color: Values.of(context).c_black_front_33,
                decoration: TextDecoration.none),
          ),
          Expanded(
              child: GestureDetector(
            onTap: () {
              placeCityChoose((city) {
                setState(() {
                  _province = city[0];
                  _city = city[1];
                  _area = city[2];
                  _citycontent = _province + '|' + _city + '|' + _area;
                  _isChange = true;
                  Logger.print(context
                      .findRootAncestorStateOfType<_PlaceDetailPageState>());
                  context
                      .findRootAncestorStateOfType<_PlaceDetailPageState>()
                      .updatePlace(_province, _city, _area);
                });
              });
            },
            child: Container(
              padding: EdgeInsets.only(
                  left: Values.d_18, top: Values.d_15, bottom: Values.d_15),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.end,
                children: <Widget>[
                  Text(
                    StringUtil.isEmpty(_citycontent)
                        ? S.of(context).place_city_hint
                        : _citycontent,
                    style: TextStyle(
                        fontSize: Values.s_text_14,
                        fontWeight: StringUtil.isEmpty(_citycontent)
                            ? Values.font_Weight_normal
                            : Values.font_Weight_medium,
                        color: StringUtil.isEmpty(_citycontent)
                            ? Values.of(context).c_grey_front_cc
                            : Values.of(context).c_black_front_33,
                        decoration: TextDecoration.none),
                  ),
                  SizedBox(width: Values.d_2),
                  Image.asset('assets/images/right_icon.png')
                ],
              ),
            ),
          )),
        ],
      ),
    );
  }

  void placeCityChoose(Function(List<String>) cityCallBack) {
    showModalBottomSheet<int>(
      context: context,
      builder: (BuildContext context) {
        return Container(
          child: AddressWidget(
            (city) {
              cityCallBack(city);
              Logger.print(city);
              Navigator.of(context).pop();
            },
            close: () {
              Navigator.of(context).pop();
            },
          ),
        );
      },
    );
  }
}

class SwitchWidget extends StatefulWidget {
  final bool priority;

  const SwitchWidget({this.priority = false});

  @override
  _SwitchWidgetState createState() => _SwitchWidgetState();
}

class _SwitchWidgetState extends State<SwitchWidget> {
  bool _priority;
  bool _isChange = false;

  @override
  Widget build(BuildContext context) {
    if (!_isChange) {
      _priority = widget.priority;
    }
    return Container(
      decoration: new BoxDecoration(
          color: Values.of(context).c_white_background,
          border: new Border(
              bottom:
                  BorderSide(color: Values.of(context).c_grey_ea, width: 1.0))),
      padding: EdgeInsets.only(
          left: Values.d_18,
          right: Values.d_18,
          top: Values.d_5,
          bottom: Values.d_5),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: <Widget>[
          Text(
            S.of(context).place_normal_title,
            style: TextStyle(
                fontSize: Values.s_text_15,
                fontWeight: Values.font_Weight_normal,
                color: Values.of(context).c_black_front_33,
                decoration: TextDecoration.none),
          ),
          Switch(
            value: _priority,
            activeColor: Values.of(context).c_orange_background_0b,
            onChanged: (value) {
              setState(() {
                _isChange = true;
                _priority = value;
                context
                    .findRootAncestorStateOfType<_PlaceDetailPageState>()
                    ._priority = _priority;
              });
            },
          ),
        ],
      ),
    );
  }
}
