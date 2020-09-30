import 'package:flutter_ienglish_fine/src/business/dispatch/bean/dispatch_list.dart';
import 'package:flutter_ienglish_fine/src/business/dispatch/model/dispatch_list_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

class DispatchListViewModel extends BaseViewModel {

  DispatchListViewModel() : super();

  DispatchListModel _dispatchListModel = DispatchListModel();

  Stream<DispatchListBean> get streamDispatchList=> _dispatchListModel.getStream<DispatchListBean>();

  bool hasMoreList;

  int pageIndex = 1;

  Future loadDispatchList({bool isFirst ,bool isRefresh}) async {
    if (isFirst) {
      showLoadding();
    }
    if(isRefresh){
      pageIndex = 1;
    }
    else{
      pageIndex ++;
    }
    return _dispatchListModel.getDispatchList(pageIndex.toString()).then((value) {
      if (isFirst && value.items.length == 0) {
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

  Future loadRejectOrder(
      String numberCode,
      Function(NetBean statusInfo) callback) async{
    return _dispatchListModel.postRejectOrder(numberCode).then((value) {
      callback(value);
    }).catchError((e) {
      error(e);
    });
  }

  @override
  void dispose() {
    super.dispose();
    _dispatchListModel?.close();
  }
}
