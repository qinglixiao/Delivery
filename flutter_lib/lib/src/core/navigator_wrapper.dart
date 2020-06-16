import 'package:flutter/cupertino.dart';
import 'package:flutter_lib/flutter_lib.dart';

class NavigatorWrapper {
  static final NavigatorWrapper _instance = NavigatorWrapper._();

  NavigatorWrapper._();

  NavigatorState _navigatorState;

  static NavigatorWrapper of(
    BuildContext context, {
    bool rootNavigator = false,
    bool nullOk = false,
  }) {
    _instance._navigatorState = Navigator.of(context);
    return _instance;
  }

  Future<T> pushName<T extends Object>(
    String routeName, {
    Object arguments,
  }) {
    return _navigatorState.pushNamed(routeName, arguments: arguments);
  }

  void pop<T extends Object>([T result]) {
    if (_navigatorState.canPop()) {
      _navigatorState.pop(result);
      _navigatorState = null;
    }
  }

  Object arguments() {
    if (_navigatorState == null) {
      throw ASError(message: "Navigator has not be Init ");
    }
    return ModalRoute.of(_navigatorState.context).settings.arguments;
  }
}
