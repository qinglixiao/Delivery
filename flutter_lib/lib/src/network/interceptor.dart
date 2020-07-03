import 'package:dio/dio.dart';

import '../../flutter_lib.dart';

class EghIntercaptor extends InterceptorsWrapper {
  IEnglishNetClient iEnglishNetClient;

  EghIntercaptor(IEnglishNetClient client) {
    this.iEnglishNetClient = client;
  }

  @override
  Future onRequest(RequestOptions options) {
    CancelToken cancelToken = CancelToken();
    options.cancelToken = cancelToken;
    iEnglishNetClient.addRequestHandler(options.uri, cancelToken);
    return super.onRequest(options);
  }

  @override
  Future onResponse(Response response) {
    iEnglishNetClient.deleteRequestHandler(response.request.uri);
    return super.onResponse(response);
  }

  @override
  Future onError(DioError err) {
    return super.onError(err);
  }
}
