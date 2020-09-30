import 'package:flutter_ienglish_fine/src/business/budget/bean/good.dart';
import 'package:flutter_ienglish_fine/src/business/mine/bean/mine_sell_list.dart';
import 'package:flutter_ienglish_fine/src/business/mine/model/mine_sell_list_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

class MineSellListViewModel extends BaseViewModel {
  MineSellListModel _mineSellListModel = MineSellListModel();

  Stream<MineSellList> get streamAllList =>
      _mineSellListModel.getStream<MineSellList>();
  Stream<MineSellList> get streamWaitGatheringList =>
      _mineSellListModel.getStream<MineSellList>();
  Stream<MineSellList>  get streamWaitReplenishList =>
      _mineSellListModel.getStream<MineSellList>();
  Stream<MineSellList> get streamFinishList =>
      _mineSellListModel.getStream<MineSellList>();

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
    return _mineSellListModel.getAllSellList(pageIndex.toString()).then((value) {
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

  Future loadWaitGatheringList({bool isFirst,bool isRefresh}) async {
    if (isFirst) {
      showLoadding();
    }
    if(isRefresh){
      pageIndex = 1;
    }
    else{
      pageIndex ++;
    }
    return _mineSellListModel.getWaitGatheringList(pageIndex.toString()).then((value) {
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

  Future loadWaitReplenishyList({bool isFirst,bool isRefresh}) async {
    if (isFirst) {
      showLoadding();
    }
    if(isRefresh){
      pageIndex = 1;
    }
    else{
      pageIndex ++;
    }
    return _mineSellListModel.getWaitReplenishList(pageIndex.toString()).then((value) {
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

  Future loadFinishList({bool isFirst,bool isRefresh}) async {
    if (isFirst) {
      showLoadding();
    }
    if(isRefresh){
      pageIndex = 1;
    }
    else{
      pageIndex ++;
    }
    return _mineSellListModel.getFinishList(pageIndex.toString()).then((value) {
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

  Future loadAffirmPay({String numberCode}) async {
    return _mineSellListModel.getAffirmPay(numberCode);
  }


  @override
  void dispose() {
    super.dispose();
    _mineSellListModel?.close();
  }
}
