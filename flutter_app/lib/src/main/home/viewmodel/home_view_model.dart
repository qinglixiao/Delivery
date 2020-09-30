import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/src/main/home/model/home_model.dart';
import 'package:flutter_ienglish_fine/src/main/home/bean/home.dart';

class HomeViewModel extends BaseViewModel {
  HomeViewModel() : super();

  HomeModel _homeModel = HomeModel();

  bool hasRead = false;

  Stream<BannerBean> get streamHomeBanner =>  _homeModel.getStream<BannerBean>();
  Stream<FunctionBean> get streamHomeFunction => _homeModel.getStream<FunctionBean>();
  Stream<OrderNumBean> get streamHomeOrderNum=> _homeModel.getStream<OrderNumBean>();
  Stream<NewsBean> get streamHomeNews => _homeModel.getStream<NewsBean>();

  Future getHomeData() async{
    return Future.wait([getBanner(),getFunction(),getOrderNum(),getNews()]).then((value){
    });
  }

  Future getBanner() {
    return _homeModel
        .getAdvertises()
        .then((value) => {
            })
        .catchError((e) {
      error(e);
    });
  }

  Future getFunction() {
    return _homeModel
        .getByTypeGroup()
        .then((value) => {
    })
        .catchError((e) {
      error(e);
    });
  }

  Future getOrderNum() {
    return _homeModel
        .getOrderSize()
        .then((value) => {
    })
        .catchError((e) {
      error(e);
    });
  }

  Future getNews() {
    return _homeModel
        .getMessage()
        .then((value) => {
    })
        .catchError((e) {
      error(e);
    });
  }

  Future readMsg(int messageReceiverId, Function(NetBean info) callback) async {
    return _homeModel.readMsg(messageReceiverId).then((value) {
      callback(value);
    }).catchError((e) {
      error(e);
    });
  }

  @override
  void unBindStream() {
    super.unBindStream();
    _homeModel.unBindStream();
  }

  @override
  void dispose() {
    super.dispose();
    _homeModel?.close();
  }
}
