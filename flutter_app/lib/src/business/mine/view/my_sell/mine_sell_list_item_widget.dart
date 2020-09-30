import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter_ienglish_fine/src/business/mine/bean/mine_sell_list.dart';

Widget SellListItemWidget(
    BuildContext context, Object itemData, Function(int type)callBack) {
  MineSellListItem data = itemData as MineSellListItem;

  Widget _senderInfo = Container(
    decoration: new BoxDecoration(
        border: new Border(
            bottom:
                BorderSide(color: Values.of(context).c_grey_ea, width: 1.0))),
    padding: EdgeInsets.only(
        left: Values.d_10, top: Values.d_5, bottom: Values.d_10),
    child: Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: <Widget>[
        Row(
          mainAxisAlignment: MainAxisAlignment.start,
          children: <Widget>[
            Text(S.of(context).dispatch_detail_express_name + data.username,
                style: TextStyle(
                    fontSize: Values.s_text_14,
                    color: Values.of(context).c_black_front_33)),
          ],
        ),
        Text((data.ifhandAffirmpay !=null && data.ifhandAffirmpay ==true )?S.of(context).order_status_1:StringUtil.getNotNullString(data.status),
            style: TextStyle(
                fontSize: Values.s_text_14,
                fontWeight: Values.font_Weight_normal,
                color: Values.of(context).c_red_front_68)),
      ],
    ),
  );

  Widget _image = CachedNetworkImage(
    width: 75,
    height: 75,
    imageUrl: data?.imgUrl ?? "",
  );

  Widget _title = Text(
    StringUtil.getNotNullString(data?.title),
    overflow: TextOverflow.ellipsis,
    maxLines: 2,
    style: TextStyle(
        fontSize: Values.s_text_15, color: Values.of(context).c_black_front_33),
  );

  Widget _price = Text(
    '￥' + data.price.toString() + S.of(context).dispatch_detail_price_unit,
    overflow: TextOverflow.ellipsis,
    style: TextStyle(
        fontSize: Values.s_text_14, color: Values.of(context).c_black_front_66),
  );

  Widget _number = Text(
    'X' + data.count.toString(),
    overflow: TextOverflow.ellipsis,
    style: TextStyle(
        fontSize: Values.s_text_14, color: Values.of(context).c_black_front_66),
  );

  Widget _total = Text.rich(TextSpan(children: [
    TextSpan(text: "${S.of(context).receiving_list_total} "),
    TextSpan(
      text: '￥' + data.paymentFee.toStringAsFixed(2),
      style: TextStyle(color: Values.of(context).c_red_front_68),
    ),
  ]));

  Widget _sendCount = Row(
    children: <Widget>[
      Text(
        S.of(context).dispatch_detail_shipping_status1 + ':',
        style: TextStyle(
            fontSize: Values.s_text_13,
            color: Values.of(context).c_black_front_66),
      ),
      Text(
        StringUtil.getNotNullString((data.count - data.sendCount).toString()),
        style: TextStyle(
            fontSize: Values.s_text_13,
            color: Values.of(context).c_red_front_68),
      )
    ],
  );

  Widget _orderInfoButton = Container(
      height: 28,
      child: OutlineButton(
          highlightColor: Values.c_translucent,
          splashColor: Values.c_translucent,
          color: Values.of(context).c_white_background,
          child: Text(
            S.of(context).order_info,
            style: TextStyle(
                fontSize: Values.s_text_14,
                fontWeight: Values.font_Weight_normal,
                color: Values.of(context).c_orange_front_0b,
                decoration: TextDecoration.none),
          ),
          shape: new RoundedRectangleBorder(
              borderRadius: new BorderRadius.circular(Values.d_36)),
          borderSide:
              new BorderSide(color: Values.of(context).c_orange_background_0b),
          highlightedBorderColor: Values.of(context).c_orange_background_0b,
          onPressed: () {
            callBack(0);
          }));

  Widget _payButton = Container(
    height: 28,
    margin: EdgeInsets.only(left: Values.d_10),
    child: FlatButton(
        onPressed: () {
          callBack(1);
        },
        color: Values.of(context).c_orange_background_0b,
        disabledColor: Values.of(context).c_grey_background_cc,
        highlightColor: Values.c_translucent,
        splashColor: Values.c_translucent,
        shape:
        RoundedRectangleBorder(borderRadius: BorderRadius.circular(14.0)),
        child: Text(
          S.of(context).confirm_receipt,
          style: TextStyle(
              fontSize: Values.s_text_14,
              color: Values.of(context).c_white_front),
        )),
  );

  return Container(
    padding: EdgeInsets.all(Values.d_12),
    margin: EdgeInsets.only(
      left: Values.d_10,
      top: Values.d_10,
      right: Values.d_10,
    ),
    decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(Values.d_5),
        color: Values.of(context).c_white_background),
    child: Column(
      children: <Widget>[
        _senderInfo,
        SizedBox(
          height: Values.d_15,
        ),
        Row(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Container(
                margin: EdgeInsets.only(right: Values.d_10), child: _image),
            Expanded(
              child: Container(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: <Widget>[
                    _title,
                    SizedBox(height: Values.d_15),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: <Widget>[
                        _price,
                        _number,
                      ],
                    ),
                    SizedBox(height: Values.d_30),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.end,
                      children: <Widget>[
                        _total,
                      ],
                    ),
                    SizedBox(height: Values.d_10),
                  ],
                ),
              ),
            ),
          ],
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: <Widget>[
            data.count != data.sendCount ? _sendCount : Container(),
            (data.ifhandAffirmpay !=null && data.ifhandAffirmpay ==true)?_payButton:_orderInfoButton,
          ],
        ),
      ],
    ),
  );
}
