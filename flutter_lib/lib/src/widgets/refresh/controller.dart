import 'package:flutter/material.dart';
import 'refresh_indicator.dart';
import 'header.dart';

class RefreshController {
  RefreshController(this.header);

  void refresh() {
      WidgetsBinding.instance
        .addPostFrameCallback((_) => refreshIndicatorKey.currentState.show());
  }

  final RefreshHeader header;

  final GlobalKey<PullToRefreshState> refreshIndicatorKey =
      new GlobalKey<PullToRefreshState>();
}