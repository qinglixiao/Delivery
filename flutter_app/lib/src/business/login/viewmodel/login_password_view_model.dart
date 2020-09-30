import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/src/business/login/model/login_password_model.dart';
import 'package:flutter_ienglish_fine/src/business/login/bean/login.dart';

class LoginPasswordViewModel extends BaseViewModel {
  LoginPasswordViewModel() : super();

  LoginModel _loginModel = LoginModel();

  Stream<LoginBean> get streamLoginWithPassword =>
      _loginModel.getStream<LoginBean>();

  Stream<UserInfoBean> get streamGetUserInfo =>
      _loginModel.getStream<UserInfoBean>();

  void getLoginWithPassword(String phone, String code,
      Function(bool success, String message) callback) {
    ProgressHUD.showLoading();
    String errorcode;
    String token;
    _loginModel
        .getLoginWithPassword(phone, code)
        .then((value) => {
              errorcode = (value as LoginBean).error_code,
              token = (value as LoginBean).token,
              if (errorcode == '1' && token != null)
                {
                  SpUtil.setSessionId(token),
                  SpUtil.setToken(token),
                  eventCenter.emit(LoginEvent(0)),
                  callback(true, '')
                }
              else
                {
                  callback(false, (value as LoginBean).message),
                  ProgressHUD.hiddenHUD()
                }
            })
        .catchError((e) {
      error(e);
    });
  }

  void getUserInfo(Function(bool success,UserInfoBean bean) callback) {
    String errorcode;
    _loginModel
        .getUserInfo()
        .then((value) => {
              errorcode = (value as UserInfoBean).error_code,
              ProgressHUD.hiddenHUD(),
              if (errorcode == '1')
                {
                  callback(true,value),
                  SpUtil.setUserId((value as UserInfoBean).userId.toString()),
                  SpUtil.setUserName((value as UserInfoBean).username),
                  SpUtil.setUserImage((value as UserInfoBean).imgUrl),
                  SpUtil.setUserMobile((value as UserInfoBean).mobile),
                  SpUtil.setUserNumberDesc((value as UserInfoBean).numberDesc),
                  SpUtil.setUserRankNum((value as UserInfoBean).rankNum),
                  SpUtil.setUserCapacityId(
                      (value as UserInfoBean).capacityId.toString()),
                }
              else
                {
                  callback(false,value),
                }
            });
  }

  @override
  void dispose() {
    super.dispose();
    _loginModel?.close();
  }

}
