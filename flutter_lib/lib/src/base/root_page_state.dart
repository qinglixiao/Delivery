import 'package:flutter/cupertino.dart';

class RequestState {
  ConnectionState state;
  var exception;
  dynamic data;

  bool get hasError => exception != null;

  String get errorMessage => exception?.message;

  RequestState({this.state = ConnectionState.none, this.exception, this.data});

  factory RequestState.error(Exception error) {
    return RequestState(exception: error);
  }

  factory RequestState.state(ConnectionState state, {dynamic data}) {
    return RequestState(state: state, data: data);
  }
}
