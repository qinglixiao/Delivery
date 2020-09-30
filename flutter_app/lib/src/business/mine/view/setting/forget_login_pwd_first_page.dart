import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_ienglish_fine/src/business/login/bean/login.dart';
import 'package:flutter_ienglish_fine/src/business/mine/viewmodel/forget_login_pwd_view_model.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/src/business/login/viewmodel/login_password_view_model.dart';

class ForgetLoginPwdFirstPage extends StatefulWidget {
  @override
  _ForgetLoginPwdFirstPageState createState() =>
      _ForgetLoginPwdFirstPageState();
}

class _ForgetLoginPwdFirstPageState extends State<ForgetLoginPwdFirstPage>
    with PageBridge {
  ForgetLoginPwdViewModel _forgetLoginPwdViewModel;

  GlobalKey<FormState> _loginKey = new GlobalKey<FormState>();
  GlobalKey timerKey = GlobalKey();
  String _userName;
  String _password;
  bool _isCanChange = false;
  bool _isEditUserName = false;
  bool _isEditPassword = false;
  FocusNode _userFocusNode = FocusNode();
  FocusNode _passwordFocusNode = FocusNode();

  int fromPageType;//区分修改密码的来源页面

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    _forgetLoginPwdViewModel = ForgetLoginPwdViewModel();
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

  void sendMessage() {
    _forgetLoginPwdViewModel.sendMessage(_userName).then((value) {
      if (value.isSuccess()) {
        (timerKey.currentState as TimerCountDownState).beginCountDown();
      } else {
        ProgressHUD.showError(warnText: value.message);
      }
    });
  }

  void checkLoginInfo() {
    if (_userName.length > 0 && _password.length == 6) {
      setState(() {
        _isCanChange = true;
      });
    } else {
      setState(() {
        _isCanChange = false;
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
    if (_isCanChange) {
      open(RouterName.forget_login_pwd_change,
          argument: {"account": _userName, "code": _password,"fromType":fromPageType});
    } else {}
  }

  @override
  Widget build(BuildContext context) {
    getInitArg(context).then((value) {
      if(value is int){
        fromPageType = value;
      }
    });
    return RootPageWidget(
        appBar: IsAppBar(
          title: S.of(context).forget_login_password_title,
        ),
        viewModel: _forgetLoginPwdViewModel,
        body: Container(
          color: Theme.of(context).scaffoldBackgroundColor,
          child: GestureDetector(
            behavior: HitTestBehavior.translucent,
            onTap: () {
              FocusScope.of(context).requestFocus(FocusNode());
            },
            child: Column(
              children: <Widget>[
                StatusWidget(),
                InputWidget(),
                LoginButtonWidget(),
              ],
            ),
          ),
        ));
  }

  Widget StatusWidget() {
    return Container(
        margin: EdgeInsets.only(top: Values.d_50),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Image.asset('assets/images/first_icon.png'),
                SizedBox(height: Values.d_10),
                Text(
                  S.of(context).check_tel_email_title,
                  style: TextStyle(
                    fontSize: Values.s_text_12,
                    fontWeight: Values.font_Weight_normal,
                    color: Values.of(context).c_black_front_99,
                  ),
                ),
              ],
            ),
            Container(
              margin: EdgeInsets.only(top: Values.d_8),
              height: 1,
              width: 100,
              color: Values.of(context).c_grey_ea,
            ),
            Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Image.asset('assets/images/second_unselect_icon.png'),
                SizedBox(height: Values.d_10),
                Text(
                  S.of(context).reset_login_password_title,
                  style: TextStyle(
                    fontSize: Values.s_text_12,
                    fontWeight: Values.font_Weight_normal,
                    color: Values.of(context).c_black_front_99,
                  ),
                ),
              ],
            ),
          ],
        ));
  }

  Widget InputWidget() {
    final _size = MediaQuery.of(context).size;
    final _width = _size.width;
    return Container(
      margin: EdgeInsets.only(
          top: Values.d_50,
          left: Values.d_28,
          right: Values.d_28,
          bottom: Values.d_30),
      child: Form(
          key: _loginKey,
          child: Column(
            children: <Widget>[
              Container(
                height: 50,
                decoration: new BoxDecoration(
                    border: new Border(
                        bottom: BorderSide(
                            color: Values.of(context).c_grey_ea, width: 1.0))),
                child: TextFormField(
                  textAlign: TextAlign.left,
                  focusNode: _userFocusNode,
                  style: TextStyle(
                      color: Values.of(context).c_black_front_33,
                      fontWeight: Values.font_Weight_medium,
                      fontSize: Values.s_text_18),
                  keyboardType: TextInputType.emailAddress,
                  decoration: InputDecoration(
                    labelText:
                        _isEditUserName ? S.of(context).tel_email_title : null,
                    labelStyle: TextStyle(
                        color: Values.of(context).c_black_front_99,
                        fontWeight: Values.font_Weight_normal,
                        fontSize: Values.s_text_12,
                        height: 0.8),
                    hintText: !_isEditUserName
                        ? S.of(context).tel_email_title_hint
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
                  onSaved: (value) {
                    setState(() {
                      this._userName = value;
                    });
                  },
                  onChanged: (value) {
                    if (value.length > 0) {
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
                                color: Values.of(context).c_black_front_99,
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
                          keyboardType: TextInputType.number,
                          cursorColor: Values.of(context).c_black_front_33,
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
                              titleColor: Values.of(context).c_orange_front_0b,
                              countDowntitle: "",
                              startCountDownTitle: '00:',
                              countDownTitleColor:
                                  Values.of(context).c_grey_front_cc,
                              finishTitle: S.of(context).login_sms_btn_again,
                              fontSize: Values.s_text_14,
                              bgColor: Values.of(context).c_grey_background_f8,
                              beginCallBack: () {
                                formSave();
                                if (_userName != null &&
                                    (_userName.length == 11 || _userName.contains('@'))) {
                                  sendMessage();
                                } else if (_userName != null ||
                                    _userName.length == 0) {
                                  ProgressHUD.showError(
                                      warnText: S.of(context).sms_warn3);
                                } else {
                                  ProgressHUD.showError(
                                      warnText: S.of(context).sms_warn4);
                                }
                              },
                              finishCallBack: () {})),
                    ],
                  ))
            ],
          )),
    );
  }

  Widget LoginButtonWidget() {
    return Container(
      height: Values.d_44,
      margin: EdgeInsets.only(top: 18.0, left: 27, right: 27),
      child: new SizedBox.expand(
        child: new FlatButton(
          onPressed: () {
            formSave();
            login();
          },
          color: _isCanChange
              ? Values.of(context).c_orange_background_0b
              : Values.of(context).c_grey_background_cc,
          splashColor: Values.c_translucent,
          highlightColor: Values.c_translucent,
          disabledColor: Values.of(context).c_grey_background_cc,
          child: new Text(
            S.of(context).next_title,
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
}
