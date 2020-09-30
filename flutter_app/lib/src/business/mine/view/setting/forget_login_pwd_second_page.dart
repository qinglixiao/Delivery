import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_ienglish_fine/src/business/mine/comm/constant.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_ienglish_fine/src/business/mine/viewmodel/forget_login_pwd_view_model.dart';

class ForgetLoginPwdSecondPage extends StatefulWidget {
  @override
  _ForgetLoginPwdSecondPageState createState() => _ForgetLoginPwdSecondPageState();
}

class _ForgetLoginPwdSecondPageState extends State<ForgetLoginPwdSecondPage> with PageBridge {
  ForgetLoginPwdViewModel _forgetLoginPwdViewModel;

  GlobalKey<FormState> _loginKey = new GlobalKey<FormState>();
  String _userName;
  String _password;
  bool _isCanChange = false;
  bool _isEditUserName = false;
  bool _isEditPassword = false;
  bool _isObscure = true;
  bool _isObscureTwo = true;
  FocusNode _userFocusNode = FocusNode();
  FocusNode _passwordFocusNode = FocusNode();
  String _account;
  String _code;
  int _fromType;

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

  void checkLoginInfo() {
    if (_userName.length >= 6 && _password.length >= 6 && _userName == _password) {
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

  void save() {
    FocusScope.of(context).requestFocus(FocusNode());
    if (_isCanChange) {
      _forgetLoginPwdViewModel.savePwd(_account, _code, _password).then((value){
        if(value.isSuccess()){
          if(_fromType == FORGET_LOGIN_PWD_PAGE_FROM_PWD_MANAGER_TYPE){
            popUtil(RouterName.login_pwd_manager);
          } else if(_fromType == FORGET_LOGIN_PWD_PAGE_FROM_LOGIN_TYPE){
            popUtil(RouterName.login_password);
          }
        }
        else{
          showDialogInfo(value.message);
        }
      });
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

  @override
  Widget build(BuildContext context) {
    getInitArg(context).then((params) {
      _account = params['account'];
      _code = params['code'];
      if(params['fromType'] is int){
        _fromType = params['fromType'];
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
                SaveButtonWidget(),
              ],
            ),
          ),
        ));
  }

  Widget StatusWidget(){
    return Container(
        margin: EdgeInsets.only(top:Values.d_50),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Image.asset('assets/images/select_icon.png'),
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
              margin: EdgeInsets.only(top:Values.d_8),
              height: 1,
              width: 100,
              color: Values.of(context).c_grey_ea,
            ),
            Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Image.asset('assets/images/second_select_icon.png'),
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
        )
    );
  }

  Widget InputWidget() {
    final _size = MediaQuery.of(context).size;
    return Container(
      margin: EdgeInsets.only(top:Values.d_50,left:Values.d_28,right:Values.d_28,bottom:Values.d_30),
      child: Form(
          key: _loginKey,
          child: Column(
            children: <Widget>[
              Container(
                height: 50,
                decoration: new BoxDecoration(
                    border: new Border(
                        bottom: BorderSide(
                            color: Theme.of(context).dividerColor,
                            width: 1.0))),
                child:Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: <Widget>[
                    Container(
                      width: _size.width - 56-40,
                      child:
                      TextFormField(
                        textAlign: TextAlign.left,
                        focusNode: _userFocusNode,
                        obscureText: _isObscure,
                        style: TextStyle(
                            color: Theme.of(context).primaryColorDark,
                            fontWeight: Values.font_Weight_medium,
                            fontSize: Values.s_text_18),
                        keyboardType: TextInputType.text,
                        decoration: InputDecoration(
                          labelText: _isEditUserName
                              ? S.of(context).new_pwd
                              : null,
                          labelStyle: TextStyle(
                              color: Theme.of(context).canvasColor,
                              fontWeight: Values.font_Weight_normal,
                              fontSize: Values.s_text_12,
                              height: 0.8),
                          hintText: !_isEditUserName
                              ? S.of(context).new_pwd_hint_text
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
                )
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
                child:Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: <Widget>[
                    Container(
                      width: _size.width - 56-40,
                      child:
                      TextFormField(
                        focusNode: _passwordFocusNode,
                        textAlign: TextAlign.left,
                        obscureText: _isObscureTwo,
                        style: TextStyle(
                            color: Theme.of(context).primaryColorDark,
                            fontWeight: Values.font_Weight_medium,
                            fontSize: Values.s_text_18),
                        decoration: InputDecoration(
                          labelText: _isEditPassword
                              ? S.of(context).pwd_again_confirm
                              : null,
                          labelStyle: TextStyle(
                              color: Theme.of(context).canvasColor,
                              fontWeight: Values.font_Weight_normal,
                              fontSize: Values.s_text_12,
                              height: 0.2),
                          hintText: !_isEditPassword
                              ? S.of(context).new_pwd_again_hint_text
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
                        keyboardType: TextInputType.text,
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
                        child: _isObscureTwo
                            ? Image.asset(
                          'assets/images/hidden_icon_grey.png',
                        )
                            : Image.asset(
                          'assets/images/show_icon_grey.png',
                        ),
                        onTap: () {
                          setState(() {
                            _isObscureTwo = !_isObscureTwo;
                          });
                        },
                      ),
                    ),
                  ],
                )
              )
            ],
          )),
    );
  }

  Widget SaveButtonWidget() {
    return Container(
      height: Values.d_44,
      margin: EdgeInsets.only(top: 18.0, left: 27, right: 27),
      child: new SizedBox.expand(
        child: new FlatButton(
          onPressed: () {
            formSave();
            save();
          },
          color: _isCanChange
              ? Values.of(context).c_orange_background_0b
              : Values.of(context).c_grey_background_cc,
          splashColor: Values.c_translucent,
          highlightColor: Values.c_translucent,
          disabledColor: Values.of(context).c_grey_background_cc,
          child: new Text(
            S.of(context).common_finish,
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
