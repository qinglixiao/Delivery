import 'dart:ui';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/src/business/invite/bean/collocation_list.dart';
import 'package:cached_network_image/cached_network_image.dart';

///  created by：sunyuancun
/// 2020/9/9 17
///desc:
// ignore: must_be_immutable
class InviteCollocationWidget extends StatefulWidget {
  final CollocationListItem _collocationListItem;
  final int currentPos, lastPos;
  Function onTap;

  InviteCollocationWidget(this._collocationListItem, this.currentPos, this.lastPos, this.onTap);

  @override
  _InviteCollocationWidgetState createState() => _InviteCollocationWidgetState();
}

class _InviteCollocationWidgetState extends State<InviteCollocationWidget> {
  @override
  Widget build(BuildContext context) {
    Widget _image = CachedNetworkImage(
      width: 97,
      height: 97,
      fit: BoxFit.fill,
      imageUrl: widget._collocationListItem.coverImgUrl ?? "",
    );

    Widget _inviteButton = Container(
      height: Values.d_30,
      child: FlatButton(
          onPressed: () {
            widget.onTap(widget._collocationListItem.id);
          },
          color: Values.of(context).c_orange_background_0b,
          disabledColor: Values.of(context).c_grey_background_cc,
          highlightColor: Values.c_translucent,
          splashColor: Values.c_translucent,
          shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(Values.d_30)),
          child: Text(
            S.of(context).invite_buy_txt,
            style: TextStyle(fontSize: Values.s_text_14, color: Values.of(context).c_white_front),
          )),
    );

    return Container(
      decoration: BoxDecoration(
        color: Values.of(context).c_white_background,
        borderRadius: _calculateWidgetRadius(),
      ),
      margin: EdgeInsets.only(left: 15, right: 15),
      padding: EdgeInsets.fromLTRB(Values.d_12, Values.d_15, Values.d_12, Values.d_15),
      child: Row(
        children: <Widget>[
          _image,
          SizedBox(width: Values.d_12),
          Expanded(
              child: Container(
            height: 97,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: <Widget>[
                Text(
                  widget._collocationListItem.title,
                  maxLines: 1,
                  overflow: TextOverflow.ellipsis,
                  style: TextStyle(
                      color: Values.of(context).c_black_front_33,
                      fontSize: Values.s_text_14,
                      fontWeight: Values.font_Weight_medium),
                ),
                Text(widget._collocationListItem.relayDesc,
                    maxLines: 1,
                    overflow: TextOverflow.ellipsis,
                    style: TextStyle(
                        color: Values.of(context).c_black_front_99,
                        fontSize: Values.s_text_12,
                        fontWeight: Values.font_Weight_normal)),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: <Widget>[
                    Expanded(
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: <Widget>[
                          Text("${S.of(context).invite_buy_total_price_txt}",
                              maxLines: 1,
                              overflow: TextOverflow.ellipsis,
                              style: TextStyle(
                                  color: Values.c_red_r1,
                                  fontSize: Values.s_text_12,
                                  fontWeight: Values.font_Weight_normal)),
                          SizedBox(
                            height: 5,
                          ),
                          Text("¥${widget._collocationListItem.totalPrice.toString()}",
                              maxLines: 1,
                              overflow: TextOverflow.ellipsis,
                              style: TextStyle(
                                  color: Values.c_red_r1,
                                  fontSize: Values.s_text_12,
                                  fontWeight: Values.font_Weight_medium)),
                        ],
                      ),
                    ),
                    _inviteButton
                  ],
                )
              ],
            ),
          ))
        ],
      ),
    );
  }

  BorderRadius _calculateWidgetRadius() {
    if (widget.currentPos == 0 && widget.currentPos == widget.lastPos) {
      return BorderRadius.circular(10); //上下都有
    }

    if (widget.currentPos == 0 && widget.currentPos < widget.lastPos) {
      return BorderRadius.only(topLeft: Radius.circular(10), topRight: Radius.circular(10)); //上有下没有
    }

    if (widget.currentPos > 0 && widget.currentPos == widget.lastPos) {
      return BorderRadius.only(bottomLeft: Radius.circular(10), bottomRight: Radius.circular(10)); //上没有下有
    }

    if (widget.currentPos > 0 && widget.currentPos < widget.lastPos) {
      return BorderRadius.circular(0); //上没有下没有
    }

    return BorderRadius.circular(0);
  }
}
