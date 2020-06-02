import 'package:flutter/material.dart';
import 'package:flutter_lib/src/base/view_model.dart';

class StateProvider<T extends BaseViewModel> extends StatefulWidget {
  StateProvider({
    Key key,
    @required this.child,
    @required this.viewModel,
  }) : super(key: key);

  final Widget child;
  final T viewModel;

  @override
  _StateProviderState<T> createState() => _StateProviderState<T>();

  static T of<T extends BaseViewModel>(BuildContext context) {
    _StateProviderInherited<T> provider = context
        .getElementForInheritedWidgetOfExactType<_StateProviderInherited<T>>()
        .widget;
    return provider?.viewModel;
  }
}

class _StateProviderState<T extends BaseViewModel>
    extends State<StateProvider<T>> {
  @override
  void dispose() {
    widget.viewModel?.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return new _StateProviderInherited<T>(
      viewModel: widget.viewModel,
      child: widget.child,
    );
  }
}

class _StateProviderInherited<T> extends InheritedWidget {
  _StateProviderInherited({
    Key key,
    @required Widget child,
    @required this.viewModel,
  }) : super(key: key, child: child);

  final T viewModel;

  @override
  bool updateShouldNotify(_StateProviderInherited oldWidget) => true;
}
