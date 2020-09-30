import 'package:flutter_ienglish_fine/src/business/invite/bean/collocation_activity_list.dart';
import 'package:flutter_ienglish_fine/src/business/invite/bean/collocation_list.dart';
import 'package:flutter_ienglish_fine/src/business/invite/model/invite_friend_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

///  created by：sunyuancun
/// 2020/9/8 20
///desc:邀请viewmodel

class InviteFriendViewModel extends BaseViewModel {
  InviteFriendViewModel() : super();

  InviteFriendModel _inviteFriendModel = InviteFriendModel();

  Stream<CollocationList> get streamCollocationList => _inviteFriendModel.getStream<CollocationList>();

  Stream<CollocationActivityList> get streamActivityCollocationList => _inviteFriendModel.getStream<CollocationActivityList>();

  Future loadInviteFriendData() async {
     loadCollocations();
     loadActivityCollocations();
  }

  Future loadCollocations() async {
    return _inviteFriendModel.getCollocations().then((value) {
    }).catchError((e) {
      error(e);
    });
  }

  Future loadActivityCollocations() async {
    return _inviteFriendModel.getActivityCollocations().then((value) {
    }).catchError((e) {
      error(e);
    });
  }

  @override
  void dispose() {
    super.dispose();
    _inviteFriendModel?.close();
  }
}
