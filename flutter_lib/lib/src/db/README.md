//示例：表结构

class UserTable extends TableDefinition {
  @override
  int get version => 1;

  @override
  String get name => "user";

  @override
  List<VersionMigration> onUpdate(int version) {
    return [
      VersionMigration(
        version: 2,
        sql: "ALTER TABLE user ADD description TEXT",
      ),
      VersionMigration(
        version: 3,
        sql: "ALTER TABLE user ADD description TEXT",
      ),
    ];
  }

  @override
  String onCreate() {
    return "$CREATE_TABLE $name(${UserColumn.ID} ${Type.INTEGER} $PRIMARY_KEY $AUTOINCREMENT,${UserColumn.MOBILE} ${Type.TEXT})";
  }
}

class UserColumn {
  static const ID = "_id";
  static const MOBILE = "mobile";
}