import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';

///
///create by lx
///date 2020/8/29

class ChangedElementPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return ChangedElementState();
  }
}

class ChangedElementState extends State<ChangedElementPage> {
  List<BlockWidget> _list;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    _list = [BlockWidget(Colors.red), BlockWidget(Colors.amber)];
  }

  _changed() {
    setState(() {
      _list.insert(0, _list.removeAt(1));
    });
  }

  @override
  Widget build(BuildContext context) {
    return RootPageWidget(
        appBar: IsAppBar(
          title: "change",
        ),
        body: Column(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: <Widget>[
            Row(
              children: _list,
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: [
                RaisedButton(
                  onPressed: _changed,
                  child: Text("交换颜色"),
                ),
                RaisedButton(
                  onPressed: () {
                    Future.error("create crash by person");
                  },
                  child: Text("Crash"),
                )
              ],
            ),
          ],
        ));
  }
}

class BlockWidget extends StatelessWidget {
  Color _color;

  BlockWidget(this._color);

  @override
  Widget build(BuildContext context) {
    return Container(
      width: 100,
      height: 100,
      color: _color,
    );
  }
}
