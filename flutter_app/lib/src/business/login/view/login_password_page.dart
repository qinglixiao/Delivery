import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_ienglish_fine/src/business/login/bean/login.dart';
import 'package:flutter_ienglish_fine/src/business/mine/comm/constant.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/src/business/login/comm/interfaces.dart';
import 'package:flutter_ienglish_fine/src/business/login/viewmodel/login_password_view_model.dart';

class LoginPasswordPage extends StatefulWidget {
  @override
  _LoginPasswordPageState createState() => _LoginPasswordPageState();
}

class _LoginPasswordPageState extends State<LoginPasswordPage> with PageBridge {
  LoginPasswordViewModel _loginPasswordViewModel;

  GlobalKey<FormState> _loginKey = new GlobalKey<FormState>();

  String _userName;
  String _password;
  bool _isAgree = false;
  bool _isObscure = true;
  bool _isCanLogin = false;
  bool _isEditUserName = false;
  bool _isEditPassword = false;
  FocusNode _userFocusNode = FocusNode();
  FocusNode _passwordFocusNode = FocusNode();

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    _loginPasswordViewModel = LoginPasswordViewModel();
    _userFocusNode.addListener(() {
      if (!_userFocusNode.hasFocus) {
        formSave();
        if (_userName.length > 0) {
          setState(() {
            _isEditUserName = true;
          });
        } else {
          setState(() {
            _isEditUserName = false;
          });
        }
      }
      checkLoginInfo();
    });
    _passwordFocusNode.addListener(() {
      if (!_passwordFocusNode.hasFocus) {
        formSave();
        if (_password.length > 0) {
          setState(() {
            _isEditPassword = true;
          });
        } else {
          setState(() {
            _isEditPassword = false;
          });
        }
        checkLoginInfo();
      }
    });
  }

  void checkLoginInfo() {
    if (_isAgree && _userName.length > 0 && _password.length > 0) {
      setState(() {
        _isCanLogin = true;
      });
    } else {
      setState(() {
        _isCanLogin = false;
      });
    }
  }

  void formSave() {
    var _state = _loginKey.currentState;
    if (_state.validate()) {
      _state.save();
    }
  }

  void login() {
    FocusScope.of(context).requestFocus(FocusNode());
    if (_isCanLogin) {
      _loginPasswordViewModel.getLoginWithPassword(_userName, _password,
          (bool success, String message) {
        handleLogin(success, message);
      });
    }
  }

  void handleLogin(bool success, String message) {
    if (success) {
      _loginPasswordViewModel.getUserInfo((success, bean) {
        if (success) {
          if (bean.userEpithetId == 5 && StringUtil.isEmpty(bean.ramarkJSON)) {
            showDialogInfo(S.of(context).login_error_warn);
          } else {
            if (bean.userEpithetId == 5 &&
                !StringUtil.isEmpty(bean.ramarkJSON)) {
            } else {
              openAndRemoveUtil(RouterName.index_page);
            }
          }
        }
      });
    } else {
      if (message == '用户不存在') {
        showDialogInfo(S.of(context).login_error_warn);
      } else if (message == '手机号或验证码错误') {
        showDialogInfo(S.of(context).login_error_warn);
      } else {
        showDialogInfo(message);
      }
    }
  }

  void showDialogInfo(String message) {
    showDialog(
        context: context,
        child: DialogTool(
          bgColor: Values.of(context).c_white_background,
          title: S.of(context).warn_title,
          titleFontSize: Values.s_text_18,
          titleColor: Values.of(context).c_black_front_33,
          content: message,
          middleTitle: S.of(context).warn_button_title,
          middleColor: Values.of(context).c_white_front,
          middleBgColor: Values.of(context).c_orange_background_0b,
          contentColor: Values.of(context).c_black_front_33,
          onTap: (int index) {},
        ));
  }

  void handleUserInfo(UserInfoBean bean) {}

  void changeLogin() {
    FocusScope.of(context).requestFocus(FocusNode());
    pop();
  }

  void forgetPassword() {
    FocusScope.of(context).requestFocus(FocusNode());
    open(RouterName.forget_login_pwd,argument:FORGET_LOGIN_PWD_PAGE_FROM_LOGIN_TYPE);
  }

  @override
  Widget build(BuildContext context) {
    return RootPageWidget(
        viewModel: _loginPasswordViewModel,
        closeKeyboardCallBack: (){},
        body: Container(
          color: Theme.of(context).scaffoldBackgroundColor,
          child: GestureDetector(
            behavior: HitTestBehavior.translucent,
            onTap: () {
              FocusScope.of(context).requestFocus(FocusNode());
            },
            child: Column(
              children: <Widget>[
                InputWidget(context),
                AgreementWidget(context),
                LoginButtonWidget(context),
                ChangeLoginWidget(context),
              ],
            ),
          ),
        ));
  }

  Widget InputWidget(BuildContext context) {
    final _size = MediaQuery.of(context).size;
    final _width = _size.width;
    return Container(
      child: Stack(
        children: <Widget>[
          Container(
            width: _width,
            padding: EdgeInsets.all(0),
            child: Image.asset('assets/images/login_bg.png', fit: BoxFit.fill),
          ),
          Positioned(
            left: 28,
            right: 28,
            bottom: 20,
            child: Form(
                key: _loginKey,
                child: Column(
                  children: <Widget>[
                    Container(
                      decoration: new BoxDecoration(
                          border: new Border(
                              bottom: BorderSide(
                                  color: Theme.of(context).dividerColor,
                                  width: 1.0))),
                      child: TextFormField(
                        textAlign: TextAlign.left,
                        focusNode: _userFocusNode,
                        style: TextStyle(
                            color: Theme.of(context).primaryColorDark,
                            fontWeight: Values.font_Weight_medium,
                            fontSize: Values.s_text_18),
                        keyboardType: TextInputType.emailAddress,
                        decoration: InputDecoration(
                          labelText: _isEditUserName
                              ? S.of(context).login_username
                              : null,
                          labelStyle: TextStyle(
                              color: Theme.of(context).canvasColor,
                              fontWeight: Values.font_Weight_normal,
                              fontSize: Values.s_text_12,
                              height: 0.8),
                          hintText: !_isEditUserName
                              ? S.of(context).login_username_hint
                              : null,
                          hintStyle: TextStyle(
                            color: Values.of(context).c_black_front_99,
                            fontWeight: Values.font_Weight_normal,
                            fontSize: Values.s_text_14,
                          ),
                          enabledBorder: UnderlineInputBorder(
                            borderSide: BorderSide(color: Values.c_translucent),
                          ),
                          focusedBorder: UnderlineInputBorder(
                            borderSide: BorderSide(color: Values.c_translucent),
                          ),
                        ),
                        cursorColor: Theme.of(context).primaryColorDark,
                        onSaved: (value) {
                          setState(() {
                            this._userName = value;
                          });
                        },
                        onChanged: (value) {
                          if (value.length >= 6) {
                            formSave();
                            setState(() {
                              this._userName = value;
                            });
                            checkLoginInfo();
                          } else {
                            setState(() {
                              this._userName = value;
                            });
                            checkLoginInfo();
                          }
                        },
                        onTap: () {
                          setState(() {
                            _isEditUserName = true;
                          });
                        },
                      ),
                    ),
                    Container(
                      height: 50,
                      decoration: new BoxDecoration(
                          border: new Border(
                              bottom: BorderSide(
                                  color: Theme.of(context).dividerColor,
                                  width: 1.0))),
                      margin: EdgeInsets.only(top: 12, left: 0, right: 0),
                      padding: EdgeInsets.all(0),
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: <Widget>[
                          Container(
                            width: _width - 56 - 40,
                            child:
                            TextFormField(
                              focusNode: _passwordFocusNode,
                              textAlign: TextAlign.left,
                              obscureText: _isObscure,
                              style: TextStyle(
                                  color: Theme.of(context).primaryColorDark,
                                  fontWeight: Values.font_Weight_medium,
                                  fontSize: Values.s_text_18),
                              decoration: InputDecoration(
                                labelText: _isEditPassword
                                    ? S.of(context).login_password
                                    : null,
                                labelStyle: TextStyle(
                                    color: Theme.of(context).canvasColor,
                                    fontWeight: Values.font_Weight_normal,
                                    fontSize: Values.s_text_12,
                                    height: 0.2),
                                hintText: !_isEditPassword
                                    ? S.of(context).login_password_hint
                                    : null,
                                hintStyle: TextStyle(
                                  color: Values.of(context).c_black_front_99,
                                  fontWeight: Values.font_Weight_normal,
                                  fontSize: Values.s_text_14,
                                ),
                                enabledBorder: UnderlineInputBorder(
                                  borderSide: BorderSide(color: Values.c_translucent),
                                ),
                                focusedBorder: UnderlineInputBorder(
                                  borderSide: BorderSide(color: Values.c_translucent),
                                ),
                              ),
                              keyboardType: TextInputType.visiblePassword,
                              cursorColor: Theme.of(context).primaryColorDark,
                              onSaved: (value) {
                                setState(() {
                                  this._password = value;
                                });
                              },
                              onChanged: (value) {
                                if (value.length > 0) {
                                  formSave();
                                  setState(() {
                                    this._password = value;
                                  });
                                  checkLoginInfo();
                                } else {
                                  setState(() {
                                    this._password = value;
                                  });
                                  checkLoginInfo();
                                }
                              },
                              onTap: () {
                                setState(() {
                                  _isEditPassword = true;
                                });
                              },
                            ),
                          ),
                          Container(
                            width: 36,
                            height: 36,
                            child: GestureDetector(
                              child: _isObscure
                                  ? Image.asset(
                                'assets/images/hidden_icon_grey.png',
                              )
                                  : Image.asset(
                                'assets/images/show_icon_grey.png',
                              ),
                              onTap: () {
                                setState(() {
                                  _isObscure = !_isObscure;
                                });
                              },
                            ),
                          ),
                        ],
                      ),
                    )
                  ],
                )),
          ),
        ],
      ),
    );
  }

  Widget AgreementWidget(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(left: 17, right: 27, top: 0),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Container(
            width: 34,
            height: 34,
            child: GestureDetector(
              child: _isAgree
                  ? Image.asset(
                      'assets/images/button_select.png',
                    )
                  : Image.asset(
                      'assets/images/button_unSelect.png',
                    ),
              onTap: () {
                setState(() {
                  _isAgree = !_isAgree;
                });
                formSave();
                checkLoginInfo();
              },
            ),
          ),
          Expanded(
            child: Text.rich(
              TextSpan(
                text: S.of(context).agreement_info,
                style: TextStyle(
                  fontSize: Values.s_text_12,
                  color: Theme.of(context).canvasColor,
                  fontWeight: Values.font_Weight_normal,
                ),
                children: [
                  TextSpan(
                      recognizer: TapGestureRecognizer()
                        ..onTap = () {
                          open(RouterName.web_view,
                              argument: WebParams(
                                  title: S.of(context).agreement_user,
                                  url: NetworkConfig().getHostForWeb() +
                                      NET_WEB_AGREMENT));
                        },
                      text: S.of(context).agreement_user,
                      style: TextStyle(
                        decoration: TextDecoration.underline,
                      )),
                  TextSpan(
                    text: S.of(context).agreement_info_1,
                  ),
                  TextSpan(
                      recognizer: TapGestureRecognizer()
                        ..onTap = () {
                          open(RouterName.web_view,
                              argument: WebParams(
                                  title: S.of(context).agreement_privacy,
                                  url: NetworkConfig().getHostForWeb() +
                                      NET_WEB_DISCLAIMER));
                        },
                      text: S.of(context).agreement_privacy,
                      style: TextStyle(
                        decoration: TextDecoration.underline,
                      )),
                  TextSpan(
                    text: S.of(context).agreement_info_2,
                  ),
                  TextSpan(
                      recognizer: TapGestureRecognizer()
                        ..onTap = () {
                          open(RouterName.web_view,
                              argument: WebParams(
                                  title: S.of(context).agreement_liability,
                                  url: NetworkConfig().getHostForWeb() +
                                      NET_WEB_PRIVACY));
                        },
                      text: S.of(context).agreement_liability,
                      style: TextStyle(
                        decoration: TextDecoration.underline,
                      )),
                  TextSpan(
                    text: S.of(context).agreement_info_3,
                  ),
                ],
              ),
            ),
          )
        ],
      ),
    );
  }

  Widget LoginButtonWidget(BuildContext context) {
    return Container(
      height: Values.d_44,
      margin: EdgeInsets.only(top: 18.0, left: 27, right: 27),
      child: new SizedBox.expand(
        child: new FlatButton(
          onPressed: () {
            formSave();
            login();
          },
          color: _isCanLogin
              ? Values.of(context).c_orange_background_0b
              : Values.of(context).c_grey_background_cc,
          splashColor: Values.c_translucent,
          highlightColor: Values.c_translucent,
          disabledColor: Values.of(context).c_grey_background_cc,
          child: new Text(
            S.of(context).login_name,
            style: TextStyle(
              fontSize: Values.s_text_16,
              color: Theme.of(context).primaryColorLight,
            ),
          ),
          shape: new RoundedRectangleBorder(
              borderRadius: new BorderRadius.circular(44.0)),
        ),
      ),
    );
  }

  Widget ChangeLoginWidget(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(top: 18.0, left: 40, right: 40),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: <Widget>[
          FlatButton(
            splashColor: Values.c_translucent,
            highlightColor: Values.c_translucent,
            onPressed: changeLogin,
            child: new Text(
              S.of(context).login_change_sms,
              style: TextStyle(
                fontSize: Values.s_text_12,
                color: Theme.of(context).canvasColor,
              ),
            ),
          ),
          FlatButton(
            splashColor: Values.c_translucent,
            highlightColor: Values.c_translucent,
            onPressed: forgetPassword,
            child: new Text(
              S.of(context).login_forget_password,
              style: TextStyle(
                fontSize: Values.s_text_12,
                color: Theme.of(context).canvasColor,
              ),
            ),
          ),
        ],
      ),
    );
  }
}
