import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/src/business/login/model/login_sms_model.dart';
import 'package:flutter_ienglish_fine/src/business/login/bean/login.dart';

class LoginSmsViewModel extends BaseViewModel {
  LoginSmsViewModel() : super();

  LoginModel _loginModel = LoginModel();

  Stream<SmsBean> get streamSendSMS => _loginModel.getStream<SmsBean>();

  Stream<LoginBean> get streamLoginWithSms => _loginModel.getStream<LoginBean>();

  Stream<UserInfoBean> get streamGetUserInfo => _loginModel.getStream<UserInfoBean>();

  void getSmsCode(String phone, Function(bool success, String message) callback) {
    String errorcode;
    String message;
    _loginModel
        .getSmsCode(phone)
        .then((value) => {
              print(value),
              errorcode = (value as SmsBean).error_code,
              message = (value as SmsBean).message,
              if (errorcode == '1')
                {
                  callback(true, null),
                }
              else
                {callback(false, message)}
            })
        .catchError((e) {
      error(e);
    });
  }

  void getLoginWithSms(String phone, String code, Function(bool success, String message) callback) {
    ProgressHUD.showLoading();
    String errorcode;
    String token;
    _loginModel
        .getLoginWithSms(phone, code)
        .then((value) => {
              errorcode = (value as LoginBean).error_code,
              token = (value as LoginBean).token,
              if (errorcode == '1' && token != null)
                {
                  callback(true, ''),
                  SpUtil.setSessionId(token),
                  SpUtil.setToken(token),
                  eventCenter.emit(LoginEvent(0)),
                }
              else
                {ProgressHUD.hiddenHUD(), callback(false, (value as LoginBean).message)}
            })
        .catchError((e) {
      error(e);
    });
  }

  void getUserInfo(Function(bool success, UserInfoBean bean) callback) {
    String errorcode;
    _loginModel.getUserInfo().then((value) => {
          errorcode = (value as UserInfoBean).error_code,
          ProgressHUD.hiddenHUD(),
          if (errorcode == '1')
            {
              callback(true, value),
              SpUtil.setUserId((value as UserInfoBean).userId.toString()),
              SpUtil.setUserName((value as UserInfoBean).username),
              SpUtil.setUserImage((value as UserInfoBean).imgUrl),
              SpUtil.setUserMobile((value as UserInfoBean).mobile),
              SpUtil.setUserNumberDesc((value as UserInfoBean).numberDesc),
              SpUtil.setUserRankNum((value as UserInfoBean).rankNum),
              SpUtil.setUserCapacityId((value as UserInfoBean).capacityId.toString()),
            }
          else
            {
              callback(false, value),
            }
        });
  }

  @override
  void dispose() {
    super.dispose();
    _loginModel?.close();
  }
}
