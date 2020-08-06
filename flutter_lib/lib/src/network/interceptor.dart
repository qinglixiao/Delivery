import 'package:dio/dio.dart';
import 'package:flutter_lib/src/util/share_preference.dart';

import '../../flutter_lib.dart';

class EghInterceptor extends InterceptorsWrapper {
  IEnglishNetClient iEnglishNetClient;

  EghInterceptor(IEnglishNetClient client) {
    this.iEnglishNetClient = client;
  }

  @override
  Future onRequest(RequestOptions options) {
    CancelToken cancelToken = CancelToken();
    options.cancelToken = cancelToken;

    fetchHeaderToUrl(options);
    return super.onRequest(options);
  }

  ///后端接口不从header里取值，所以将header拼接到url
  fetchHeaderToUrl(RequestOptions options) {
    if (options.headers != null && options.headers.length > 0) {
      options.queryParameters.addAll(options.headers);
    }

    var loc = Map();

    if (!options.queryParameters.containsKey("token") &&
        SpUtil.getToken() != null) {
      loc["token"] = SpUtil.getToken();
    }

    if (!options.queryParameters.containsKey("xxl_sso_sessionid") &&
        SpUtil.getSessionId() != null) {
      loc["xxl_sso_sessionid"] = SpUtil.getSessionId();
    }

    if(loc.length > 0) {
      options.uri.queryParameters.addAll(loc);
    }
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
