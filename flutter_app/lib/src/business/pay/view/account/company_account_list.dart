import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/account_list.dart';
import 'package:flutter_ienglish_fine/src/business/pay/viewmodel/company_account_list_view_model.dart';

class CompanyAccountListPage extends StatefulWidget {
  @override
  _CompanyAccountListPageState createState() => _CompanyAccountListPageState();
}

class _CompanyAccountListPageState extends State<CompanyAccountListPage> with PageBridge {
  CompanyAccountListViewModel _viewModel = CompanyAccountListViewModel();
  List<AccountListItemInfo> _items = List();

  @override
  Widget build(BuildContext context) {
    return RootPageWidget(
        appBar: IsAppBar(
          title: S.of(context).open_company_account,
        ),
        viewModel: _viewModel,
        task: _viewModel.getCompanyAccountList(true),
        body: StreamBuilder<AccountListInfo>(
            stream: _viewModel.streamAccount,
            builder: (BuildContext context, AsyncSnapshot<AccountListInfo> snapshot) {
              if (snapshot.data == null || snapshot.data?.data == null) {
                return CommonWidget.emptyWidget();
              }
              _items = snapshot.data?.data;
              return PullToRefresh(
                child: SListView(_buildItemView, itemAction: _itemAction)
                    .build(context, _items),
                onRefresh: () {
                  return _viewModel.getCompanyAccountList(false);
                },
              );
            }));
  }

  Widget _buildItemView(BuildContext context, Object itemData) {
    return ItemWidget(context, itemData);
  }

  void _itemAction(Widget widget, Object itemData) {
    open(RouterName.open_company_account);
  }

  Widget textWidget(String text, bool medium) {
    return Text(
      StringUtil.getNotNullString(text),
      overflow: TextOverflow.ellipsis,
      maxLines: 1,
      style: TextStyle(
          fontSize: Values.s_text_14,
          color: Values.of(context).c_black_front_33,
          fontWeight: medium ? Values.font_Weight_medium : Values.font_Weight_normal),
    );
  }

  Widget ItemWidget(BuildContext context, Object itemData) {
    AccountListItemInfo itemInfo = itemData as AccountListItemInfo;
    return Container(
      padding: EdgeInsets.all(Values.d_15),
      margin: EdgeInsets.only(
        left: Values.d_10,
        top: Values.d_10,
        right: Values.d_10,
      ),
      decoration:
          BoxDecoration(borderRadius: BorderRadius.circular(Values.d_5), color: Values.of(context).c_white_background),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Row(
            mainAxisAlignment: MainAxisAlignment.start,
            children: <Widget>[
              textWidget(S.of(context).company_name+':', false),
              textWidget(itemInfo.enterpriseName, true),
            ],
          ),
          SizedBox(height: Values.d_12),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            crossAxisAlignment: CrossAxisAlignment.end,
            children: <Widget>[
              Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Row(
                    children: <Widget>[
                      textWidget(S.of(context).company_card_type+':', false),
                      textWidget(itemInfo.enterpriseCardCode, true),
                    ],
                  ),
                  SizedBox(height: Values.d_12),
                  Row(
                    children: <Widget>[
                      textWidget(S.of(context).pay_save_open_bank+':', false),
                      textWidget(itemInfo.bankName, true),
                    ],
                  ),
                  SizedBox(height: Values.d_12),
                  Row(
                    children: <Widget>[
                      textWidget(S.of(context).bank_company_card_number_simple+':', false),
                      textWidget(itemInfo.bankCode, true),
                    ],
                  ),
                ],
              ),
              Container(
                width: 80,
                height: 65,
                child: itemInfo.merNetInOutStatus =='0'? Image.asset('assets/images/account_audit.png'):Image.asset('assets/images/account_fail.png'),
              )
            ],
          ),
        ],
      ),
    );
  }
}
