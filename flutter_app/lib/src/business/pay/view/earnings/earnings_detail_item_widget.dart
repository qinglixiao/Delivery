import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/earnings.dart';

Widget EarningsDetailItemWidget(BuildContext context, Object itemData) {
  EarningsItemBean data = itemData as EarningsItemBean;

  Widget _price = Text(
    data.amount.toStringAsFixed(2),
    style: TextStyle(
        fontSize: Values.s_text_15,
        color: Values.of(context).c_black_front_33,
        fontWeight: Values.font_Weight_medium),
  );

  Widget _status = Text(
    StringUtil.getNotNullString(data?.status),
    style: TextStyle(
        fontSize: Values.s_text_15,
        color: data?.status == '待结算' ?  Values.of(context).c_red_front_68 : Values.of(context).c_black_front_33,
        fontWeight: Values.font_Weight_medium),
  );

  Widget _orederId = Text(
    '收益单号：'+StringUtil.getNotNullString(data?.number),
    style: TextStyle(
        fontSize: Values.s_text_14,
        color: Values.of(context).c_black_front_33,
        fontWeight: Values.font_Weight_normal),
  );

  Widget _time = Text(
    StringUtil.getNotNullString(data?.clearTime),
    style: TextStyle(
        fontSize: Values.s_text_12,
        color: Values.of(context).c_black_front_99,
        fontWeight: Values.font_Weight_normal),
  );

  return Container(
    padding: EdgeInsets.all(Values.d_15),
    decoration: BoxDecoration(
        color: Values.of(context).c_white_background,
        border: Border(
            bottom:
            BorderSide(color: Values.of(context).c_grey_ea, width: 1.0))),
    child: Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: <Widget>[
            _price,
            _status,
          ],
        ),
        SizedBox(height: Values.d_10),
        _orederId,
        SizedBox(height: Values.d_10),
        _time
      ],
    ),
  );
}
