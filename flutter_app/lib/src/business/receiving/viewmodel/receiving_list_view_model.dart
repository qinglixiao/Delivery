import 'package:flutter_ienglish_fine/src/business/receiving/bean/receiving_list.dart';
import 'package:flutter_ienglish_fine/src/business/receiving/model/receiving_list_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

class ReceivingListViewModel extends BaseViewModel {

  ReceivingListViewModel() : super();

  ReceivingListModel _receivingListModel = ReceivingListModel();

  Stream<ReceivingListBean> get streamReceivingList=> _receivingListModel.getStream<ReceivingListBean>();

  bool hasMoreList;

  int pageIndex = 1;

  Future loadReceivingList({bool isFirst,bool isRefresh}) async {
    if (isFirst) {
      showLoadding();
    }
    if(isRefresh){
      pageIndex = 1;
    }
    else{
      pageIndex ++;
    }
    return _receivingListModel.getReceivingList(pageIndex.toString()).then((value) {
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
    _receivingListModel?.close();
  }
}
