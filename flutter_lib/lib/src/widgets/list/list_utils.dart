import 'dart:core';

import 'package:flutter/material.dart';
import '../refresh/load_more.dart';

class SListView<T> {
  var _buildItem;
  var _itemAction;
  var _moreController;

  SListView(BuildItemView buildItemView,
      {ListItemAction itemAction, LoadMoreController moreController}) {
    _buildItem = buildItemView;
    _moreController = moreController;
    _itemAction = itemAction;
  }

  Widget build(BuildContext context, List items) {
    Widget indicator;
    ScrollController scrollController;

    if (_moreController != null) {
      indicator = _moreController.indicator();
      scrollController = _moreController.controller();
    }

    return ListView.builder(
      itemCount: indicator != null ? items.length + 1 : items.length,
      itemBuilder: (BuildContext context, int index) {
        if (items.length == index) {
          return indicator;
        } else {
          Widget w = _buildItem(context, items[index]);
          if (_itemAction != null) {
            return GestureDetector(
              onTap: () {
                if (_itemAction != null) {
                  _itemAction(w, items[index]);
                }
              },
              child: w,
            );
          }
          return w;
        }
      },
      controller: scrollController,
    );
  }
}

typedef BuildItemView = Widget Function(BuildContext context, Object itemData);
typedef ListItemAction = Function(Widget widget, Object itemData);
