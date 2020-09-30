import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/src/business/dispatch/bean/dispatch_detail.dart';
import 'package:flutter_ienglish_fine/src/business/pay/viewmodel/pay_affirm_view_model.dart';
import 'package:flutter_ienglish_fine/src/comm/event/user_info_event.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';

class PayAffirmSuccessPage extends StatelessWidget with PageBridge {
  PayAffirmViewModel _payAffirmViewModel = PayAffirmViewModel();
  bool isSuccess = false;

  @override
  Widget build(BuildContext context) {
    Map argumnets = ModalRoute.of(context).settings.arguments;
    String _numberNo;
    if (argumnets != null) {
      _numberNo = argumnets["orderNo"];
    }
    return RootPageWidget(
      onWillBack: (){
        emitUserInfoEvent();
        return Future.value(true);
      },
      appBar: IsAppBar(
        title: S.of(context).pay_affirm_success_title,
        leftOnTap: () {
          emitUserInfoEvent();
          popUtil(RouterName.index_page);
        },
      ),
      viewModel: _payAffirmViewModel,
      task: _payAffirmViewModel.loadOrderQueryPay(_numberNo).then((value) {
        _payAffirmViewModel.loadOrderDetail(_numberNo);
      }),
      body: StreamBuilder(
          stream: _payAffirmViewModel.streamOrderDetail,
          builder: (BuildContext context, AsyncSnapshot<DispatchOrderDetail> snapshot) {
            if (snapshot.data == null) {
              return Container();
            } else {
              isSuccess = snapshot.data.status != '待付款';
              return Container(
                  alignment: Alignment.center,
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: <Widget>[
                      Container(
                        margin: EdgeInsets.only(top: Values.d_36),
                        child:
                            Image.asset(isSuccess ? 'assets/images/pay_success.png' : 'assets/images/status_error.png'),
                      ),
                      Container(
                          margin: EdgeInsets.only(top: Values.d_15),
                          child: Text(
                            isSuccess
                                ? S.of(context).pay_affirm_success_content
                                : S.of(context).pay_affirm_error_content,
                            style: TextStyle(
                                color: Values.of(context).c_black_front_33,
                                fontWeight: Values.font_Weight_normal,
                                fontSize: Values.s_text_18),
                          )),
                      Row(
                        children: <Widget>[
                          Expanded(
                            child: Container(
                              margin: EdgeInsets.only(top: Values.d_50, left: Values.d_15, right: Values.d_15),
                              height: Values.d_50,
                              decoration: BoxDecoration(
                                  color: Values.of(context).c_orange_background_0b,
                                  borderRadius: BorderRadius.all(Radius.circular(5))),
                              child: FlatButton(
                                  splashColor: Values.c_translucent,
                                  highlightColor: Values.c_translucent,
                                  onPressed: () {
                                    emitUserInfoEvent();
                                    popUtil(RouterName.index_page);
                                  },
                                  child: Text(
                                    S.of(context).pay_affirm_success_button1,
                                    style: TextStyle(
                                        color: Values.of(context).c_white_front,
                                        fontWeight: Values.font_Weight_medium,
                                        fontSize: Values.s_text_16),
                                  )),
                            ),
                          )
                        ],
                      ),
                      Row(
                        children: <Widget>[
                          Expanded(
                              child: Container(
                            margin: EdgeInsets.only(top: Values.d_20, left: Values.d_15, right: Values.d_15),
                            height: Values.d_50,
                            decoration: BoxDecoration(
                                color: Values.of(context).c_white_background,
                                border: Border.all(color: Values.of(context).c_black_front_99, width: 0.5),
                                borderRadius: BorderRadius.all(Radius.circular(5))),
                            child: FlatButton(
                                splashColor: Values.c_translucent,
                                highlightColor: Values.c_translucent,
                                onPressed: () {
                                  emitUserInfoEvent();
                                  open(RouterName.my_buy_list);
                                },
                                child: Text(
                                  S.of(context).pay_affirm_success_button2,
                                  style: TextStyle(
                                      color: Values.of(context).c_black_front_99,
                                      fontWeight: Values.font_Weight_medium,
                                      fontSize: Values.s_text_16),
                                )),
                          ))
                        ],
                      )
                    ],
                  ));
            }
          }),
    );
  }

  ///付款成功后,发送UserInfoEvent,更新个人信息
  emitUserInfoEvent() {
    if (isSuccess) {
      eventCenter.emit(UserInfoEvent(type: USER_INFO_EVENT_TYPE_2));
    }
  }
}
