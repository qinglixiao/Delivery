import 'package:flutter_ienglish_fine/src/business/budget/bean/good.dart';
import 'package:flutter_ienglish_fine/src/business/mine/bean/mine_buy_list.dart';
import 'package:flutter_ienglish_fine/src/business/mine/model/mine_buy_list_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

class MineBuyListViewModel extends BaseViewModel {
  MineBuyListModel _mineBuyListModel = MineBuyListModel();

  Stream<MineBuyList> get streamAllList =>
      _mineBuyListModel.getStream<MineBuyList>();
  Stream<MineBuyList> get streamWaitPayList =>
      _mineBuyListModel.getStream<MineBuyList>();
  Stream<MineBuyList>  get streamPayList =>
      _mineBuyListModel.getStream<MineBuyList>();
  Stream<MineBuyList> get streamCancelList =>
      _mineBuyListModel.getStream<MineBuyList>();
  Stream<MineBuyList> get streamWaitReceivingList =>
      _mineBuyListModel.getStream<MineBuyList>();

  bool hasMoreList;

  int pageIndex = 1;

  Future loadAllList({bool isFirst,bool isRefresh}) async {
    if (isFirst) {
      showLoadding();
    }
    if(isRefresh){
      pageIndex = 1;
    }
    else{
      pageIndex ++;
    }
    return _mineBuyListModel.getAllBuyList(pageIndex.toString()).then((value) {
      if (isFirst && value?.items?.length == 0) {
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

  Future loadWaitPayList({bool isFirst,bool isRefresh}) async {
    if (isFirst) {
      showLoadding();
    }
    if(isRefresh){
      pageIndex = 1;
    }
    else{
      pageIndex ++;
    }
    return _mineBuyListModel.getWaitPayList(pageIndex.toString()).then((value) {
      if (isFirst && value?.items?.length == 0) {
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

  Future loadPayList({bool isFirst,bool isRefresh}) async {
    if (isFirst) {
      showLoadding();
    }
    if(isRefresh){
      pageIndex = 1;
    }
    else{
      pageIndex ++;
    }
    return _mineBuyListModel.getPayList(pageIndex.toString()).then((value) {
      if (isFirst && value?.items?.length == 0) {
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

  Future loadCancelList({bool isFirst,bool isRefresh}) async {
    if (isFirst) {
      showLoadding();
    }
    if(isRefresh){
      pageIndex = 1;
    }
    else{
      pageIndex ++;
    }
    return _mineBuyListModel.getCancelList(pageIndex.toString()).then((value) {
      if (isFirst && value?.items?.length == 0) {
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

  Future loadWaitReceivingList({bool isFirst,bool isRefresh}) async {
    if (isFirst) {
      showLoadding();
    }
    if(isRefresh){
      pageIndex = 1;
    }
    else{
      pageIndex ++;
    }
    return _mineBuyListModel.getWaitReceivingList(pageIndex.toString()).then((value) {
      if (isFirst && value?.items?.length == 0) {
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

  Future loadCancelOrder(String code,String message) async {
    return _mineBuyListModel.postCancelOrder(code,message);
  }

  @override
  void dispose() {
    super.dispose();
    _mineBuyListModel?.close();
  }
}
