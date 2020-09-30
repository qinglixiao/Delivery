import 'package:flutter_ienglish_fine/src/business/message/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';

class MsgDetailModel extends BaseModel {
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
