import 'package:flutter/material.dart';
import '../refresh/load_more.dart';

class GridItem {
  Widget build(BuildContext context, int index) {
    return null;
  }
}

typedef GridItemAction = Function(GridItem item, int index);

class GridModel {
  List<GridItem> _items = [];
  Widget _supplementItem; //额外的 list 选项

  GridModel({
    this.columns = 2,
    this.mainAxisSpacing = 0,
    this.crossAxisSpacing = 0,
    this.childAspectRatio = 1,
    this.loadMoreController,
    this.padding,
  });

  final EdgeInsets padding;

  final int columns;

  final double mainAxisSpacing;

  final double crossAxisSpacing;

  final double childAspectRatio;

  LoadMoreController loadMoreController;

  GridItemAction _action;

  void setItemAction(GridItemAction action) {
    _action = action;
  }

  void clear() {
    _items.clear();
  }

  bool isEmpty() {
    return _items.length == 0;
  }

  void reset(bool isRefresh) {
    if (isRefresh) {
      clear();
    }
  }

  List<GridItem> items() {
    return _items;
  }

  void addSupplementItem(Widget supplementItem) {
    _supplementItem = supplementItem;
  }

  void addAll(List<GridItem> items) {
    _items.addAll(items);
  }

  void add(GridItem item) {
    if (item != null) {
      _items.add(item);
    }
  }

  GridItem itemAt(int index) {
    if (index >= 0 && index < items().length) {
      return items()[index];
    }
    return null;
  }

  Widget build(BuildContext context) {
    ScrollController scrollController;

    if (loadMoreController != null) {
      _supplementItem = loadMoreController.indicator();
      scrollController = loadMoreController.controller();
    }

    return GridView.builder(
      controller: scrollController,
      itemCount: _supplementItem == null ? items().length : items().length + 1,
      gridDelegate: new SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: columns,
          mainAxisSpacing: mainAxisSpacing,
          crossAxisSpacing: crossAxisSpacing,
          childAspectRatio: childAspectRatio),
      padding: padding ?? EdgeInsets.all(0),
      itemBuilder: (BuildContext context, int index) {
        if (index == items().length) {
          return _supplementItem;
        }
        GridItem item = itemAt(index);
        if (item != null) {
          Widget w = item.build(context, index);
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
        return null;
      },
    );
  }
}
