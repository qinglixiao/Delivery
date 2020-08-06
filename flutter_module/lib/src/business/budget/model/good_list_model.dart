import 'package:flutter_lib/flutter_lib.dart';
import 'package:fluttermodule/src/business/budget/bean/good.dart';

class GoodListModel extends BaseModel<List<GoodBean>> {
  dynamic params;
  GoodListLoader _loader;

  GoodListModel({this.params}) {
    _loader = GoodListLoader(url: "/getlunbo");
  }

  var images = [
    "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2453488943,2804257221&fm=26&gp=0.jpg",
    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1594200147322&di=a3da46738460a34117d7efae0ede8a54&imgtype=0&src=http%3A%2F%2Fimg.taopic.com%2Fuploads%2Fallimg%2F130617%2F318765-13061G6460186.jpg",
    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1594200169930&di=10c525911e2223bc7bdab0f54d5d41bb&imgtype=0&src=http%3A%2F%2Fpic2.cxtuku.com%2F00%2F02%2F11%2Fb093ad5c3b9d.jpg",
    "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1958766199,417567416&fm=26&gp=0.jpg"
  ];

  var list = <GoodBean>[];

  Future load(bool refresh) async {
    return _loader.load().then((value) {
      print(value);
      var data = List.generate(10, (index) {
        images[index % images.length];
        return GoodBean()
          ..title = "iEnglish课程 $index"
          ..imgUrl = images[index % images.length];
      });

      if (refresh) {
        list.clear();
      }
      list.addAll(data);
      add(list);
      return _loader.pageSize != list.length;
    });
  }

  @override
  void dispose() {}
}

class GoodListLoader extends UrlPagedLoader {
  var url;

  GoodListLoader({this.url}) : super(url: url);

  @override
  Map buildParameters(int page) {
    return super.buildParameters(page);
  }

  @override
  List parseResult<List>(Object o) {
    return super.parseResult(o);
  }
}
