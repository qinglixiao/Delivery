import 'dart:io';

import 'package:flutter/services.dart';
import 'package:sqflite/sqflite.dart';

///
///create by lx
///date 2020/8/7
///

class DbUtil {
  static Future<bool> copyFromto(String fromDb, String toDb) async {
    var dbPath = await getDatabasesPath();
    var path = "$dbPath/$toDb";

    var exist = await databaseExists(toDb);
    if (!exist) {
      try {
        await File(path).create(recursive: true);
      } catch (e) {
        return false;
      }

      ByteData data = await rootBundle.load(fromDb);
      List<int> bytes =
          data.buffer.asUint8List(data.offsetInBytes, data.lengthInBytes);

      await File(path).writeAsBytes(bytes, flush: true);
      return true;
    } else {
      return true;
    }
  }
}
