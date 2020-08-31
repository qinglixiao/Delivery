import 'package:flutter/material.dart';
import 'package:flutter_lib/src/base/view_model.dart';

///用于保存全局信息
///create by lx
///date 2020/6/8
class StateProvider<T> extends StatefulWidget {
  StateProvider({
    Key key,
    @required this.child,
    @required this.data,
  }) : super(key: key);

  final Widget child;
  final T data;

  @override
  _StateProviderState<T> createState() => _StateProviderState<T>();

  static T of<T>(BuildContext context) {
    _StateProviderInherited<T> provider = context
        .getElementForInheritedWidgetOfExactType<_StateProviderInherited<T>>()
        .widget;
    return provider?.data;
  }
}

class _StateProviderState<T> extends State<StateProvider<T>> {
  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return new _StateProviderInherited<T>(
      data: widget.data,
      child: widget.child,
    );
  }
}

class _StateProviderInherited<T> extends InheritedWidget {
  _StateProviderInherited({
    Key key,
    @required Widget child,
    @required this.data,
  }) : super(key: key, child: child);

  final T data;

  @override
  bool updateShouldNotify(_StateProviderInherited oldWidget) => true;
}
