import 'package:flutter_ienglish_fine/src/business/budget/bean/good_confirm.dart';
import 'package:flutter_ienglish_fine/src/business/budget/model/good_confirm_model.dart';
import 'package:flutter_ienglish_fine/src/business/message/bean/msg_status.dart';
import 'package:flutter_ienglish_fine/src/main/me/bean/me.dart';
import 'package:flutter_ienglish_fine/src/main/me/model/me_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

class MeViewModel extends BaseViewModel {
  MeViewModel() : super();

  GoodConfirmModel _goodConfirmModel = GoodConfirmModel();
  MeModel _meModel = MeModel();

  bool updateUserInfo = false;

  Stream<AccountCountBean> get streamAccountCount =>
      _goodConfirmModel.getStream<AccountCountBean>();
  Stream<FundQuery> get streamFundQuery =>
      _goodConfirmModel.getStream<FundQuery>();

  Stream<MySettleAccounts> get streamSettleAccounts =>
      _meModel.getStream<MySettleAccounts>();
  Stream<MyAccount> get streamAccount =>
      _meModel.getStream<MyAccount>();
  Stream<MsgStatus> get streamMessageStatus =>
      _meModel.getStream<MsgStatus>();

  Future getUserInfo() async{
    return Future.wait([loadAccountCountInfo(),loadFundQuery(),loadSettleAccounts(),loadAccount(),loadMessageStatus()]);
  }

  Future loadAccountCountInfo(){
    return _goodConfirmModel.getAccountCountInfo();
  }

  Future loadFundQuery() async {
    String code = SpUtil.getUserNumberDesc();
    return _goodConfirmModel.getFundQuery(code);
  }

  Future loadSettleAccounts() async {
    String code = SpUtil.getUserNumberDesc();
    return _meModel.getSettleAccounts(code);
  }

  Future loadAccount() async {
    String code = SpUtil.getUserNumberDesc();
    return _meModel.getAccount(code);
  }

  Future loadMessageStatus() async {
    String code = SpUtil.getUserId();
    return _meModel.getMessageStatus(code);
  }

  @override
  void dispose() {
    super.dispose();
    _goodConfirmModel?.close();
    _meModel?.close();
  }
}