
import 'package:fluttermodule/case1/redux/actions.dart';
import 'package:fluttermodule/case1/redux/application_state.dart';
import 'package:redux/redux.dart';

void authenticateMiddleware(
    Store<ApplicationState> store, dynamic action, NextDispatcher next) {
  if (action is LoginAction) {
    //
    // Notify that we are performing the authentication
    //
    store.dispatch(ServerCommunicationAction());
    
    //
    // Simulate delayed authentication
    //
    Future.delayed(Duration(seconds: 2)).then((_) {
      store.dispatch(AuthenticatedAction(
        firstName: "John",
        lastName: "Doe",
      ));
    });
  }

  //
  // Do not forget linking the middlewares
  //
  next(action);
}

void loggerMiddleware(Store<ApplicationState> store, dynamic action, NextDispatcher next){

  //
  // Simply log the action
  //
  print('Action: $action');
  
  //
  // Do not forget linking the middlewares
  //
  next(action);
}

void nextMiddleware(Store<ApplicationState> store, dynamic action, NextDispatcher next){

  //
  // Simply log the action
  //
  print('Action Next: $action');
//  debugPrint('Action Next_debug: $action');
//  Logger.print('Action Next_debug: $action');

  //
  // Do not forget linking the middlewares
  //
  next(action);
}