import 'package:flutter_ienglish_fine/src/business/message/model/msg_detail_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

class MsgDetailViewModel extends BaseViewModel {
  MsgDetailModel _msgDetailModel = MsgDetailModel();

  Future readMsg(int messageReceiverId, Function(NetBean info) callback) async {
    return _msgDetailModel.readMsg(messageReceiverId).then((value) {
      callback(value);
    }).catchError((e) {
      error(e);
    });
  }

  @override
  void dispose() {
    super.dispose();
    _msgDetailModel?.close();
  }
}
