import 'package:flutter/material.dart';
import 'package:fluttermodule/case1/bloc/application_state_bloc.dart';
import 'package:fluttermodule/common/bloc_provider.dart';

class TransferPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    ApplicationStateBloc bloc = BlocProvider.of<ApplicationStateBloc>(context);
//    Logger.print("transfer of bloc ${bloc.hashCode}");

    return Scaffold(
        appBar: AppBar(
          title: Text("APP Bar "),
          centerTitle: true,
        ),
        body: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: <Widget>[
            RaisedButton(
              onPressed: () {
                Navigator.pop(context, "from transfer_page");
              },
              child: Text("返回"),
            ),
            Text(ModalRoute.of(context).settings.arguments ??
                "this is transfer page"),
            RaisedButton(
              onPressed: () {
                Navigator.pushNamed(context, "next", arguments: "pushNamed");
              },
              child: Text("下页"),
            ),
          ],
        ));
  }
}
