import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/src/business/message/bean/msg_list.dart';
import 'package:flutter_lib/flutter_lib.dart';
///
/// 消息列表共用Item
/// create by sunyuancun
///
// ignore: must_be_immutable
class MsgListItemWidget extends StatefulWidget {
  MsgListItem msgListItem;

  MsgListItemWidget({this.msgListItem});

  @override
  _MsgListItemWidgetState createState() => _MsgListItemWidgetState();
}

class _MsgListItemWidgetState extends State<MsgListItemWidget> {
  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(left: 12, right: 12, top: 12),
      padding: EdgeInsets.only(left: 15, right: 15),
      decoration: BoxDecoration(
          color: Values.of(context).c_white_background,
          borderRadius: BorderRadius.circular(5.0)),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          SizedBox(
            height: Values.d_12,
          ),
          _buildTitleWidget(),
          SizedBox(
            height: Values.d_12,
          ),
          Container(
            height: Values.d_0_5,
            color: Values.c_grey_g2,
          ),
          SizedBox(
            height: Values.d_12,
          ),
          _buildContentWidget(),
          SizedBox(
            height: Values.d_12,
          ),
        ],
      ),
    );
  }

  Widget _buildTitleWidget() {
    Widget _titleWidget = Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: <Widget>[
        Expanded(
          child: Row(
            children: <Widget>[
              widget.msgListItem.status != S.of(context).msg_read_title
                  ? Container(
                      color: Values.of(context).c_red_background_68,
                      padding:
                          EdgeInsets.only(left: 3, top: 2, right: 3, bottom: 2),
                      margin: EdgeInsets.only(right: 5),
                      child: Text(
                        widget.msgListItem.status,
                        style: TextStyle(
                            fontSize: Values.s_text_8,
                            fontWeight: Values.font_Weight_normal,
                            color: Values.of(context).c_white_front),
                      ),
                    )
                  : Container(
                      color: Values.of(context).c_grey_background_ee,
                      padding:
                          EdgeInsets.only(left: 3, top: 2, right: 3, bottom: 2),
                      margin: EdgeInsets.only(right: 5),
                      child: Text(
                        widget.msgListItem.status,
                        style: TextStyle(
                            fontSize: Values.s_text_8,
                            fontWeight: Values.font_Weight_normal,
                            color: Values.of(context).c_black_front_33),
                      ),
                    ),
              Expanded(
                child: Text(
                  widget.msgListItem.title,
                  style: TextStyle(
                      fontSize: Values.s_text_15,
                      fontWeight: Values.font_Weight_medium,
                      color: Values.of(context).c_black_front_33),
                  softWrap: false,
                  maxLines: 1,
                  overflow: TextOverflow.ellipsis,
                ),
              ),
            ],
          ),
        ),
        Text(
          StringUtil.getNotNullString(widget.msgListItem.createTime),
          overflow: TextOverflow.ellipsis,
          maxLines: 1,
          style: TextStyle(
              fontSize: Values.s_text_12,
              color: Values.of(context).c_black_front_99),
        )
      ],
    );
    return _titleWidget;
  }

  Widget _buildContentWidget() {
    Widget _contentWidget = Text(
      StringUtil.getNotNullString(widget.msgListItem.content),
      overflow: TextOverflow.ellipsis,
      maxLines: 3,
      style: TextStyle(
          fontSize: Values.s_text_12,
          color: Values.of(context).c_black_front_66),
    );

    return _contentWidget;
  }
}
