import 'package:flutter/cupertino.dart';

class RequestState {
  ConnectionState state;
  Error error;
  dynamic data;

  RequestState({this.state, this.error, this.data});

  factory RequestState.error(Error error) {
    return RequestState(error: error);
  }

  factory RequestState.state(ConnectionState state, {dynamic data}) {
    return RequestState(state: state, data: data);
  }
}
