import 'package:flutter_lib/flutter_lib.dart';

///  created by：sunyuancun
/// 2020/9/15 
///desc:

const int USER_INFO_EVENT_TYPE_1 = 1;
const int USER_INFO_EVENT_TYPE_2 = 2;

class UserInfoEvent extends EventObject {
  int type; //消息类型(1:个人信息修改,2:余额、权益、账号变动)
  UserInfoEvent({this.type, dynamic data}) : super(data: data);
}





