import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/src/business/login/viewmodel/login_password_view_model.dart';
import 'package:flutter_ienglish_fine/src/business/mine/viewmodel/set_login_pwd_view_model.dart';
import 'package:flutter_ienglish_fine/src/business/mine/widget/item_widget.dart';
import 'package:flutter_lib/flutter_lib.dart';

///
///create by lx
///date 2020/8/18
///

class LoginPwdModifyPage extends StatefulWidget {
  @override
  _LoginPwdModifyPageState createState() => _LoginPwdModifyPageState();
}

class _LoginPwdModifyPageState extends State<LoginPwdModifyPage>
    with PageBridge {
  GlobalKey<FormState> _globalKey = GlobalKey<FormState>();

  bool _finished = false;

  var _new_pwd;

  var _old_pwd;

  var _viewModel = LoginPwdViewModel();

  @override
  Widget build(BuildContext context) {
    var textStyle =
        TextStyle(fontSize: 14, color: Values.of(context).c_black_front_33);
    var hintTextStyle =
        TextStyle(fontSize: 14, color: Values.of(context).c_grey_front_cc);
    var editWidth = MediaQuery.of(context).size.width * 2 / 3;

    var _old_pwd_wiget = TextFormField(
      obscureText: true,
      style: textStyle,
      textAlign: TextAlign.end,
      autofocus: true,
      decoration: InputDecoration(
          hintText: S.of(context).old_pwd_hint_text,
          border: InputBorder.none,
          hintStyle: hintTextStyle),
      validator: (v) {
        return v.length == 0 ? S.of(context).pwd_no_empty : null;
      },
      onSaved: (pwd) {
        _old_pwd = pwd;
      },
    );

    var _new_pwd_widget = TextFormField(
      obscureText: true,
      style: textStyle,
      textAlign: TextAlign.end,
      autofocus: true,
      decoration: InputDecoration(
          hintText: S.of(context).new_pwd_hint_text,
          border: InputBorder.none,
          hintStyle: hintTextStyle),
      validator: (v) {
        if (v.length == 0) {
          return S.of(context).pwd_no_empty;
        }
        if (v.length < 6) {
          return S.of(context).pwd_length_limit;
        }
        _new_pwd = v;
        return null;
      },
      onSaved: (value) {
        _new_pwd = value;
      },
    );

    var _new_pwd_again_widget = TextFormField(
      obscureText: true,
      style: textStyle,
      textAlign: TextAlign.end,
      autofocus: true,
      decoration: InputDecoration(
          hintText: S.of(context).new_pwd_again_hint_text,
          border: InputBorder.none,
          hintStyle: hintTextStyle),
      validator: (v) {
        if (v.length == 0) {
          return S.of(context).pwd_no_empty;
        }
        if (v.length < 6) {
          return S.of(context).pwd_length_limit;
        }
        if (_new_pwd != v) {
          return S.of(context).pwd_no_eques;
        }
        return null;
      },
      onSaved: (value) {},
    );

    return RootPageWidget(
      appBar: IsAppBar(
        title: S.of(context).login_pwd_modify,
        leftWidget: GestureDetector(
          onTap: () {
            pop();
          },
          child: Text(S.of(context).common_cancel),
        ),
        rightWidget: GestureDetector(
          onTap: _onSave,
          child: Text(S.of(context).common_finish),
        ),
        showBottomLine: true,
      ),
      body: Column(
        children: <Widget>[
          Container(
              color: Values.of(context).c_white_background,
              height: 160.5,
              child: Form(
                  onChanged: () {
                    if (_finished) {
                      _globalKey.currentState.validate();
                    }
                  },
                  key: _globalKey,
                  child: Column(
                    children: <Widget>[
                      ItemWidget.leftTextRightWidget(
                        context,
                        S.of(context).old_pwd,
                        ConstrainedBox(
                            constraints: BoxConstraints(maxWidth: editWidth),
                            child: _old_pwd_wiget),
                      ),
                      Container(
                        height: 10,
                        color: Values.of(context).c_grey_background_f8,
                      ),
                      ItemWidget.leftTextRightWidget(
                          context,
                          S.of(context).new_pwd,
                          ConstrainedBox(
                              constraints: BoxConstraints(maxWidth: editWidth),
                              child: _new_pwd_widget)),
                      Container(
                        height: 0.5,
                        color: Values.of(context).c_grey_ea,
                        margin: EdgeInsets.symmetric(horizontal: 15),
                      ),
                      ItemWidget.leftTextRightWidget(
                          context,
                          S.of(context).pwd_again_confirm,
                          ConstrainedBox(
                              constraints: BoxConstraints(maxWidth: editWidth),
                              child: _new_pwd_again_widget)),
                    ],
                  ))),
          Padding(
              padding: EdgeInsets.only(top: 10, left: 15),
              child: Row(
                crossAxisAlignment: CrossAxisAlignment.center,
                children: <Widget>[
                  Image.asset("assets/images/icon_warn.png"),
                  SizedBox(
                    width: 4,
                  ),
                  Text(
                    S.of(context).pwd_length_limit,
                    style: TextStyle(
                        fontSize: 12,
                        color: Values.of(context).c_black_front_99),
                  )
                ],
              )),
        ],
      ),
    );
  }

  _onSave() {
    _finished = true;
    if (_globalKey.currentState.validate()) {
      _globalKey.currentState.save();
      _viewModel.save(_old_pwd, _new_pwd).then((value) {
        if (value.isSuccess()) {
          ProgressHUD.showText(warnText: S.of(context).pwd_modify_sucess);
          pop();
        } else {
          ProgressHUD.showText(
              warnText: value.message ?? S.of(context).pwd_modify_fail);
        }
      });
    }
  }
}
