import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:fluttermodule/as/business/budget/viewmodel/good_list_view_model.dart';

class GoodListPage extends StatefulWidget with UIWrap {
  @override
  State<StatefulWidget> createState() {
    return _GoodListState();
  }
}

class _GoodListState extends State<GoodListPage> {
  GoodListViewModel _goodListViewModel;

  @override
  void initState() {
    super.initState();
    _goodListViewModel = GoodListViewModel();
  }

  @override
  Widget build(BuildContext context) {
    return RootPageWidget(
      viewModel: _goodListViewModel,
      body: Scaffold(
        appBar: AppBar(
          title: Text('s 订货'),
        ),
        body: RaisedButton(
            child: Text("进入下一页"),
            onPressed: () {
              Navigator.pushNamed(context, "next", arguments: "from bloc page");
            }),
      ),
    );
  }
}
