import 'package:flutter/cupertino.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/src/business/mine/viewmodel/set_pay_pwd_view_model.dart';
import 'package:flutter_ienglish_fine/src/business/mine/widget/item_widget.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_lib/flutter_lib.dart';

///
///create by lx
///date 2020/8/18
///

class PayPwdManagerPage extends StatelessWidget with PageBridge {

  SetPayPwdViewModel _viewModel = SetPayPwdViewModel();

  @override
  Widget build(BuildContext context) {
    return RootPageWidget(
      appBar: IsAppBar(
        title: S.of(context).change_deal_password,
        showBottomLine: true,
      ),
      body: Container(
          color: Values.of(context).c_white_background,
          height: 101,
          child: Column(
            children: <Widget>[
              ItemWidget.leftTextRightIcon(context,
                  S.of(context).pay_pwd_modify, (){
                    _viewModel.getPayPwdModify().then((value) => {
                      if (value.isSuccess())
                        {
                          open(RouterName.web_view,
                              argument: WebParams(
                                  title: S.of(context).pay_pwd_modify,
                                  url: value.data.retUrl,
                                  route: RouterName.pay_status,
                                  routeParams: {
                                    'title': S.of(context).pay_pwd_modify,
                                    'content': S.of(context).pay_pwd_modify_success,
                                    'router': RouterName.index_page,
                                    'action': S.of(context).pay_affirm_success_button1
                                  }))
                        }
                    });
                  }),
              Container(
                height: Values.d_0_5,
                color: Values.of(context).c_grey_ea,
                margin: EdgeInsets.symmetric(horizontal: 15),
              ),
              ItemWidget.leftTextRightIcon(context, S.of(context).reset_pay_pwd, () {
                _viewModel.getResetPayPwd().then((value) => {
                  if (value.isSuccess())
                    {
                      open(RouterName.web_view,
                          argument: WebParams(
                              title: S.of(context).reset_pay_pwd,
                              url: value.data.retUrl,
                              route: RouterName.pay_status,
                              routeParams: {
                                'title': S.of(context).reset_pay_pwd,
                                'content': S.of(context).reset_pay_pwd_success,
                                'router': RouterName.index_page,
                                'action': S.of(context).pay_affirm_success_button1
                              }))
                    }
                });
              }),
            ],
          )),
    );
  }
}
