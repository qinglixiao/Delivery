import 'package:flutter/material.dart';
import '../refresh/load_more.dart';

class ListItem {
  Widget build(BuildContext ctxt, int index) {
    return null;
  }
}

typedef ListItemAction = Function(ListItem item, int index);

class ListItemActions {
  void add(ListItem item, ListItemAction action) {}
}

class ListModel {
  ListModel({this.loadMoreController});

  LoadMoreController loadMoreController;

  List<ListItem> items = [];

  ListItemAction _action;

  void setItemAction(ListItemAction action) {
    _action = action;
  }

  void clear() {
    items.clear();
  }

  void add(ListItem item) {
    items.add(item);
  }

  void addItems(List<ListItem> items) {
    this.items.addAll(items);
  }

  void reset(bool isRefresh) {
    if (isRefresh) {
      items.clear();
    }
  }

  bool isEmpty() {
    return items.length == 0;
  }

  Widget build(BuildContext context) {
    Widget indicator;
    ScrollController scrollController;

    if (loadMoreController !=null) {
      indicator = loadMoreController.indicator();
      scrollController =loadMoreController.controller(); 
    } 

    var widget = new ListView.builder(
      itemCount: indicator != null ? items.length + 1 : items.length,
      itemBuilder: (BuildContext ctxt, int index) {
        if (items.length == index) {
          return indicator;
        } else {
          ListItem item = items[index];
          Widget w = item.build(ctxt, index);
          if (_action != null) {
            return GestureDetector(
              onTap: () {
                if (_action != null) {
                  _action(item, index);
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
    return widget;
  }
}
