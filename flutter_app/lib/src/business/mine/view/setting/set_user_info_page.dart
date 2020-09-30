import 'dart:io';
import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/src/business/mine/viewmodel/set_user_info_view_model.dart';
import 'package:flutter_ienglish_fine/src/comm/event/user_info_event.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:image_picker/image_picker.dart';

class SetUserInfoPage extends StatefulWidget {
  @override
  _SetUserInfoPageState createState() => _SetUserInfoPageState();
}

class _SetUserInfoPageState extends State<SetUserInfoPage> with PageBridge {
  SetUserInfoViewModel _viewModel = SetUserInfoViewModel();
  final _picker = ImagePicker();

  @override
  Widget build(BuildContext context) {
    return RootPageWidget(
        appBar: IsAppBar(
          title: S.of(context).user_info_title,
        ),
        body: ListWidget());
  }

  Widget ListWidget() {
    return Column(
      children: <Widget>[
        ItemWiget(S.of(context).set_user_info_image, SpUtil.getUserImage(), true, true, () {
          _showPickImageDialog();
        }),
        lineWidget(),
        ItemWiget(S.of(context).set_user_info_name, SpUtil.getUserName(), false, false, () {}),
        lineWidget(),
        ItemWiget(S.of(context).set_user_info_tel, SpUtil.getUserMobile(), false, false, () {}),
        SizedBox(height: Values.d_10),
        ItemWiget(S.of(context).set_user_info_place, '', false, true, () {
          open(RouterName.place_list);
        }),
      ],
    );
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

  Widget ItemWiget(String name, String content, bool isImage, bool isEdit, Function() callback) {
    return GestureDetector(
      child: Container(
        color: Values.of(context).c_white_background,
        height: isImage ? Values.d_60 : Values.d_50,
        padding: EdgeInsets.only(left: Values.d_15, right: Values.d_15),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            Text(
              name,
              style: TextStyle(
                  fontSize: Values.s_text_15,
                  fontWeight: Values.font_Weight_normal,
                  color: Values.of(context).c_black_front_33,
                  decoration: TextDecoration.none),
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.end,
              children: <Widget>[
                isImage
                    ? Container(
                        decoration: BoxDecoration(
                          border: Border.all(color: Values.of(context).c_white_background, width: 1),
                          borderRadius: new BorderRadius.circular((Values.d_36 / 2)), // 圆角度
                        ),
                        width: Values.d_36,
                        height: Values.d_36,
                        child: ClipOval(
                          child: CachedNetworkImage(
                            imageUrl: StringUtil.getNotNullString(content),
                            fit: BoxFit.cover,
                            width: Values.d_36,
                            height: Values.d_36,
                            placeholder: (BuildContext context, String url) {
                              return Container(
                                color: Values.of(context).c_grey_background_cc,
                              );
                            },
                          ),
                        ))
                    : Container(),
                !isImage && !StringUtil.isEmpty(content)
                    ? Text(
                        content,
                        style: TextStyle(
                            fontSize: Values.s_text_15,
                            fontWeight: Values.font_Weight_normal,
                            color: Values.of(context).c_black_front_33,
                            decoration: TextDecoration.none),
                      )
                    : Container(),
                SizedBox(width: Values.d_5),
                isEdit ? Image.asset('assets/images/right_icon.png') : Container()
              ],
            )
          ],
        ),
      ),
      onTap: () {
        callback();
      },
    );
  }

  _showPickImageDialog() async {
    showModalBottomSheet(
        context: context,
        backgroundColor: Colors.transparent,
        builder: (BuildContext context) {
          return Container(
            decoration: BoxDecoration(
                color: Values.of(context).c_white_background,
                borderRadius: BorderRadius.only(topLeft: Radius.circular(10), topRight: Radius.circular(10))),
            height: 200.0,
            child: Column(
              children: <Widget>[
                ListTile(
                  title: Text(S.of(context).user_header_camera,
                      textAlign: TextAlign.center,
                      style: TextStyle(color: Values.of(context).c_black_front_33, fontSize: Values.s_text_18)),
                  onTap: () {
                    Navigator.pop(context);
                    _editUserInfoImage(isGallery: false);
                  },
                ),
                Container(
                  height: Values.d_1,
                  color: Values.of(context).c_grey_ea,
                ),
                ListTile(
                  title: Text(S.of(context).user_header_gallery,
                      textAlign: TextAlign.center,
                      style: TextStyle(color: Values.of(context).c_black_front_33, fontSize: Values.s_text_18)),
                  onTap: () {
                    Navigator.pop(context);
                    _editUserInfoImage(isGallery: true);
                  },
                ),
                Container(
                  height: Values.d_8,
                  color: Values.of(context).c_grey_ea,
                ),
                ListTile(
                  title: Text(S.of(context).user_header_cancel,
                      textAlign: TextAlign.center,
                      style: TextStyle(color: Values.of(context).c_black_front_33, fontSize: Values.s_text_18)),
                  onTap: () {
                    Navigator.pop(context);
                  },
                ),
              ],
            ),
          );
        });
  }

  _editUserInfoImage({bool isGallery = true}) async {
    final pickedFile = await _picker.getImage(source: isGallery ? ImageSource.gallery : ImageSource.camera);

    if (pickedFile == null || pickedFile.path == null) {
      Toast.show(S.of(context).user_header_edit_cancel);
      return;
    }

    var aliyunCryp = await _viewModel.getAliYunCryp();

    if (aliyunCryp == null) {
      Toast.show(S.of(context).user_header_edit_Failed);
      return;
    }

    ///阿里云上传逻辑
    var aliyunUpdateResult = await _viewModel.updateHeaderImageToAliYun(aliyunCryp, File(pickedFile.path));

    if (aliyunUpdateResult == null || aliyunUpdateResult.responseBody == null) {
      Toast.show(S.of(context).user_header_edit_Failed);
      return;
    }

    Map<String, dynamic> aliyunResultMap = jsonDecode(aliyunUpdateResult.responseBody);

    if (aliyunResultMap != null && aliyunResultMap.containsKey('filename')) {
      _viewModel.updateHeaderImageToServer(aliyunResultMap['filename']).then((value) {
        setState(() {
          Logger.print("save-----------------${aliyunResultMap['filename']}");
          //刷新UI
          SpUtil.setUserImage(aliyunResultMap['filename']);
          Toast.show(S.of(context).user_header_edit_success);
          eventCenter.emit(UserInfoEvent(type: USER_INFO_EVENT_TYPE_1));
        });
      });
    }

  }




}
