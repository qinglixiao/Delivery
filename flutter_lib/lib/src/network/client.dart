import 'dart:collection';
import 'dart:io';

import 'package:cookie_jar/cookie_jar.dart';
import 'package:dio/adapter.dart';
import 'package:dio/dio.dart';
import 'package:dio_cookie_manager/dio_cookie_manager.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:path_provider/path_provider.dart';

import 'interceptor.dart';

typedef CertificateCallback = bool Function(
    X509Certificate cert, String host, int port);

class IEnglishNetClient extends DioMixin {
  static IEnglishNetClient _instance;
  var _baseUrl = ClientConfig.baseUrl;
  Map<String, dynamic> _header = HashMap();
  CookieManager _cookieManager;
  NetworkConfig _networkConfig;
  var cancelable = Map<Uri, CancelToken>();

  get cookieManager => _cookieManager;

  IEnglishNetClient baseUrl(String url) {
    _baseUrl = url;
    if (options != null) {
      options.baseUrl = url;
    }
    return this;
  }

  IEnglishNetClient._() {
    httpClientAdapter = DefaultHttpClientAdapter();
    interceptors..add(LogInterceptor())..add(EghIntercaptor(this));
  }

  factory IEnglishNetClient() {
    if (_instance == null) {
      _instance = IEnglishNetClient._();
    }
    return _instance;
  }

  IEnglishNetClient addHeader(Map<String, dynamic> header) {
    if (header != null) {
      _header.addAll(header);
    }
    return this;
  }

  addRequestHandler(Uri uri, CancelToken cancelToken) {
    if (uri != null && cancelToken != null && !cancelable.containsKey(uri)) {
      cancelable[uri] = cancelToken;
    }
  }

  deleteRequestHandler(Uri uri) {
    if (uri != null && cancelable.containsKey(uri)) {
      cancelable.remove(uri);
    }
  }

  _defaultConfig() {
    options = BaseOptions(
        baseUrl: _baseUrl ?? ClientConfig.baseUrl,
        connectTimeout: ClientConfig.connectTimeout,
        receiveTimeout: ClientConfig.receiveTimeout,
        sendTimeout: ClientConfig.sendTimeout,
        responseType: ResponseType.json,
        headers: _header);
  }

  _addCookieManager() async {
    var directory = await getTemporaryDirectory();
    _cookieManager = CookieManager(PersistCookieJar(dir: directory.path));
    interceptors.add(_cookieManager);
  }

  List<Cookie> cookies(Uri uri) {
    return _cookieManager?.cookieJar?.loadForRequest(uri);
  }

  _httpsCertificate() {
    (httpClientAdapter as DefaultHttpClientAdapter).onHttpClientCreate =
        (client) {
      CertificateManager(client);
    };
  }

  void cancelRequestAll() {
    if (cancelable.length != 0) {
      cancelable.forEach((uri, cancelToken) {
        cancelToken.cancel();
      });
      cancelable.clear();
    }
  }

  init(NetworkConfig config) {
    _networkConfig = config;
    _defaultConfig();
    _httpsCertificate();
    _addCookieManager();
  }
}

class CertificateManager {
  CertificateManager(HttpClient client) {
    client.badCertificateCallback =
        (X509Certificate cert, String host, int port) {
      Logger.print(cert);
      //暂时不校验证书
      return true;
    };
  }
}

class ClientConfig {
  static String baseUrl = "http://member-agent.tope365.com";
  static final int connectTimeout = 5 * 1000;
  static final int sendTimeout = 5 * 1000;
  static final int receiveTimeout = 3 * 1000;
}
