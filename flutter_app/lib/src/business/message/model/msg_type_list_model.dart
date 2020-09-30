import 'package:flutter_ienglish_fine/src/business/message/bean/msg_list.dart';
import 'package:flutter_ienglish_fine/src/business/message/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';

class MsgTypeListModel extends BaseModel {

  ///分类消息列表
  /// type  1:消息,2:公告
  Future<MsgList> getMessageTypeList(int pageNo, int type) async {
    return IEnglishNetClient().get(NET_GET_MESSAGETYPE, queryParameters: {
      'pageNo': pageNo,
      'pageSize': 10,
      'type': type
    }).then((value) {
      return add(MsgList.fromJson(value.data));
    });
  }

  ///消息列表全部已读
  /// type  1:消息,2:公告
  Future<NetBean> readMsgType(int type) async {
    return IEnglishNetClient()
        .post(NET_POST_READ, queryParameters: {'type': type}).then((value) {
      return add(NetBean.fromJson(value.data));
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
