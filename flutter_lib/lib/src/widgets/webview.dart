import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:webview_flutter/webview_flutter.dart';

///webview
///
///create by lx
///date 2020/7/22
///

class IAgentWebPage extends StatefulWidget {
  IAgentWebPage();

  @override
  State<StatefulWidget> createState() {
    return _IAgentWebPageState();
  }
}

class _IAgentWebPageState extends State<IAgentWebPage> with PageBridge {
  WebParams _params;

  final Completer<WebViewController> _controller =
      Completer<WebViewController>();

  WebViewController _webViewController;

  _init() async {
    getInitArg(context).then((params) {
      _params = params;
    });
  }

  ///js回调
  JavascriptChannel _flutterInvokeHandler() {
    return JavascriptChannel(
        name: 'method',
        onMessageReceived: (JavascriptMessage message) {
          Logger.print(message.message);
        });
  }

  ///request拦截
  FutureOr<NavigationDecision> _pageRequestIntercept(
      NavigationRequest request) {
    Logger.print('page to $request');

    if (request.url.contains("/payplatform/callback/yeepay/")) {
      //拦截此url
      open(_params.route);
      return NavigationDecision.prevent;
    }
    else if (request.url.contains("//www.ienglish.cn/app/open_person")) {
      //拦截此url
      open(_params.route,argument:{'status':true,'title':_params.routeParams['title'],'content':_params.routeParams['content'],'router':_params.routeParams['router'],'action':_params.routeParams['action']});
      return NavigationDecision.prevent;
    }
    else if (request.url.contains("//www.ienglish.cn/app/open_company")) {
      //拦截此url
      open(_params.route,argument:{'status':true,'title':_params.routeParams['title'],'content':_params.routeParams['content'],'router':_params.routeParams['router'],'action':_params.routeParams['action']});
      return NavigationDecision.prevent;
    }
    else if(request.url.contains("//www.ienglish.cn/app/pay_pwd_modify")){
      //拦截此url
      if(request.url.contains("state=0")) {
        open(_params.route,argument:{'status':true,'title':_params.routeParams['title'],'content':_params.routeParams['content'],'router':_params.routeParams['router'],'action':_params.routeParams['action']});
      }
      else{
        pop();
      }
      return NavigationDecision.prevent;
    }
    else if(request.url.contains("//www.ienglish.cn/app/reset_pay_pwd")){
      if(request.url.contains("state=0")){
        open(_params.route,argument:{'status':true,'title':_params.routeParams['title'],'content':_params.routeParams['content'],'router':_params.routeParams['router'],'action':_params.routeParams['action']});
      }
      else{
        pop();
      }
      return NavigationDecision.prevent;
    }

    return NavigationDecision.navigate;
  }

  _onPageStarted(String url) {
    Logger.print("start url:$url");
  }

  _onPageFinished(String url) {
    Logger.print("finish url:$url");
  }

  bool _isReady() {
    return _controller.isCompleted;
  }

  exeJs(String script) async {
    while (!_isReady()) {
      _webViewController = await _controller.future;
    }

    _webViewController.evaluateJavascript("javascriptString");
  }

  @override
  Widget build(BuildContext context) {
    _init();
    WebView _webView = WebView(
      javascriptMode: JavascriptMode.unrestricted,
      onWebViewCreated: (WebViewController webViewController) {
        _controller.complete(webViewController);
      },
      javascriptChannels: [_flutterInvokeHandler()].toSet(),
      navigationDelegate: _pageRequestIntercept,
      gestureNavigationEnabled: true,
      onPageStarted: _onPageStarted,
      onPageFinished: _onPageFinished,
    );

    return RootPageWidget(
        onWillBack: () async {
          var canBack = await _webViewController.canGoBack();
          if (canBack) {
            _onBack();
          }
          return !canBack;
        },
        body: FutureBuilder<WebViewController>(
            future: _controller.future,
            builder:
                (BuildContext context, AsyncSnapshot<WebViewController> c) {
              if (c.hasData) {
                _webViewController = c.data;
                Future(() {
                  c.data.loadUrl(_params.url);
                });
              }

              return Scaffold(
                appBar: IsAppBar(
                  leftOnTap: _onBack,
                  title: _params?.title ?? "",
                ),
                body: _webView,
              );
            }));
  }

  _onBack() async {
    if (_isReady()) {
      var canBack = await _webViewController.canGoBack();
      canBack ? _webViewController.goBack() : pop(data: _params);
      return !canBack;
    }
    return true;
  }
}

class WebParams {
  String url;
  String title;
  String route;
  Map routeParams;

  WebParams({this.url, this.title, this.route,this.routeParams});
}
