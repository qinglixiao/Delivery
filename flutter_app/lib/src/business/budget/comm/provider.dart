import 'package:flutter_ienglish_fine/src/business/budget/bean/good_confirm.dart';
import 'package:flutter_ienglish_fine/src/business/budget/model/good_confirm_model.dart';

///
///create by lx
///date 2020/7/23
///

class BudgetProvider {
  GoodConfirmModel _placeDetailModel = GoodConfirmModel();
  Future<FundQuery> getGoodFuns(String code) async {
    return _placeDetailModel.getFundQuery(code);
  }
}
