import 'package:flutter_ienglish_fine/src/business/replenish/bean/replenish_list.dart';
import 'package:flutter_ienglish_fine/src/business/replenish/model/replenish_list_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

class ReplenishListViewModel extends BaseViewModel {

  ReplenishListViewModel() : super();

  ReplenishListModel _replenishListModel = ReplenishListModel();

  Stream<ReplenishListBean> get streamReplenishList=> _replenishListModel.getStream<ReplenishListBean>();

  bool hasMoreList;

  int pageIndex = 1;

  Future loadReplenishList({bool isFirst,bool isRefresh}) async {
    if (isFirst) {
      showLoadding();
    }
    if(isRefresh){
      pageIndex = 1;
    }
    else{
      pageIndex ++;
    }
    return _replenishListModel.getReplenishList(pageIndex.toString()).then((value) {
      if (isFirst && value?.items.length == 0) {
        showEmptyView();
      } else {
        hideLoadding();
      }
      if(value.totalPage > pageIndex){
        hasMoreList = true;
      }
      else{
        hasMoreList = false;
      }
    }).catchError((e) {
      error(e);
    });
  }

  @override
  void dispose() {
    super.dispose();
    _replenishListModel?.close();
  }
}
