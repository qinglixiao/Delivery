import '../bridge/bridge.dart';

enum LoaderStateType {
  init,
  loaded,
  failed,
  succeed,
}

class LoaderState {
  LoaderStateType _state = LoaderStateType.init;
  LoaderStateType _lastLoadState = LoaderStateType.init;

  var error;

  void succeed() {
    _state = LoaderStateType.loaded;
    _lastLoadState = LoaderStateType.succeed;
  }

  void fail(e) {
    if (e is IsLoadingException) {
      _clearError();
      return;
    }

    _lastLoadState = LoaderStateType.failed;
    error = e;
  }

  bool isLoaded() {
    return _state == LoaderStateType.loaded;
  }

  void reset() {
    _lastLoadState = LoaderStateType.init;
    _state = LoaderStateType.init;
  }

  void _clearError() {
    _lastLoadState = LoaderStateType.init;
  }

  bool isLastLoadFailed() {
    return _lastLoadState == LoaderStateType.failed;
  }

  bool isNew() {
    return _lastLoadState == LoaderStateType.init && _lastLoadState == LoaderStateType.init;
  }
}

class PagedLoader {
  PagedLoader({
    this.initialPage=1,
    this.pageSize=20,
    this.pageParameterName="page",
    this.pageSizeParameterName="page_size",
    });

  final String pageParameterName;
  final String pageSizeParameterName;

  final int initialPage;
  final int pageSize;

  // state
  int currentPage = -1;
  bool canLoadMore =true;
  bool isLoading =false;

  int beginLoading(bool isRefresh) {
    if (isLoading) throw IsLoadingException();

    isLoading =true;

    if (currentPage < 0) isRefresh =true;

    int page =isRefresh? this.initialPage : this.currentPage + 1;

    return page;
  }

  void endLoading(int page) {
    currentPage =page;
  }

  Future<T> load<T>({bool isRefresh=true}) async {
    return null;
  }

  Map buildPageParameters(int page) {
    return {
      pageParameterName: page,
      pageSizeParameterName:pageSize
    };
  }

  Map buildParameters(int page) {
    return buildPageParameters(page);
  }
}

class UrlPagedLoader extends PagedLoader {
  final String url;
  final String method;
  final bool useNativeHttp;
  Map parameters;

  UrlPagedLoader({
    this.url,
    this.method="GET",
    this.parameters,
    this.useNativeHttp = true,
    int initialPage=1,
    int pageSize=20,
    String pageSizeParameterName="page_size",
    String pageParameterName="page",
  }) :super(
    initialPage:initialPage, 
    pageSize:pageSize,
    pageSizeParameterName:pageSizeParameterName,
    pageParameterName:pageParameterName,
  );

  @override
  Future<T> load<T>({bool isRefresh=true}) async {
    int page = beginLoading(isRefresh);
    Map p = buildParameters(page);

    return CYBridge()
    .request(method, url, p)
    .then((o){
      print("http result: $o");
      isLoading = false;

      var r = parseResult(o);

      print("$r");

      endLoading(page);

      return r;
    }, onError: (e) {
      isLoading = false; 
      print("eeee: $e");
      throw e;
    });
  }

  T parseResult<T>(Object o) {
    return o;
  }
}

typedef Object PagedFakeDataGenerator(bool isRefresh, int page);

class IsLoadingException implements Exception {
}

class FakePagedLoader extends PagedLoader {
  final PagedFakeDataGenerator generator;

  FakePagedLoader(this.generator);

  @override
  Future<T> load<T>({bool isRefresh=true}) async {
    int page = beginLoading(isRefresh);

    return new Future.delayed(const Duration(seconds: 2), () {
      Object data = generator(isRefresh, page);

      endLoading(page);

      return data;
    });
  } 
}
