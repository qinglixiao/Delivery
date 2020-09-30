import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:url_launcher/url_launcher.dart';

class SettingPage extends StatefulWidget {
  @override
  _SettingPageState createState() => _SettingPageState();
}

class _SettingPageState extends State<SettingPage> with PageBridge {
  @override
  Widget build(BuildContext context) {
    return RootPageWidget(
        appBar: IsAppBar(
          title: S.of(context).my_setting,
        ),
        body: Container(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: <Widget>[ListWidget(), SpUtil.getUserName() != null ? BottomWidget() : Container()],
          ),
        ));
  }

  Widget ListWidget() {
    return Column(
      children: <Widget>[
        ItemWiget(S.of(context).user_info_title, () {
          open(RouterName.set_user_info_page);
        }),
        SizedBox(height: Values.d_10),
        ItemWiget(S.of(context).change_login_password, () {
          open(RouterName.login_pwd_manager);
        }),
        Container(
          padding: EdgeInsets.only(left: Values.d_15, right: Values.d_15),
          color: Values.of(context).c_white_background,
          height: 1,
          child: Container(
            color: Values.of(context).c_grey_ea,
          ),
        ),
        ItemWiget(S.of(context).change_deal_password, () {
          open(RouterName.pay_pwd_manager);
        }),
        SizedBox(height: Values.d_10),
        ItemWiget(S.of(context).service_tel, () {
          callCustomerPhone();
        }),
      ],
    );
  }

  Widget ItemWiget(String name, Function() callback) {
    Widget rightWidget = Image.asset('assets/images/right_icon.png');

    if (name == S.of(context).service_tel) {
      rightWidget = Text(
        '010-86391726',
        textAlign: TextAlign.right,
        style: TextStyle(
            fontSize: Values.s_text_13,
            fontWeight: Values.font_Weight_medium,
            color: Values.of(context).c_black_front_99,
            decoration: TextDecoration.none),
      );
    }

    return GestureDetector(
      child: Container(
        color: Values.of(context).c_white_background,
        height: Values.d_50,
        padding: EdgeInsets.only(left: Values.d_15, right: Values.d_15),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            Text(
              name,
              style: TextStyle(
                  fontSize: Values.s_text_15,
                  fontWeight: Values.font_Weight_normal,
                  color: Values.of(context).c_black_front_33,
                  decoration: TextDecoration.none),
            ),
            rightWidget
          ],
        ),
      ),
      onTap: () {
        callback();
      },
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
                      showDialoLogout(S.of(context).logout_warn);
                    },
                    color: Values.of(context).c_orange_background_0b,
                    disabledColor: Values.of(context).c_grey_background_cc,
                    highlightColor: Values.c_translucent,
                    splashColor: Values.c_translucent,
                    child: Text(
                      S.of(context).logout,
                      style: TextStyle(fontSize: Values.s_text_16, color: Values.of(context).c_white_front),
                    )))));
  }

  void showDialoLogout(String message) {
    showDialog(
        context: context,
        child: DialogTool(
          bgColor: Values.of(context).c_white_background,
          title: S.of(context).warn_title,
          titleFontSize: Values.s_text_18,
          titleColor: Values.of(context).c_black_front_33,
          content: message,
          contentColor: Values.of(context).c_black_front_33,
          leftTitle: S.of(context).warn_cancel,
          leftColor: Values.of(context).c_orange_front_0b,
          leftBgColor: Values.of(context).c_white_background,
          rightTitle: S.of(context).warn_sure,
          rightColor: Values.of(context).c_white_front,
          rightBgColor: Values.of(context).c_orange_background_0b,
          onTap: (int index) {
            if (index == 1) {
              SpUtil.clearAll();
              openAndRemoveUtil(RouterName.login_sms);
              eventCenter.emit(LoginEvent(1));
            }
          },
        ));
  }

  void callCustomerPhone() async {
    String url = 'tel:01086391726';
    if (await canLaunch(url)) {
      await launch(url);
    } else {
      Toast.show(S.of(context).customer_phone_failed);
    }
  }
}
