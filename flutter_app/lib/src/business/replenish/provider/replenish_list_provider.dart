import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/src/business/replenish/bean/replenish_list.dart';

class ReplenishListProvider with ChangeNotifier {
  int get count => _list.length;
  List<ReplenishListItemBean> _list = new List();
  List get selectList =>_list;

  bool isSelect(ReplenishListItemBean itemBean){
    return _list.contains(itemBean);
  }

  void initSelectList(List<ReplenishListItemBean> list){
    _list.clear();
    _list.addAll(list);
  }

  void selectAll(List<ReplenishListItemBean> list){
    _list.clear();
    _list.addAll(list);
    notifyListeners();
  }

  void unSelectAll(){
    _list.clear();
    notifyListeners();
  }

  void increment(ReplenishListItemBean itemBean) {
    if(!_list.contains(itemBean)){
      _list.add(itemBean);
    }
    notifyListeners();
  }

  void reduce(ReplenishListItemBean itemBean) {
    if(_list.contains(itemBean)){
      _list.remove(itemBean);
    }
    notifyListeners();
  }
}
