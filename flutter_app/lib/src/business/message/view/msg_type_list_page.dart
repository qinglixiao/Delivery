import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/src/business/message/bean/msg_list.dart';
import 'package:flutter_ienglish_fine/src/business/message/comm/message_provider.dart';
import 'package:flutter_ienglish_fine/src/business/message/view/msg_list_item_widget.dart';
import 'package:flutter_ienglish_fine/src/business/message/viewmodel/msg_type_list_view_model.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_ienglish_fine/src/comm/event/message_center.dart';
import 'package:flutter_lib/flutter_lib.dart';

///
/// 消息分类列表
/// create by sunyuancun
///
class MsgTypeListPage extends StatefulWidget {
  @override
  _MsgTypeListPageState createState() => _MsgTypeListPageState();
}

class _MsgTypeListPageState extends State<MsgTypeListPage> with PageBridge, AutomaticKeepAliveClientMixin {
  int _type;
  String _pageTitle = "";
  List<MsgListItem> _msgList = List();

  StreamSubscription _subscription;

  //下拉加载功能 注释掉
  //SimpleLoadMoreController _loadMoreController;
  MsgTypeListViewModel _msgTypeListViewModel = MsgTypeListViewModel();

  @override
  bool get wantKeepAlive => true;

  @override
  void initState() {
    super.initState();
    // _loadMoreController = SimpleLoadMoreController(() {
    //   _loadMessageList();
    // });
    _subscription =  eventCenter.listen<MessageReadEvent>((event) {
      _msgTypeListViewModel.hasRead = true;
    });
  }

  @override
  Widget build(BuildContext context) {
    super.build(context);
    return FutureBuilder(
      future: _initPageParams(context),
      builder: (BuildContext context, AsyncSnapshot<Object> snapshot) {
        return RootPageWidget(
          viewModel: _msgTypeListViewModel,
          task: _loadMessageList(isFirst: true, isRefresh: true),
          pageChangedCallBack: (routerName) {
            pageChangedCallBack(routerName);
          },
          appBar: IsAppBar(
            title: _pageTitle,
            showBottomLine: true,
            rightWidget: GestureDetector(
              onTap: _signMessageTypeRead,
              child: Text(S
                  .of(context)
                  .msg_read_all_title,
                  style: TextStyle(fontSize: 15, color: Values
                      .of(context)
                      .c_orange_front_0b)),
            ),
          ),
          emptyWidget: AsEmptyWidget("assets/images/bg_no_msg.png", S
              .of(context)
              .msg_no_data_txt, () {}),
          body: Scaffold(
            body: Container(
              color: Values
                  .of(context)
                  .c_grey_background_f8,
              child: _buildMsgListWidget(),
            ),
          ),
        );
      },
    );
  }

  Widget _buildMsgListWidget() {
    Widget msgListWidget = StreamBuilder<MsgList>(
        stream: _msgTypeListViewModel.streamMsgList,
        builder: (BuildContext context, AsyncSnapshot<MsgList> snapshot) {
          if (snapshot.data == null || snapshot.data?.items == null) {
            return CommonWidget.noPreparedWidget();
          }

          if (_msgTypeListViewModel.pageIndex == 1) {
            _msgList.clear();
          }
          _msgList.addAll(snapshot.data?.items);
          // _loadMoreController.hasMore =_msgTypeListViewModel.hasMoreList;

          return PullToRefresh(
            child: SListView(_buildItemView, itemAction: _itemAction).build(context, _msgList),
            onRefresh: () {
              return _loadMessageList(isFirst: false, isRefresh: true);
            },
          );
        });
    return msgListWidget;
  }

  Widget _buildItemView(BuildContext context, Object itemData) {
    return MsgListItemWidget(
      msgListItem: itemData,
    );
  }

  void _itemAction(Widget widget, Object itemData) {
    MsgListItem msgListItem = itemData as MsgListItem;
    if(msgListItem.status != "已读"){
      _msgTypeListViewModel.readMsg(msgListItem.messageReceiverId, (info) {
        eventCenter.emit(MessageReadEvent());
      });
    }
    MessageProvider().openMsgDetailPage(msgListItem);
  }

  _initPageParams(BuildContext context) async {
    getInitArg(context).then((value) {
      if (value != null) {
        _type = value as int;
        if (_type != null && _type == 1) {
          _pageTitle = S
              .of(context)
              .msg_msg_type_title;
        }
        if (_type != null && _type == 2) {
          _pageTitle = S
              .of(context)
              .msg_notice_type_title;
        }
      }
    });
  }

  Future _loadMessageList({bool isFirst = false, bool isRefresh = false}) async {
    if (_type == null) return Future.value('');
    return _msgTypeListViewModel.loadMessageTypeList(_type, isFirst: isFirst, isRefresh: isRefresh);
  }

  Future _signMessageTypeRead() async {
    ProgressHUD.showLoading(loadingText: S
        .of(context)
        .msg_reading);
    _msgTypeListViewModel.readMsgType(_type, (info) {
      _loadMessageList(isFirst: false, isRefresh: true);
      ProgressHUD.hiddenHUD();
      eventCenter.emit(MessageReadEvent());
    });
  }

  pageChangedCallBack(String routerName) {
    if (routerName!= null && routerName == RouterName.msg_type_list && _msgTypeListViewModel.hasRead) {
      _msgTypeListViewModel.hasRead = false;
      _loadMessageList(isFirst: false, isRefresh: true);
    }
  }

  @override
  void dispose() {
    _msgTypeListViewModel.dispose();
    _subscription.cancel();
    super.dispose();
  }
}
