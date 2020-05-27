import 'package:fluttermodule/case1/model/authentication_state.dart';

class ApplicationState {
  AuthenticationState authenticationState;
  String userFirstName;
  String userLastName;
  bool isWorking;

  ApplicationState({
    this.authenticationState,
    this.userFirstName,
    this.userLastName,
    this.isWorking = false,
  });
}