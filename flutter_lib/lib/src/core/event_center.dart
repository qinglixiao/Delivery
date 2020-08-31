import 'dart:async';

///
///create by lx
///date 2020/8/10
///

class EventCenter {
  StreamController _streamController;

  EventCenter() : _streamController = StreamController.broadcast(sync: false);

  EventCenter.customController(StreamController controller)
      : _streamController = controller;

  StreamSubscription<T> listen<T>(void onData(T event)) {
    if (T == dynamic) {
      return _streamController.stream.listen(onData);
    } else {
      return _streamController.stream
          .where((event) => event is T)
          .cast<T>()
          .listen(onData);
    }
  }

  emit(dynamic event) {
    _streamController.add(event);
  }

  close() {
    _streamController.close();
  }
}

var eventCenter = EventCenter();
