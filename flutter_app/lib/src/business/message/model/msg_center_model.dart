import 'package:flutter_ienglish_fine/src/business/message/bean/msg_list.dart';
import 'package:flutter_ienglish_fine/src/business/message/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';

class MsgCenterModel extends BaseModel {
  Future<MsgList> getMessage() async {
    return IEnglishNetClient().get(NET_GET_MESSAGE).then((value) {
      return add(MsgList.fromJson(value.data));
    });
  }

  Future<NetBean> readMsg(int messageReceiverId) async {
    return IEnglishNetClient().post(NET_POST_READ,queryParameters: {'messageReceiverId':messageReceiverId}).then((value) {
      return add(NetBean.fromJson(value.data));
    });
  }

  @override
  void dispose() {
    super.dispose();
    IEnglishNetClient().cancelRequestAll();
  }
}
