import 'dart:async';
import 'dart:io';
import 'dart:isolate';

import 'package:flutter/cupertino.dart';

///
///create by lx
///date 2020/7/20
///

class F extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    WidgetsBinding.instance.addPostFrameCallback(run);
    return Center();
  }

  run(_) {
    f2();
    f1();
    f3();
    f6();
    f4();
    f7();
  }

  Future f1() async{
    await Future(() => print("f1 异步"));
  }

  Future f2() async{
    await Future(() => print("f2 异步"));
  }

  Future f6() async{
    Timer.run(() {
      print("f6 timer");
    });
    
    Isolate.spawn((message) { }, "message").then((value) => value);
  }

  Future f7() async{
    Future.microtask(() => print("f7 micro"));
  }

  f3() {
    print("f3==同步");
    sleep(Duration(seconds: 1));
  }

  f4() {
    print("f4==同步");
    sleep(Duration(seconds: 4));
  }
}
