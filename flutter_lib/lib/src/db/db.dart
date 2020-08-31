import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_lib/src/db/table_helper.dart';
import 'package:flutter_lib/src/util/string_util.dart';
import 'package:sqflite/sqflite.dart';
import 'package:sqflite/utils/utils.dart';

///
///create by lx
///date 2020/7/29
///

class IsDb {
  String _db_name;

  Database _innerDb;

  List<TableDefinition> _tables;

  TableContainer _tableHelper;

  static IsDb _instance;

  factory IsDb() {
    if (_instance == null) {
      throw ASError(message: "db is not be create");
    }
    return _instance;
  }

  get name => _db_name;

  var _version;

  int get version => _version;

  IsDb._(String dbName, int version)
      : _db_name = dbName ?? "iagent_db",
        _version = version ?? 1;

  static create(TableContainer tableHelper,
      {String dbName, int version}) async {
    if (_instance == null) {
      _instance = IsDb._(dbName, version);
    }
    _instance._tableHelper = tableHelper;
    _instance._init();
  }

  _init() async {
    _tables = _tableHelper.tables;
    if (_innerDb == null || !_innerDb.isOpen) {
      _innerDb = await openDatabase(_db_name,
          version: version,
          onCreate: _createTables,
          onOpen: _opened,
          onUpgrade: _upgrade);
    }
  }

  _createTables(Database db, int version) async {
    var batch = db.batch();
    _tables.forEach((table) async {

      if (isSafeSQL(table.onCreate())) {
        Logger.print("create table : ${table.onCreate()}");
        batch.execute("DROP TABLE IF EXISTS ${table.name}");
        batch.execute(table.onCreate());
      }
    });
    return batch.commit(noResult: false, continueOnError: false).then((value) {
      if (version > 1) {
        _upgrade(db, 0, version).then((value) {}).catchError((e) {
          db.setVersion(0);
        });
      }
    });
  }

  _opened(Database db) {
    _innerDb = db;
  }

  _upgrade(Database db, int oldVersion, int newVersion) async {
    Logger.print("version : ${await db.getVersion()}");
    if (oldVersion < newVersion) {
      var batch = db.batch();

      _tables.forEach((table) {
        if (newVersion == table.version) {
          if (isSafeSQL(table.onCreate())) {
            Logger.print("create table : ${table.name}=${table.onCreate()}");
            batch.execute(table.onCreate());
          }
        } else {
          List<VersionMigration> updates = table.onUpdate(oldVersion);
          if (updates != null) {
            updates.forEach((migrate) {
              if (migrate.version > oldVersion &&
                  migrate.version <= newVersion) {
                if (isSafeSQL(migrate.sql)) {
                  Logger.print(
                      "oldVersion : $oldVersion, newVersion : $newVersion");
                  Logger.print("update table : ${table.name}=${migrate.sql}");
                  batch.execute(migrate.sql);
                }
              }
            });
          }
        }
      });

      return batch
          .commit(noResult: false, continueOnError: false)
          .then((value) {
        Logger.print("upgrade success (version=$newVersion) ! ");
      }).catchError((e) {
        Logger.print("upgrade fail : ${e.toString()}");
        return Future.error("${e.toString()}");
      });
    }
  }

  bool isSafeSQL(String sql) {
    if (StringUtil.isEmpty(sql)) {
      return false;
    }
    return true;
  }

  Future<bool> tableExists(String table) async {
    var count = firstIntValue(await _innerDb.query('sqlite_master',
        columns: ['COUNT(*)'],
        where: 'type = ? AND name = ?',
        whereArgs: ['table', table]));
    return count > 0;
  }

  Future<List<String>> getTableNames() async {
    var tableNames = (await _innerDb
        .query('sqlite_master', where: 'type = ?', whereArgs: ['table']))
        .map((row) => row['name'] as String)
        .toList(growable: false)
      ..sort();
    return tableNames;
  }

  ///**********************数据库表操作***********************

  Future<int> insert(String table, Map<String, dynamic> values) async {
    return _innerDb.insert(table, values,
        conflictAlgorithm: ConflictAlgorithm.replace);
  }

  Future<List<Map>> query(String table) async {
    return _innerDb.query(table);
  }

  execute(String sql, [List<dynamic> arguments]) async {
    return _innerDb.execute(sql, arguments);
  }

  //example: ('INSERT INTO my_table(name, year) VALUES (?, ?)', ['my_name', 2019])
  rawInsert(String sql, [List<dynamic> arguments]) {
    return _innerDb.rawInsert(sql, arguments);
  }

  //example: ('SELECT * FROM my_table WHERE name IN (?, ?, ?)', ['cat', 'dog', 'fish'])
  Future<List<Map>> rawQuery(String sql, [List<dynamic> arguments]) async {
    return _innerDb.rawQuery(sql, arguments);
  }

  rawUpdate(String sql, [List<dynamic> arguments]) {
    return _innerDb.rawUpdate(sql, arguments);
  }

  rawDelete(String sql, [List<dynamic> arguments]) {
    return _innerDb.rawDelete(sql, arguments);
  }

  ///事务处理
  ///example:
  ///await transaction.execute(sql)
  ///await transaction.execute(sql)
  ///...
  ///
  transaction(Function(Transaction transaction) action) async {
    return _innerDb.transaction(action);
  }

  ///批处理
  ///example:
  ///await batch.rawInsert(sql)
  ///await batch.rawInsert(sql)
  ///...
  ///
  batch(Function(Batch batch) action) async {
    _innerDb.transaction((txn) {
      var b = txn.batch();
      action(b);
      return b.commit();
    });
  }

  ///**********************************************************

  close() async {
    if (_innerDb != null) {
      _innerDb.close();
    }
  }
}

abstract class TableDefinition {
  String get name;

  int get version => 1;

  String onCreate(); //数据库表创建语句

  List<VersionMigration> onUpdate(int version) => []; //升级语句
}

class VersionMigration {
  int version;

  String sql;

  VersionMigration({this.version, this.sql});
}

