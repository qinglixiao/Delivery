/*
 * 原生提供给flutter的基本方法
 * iOS/Android均需要严格按照本spec实现
 */

class STBridgeMethodSpec {
  String methodName() {
    return null;
  }

  Map make() {
    return null;
  }
}

/*
 * NativeNetworkSpec
 * 调用原生的http 请求
 */
class NativeNetworkSpec extends STBridgeMethodSpec {
  NativeNetworkSpec({this.url, this.httpMethod, this.parameters});

  String url;
  String httpMethod;
  Map parameters;

  @override
  Map make() {
    Map m = {
      'url': url,
      'method': httpMethod,
    };

    if (parameters != null) {
      m['parameters'] = parameters;
    }
    return m;
  }

  @override
  String methodName() {
    return "network";
  }
}

enum ActionButtonType { share, favor, custom }

/*
 * MethodCallbackSpec
 * 原生对flutter的回调
 */
class MethodCallbackSpec extends STBridgeMethodSpec {
  MethodCallbackSpec({this.method, this.parameters});

  /*
   * 回调时需要带回来的参数
   * 调用方，也可以传其他参数
   * optional
   */
  Object parameters;

  // 回调方法名，需要注册到CYBridge对像中
  String method;

  @override
  Map make() {
    Map m = {'method': method};
    if (parameters != null) {
      m['parameters'] = parameters;
    }
    return m;
  }
}

class MethodCallSpec extends STBridgeMethodSpec {
  MethodCallSpec({this.method, this.parameters});

  /*
   * 回调时需要带回来的参数
   * 调用方，也可以传其他参数
   * optional
   */
  Object parameters;

  // 回调方法名，需要注册到CYBridge对像中
  String method;

  @override
  Map make() {
    Map m = {'method': method};
    if (parameters != null) {
      m['parameters'] = parameters;
    }
    return m;
  }

  @override
  String methodName() {
    return "call_native";
  }
}

/*
 * ActionButtonSpec
 * 导航栏按钮定义
 * 1. 样式
 * 2. 回调
 */
class ActionButtonSpec extends STBridgeMethodSpec {
  ActionButtonSpec(
      {this.type = ActionButtonType.custom,
      this.title,
      this.icon,
      this.callback,
      this.data,
      this.style});

  /*
   * appearance
   * type: 预定义样式
   * title & icon: 自定义样式
   */
  final ActionButtonType type;

  final String style; // optional for button style: title color, etc.

  // title & icon 用于自定义ActionButton样式
  final String title; // optional
  final String icon; // optional

  final Map data;

  // button的点击callback，可以为空，这时type对应的button应该已有预设的功能
  MethodCallbackSpec callback;

  @override
  Map make() {
    Map m = {
      'type': type.toString(),
    };
    if (title != null) {
      m['title'] = title;
    }
    if (icon != null) {
      m['icon'] = icon;
    }
    if (data != null) {
      m['data'] = data;
    }
    if (style != null) {
      m['style'] = style;
    }
    if (callback != null) {
      m['callback'] = callback.make();
    }
    return m;
  }
}

/*
 * OpenFlutterPageSpec
 * 打开一个flutter页面
 */
class OpenFlutterPageSpec extends STBridgeMethodSpec {
  OpenFlutterPageSpec(
      {this.title,
      this.route,
      this.rightBarButtons,
      this.parameters,
      this.callback,
      this.replaceTop,
      this.showNavigationBar});

  @override
  Map make() {
    Map m = {
      'route': route,
      'present': present,
      'replace_top': replaceTop,
    };
    if (title != null) {
      m['title'] = title;
    }
    if (parameters != null) {
      m['parameters'] = parameters;
    }

    if (callback != null) {
      m['callback'] = callback.make();
    }

    if (rightBarButtons != null) {
      List<Map> l = rightBarButtons.map((f) {
        return f.make();
      }).toList();

      m['right_bar_buttons'] = l;
    }
    if (showNavigationBar != null) {
      m["show_navigation_bar"] = showNavigationBar;
    }

    return m;
  }

  @override
  String methodName() {
    return "open_flutter";
  }

  // 页面的title
  String title;

  // 页面route名称，flutte依此打开页面
  String route;

  // [ActionButtonSpec] 导航栏右上角的按钮
  List<ActionButtonSpec> rightBarButtons;

  /*
   * 参数，传给你打开的flutter页面，目前只能通过channel发送？能跟route一传递吗？
   */
  Map parameters;

  // 页面中回调flutter方法, optional，暂时没用？
  MethodCallbackSpec callback;

  bool present = false; // for iOS

  bool showNavigationBar;
  bool replaceTop = false; // 替换当前页面打开
}

/*
 * UpdateFlutterPageSpec
 */
class UpdateFlutterPageSpec extends OpenFlutterPageSpec {
  UpdateFlutterPageSpec(
      {String title,
      List<ActionButtonSpec> rightBarButtons,
      bool showNavigationBar})
      : super(
            title: title,
            rightBarButtons: rightBarButtons,
            showNavigationBar: showNavigationBar);

  @override
  String methodName() {
    return "update_flutter_page";
  }
}

/*
 * EventSpec
 * Android EventBus事件
 * iOS NSNotificationCenter事件
 */
class EventSpec extends STBridgeMethodSpec {
  EventSpec(this.name, this.data, this.error, {this.source});

  @override
  String methodName() {
    return "flutter_event";
  }

  @override
  Map make() {
    Map m = {'name': name};
    if (data != null) {
      m['data'] = data;
    }
    if (source != null) {
      m['source'] = source;
    }
    if (error != null) {
      m['error'] = error;
    }
    return m;
  }

  String name;

  String source;

  String error;

  Map data;
}

class EventObserveSpec extends STBridgeMethodSpec {
  EventObserveSpec(this.name, this.callback,
      {this.source, this.unsubscribe = false});

  @override
  String methodName() {
    return "flutter_observe";
  }

  @override
  Map make() {
    Map m = {'name': name};

    if (callback != null) {
      m['callback'] = callback.make();
    }
    if (source != null) {
      m['source'] = source;
    }
    return m;
  }

  String name;
  String source;
  MethodCallbackSpec callback;
  bool unsubscribe = false;
}

/*
 * OpenNativePageSpec
 * 打开原生页面
 */
class OpenNativePageSpec extends STBridgeMethodSpec {
  OpenNativePageSpec({
    this.route,
    this.parameters,
    this.present,
    this.callback,
    this.requestCode,
  });

  @override
  Map make() {
    Map m = {'route': route, 'present': present};

    if (parameters != null) {
      m['parameters'] = parameters;
    }

    if (callback != null) {
      m['callback'] = callback.make();
    }

    if (requestCode != null) {
      m["requestCode"] = requestCode;
    }

    return m;
  }

  @override
  String methodName() {
    return "open_native";
  }

  // 页面route名称，依此打开原生页面，iOS/Android需统一
  String route;

  // 传给原生页面的参数
  Map parameters;

  // 页面中回调flutter方法, optional，暂时没用?
  MethodCallbackSpec callback;

  bool present = false; // for iOS

  int requestCode = 1; //for android
}

class GetInitArgumentsSpec extends STBridgeMethodSpec {
  @override
  String methodName() {
    return "get_init_args";
  }
}

/*
 * 关闭当前flutter原生容器
 */
class CloseFlutterPageSpec extends STBridgeMethodSpec {
  String result;
  Map data;

  CloseFlutterPageSpec({this.result, this.data});

  @override
  String methodName() {
    return "close";
  }

  @override
  Map make() {
    return {"result": result, "data": data};
  }
}

/*
 * 调用原生pop方法，一般在一个原生容器承载多个flutter页面栈时调用
 */
class PopFlutterPageSpec extends STBridgeMethodSpec {
  Map data;

  PopFlutterPageSpec({this.data});

  @override
  String methodName() {
    return "pop";
  }

  @override
  Map make() {
    return data;
  }
}

Map mergeOptions(Map parameters, Map p) {
  if (parameters == null) return p;
  if (p != null) {
    parameters.addAll(p);
  }
  return parameters;
}
