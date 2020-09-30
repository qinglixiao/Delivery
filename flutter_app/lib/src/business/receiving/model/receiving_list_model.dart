import 'package:flutter_ienglish_fine/src/business/receiving/bean/receiving_list.dart';
import 'package:flutter_ienglish_fine/src/business/receiving/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';

class ReceivingListModel extends BaseModel {
  Future getReceivingList(String pageIndex) async {
    return IEnglishNetClient().get(NET_GET_RECEIVING_LIST,queryParameters: {"pageNo":pageIndex,'pageSize': '10'}).then((value) {
      return add(ReceivingListBean.fromJson(value.data));
    });
  }

  @override
  void dispose() {
    super.dispose();
    IEnglishNetClient().cancelRequestAll();
    // TODO: implement dispose
  }
}