import 'package:flutter_ienglish_fine/src/business/message/bean/msg_list.dart';
import 'package:flutter_ienglish_fine/src/business/message/model/msg_center_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

class MsgCenterViewModel extends BaseViewModel {
  MsgCenterModel _msgCenterModel = MsgCenterModel();
  bool hasRead = false;

  Stream<MsgList>  get streamMsgList =>
      _msgCenterModel.getStream<MsgList>();

  Future loadMessageCenterList({isFrist: bool}) async {
    if(isFrist){
      showLoadding();
    }
    return _msgCenterModel.getMessage().then((value) {
      if (value?.items?.length == 0) {
        showEmptyView();
      } else {
        hideLoadding();
      }
    }).catchError((e) {
      error(e);
    });
  }

  Future readMsg(int messageReceiverId, Function(NetBean info) callback) async {
    return _msgCenterModel.readMsg(messageReceiverId).then((value) {
      callback(value);
    }).catchError((e) {
      error(e);
    });
  }

  @override
  void dispose() {
    super.dispose();
    _msgCenterModel?.close();
  }
}
