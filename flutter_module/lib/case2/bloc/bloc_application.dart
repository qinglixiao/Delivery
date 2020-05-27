import 'package:flutter/material.dart';

import 'bloc_page.dart';

class Case2BlocApplication extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'ReduxScopedModelBloc_Comparison',
      home: BlocPage(),
    );
  }
}
