import 'dart:io';

import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_ienglish_fine/src/business/login/comm/interfaces.dart';
import 'package:flutter_ienglish_fine/src/business/login/viewmodel/login_sms_view_model.dart';

class LoginSmsPage extends StatefulWidget {
  @override
  _LoginSmsPageState createState() => _LoginSmsPageState();
}

class _LoginSmsPageState extends State<LoginSmsPage> with PageBridge {
  LoginSmsViewModel _loginSmsViewModel;

  GlobalKey<FormState> _loginKey = new GlobalKey<FormState>();
  GlobalKey timerKey = GlobalKey();

  String _userName;
  String _password;
  bool _isAgree = false;
  bool _isCanLogin = false;
  bool _isEditUserName = false;
  bool _isEditPassword = false;
  FocusNode _userFocusNode = FocusNode();
  FocusNode _passwordFocusNode = FocusNode();

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    _loginSmsViewModel = LoginSmsViewModel();
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
//  _userName = '18000000017';
//  _password = '123456';
    if (_isAgree && _userName.length == 11 && _password.length == 6) {
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

  void sendMessage() {
    _loginSmsViewModel.getSmsCode(_userName, (bool success,String message) {
      if (success) {
        (timerKey.currentState as TimerCountDownState).beginCountDown();
        ProgressHUD.showText(warnText: S.of(context).send_message_success);
      }
      else{
        ProgressHUD.showError(warnText: message != null ? message : S.of(context).send_message_success);
      }
    });
  }

  void login() {
    FocusScope.of(context).requestFocus(FocusNode());
    if (_isCanLogin) {
//      _userName = '18000000018';
//      _password = '123456';
      _loginSmsViewModel.getLoginWithSms(_userName, _password,
          (bool success, String message) {
        handleLogin(success, message);
      });
    } else {}
  }

  void handleLogin(bool success, String message) {
    if (success) {
      _loginSmsViewModel.getUserInfo((success, bean) {
        if (success) {
          if (bean.userEpithetId == 5 && StringUtil.isEmpty(bean.ramarkJSON)) {
            showDialogInfo(S.of(context).login_error_warn);
          }
          else {
            if (bean.userEpithetId == 5 &&
                !StringUtil.isEmpty(bean.ramarkJSON)) {
            } else {
              openAndRemoveUtil(RouterName.index_page);
            }
          }
        } else {
          showDialogInfo(bean.message);
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

  void changeLogin() {
    FocusScope.of(context).requestFocus(FocusNode());
    open(RouterName.login_password);
  }

  @override
  Widget build(BuildContext context) {
    return RootPageWidget(
        viewModel: _loginSmsViewModel,
        closeKeyboardCallBack: (){},
        body: Container(
          color: Values.of(context).c_grey_background_f8,
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
                                  color: Values.of(context).c_grey_ea,
                                  width: 1.0))),
                      child: TextFormField(
                        textAlign: TextAlign.left,
                        focusNode: _userFocusNode,
                        style: TextStyle(
                            color: Values.of(context).c_black_front_33,
                            fontWeight: Values.font_Weight_medium,
                            fontSize: Values.s_text_18),
                        keyboardType: TextInputType.number,
                        decoration: InputDecoration(
                          labelText: _isEditUserName
                              ? S.of(context).login_phone
                              : null,
                          labelStyle: TextStyle(
                              color: Values.of(context).c_black_front_99,
                              fontWeight: Values.font_Weight_normal,
                              fontSize: Values.s_text_12,
                              height: 0.8),
                          hintText: !_isEditUserName
                              ? S.of(context).login_phone_hint
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
                        cursorColor: Values.of(context).c_black_front_33,
                        inputFormatters: [LengthLimitingTextInputFormatter(11)],
                        onSaved: (value) {
                          if (value.length <= 11) {
                            setState(() {
                              this._userName = value;
                            });
                          }
                        },
                        onChanged: (value) {
                          if (value.length == 11) {
                            formSave();
                            setState(() {
                              this._userName = value;
                            });
                            checkLoginInfo();
                          } else if (value.length < 11) {
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
                        height: 58,
                        decoration: new BoxDecoration(
                            border: new Border(
                                bottom: BorderSide(
                                    color: Values.of(context).c_grey_ea,
                                    width: 1.0))),
                        margin: EdgeInsets.only(top: 4, left: 0, right: 0),
                        padding: EdgeInsets.all(0),
                        child: Row(
                          crossAxisAlignment: CrossAxisAlignment.end,
                          children: <Widget>[
                            Container(
                              width: _width - 56 - 101,
                              child: TextFormField(
                                focusNode: _passwordFocusNode,
                                textAlign: TextAlign.left,
                                style: TextStyle(
                                    color: Values.of(context).c_black_front_33,
                                    fontWeight: Values.font_Weight_medium,
                                    fontSize: Values.s_text_18),
                                decoration: InputDecoration(
                                  labelText: _isEditPassword
                                      ? S.of(context).login_sms
                                      : null,
                                  labelStyle: TextStyle(
                                      color:
                                          Values.of(context).c_black_front_99,
                                      fontWeight: Values.font_Weight_normal,
                                      fontSize: Values.s_text_12,
                                      height: 0.8),
                                  hintText: !_isEditPassword
                                      ? S.of(context).login_sms_hint
                                      : null,
                                  hintStyle: TextStyle(
                                    color: Values.of(context).c_black_front_99,
                                    fontWeight: Values.font_Weight_normal,
                                    fontSize: Values.s_text_14,
                                  ),
                                  enabledBorder: UnderlineInputBorder(
                                    borderSide:
                                        BorderSide(color: Values.c_translucent),
                                  ),
                                  focusedBorder: UnderlineInputBorder(
                                    borderSide:
                                        BorderSide(color: Values.c_translucent),
                                  ),
                                ),
                                inputFormatters: [
                                  LengthLimitingTextInputFormatter(6)
                                ],
                                keyboardType: TextInputType.number,
                                cursorColor:
                                    Values.of(context).c_black_front_33,
                                onSaved: (value) {
                                  if (value.length <= 6) {
                                    setState(() {
                                      this._password = value;
                                    });
                                  }
                                },
                                onChanged: (value) {
                                  if (value.length == 6) {
                                    formSave();
                                    setState(() {
                                      this._password = value;
                                    });
                                    checkLoginInfo();
                                  } else if (value.length < 6) {
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
                              color: Values.of(context).c_grey_ea,
                              width: 1,
                              height: 15,
                              margin: EdgeInsets.only(bottom: 15),
                            ),
                            Container(
                                width: 90,
                                height: 30,
                                margin: EdgeInsets.only(left: 10, bottom: 7),
                                child: TimerCountDown(
                                    key: timerKey,
                                    title: S.of(context).login_sms_btn,
                                    titleColor:
                                        Values.of(context).c_orange_front_0b,
                                    countDowntitle: "",
                                    startCountDownTitle: '00:',
                                    countDownTitleColor:
                                        Values.of(context).c_grey_front_cc,
                                    finishTitle:
                                        S.of(context).login_sms_btn_again,
                                    fontSize: Values.s_text_14,
                                    bgColor:
                                        Values.of(context).c_grey_background_f8,
                                    beginCallBack: () {
                                      formSave();
                                      if (_userName != null &&
                                          _userName.length == 11) {
                                        sendMessage();
                                      } else if (_userName != null ||
                                          _userName.length == 0) {
                                        ProgressHUD.showError(
                                            warnText: S.of(context).sms_warn1);
                                      } else {
                                        ProgressHUD.showError(
                                            warnText: S.of(context).sms_warn2);
                                      }
                                    },
                                    finishCallBack: () {})),
                          ],
                        ))
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
                  color: Values.of(context).c_black_front_99,
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
              fontSize: Values.s_text_14,
              color: Values.of(context).c_white_front,
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
      margin: EdgeInsets.only(top: 18.0),
      child: new FlatButton(
        splashColor: Values.c_translucent,
        highlightColor: Values.c_translucent,
        onPressed: changeLogin,
        child: new Text(
          S.of(context).login_change,
          style: TextStyle(
            fontSize: Values.s_text_12,
            color: Values.of(context).c_black_front_99,
          ),
        ),
      ),
    );
  }
}
