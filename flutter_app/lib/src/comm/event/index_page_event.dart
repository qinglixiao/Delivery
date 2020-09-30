import 'package:flutter_lib/flutter_lib.dart';

///
///create by lx
///date 2020/9/12
///

class IndexPageEvent extends EventObject {
  int type; //消息类型(1:tab切换)

  IndexPageEvent({this.type, dynamic data}) : super(data: data);
}
