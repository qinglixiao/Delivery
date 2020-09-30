import 'package:flutter_ienglish_fine/src/business/pay/bean/bank_info.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/earnings.dart';
import 'package:flutter_ienglish_fine/src/business/pay/model/earnings_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

class EarningsViewModel extends BaseViewModel {
  EarningsViewModel() : super();

  EarningsModel _earningsModel = EarningsModel();

  Stream<EarningsBean> get streamEarnings => _earningsModel.getStream<EarningsBean>();
  Stream<EarningsBean> get streamEarningsStatus=> _earningsModel.getStream<EarningsBean>();

  Future getEarningsData() async{
    String code = SpUtil.getUserNumberDesc();
    return _earningsModel.getEarningsInfo(code).then((value) {
    }).catchError((e) {
      error(e);
    });
  }

  Future getEarningsStatusData({bool isFirst,String status}) async{
    if (isFirst) {
      showLoadding();
    }
    String code = SpUtil.getUserNumberDesc();
    return _earningsModel.getEarningsStatusInfo(code,status).then((value) {
      if (value?.items?.length == 0) {
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
    _earningsModel?.close();
  }
}
