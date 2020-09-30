import 'package:flutter_ienglish_fine/src/business/budget/view/good_confirm_page.dart';
import 'package:flutter_ienglish_fine/src/business/budget/view/good_detail_page.dart';
import 'package:flutter_ienglish_fine/src/business/budget/view/good_list_page.dart';
import 'package:flutter_ienglish_fine/src/business/dispatch/view/dispatch_detail_page.dart';
import 'package:flutter_ienglish_fine/src/business/dispatch/view/dispatch_list_page.dart';
import 'package:flutter_ienglish_fine/src/business/invite/view/invite_friend_page.dart';
import 'package:flutter_ienglish_fine/src/business/login/view/login_password_page.dart';
import 'package:flutter_ienglish_fine/src/business/login/view/login_sms_page.dart';
import 'package:flutter_ienglish_fine/src/business/message/view/msg_center_page.dart';
import 'package:flutter_ienglish_fine/src/business/message/view/msg_detail_page.dart';
import 'package:flutter_ienglish_fine/src/business/message/view/msg_type_list_page.dart';
import 'package:flutter_ienglish_fine/src/business/mine/view/my_buy/mine_buy_detail_page.dart';
import 'package:flutter_ienglish_fine/src/business/mine/view/my_buy/mine_buy_list_page.dart';
import 'package:flutter_ienglish_fine/src/business/mine/view/my_sell/mine_sell_list_page.dart';
import 'package:flutter_ienglish_fine/src/business/mine/view/place/place_detail_page.dart';
import 'package:flutter_ienglish_fine/src/business/mine/view/place/place_list_page.dart';
import 'package:flutter_ienglish_fine/src/business/mine/view/place/place_new_page.dart';
import 'package:flutter_ienglish_fine/src/business/mine/view/setting/forget_login_pwd_first_page.dart';
import 'package:flutter_ienglish_fine/src/business/mine/view/setting/forget_login_pwd_second_page.dart';
import 'package:flutter_ienglish_fine/src/business/mine/view/setting/set_login_pwd_manager_page.dart';
import 'package:flutter_ienglish_fine/src/business/mine/view/setting/set_login_pwd_modify_page.dart';
import 'package:flutter_ienglish_fine/src/business/mine/view/setting/set_pay_pwd_manager_page.dart';
import 'package:flutter_ienglish_fine/src/business/mine/view/setting/set_user_info_page.dart';
import 'package:flutter_ienglish_fine/src/business/mine/view/setting/setting_page.dart';
import 'package:flutter_ienglish_fine/src/business/pay/view/account/company_account_list.dart';
import 'package:flutter_ienglish_fine/src/business/pay/view/account/open_company_account.dart';
import 'package:flutter_ienglish_fine/src/business/pay/view/account/open_person_account.dart';
import 'package:flutter_ienglish_fine/src/business/pay/view/balance/balance_detail_page.dart';
import 'package:flutter_ienglish_fine/src/business/pay/view/balance/balance_page.dart';
import 'package:flutter_ienglish_fine/src/business/pay/view/earnings/earnings_page.dart';
import 'package:flutter_ienglish_fine/src/business/pay/view/pay_affirm_page.dart';
import 'package:flutter_ienglish_fine/src/business/pay/view/pay_affirm_success_page.dart';
import 'package:flutter_ienglish_fine/src/business/pay/view/pay_save_page.dart';
import 'package:flutter_ienglish_fine/src/business/pay/view/pay_status_page.dart';
import 'package:flutter_ienglish_fine/src/business/pay/view/pay_withdraw_page.dart';
import 'package:flutter_ienglish_fine/src/business/receiving/view/receiving_list_page.dart';
import 'package:flutter_ienglish_fine/src/business/receiving/view/receiving_detail_page.dart';
import 'package:flutter_ienglish_fine/src/business/replenish/view/replenish_detail_page.dart';
import 'package:flutter_ienglish_fine/src/business/replenish/view/replenish_list_page.dart';
import 'package:flutter_ienglish_fine/src/business/pay/view/account/bank_list_search_page.dart';
import 'package:flutter_ienglish_fine/src/main/index_tab.dart';
import 'package:flutter_lib/flutter_lib.dart';

///路由对应表
///create by lx
///date 2020/6/8

class RouterName {
  static const String good_list = "good_list_page"; //订货列表
  static const String good_detail = "good_detail";
  static const String widget_demo = "widget_demo";
  static const String login_sms = "login_sms_page";
  static const String login_password = "login_password_page";
  static const String index_page = "index_tab";
  static const String good_confirm_page = "good_confirm_page";
  static const String pay_affirm_page = "pay_affirm_page";
  static const String web_view = "web_view_page";
  static const String pay_success = "pay_affirm_success_page";
  static const String dispatch_list = "dispatch_list_page";
  static const String dispatch_detail = "dispatch_detail_page";
  static const String receiving_list = "receiving_list_page";
  static const String receiving_detail = "receiving_detail_page";
  static const String replenish_list = "replenish_list";
  static const String replenish_detail = "replenish_detail";
  static const String my_buy_list = "my_buy_list";
  static const String my_buy_Detail = "my_buy_Detail";
  static const String my_sell_List = "my_sell_List";
  static const String setting_page = "setting_page";
  static const String set_user_info_page = "set_user_info_page";
  static const String place_list = "place_list";
  static const String place_detail = "place_detail";
  static const String place_new = "place_new";
  static const String my_balance = "my_balance";
  static const String my_balance_detail = "my_balance_detail";
  static const String my_earnings = "my_earnings";
  static const String pay_save = "pay_save";
  static const String open_company_account = "open_company_account";
  static const String open_person_account = "open_person_account";
  static const String login_pwd_manager = "login_pwd_manager";
  static const String login_pwd_modify = "login_pwd_modify";
  static const String forget_login_pwd = "forget_login_pwd";
  static const String forget_login_pwd_change = "forget_login_pwd_change";
  static const String pay_withdraw = "pay_withdraw";
  static const String pay_status = "pay_status";
  static const String pay_pwd_manager = "pay_pwd_manager";
  static const String msg_center = "msg_center";
  static const String msg_type_list = "msg_type_list";
  static const String msg_detail = "msg_detail";
  static const String invite_friend= "invite_friend";
  static const String invite_detail= "invite_detail";
  static const String blan_list_search= "blan_list_search";
  static const String company_account_list= "company_account_list";


}

var routers = {
  RouterName.good_list: (context) => GoodListPage(),
  RouterName.good_detail: (context) => GoodDetailPage(),
  RouterName.login_sms: (context) => LoginSmsPage(),
  RouterName.login_password: (context) => LoginPasswordPage(),
  RouterName.index_page: (context) => IndexPage(),
  RouterName.good_confirm_page: (context) => GoodConfirmPage(),
  RouterName.pay_affirm_page: (context) => PayAffirmPage(),
  RouterName.web_view: (context) => IAgentWebPage(),
  RouterName.pay_success: (context) => PayAffirmSuccessPage(),
  RouterName.dispatch_list: (context) => DispatchListPage(),
  RouterName.dispatch_detail: (context) => DispathDeatilPage(),
  RouterName.receiving_list: (context) => ReceivingListPage(),
  RouterName.receiving_detail: (context) => ReceivingDeatilPage(),
  RouterName.replenish_list: (context) => ReplenishListPage(),
  RouterName.replenish_detail: (context) => ReplenishDetailPage(),
  RouterName.my_buy_list: (context) => MineBuyListPage(),
  RouterName.my_buy_Detail: (context) => MineBuyDeatilPage(),
  RouterName.my_sell_List: (context) => MineSellListPage(),
  RouterName.setting_page: (context) => SettingPage(),
  RouterName.set_user_info_page: (context) => SetUserInfoPage(),
  RouterName.place_list: (context) => PlaceListPage(),
  RouterName.place_detail: (context) => PlaceDetailPage(),
  RouterName.place_new: (context) => PlaceNewPage(),
  RouterName.my_balance: (context) => BalancePage(),
  RouterName.my_balance_detail: (context) => BalanceDetailPage(),
  RouterName.my_earnings: (context) => EarningsPage(),
  RouterName.pay_save: (context) => PaySavePage(),
  RouterName.open_company_account: (context) => OpenCompanyAccount(),
  RouterName.open_person_account: (context) => OpenPersonAccount(),
  RouterName.login_pwd_manager: (context) => LoginPwdManagerPage(),
  RouterName.login_pwd_modify:(context) => LoginPwdModifyPage(),
  RouterName.forget_login_pwd:(context) => ForgetLoginPwdFirstPage(),
  RouterName.forget_login_pwd_change:(context) => ForgetLoginPwdSecondPage(),
  RouterName.pay_withdraw:(context) => PayWithdrawPage(),
  RouterName.pay_status:(context) => PayStatusPage(),
  RouterName.pay_pwd_manager:(context) => PayPwdManagerPage(),
  RouterName.msg_center:(context) => MsgCenterPage(),
  RouterName.msg_type_list:(context) => MsgTypeListPage(),
  RouterName.msg_detail:(context) => MsgDetailPage(),
  RouterName.invite_friend:(context) => InviteFriendPage(),
  RouterName.blan_list_search:(context) => BankListSearchPage(),
  RouterName.company_account_list:(context) => CompanyAccountListPage(),

};
