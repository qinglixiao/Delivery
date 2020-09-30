import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/src/config/global.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_lib/flutter_lib.dart';

///
///create by lx
///date 2020/8/8
///

class AppLaunchPage extends StatelessWidget with PageBridge {
  @override
  Widget build(BuildContext context) {
    Timer(Duration(seconds: 3), _jump(context));
    return RootPageWidget(
        body: Container(
      color: Values.c_red_r1,
    ));
  }

  _jump(BuildContext context) {
    if (Global.of(context).isLogin) {
      open(RouterName.login_sms);
    } else {
      open(RouterName.index_page);
    }
  }
}
