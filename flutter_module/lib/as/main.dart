import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:fluttermodule/as/comm/name_router.dart';
import 'package:fluttermodule/as/view_model/app_view_model.dart';

import 'comm/init_app.dart';

void main() {
  debugProfileBuildsEnabled = true;
  InitApp.init().then(
    (value) => runApp(StateProvider(
      viewModel: AppViewModel(),
      child: MaterialApp(
        title: "top i Eng",
        theme: null,
        routes: routers,
      ),
    )),
  );
}
