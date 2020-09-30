import 'package:flutter_architect/case1/model/authentication_state.dart';

class ApplicationModel {
  String firstName = "";
  String lastName = "";
  bool isWorking = false;
  AuthenticationState authenticationState = AuthenticationState.notAuthenticated;
}