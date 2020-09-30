// GENERATED CODE - DO NOT MODIFY BY HAND
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'intl/messages_all.dart';

// **************************************************************************
// Generator: Flutter Intl IDE plugin
// Made by Localizely
// **************************************************************************

// ignore_for_file: non_constant_identifier_names, lines_longer_than_80_chars

class S {
  S();
  
  static S current;
  
  static const AppLocalizationDelegate delegate =
    AppLocalizationDelegate();

  static Future<S> load(Locale locale) {
    final name = (locale.countryCode?.isEmpty ?? false) ? locale.languageCode : locale.toString();
    final localeName = Intl.canonicalizedLocale(name); 
    return initializeMessages(localeName).then((_) {
      Intl.defaultLocale = localeName;
      S.current = S();
      
      return S.current;
    });
  } 

  static S of(BuildContext context) {
    return Localizations.of<S>(context, S);
  }

  /// `iEnglish`
  String get appName {
    return Intl.message(
      'iEnglish',
      name: 'appName',
      desc: '',
      args: [],
    );
  }

  /// `1.0.0`
  String get appCode {
    return Intl.message(
      '1.0.0',
      name: 'appCode',
      desc: '',
      args: [],
    );
  }

  /// `再按一次返回键退出`
  String get app_pop_title {
    return Intl.message(
      '再按一次返回键退出',
      name: 'app_pop_title',
      desc: '',
      args: [],
    );
  }

  /// `订货列表`
  String get order_good {
    return Intl.message(
      '订货列表',
      name: 'order_good',
      desc: '',
      args: [],
    );
  }

  /// `iEnglish服务商平台`
  String get home_title {
    return Intl.message(
      'iEnglish服务商平台',
      name: 'home_title',
      desc: '',
      args: [],
    );
  }

  /// `我的`
  String get tab_me {
    return Intl.message(
      '我的',
      name: 'tab_me',
      desc: '',
      args: [],
    );
  }

  /// `合伙人平台`
  String get tab_plateform {
    return Intl.message(
      '合伙人平台',
      name: 'tab_plateform',
      desc: '',
      args: [],
    );
  }

  /// `主页`
  String get tab_home {
    return Intl.message(
      '主页',
      name: 'tab_home',
      desc: '',
      args: [],
    );
  }

  /// `请输入手机号`
  String get login_phone_hint {
    return Intl.message(
      '请输入手机号',
      name: 'login_phone_hint',
      desc: '',
      args: [],
    );
  }

  /// `手机号`
  String get login_phone {
    return Intl.message(
      '手机号',
      name: 'login_phone',
      desc: '',
      args: [],
    );
  }

  /// `请输入验证码`
  String get login_sms_hint {
    return Intl.message(
      '请输入验证码',
      name: 'login_sms_hint',
      desc: '',
      args: [],
    );
  }

  /// `验证码`
  String get login_sms {
    return Intl.message(
      '验证码',
      name: 'login_sms',
      desc: '',
      args: [],
    );
  }

  /// `发送验证码`
  String get login_sms_btn {
    return Intl.message(
      '发送验证码',
      name: 'login_sms_btn',
      desc: '',
      args: [],
    );
  }

  /// `重新发送`
  String get login_sms_btn_again {
    return Intl.message(
      '重新发送',
      name: 'login_sms_btn_again',
      desc: '',
      args: [],
    );
  }

  /// `我已阅读并同意iEnglih服务商平台《`
  String get agreement_info {
    return Intl.message(
      '我已阅读并同意iEnglih服务商平台《',
      name: 'agreement_info',
      desc: '',
      args: [],
    );
  }

  /// `服务商管理平台经营规则（总则）`
  String get agreement_user {
    return Intl.message(
      '服务商管理平台经营规则（总则）',
      name: 'agreement_user',
      desc: '',
      args: [],
    );
  }

  /// `》、《`
  String get agreement_info_1 {
    return Intl.message(
      '》、《',
      name: 'agreement_info_1',
      desc: '',
      args: [],
    );
  }

  /// `服务商管理平台免责声明`
  String get agreement_privacy {
    return Intl.message(
      '服务商管理平台免责声明',
      name: 'agreement_privacy',
      desc: '',
      args: [],
    );
  }

  /// `》及《`
  String get agreement_info_2 {
    return Intl.message(
      '》及《',
      name: 'agreement_info_2',
      desc: '',
      args: [],
    );
  }

  /// `服务商管理平台隐私协议`
  String get agreement_liability {
    return Intl.message(
      '服务商管理平台隐私协议',
      name: 'agreement_liability',
      desc: '',
      args: [],
    );
  }

  /// `》`
  String get agreement_info_3 {
    return Intl.message(
      '》',
      name: 'agreement_info_3',
      desc: '',
      args: [],
    );
  }

  /// `账号密码登录`
  String get login_change {
    return Intl.message(
      '账号密码登录',
      name: 'login_change',
      desc: '',
      args: [],
    );
  }

  /// `登录`
  String get login_name {
    return Intl.message(
      '登录',
      name: 'login_name',
      desc: '',
      args: [],
    );
  }

  /// `请输入手机号!`
  String get sms_warn1 {
    return Intl.message(
      '请输入手机号!',
      name: 'sms_warn1',
      desc: '',
      args: [],
    );
  }

  /// `请输入正确的手机号!`
  String get sms_warn2 {
    return Intl.message(
      '请输入正确的手机号!',
      name: 'sms_warn2',
      desc: '',
      args: [],
    );
  }

  /// `请输入手机号或邮箱!`
  String get sms_warn3 {
    return Intl.message(
      '请输入手机号或邮箱!',
      name: 'sms_warn3',
      desc: '',
      args: [],
    );
  }

  /// `请输入正确的手机号或邮箱!`
  String get sms_warn4 {
    return Intl.message(
      '请输入正确的手机号或邮箱!',
      name: 'sms_warn4',
      desc: '',
      args: [],
    );
  }

  /// `手机验证码登录`
  String get login_change_sms {
    return Intl.message(
      '手机验证码登录',
      name: 'login_change_sms',
      desc: '',
      args: [],
    );
  }

  /// `忘记密码`
  String get login_forget_password {
    return Intl.message(
      '忘记密码',
      name: 'login_forget_password',
      desc: '',
      args: [],
    );
  }

  /// `请输入手机号/邮箱`
  String get login_username_hint {
    return Intl.message(
      '请输入手机号/邮箱',
      name: 'login_username_hint',
      desc: '',
      args: [],
    );
  }

  /// `手机号/邮箱`
  String get login_username {
    return Intl.message(
      '手机号/邮箱',
      name: 'login_username',
      desc: '',
      args: [],
    );
  }

  /// `请输入密码`
  String get login_password_hint {
    return Intl.message(
      '请输入密码',
      name: 'login_password_hint',
      desc: '',
      args: [],
    );
  }

  /// `密码`
  String get login_password {
    return Intl.message(
      '密码',
      name: 'login_password',
      desc: '',
      args: [],
    );
  }

  /// `您还不是服务商，需要邀请注册才可获得用户身份，快去找您身边的iEnglish服务商一起来做事业吧！`
  String get login_error_warn {
    return Intl.message(
      '您还不是服务商，需要邀请注册才可获得用户身份，快去找您身边的iEnglish服务商一起来做事业吧！',
      name: 'login_error_warn',
      desc: '',
      args: [],
    );
  }

  /// `验证码已发送`
  String get send_message_success {
    return Intl.message(
      '验证码已发送',
      name: 'send_message_success',
      desc: '',
      args: [],
    );
  }

  /// `验证码发送失败`
  String get send_message_fail {
    return Intl.message(
      '验证码发送失败',
      name: 'send_message_fail',
      desc: '',
      args: [],
    );
  }

  /// `需同意`
  String get need_agree_title {
    return Intl.message(
      '需同意',
      name: 'need_agree_title',
      desc: '',
      args: [],
    );
  }

  /// `总价`
  String get total_price {
    return Intl.message(
      '总价',
      name: 'total_price',
      desc: '',
      args: [],
    );
  }

  /// `去订货`
  String get go_order {
    return Intl.message(
      '去订货',
      name: 'go_order',
      desc: '',
      args: [],
    );
  }

  /// `提示`
  String get warn_title {
    return Intl.message(
      '提示',
      name: 'warn_title',
      desc: '',
      args: [],
    );
  }

  /// `我知道了`
  String get warn_button_title {
    return Intl.message(
      '我知道了',
      name: 'warn_button_title',
      desc: '',
      args: [],
    );
  }

  /// `订货协议`
  String get warn_protocol_title {
    return Intl.message(
      '订货协议',
      name: 'warn_protocol_title',
      desc: '',
      args: [],
    );
  }

  /// `请务必审慎阅读、充分理解协议内容，其中包含：`
  String get warn_protocol_contnet {
    return Intl.message(
      '请务必审慎阅读、充分理解协议内容，其中包含：',
      name: 'warn_protocol_contnet',
      desc: '',
      args: [],
    );
  }

  /// `《服务商管理平台订货及发货规则》`
  String get warn_protocol_1 {
    return Intl.message(
      '《服务商管理平台订货及发货规则》',
      name: 'warn_protocol_1',
      desc: '',
      args: [],
    );
  }

  /// `《服务商管理平台免责声明》`
  String get warn_protocol_2 {
    return Intl.message(
      '《服务商管理平台免责声明》',
      name: 'warn_protocol_2',
      desc: '',
      args: [],
    );
  }

  /// `《服务商管理平台特许服务协议》`
  String get warn_protocol_3 {
    return Intl.message(
      '《服务商管理平台特许服务协议》',
      name: 'warn_protocol_3',
      desc: '',
      args: [],
    );
  }

  /// `【其他注意事项】`
  String get warn_protocol_other_title {
    return Intl.message(
      '【其他注意事项】',
      name: 'warn_protocol_other_title',
      desc: '',
      args: [],
    );
  }

  /// `1、与您约定免除或限制责任的条款；\n2、与您约定法律适用和管理的条款；\n3、与您约定的订货和售后争议处理条款；\n4、其他以粗体、下划线或其他形式标识的重要条款`
  String get warn_protocol_other {
    return Intl.message(
      '1、与您约定免除或限制责任的条款；\n2、与您约定法律适用和管理的条款；\n3、与您约定的订货和售后争议处理条款；\n4、其他以粗体、下划线或其他形式标识的重要条款',
      name: 'warn_protocol_other',
      desc: '',
      args: [],
    );
  }

  /// `阅读协议的过程中，如果您不同意相关协议或其中的任何条款约定，您应立即停止订货，否则表示您已充分阅读、理解并接受协议的全部内容。`
  String get warn_protocol_stress {
    return Intl.message(
      '阅读协议的过程中，如果您不同意相关协议或其中的任何条款约定，您应立即停止订货，否则表示您已充分阅读、理解并接受协议的全部内容。',
      name: 'warn_protocol_stress',
      desc: '',
      args: [],
    );
  }

  /// `我已阅读并同意以上协议`
  String get warn_protocol_agree {
    return Intl.message(
      '我已阅读并同意以上协议',
      name: 'warn_protocol_agree',
      desc: '',
      args: [],
    );
  }

  /// `同意`
  String get warn_protocol_button {
    return Intl.message(
      '同意',
      name: 'warn_protocol_button',
      desc: '',
      args: [],
    );
  }

  /// `校董`
  String get service_provider_level_1 {
    return Intl.message(
      '校董',
      name: 'service_provider_level_1',
      desc: '',
      args: [],
    );
  }

  /// `会长`
  String get service_provider_level_2 {
    return Intl.message(
      '会长',
      name: 'service_provider_level_2',
      desc: '',
      args: [],
    );
  }

  /// `合伙人`
  String get service_provider_level_3 {
    return Intl.message(
      '合伙人',
      name: 'service_provider_level_3',
      desc: '',
      args: [],
    );
  }

  /// `超级会员`
  String get service_provider_level_4 {
    return Intl.message(
      '超级会员',
      name: 'service_provider_level_4',
      desc: '',
      args: [],
    );
  }

  /// `普通会员`
  String get service_provider_level_5 {
    return Intl.message(
      '普通会员',
      name: 'service_provider_level_5',
      desc: '',
      args: [],
    );
  }

  /// `起订量为`
  String get start_count_title {
    return Intl.message(
      '起订量为',
      name: 'start_count_title',
      desc: '',
      args: [],
    );
  }

  /// `确认订货`
  String get good_confirm_title {
    return Intl.message(
      '确认订货',
      name: 'good_confirm_title',
      desc: '',
      args: [],
    );
  }

  /// `再买`
  String get good_confirm_upgrade_buy_info1 {
    return Intl.message(
      '再买',
      name: 'good_confirm_upgrade_buy_info1',
      desc: '',
      args: [],
    );
  }

  /// `台可升级【`
  String get good_confirm_upgrade_buy_info2 {
    return Intl.message(
      '台可升级【',
      name: 'good_confirm_upgrade_buy_info2',
      desc: '',
      args: [],
    );
  }

  /// `】头衔`
  String get good_confirm_upgrade_buy_info3 {
    return Intl.message(
      '】头衔',
      name: 'good_confirm_upgrade_buy_info3',
      desc: '',
      args: [],
    );
  }

  /// `您已达到升级【`
  String get good_confirm_upgrade_up_info1 {
    return Intl.message(
      '您已达到升级【',
      name: 'good_confirm_upgrade_up_info1',
      desc: '',
      args: [],
    );
  }

  /// `】条件，需逐级升级【校董】`
  String get good_confirm_upgrade_up_info2 {
    return Intl.message(
      '】条件，需逐级升级【校董】',
      name: 'good_confirm_upgrade_up_info2',
      desc: '',
      args: [],
    );
  }

  /// `购买校董升级套组需同意`
  String get good_confirm_upgrade_info {
    return Intl.message(
      '购买校董升级套组需同意',
      name: 'good_confirm_upgrade_info',
      desc: '',
      args: [],
    );
  }

  /// `《服务商签约协议》`
  String get good_confirm_upgrade_protocol {
    return Intl.message(
      '《服务商签约协议》',
      name: 'good_confirm_upgrade_protocol',
      desc: '',
      args: [],
    );
  }

  /// `余额可订量`
  String get good_confirm_balance_facility {
    return Intl.message(
      '余额可订量',
      name: 'good_confirm_balance_facility',
      desc: '',
      args: [],
    );
  }

  /// `剩余可用余额`
  String get good_confirm_balance_money {
    return Intl.message(
      '剩余可用余额',
      name: 'good_confirm_balance_money',
      desc: '',
      args: [],
    );
  }

  /// `购买数量已达到升级`
  String get good_confirm_upgrade_title {
    return Intl.message(
      '购买数量已达到升级',
      name: 'good_confirm_upgrade_title',
      desc: '',
      args: [],
    );
  }

  /// `要求`
  String get good_confirm_upgrade_title_end {
    return Intl.message(
      '要求',
      name: 'good_confirm_upgrade_title_end',
      desc: '',
      args: [],
    );
  }

  /// `当前头衔订货`
  String get good_confirm_upgrade_choose_current {
    return Intl.message(
      '当前头衔订货',
      name: 'good_confirm_upgrade_choose_current',
      desc: '',
      args: [],
    );
  }

  /// `升级`
  String get good_confirm_upgrade_choose_up {
    return Intl.message(
      '升级',
      name: 'good_confirm_upgrade_choose_up',
      desc: '',
      args: [],
    );
  }

  /// `订货`
  String get good_confirm_upgrade_choose_up_end {
    return Intl.message(
      '订货',
      name: 'good_confirm_upgrade_choose_up_end',
      desc: '',
      args: [],
    );
  }

  /// `数量`
  String get good_confirm_num {
    return Intl.message(
      '数量',
      name: 'good_confirm_num',
      desc: '',
      args: [],
    );
  }

  /// `单价（最低起订量`
  String get good_confirm_price {
    return Intl.message(
      '单价（最低起订量',
      name: 'good_confirm_price',
      desc: '',
      args: [],
    );
  }

  /// `台/单）`
  String get good_confirm_price_end {
    return Intl.message(
      '台/单）',
      name: 'good_confirm_price_end',
      desc: '',
      args: [],
    );
  }

  /// `总计：`
  String get good_confirm_total {
    return Intl.message(
      '总计：',
      name: 'good_confirm_total',
      desc: '',
      args: [],
    );
  }

  /// `提交订单`
  String get good_confirm_submit {
    return Intl.message(
      '提交订单',
      name: 'good_confirm_submit',
      desc: '',
      args: [],
    );
  }

  /// `您已经达到升级【`
  String get good_confirm_update_warn_1 {
    return Intl.message(
      '您已经达到升级【',
      name: 'good_confirm_update_warn_1',
      desc: '',
      args: [],
    );
  }

  /// `】的条件，是否升级？（升级`
  String get good_confirm_update_warn_2 {
    return Intl.message(
      '】的条件，是否升级？（升级',
      name: 'good_confirm_update_warn_2',
      desc: '',
      args: [],
    );
  }

  /// `进货价更低）`
  String get good_confirm_update_warn_3 {
    return Intl.message(
      '进货价更低）',
      name: 'good_confirm_update_warn_3',
      desc: '',
      args: [],
    );
  }

  /// `不升级`
  String get good_confirm_update_button_1 {
    return Intl.message(
      '不升级',
      name: 'good_confirm_update_button_1',
      desc: '',
      args: [],
    );
  }

  /// `升级`
  String get good_confirm_update_button_2 {
    return Intl.message(
      '升级',
      name: 'good_confirm_update_button_2',
      desc: '',
      args: [],
    );
  }

  /// `确认不升级`
  String get good_confirm_currentLevel_warn_1 {
    return Intl.message(
      '确认不升级',
      name: 'good_confirm_currentLevel_warn_1',
      desc: '',
      args: [],
    );
  }

  /// `\n（升级`
  String get good_confirm_currentLevel_warn_2 {
    return Intl.message(
      '\n（升级',
      name: 'good_confirm_currentLevel_warn_2',
      desc: '',
      args: [],
    );
  }

  /// `进货价更低）`
  String get good_confirm_currentLevel_warn_3 {
    return Intl.message(
      '进货价更低）',
      name: 'good_confirm_currentLevel_warn_3',
      desc: '',
      args: [],
    );
  }

  /// `想一想`
  String get good_confirm_currentLevel_button_1 {
    return Intl.message(
      '想一想',
      name: 'good_confirm_currentLevel_button_1',
      desc: '',
      args: [],
    );
  }

  /// `确认`
  String get good_confirm_currentLevel_button_2 {
    return Intl.message(
      '确认',
      name: 'good_confirm_currentLevel_button_2',
      desc: '',
      args: [],
    );
  }

  /// `您当前帐户为个人账户，请去申请公司帐户！如有疑问联系运营督导`
  String get good_confirm_open_account {
    return Intl.message(
      '您当前帐户为个人账户，请去申请公司帐户！如有疑问联系运营督导',
      name: 'good_confirm_open_account',
      desc: '',
      args: [],
    );
  }

  /// `为了您资金交易安全，请先开户！`
  String get good_confirm_open_account_2 {
    return Intl.message(
      '为了您资金交易安全，请先开户！',
      name: 'good_confirm_open_account_2',
      desc: '',
      args: [],
    );
  }

  /// `继续支付`
  String get good_confirm_open_account_button_1 {
    return Intl.message(
      '继续支付',
      name: 'good_confirm_open_account_button_1',
      desc: '',
      args: [],
    );
  }

  /// `去开户`
  String get good_confirm_open_account_button_2 {
    return Intl.message(
      '去开户',
      name: 'good_confirm_open_account_button_2',
      desc: '',
      args: [],
    );
  }

  /// `开企业户`
  String get good_confirm_open_account_button_3 {
    return Intl.message(
      '开企业户',
      name: 'good_confirm_open_account_button_3',
      desc: '',
      args: [],
    );
  }

  /// `查看订单`
  String get good_confirm_exist_order_button_1 {
    return Intl.message(
      '查看订单',
      name: 'good_confirm_exist_order_button_1',
      desc: '',
      args: [],
    );
  }

  /// `继续支付`
  String get good_confirm_exist_order_button_2 {
    return Intl.message(
      '继续支付',
      name: 'good_confirm_exist_order_button_2',
      desc: '',
      args: [],
    );
  }

  /// `请选择收货地址`
  String get good_confirm_no_place {
    return Intl.message(
      '请选择收货地址',
      name: 'good_confirm_no_place',
      desc: '',
      args: [],
    );
  }

  /// `本次订单涨价`
  String get rise_in_price_title {
    return Intl.message(
      '本次订单涨价',
      name: 'rise_in_price_title',
      desc: '',
      args: [],
    );
  }

  /// `元`
  String get rise_in_price_until {
    return Intl.message(
      '元',
      name: 'rise_in_price_until',
      desc: '',
      args: [],
    );
  }

  /// `当前帐户为个人账户，下单每台设备涨`
  String get rise_in_price_warn1 {
    return Intl.message(
      '当前帐户为个人账户，下单每台设备涨',
      name: 'rise_in_price_warn1',
      desc: '',
      args: [],
    );
  }

  /// `元，开通企业户后可原价购买。`
  String get rise_in_price_warn2 {
    return Intl.message(
      '元，开通企业户后可原价购买。',
      name: 'rise_in_price_warn2',
      desc: '',
      args: [],
    );
  }

  /// `不涨价`
  String get rise_in_price_status1 {
    return Intl.message(
      '不涨价',
      name: 'rise_in_price_status1',
      desc: '',
      args: [],
    );
  }

  /// `涨价后`
  String get rise_in_price_status2 {
    return Intl.message(
      '涨价后',
      name: 'rise_in_price_status2',
      desc: '',
      args: [],
    );
  }

  /// `继续下单默认签署`
  String get rise_in_price_protocol1 {
    return Intl.message(
      '继续下单默认签署',
      name: 'rise_in_price_protocol1',
      desc: '',
      args: [],
    );
  }

  /// `《悦多丰委托授权协议》`
  String get rise_in_price_protocol2 {
    return Intl.message(
      '《悦多丰委托授权协议》',
      name: 'rise_in_price_protocol2',
      desc: '',
      args: [],
    );
  }

  /// `确定要离开收银台？`
  String get pay_affirm_warn_title {
    return Intl.message(
      '确定要离开收银台？',
      name: 'pay_affirm_warn_title',
      desc: '',
      args: [],
    );
  }

  /// `您的订单在30分钟内未支付将被取消，请尽快完成支付。`
  String get pay_affirm_warn_content {
    return Intl.message(
      '您的订单在30分钟内未支付将被取消，请尽快完成支付。',
      name: 'pay_affirm_warn_content',
      desc: '',
      args: [],
    );
  }

  /// `继续支付`
  String get pay_affirm_warn_cancel {
    return Intl.message(
      '继续支付',
      name: 'pay_affirm_warn_cancel',
      desc: '',
      args: [],
    );
  }

  /// `确认离开`
  String get pay_affirm_warn_sure {
    return Intl.message(
      '确认离开',
      name: 'pay_affirm_warn_sure',
      desc: '',
      args: [],
    );
  }

  /// `收银台`
  String get pay_affirm_title {
    return Intl.message(
      '收银台',
      name: 'pay_affirm_title',
      desc: '',
      args: [],
    );
  }

  /// `支付剩余时间`
  String get pay_affirm_countDown_title {
    return Intl.message(
      '支付剩余时间',
      name: 'pay_affirm_countDown_title',
      desc: '',
      args: [],
    );
  }

  /// `订单号:`
  String get pay_affirm_order_title {
    return Intl.message(
      '订单号:',
      name: 'pay_affirm_order_title',
      desc: '',
      args: [],
    );
  }

  /// `余额支付`
  String get pay_affirm_balance_title {
    return Intl.message(
      '余额支付',
      name: 'pay_affirm_balance_title',
      desc: '',
      args: [],
    );
  }

  /// `系统外支付`
  String get pay_affirm_offline_title {
    return Intl.message(
      '系统外支付',
      name: 'pay_affirm_offline_title',
      desc: '',
      args: [],
    );
  }

  /// `余额`
  String get pay_affirm_balance_title1 {
    return Intl.message(
      '余额',
      name: 'pay_affirm_balance_title1',
      desc: '',
      args: [],
    );
  }

  /// ` 提前转存，大额支付无限额！`
  String get pay_affirm_balance_warn {
    return Intl.message(
      ' 提前转存，大额支付无限额！',
      name: 'pay_affirm_balance_warn',
      desc: '',
      args: [],
    );
  }

  /// `余额不足，点击去充值`
  String get pay_affirm_insufficient_warn {
    return Intl.message(
      '余额不足，点击去充值',
      name: 'pay_affirm_insufficient_warn',
      desc: '',
      args: [],
    );
  }

  /// `去充值`
  String get pay_affirm_insufficient_button {
    return Intl.message(
      '去充值',
      name: 'pay_affirm_insufficient_button',
      desc: '',
      args: [],
    );
  }

  /// `确认支付`
  String get pay_affirm_button {
    return Intl.message(
      '确认支付',
      name: 'pay_affirm_button',
      desc: '',
      args: [],
    );
  }

  /// `下单成功`
  String get pay_affirm_success_title {
    return Intl.message(
      '下单成功',
      name: 'pay_affirm_success_title',
      desc: '',
      args: [],
    );
  }

  /// `恭喜您，已下单完成！`
  String get pay_affirm_success_content {
    return Intl.message(
      '恭喜您，已下单完成！',
      name: 'pay_affirm_success_content',
      desc: '',
      args: [],
    );
  }

  /// `支付失败,请稍后再试！`
  String get pay_affirm_error_content {
    return Intl.message(
      '支付失败,请稍后再试！',
      name: 'pay_affirm_error_content',
      desc: '',
      args: [],
    );
  }

  /// `返回首页`
  String get pay_affirm_success_button1 {
    return Intl.message(
      '返回首页',
      name: 'pay_affirm_success_button1',
      desc: '',
      args: [],
    );
  }

  /// `查看订货单`
  String get pay_affirm_success_button2 {
    return Intl.message(
      '查看订货单',
      name: 'pay_affirm_success_button2',
      desc: '',
      args: [],
    );
  }

  /// `发货列表`
  String get dispatch_list_title {
    return Intl.message(
      '发货列表',
      name: 'dispatch_list_title',
      desc: '',
      args: [],
    );
  }

  /// `下级服务商：`
  String get dispatch_list_next_name {
    return Intl.message(
      '下级服务商：',
      name: 'dispatch_list_next_name',
      desc: '',
      args: [],
    );
  }

  /// `应付总额：`
  String get dispatch_list_total {
    return Intl.message(
      '应付总额：',
      name: 'dispatch_list_total',
      desc: '',
      args: [],
    );
  }

  /// `拒绝发货`
  String get dispatch_list_reject {
    return Intl.message(
      '拒绝发货',
      name: 'dispatch_list_reject',
      desc: '',
      args: [],
    );
  }

  /// `发货`
  String get dispatch_list_sure {
    return Intl.message(
      '发货',
      name: 'dispatch_list_sure',
      desc: '',
      args: [],
    );
  }

  /// `发货成功`
  String get dispatch_success {
    return Intl.message(
      '发货成功',
      name: 'dispatch_success',
      desc: '',
      args: [],
    );
  }

  /// `供货成本过高`
  String get dispatch_list_cost_high {
    return Intl.message(
      '供货成本过高',
      name: 'dispatch_list_cost_high',
      desc: '',
      args: [],
    );
  }

  /// `冻结账号:`
  String get dispatch_list_freeze_account {
    return Intl.message(
      '冻结账号:',
      name: 'dispatch_list_freeze_account',
      desc: '',
      args: [],
    );
  }

  /// `可分配账号:`
  String get dispatch_list_usable_account {
    return Intl.message(
      '可分配账号:',
      name: 'dispatch_list_usable_account',
      desc: '',
      args: [],
    );
  }

  /// `账号说明`
  String get dispatch_list_account_explain {
    return Intl.message(
      '账号说明',
      name: 'dispatch_list_account_explain',
      desc: '',
      args: [],
    );
  }

  /// `拒绝后整单流转给下一个供货人\n确认拒绝？`
  String get dispatch_list_reject_content {
    return Intl.message(
      '拒绝后整单流转给下一个供货人\n确认拒绝？',
      name: 'dispatch_list_reject_content',
      desc: '',
      args: [],
    );
  }

  /// `确认`
  String get dispatch_list_reject_sure {
    return Intl.message(
      '确认',
      name: 'dispatch_list_reject_sure',
      desc: '',
      args: [],
    );
  }

  /// `再想想`
  String get dispatch_list_reject_cancel {
    return Intl.message(
      '再想想',
      name: 'dispatch_list_reject_cancel',
      desc: '',
      args: [],
    );
  }

  /// `发货详情`
  String get dispatch_detail_title {
    return Intl.message(
      '发货详情',
      name: 'dispatch_detail_title',
      desc: '',
      args: [],
    );
  }

  /// `待发货`
  String get dispatch_detail_status {
    return Intl.message(
      '待发货',
      name: 'dispatch_detail_status',
      desc: '',
      args: [],
    );
  }

  /// `共`
  String get dispatch_detail_count_title1 {
    return Intl.message(
      '共',
      name: 'dispatch_detail_count_title1',
      desc: '',
      args: [],
    );
  }

  /// `件商品`
  String get dispatch_detail_count_title2 {
    return Intl.message(
      '件商品',
      name: 'dispatch_detail_count_title2',
      desc: '',
      args: [],
    );
  }

  /// `订货总价：`
  String get dispatch_detail_total_price {
    return Intl.message(
      '订货总价：',
      name: 'dispatch_detail_total_price',
      desc: '',
      args: [],
    );
  }

  /// `未发货`
  String get dispatch_detail_shipping_status1 {
    return Intl.message(
      '未发货',
      name: 'dispatch_detail_shipping_status1',
      desc: '',
      args: [],
    );
  }

  /// `已发货`
  String get dispatch_detail_shipping_status2 {
    return Intl.message(
      '已发货',
      name: 'dispatch_detail_shipping_status2',
      desc: '',
      args: [],
    );
  }

  /// `总数量`
  String get dispatch_detail_shipping_status3 {
    return Intl.message(
      '总数量',
      name: 'dispatch_detail_shipping_status3',
      desc: '',
      args: [],
    );
  }

  /// `/台`
  String get dispatch_detail_price_unit {
    return Intl.message(
      '/台',
      name: 'dispatch_detail_price_unit',
      desc: '',
      args: [],
    );
  }

  /// `物流发货`
  String get dispatch_detail_express {
    return Intl.message(
      '物流发货',
      name: 'dispatch_detail_express',
      desc: '',
      args: [],
    );
  }

  /// `下级自提`
  String get dispatch_detail_take_their {
    return Intl.message(
      '下级自提',
      name: 'dispatch_detail_take_their',
      desc: '',
      args: [],
    );
  }

  /// `物流公司:`
  String get dispatch_detail_express_title {
    return Intl.message(
      '物流公司:',
      name: 'dispatch_detail_express_title',
      desc: '',
      args: [],
    );
  }

  /// `请输入物流公司`
  String get dispatch_detail_express_hint_title {
    return Intl.message(
      '请输入物流公司',
      name: 'dispatch_detail_express_hint_title',
      desc: '',
      args: [],
    );
  }

  /// `物流单号:`
  String get dispatch_detail_express_num {
    return Intl.message(
      '物流单号:',
      name: 'dispatch_detail_express_num',
      desc: '',
      args: [],
    );
  }

  /// `请输入物流单号`
  String get dispatch_detail_express_hint_num {
    return Intl.message(
      '请输入物流单号',
      name: 'dispatch_detail_express_hint_num',
      desc: '',
      args: [],
    );
  }

  /// `发货数量`
  String get dispatch_detail_express_count {
    return Intl.message(
      '发货数量',
      name: 'dispatch_detail_express_count',
      desc: '',
      args: [],
    );
  }

  /// `确认发货`
  String get dispatch_detail_submit_button {
    return Intl.message(
      '确认发货',
      name: 'dispatch_detail_submit_button',
      desc: '',
      args: [],
    );
  }

  /// `收货人：`
  String get dispatch_detail_express_name {
    return Intl.message(
      '收货人：',
      name: 'dispatch_detail_express_name',
      desc: '',
      args: [],
    );
  }

  /// `收货人手机号：`
  String get dispatch_detail_express_tel {
    return Intl.message(
      '收货人手机号：',
      name: 'dispatch_detail_express_tel',
      desc: '',
      args: [],
    );
  }

  /// `发货时间：`
  String get dispatch_detail_express_time {
    return Intl.message(
      '发货时间：',
      name: 'dispatch_detail_express_time',
      desc: '',
      args: [],
    );
  }

  /// `查看完整物流信息`
  String get dispatch_detail_express_detail {
    return Intl.message(
      '查看完整物流信息',
      name: 'dispatch_detail_express_detail',
      desc: '',
      args: [],
    );
  }

  /// `自提`
  String get dispatch_detail_express_selftaking {
    return Intl.message(
      '自提',
      name: 'dispatch_detail_express_selftaking',
      desc: '',
      args: [],
    );
  }

  /// `- 物流记录 -`
  String get dispatch_detail_express_info {
    return Intl.message(
      '- 物流记录 -',
      name: 'dispatch_detail_express_info',
      desc: '',
      args: [],
    );
  }

  /// `订单号：`
  String get dispatch_detail_order_num {
    return Intl.message(
      '订单号：',
      name: 'dispatch_detail_order_num',
      desc: '',
      args: [],
    );
  }

  /// `下单人：`
  String get dispatch_detail_order_person {
    return Intl.message(
      '下单人：',
      name: 'dispatch_detail_order_person',
      desc: '',
      args: [],
    );
  }

  /// `下单时间：`
  String get dispatch_detail_order_time {
    return Intl.message(
      '下单时间：',
      name: 'dispatch_detail_order_time',
      desc: '',
      args: [],
    );
  }

  /// `收起`
  String get pack_up {
    return Intl.message(
      '收起',
      name: 'pack_up',
      desc: '',
      args: [],
    );
  }

  /// `商品详情`
  String get good_detail {
    return Intl.message(
      '商品详情',
      name: 'good_detail',
      desc: '',
      args: [],
    );
  }

  /// `立即购买`
  String get good_btn_buy {
    return Intl.message(
      '立即购买',
      name: 'good_btn_buy',
      desc: '',
      args: [],
    );
  }

  /// `订货总价 ¥{price}`
  String good_total_price(Object price) {
    return Intl.message(
      '订货总价 ¥$price',
      name: 'good_total_price',
      desc: '',
      args: [price],
    );
  }

  /// `¥`
  String get RNB {
    return Intl.message(
      '¥',
      name: 'RNB',
      desc: '',
      args: [],
    );
  }

  /// `输入正确数量`
  String get dispatch_count_warn {
    return Intl.message(
      '输入正确数量',
      name: 'dispatch_count_warn',
      desc: '',
      args: [],
    );
  }

  /// `发货单确认`
  String get dispatch_detail_selftaking_title {
    return Intl.message(
      '发货单确认',
      name: 'dispatch_detail_selftaking_title',
      desc: '',
      args: [],
    );
  }

  /// `发货数量：`
  String get dispatch_detail_selftaking_content1 {
    return Intl.message(
      '发货数量：',
      name: 'dispatch_detail_selftaking_content1',
      desc: '',
      args: [],
    );
  }

  /// `\n发货类型：下级自提`
  String get dispatch_detail_selftaking_content2 {
    return Intl.message(
      '\n发货类型：下级自提',
      name: 'dispatch_detail_selftaking_content2',
      desc: '',
      args: [],
    );
  }

  /// `发货人：`
  String get dispatch_detail_express_content1 {
    return Intl.message(
      '发货人：',
      name: 'dispatch_detail_express_content1',
      desc: '',
      args: [],
    );
  }

  /// `收货人手机号：`
  String get dispatch_detail_express_content2 {
    return Intl.message(
      '收货人手机号：',
      name: 'dispatch_detail_express_content2',
      desc: '',
      args: [],
    );
  }

  /// `收货地址：`
  String get dispatch_detail_express_content3 {
    return Intl.message(
      '收货地址：',
      name: 'dispatch_detail_express_content3',
      desc: '',
      args: [],
    );
  }

  /// `发货类型：`
  String get dispatch_detail_express_content4 {
    return Intl.message(
      '发货类型：',
      name: 'dispatch_detail_express_content4',
      desc: '',
      args: [],
    );
  }

  /// `物流发货`
  String get dispatch_detail_express_content_value {
    return Intl.message(
      '物流发货',
      name: 'dispatch_detail_express_content_value',
      desc: '',
      args: [],
    );
  }

  /// `物流公司：`
  String get dispatch_detail_express_content5 {
    return Intl.message(
      '物流公司：',
      name: 'dispatch_detail_express_content5',
      desc: '',
      args: [],
    );
  }

  /// `物流单号：`
  String get dispatch_detail_express_content6 {
    return Intl.message(
      '物流单号：',
      name: 'dispatch_detail_express_content6',
      desc: '',
      args: [],
    );
  }

  /// `收货列表`
  String get receiving_list_title {
    return Intl.message(
      '收货列表',
      name: 'receiving_list_title',
      desc: '',
      args: [],
    );
  }

  /// `供货人：`
  String get receiving_list_sender {
    return Intl.message(
      '供货人：',
      name: 'receiving_list_sender',
      desc: '',
      args: [],
    );
  }

  /// `合计：`
  String get receiving_list_total {
    return Intl.message(
      '合计：',
      name: 'receiving_list_total',
      desc: '',
      args: [],
    );
  }

  /// `确认收货`
  String get receiving_list_submit_button {
    return Intl.message(
      '确认收货',
      name: 'receiving_list_submit_button',
      desc: '',
      args: [],
    );
  }

  /// `未收货`
  String get receiving_detail_shipping_status1 {
    return Intl.message(
      '未收货',
      name: 'receiving_detail_shipping_status1',
      desc: '',
      args: [],
    );
  }

  /// `已收货`
  String get receiving_detail_shipping_status2 {
    return Intl.message(
      '已收货',
      name: 'receiving_detail_shipping_status2',
      desc: '',
      args: [],
    );
  }

  /// `发货方式`
  String get receiving_detail_shipping_type {
    return Intl.message(
      '发货方式',
      name: 'receiving_detail_shipping_type',
      desc: '',
      args: [],
    );
  }

  /// `发货提醒`
  String get receiving_detail_warn_title {
    return Intl.message(
      '发货提醒',
      name: 'receiving_detail_warn_title',
      desc: '',
      args: [],
    );
  }

  /// `收货`
  String get receiving_detail_warn_sure {
    return Intl.message(
      '收货',
      name: 'receiving_detail_warn_sure',
      desc: '',
      args: [],
    );
  }

  /// `补单列表`
  String get replenish_list_title {
    return Intl.message(
      '补单列表',
      name: 'replenish_list_title',
      desc: '',
      args: [],
    );
  }

  /// `订货人`
  String get replenish_list_orderer {
    return Intl.message(
      '订货人',
      name: 'replenish_list_orderer',
      desc: '',
      args: [],
    );
  }

  /// `补货`
  String get replenish_list_item_button {
    return Intl.message(
      '补货',
      name: 'replenish_list_item_button',
      desc: '',
      args: [],
    );
  }

  /// `待补货倒计时：`
  String get replenish_list_count_down {
    return Intl.message(
      '待补货倒计时：',
      name: 'replenish_list_count_down',
      desc: '',
      args: [],
    );
  }

  /// `全选`
  String get replenish_list_select_all {
    return Intl.message(
      '全选',
      name: 'replenish_list_select_all',
      desc: '',
      args: [],
    );
  }

  /// `批量补单({count})`
  String replenish_list_select_all_button(Object count) {
    return Intl.message(
      '批量补单($count)',
      name: 'replenish_list_select_all_button',
      desc: '',
      args: [count],
    );
  }

  /// `补单详情`
  String get replenish_detail_title {
    return Intl.message(
      '补单详情',
      name: 'replenish_detail_title',
      desc: '',
      args: [],
    );
  }

  /// `共({count})件商品`
  String replenish_detail_count_info(Object count) {
    return Intl.message(
      '共($count)件商品',
      name: 'replenish_detail_count_info',
      desc: '',
      args: [count],
    );
  }

  /// `确认拒绝补货吗？`
  String get replenish_detail_reject_warn {
    return Intl.message(
      '确认拒绝补货吗？',
      name: 'replenish_detail_reject_warn',
      desc: '',
      args: [],
    );
  }

  /// `供货成本较高，确认供货吗？`
  String get replenish_detail_costhigh_warn {
    return Intl.message(
      '供货成本较高，确认供货吗？',
      name: 'replenish_detail_costhigh_warn',
      desc: '',
      args: [],
    );
  }

  /// `拒接补货`
  String get replenish_detail_reject_button {
    return Intl.message(
      '拒接补货',
      name: 'replenish_detail_reject_button',
      desc: '',
      args: [],
    );
  }

  /// `去补货`
  String get replenish_detail_replenish_button {
    return Intl.message(
      '去补货',
      name: 'replenish_detail_replenish_button',
      desc: '',
      args: [],
    );
  }

  /// `后结束`
  String get replenish_detail_countdown_title {
    return Intl.message(
      '后结束',
      name: 'replenish_detail_countdown_title',
      desc: '',
      args: [],
    );
  }

  /// `请选择需要补货的订单！`
  String get replenish_dialog_no_select {
    return Intl.message(
      '请选择需要补货的订单！',
      name: 'replenish_dialog_no_select',
      desc: '',
      args: [],
    );
  }

  /// `批量补单后不可拒绝补货！`
  String get replenish_dialog_content {
    return Intl.message(
      '批量补单后不可拒绝补货！',
      name: 'replenish_dialog_content',
      desc: '',
      args: [],
    );
  }

  /// `我已阅读并同意`
  String get replenish_dialog_protocol_title {
    return Intl.message(
      '我已阅读并同意',
      name: 'replenish_dialog_protocol_title',
      desc: '',
      args: [],
    );
  }

  /// `《订货规则》`
  String get replenish_dialog_protocol1 {
    return Intl.message(
      '《订货规则》',
      name: 'replenish_dialog_protocol1',
      desc: '',
      args: [],
    );
  }

  /// `《经销商退货退款规则》`
  String get replenish_dialog_protocol2 {
    return Intl.message(
      '《经销商退货退款规则》',
      name: 'replenish_dialog_protocol2',
      desc: '',
      args: [],
    );
  }

  /// `您确认补货吗?`
  String get replenish_dialog_make_sure {
    return Intl.message(
      '您确认补货吗?',
      name: 'replenish_dialog_make_sure',
      desc: '',
      args: [],
    );
  }

  /// `补单说明`
  String get replenish_list_explain {
    return Intl.message(
      '补单说明',
      name: 'replenish_list_explain',
      desc: '',
      args: [],
    );
  }

  /// `拒绝成功！`
  String get replenish_list_reject_success {
    return Intl.message(
      '拒绝成功！',
      name: 'replenish_list_reject_success',
      desc: '',
      args: [],
    );
  }

  /// `订货详情`
  String get order_goods_title {
    return Intl.message(
      '订货详情',
      name: 'order_goods_title',
      desc: '',
      args: [],
    );
  }

  /// `账户中还有资金请提现后再开企业户`
  String get open_business_account_warn {
    return Intl.message(
      '账户中还有资金请提现后再开企业户',
      name: 'open_business_account_warn',
      desc: '',
      args: [],
    );
  }

  /// `取消`
  String get warn_cancel {
    return Intl.message(
      '取消',
      name: 'warn_cancel',
      desc: '',
      args: [],
    );
  }

  /// `确定`
  String get warn_sure {
    return Intl.message(
      '确定',
      name: 'warn_sure',
      desc: '',
      args: [],
    );
  }

  /// `操作失败，稍后尝试！`
  String get warn_fail {
    return Intl.message(
      '操作失败，稍后尝试！',
      name: 'warn_fail',
      desc: '',
      args: [],
    );
  }

  /// `去付款`
  String get go_pay {
    return Intl.message(
      '去付款',
      name: 'go_pay',
      desc: '',
      args: [],
    );
  }

  /// `确认收款`
  String get confirm_receipt {
    return Intl.message(
      '确认收款',
      name: 'confirm_receipt',
      desc: '',
      args: [],
    );
  }

  /// `确认收到订单货款`
  String get confirm_receipt_warn_title {
    return Intl.message(
      '确认收到订单货款',
      name: 'confirm_receipt_warn_title',
      desc: '',
      args: [],
    );
  }

  /// `取消订单`
  String get cancel_order {
    return Intl.message(
      '取消订单',
      name: 'cancel_order',
      desc: '',
      args: [],
    );
  }

  /// `查看详情`
  String get order_info {
    return Intl.message(
      '查看详情',
      name: 'order_info',
      desc: '',
      args: [],
    );
  }

  /// `剩余时间`
  String get countDown_title {
    return Intl.message(
      '剩余时间',
      name: 'countDown_title',
      desc: '',
      args: [],
    );
  }

  /// `请选择取消订单原因`
  String get cancel_warn_content {
    return Intl.message(
      '请选择取消订单原因',
      name: 'cancel_warn_content',
      desc: '',
      args: [],
    );
  }

  /// `我不想买了`
  String get cancel_reason_1 {
    return Intl.message(
      '我不想买了',
      name: 'cancel_reason_1',
      desc: '',
      args: [],
    );
  }

  /// `信息填写错误`
  String get cancel_reason_2 {
    return Intl.message(
      '信息填写错误',
      name: 'cancel_reason_2',
      desc: '',
      args: [],
    );
  }

  /// `其他原因`
  String get cancel_reason_3 {
    return Intl.message(
      '其他原因',
      name: 'cancel_reason_3',
      desc: '',
      args: [],
    );
  }

  /// `暂不取消`
  String get cancel_title {
    return Intl.message(
      '暂不取消',
      name: 'cancel_title',
      desc: '',
      args: [],
    );
  }

  /// `确认取消`
  String get cancel_make_sure_title {
    return Intl.message(
      '确认取消',
      name: 'cancel_make_sure_title',
      desc: '',
      args: [],
    );
  }

  /// `待收款`
  String get order_status_1 {
    return Intl.message(
      '待收款',
      name: 'order_status_1',
      desc: '',
      args: [],
    );
  }

  /// `待发货`
  String get order_status_2 {
    return Intl.message(
      '待发货',
      name: 'order_status_2',
      desc: '',
      args: [],
    );
  }

  /// `已完成`
  String get order_status_3 {
    return Intl.message(
      '已完成',
      name: 'order_status_3',
      desc: '',
      args: [],
    );
  }

  /// `待付款`
  String get order_status_4 {
    return Intl.message(
      '待付款',
      name: 'order_status_4',
      desc: '',
      args: [],
    );
  }

  /// `待收货`
  String get order_status_5 {
    return Intl.message(
      '待收货',
      name: 'order_status_5',
      desc: '',
      args: [],
    );
  }

  /// `已取消`
  String get order_status_6 {
    return Intl.message(
      '已取消',
      name: 'order_status_6',
      desc: '',
      args: [],
    );
  }

  /// `全部`
  String get order_status_7 {
    return Intl.message(
      '全部',
      name: 'order_status_7',
      desc: '',
      args: [],
    );
  }

  /// `我买的`
  String get my_buy_order {
    return Intl.message(
      '我买的',
      name: 'my_buy_order',
      desc: '',
      args: [],
    );
  }

  /// `我卖的`
  String get my_sell_order {
    return Intl.message(
      '我卖的',
      name: 'my_sell_order',
      desc: '',
      args: [],
    );
  }

  /// `我的好友`
  String get my_friend {
    return Intl.message(
      '我的好友',
      name: 'my_friend',
      desc: '',
      args: [],
    );
  }

  /// `数据中心`
  String get my_data_center {
    return Intl.message(
      '数据中心',
      name: 'my_data_center',
      desc: '',
      args: [],
    );
  }

  /// `小商城`
  String get my_store {
    return Intl.message(
      '小商城',
      name: 'my_store',
      desc: '',
      args: [],
    );
  }

  /// `企业开户`
  String get my_firm {
    return Intl.message(
      '企业开户',
      name: 'my_firm',
      desc: '',
      args: [],
    );
  }

  /// `消息中心`
  String get my_message {
    return Intl.message(
      '消息中心',
      name: 'my_message',
      desc: '',
      args: [],
    );
  }

  /// `我的助理`
  String get my_assistant {
    return Intl.message(
      '我的助理',
      name: 'my_assistant',
      desc: '',
      args: [],
    );
  }

  /// `设置`
  String get my_setting {
    return Intl.message(
      '设置',
      name: 'my_setting',
      desc: '',
      args: [],
    );
  }

  /// `客服中心`
  String get my_service {
    return Intl.message(
      '客服中心',
      name: 'my_service',
      desc: '',
      args: [],
    );
  }

  /// `收益`
  String get my_earnings {
    return Intl.message(
      '收益',
      name: 'my_earnings',
      desc: '',
      args: [],
    );
  }

  /// `余额`
  String get my_balance {
    return Intl.message(
      '余额',
      name: 'my_balance',
      desc: '',
      args: [],
    );
  }

  /// `账号`
  String get my_account {
    return Intl.message(
      '账号',
      name: 'my_account',
      desc: '',
      args: [],
    );
  }

  /// `订单中心`
  String get my_order_info {
    return Intl.message(
      '订单中心',
      name: 'my_order_info',
      desc: '',
      args: [],
    );
  }

  /// `工具与服务`
  String get my_tool_info {
    return Intl.message(
      '工具与服务',
      name: 'my_tool_info',
      desc: '',
      args: [],
    );
  }

  /// `个人资料`
  String get user_info_title {
    return Intl.message(
      '个人资料',
      name: 'user_info_title',
      desc: '',
      args: [],
    );
  }

  /// `登录密码管理`
  String get change_login_password {
    return Intl.message(
      '登录密码管理',
      name: 'change_login_password',
      desc: '',
      args: [],
    );
  }

  /// `交易密码管理`
  String get change_deal_password {
    return Intl.message(
      '交易密码管理',
      name: 'change_deal_password',
      desc: '',
      args: [],
    );
  }

  /// `修改登录密码`
  String get login_pwd_modify {
    return Intl.message(
      '修改登录密码',
      name: 'login_pwd_modify',
      desc: '',
      args: [],
    );
  }

  /// `忘记登录密码`
  String get forget_login_pwd {
    return Intl.message(
      '忘记登录密码',
      name: 'forget_login_pwd',
      desc: '',
      args: [],
    );
  }

  /// `修改交易密码`
  String get pay_pwd_modify {
    return Intl.message(
      '修改交易密码',
      name: 'pay_pwd_modify',
      desc: '',
      args: [],
    );
  }

  /// `重置交易密码`
  String get reset_pay_pwd {
    return Intl.message(
      '重置交易密码',
      name: 'reset_pay_pwd',
      desc: '',
      args: [],
    );
  }

  /// `修改交易密码成功`
  String get pay_pwd_modify_success {
    return Intl.message(
      '修改交易密码成功',
      name: 'pay_pwd_modify_success',
      desc: '',
      args: [],
    );
  }

  /// `修改交易密码失败`
  String get pay_pwd_modify_error {
    return Intl.message(
      '修改交易密码失败',
      name: 'pay_pwd_modify_error',
      desc: '',
      args: [],
    );
  }

  /// `重置交易密码成功`
  String get reset_pay_pwd_success {
    return Intl.message(
      '重置交易密码成功',
      name: 'reset_pay_pwd_success',
      desc: '',
      args: [],
    );
  }

  /// `重置交易密码失败`
  String get reset_pay_pwd_error {
    return Intl.message(
      '重置交易密码失败',
      name: 'reset_pay_pwd_error',
      desc: '',
      args: [],
    );
  }

  /// `请输入原密码、如有字母请注意大小写`
  String get old_pwd_hint_text {
    return Intl.message(
      '请输入原密码、如有字母请注意大小写',
      name: 'old_pwd_hint_text',
      desc: '',
      args: [],
    );
  }

  /// `请输入新密码`
  String get new_pwd_hint_text {
    return Intl.message(
      '请输入新密码',
      name: 'new_pwd_hint_text',
      desc: '',
      args: [],
    );
  }

  /// `请再次输入新密码`
  String get new_pwd_again_hint_text {
    return Intl.message(
      '请再次输入新密码',
      name: 'new_pwd_again_hint_text',
      desc: '',
      args: [],
    );
  }

  /// `再次确认`
  String get pwd_again_confirm {
    return Intl.message(
      '再次确认',
      name: 'pwd_again_confirm',
      desc: '',
      args: [],
    );
  }

  /// `两次密码不一致！`
  String get pwd_no_eques {
    return Intl.message(
      '两次密码不一致！',
      name: 'pwd_no_eques',
      desc: '',
      args: [],
    );
  }

  /// `密码长度至少为6位`
  String get pwd_length_limit {
    return Intl.message(
      '密码长度至少为6位',
      name: 'pwd_length_limit',
      desc: '',
      args: [],
    );
  }

  /// `密码不能为空！`
  String get pwd_no_empty {
    return Intl.message(
      '密码不能为空！',
      name: 'pwd_no_empty',
      desc: '',
      args: [],
    );
  }

  /// `修改成功`
  String get pwd_modify_sucess {
    return Intl.message(
      '修改成功',
      name: 'pwd_modify_sucess',
      desc: '',
      args: [],
    );
  }

  /// `修改失败`
  String get pwd_modify_fail {
    return Intl.message(
      '修改失败',
      name: 'pwd_modify_fail',
      desc: '',
      args: [],
    );
  }

  /// `原密码`
  String get old_pwd {
    return Intl.message(
      '原密码',
      name: 'old_pwd',
      desc: '',
      args: [],
    );
  }

  /// `新密码`
  String get new_pwd {
    return Intl.message(
      '新密码',
      name: 'new_pwd',
      desc: '',
      args: [],
    );
  }

  /// `客服电话`
  String get service_tel {
    return Intl.message(
      '客服电话',
      name: 'service_tel',
      desc: '',
      args: [],
    );
  }

  /// `退出登录`
  String get logout {
    return Intl.message(
      '退出登录',
      name: 'logout',
      desc: '',
      args: [],
    );
  }

  /// `确定退出登录`
  String get logout_warn {
    return Intl.message(
      '确定退出登录',
      name: 'logout_warn',
      desc: '',
      args: [],
    );
  }

  /// `完成`
  String get common_finish {
    return Intl.message(
      '完成',
      name: 'common_finish',
      desc: '',
      args: [],
    );
  }

  /// `取消`
  String get common_cancel {
    return Intl.message(
      '取消',
      name: 'common_cancel',
      desc: '',
      args: [],
    );
  }

  /// `删除`
  String get common_delete {
    return Intl.message(
      '删除',
      name: 'common_delete',
      desc: '',
      args: [],
    );
  }

  /// `头像`
  String get set_user_info_image {
    return Intl.message(
      '头像',
      name: 'set_user_info_image',
      desc: '',
      args: [],
    );
  }

  /// `会员名`
  String get set_user_info_name {
    return Intl.message(
      '会员名',
      name: 'set_user_info_name',
      desc: '',
      args: [],
    );
  }

  /// `手机号`
  String get set_user_info_tel {
    return Intl.message(
      '手机号',
      name: 'set_user_info_tel',
      desc: '',
      args: [],
    );
  }

  /// `收货地址管理`
  String get set_user_info_place {
    return Intl.message(
      '收货地址管理',
      name: 'set_user_info_place',
      desc: '',
      args: [],
    );
  }

  /// `添加收货地址`
  String get place_add_title {
    return Intl.message(
      '添加收货地址',
      name: 'place_add_title',
      desc: '',
      args: [],
    );
  }

  /// `收货地址`
  String get place_title {
    return Intl.message(
      '收货地址',
      name: 'place_title',
      desc: '',
      args: [],
    );
  }

  /// `新增收货地址`
  String get place_new_create {
    return Intl.message(
      '新增收货地址',
      name: 'place_new_create',
      desc: '',
      args: [],
    );
  }

  /// `请输入收货人姓名`
  String get place_name_hint {
    return Intl.message(
      '请输入收货人姓名',
      name: 'place_name_hint',
      desc: '',
      args: [],
    );
  }

  /// `请输入收货人手机号码`
  String get place_tel_hint {
    return Intl.message(
      '请输入收货人手机号码',
      name: 'place_tel_hint',
      desc: '',
      args: [],
    );
  }

  /// `所在地区:`
  String get place_city_title {
    return Intl.message(
      '所在地区:',
      name: 'place_city_title',
      desc: '',
      args: [],
    );
  }

  /// `省份 城市 地区`
  String get place_city_hint {
    return Intl.message(
      '省份 城市 地区',
      name: 'place_city_hint',
      desc: '',
      args: [],
    );
  }

  /// `请选择省份 城市 地区`
  String get place_city_warn {
    return Intl.message(
      '请选择省份 城市 地区',
      name: 'place_city_warn',
      desc: '',
      args: [],
    );
  }

  /// `请输入详细地址：如道路、门牌号、小区、楼栋号、单元、室等信息`
  String get place_detail_hint {
    return Intl.message(
      '请输入详细地址：如道路、门牌号、小区、楼栋号、单元、室等信息',
      name: 'place_detail_hint',
      desc: '',
      args: [],
    );
  }

  /// `请输入详细地址`
  String get place_detail_warn {
    return Intl.message(
      '请输入详细地址',
      name: 'place_detail_warn',
      desc: '',
      args: [],
    );
  }

  /// `设为默认地址`
  String get place_normal_title {
    return Intl.message(
      '设为默认地址',
      name: 'place_normal_title',
      desc: '',
      args: [],
    );
  }

  /// `删除收货地址`
  String get place_delete_title {
    return Intl.message(
      '删除收货地址',
      name: 'place_delete_title',
      desc: '',
      args: [],
    );
  }

  /// `确定要删除该地址吗?`
  String get place_delete_warn {
    return Intl.message(
      '确定要删除该地址吗?',
      name: 'place_delete_warn',
      desc: '',
      args: [],
    );
  }

  /// `保存`
  String get place_save {
    return Intl.message(
      '保存',
      name: 'place_save',
      desc: '',
      args: [],
    );
  }

  /// `设为默认`
  String get place_set_default {
    return Intl.message(
      '设为默认',
      name: 'place_set_default',
      desc: '',
      args: [],
    );
  }

  /// `默认`
  String get default_title {
    return Intl.message(
      '默认',
      name: 'default_title',
      desc: '',
      args: [],
    );
  }

  /// `账户余额(元)`
  String get balance_title {
    return Intl.message(
      '账户余额(元)',
      name: 'balance_title',
      desc: '',
      args: [],
    );
  }

  /// `预收款(元)`
  String get balance_frozen {
    return Intl.message(
      '预收款(元)',
      name: 'balance_frozen',
      desc: '',
      args: [],
    );
  }

  /// `可提现(元)`
  String get balance_cash {
    return Intl.message(
      '可提现(元)',
      name: 'balance_cash',
      desc: '',
      args: [],
    );
  }

  /// `转入`
  String get balance_save {
    return Intl.message(
      '转入',
      name: 'balance_save',
      desc: '',
      args: [],
    );
  }

  /// `提现`
  String get balance_fetch {
    return Intl.message(
      '提现',
      name: 'balance_fetch',
      desc: '',
      args: [],
    );
  }

  /// `提现/结算银行卡`
  String get balance_bank_info {
    return Intl.message(
      '提现/结算银行卡',
      name: 'balance_bank_info',
      desc: '',
      args: [],
    );
  }

  /// `说明：\n1、提现到账时间3个工作日以内，具体以银行处理为准；\n2、提现费率0.5/笔；\n3、余额为您当前帐户全部资产，可提现余额为您账号实际可提现到银行卡金额。`
  String get balance_warn {
    return Intl.message(
      '说明：\n1、提现到账时间3个工作日以内，具体以银行处理为准；\n2、提现费率0.5/笔；\n3、余额为您当前帐户全部资产，可提现余额为您账号实际可提现到银行卡金额。',
      name: 'balance_warn',
      desc: '',
      args: [],
    );
  }

  /// `可提现金额`
  String get balance_cash_warn_title {
    return Intl.message(
      '可提现金额',
      name: 'balance_cash_warn_title',
      desc: '',
      args: [],
    );
  }

  /// `已经结算到您的账户，可直接提现至银行卡的金额`
  String get balance_cash_warn_content {
    return Intl.message(
      '已经结算到您的账户，可直接提现至银行卡的金额',
      name: 'balance_cash_warn_content',
      desc: '',
      args: [],
    );
  }

  /// `冻结金额`
  String get balance_frozen_warn_title {
    return Intl.message(
      '冻结金额',
      name: 'balance_frozen_warn_title',
      desc: '',
      args: [],
    );
  }

  /// `冻结金额为仅可用于向上级进货，但不能提现的金额`
  String get balance_frozen_warn_content {
    return Intl.message(
      '冻结金额为仅可用于向上级进货，但不能提现的金额',
      name: 'balance_frozen_warn_content',
      desc: '',
      args: [],
    );
  }

  /// `账户明细`
  String get balance_detail_title {
    return Intl.message(
      '账户明细',
      name: 'balance_detail_title',
      desc: '',
      args: [],
    );
  }

  /// `预付款`
  String get balance_detail_frozen {
    return Intl.message(
      '预付款',
      name: 'balance_detail_frozen',
      desc: '',
      args: [],
    );
  }

  /// `收入`
  String get balance_detail_save {
    return Intl.message(
      '收入',
      name: 'balance_detail_save',
      desc: '',
      args: [],
    );
  }

  /// `支出`
  String get balance_detail_fetch {
    return Intl.message(
      '支出',
      name: 'balance_detail_fetch',
      desc: '',
      args: [],
    );
  }

  /// `预付款余额`
  String get balance_detail_frozen_balance {
    return Intl.message(
      '预付款余额',
      name: 'balance_detail_frozen_balance',
      desc: '',
      args: [],
    );
  }

  /// `累计收入(元)`
  String get earnings_title {
    return Intl.message(
      '累计收入(元)',
      name: 'earnings_title',
      desc: '',
      args: [],
    );
  }

  /// `收益结算之后自动转到余额`
  String get earnings_warn {
    return Intl.message(
      '收益结算之后自动转到余额',
      name: 'earnings_warn',
      desc: '',
      args: [],
    );
  }

  /// `收益明细`
  String get earnings_detail {
    return Intl.message(
      '收益明细',
      name: 'earnings_detail',
      desc: '',
      args: [],
    );
  }

  /// `待结算`
  String get earnings_wait_title {
    return Intl.message(
      '待结算',
      name: 'earnings_wait_title',
      desc: '',
      args: [],
    );
  }

  /// `已结算`
  String get earnings_finish_title {
    return Intl.message(
      '已结算',
      name: 'earnings_finish_title',
      desc: '',
      args: [],
    );
  }

  /// `姓名`
  String get pay_save_name {
    return Intl.message(
      '姓名',
      name: 'pay_save_name',
      desc: '',
      args: [],
    );
  }

  /// `支付方式`
  String get pay_save_type {
    return Intl.message(
      '支付方式',
      name: 'pay_save_type',
      desc: '',
      args: [],
    );
  }

  /// `银行大额转账`
  String get pay_save_type_content {
    return Intl.message(
      '银行大额转账',
      name: 'pay_save_type_content',
      desc: '',
      args: [],
    );
  }

  /// `转账无限额`
  String get pay_save_type_detail {
    return Intl.message(
      '转账无限额',
      name: 'pay_save_type_detail',
      desc: '',
      args: [],
    );
  }

  /// `开户名`
  String get pay_save_open_name {
    return Intl.message(
      '开户名',
      name: 'pay_save_open_name',
      desc: '',
      args: [],
    );
  }

  /// `开户银行`
  String get pay_save_open_bank {
    return Intl.message(
      '开户银行',
      name: 'pay_save_open_bank',
      desc: '',
      args: [],
    );
  }

  /// `电子银行卡号`
  String get pay_save_bank_cardId {
    return Intl.message(
      '电子银行卡号',
      name: 'pay_save_bank_cardId',
      desc: '',
      args: [],
    );
  }

  /// `预付款余额`
  String get pay_save_balance {
    return Intl.message(
      '预付款余额',
      name: 'pay_save_balance',
      desc: '',
      args: [],
    );
  }

  /// `大额转账详细说明`
  String get pay_save_detail_warn {
    return Intl.message(
      '大额转账详细说明',
      name: 'pay_save_detail_warn',
      desc: '',
      args: [],
    );
  }

  /// `·大额转账充值无手续费\n·5万实时到账，5万以上次日到账`
  String get pay_save_warn {
    return Intl.message(
      '·大额转账充值无手续费\n·5万实时到账，5万以上次日到账',
      name: 'pay_save_warn',
      desc: '',
      args: [],
    );
  }

  /// `余额提现`
  String get pay_withdraw_title {
    return Intl.message(
      '余额提现',
      name: 'pay_withdraw_title',
      desc: '',
      args: [],
    );
  }

  /// `储蓄卡`
  String get pay_bank_card_type {
    return Intl.message(
      '储蓄卡',
      name: 'pay_bank_card_type',
      desc: '',
      args: [],
    );
  }

  /// `尾号`
  String get pay_bank_card_tail_number {
    return Intl.message(
      '尾号',
      name: 'pay_bank_card_tail_number',
      desc: '',
      args: [],
    );
  }

  /// `提现金额`
  String get pay_withdraw_amount_title {
    return Intl.message(
      '提现金额',
      name: 'pay_withdraw_amount_title',
      desc: '',
      args: [],
    );
  }

  /// `请输入金额`
  String get pay_withdraw_amount_hint {
    return Intl.message(
      '请输入金额',
      name: 'pay_withdraw_amount_hint',
      desc: '',
      args: [],
    );
  }

  /// `手续费：0.5元/笔`
  String get pay_withdraw_warn {
    return Intl.message(
      '手续费：0.5元/笔',
      name: 'pay_withdraw_warn',
      desc: '',
      args: [],
    );
  }

  /// `可提现金额`
  String get pay_withdraw_amount {
    return Intl.message(
      '可提现金额',
      name: 'pay_withdraw_amount',
      desc: '',
      args: [],
    );
  }

  /// `全部提现`
  String get pay_withdraw_amount_all {
    return Intl.message(
      '全部提现',
      name: 'pay_withdraw_amount_all',
      desc: '',
      args: [],
    );
  }

  /// `立即提现`
  String get pay_withdraw_amount_action {
    return Intl.message(
      '立即提现',
      name: 'pay_withdraw_amount_action',
      desc: '',
      args: [],
    );
  }

  /// `您输入的金额超过可提现余额`
  String get pag_withdraw_error_1 {
    return Intl.message(
      '您输入的金额超过可提现余额',
      name: 'pag_withdraw_error_1',
      desc: '',
      args: [],
    );
  }

  /// `您输入的金额超过单笔最高限额`
  String get pag_withdraw_error_2 {
    return Intl.message(
      '您输入的金额超过单笔最高限额',
      name: 'pag_withdraw_error_2',
      desc: '',
      args: [],
    );
  }

  /// `提现金额`
  String get pag_withdraw_amount_title {
    return Intl.message(
      '提现金额',
      name: 'pag_withdraw_amount_title',
      desc: '',
      args: [],
    );
  }

  /// `手续费`
  String get pag_withdraw_service_charge {
    return Intl.message(
      '手续费',
      name: 'pag_withdraw_service_charge',
      desc: '',
      args: [],
    );
  }

  /// `到账金额`
  String get pag_withdraw_reality {
    return Intl.message(
      '到账金额',
      name: 'pag_withdraw_reality',
      desc: '',
      args: [],
    );
  }

  /// `提现申请提交成功\n等待银行打款`
  String get pag_withdraw_success_warn {
    return Intl.message(
      '提现申请提交成功\n等待银行打款',
      name: 'pag_withdraw_success_warn',
      desc: '',
      args: [],
    );
  }

  /// `系统繁忙，请稍后再试`
  String get pag_withdraw_error_warn {
    return Intl.message(
      '系统繁忙，请稍后再试',
      name: 'pag_withdraw_error_warn',
      desc: '',
      args: [],
    );
  }

  /// `复制`
  String get copy_title {
    return Intl.message(
      '复制',
      name: 'copy_title',
      desc: '',
      args: [],
    );
  }

  /// `复制成功`
  String get copy_success_title {
    return Intl.message(
      '复制成功',
      name: 'copy_success_title',
      desc: '',
      args: [],
    );
  }

  /// `请选择所在地区`
  String get address_widget_title {
    return Intl.message(
      '请选择所在地区',
      name: 'address_widget_title',
      desc: '',
      args: [],
    );
  }

  /// `请选择`
  String get address_select_tag {
    return Intl.message(
      '请选择',
      name: 'address_select_tag',
      desc: '',
      args: [],
    );
  }

  /// `请选择开户类型`
  String get open_account_type {
    return Intl.message(
      '请选择开户类型',
      name: 'open_account_type',
      desc: '',
      args: [],
    );
  }

  /// `个人开户`
  String get open_person_account {
    return Intl.message(
      '个人开户',
      name: 'open_person_account',
      desc: '',
      args: [],
    );
  }

  /// `填写个人开户信息`
  String get open_person_account_title {
    return Intl.message(
      '填写个人开户信息',
      name: 'open_person_account_title',
      desc: '',
      args: [],
    );
  }

  /// `证件类型`
  String get user_id_card_type_title {
    return Intl.message(
      '证件类型',
      name: 'user_id_card_type_title',
      desc: '',
      args: [],
    );
  }

  /// `身份证`
  String get user_id_card_type {
    return Intl.message(
      '身份证',
      name: 'user_id_card_type',
      desc: '',
      args: [],
    );
  }

  /// `证件号码`
  String get user_id_card_number {
    return Intl.message(
      '证件号码',
      name: 'user_id_card_number',
      desc: '',
      args: [],
    );
  }

  /// `请输入证件号码`
  String get user_id_card_number_hint {
    return Intl.message(
      '请输入证件号码',
      name: 'user_id_card_number_hint',
      desc: '',
      args: [],
    );
  }

  /// `个人信息`
  String get person_info {
    return Intl.message(
      '个人信息',
      name: 'person_info',
      desc: '',
      args: [],
    );
  }

  /// `请输入姓名`
  String get person_name_hint {
    return Intl.message(
      '请输入姓名',
      name: 'person_name_hint',
      desc: '',
      args: [],
    );
  }

  /// `开户银行信息`
  String get bank_info {
    return Intl.message(
      '开户银行信息',
      name: 'bank_info',
      desc: '',
      args: [],
    );
  }

  /// `选择银行`
  String get bank_choose {
    return Intl.message(
      '选择银行',
      name: 'bank_choose',
      desc: '',
      args: [],
    );
  }

  /// `请选择`
  String get choose_hint {
    return Intl.message(
      '请选择',
      name: 'choose_hint',
      desc: '',
      args: [],
    );
  }

  /// `请选择开户银行`
  String get bank_choose_warn {
    return Intl.message(
      '请选择开户银行',
      name: 'bank_choose_warn',
      desc: '',
      args: [],
    );
  }

  /// `储蓄卡卡号`
  String get bank_card_number {
    return Intl.message(
      '储蓄卡卡号',
      name: 'bank_card_number',
      desc: '',
      args: [],
    );
  }

  /// `请输入储蓄卡卡号`
  String get bank_card_number_hint {
    return Intl.message(
      '请输入储蓄卡卡号',
      name: 'bank_card_number_hint',
      desc: '',
      args: [],
    );
  }

  /// `银行预留手机号`
  String get bank_user_tel {
    return Intl.message(
      '银行预留手机号',
      name: 'bank_user_tel',
      desc: '',
      args: [],
    );
  }

  /// `请输入银行预留手机号`
  String get bank_user_tel_hint {
    return Intl.message(
      '请输入银行预留手机号',
      name: 'bank_user_tel_hint',
      desc: '',
      args: [],
    );
  }

  /// `选择银行`
  String get bank_list_search_title {
    return Intl.message(
      '选择银行',
      name: 'bank_list_search_title',
      desc: '',
      args: [],
    );
  }

  /// `输入银行名进行搜索`
  String get bank_search_hint {
    return Intl.message(
      '输入银行名进行搜索',
      name: 'bank_search_hint',
      desc: '',
      args: [],
    );
  }

  /// `提交`
  String get submit_title {
    return Intl.message(
      '提交',
      name: 'submit_title',
      desc: '',
      args: [],
    );
  }

  /// `提交企业开户信息，个人户将被注销`
  String get open_company_account_warn {
    return Intl.message(
      '提交企业开户信息，个人户将被注销',
      name: 'open_company_account_warn',
      desc: '',
      args: [],
    );
  }

  /// `企业开户`
  String get open_company_account {
    return Intl.message(
      '企业开户',
      name: 'open_company_account',
      desc: '',
      args: [],
    );
  }

  /// `填写企业开户信息`
  String get open_company_account_title {
    return Intl.message(
      '填写企业开户信息',
      name: 'open_company_account_title',
      desc: '',
      args: [],
    );
  }

  /// `示例说明`
  String get open_company_account_explain {
    return Intl.message(
      '示例说明',
      name: 'open_company_account_explain',
      desc: '',
      args: [],
    );
  }

  /// `企业信息`
  String get company_info_title {
    return Intl.message(
      '企业信息',
      name: 'company_info_title',
      desc: '',
      args: [],
    );
  }

  /// `企业名称`
  String get company_name {
    return Intl.message(
      '企业名称',
      name: 'company_name',
      desc: '',
      args: [],
    );
  }

  /// `请输入企业名称`
  String get company_name_hint {
    return Intl.message(
      '请输入企业名称',
      name: 'company_name_hint',
      desc: '',
      args: [],
    );
  }

  /// `企业证件证件类型`
  String get company_card_type_title {
    return Intl.message(
      '企业证件证件类型',
      name: 'company_card_type_title',
      desc: '',
      args: [],
    );
  }

  /// `营业执照`
  String get company_card_type {
    return Intl.message(
      '营业执照',
      name: 'company_card_type',
      desc: '',
      args: [],
    );
  }

  /// `证件号码`
  String get company_card_number {
    return Intl.message(
      '证件号码',
      name: 'company_card_number',
      desc: '',
      args: [],
    );
  }

  /// `请输入证件号码`
  String get company_card_number_hint {
    return Intl.message(
      '请输入证件号码',
      name: 'company_card_number_hint',
      desc: '',
      args: [],
    );
  }

  /// `预留经办人手机号`
  String get bank_company_tel {
    return Intl.message(
      '预留经办人手机号',
      name: 'bank_company_tel',
      desc: '',
      args: [],
    );
  }

  /// `请输入预留手机号`
  String get bank_company_tel_hint {
    return Intl.message(
      '请输入预留手机号',
      name: 'bank_company_tel_hint',
      desc: '',
      args: [],
    );
  }

  /// `企业对公账号`
  String get bank_company_card_number {
    return Intl.message(
      '企业对公账号',
      name: 'bank_company_card_number',
      desc: '',
      args: [],
    );
  }

  /// `对公账户`
  String get bank_company_card_number_simple {
    return Intl.message(
      '对公账户',
      name: 'bank_company_card_number_simple',
      desc: '',
      args: [],
    );
  }

  /// `请输入对公账户卡号`
  String get bank_company_card_number_hint {
    return Intl.message(
      '请输入对公账户卡号',
      name: 'bank_company_card_number_hint',
      desc: '',
      args: [],
    );
  }

  /// `法人信息`
  String get legal_person_info {
    return Intl.message(
      '法人信息',
      name: 'legal_person_info',
      desc: '',
      args: [],
    );
  }

  /// `法人姓名`
  String get legal_person_name {
    return Intl.message(
      '法人姓名',
      name: 'legal_person_name',
      desc: '',
      args: [],
    );
  }

  /// `请输入法人姓名`
  String get legal_person_name_hint {
    return Intl.message(
      '请输入法人姓名',
      name: 'legal_person_name_hint',
      desc: '',
      args: [],
    );
  }

  /// `性别`
  String get person_sex {
    return Intl.message(
      '性别',
      name: 'person_sex',
      desc: '',
      args: [],
    );
  }

  /// `请选择性别`
  String get person_sex_hint {
    return Intl.message(
      '请选择性别',
      name: 'person_sex_hint',
      desc: '',
      args: [],
    );
  }

  /// `您当前已有提交记录`
  String get already_submit {
    return Intl.message(
      '您当前已有提交记录',
      name: 'already_submit',
      desc: '',
      args: [],
    );
  }

  /// `1:若已提交营业执照照片，小额转账等信息，请耐心等待银行审核结果\n2:若未提交营业执照照片，小额转账等信息，点击“提交”按钮后继续提交开户信息`
  String get already_submit_content {
    return Intl.message(
      '1:若已提交营业执照照片，小额转账等信息，请耐心等待银行审核结果\n2:若未提交营业执照照片，小额转账等信息，点击“提交”按钮后继续提交开户信息',
      name: 'already_submit_content',
      desc: '',
      args: [],
    );
  }

  /// `审核失败`
  String get open_company_account_fail {
    return Intl.message(
      '审核失败',
      name: 'open_company_account_fail',
      desc: '',
      args: [],
    );
  }

  /// `您提交的企业开户信息审核未通过！`
  String get open_company_account_fail_content {
    return Intl.message(
      '您提交的企业开户信息审核未通过！',
      name: 'open_company_account_fail_content',
      desc: '',
      args: [],
    );
  }

  /// `开户成功`
  String get open_account_success {
    return Intl.message(
      '开户成功',
      name: 'open_account_success',
      desc: '',
      args: [],
    );
  }

  /// `您还未提交银行审核`
  String get open_account_wait_status_title {
    return Intl.message(
      '您还未提交银行审核',
      name: 'open_account_wait_status_title',
      desc: '',
      args: [],
    );
  }

  /// `1. 若未提交营业执照照片，小额转账等信息，点击“提交”按钮后继续提交开户信息\n2. 企业开户未成功状态下暂无法处理发货，如需处理，请联系商务`
  String get open_account_wait_status_content {
    return Intl.message(
      '1. 若未提交营业执照照片，小额转账等信息，点击“提交”按钮后继续提交开户信息\n2. 企业开户未成功状态下暂无法处理发货，如需处理，请联系商务',
      name: 'open_account_wait_status_content',
      desc: '',
      args: [],
    );
  }

  /// `银行审核中`
  String get open_account_check_status_title {
    return Intl.message(
      '银行审核中',
      name: 'open_account_check_status_title',
      desc: '',
      args: [],
    );
  }

  /// `1. 您已提交开户信息，请耐心等待银行审核结果\n2. 若信息填写错误，可重新提交开户信息，不影响审核进度`
  String get open_account_check_status_content {
    return Intl.message(
      '1. 您已提交开户信息，请耐心等待银行审核结果\n2. 若信息填写错误，可重新提交开户信息，不影响审核进度',
      name: 'open_account_check_status_content',
      desc: '',
      args: [],
    );
  }

  /// `审核失败`
  String get open_account_fail_status_title {
    return Intl.message(
      '审核失败',
      name: 'open_account_fail_status_title',
      desc: '',
      args: [],
    );
  }

  /// `您提交的企业开户信息审核未通过！\n失败原因:\n1. 营业执照不清晰；2. 营业执照与证件照不一致\n2. 企业开户未成功状态下暂无法处理发货，如需处理，请联系商务`
  String get open_account_fail_status_content {
    return Intl.message(
      '您提交的企业开户信息审核未通过！\n失败原因:\n1. 营业执照不清晰；2. 营业执照与证件照不一致\n2. 企业开户未成功状态下暂无法处理发货，如需处理，请联系商务',
      name: 'open_account_fail_status_content',
      desc: '',
      args: [],
    );
  }

  /// `提交企业开户信息，个人户将被注销`
  String get open_account_recheck_status_content {
    return Intl.message(
      '提交企业开户信息，个人户将被注销',
      name: 'open_account_recheck_status_content',
      desc: '',
      args: [],
    );
  }

  /// `继续提交`
  String get open_account_again_title {
    return Intl.message(
      '继续提交',
      name: 'open_account_again_title',
      desc: '',
      args: [],
    );
  }

  /// `重新提交`
  String get open_account_recheck_title {
    return Intl.message(
      '重新提交',
      name: 'open_account_recheck_title',
      desc: '',
      args: [],
    );
  }

  /// `忘记登录密码`
  String get forget_login_password_title {
    return Intl.message(
      '忘记登录密码',
      name: 'forget_login_password_title',
      desc: '',
      args: [],
    );
  }

  /// `验证手机号/邮箱`
  String get check_tel_email_title {
    return Intl.message(
      '验证手机号/邮箱',
      name: 'check_tel_email_title',
      desc: '',
      args: [],
    );
  }

  /// `重置登录密码`
  String get reset_login_password_title {
    return Intl.message(
      '重置登录密码',
      name: 'reset_login_password_title',
      desc: '',
      args: [],
    );
  }

  /// `手机号/邮箱`
  String get tel_email_title {
    return Intl.message(
      '手机号/邮箱',
      name: 'tel_email_title',
      desc: '',
      args: [],
    );
  }

  /// `请输入手机号/邮箱`
  String get tel_email_title_hint {
    return Intl.message(
      '请输入手机号/邮箱',
      name: 'tel_email_title_hint',
      desc: '',
      args: [],
    );
  }

  /// `下一步`
  String get next_title {
    return Intl.message(
      '下一步',
      name: 'next_title',
      desc: '',
      args: [],
    );
  }

  /// `消息中心`
  String get msg_center_title {
    return Intl.message(
      '消息中心',
      name: 'msg_center_title',
      desc: '',
      args: [],
    );
  }

  /// `公告`
  String get msg_notice_type_title {
    return Intl.message(
      '公告',
      name: 'msg_notice_type_title',
      desc: '',
      args: [],
    );
  }

  /// `消息`
  String get msg_msg_type_title {
    return Intl.message(
      '消息',
      name: 'msg_msg_type_title',
      desc: '',
      args: [],
    );
  }

  /// `全部已读`
  String get msg_read_all_title {
    return Intl.message(
      '全部已读',
      name: 'msg_read_all_title',
      desc: '',
      args: [],
    );
  }

  /// `已读`
  String get msg_read_title {
    return Intl.message(
      '已读',
      name: 'msg_read_title',
      desc: '',
      args: [],
    );
  }

  /// `暂无消息~`
  String get msg_no_data_txt {
    return Intl.message(
      '暂无消息~',
      name: 'msg_no_data_txt',
      desc: '',
      args: [],
    );
  }

  /// `该类型暂不支持跳转`
  String get msg_type_no_pass {
    return Intl.message(
      '该类型暂不支持跳转',
      name: 'msg_type_no_pass',
      desc: '',
      args: [],
    );
  }

  /// `已读中...`
  String get msg_reading {
    return Intl.message(
      '已读中...',
      name: 'msg_reading',
      desc: '',
      args: [],
    );
  }

  /// `invite`
  String get invite_txt {
    return Intl.message(
      'invite',
      name: 'invite_txt',
      desc: '',
      args: [],
    );
  }

  /// `邀请好友`
  String get invite_friend_title {
    return Intl.message(
      '邀请好友',
      name: 'invite_friend_title',
      desc: '',
      args: [],
    );
  }

  /// `立即邀请`
  String get invite_friend_now_title {
    return Intl.message(
      '立即邀请',
      name: 'invite_friend_now_title',
      desc: '',
      args: [],
    );
  }

  /// `点击邀请购买\n分享商品链接`
  String get invite_share_title {
    return Intl.message(
      '点击邀请购买\n分享商品链接',
      name: 'invite_share_title',
      desc: '',
      args: [],
    );
  }

  /// `好友注册`
  String get invite_rigist_title {
    return Intl.message(
      '好友注册',
      name: 'invite_rigist_title',
      desc: '',
      args: [],
    );
  }

  /// `购买\n升级商品`
  String get invite_buy_title {
    return Intl.message(
      '购买\n升级商品',
      name: 'invite_buy_title',
      desc: '',
      args: [],
    );
  }

  /// `好友成为\n我的直接客户`
  String get invite_customer_title {
    return Intl.message(
      '好友成为\n我的直接客户',
      name: 'invite_customer_title',
      desc: '',
      args: [],
    );
  }

  /// `邀请购买`
  String get invite_buy_txt {
    return Intl.message(
      '邀请购买',
      name: 'invite_buy_txt',
      desc: '',
      args: [],
    );
  }

  /// `订货总价:`
  String get invite_buy_total_price_txt {
    return Intl.message(
      '订货总价:',
      name: 'invite_buy_total_price_txt',
      desc: '',
      args: [],
    );
  }

  /// `1、您可以通过邀请好友注册并购买后成为自己的直接客户`
  String get invite_rule_txt_1 {
    return Intl.message(
      '1、您可以通过邀请好友注册并购买后成为自己的直接客户',
      name: 'invite_rule_txt_1',
      desc: '',
      args: [],
    );
  }

  /// `2、您有三种头衔价格可以分享，分别是：升级成超级会员、升级成合伙人、升级成会长;`
  String get invite_rule_txt_2 {
    return Intl.message(
      '2、您有三种头衔价格可以分享，分别是：升级成超级会员、升级成合伙人、升级成会长;',
      name: 'invite_rule_txt_2',
      desc: '',
      args: [],
    );
  }

  /// `3、好友在购买时，可以调整数量，但只能升级到该头衔;如果好友想升级到其他头衔，您可以分享其他链接,好友重新购买;`
  String get invite_rule_txt_3 {
    return Intl.message(
      '3、好友在购买时，可以调整数量，但只能升级到该头衔;如果好友想升级到其他头衔，您可以分享其他链接,好友重新购买;',
      name: 'invite_rule_txt_3',
      desc: '',
      args: [],
    );
  }

  /// `4、购买成功后，绑定关系，将无法调整。`
  String get invite_rule_txt_4 {
    return Intl.message(
      '4、购买成功后，绑定关系，将无法调整。',
      name: 'invite_rule_txt_4',
      desc: '',
      args: [],
    );
  }

  /// `男`
  String get male {
    return Intl.message(
      '男',
      name: 'male',
      desc: '',
      args: [],
    );
  }

  /// `女`
  String get female {
    return Intl.message(
      '女',
      name: 'female',
      desc: '',
      args: [],
    );
  }

  /// `帮助中心`
  String get customer_help_title {
    return Intl.message(
      '帮助中心',
      name: 'customer_help_title',
      desc: '',
      args: [],
    );
  }

  /// `客服电话拨打失败`
  String get customer_phone_failed {
    return Intl.message(
      '客服电话拨打失败',
      name: 'customer_phone_failed',
      desc: '',
      args: [],
    );
  }

  /// `头像修改成功`
  String get user_header_edit_success {
    return Intl.message(
      '头像修改成功',
      name: 'user_header_edit_success',
      desc: '',
      args: [],
    );
  }

  /// `头像修改失败`
  String get user_header_edit_Failed {
    return Intl.message(
      '头像修改失败',
      name: 'user_header_edit_Failed',
      desc: '',
      args: [],
    );
  }

  /// `头像修改取消`
  String get user_header_edit_cancel {
    return Intl.message(
      '头像修改取消',
      name: 'user_header_edit_cancel',
      desc: '',
      args: [],
    );
  }

  /// `拍照`
  String get user_header_camera {
    return Intl.message(
      '拍照',
      name: 'user_header_camera',
      desc: '',
      args: [],
    );
  }

  /// `从手机相册选择`
  String get user_header_gallery {
    return Intl.message(
      '从手机相册选择',
      name: 'user_header_gallery',
      desc: '',
      args: [],
    );
  }

  /// `取消`
  String get user_header_cancel {
    return Intl.message(
      '取消',
      name: 'user_header_cancel',
      desc: '',
      args: [],
    );
  }

  /// `拍照`
  String get camera {
    return Intl.message(
      '拍照',
      name: 'camera',
      desc: '',
      args: [],
    );
  }

  /// `从相册选择`
  String get photo {
    return Intl.message(
      '从相册选择',
      name: 'photo',
      desc: '',
      args: [],
    );
  }
}

class AppLocalizationDelegate extends LocalizationsDelegate<S> {
  const AppLocalizationDelegate();

  List<Locale> get supportedLocales {
    return const <Locale>[
      Locale.fromSubtags(languageCode: 'zh'),
    ];
  }

  @override
  bool isSupported(Locale locale) => _isSupported(locale);
  @override
  Future<S> load(Locale locale) => S.load(locale);
  @override
  bool shouldReload(AppLocalizationDelegate old) => false;

  bool _isSupported(Locale locale) {
    if (locale != null) {
      for (var supportedLocale in supportedLocales) {
        if (supportedLocale.languageCode == locale.languageCode) {
          return true;
        }
      }
    }
    return false;
  }
}