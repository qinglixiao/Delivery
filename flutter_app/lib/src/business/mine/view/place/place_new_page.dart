import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/src/widget/address_widget.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/src/business/mine/viewmodel/place_detail_view_model.dart';

class PlaceNewPage extends StatefulWidget {
  @override
  _PlaceNewPageState createState() => _PlaceNewPageState();
}

class _PlaceNewPageState extends State<PlaceNewPage> with PageBridge {
  PlaceDetailViewModel _viewModel = PlaceDetailViewModel();

  TextEditingController _nameController = new TextEditingController();
  TextEditingController _telController = new TextEditingController();
  TextEditingController _placeController = new TextEditingController();

  String _citycontent;
  String _province;
  String _city;
  String _area;

  bool _priority = false;

  @override
  Widget build(BuildContext context) {
    return RootPageWidget(
        appBar: IsAppBar(
          title: S.of(context).place_new_create,
        ),
        viewModel: _viewModel,
        body: GestureDetector(
            behavior: HitTestBehavior.translucent,
            onTap: () {
              FocusScope.of(context).requestFocus(FocusNode());
            },
            child: ContentWidget()));
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
                    placeCityChoose();
                  },
                  child: Container(
                    padding: EdgeInsets.only(
                        left: Values.d_18,
                        top: Values.d_15,
                        bottom: Values.d_15),
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
                      _priority = value;
                    });
                  },
                ),
              ],
            ),
          )
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

    _viewModel.loadPlaceAdd(
        detailaddress, province, city, area, tel, username, _priority,
        (NetBean info) {
      if (info.error_code == '1') {
        pop(data: true);
      }
    });
  }

  void placeCityChoose() {
    showModalBottomSheet<int>(
      context: context,
      builder: (BuildContext context) {
        return Container(
          child: AddressWidget(
            (city) {
              setState(() {
                _province = city[0];
                _city = city[1];
                _area = city[2];
                _citycontent = _province + '|' + _city + '|' + _area;
              });
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
