import 'package:flutter_ienglish_fine/src/business/message/bean/msg_list.dart';
import 'package:flutter_ienglish_fine/src/business/mine/comm/constant.dart';
import 'package:flutter_ienglish_fine/src/comm/constant.dart';
import 'package:flutter_ienglish_fine/src/comm/event/index_page_event.dart';
import 'package:flutter_ienglish_fine/src/comm/event/message_center.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_lib/flutter_lib.dart';

///  created by：sunyuancun
/// 2020/9/11
///desc:
class MessageProvider with PageBridge {
  static MessageProvider _instance;

  MessageProvider._();

  static MessageProvider _getInstance() {
    if (_instance == null) {
      _instance = MessageProvider._();
    }

    return _instance;
  }

  factory MessageProvider() => _getInstance();

  openMsgDetailPage(MsgListItem item) {
    int id = item.messageReceiverAllId ?? item.messageReceiverId;
    String title = item.title;

    if (title == '升级提醒!') {
      // 目前H5跳转消息详情页面
      open(RouterName.msg_detail, argument: item);
    } else if (title == '补货提醒!') {
      // 跳转到 补货列表 b2bPartnerOrderList
      open(RouterName.replenish_list);
    } else if (title == '恭喜您，有一个新客户注册' || title == '恭喜您，有一个新伙伴注册。' || title == '恭喜您，有一个新伙伴下单成功。') {
      //跳转到“我的好友”
      //第一期没有该功能,暂时跳转消息详情页面
      open(RouterName.msg_detail, argument: item);
    } else if (title == '欢迎成为IENGLISH服务商！') {
      // 目前H5跳转消息详情页面
      open(RouterName.msg_detail, argument: item);
    } else if (title == '升级后可收到补单') {
      // 跳转到订货页面
      open(RouterName.good_list);
    } else if (title == '已发货提醒!') {
      // 跳转到我卖的已完成
      open(RouterName.my_sell_List, argument: {'type': MINE_SELL_LIST_PAGE_COMPLETED_TAB});
    } else if (title == '账号已到账！') {
      // 跳转到我的页面
      eventCenter.emit(IndexPageEvent(type: 1, data: INDEX_PAGE_ME_TAB));
      popUtil(RouterName.index_page);
    } else if (title == "待收货提醒！") {
      // 跳转到我买的待收货
      open(RouterName.my_buy_list, argument: {'type': MINE_BUY_LIST_PAGE_WAIT_RECEIVING_TAB});
    } else if (title == "订单提醒！") {
      // 跳转到首页
      eventCenter.emit(IndexPageEvent(type: 1, data: INDEX_PAGE_HOME_TAB));
      popUtil(RouterName.index_page);
    } else if (title == '您好，您的的账户资金发生变动，请查看>' || title == '发货提醒') {
      //跳转到余额
      open(RouterName.my_balance);
    } else {
      open(RouterName.msg_detail, argument: item);
    }
  }
}
