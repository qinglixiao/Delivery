import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/account_info.dart';
import 'package:flutter_ienglish_fine/src/business/pay/bean/bank_name_info.dart';
import 'package:flutter_ienglish_fine/src/business/pay/comm/interfaces.dart';
import 'package:flutter_ienglish_fine/src/business/pay/view/account/dialog_open_account_result.dart';
import 'package:flutter_ienglish_fine/src/business/pay/viewmodel/open_account_view_model.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:keyboard_avoider/keyboard_avoider.dart';

class OpenPersonAccount extends StatefulWidget {
  @override
  _OpenPersonAccountState createState() => _OpenPersonAccountState();
}

class _OpenPersonAccountState extends State<OpenPersonAccount> with PageBridge {
  OpenAccountViewModel _openAccountViewModel = OpenAccountViewModel();

  TextEditingController _nameController = new TextEditingController();
  TextEditingController _idNumController =
  new TextEditingController();
  TextEditingController _bankCardIdController =
  new TextEditingController();
  TextEditingController _telController =
  new TextEditingController();

  final ScrollController _scrollController = ScrollController();
  String _idType;

  String _bankType ;
  String _bankTypeCode ;
  bool _isFirst = true;
  bool _isCanSubmit = false;

  @override
  Widget build(BuildContext context) {
    _idType = S.of(context).user_id_card_type;
    loadOpenAccountInfo();
    return RootPageWidget(
        appBar: IsAppBar(
          title: S.of(context).open_person_account_title,
        ),
        body: StreamBuilder<AccountInfo>(
            stream: _openAccountViewModel.streamAccountInfo,
            builder:
                (BuildContext context, AsyncSnapshot<AccountInfo> snapshot) {
              _isFirst = false;
              return GestureDetector(
                        behavior: HitTestBehavior.translucent,
                        onTap: () {
                          checkStatus();
                          FocusScope.of(context).requestFocus(FocusNode());
                        },
                        child: KeyboardAvoider(
                            child: CustomScrollView(
                                controller: _scrollController,
                                slivers: <Widget>[
                                  SliverToBoxAdapter(
                                      child: Container(
                                        child: Column(
                                          crossAxisAlignment: CrossAxisAlignment.start,
                                          children: <Widget>[
                                            GestureDetector(
                                              onTap: () {
                                                open(RouterName.web_view,
                                                    argument: WebParams(
                                                        url: NetworkConfig().getHostForWeb() +
                                                            NET_WEB_OPEN_ACCOUNT));
                                              },
                                              child: AspectRatio(
                                                  aspectRatio: 375 / 120,
                                                  child: Image.asset(
                                                      'assets/images/open_account_header.png',
                                                      fit: BoxFit.fill)),
                                            ),
                                            Container(
                                              margin: EdgeInsets.only(
                                                  left: Values.d_15,
                                                  top: Values.d_18,
                                                  bottom: Values.d_12),
                                              child: Text(S.of(context).person_info,
                                                  style: TextStyle(
                                                      fontSize: Values.s_text_14,
                                                      fontWeight: Values.font_Weight_medium,
                                                      color:
                                                      Values.of(context).c_black_front_66,
                                                      decoration: TextDecoration.none),
                                                  textAlign: TextAlign.left),
                                            ),
                                            Container(
                                              color: Values.of(context).c_white_background,
                                              padding: EdgeInsets.only(
                                                  left: Values.d_15,
                                                  right: Values.d_15,
                                                  top: Values.d_5,
                                                  bottom: Values.d_5),
                                              child: Row(
                                                mainAxisAlignment:
                                                MainAxisAlignment.spaceBetween,
                                                children: <Widget>[
                                                  Text(
                                                    S.of(context).pay_save_name,
                                                    style: TextStyle(
                                                        fontSize: Values.s_text_15,
                                                        fontWeight: Values.font_Weight_normal,
                                                        color: Values.of(context)
                                                            .c_black_front_33,
                                                        decoration: TextDecoration.none),
                                                  ),
                                                  Expanded(
                                                    child: TextField(
                                                      controller: _nameController,
                                                      textAlign: TextAlign.right,
                                                      style: TextStyle(
                                                          color: Values.of(context)
                                                              .c_black_front_33,
                                                          fontWeight:
                                                          Values.font_Weight_medium,
                                                          fontSize: Values.s_text_14),
                                                      decoration: InputDecoration(
                                                          enabledBorder: UnderlineInputBorder(
                                                            borderSide: BorderSide(
                                                                color: Values.c_translucent),
                                                          ),
                                                          focusedBorder: UnderlineInputBorder(
                                                            borderSide: BorderSide(
                                                                color: Values.c_translucent),
                                                          ),
                                                          hintText:
                                                          S.of(context).person_name_hint,
                                                          hintStyle: TextStyle(
                                                              color: Values.of(context)
                                                                  .c_grey_front_cc,
                                                              fontWeight:
                                                              Values.font_Weight_medium,
                                                              fontSize: Values.s_text_14)),
                                                      onSubmitted: (value) {
                                                        checkStatus();
                                                      },
                                                      cursorColor:
                                                      Values.of(context).c_black_front_33,
                                                    ),
                                                  ),
                                                ],
                                              ),
                                            ),
                                            lineWidget(),
                                            Container(
                                              color: Values.of(context).c_white_background,
                                              padding: EdgeInsets.only(
                                                  left: Values.d_15,
                                                  right: Values.d_15,
                                                  top: Values.d_5,
                                                  bottom: Values.d_5),
                                              child: Row(
                                                mainAxisAlignment:
                                                MainAxisAlignment.spaceBetween,
                                                children: <Widget>[
                                                  Text(
                                                    S.of(context).user_id_card_type_title,
                                                    style: TextStyle(
                                                        fontSize: Values.s_text_15,
                                                        fontWeight: Values.font_Weight_normal,
                                                        color: Values.of(context)
                                                            .c_black_front_33,
                                                        decoration: TextDecoration.none),
                                                  ),
                                                  Expanded(
                                                    child: Container(
                                                      padding: EdgeInsets.only(
                                                          left: Values.d_18,
                                                          top: Values.d_15,
                                                          bottom: Values.d_15),
                                                      child: Row(
                                                        mainAxisAlignment:
                                                        MainAxisAlignment.end,
                                                        children: <Widget>[
                                                          Text(
                                                            _idType,
                                                            style: TextStyle(
                                                                fontSize: Values.s_text_14,
                                                                fontWeight:
                                                                Values.font_Weight_medium,
                                                                color: Values.of(context)
                                                                    .c_black_front_33,
                                                                decoration:
                                                                TextDecoration.none),
                                                          ),
                                                          SizedBox(width: Values.d_2),
                                                          Image.asset(
                                                              'assets/images/right_icon.png')
                                                        ],
                                                      ),
                                                    ),
                                                  ),
                                                ],
                                              ),
                                            ),
                                            lineWidget(),
                                            Container(
                                              color: Values.of(context).c_white_background,
                                              padding: EdgeInsets.only(
                                                  left: Values.d_15,
                                                  right: Values.d_15,
                                                  top: Values.d_5,
                                                  bottom: Values.d_5),
                                              child: Row(
                                                mainAxisAlignment:
                                                MainAxisAlignment.spaceBetween,
                                                children: <Widget>[
                                                  Text(
                                                    S.of(context).user_id_card_number,
                                                    style: TextStyle(
                                                        fontSize: Values.s_text_15,
                                                        fontWeight: Values.font_Weight_normal,
                                                        color: Values.of(context)
                                                            .c_black_front_33,
                                                        decoration: TextDecoration.none),
                                                  ),
                                                  Expanded(
                                                    child: TextField(
                                                      controller: _idNumController,
                                                      textAlign: TextAlign.right,
                                                      style: TextStyle(
                                                          color: Values.of(context)
                                                              .c_black_front_33,
                                                          fontWeight:
                                                          Values.font_Weight_medium,
                                                          fontSize: Values.s_text_14),
                                                      decoration: InputDecoration(
                                                          enabledBorder: UnderlineInputBorder(
                                                            borderSide: BorderSide(
                                                                color: Values.c_translucent),
                                                          ),
                                                          focusedBorder: UnderlineInputBorder(
                                                            borderSide: BorderSide(
                                                                color: Values.c_translucent),
                                                          ),
                                                          hintText: S
                                                              .of(context)
                                                              .user_id_card_number_hint,
                                                          hintStyle: TextStyle(
                                                              color: Values.of(context)
                                                                  .c_grey_front_cc,
                                                              fontWeight:
                                                              Values.font_Weight_medium,
                                                              fontSize: Values.s_text_14)),
                                                      onSubmitted: (value) {
                                                        checkStatus();
                                                      },
                                                      cursorColor:
                                                      Values.of(context).c_black_front_33,
                                                    ),
                                                  ),
                                                ],
                                              ),
                                            ),
                                            Container(
                                              margin: EdgeInsets.only(
                                                  left: Values.d_15,
                                                  top: Values.d_18,
                                                  bottom: Values.d_12),
                                              child: Text(S.of(context).bank_info,
                                                  style: TextStyle(
                                                      fontSize: Values.s_text_14,
                                                      fontWeight: Values.font_Weight_medium,
                                                      color:
                                                      Values.of(context).c_black_front_66,
                                                      decoration: TextDecoration.none),
                                                  textAlign: TextAlign.left),
                                            ),
                                            Container(
                                              color: Values.of(context).c_white_background,
                                              padding: EdgeInsets.only(
                                                  left: Values.d_15,
                                                  right: Values.d_15,
                                                  top: Values.d_5,
                                                  bottom: Values.d_5),
                                              child: Row(
                                                mainAxisAlignment:
                                                MainAxisAlignment.spaceBetween,
                                                children: <Widget>[
                                                  Text(
                                                    S.of(context).bank_choose,
                                                    style: TextStyle(
                                                        fontSize: Values.s_text_15,
                                                        fontWeight: Values.font_Weight_normal,
                                                        color: Values.of(context)
                                                            .c_black_front_33,
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
                                                          padding: EdgeInsets.only(
                                                              left: Values.d_18,
                                                              top: Values.d_15,
                                                              bottom: Values.d_15),
                                                          child: Row(
                                                            mainAxisAlignment:
                                                            MainAxisAlignment.end,
                                                            children: <Widget>[
                                                              Text(
                                                                StringUtil.isEmpty(_bankType)
                                                                    ? S.of(context).choose_hint
                                                                    : _bankType,
                                                                style: TextStyle(
                                                                    fontSize: Values.s_text_14,
                                                                    fontWeight: StringUtil
                                                                        .isEmpty(_bankType)
                                                                        ? Values
                                                                        .font_Weight_normal
                                                                        : Values
                                                                        .font_Weight_medium,
                                                                    color: StringUtil.isEmpty(
                                                                        _bankType)
                                                                        ? Values.of(context)
                                                                        .c_grey_front_cc
                                                                        : Values.of(context)
                                                                        .c_black_front_33,
                                                                    decoration:
                                                                    TextDecoration.none),
                                                              ),
                                                              SizedBox(width: Values.d_2),
                                                              Image.asset(
                                                                  'assets/images/right_icon.png')
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
                                              padding: EdgeInsets.only(
                                                  left: Values.d_15,
                                                  right: Values.d_15,
                                                  top: Values.d_5,
                                                  bottom: Values.d_5),
                                              child: Row(
                                                mainAxisAlignment:
                                                MainAxisAlignment.spaceBetween,
                                                children: <Widget>[
                                                  Text(
                                                    S.of(context).bank_card_number,
                                                    style: TextStyle(
                                                        fontSize: Values.s_text_15,
                                                        fontWeight: Values.font_Weight_normal,
                                                        color: Values.of(context)
                                                            .c_black_front_33,
                                                        decoration: TextDecoration.none),
                                                  ),
                                                  Expanded(
                                                    child: TextField(
                                                      controller: _bankCardIdController,
                                                      textAlign: TextAlign.right,
                                                      keyboardType: TextInputType.phone,
                                                      style: TextStyle(
                                                          color: Values.of(context)
                                                              .c_black_front_33,
                                                          fontWeight:
                                                          Values.font_Weight_medium,
                                                          fontSize: Values.s_text_14),
                                                      decoration: InputDecoration(
                                                          enabledBorder: UnderlineInputBorder(
                                                            borderSide: BorderSide(
                                                                color: Values.c_translucent),
                                                          ),
                                                          focusedBorder: UnderlineInputBorder(
                                                            borderSide: BorderSide(
                                                                color: Values.c_translucent),
                                                          ),
                                                          hintText: S
                                                              .of(context)
                                                              .bank_card_number_hint,
                                                          hintStyle: TextStyle(
                                                              color: Values.of(context)
                                                                  .c_grey_front_cc,
                                                              fontWeight:
                                                              Values.font_Weight_medium,
                                                              fontSize: Values.s_text_14)),
                                                      onSubmitted: (value) {
                                                        checkStatus();
                                                      },
                                                      cursorColor:
                                                      Values.of(context).c_black_front_33,
                                                    ),
                                                  ),
                                                ],
                                              ),
                                            ),
                                            lineWidget(),
                                            Container(
                                              color: Values.of(context).c_white_background,
                                              padding: EdgeInsets.only(
                                                  left: Values.d_15,
                                                  right: Values.d_15,
                                                  top: Values.d_5,
                                                  bottom: Values.d_5),
                                              child: Row(
                                                mainAxisAlignment:
                                                MainAxisAlignment.spaceBetween,
                                                children: <Widget>[
                                                  Text(
                                                    S.of(context).bank_user_tel,
                                                    style: TextStyle(
                                                        fontSize: Values.s_text_15,
                                                        fontWeight: Values.font_Weight_normal,
                                                        color: Values.of(context)
                                                            .c_black_front_33,
                                                        decoration: TextDecoration.none),
                                                  ),
                                                  Expanded(
                                                    child: TextField(
                                                      controller: _telController,
                                                      textAlign: TextAlign.right,
                                                      keyboardType: TextInputType.phone,
                                                      style: TextStyle(
                                                          color: Values.of(context)
                                                              .c_black_front_33,
                                                          fontWeight:
                                                          Values.font_Weight_medium,
                                                          fontSize: Values.s_text_14),
                                                      decoration: InputDecoration(
                                                          enabledBorder: UnderlineInputBorder(
                                                            borderSide: BorderSide(
                                                                color: Values.c_translucent),
                                                          ),
                                                          focusedBorder: UnderlineInputBorder(
                                                            borderSide: BorderSide(
                                                                color: Values.c_translucent),
                                                          ),
                                                          hintText: S
                                                              .of(context)
                                                              .bank_user_tel_hint,
                                                          hintStyle: TextStyle(
                                                              color: Values.of(context)
                                                                  .c_grey_front_cc,
                                                              fontWeight:
                                                              Values.font_Weight_medium,
                                                              fontSize: Values.s_text_14)),
                                                      onSubmitted: (value) {
                                                        checkStatus();
                                                      },
                                                      cursorColor:
                                                      Values.of(context).c_black_front_33,
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
                                                      ? Values.of(context)
                                                      .c_grey_background_cc
                                                      : Values.of(context)
                                                      .c_orange_background_0b,
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
                                                  shape: new RoundedRectangleBorder(
                                                      borderRadius:
                                                      new BorderRadius.circular(44.0)),
                                                ),
                                              ),
                                            )
                                          ],
                                        ),
                                      ))
                                ])));
            }));
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

  void loadOpenAccountInfo(){
    if(_isFirst){
      _openAccountViewModel.getOpenAccountInfo();
    }
  }
  /// 普通消息提示框
  void showDialogInfo(String message) {
    showDialog(
        context: context,
        child: DialogTool(
          bgColor: Values.of(context).c_white_background,
          title: S.of(context).warn_title,
          titleFontSize: Values.s_text_18,
          titleColor: Values.of(context).c_black_front_33,
          content: message,
          middleTitle: S.of(context).warn_button_title,
          middleColor: Values.of(context).c_white_front,
          middleBgColor: Values.of(context).c_orange_background_0b,
          contentColor: Values.of(context).c_black_front_33,
          onTap: (int index) {},
        ));
  }

  /// 普通消息提示框
  void showDialogResultInfo(bool success, String message) {
    showDialog(
        context: context,
        child: DialogOpenAccountResult(
            onTap: () {},
            statusImage: success
                ? 'assets/images/status_success.png'
                : 'assets/images/status_error.png',
            buttonContent: S.of(context).warn_button_title,
            statusTitle: success
                ? S.of(context).already_submit
                : S.of(context).open_company_account_fail,
            normalContent: success
                ? S.of(context).already_submit_content
                : S.of(context).open_company_account_fail_content,
            result: message));
  }

  void checkStatus() {
    if (StringUtil.isEmpty(_nameController.text)) {
      updateStatus(false);
      return;
    }
    if (StringUtil.isEmpty(_idType)) {
      updateStatus(false);
      return;
    }
    if (StringUtil.isEmpty(_idNumController.text)) {
      updateStatus(false);
      return;
    }
    if (StringUtil.isEmpty(_bankType)) {
      updateStatus(false);
      return;
    }
    if (StringUtil.isEmpty(_bankCardIdController.text)) {
      updateStatus(false);
      return;
    }
    if (StringUtil.isEmpty(_telController.text)) {
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
    if (StringUtil.isEmpty(_nameController.text)) {
      ProgressHUD.showError(warnText: S.of(context).person_name_hint);
      return false;
    }
    if (StringUtil.isEmpty(_idNumController.text)) {
      ProgressHUD.showError(warnText: S.of(context).user_id_card_number_hint);
      return false;
    }
    if (StringUtil.isEmpty(_bankType)) {
      ProgressHUD.showError(warnText: S.of(context).bank_choose_warn);
      return false;
    }
    if (StringUtil.isEmpty(_bankCardIdController.text)) {
      ProgressHUD.showError(warnText: S.of(context).bank_card_number_hint);
      return false;
    }
    if (StringUtil.isEmpty(_telController.text)) {
      ProgressHUD.showError(warnText: S.of(context).bank_user_tel_hint);
      return false;
    }
    return true;
  }

  void submitAction() {
//    showDialogResultInfo(true,'');
    if (!_isCanSubmit) {
      return;
    }
    if (!checkParams()) {
      return;
    }
    String realName = _nameController.text;
    String cardNo = _idNumController.text;
    String phone = _telController.text;
    String headBankCode = _bankTypeCode;
    String bankCode = _bankCardIdController.text;
    _openAccountViewModel.postOpenAccount(
        realName, cardNo, phone, headBankCode, bankCode, (info) {
      if (info.error_code == "1") {
        open(RouterName.web_view,
            argument: WebParams(
                title: S.of(context).open_person_account,
                url: info.retUrl,
                route: RouterName.pay_status,
                routeParams: {
                  'status': true,
                  'title': S.of(context).open_person_account,
                  'content': S.of(context).open_account_success,
                  'router': RouterName.index_page,
                  'action': S.of(context).pay_affirm_success_button1
                }));
      } else {
        showDialogInfo(info.message);
      }
    });
  }
}
