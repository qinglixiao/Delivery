import 'package:flutter_ienglish_fine/src/business/invite/bean/collocation_activity_list.dart';
import 'package:flutter_ienglish_fine/src/business/invite/bean/collocation_list.dart';
import 'package:flutter_ienglish_fine/src/business/invite/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';

///  created by：sunyuancun
/// 2020/9/8 20
///desc:

class InviteFriendModel extends BaseModel {

  Future<CollocationList> getCollocations() async {
    return IEnglishNetClient().get(NET_GET_COLLOCATIONS,
        queryParameters: {'rightsName':'邀请显示权'}).then((value) {
      return add(CollocationList.fromJson(value.data));
    });
  }

  Future<CollocationActivityList> getActivityCollocations() async {
    return IEnglishNetClient().get(NET_GET_COLLOCATIONS_ACTIVITY,
        queryParameters: {'rightsName':'邀请显示权','activityType':1}).then((value) {
      return add(CollocationActivityList.fromJson(value.data));
    });
  }

  @override
  void dispose() {
    super.dispose();
    IEnglishNetClient().cancelRequestAll();
  }
}