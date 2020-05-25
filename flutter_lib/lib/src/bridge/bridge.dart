import 'package:flutter/services.dart';
import 'dart:async';
import 'package:flutter/material.dart';
import 'spec.dart';
import 'defs.dart';

/*
 * flutter向原生提供的方法
 * 1. 通用方法
 * 2. 页面相关的方法，包括回调
 */
class CYFlutterMethodManager {
  Map methods = Map();

  Map commonMethods = Map();

  bool register(String name, CYCallBackFn function) {
    print('flutter method manager, register $name, $function');
    print("methods  ${methods.toString()}");
    if (methods.containsKey(name)) {
      return false;
    }

    methods[name] = function;
    print("methods  ${methods.toString()}");
    return true;
  }

  void remove(String name) {
    print('flutter method manager, remove $name');
    methods.remove(name);
  }

  void registerCommandMethods(String name, CYCallBackFn fn) {
    print("flutter method manager, register common: $name");
    print("fn = ${fn}");
    commonMethods[name] = fn;
  }

  Function get(String name) {
    Function fn;
    if (methods.containsKey(name)) {
      fn = methods[name];
    } else {
      fn = commonMethods[name];
    }

    print("flutter method manager, get: $name, $fn");

    return fn;
  }
}

/*
 * 1. 调用原生方法
 * 2. 对原生端，提供flutter方法
 */
class CYBridge {
  final _methodChannel = new MethodChannel(
      'flutter.chunyu.com.channel.method', const JSONMethodCodec());

  CYFlutterMethodManager _methodManager = new CYFlutterMethodManager();

  BuildContext _buildContext;

  CYBridge._() {
    print("init bridge");

    _methodChannel.setMethodCallHandler((MethodCall call) async {
      print("method call handler: ${call.method}, ${call.arguments}");

      Function fn = _methodManager.get(call.method);

      print("fn = ${fn}");

      if (fn == null) {
        throw MissingPluginException();
      }

      Object data;
      String error;

      if (call.arguments != null) {
        if (call.arguments is Map) {
          // 如果传了参数，就按这个格式
          error = call.arguments["error"];
          data = call.arguments["data"];

          if (data != null) {
            print('type: ${data.runtimeType}');
          }
        }

        if (data == null && error == null) {
          // 兼容，比如要监听一个老的通知(EventBus or NSNotificationCenter)
          data = call.arguments;
        }
      }

      print("call method handler: ${call.method}, $data, $error");

      return fn(data, error);
    });

    _methodManager.registerCommandMethods("pop", (Object data, String error) {
      return Navigator.of(_buildContext).maybePop().then((r) {
        if (r) {
          return 'completed';
        } else {
          return '';
        }
      });
    });
  }

  static CYBridge _instance;

  static CYBridge _getInstance() {
    if (_instance == null) {
      _instance = CYBridge._();
    }

    return _instance;
  }

  factory CYBridge() => _getInstance();

  void registerMethod(String name, CYCallBackFn fn) {
    _methodManager.register(name, fn);
  }

  void unregisterMethod(String name) {
    _methodManager.remove(name);
  }

  void setContext(BuildContext context) {
    _buildContext = context;
  }

  Future callMethod(String name, {Object parameters}) {
    return callNative(MethodCallSpec(method: name, parameters: parameters));
  }

  Future callNative(CYBridgeMethodSpec methodSpec) {
    return _callNative(null, methodSpec);
  }

  Future _callNative(
      MethodChannel channel, CYBridgeMethodSpec methodSpec) async {
    Map data = methodSpec.make();
    String name = methodSpec.methodName();

    print("call native: $name , $data");

    Object r;
    if (channel == null) {
      r = await _methodChannel.invokeMethod(name, data);
    } else {
      r = await channel.invokeMethod(name, data);
    }

    print("call native(return): $name, $r");

    return r;
  }

  Future request(String method, String url, [Map p]) async {
    var spec = NativeNetworkSpec(url: url, httpMethod: method, parameters: p);

    return _callNative(null, spec).then((data) {
      if (data['success']) {
        print("data type:${data['data'].runtimeType}");
        return data['data'];
      } else {
        throw data['error'];
      }
    });
  }

  Future openFlutter(String path,
      {Map parameters,
      String title,
      List rightBarButtons,
      bool replaceTop}) async {
    var spec = OpenFlutterPageSpec(
        route: path,
        parameters: parameters,
        title: title,
        replaceTop: replaceTop,
        rightBarButtons: rightBarButtons);
    return _callNative(null, spec);
  }

  Future openNative(String path, [Map parameters]) async {
    var spec = OpenNativePageSpec(route: path, parameters: parameters);
    return _callNative(null, spec);
  }

  Future emitEvent(String name, {Map data, String error, String source}) {
    var spec = EventSpec(name, data, error, source: source);
    return _callNative(null, spec);
  }

  String subscribeEvent(String name, CYCallBackFn callback, {String source}) {
    MethodCallbackSpec callbackSpec = createCallback(name, callback);

    var methodSpec = EventObserveSpec(name, callbackSpec, source: source);

    _callNative(null, methodSpec);

    return callbackSpec.method;
  }

  Future unsubscribe(String name, String methodName, {String source}) {
    unregisterMethod(methodName);

    var callbackSpec = MethodCallbackSpec(method: methodName, parameters: null);

    var methodSpec =
        EventObserveSpec(name, callbackSpec, source: source, unsubscribe: true);

    return _callNative(null, methodSpec);
  }

  Future<Map> getInitArgs() async {
    Map r = await _callNative(null, GetInitArgumentsSpec());

    return r['data'];
  }

  Future updatePage(
      {String title,
      List<ActionButtonSpec> rightBarButtons,
      bool showNavigationBar}) {
    return _callNative(
        null,
        UpdateFlutterPageSpec(
            title: title,
            rightBarButtons: rightBarButtons,
            showNavigationBar: showNavigationBar));
  }

  MethodCallbackSpec createCallback(String name, CYCallBackFn fn) {
    var timestamp = new DateTime.now().millisecondsSinceEpoch;

    String methodName = name + '_$timestamp';

    registerMethod(methodName, fn);

    var callbackSpec = MethodCallbackSpec(method: methodName, parameters: null);

    return callbackSpec;
  }
}
