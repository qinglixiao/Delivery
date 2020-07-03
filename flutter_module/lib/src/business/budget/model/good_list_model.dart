import 'package:flutter_lib/flutter_lib.dart';
import 'package:fluttermodule/src/business/budget/bean/good.dart';
import 'package:fluttermodule/src/business/budget/bean/movies.dart';
import 'package:fluttermodule/src/main/topbean.dart';

class GoodListModel extends BaseModel<GoodBean> {
  dynamic params;

  GoodListModel({this.params});

  Future load(int top) async {
    IEnglishNetClient()
        .baseUrl("http://api.douban.com/v2/movie")
        .get(
          "/in_theaters?apikey=0df993c66c0c636e29ecbb5344252a4a&start=0&count=10",
        )
        .then((value) {
      TopMovies.fromJson(value.data);
      return true;
    });
  }

  @override
  void dispose() {}
}
