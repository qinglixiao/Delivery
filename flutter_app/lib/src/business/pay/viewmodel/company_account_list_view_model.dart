import 'package:flutter_ienglish_fine/src/business/pay/bean/account_list.dart';
import 'package:flutter_ienglish_fine/src/business/pay/model/company_account_list_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

class CompanyAccountListViewModel extends BaseViewModel {
  CompanyAccountListViewModel() : super();

  CompanyAccountListModel _companyAccountListModel = CompanyAccountListModel();

  Stream<AccountListInfo> get streamAccount => _companyAccountListModel.getStream<AccountListInfo>();

  Future getCompanyAccountList(bool isFirst) async{
    if (isFirst) {
      showLoadding();
    }
    String code = SpUtil.getUserNumberDesc();
    return _companyAccountListModel.getCompanyAccountListInfo(code).then((value){
      if (isFirst && value?.data?.length == 0) {
        showEmptyView();
      } else {
        hideLoadding();
      }
    }).catchError((e) {
      error(e);
    });
  }

  @override
  void dispose() {
    super.dispose();
    _companyAccountListModel?.close();
  }
}
