import 'package:flutter/services.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/balance_detail.dart';
import 'package:flutter_ienglish_fine/src/business/pay/model/balance_detail_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

class BalanceDetailViewModel extends BaseViewModel {
  BalanceDetailViewModel() : super();

  BalanceDetailModel _balanceDetailModel = BalanceDetailModel();

  Stream<BalanceDetailBean> get streamFlows => _balanceDetailModel.getStream<BalanceDetailBean>();

  bool hasMoreList;

  int pageIndex = 1;

  Future getBalanceFlowsData({bool isFirst,bool isRefresh}) async{
    String code = SpUtil.getUserNumberDesc();
    if (isFirst) {
      showLoadding();
    }
    if(isRefresh){
      pageIndex = 1;
    }
    else{
      pageIndex ++;
    }
    return _balanceDetailModel.getPayAccountFolws(code,pageIndex.toString()).then((value) {
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

  Future getBalanceDetail({bool isFirst,bool isRefresh}) async{
    String code = SpUtil.getUserNumberDesc();
    if (isFirst) {
      showLoadding();
    }
    if(isRefresh){
      pageIndex = 1;
    }
    else{
      pageIndex ++;
    }
    return _balanceDetailModel.getPayAccountBalance(code,pageIndex.toString()).then((value) {
      if (isFirst&&value?.items?.length == 0) {
        showEmptyView();
      } else {
        hideLoadding();
      }
      if(value.ifLasgPage){
        hasMoreList = false;
      }
      else{
        hasMoreList = true;
      }
    }).catchError((e) {
      error(e);
    });
  }

  @override
  void dispose() {
    super.dispose();
    _balanceDetailModel?.close();
  }
}