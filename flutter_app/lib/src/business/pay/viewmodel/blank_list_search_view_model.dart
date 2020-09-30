import 'package:flutter_ienglish_fine/src/business/pay/bean/bank_name_info.dart';
import 'package:flutter_ienglish_fine/src/business/pay/model/blank_list_search_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

///  created by：sunyuancun
/// 2020/9/23
///desc:
class BlankListSearchViewModel extends BaseViewModel {
  BlankListSearchViewModel() : super();

  BlankListSearchModel _model = BlankListSearchModel();


  Stream<BankNameInfo> get streamList =>  _model.getStream<BankNameInfo>();


  Future<BankNameInfo> getBankListInfo() async {
    return _model.getBankInfoList();
  }

  @override
  void dispose() {
    super.dispose();
    _model?.close();
  }
}
