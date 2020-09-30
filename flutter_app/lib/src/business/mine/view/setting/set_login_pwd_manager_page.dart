import 'package:flutter/cupertino.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/src/business/mine/comm/constant.dart';
import 'package:flutter_ienglish_fine/src/business/mine/widget/item_widget.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_lib/flutter_lib.dart';

///
///create by lx
///date 2020/8/18
///

class LoginPwdManagerPage extends StatelessWidget with PageBridge{
  @override
  Widget build(BuildContext context) {
    return RootPageWidget(
      appBar: IsAppBar(
        title: S.of(context).change_login_password,
        showBottomLine: true,
      ),
      body: Container(
          color: Values.of(context).c_white_background,
          height: 101,
          child: Column(
            children: <Widget>[
              ItemWidget.leftTextRightIcon(context,
                  S.of(context).login_pwd_modify, _clickModifyPwd),
              Container(height: Values.d_0_5,color: Values.of(context).c_grey_ea,margin: EdgeInsets.symmetric(horizontal: 15),),
              ItemWidget.leftTextRightIcon(
                  context, S.of(context).forget_login_pwd, _clickForgetPwd),
            ],
          )),
    );
  }

  _clickModifyPwd() {
    open(RouterName.login_pwd_modify);
  }

  _clickForgetPwd() {
    open(RouterName.forget_login_pwd,argument:FORGET_LOGIN_PWD_PAGE_FROM_PWD_MANAGER_TYPE);
  }
}
