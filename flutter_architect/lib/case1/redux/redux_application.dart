import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:flutter_architect/case1/model/authentication_state.dart';
import 'package:flutter_architect/case1/redux/application_state.dart';
import 'package:flutter_architect/case1/redux/middlewares.dart';
import 'package:flutter_architect/case1/redux/reducer.dart';
import 'package:flutter_architect/case1/redux/redux_page.dart';
import 'package:redux/redux.dart';

class Case1ReduxApplication extends StatefulWidget {
  @override
  _Case1ReduxApplicationState createState() => _Case1ReduxApplicationState();
}

class _Case1ReduxApplicationState extends State<Case1ReduxApplication> {
  Store<ApplicationState> applicationStore;

  @override
  void initState(){
    super.initState();
    applicationStore = Store<ApplicationState>(
      authenticationReducer,
      initialState: ApplicationState(authenticationState: AuthenticationState.notAuthenticated),
      middleware: <Middleware<ApplicationState>>[
        authenticateMiddleware,
        loggerMiddleware,
        nextMiddleware,
      ],
    );
  }

  @override
  void dispose(){
    applicationStore?.teardown();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return StoreProvider<ApplicationState>(
      store: applicationStore,
      child: MaterialApp(
        title: 'ReduxScopedModelBloc_Comparison',
        home: ReduxPage(),
      ),
    );
  }
}