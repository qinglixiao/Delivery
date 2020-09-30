import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/balance_detail.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';

Widget BalanceDetailItemWidget(BuildContext context, Object itemData) {
  BalanceDetailItemBean data = itemData as BalanceDetailItemBean;

  Widget _title = Text(
    StringUtil.getNotNullString(data.type??data.remark),
    style: TextStyle(
        fontSize: Values.s_text_15,
        color: Values.of(context).c_black_front_33,
        fontWeight: Values.font_Weight_medium),
  );

  Widget _price = Text(
    data.changeFee!=null?data.changeFee.toStringAsFixed(2):((data.transType == '支出'?'-':'+')+data.transAmt),
    style: TextStyle(
        fontSize: Values.s_text_14,
        color: (data.changeFee != null ? data.changeFee < 0 : data.transType == '支出')
            ? Values.of(context).c_black_front_33
            : Values.of(context).c_red_front_68,
        fontWeight: Values.font_Weight_medium),
  );

  Widget _orederId = Text(
    StringUtil.getNotNullString(data.clearingNo??data.transId),
    style: TextStyle(
        fontSize: Values.s_text_14,
        color: Values.of(context).c_black_front_33,
        fontWeight: Values.font_Weight_normal),
  );

  Widget _time = Text(
    StringUtil.getNotNullString(data.createTime??data.transTime),
    style: TextStyle(
        fontSize: Values.s_text_12,
        color: Values.of(context).c_black_front_99,
        fontWeight: Values.font_Weight_normal),
  );

  Widget _balance = Text(
      data.remainderFee != null ?(S.of(context).balance_detail_frozen_balance +
          ':' + data?.remainderFee.toStringAsFixed(2)):(S.of(context).my_balance +
          ':' + data.bal),
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
            _title,
            _price,
          ],
        ),
        SizedBox(height: Values.d_10),
        _orederId,
        SizedBox(height: Values.d_10),
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: <Widget>[_time, _balance],
        )
      ],
    ),
  );
}
