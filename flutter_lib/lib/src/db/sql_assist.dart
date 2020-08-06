///
///create by lx
///date 2020/7/31
///

///sqlite 支持的数据类型
class Type {
  static const INTEGER = "INTEGER"; //整型
  static const REAL = "REAL"; //浮点型
  static const TEXT = "TEXT"; //文本
  static const BLOB = "BLOB"; //大字节
}

//"add","all","alter","and","as","autoincrement","between","case","check","collate",
// "commit","constraint","create","default","deferrable","delete","distinct","drop",
// "else","escape","except","exists","foreign","from","group","having","if","in","index",
// "insert","intersect","into","is","isnull","join","limit","not","notnull","null","on",
// "or","order","primary","references","select","set","table","then","to","transaction",
// "union","unique","update","using","values","when","where"

const SELECT = "select";
const TABLE = "table";
const FROM = "from";
const WHERE = "where";
const LIMIT = "limit";
const WHEN = "when";
const CREATE_TABLE = "create table";
const AUTOINCREMENT = "autoincrement";
const PRIMARY_KEY = "primary key";

class SQLBuilder {
  SQLBuilder();

  StringBuffer _sql = StringBuffer();

  select(List<String> columns) {
    _sql.write("$SELECT ${_combine(columns)}");
  }

  from(List<String> table) {
    _sql.write("$FROM $TABLE ${_combine(table)}");
  }

  where(String where) {
    _sql.write("$WHERE $where");
  }

  when(String when) {
    _sql.write("$WHEN $when");
  }

  limit(String limit) {
    _sql.write("$LIMIT $limit");
  }

  String _combine(List<String> data) {
    if (data == null || data.length == 0) {
      return "";
    }
    var buffer = StringBuffer();
    data.forEach((element) {
      buffer.write("$element,");
    });

    return buffer.toString().substring(0, buffer.length - 1);
  }

  String build() => _sql.toString();
}
