import 'package:flutter/material.dart';

typedef LoadMoreCallback = void Function();

class LoadMoreController {
    ScrollController _scrollController = new ScrollController();

    bool hasMore = false;

    LoadMoreController(LoadMoreCallback callback) {
      _scrollController.addListener(() {
        if (_scrollController.position.pixels == _scrollController.position.maxScrollExtent) {
          if (hasMore) {
            print("loadmore");
            callback();
          } else {
            print("has no more!!");
          }
        }
      });
    }

    void dispose() {
      _scrollController.dispose();
    }

    ScrollController controller() {
      return _scrollController;
    }

    Widget indicator() {
      return null;
    }
}

class SimpleLoadMoreController extends LoadMoreController {
  SimpleLoadMoreController(LoadMoreCallback callback) : super(callback);

  @override
  Widget indicator() {
    return new Padding(
      padding: const EdgeInsets.all(8.0),
      child: new Center(
        child: new Opacity(
          opacity: hasMore ? 1.0 : 0.0,
          child: new CircularProgressIndicator(),
        ),
      ),
    );
  }
}
