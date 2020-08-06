import 'package:flutter_lib/src/db/db.dart';
import 'package:flutter_lib/src/db/table_helper.dart';

///
///create by lx
///date 2020/7/30
///

class DbConfig {
  static init(TableContainer tableHelper, {String dbName, int version}) async {
    IsDb.create(tableHelper, dbName: dbName, version: version);
  }
}
