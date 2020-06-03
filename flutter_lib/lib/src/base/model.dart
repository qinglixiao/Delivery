import 'package:rxdart/rxdart.dart';

abstract class BaseModel<T> {
  BehaviorSubject<T> control;
  T data;

  Stream<T> get stateStream {
    return control;
  }

  BaseStreamState() {
    control = BehaviorSubject<T>();
  }

  void add(T data) {
    this.data = data;
    control?.sink?.add(data);
  }

  void close() {
    control?.close();
    dispose();
  }

  void dispose();
}
