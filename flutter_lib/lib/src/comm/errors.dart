class ASError extends Error {
  final Object message;

  ASError({this.message});

  @override
  String toString() {
    if (message != null) {
      return "aserror failed: ${Error.safeToString(message)}";
    }
    return "aserror failed";
  }
}

class NetConnectError extends ASError {}

class ServerError extends ASError {}

class DataConvertError extends ASError {
  DataConvertError({String message}) : super(message: message);
}
