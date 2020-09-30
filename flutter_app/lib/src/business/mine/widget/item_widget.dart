import 'package:flutter/widgets.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';

///
///create by lx
///date 2020/8/18
///
///

class ItemWidget {
  static Widget leftTextRightIcon(
      BuildContext context, String text, VoidCallback callback,
      {int height, String icon, double padding}) {
    return GestureDetector(
      onTap: () {
        callback();
      },
      child: Container(
        padding: EdgeInsets.symmetric(horizontal: padding ?? 15),
        color: Values.of(context).c_white_background,
        height: height ?? 50,
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            Text(
              text,
              style: TextStyle(
                  fontSize: Values.s_text_15,
                  fontWeight: Values.font_Weight_normal,
                  color: Values.of(context).c_black_front_33,
                  decoration: TextDecoration.none),
            ),
            Image.asset(icon ?? 'assets/images/right_icon.png')
          ],
        ),
      ),
    );
  }

  static Widget leftTextRightWidget(
      BuildContext context, String text, Widget widget,
      {int height, double padding}) {
    return Container(
      padding: EdgeInsets.symmetric(horizontal: padding ?? 15),
      color: Values.of(context).c_white_background,
      height: height ?? 50,
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: <Widget>[
          Text(
            text,
            style: TextStyle(
                fontSize: Values.s_text_15,
                fontWeight: Values.font_Weight_normal,
                color: Values.of(context).c_black_front_33,
                decoration: TextDecoration.none),
          ),
          widget
        ],
      ),
    );
  }
}
