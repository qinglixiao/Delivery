import 'dart:io';

import 'package:flutter/cupertino.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:webview_cookie_manager/webview_cookie_manager.dart';

///
///create by lx
///date 2020/9/22
///

class WebViewCookiePage extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<WebViewCookiePage> {
  final cookieManager = WebviewCookieManager();

  final String _url = 'https://www.baidu.com';
  final String cookieValue = 'some-cookie-value';
  final String domain = 'baidu.com';
  final String cookieName = 'some_cookie_name';

  @override
  void initState() {
    super.initState();
    cookieManager.clearCookies();

    cookieManager.setCookies([
      Cookie('cookieName', 'cookieValue')
        ..domain = 'youtube.com'
        ..expires = DateTime.now().add(Duration(days: 10))
        ..httpOnly = false
    ]);
  }

  @override
  Widget build(BuildContext context) {
    return RootPageWidget(
      appBar: IsAppBar(
        title: "baidu",
      ),
      body: IAgentWebPage(
        params: WebParams(url: _url, showTitle: false),
      ),
    );
  }
}
