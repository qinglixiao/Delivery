import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/painting.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/bank_name_info.dart';
import 'package:flutter_ienglish_fine/src/business/pay/viewmodel/blank_list_search_view_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

///  created by：sunyuancun
/// 2020/9/23
///desc:

class BankListSearchPage extends StatefulWidget {
  @override
  _BankListSearchPageState createState() => _BankListSearchPageState();
}

class _BankListSearchPageState extends State<BankListSearchPage> with PageBridge {
  BlankListSearchViewModel _viewModel = BlankListSearchViewModel();
  List<BankNameInfoItem> bankList;
  String keyWords = "银行";

  StreamController<List<BankNameInfoItem>> _streamListController;//银行列表数据流

  @override
  void initState() {
    super.initState();
    _streamListController = StreamController<List<BankNameInfoItem>>();
    _viewModel.getBankListInfo().then((value) {
      bankList = value.data;
      sendSearchAction();
    });
  }

  @override
  Widget build(BuildContext context) {
    return RootPageWidget(
        appBar: IsAppBar(
          title: S.of(context).bank_list_search_title,
        ),
        body: Column(
          children: <Widget>[_buildSearchWidget(), _buildBankListWidget()],
        ));
  }

  _buildSearchWidget() {
    return Container(
      height: Values.d_52,
      padding: EdgeInsets.only(top: Values.d_10, bottom: Values.d_10, left: Values.d_18, right: Values.d_18),
      alignment: Alignment.centerLeft,
      child: Container(
        decoration:
            BoxDecoration(color: Values.of(context).c_white_background, borderRadius: BorderRadius.circular(5.0)),
        child: Row(
          children: <Widget>[
            SizedBox(
              width: 10,
            ),
            Image.asset("assets/images/icon_search.png"),
            SizedBox(
              width: 10,
            ),
            Expanded(
              child: TextField(
                  decoration: InputDecoration(
                      border: OutlineInputBorder(borderSide: BorderSide.none),
                      contentPadding: const EdgeInsets.all(0.0),
                      hintText: S.of(context).bank_search_hint,
                      hintStyle: TextStyle(color: Values.of(context).c_grey_front_cc, fontSize: Values.s_text_12)),
                  style: TextStyle(color: Values.of(context).c_black_front_33, fontSize: Values.s_text_14),
                  maxLines: 1,
                  textAlign: TextAlign.left,
                  textCapitalization: TextCapitalization.none,
                  onChanged: (s) {
                    keyWords = s;
                    sendSearchAction();
                  }),
            )
          ],
        ),
      ),
    );
  }

  _buildBankListWidget() {
    return StreamBuilder(
      stream: _viewModel.streamList,
      builder: (BuildContext context, AsyncSnapshot<BankNameInfo> snapshot) {
        if (snapshot.data == null) {
          return Container();
        }
        return StreamBuilder(
          stream: _streamListController.stream,
          builder: (BuildContext context, AsyncSnapshot<List<BankNameInfoItem>> snapshot) {
            if (snapshot.data == null) return Container();
            return Expanded(
              child: SListView(_buildItemView, itemAction: _itemAction).build(context, snapshot.data),
            );
          },
        );
      },
    );
  }

  Widget _buildItemView(BuildContext context, Object data) {
    var bankNameInfoItem = data as BankNameInfoItem;
    return Container(
      color: Values.of(context).c_white_background,
      height: Values.d_50,
      padding: EdgeInsets.only(left: Values.d_15, right: Values.d_15),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Container(
            alignment: Alignment.centerLeft,
            height: Values.d_49,
            child: Text(bankNameInfoItem.name),
          ),
          Container(
            height: Values.d_1,
            color: Values.of(context).c_grey_ea,
          )
        ],
      ),
    );
  }

  void _itemAction(Widget widget, Object data) {
    var bankNameInfoItem = data as BankNameInfoItem;
    pop(data: {"bank_name": bankNameInfoItem.name, "bank_code": bankNameInfoItem.headBankCode});
  }

  sendSearchAction() {
    if (bankList == null) return;
    List<BankNameInfoItem> keyWordsBankList = bankList.where((element) {
      return element.name.contains(keyWords);
    }).toList();
    _streamListController.add(keyWordsBankList);
  }

  @override
  dispose() {
    super.dispose();
    _streamListController.close();
  }
}
