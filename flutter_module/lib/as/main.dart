import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';

import '../transfer_page.dart';
import 'business/budget/view/good_list_page.dart';
import 'business/budget/viewmodel/good_list_view_model.dart';
import 'comm/init_app.dart';

var _routes = {
  "next": (context) => TransferPage(),
};

class App extends StatelessWidget {
  BaseViewModel _goodListViewModel;

  @override
  Widget build(BuildContext context) {
    _goodListViewModel = GoodListViewModel();
    return StateProvider(
      viewModel: _goodListViewModel,
      child: MaterialApp(
        routes: _routes,
        home: GoodListPage(),
      ),
    );
  }
}

void main() {
  debugProfileBuildsEnabled = true;
  InitApp.init().then((value) => runApp(App()));
}
