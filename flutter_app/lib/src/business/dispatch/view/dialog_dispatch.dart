import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_lib/flutter_lib.dart';

const Color _titleColor = Color(0xFF333333);
const Color _contentColor = Color(0xFF999999);
const Color _middlecolor = Color(0xFF999999);
const Color _middleBgColor = Color(0xFFffffff);
const Color _translucentColor = Color(0x00ffffff);

const double _titleSize = 18.0;
const double _contentSize = 16.0;
const double _buttonSize = 16.0;

class DialogDispatch extends StatefulWidget {
  final String title; //标题
  final String listContent; //内容
  final String middleTitle; //中间按钮名称

  final Color titleColor; //标题颜色
  final Color contentColor;
  final Color middleColor;
  final Color middleBgColor;

  final double titleFontSize;
  final double contentFontSize;
  final double buttonFontSize;
  final Function(int) onTap;

  const DialogDispatch({
    Key key,
    this.title,
    this.listContent,
    this.middleTitle,
    this.titleColor = _titleColor,
    this.contentColor = _contentColor,
    this.middleColor = _middlecolor,
    this.middleBgColor = _middleBgColor,
    this.titleFontSize = _titleSize,
    this.contentFontSize = _contentSize,
    this.buttonFontSize = _buttonSize,
    this.onTap,
  }) : super(key: key);

  @override
  _DialogDispatchState createState() => _DialogDispatchState();
}

class _DialogDispatchState extends State<DialogDispatch> {
  @override
  Widget build(BuildContext context) {
    List list = widget.listContent.split('#*');
    return Padding(
      padding: EdgeInsets.all(0.0),
      child: Column(
        textDirection: TextDirection.ltr,
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: <Widget>[
          Container(
            padding: EdgeInsets.symmetric(horizontal: 48),
            child: Container(
              decoration: BoxDecoration(
                  color: Colors.white,
                  borderRadius: BorderRadius.all(Radius.circular(5))),
              child: Column(
                children: <Widget>[
                  SizedBox(
                    height: 24,
                  ),
                  Text(
                    widget.title,
                    style: TextStyle(
                        fontSize: widget.titleFontSize,
                        fontWeight: FontWeight.w500,
                        color: widget.titleColor,
                        decoration: TextDecoration.none),
                  ),
                  SizedBox(
                    height: 20,
                  ),
                  contentWidget(S.of(context).dispatch_detail_selftaking_content1,list.length>0?list[0]:''),
                  contentWidget(S.of(context).dispatch_detail_express_content1, list.length>1?list[1]:''),
                  contentWidget(S.of(context).dispatch_detail_express_content2, list.length>2?list[2]:''),
                  contentWidget(S.of(context).dispatch_detail_express_content3, list.length>3?list[3]:''),
                  contentWidget(S.of(context).dispatch_detail_express_content4, list.length>4?list[4]:''),
                  contentWidget(S.of(context).dispatch_detail_express_content5, list.length>5?list[5]:''),
                  contentWidget(S.of(context).dispatch_detail_express_content6, list.length>6?list[6]:''),
                  SizedBox(
                    height: 26,
                  ),
                  Container(
                    height: 36,
                    margin:
                    EdgeInsets.only(left: 65, right: 65, bottom: 26),
                    child: new SizedBox.expand(
                      child: FlatButton(
                          highlightColor: _translucentColor,
                          splashColor: _translucentColor,
                          color: widget.middleBgColor,
                          child: Text(
                            widget.middleTitle,
                            style: TextStyle(
                                fontSize: widget.buttonFontSize,
                                fontWeight: FontWeight.w500,
                                color: widget.middleColor,
                                decoration: TextDecoration.none),
                          ),
                          shape: new RoundedRectangleBorder(
                              borderRadius:
                              new BorderRadius.circular(36.0)),
                          onPressed: () {
                            Navigator.pop(context);
                            widget.onTap(2);
                          }),
                    ),
                  ),
                ],
              ),
            ),
          )
        ],
      ),
    );
  }

  Widget contentWidget(String title,String content){
    if(content == null || content.length==0){
      return Container();
    }
    return Container(
      padding: EdgeInsets.only(left: Values.d_26,right: Values.d_26,top: Values.d_2,bottom: Values.d_2),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Text(
            title,
            textAlign: TextAlign.center,
            style: TextStyle(
                fontSize: widget.contentFontSize,
                fontWeight: FontWeight.w400,
                color: Values.of(context).c_black_front_99,
                decoration: TextDecoration.none),
          ),
          Container(
            child: Expanded(child:
            Text(
              content,
              textAlign: TextAlign.left,
              style: TextStyle(
                  fontSize: widget.contentFontSize,
                  fontWeight: FontWeight.w400,
                  color: widget.contentColor,
                  decoration: TextDecoration.none),
              maxLines: 4,
            ),),
          )
        ],
      ),
    );
  }

}