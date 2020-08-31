import 'package:rxdart/rxdart.dart';

abstract class BaseModel {
  Map<String, BehaviorSubject> controls;

  Stream<S> getStream<S>() {
    if (controls.containsKey(S.toString())) {
      return controls[S.toString()] as Stream<S>;
    } else {
      var c = BehaviorSubject<S>();
      controls[S.toString()] = c;
      return c;
    }
  }

  BaseModel() {
    controls = Map();
  }

  Object add(Object data) {
    var type = data.runtimeType.toString();
    if (controls.containsKey(type)) {
      controls[type].sink.add(data);
    }

    return data;
  }

  void unBindStream() {
    controls.clear();
  }

  void close() {
    controls.forEach((key, value) {
      value.close();
    });

    unBindStream();
    dispose();
  }

  void dispose() {}
}
