import 'package:flutter_lib/flutter_lib.dart';

class NetworkConfig {
  var _host_dev;
  var _host_online;
  var _host;
  var _schema = Schema.HTTP;
  bool online = false;

  NetworkConfig._();

  static NetworkConfig _instance;

  factory NetworkConfig() {
    if (_instance == null) {
      _instance = NetworkConfig._();
    }
    return _instance;
  }

  NetworkConfig onlineHost(String host) {
    _host_online = host;
    return this;
  }

  NetworkConfig devHost(String host) {
    _host_dev = host;
    return this;
  }

  NetworkConfig schema(Schema schema) {
    _schema = schema;
    return this;
  }

  NetworkConfig isOnline(bool online) {
    this.online = online;
    _host = online ? _host_online : _host_dev;
    return this;
  }

  _makeUrl(Schema schema, String host) {
    return (schema == Schema.HTTP ? "http://" : "https://") + host;
  }

  init() {
    IEnglishNetClient().baseUrl(_makeUrl(_schema, _host)).init(this);
  }
}

enum Schema {
  HTTP,
  HTTPS,
}
