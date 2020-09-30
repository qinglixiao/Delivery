import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_html/flutter_html.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/src/business/message/bean/msg_list.dart';
import 'package:flutter_ienglish_fine/src/business/message/viewmodel/msg_detail_view_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

///
/// 消息详情
/// create by sunyuancun
///
class MsgDetailPage extends StatefulWidget {
  @override
  _MsgDetailPageState createState() => _MsgDetailPageState();
}

class _MsgDetailPageState extends State<MsgDetailPage> with PageBridge, AutomaticKeepAliveClientMixin {
  MsgListItem _msgListItem;
  String _pageTitle = "";

  @override
  Widget build(BuildContext context) {
    super.build(context);
    return FutureBuilder(
        future: _initPageParams(context),
        builder: (BuildContext context, AsyncSnapshot<Object> snapshot) {
          return RootPageWidget(
            appBar: IsAppBar(title: _pageTitle, showBottomLine: true),
            body: Scaffold(
              body: Container(
                padding: EdgeInsets.only(left: 20, right: 20),
                color: Values.of(context).c_white_background,
                child: Column(
                  children: <Widget>[
                    SizedBox(
                      height: Values.d_20,
                    ),
                    _buildTitleWidget(),
                    SizedBox(
                      height: Values.d_15,
                    ),
                    _buildContentWidget()
                  ],
                ),
              ),
            ),
          );
        });
  }

  Widget _buildContentWidget() {
    Widget contentWidget = Container(
        child: Html(
      data: _msgListItem?.content ?? "",
    ));
    return contentWidget;
  }

  Widget _buildTitleWidget() {
    Widget titleWidget = Container(
      alignment: Alignment.center,
      child: Text(
        StringUtil.getNotNullString(_msgListItem?.title),
        overflow: TextOverflow.ellipsis,
        maxLines: 1,
        style: TextStyle(
            fontWeight: Values.font_Weight_medium,
            fontSize: Values.s_text_18,
            color: Values.of(context).c_black_front_33),
      ),
    );

    return titleWidget;
  }

  _initPageParams(BuildContext context) async {
    getInitArg(context).then((value) {
      if (value != null) {
        _msgListItem = value as MsgListItem;
        if (_msgListItem.type != null && _msgListItem.type == "1") {
          _pageTitle = S.of(context).msg_msg_type_title;
        }
        if (_msgListItem.type != null && _msgListItem.type == "2") {
          _pageTitle = S.of(context).msg_notice_type_title;
        }
      }
    });
  }

  @override
  bool get wantKeepAlive => true;

}
