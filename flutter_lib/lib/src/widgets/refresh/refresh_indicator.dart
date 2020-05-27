import 'dart:async';

import 'package:flutter/material.dart';

import 'defs.dart';

import 'header.dart';

class PullToRefresh extends StatefulWidget {
  const PullToRefresh({
    Key key,
    @required this.child,
    @required this.onRefresh,
    this.positionController,
    this.refreshHeader,
    this.scrollController,
    this.notificationPredicate = defaultScrollNotificationPredicate,
  })  : assert(child != null),
        assert(onRefresh != null),
        assert(notificationPredicate != null),
        super(key: key);

  /// The widget below this widget in the tree.
  ///
  /// The progress indicator will be stacked on top of this child. The indicator
  /// will appear when child's Scrollable descendant is over-scrolled.
  ///
  /// Typically a [ListView] or [CustomScrollView].
  final ScrollView child;

  /// A function that's called when the user has dragged the progress indicator
  /// far enough to demonstrate that they want the app to refresh. The returned
  /// [Future] must complete when the refresh operation is finished.
  final RefreshCallback onRefresh;

  /// A check that specifies whether a [ScrollNotification] should be
  /// handled by this widget.
  ///
  /// By default, checks whether `notification.depth == 0`. Set it to something
  /// else for more complicated layouts.
  final ScrollNotificationPredicate notificationPredicate;

  /// Controls the [ScrollView] child.
  /// [null] by default.
  final ScrollController scrollController;

  final RefreshHeader refreshHeader;

  final PositionController positionController;

  @override
  PullToRefreshState createState() => PullToRefreshState();
}

class PullToRefreshState extends State<PullToRefresh>
    with TickerProviderStateMixin<PullToRefresh> {

  PullToRefreshMode _mode;
  Future<void> _pendingRefreshFuture;
  bool _isIndicatorAtTop;
  double _dragOffset;

  RefreshHeader refreshHeader;

  PositionController positionController;

  @override
  void initState() {
    super.initState();

    positionController = widget.positionController == null ? PositionController() : widget.positionController;

    positionController.setup(tp: this);

    refreshHeader = widget.refreshHeader == null ? RefreshHeader() : widget.refreshHeader;

    refreshHeader.setup(positionController: positionController);
  }

  @override
  void didChangeDependencies() {

    
    super.didChangeDependencies();
  }

  @override
  void dispose() {
   
    positionController.dispose();

    refreshHeader.dispose();

    super.dispose();
  }

  /*
   * drag的时候内容的offset是向下的才视为一次下拉刷新
   * 先上滚再下滚不会触发
   */
  bool _handleScrollNotification(ScrollNotification notification) {
    // print('_handleScrollNotification: $notification, ${notification.metrics.extentBefore}');

    if (!widget.notificationPredicate(notification)) return false;

    if (notification is ScrollStartNotification &&
        notification.metrics.extentBefore == 0.0 &&
        _mode == null &&
        _start(notification.metrics.axisDirection)) {
      setState(() {
        _mode = PullToRefreshMode.drag;
      });
      return false;
    }

    bool indicatorAtTopNow;
    switch (notification.metrics.axisDirection) {
      case AxisDirection.down:
        indicatorAtTopNow = true;
        break;
      case AxisDirection.up:
        indicatorAtTopNow = false;
        break;
      case AxisDirection.left:
      case AxisDirection.right:
        indicatorAtTopNow = null;
        break;
    }
    if (indicatorAtTopNow != _isIndicatorAtTop) {
      if (_mode == PullToRefreshMode.drag ||
          _mode == PullToRefreshMode.armed)
        _dismiss(PullToRefreshMode.canceled, "there");
    } else if (notification is ScrollUpdateNotification) {
      if (_mode == PullToRefreshMode.drag ||
          _mode == PullToRefreshMode.armed) {
        if (notification.metrics.extentBefore > 0.0) {
          _dismiss(PullToRefreshMode.canceled, "here");
        } else {
          _dragOffset -= notification.scrollDelta;
          _checkDragOffset(notification.metrics.viewportDimension);
        }
      }

      if (_mode == PullToRefreshMode.armed &&
          notification.dragDetails == null) {
        // On iOS start the refresh when the Scrollable bounces back from the
        // OverScroll (ScrollNotification indicating this don't have dragDetails
        // because the scroll activity is not directly triggered by a drag).
        _show();
      }
    } else if (notification is OverscrollNotification) {
      if (_mode == PullToRefreshMode.drag ||
          _mode == PullToRefreshMode.armed) {
        _dragOffset -= notification.overscroll / 2.0;
        _checkDragOffset(notification.metrics.viewportDimension);
      }
    } else if (notification is ScrollEndNotification) {

      switch (_mode) {
        case PullToRefreshMode.armed:
          _show();
          break;
        case PullToRefreshMode.drag:
          _dismiss(PullToRefreshMode.canceled, "ended");
          break;
        default:
          // do nothing
          break;
      }
    }

    return false;
  }

  bool _handleGlowNotification(OverscrollIndicatorNotification notification) {
    if (notification.depth != 0 || !notification.leading) return false;
    if (_mode == PullToRefreshMode.drag) {
      notification.disallowGlow();
      return true;
    }
    return false;
  }

  // Stop showing the progress indicator.
  Future<void> _dismiss(PullToRefreshMode newMode, String source) async {
    print("_dismiss: $newMode, $source");

    await Future<void>.value();
    // This can only be called from _show() when refreshing and
    // _handleScrollNotification in response to a ScrollEndNotification or
    // direction change.
    assert(newMode == PullToRefreshMode.canceled ||
        newMode == PullToRefreshMode.done);
    setState(() {
      _mode = newMode;
    });

    refreshHeader.update(newMode, 0);

    switch (_mode) {
      case PullToRefreshMode.done:
        positionController.dismiss(); 

        refreshHeader.dismiss();
        
        break;

      case PullToRefreshMode.canceled:

        positionController.cancel();        

        break;
      default:
        assert(false);
    }

    if (mounted && _mode == newMode) {
      print("reset");

      _dragOffset = null;
      _isIndicatorAtTop = null;
      setState(() {
        _mode = null;
      });
    }
  }

  bool _start(AxisDirection direction) {
    assert(_mode == null);
    assert(_isIndicatorAtTop == null);
    assert(_dragOffset == null);
    switch (direction) {
      case AxisDirection.down:
        _isIndicatorAtTop = true;
        break;
      case AxisDirection.up:
        _isIndicatorAtTop = false;
        break;
      case AxisDirection.left:
      case AxisDirection.right:
        _isIndicatorAtTop = null;
        // we do not support horizontal scroll views.
        return false;
    }
    _dragOffset = 0.0;

    positionController.update(0);
    
    return true;
  }

  void _checkDragOffset(double containerExtent) {
    assert(_mode == PullToRefreshMode.drag ||
        _mode == PullToRefreshMode.armed);

    positionController.update(_dragOffset);

    if (positionController.isArmed()) {
      setState(() {
        _mode = PullToRefreshMode.armed;
      });
    }

    refreshHeader.update(_mode, _dragOffset);
  }

  void _show() {
    assert(_mode != PullToRefreshMode.refresh);
    assert(_mode != PullToRefreshMode.snap);
    final Completer<void> completer = Completer<void>();
    _pendingRefreshFuture = completer.future;
    _mode = PullToRefreshMode.snap;

    print("_show");

    positionController.show();

    refreshHeader.show();

    if (mounted && _mode == PullToRefreshMode.snap) {
      assert(widget.onRefresh != null);

      setState(() {
        // Show the indeterminate progress indicator.
        _mode = PullToRefreshMode.refresh;
      });

      final Future<void> refreshResult = widget.onRefresh();
      print("refresh: $refreshResult");
      assert(() {
        if (refreshResult == null)
          FlutterError.reportError(FlutterErrorDetails(
            exception: FlutterError('The onRefresh callback returned null.\n'
                'The LiquidPullToRefresh onRefresh callback must return a Future.'),
            context: "when calling onRefresh",
            library: 'LiquidPullToRefresh library',
          ));
        return true;
      }());

      if (refreshResult == null) return;

      refreshResult.whenComplete(() {
          if (mounted && _mode == PullToRefreshMode.refresh) {
            completer.complete();

            _dismiss(PullToRefreshMode.done, "complete");
          }
        });
    }
  }

  /// Show the progress indicator and run the refresh callback as if it had
  /// been started interactively. If this method is called while the refresh
  /// callback is running, it quietly does nothing.
  ///
  /// Creating the [LiquidPullToRefresh] with a [GlobalKey<LiquidPullToRefreshState>]
  /// makes it possible to refer to the [LiquidPullToRefreshState].
  ///
  /// The future returned from this method completes when the
  /// [LiquidPullToRefresh.onRefresh] callback's future completes.
  ///
  /// If you await the future returned by this function from a [State], you
  /// should check that the state is still [mounted] before calling [setState].
  ///
  /// When initiated in this manner, the progress indicator is independent of any
  /// actual scroll view. It defaults to showing the indicator at the top. To
  /// show it at the bottom, set `atTop` to false.
  Future<void> show({bool atTop = true}) {
    print("show");
    if (_mode != PullToRefreshMode.refresh &&
        _mode != PullToRefreshMode.snap) {
      if (_mode == null) _start(atTop ? AxisDirection.down : AxisDirection.up);
      _show();
    }
    return _pendingRefreshFuture;
  }

  final GlobalKey _key = GlobalKey();

  @override
  Widget build(BuildContext context) {
    assert(debugCheckHasMaterialLocalizations(context));

    // converting list items to slivers
    List<Widget> slivers =
        // ignore: invalid_use_of_protected_member
        List.from(widget.child.buildSlivers(context), growable: true);

    if (_mode == null) {
      assert(_dragOffset == null);
      assert(_isIndicatorAtTop == null);
      return _buildContent(slivers);
    }

    assert(_dragOffset != null);
    assert(_isIndicatorAtTop != null);

    slivers.insert(
      0,
      PullPadding(positionController: positionController, height: positionController.height).build(context)
    );

    List<Widget> stackChildren = [];

    // 刷新
    refreshHeader.update(_mode, _dragOffset);

    List<Widget> s = refreshHeader.build(context);
    if (s != null) {
      stackChildren.addAll(s);
    }

    stackChildren.add(
      _buildContent(slivers)
    );

    return Stack(
      children: stackChildren,
    );
  }

  Widget _buildContent(List<Widget> slivers) {
    ScrollController controller = widget.scrollController;
    if (controller == null && widget.child.controller != null) {
      controller = widget.child.controller;
    }

    final Widget child = NotificationListener<ScrollNotification>(
      key: _key,
      onNotification: _handleScrollNotification,
      child: NotificationListener<OverscrollIndicatorNotification>(
        onNotification: _handleGlowNotification,
        child: CustomScrollView(
          physics: AlwaysScrollableScrollPhysics(),
          controller: controller,
          slivers: slivers,
        ),
      ),
    );
    return child;
  }
}

class ProgressRingCurve extends Curve {
  @override
  double transform(double t) {
    if (t <= 0.5) {
      return 2 * t;
    } else {
      return 2 * (1 - t);
    }
  }
}