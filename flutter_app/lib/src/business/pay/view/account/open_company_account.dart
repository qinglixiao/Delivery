import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/account_info.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/bank_name_info.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/company_account_info.dart';
import 'package:flutter_ienglish_fine/src/business/pay/comm/interfaces.dart';

import 'package:flutter_ienglish_fine/src/business/pay/viewmodel/open_account_view_model.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:keyboard_avoider/keyboard_avoider.dart';
import 'package:flutter_lib/flutter_lib.dart';

class OpenCompanyAccount extends StatefulWidget {
  @override
  _OpenCompanyAccountState createState() => _OpenCompanyAccountState();
}

class _OpenCompanyAccountState extends State<OpenCompanyAccount> with PageBridge {
  OpenAccountViewModel _openAccountViewModel = OpenAccountViewModel();

  TextEditingController _companyNameController = new TextEditingController();
  TextEditingController _companyCardController = new TextEditingController();
  TextEditingController _companyTelController = new TextEditingController();
  TextEditingController _bankCompanyCardController = new TextEditingController();
  TextEditingController _legalNameController = new TextEditingController();
  TextEditingController _legalIdNumController = new TextEditingController();

  final ScrollController _scrollController = ScrollController();
  String _idType;
  String _bankType;

  String _bankTypeCode;

  bool _isCanSubmit = false;
  bool _isFirst = true;
  String _userSex;
  String _userSexType;
  AccountInfo _accountInfo;
  CompanyAccountInfo _companyAccountInfo;

  @override
  Widget build(BuildContext context) {
    _idType = S.of(context).user_id_card_type;
    loadOpenAccountInfo();
    return RootPageWidget(
        appBar: IsAppBar(
          title: S.of(context).open_company_account_title,
          rightWidget: FlatButton(
            onPressed: () {
              open(RouterName.web_view,
                  argument: WebParams(url: NetworkConfig().getHostForWeb() + NET_WEB_OPEN_ACCOUNT_EXPLAIN));
            },
            color: Values.c_translucent,
            splashColor: Values.c_translucent,
            highlightColor: Values.c_translucent,
            disabledColor: Values.c_translucent,
            child: new Text(
              S.of(context).open_company_account_explain,
              style: TextStyle(
                fontSize: Values.s_text_15,
                fontWeight: Values.font_Weight_normal,
                color: Values.of(context).c_orange_front_0b,
              ),
            ),
          ),
        ),
        body: StreamBuilder<AccountInfo>(
            stream: _openAccountViewModel.streamAccountInfo,
            builder: (BuildContext context, AsyncSnapshot<AccountInfo> snapshot) {
              if (snapshot.data != null) {
                if(_isFirst){
                  _accountInfo = snapshot.data;
                  if (!_accountInfo.ifSuccess) {
                    _openAccountViewModel.getOpenCompanyAccountInfo();
                    Future.delayed(Duration(milliseconds: 300)).then((value){
                      handleStatusDialog();
                    });
                    _isFirst = false;
                }
                  return StreamBuilder(
                      stream: _openAccountViewModel.streamCompanyAccountInfo,
                      builder: (BuildContext context, AsyncSnapshot<CompanyAccountInfo> accountSnapshot) {
                        if (accountSnapshot.data != null) {
                          _companyAccountInfo = accountSnapshot.data;
                          _companyNameController = TextEditingController(text: _companyAccountInfo.enterpriseName);
                          _companyCardController = TextEditingController(text: _companyAccountInfo.enterpriseCardCode);
                          _companyTelController = TextEditingController(text: _companyAccountInfo.enterprisePhone);
                          _bankCompanyCardController =
                              TextEditingController(text: _companyAccountInfo.enterpriseBankCode);
                          _bankType = _companyAccountInfo.enterpriseBankName;
                          _bankTypeCode = _companyAccountInfo.enterpriseHeadBankCode;
                        }
                        return contentWidget();
                      });
                } else {
                  return contentWidget();
                }
              }
              return contentWidget();
            }));
  }

  Widget contentWidget() {
    return GestureDetector(
              behavior: HitTestBehavior.translucent,
              onTap: () {
                checkStatus();
                FocusScope.of(context).requestFocus(FocusNode());
              },
              child: KeyboardAvoider(
                  child: CustomScrollView(controller: _scrollController, slivers: <Widget>[
                SliverToBoxAdapter(
                    child: Container(
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget>[
                      GestureDetector(
                        onTap: () {
                          open(RouterName.web_view,
                              argument: WebParams(url: NetworkConfig().getHostForWeb() + NET_WEB_OPEN_ACCOUNT));
                        },
                        child: AspectRatio(
                            aspectRatio: 375 / 120,
                            child: Image.asset('assets/images/open_account_header.png', fit: BoxFit.fill)),
                      ),
                      Container(
                        margin: EdgeInsets.only(left: Values.d_15, top: Values.d_18, bottom: Values.d_12),
                        child: Text(S.of(context).company_info_title,
                            style: TextStyle(
                                fontSize: Values.s_text_14,
                                fontWeight: Values.font_Weight_medium,
                                color: Values.of(context).c_black_front_66,
                                decoration: TextDecoration.none),
                            textAlign: TextAlign.left),
                      ),
                      Container(
                        color: Values.of(context).c_white_background,
                        padding:
                            EdgeInsets.only(left: Values.d_15, right: Values.d_15, top: Values.d_5, bottom: Values.d_5),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: <Widget>[
                            Text(
                              S.of(context).company_name,
                              style: TextStyle(
                                  fontSize: Values.s_text_15,
                                  fontWeight: Values.font_Weight_normal,
                                  color: Values.of(context).c_black_front_33,
                                  decoration: TextDecoration.none),
                            ),
                            Expanded(
                              child: TextField(
                                controller: _companyNameController,
                                textAlign: TextAlign.right,
                                style: TextStyle(
                                    color: Values.of(context).c_black_front_33,
                                    fontWeight: Values.font_Weight_medium,
                                    fontSize: Values.s_text_14),
                                decoration: InputDecoration(
                                    enabledBorder: UnderlineInputBorder(
                                      borderSide: BorderSide(color: Values.c_translucent),
                                    ),
                                    focusedBorder: UnderlineInputBorder(
                                      borderSide: BorderSide(color: Values.c_translucent),
                                    ),
                                    hintText: S.of(context).company_name_hint,
                                    hintStyle: TextStyle(
                                        color: Values.of(context).c_grey_front_cc,
                                        fontWeight: Values.font_Weight_medium,
                                        fontSize: Values.s_text_14)),
                                onSubmitted: (value) {},
                                cursorColor: Values.of(context).c_black_front_33,
                              ),
                            ),
                          ],
                        ),
                      ),
                      lineWidget(),
                      Container(
                        color: Values.of(context).c_white_background,
                        padding:
                            EdgeInsets.only(left: Values.d_15, right: Values.d_15, top: Values.d_5, bottom: Values.d_5),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: <Widget>[
                            Text(
                              S.of(context).company_card_type_title,
                              style: TextStyle(
                                  fontSize: Values.s_text_15,
                                  fontWeight: Values.font_Weight_normal,
                                  color: Values.of(context).c_black_front_33,
                                  decoration: TextDecoration.none),
                            ),
                            Container(
                              padding: EdgeInsets.only(left: Values.d_18, top: Values.d_15, bottom: Values.d_15),
                              child: Text(
                                S.of(context).company_card_type,
                                style: TextStyle(
                                    fontSize: Values.s_text_15,
                                    fontWeight: Values.font_Weight_medium,
                                    color: Values.of(context).c_black_front_33,
                                    decoration: TextDecoration.none),
                              ),
                            ),
                          ],
                        ),
                      ),
                      lineWidget(),
                      Container(
                        color: Values.of(context).c_white_background,
                        padding:
                            EdgeInsets.only(left: Values.d_15, right: Values.d_15, top: Values.d_5, bottom: Values.d_5),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: <Widget>[
                            Text(
                              S.of(context).company_card_number,
                              style: TextStyle(
                                  fontSize: Values.s_text_15,
                                  fontWeight: Values.font_Weight_normal,
                                  color: Values.of(context).c_black_front_33,
                                  decoration: TextDecoration.none),
                            ),
                            Expanded(
                              child: TextField(
                                controller: _companyCardController,
                                textAlign: TextAlign.right,
                                style: TextStyle(
                                    color: Values.of(context).c_black_front_33,
                                    fontWeight: Values.font_Weight_medium,
                                    fontSize: Values.s_text_14),
                                decoration: InputDecoration(
                                    enabledBorder: UnderlineInputBorder(
                                      borderSide: BorderSide(color: Values.c_translucent),
                                    ),
                                    focusedBorder: UnderlineInputBorder(
                                      borderSide: BorderSide(color: Values.c_translucent),
                                    ),
                                    hintText: S.of(context).company_card_number_hint,
                                    hintStyle: TextStyle(
                                        color: Values.of(context).c_grey_front_cc,
                                        fontWeight: Values.font_Weight_medium,
                                        fontSize: Values.s_text_14)),
                                onSubmitted: (value) {},
                                cursorColor: Values.of(context).c_black_front_33,
                              ),
                            ),
                          ],
                        ),
                      ),
                      lineWidget(),
                      Container(
                        color: Values.of(context).c_white_background,
                        padding:
                            EdgeInsets.only(left: Values.d_15, right: Values.d_15, top: Values.d_5, bottom: Values.d_5),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: <Widget>[
                            Text(
                              S.of(context).bank_company_tel,
                              style: TextStyle(
                                  fontSize: Values.s_text_15,
                                  fontWeight: Values.font_Weight_normal,
                                  color: Values.of(context).c_black_front_33,
                                  decoration: TextDecoration.none),
                            ),
                            Expanded(
                              child: TextField(
                                controller: _companyTelController,
                                textAlign: TextAlign.right,
                                keyboardType: TextInputType.phone,
                                style: TextStyle(
                                    color: Values.of(context).c_black_front_33,
                                    fontWeight: Values.font_Weight_medium,
                                    fontSize: Values.s_text_14),
                                decoration: InputDecoration(
                                    enabledBorder: UnderlineInputBorder(
                                      borderSide: BorderSide(color: Values.c_translucent),
                                    ),
                                    focusedBorder: UnderlineInputBorder(
                                      borderSide: BorderSide(color: Values.c_translucent),
                                    ),
                                    hintText: S.of(context).bank_company_tel_hint,
                                    hintStyle: TextStyle(
                                        color: Values.of(context).c_grey_front_cc,
                                        fontWeight: Values.font_Weight_medium,
                                        fontSize: Values.s_text_14)),
                                onSubmitted: (value) {},
                                cursorColor: Values.of(context).c_black_front_33,
                              ),
                            ),
                          ],
                        ),
                      ),
                      Container(
                        margin: EdgeInsets.only(left: Values.d_15, top: Values.d_18, bottom: Values.d_12),
                        child: Text(S.of(context).bank_info,
                            style: TextStyle(
                                fontSize: Values.s_text_14,
                                fontWeight: Values.font_Weight_medium,
                                color: Values.of(context).c_black_front_66,
                                decoration: TextDecoration.none),
                            textAlign: TextAlign.left),
                      ),
                      Container(
                        color: Values.of(context).c_white_background,
                        padding:
                            EdgeInsets.only(left: Values.d_15, right: Values.d_15, top: Values.d_5, bottom: Values.d_5),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: <Widget>[
                            Text(
                              S.of(context).bank_choose,
                              style: TextStyle(
                                  fontSize: Values.s_text_15,
                                  fontWeight: Values.font_Weight_normal,
                                  color: Values.of(context).c_black_front_33,
                                  decoration: TextDecoration.none),
                            ),
                            Expanded(
                                child: GestureDetector(
                              onTap: () {
                                open(RouterName.blan_list_search).then((value){
                                  if (value != null) {
                                    _bankType = value["bank_name"];
                                    _bankTypeCode = value["bank_code"];
                                    setState(() {});
                                  }
                                });
                              },
                              child: Container(
                                padding: EdgeInsets.only(left: Values.d_18, top: Values.d_15, bottom: Values.d_15),
                                child: Row(
                                  mainAxisAlignment: MainAxisAlignment.end,
                                  children: <Widget>[
                                    Text(
                                      _bankType ?? S.of(context).choose_hint,
                                      style: TextStyle(
                                          fontSize: Values.s_text_14,
                                          fontWeight: StringUtil.isEmpty(_bankType)
                                              ? Values.font_Weight_normal
                                              : Values.font_Weight_medium,
                                          color: StringUtil.isEmpty(_bankType)
                                              ? Values.of(context).c_grey_front_cc
                                              : Values.of(context).c_black_front_33,
                                          decoration: TextDecoration.none),
                                    ),
                                    SizedBox(width: Values.d_2),
                                    Image.asset('assets/images/right_icon.png')
                                  ],
                                ),
                              ),
                            )),
                          ],
                        ),
                      ),
                      lineWidget(),
                      Container(
                        color: Values.of(context).c_white_background,
                        padding:
                            EdgeInsets.only(left: Values.d_15, right: Values.d_15, top: Values.d_5, bottom: Values.d_5),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: <Widget>[
                            Text(
                              S.of(context).bank_company_card_number,
                              style: TextStyle(
                                  fontSize: Values.s_text_15,
                                  fontWeight: Values.font_Weight_normal,
                                  color: Values.of(context).c_black_front_33,
                                  decoration: TextDecoration.none),
                            ),
                            Expanded(
                              child: TextField(
                                controller: _bankCompanyCardController,
                                textAlign: TextAlign.right,
                                keyboardType: TextInputType.phone,
                                style: TextStyle(
                                    color: Values.of(context).c_black_front_33,
                                    fontWeight: Values.font_Weight_medium,
                                    fontSize: Values.s_text_14),
                                decoration: InputDecoration(
                                    enabledBorder: UnderlineInputBorder(
                                      borderSide: BorderSide(color: Values.c_translucent),
                                    ),
                                    focusedBorder: UnderlineInputBorder(
                                      borderSide: BorderSide(color: Values.c_translucent),
                                    ),
                                    hintText: S.of(context).bank_company_card_number_hint,
                                    hintStyle: TextStyle(
                                        color: Values.of(context).c_grey_front_cc,
                                        fontWeight: Values.font_Weight_medium,
                                        fontSize: Values.s_text_14)),
                                onSubmitted: (value) {},
                                cursorColor: Values.of(context).c_black_front_33,
                              ),
                            ),
                          ],
                        ),
                      ),
                      Container(
                        margin: EdgeInsets.only(left: Values.d_15, top: Values.d_18, bottom: Values.d_12),
                        child: Text(S.of(context).legal_person_info,
                            style: TextStyle(
                                fontSize: Values.s_text_14,
                                fontWeight: Values.font_Weight_medium,
                                color: Values.of(context).c_black_front_66,
                                decoration: TextDecoration.none),
                            textAlign: TextAlign.left),
                      ),
                      Container(
                        color: Values.of(context).c_white_background,
                        padding:
                            EdgeInsets.only(left: Values.d_15, right: Values.d_15, top: Values.d_5, bottom: Values.d_5),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: <Widget>[
                            Text(
                              S.of(context).legal_person_name,
                              style: TextStyle(
                                  fontSize: Values.s_text_15,
                                  fontWeight: Values.font_Weight_normal,
                                  color: Values.of(context).c_black_front_33,
                                  decoration: TextDecoration.none),
                            ),
                            Expanded(
                              child: TextField(
                                controller: _legalNameController,
                                textAlign: TextAlign.right,
                                style: TextStyle(
                                    color: Values.of(context).c_black_front_33,
                                    fontWeight: Values.font_Weight_medium,
                                    fontSize: Values.s_text_14),
                                decoration: InputDecoration(
                                    enabledBorder: UnderlineInputBorder(
                                      borderSide: BorderSide(color: Values.c_translucent),
                                    ),
                                    focusedBorder: UnderlineInputBorder(
                                      borderSide: BorderSide(color: Values.c_translucent),
                                    ),
                                    hintText: S.of(context).legal_person_name_hint,
                                    hintStyle: TextStyle(
                                        color: Values.of(context).c_grey_front_cc,
                                        fontWeight: Values.font_Weight_medium,
                                        fontSize: Values.s_text_14)),
                                onSubmitted: (value) {},
                                cursorColor: Values.of(context).c_black_front_33,
                              ),
                            ),
                          ],
                        ),
                      ),
                      lineWidget(),
                      Container(
                        color: Values.of(context).c_white_background,
                        padding:
                            EdgeInsets.only(left: Values.d_15, right: Values.d_15, top: Values.d_5, bottom: Values.d_5),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: <Widget>[
                            Text(
                              S.of(context).person_sex,
                              style: TextStyle(
                                  fontSize: Values.s_text_15,
                                  fontWeight: Values.font_Weight_normal,
                                  color: Values.of(context).c_black_front_33,
                                  decoration: TextDecoration.none),
                            ),
                            Expanded(
                                child: GestureDetector(
                              onTap: () {
                                showSexChoose();
                              },
                              child: Container(
                                padding: EdgeInsets.only(left: Values.d_18, top: Values.d_15, bottom: Values.d_15),
                                child: Row(
                                  mainAxisAlignment: MainAxisAlignment.end,
                                  children: <Widget>[
                                    Text(
                                      _userSex ?? S.of(context).choose_hint,
                                      style: TextStyle(
                                          fontSize: Values.s_text_14,
                                          fontWeight: StringUtil.isEmpty(_userSex)
                                              ? Values.font_Weight_normal
                                              : Values.font_Weight_medium,
                                          color: StringUtil.isEmpty(_userSex)
                                              ? Values.of(context).c_grey_front_cc
                                              : Values.of(context).c_black_front_33,
                                          decoration: TextDecoration.none),
                                    ),
                                    SizedBox(width: Values.d_2),
                                    Image.asset('assets/images/right_icon.png')
                                  ],
                                ),
                              ),
                            )),
                          ],
                        ),
                      ),
                      lineWidget(),
                      Container(
                        color: Values.of(context).c_white_background,
                        padding:
                            EdgeInsets.only(left: Values.d_15, right: Values.d_15, top: Values.d_5, bottom: Values.d_5),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: <Widget>[
                            Text(
                              S.of(context).user_id_card_type_title,
                              style: TextStyle(
                                  fontSize: Values.s_text_15,
                                  fontWeight: Values.font_Weight_normal,
                                  color: Values.of(context).c_black_front_33,
                                  decoration: TextDecoration.none),
                            ),
                            Expanded(
                                child: GestureDetector(
                              onTap: () {},
                              child: Container(
                                padding: EdgeInsets.only(left: Values.d_18, top: Values.d_15, bottom: Values.d_15),
                                child: Row(
                                  mainAxisAlignment: MainAxisAlignment.end,
                                  children: <Widget>[
                                    Text(
                                      _idType,
                                      style: TextStyle(
                                          fontSize: Values.s_text_14,
                                          fontWeight: Values.font_Weight_medium,
                                          color: Values.of(context).c_black_front_33,
                                          decoration: TextDecoration.none),
                                    ),
                                    SizedBox(width: Values.d_2),
                                    Image.asset('assets/images/right_icon.png')
                                  ],
                                ),
                              ),
                            )),
                          ],
                        ),
                      ),
                      lineWidget(),
                      Container(
                        color: Values.of(context).c_white_background,
                        padding:
                            EdgeInsets.only(left: Values.d_15, right: Values.d_15, top: Values.d_5, bottom: Values.d_5),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: <Widget>[
                            Text(
                              S.of(context).user_id_card_number,
                              style: TextStyle(
                                  fontSize: Values.s_text_15,
                                  fontWeight: Values.font_Weight_normal,
                                  color: Values.of(context).c_black_front_33,
                                  decoration: TextDecoration.none),
                            ),
                            Expanded(
                              child: TextField(
                                controller: _legalIdNumController,
                                textAlign: TextAlign.right,
                                style: TextStyle(
                                    color: Values.of(context).c_black_front_33,
                                    fontWeight: Values.font_Weight_medium,
                                    fontSize: Values.s_text_14),
                                decoration: InputDecoration(
                                    enabledBorder: UnderlineInputBorder(
                                      borderSide: BorderSide(color: Values.c_translucent),
                                    ),
                                    focusedBorder: UnderlineInputBorder(
                                      borderSide: BorderSide(color: Values.c_translucent),
                                    ),
                                    hintText: S.of(context).user_id_card_number_hint,
                                    hintStyle: TextStyle(
                                        color: Values.of(context).c_grey_front_cc,
                                        fontWeight: Values.font_Weight_medium,
                                        fontSize: Values.s_text_14)),
                                onSubmitted: (value) {},
                                cursorColor: Values.of(context).c_black_front_33,
                              ),
                            ),
                          ],
                        ),
                      ),
                      Container(
                        height: Values.d_44,
                        margin: EdgeInsets.only(
                          top: Values.d_50,
                          left: Values.d_26,
                          right: Values.d_26,
                          bottom: Values.d_26,
                        ),
                        child: new SizedBox.expand(
                          child: FlatButton(
                            onPressed: () {
                              submitAction();
                            },
                            color: !_isCanSubmit
                                ? Values.of(context).c_grey_background_cc
                                : Values.of(context).c_orange_background_0b,
                            splashColor: Values.c_translucent,
                            highlightColor: Values.c_translucent,
                            disabledColor: Values.c_translucent,
                            child: new Text(
                              S.of(context).submit_title,
                              style: TextStyle(
                                fontSize: Values.s_text_14,
                                color: Values.of(context).c_white_front,
                              ),
                            ),
                            shape: new RoundedRectangleBorder(borderRadius: new BorderRadius.circular(44.0)),
                          ),
                        ),
                      )
                    ],
                  ),
                ))
              ])));
  }

  Widget lineWidget(){
    return Container(
      padding: EdgeInsets.only(left: Values.d_15, right: Values.d_15),
      color: Values.of(context).c_white_background,
      height: 1,
      child: Container(
        color: Values.of(context).c_grey_ea,
      ),
    );
  }


  void loadOpenAccountInfo() {
    if (_isFirst) {
      _openAccountViewModel.getOpenAccountInfo();
    }
  }

  /// 普通消息提示框
  void showDialogInfo(String title, String message, String button) {
    showDialog(
        context: context,
        child: DialogTool(
          bgColor: Values.of(context).c_white_background,
          title: title,
          titleFontSize: Values.s_text_18,
          titleColor: Values.of(context).c_black_front_33,
          content: message,
          middleTitle: button,
          middleColor: Values.of(context).c_white_front,
          middleBgColor: Values.of(context).c_orange_background_0b,
          contentColor: Values.of(context).c_black_front_33,
          onTap: (int index) {},
        ));
  }

  ///    0-待审核
  ///    1-审核通过
  ///    2-等待人工审核
  ///    3-人工审核失败(信息有误)
  ///    4-人工审核失败(照片问题)
  void handleStatusDialog() {
    if (_accountInfo.merchantStatus == '0') {
      ///您还未提交银行审核
      ///      1. 若未提交营业执照照片，小额转账等信息，点击“提交”按钮后继续提交开户信息
      ///      2. 企业开户未成功状态下暂无法处理发货，如需处理，请联系商务
      ///    继续提交
      showDialogInfo(S.of(context).open_account_wait_status_title, S.of(context).open_account_wait_status_content,
          S.of(context).open_account_again_title);
    } else if (_accountInfo.merchantStatus == '2') {
      ///银行审核中
      ///      1. 您已提交开户信息，请耐心等待银行审核结果
      ///      2. 若信息填写错误，可重新提交开户信息，不影响审核进度
      ///    重新提交开户信息
      showDialogInfo(S.of(context).open_account_check_status_title, S.of(context).open_account_check_status_content,
          S.of(context).open_account_recheck_title);
    } else if (_accountInfo.merchantStatus == '3' || _accountInfo.merchantStatus == '4') {
      ///审核失败
      ///    您提交的企业开户信息审核未通过！
      ///    失败原因:
      ///      1. 营业执照不清晰；2. 营业执照与证件照不一致
      ///      2. 企业开户未成功状态下暂无法处理发货，如需处理，请联系商务
      ///    重新提交开户信息
      showDialogInfo(S.of(context).open_account_fail_status_title, S.of(context).open_account_fail_status_content,
          S.of(context).open_account_recheck_title);
    } else if (_accountInfo.ifIdCardType == 'true') {
      ///    提示
      ///    提交企业开户信息，个人户将被注销
      ///    知道了
      showDialogInfo(
          S.of(context).warn_title, S.of(context).open_account_recheck_status_content, S.of(context).warn_button_title);
    }
  }

  void showSexChoose() {
    PickerTool.showSinglePicker(context, data: [S.of(context).male, S.of(context).female],
        clickCallBack: (int selectIndex, Object selectStr) {
      _userSexType = selectIndex == 0 ? "M" : "F";
      _userSex = selectStr;
      setState(() {});
    });
  }

  void checkStatus() {
    if (StringUtil.isEmpty(_companyNameController.text)) {
      updateStatus(false);
      return;
    }
    if (StringUtil.isEmpty(_companyCardController.text)) {
      updateStatus(false);
      return;
    }
    if (StringUtil.isEmpty(_companyTelController.text)) {
      updateStatus(false);
      return;
    }
    if (StringUtil.isEmpty(_bankType)) {
      updateStatus(false);
      return;
    }
    if (StringUtil.isEmpty(_bankCompanyCardController.text)) {
      updateStatus(false);
      return;
    }
    if (StringUtil.isEmpty(_legalNameController.text)) {
      updateStatus(false);
      return;
    }
    if (StringUtil.isEmpty(_legalIdNumController.text)) {
      updateStatus(false);
      return;
    }
    updateStatus(true);
  }

  void updateStatus(bool status) {
    if (status == _isCanSubmit) {
      return;
    }
    setState(() {
      _isCanSubmit = status;
    });
  }

  bool checkParams() {
    if (StringUtil.isEmpty(_companyNameController.text)) {
      ProgressHUD.showError(warnText: S.of(context).company_name_hint);
      return false;
    }
    if (StringUtil.isEmpty(_companyCardController.text)) {
      ProgressHUD.showError(warnText: S.of(context).company_card_number_hint);
      return false;
    }
    if (StringUtil.isEmpty(_companyTelController.text)) {
      ProgressHUD.showError(warnText: S.of(context).bank_company_tel);
      return false;
    }
    if (StringUtil.isEmpty(_bankType)) {
      ProgressHUD.showError(warnText: S.of(context).bank_choose_warn);
      return false;
    }
    if (StringUtil.isEmpty(_bankCompanyCardController.text)) {
      ProgressHUD.showError(warnText: S.of(context).bank_company_card_number_hint);
      return false;
    }
    if (StringUtil.isEmpty(_legalNameController.text)) {
      ProgressHUD.showError(warnText: S.of(context).legal_person_name_hint);
      return false;
    }
    if (StringUtil.isEmpty(_userSexType)) {
      ProgressHUD.showError(warnText: S.of(context).person_sex_hint);
      return false;
    }
    if (StringUtil.isEmpty(_legalIdNumController.text)) {
      ProgressHUD.showError(warnText: S.of(context).user_id_card_number_hint);
      return false;
    }
    return true;
  }

  void submitAction() {
    if (!_isCanSubmit) {
      return;
    }
    if (!checkParams()) {
      return;
    }
    ProgressHUD.showLoading();
    String custName = _companyNameController.text;
    String orgLicense = _companyCardController.text;
    String idNo = _companyCardController.text;
    String acctNo = _bankCompanyCardController.text;
    String openBank = _bankTypeCode;
    String openBankName = _bankType;
    String mobileNo = _companyTelController.text;
    String legalName = _legalNameController.text;
    String legalGender = _userSexType;
    String legalIdNo = _legalIdNumController.text;
    _openAccountViewModel.postOpenCompanyAccount(
        custName, orgLicense, idNo, acctNo, openBank, openBankName, mobileNo, legalName, legalGender, legalIdNo,
        (info) {
      ProgressHUD.hiddenHUD();
      if (info.error_code == "1" || !StringUtil.isEmpty(info.retUrl)) {
        open(RouterName.web_view,
            argument: WebParams(
                title: S.of(context).open_company_account,
                url: info.retUrl,
                route: RouterName.pay_status,
                routeParams: {
                  'status': true,
                  'title': S.of(context).open_company_account,
                  'content': S.of(context).open_account_success,
                  'router': RouterName.index_page,
                  'action': S.of(context).pay_affirm_success_button1
                }));
      } else {
        showDialogInfo(S.of(context).warn_title, info.message, S.of(context).warn_button_title);
      }
    });
  }
}
