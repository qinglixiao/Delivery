import 'package:flutter_ienglish_fine/src/business/message/bean/msg_status.dart';
import 'package:flutter_ienglish_fine/src/main/me/bean/me.dart';
import 'package:flutter_ienglish_fine/src/main/me/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';

class MeModel extends BaseModel {

  Future getSettleAccounts(String code) async {
    return IEnglishNetClient().get(NET_GET_ORDER_CLEARING_INFO,queryParameters: {'numberDesc': code}).then((value) {
      return add(MySettleAccounts.fromJson(value.data));
    });
  }

  Future getAccount(String code) async {
    return IEnglishNetClient().get(NET_GET_ACCOUNT,queryParameters: {'numberDesc': code}).then((value) {
      return add(MyAccount.fromJson(value.data));
    });
  }

  Future getMessageStatus(String code) async {
    return IEnglishNetClient().get(NET_GET_MESSAGE_STATUS,queryParameters: {'messageReceiverAllId': code}).then((value) {
      return add(MsgStatus.fromJson(value.data));
    });
  }

  @override
  void dispose() {
    // TODO: implement dispose
  }
}
