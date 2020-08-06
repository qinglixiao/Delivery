import 'dart:collection';

import 'package:flutter_lib/src/db/db.dart';

///
///create by lx
///date 2020/7/30
///

class TableContainer {
  List<TableDefinition> _tables;

  List<TableDefinition> get tables => _tables;

  TableContainer(List<TableDefinition> tables) : _tables = tables;
}
