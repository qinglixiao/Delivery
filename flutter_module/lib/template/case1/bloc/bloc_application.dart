import 'package:flutter/material.dart';
import 'package:fluttermodule/case1/bloc/application_state_bloc.dart';
import 'package:fluttermodule/case1/bloc/bloc_page.dart';
import 'package:fluttermodule/common/bloc_provider.dart';
import 'package:fluttermodule/transfer_page.dart';

var _routes = {
  "next": (context) => TransferPage(),
};

class Case1BlocApplication extends StatefulWidget {
  @override
  _Case1BlocApplicationState createState() => _Case1BlocApplicationState();
}

class _Case1BlocApplicationState extends State<Case1BlocApplication> {
  ApplicationStateBloc applicationBloc;

  @override
  void initState() {
    super.initState();
    applicationBloc = ApplicationStateBloc();
  }

  @override
  void dispose() {
    applicationBloc?.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return BlocProvider<ApplicationStateBloc>(
      bloc: applicationBloc,
      child: MaterialApp(
        title: 'ReduxScopedModelBloc_Comparison',
        routes: _routes,
        home: BlocPage(),
      ),
    );
  }
}
