import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/src/business/message/bean/msg_list.dart';
import 'package:flutter_ienglish_fine/src/business/message/comm/message_provider.dart';
import 'package:flutter_ienglish_fine/src/business/message/viewmodel/msg_center_view_model.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_ienglish_fine/src/comm/event/message_center.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'msg_list_item_widget.dart';

///
/// 消息中心
/// create by sunyuancun
///
class MsgCenterPage extends StatefulWidget {
  @override
  _MsgCenterPageState createState() => _MsgCenterPageState();
}

class _MsgCenterPageState extends State<MsgCenterPage> with PageBridge, AutomaticKeepAliveClientMixin {
  MsgCenterViewModel _viewModel = MsgCenterViewModel();
  StreamSubscription _subscription;

  @override
  bool get wantKeepAlive => true;

  @override
  void initState() {
    super.initState();
    _subscription =  eventCenter.listen<MessageReadEvent>((event) {
      _viewModel.hasRead = true;
    });
  }

  @override
  Widget build(BuildContext context) {
    super.build(context);
    return RootPageWidget(
      appBar: IsAppBar(
        title: S.of(context).msg_center_title,
        titleColor: Values.of(context).c_white_front,
        color: [Values.of(context).c_blue_background_light, Values.of(context).c_blue_background_dark],
        canBack: true,
      ),
      viewModel: _viewModel,
      task: _viewModel.loadMessageCenterList(isFrist: true),
      pageChangedCallBack: (routerName) {
        pageChangedCallBack(routerName);
      },
      emptyWidget: AsEmptyWidget("assets/images/bg_no_msg.png", S.of(context).msg_no_data_txt, () {}),
      body: Container(
        color: Values.of(context).c_grey_background_f8,
        child: Stack(
          children: <Widget>[
            Container(
              padding: EdgeInsets.only(left: 0, right: 0, top: 0),
              height: 48.5,
              decoration: BoxDecoration(
                gradient: LinearGradient(
                  colors: [Values.of(context).c_blue_background_light, Values.of(context).c_blue_background_dark],
                ),
              ),
            ),
            Container(
              child: Column(
                children: <Widget>[_buildTabWidget(), _buildMsgListWidget()],
              ),
            )
          ],
        ),
      ),
    );
  }

  Widget _buildTabWidget() {
    return Container(
        alignment: Alignment.center,
        margin: EdgeInsets.only(left: 12, right: 12),
        height: 97,
        decoration:
            BoxDecoration(color: Values.of(context).c_white_background, borderRadius: BorderRadius.circular(5.0)),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: <Widget>[
            GestureDetector(
              onTap: () {
                openMsgTypeListPage(1);
              },
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: <Widget>[
                  Image.asset('assets/images/msg_icon.png'),
                  Text(
                    S.of(context).msg_msg_type_title,
                    style: TextStyle(
                        fontSize: Values.s_text_12,
                        fontWeight: Values.font_Weight_normal,
                        color: Values.of(context).c_black_front_33),
                  ),
                ],
              ),
            ),
            GestureDetector(
              onTap: () {
                openMsgTypeListPage(2);
              },
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: <Widget>[
                  Image.asset('assets/images/notice_icon.png'),
                  Text(
                    S.of(context).msg_notice_type_title,
                    style: TextStyle(
                        fontSize: Values.s_text_12,
                        fontWeight: Values.font_Weight_normal,
                        color: Values.of(context).c_black_front_33),
                  ),
                ],
              ),
            ),
          ],
        ));
  }

  Widget _buildMsgListWidget() {
    return Expanded(
      child: StreamBuilder<MsgList>(
          stream: _viewModel.streamMsgList,
          builder: (BuildContext context, AsyncSnapshot<MsgList> snapshot) {
            if (snapshot.data == null || snapshot.data?.items == null || snapshot.data?.items?.length == 0) {
              return CommonWidget.noPreparedWidget();
            }
            return SListView(_buildItemView, itemAction: _itemAction).build(context, snapshot.data.items);
          }),
    );
  }

  Widget _buildItemView(BuildContext context, Object itemData) {
    return MsgListItemWidget(
      msgListItem: itemData,
    );
  }

  void _itemAction(Widget widget, Object itemData) {
    MsgListItem msgListItem = itemData as MsgListItem;
    if (msgListItem.status != "已读") {
      _viewModel.readMsg(msgListItem.messageReceiverAllId, (info) {
        eventCenter.emit(MessageReadEvent());
      });
    }
    MessageProvider().openMsgDetailPage(msgListItem);
  }

  openMsgTypeListPage(int type) {
    open(RouterName.msg_type_list, argument: type);
  }

  pageChangedCallBack(String routerName) {
    if (routerName != null && routerName == RouterName.msg_center && _viewModel.hasRead) {
      _viewModel.hasRead = false;
      _viewModel.loadMessageCenterList(isFrist: true);
    }
  }

  @override
  void dispose() {
    _viewModel.dispose();
    _subscription.cancel();
    super.dispose();
  }
}
