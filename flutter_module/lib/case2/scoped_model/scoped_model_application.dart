import 'package:flutter/material.dart';
import 'package:fluttermodule/case1/scoped_model/scoped_model_page.dart';

class Case2ScopedModelApplication extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'ReduxScopedModelBloc_Comparison',
      home: ScopedModelPage(),
    );
  }
}
