import 'package:flutter_ienglish_fine/src/business/message/bean/msg_list.dart';
import 'package:flutter_ienglish_fine/src/business/message/model/msg_type_list_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

class MsgTypeListViewModel extends BaseViewModel {
  MsgTypeListModel _msgTypeListModel = MsgTypeListModel();

  Stream<MsgList> get streamMsgList => _msgTypeListModel.getStream<MsgList>();

  int pageIndex = 1;
  bool hasRead = false;
  bool hasMoreList = false;

  Future loadMessageTypeList(int type,{bool isFirst = false,bool isRefresh = false}) async {
    if (isFirst) {
      showLoadding();
    }
    if (isRefresh) {
      pageIndex = 1;
    } else {
      pageIndex++;
    }
    return _msgTypeListModel.getMessageTypeList(pageIndex, type).then((value) {
      if (isRefresh && value?.items?.length == 0) {
        showEmptyView();
      } else {
        hideLoadding();
      }
      if (value?.items?.length == 0) {
        hasMoreList = false;
      } else {
        hasMoreList = true;
      }
    }).catchError((e) {
      error(e);
    });
  }

  Future readMsgType(int type, Function(NetBean info) callback) async {
    return _msgTypeListModel.readMsgType(type).then((value) {
      callback(value);
    }).catchError((e) {
      error(e);
    });
  }

  Future readMsg(int messageReceiverId, Function(NetBean info) callback) async {
    return _msgTypeListModel.readMsg(messageReceiverId).then((value) {
      callback(value);
    }).catchError((e) {
      error(e);
    });
  }

  @override
  void dispose() {
    super.dispose();
    _msgTypeListModel?.close();
  }
}
