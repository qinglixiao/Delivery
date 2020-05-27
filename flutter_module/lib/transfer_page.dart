import 'package:flutter/material.dart';

class TransferPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
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
                Navigator.pushNamed(context, "next",arguments: "pushNamed");
              },
              child: Text("下页"),
            ),
          ],
        ));
  }
}
