import 'package:flutter/material.dart';

///页面在出错情况下展示的页面
class AsErrorWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Text(
        "出现错误了哦~",
      ),
    );
  }
}

///页面加载数据时展示的loadding窗
class LoaddingWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => _LoaddingState();
}

class _LoaddingState extends State<LoaddingWidget> {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: CircularProgressIndicator(),
    );
  }
}
