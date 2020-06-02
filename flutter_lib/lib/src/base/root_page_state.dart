import 'package:flutter_lib/flutter_lib.dart';

class RequestState{
  Error error;
  LoaddingState loadding = LoaddingState();
}

class LoaddingState {
  bool isLoading;
  String message;

  LoaddingState({this.isLoading = false, this.message});
}
