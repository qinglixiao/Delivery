import 'dart:io';
import 'dart:async';
import 'package:dio/dio.dart';
import 'package:http_parser/http_parser.dart';
import 'package:flutter_ienglish_fine/src/business/mine/bean/aliyun_cryp.dart';
import 'package:flutter_ienglish_fine/src/business/mine/bean/aliyun_update_result.dart';
import 'package:flutter_ienglish_fine/src/business/mine/model/set_user_info_model.dart';
import 'package:flutter_lib/flutter_lib.dart';

///  created by：sunyuancun
/// 2020/9/14
///desc:

class SetUserInfoViewModel extends BaseViewModel {
  SetUserInfoModel _setUserInfoModel = SetUserInfoModel();

  Future<AliyunCryp> getAliYunCryp() async {
    return _setUserInfoModel.getAliYunCryp();
  }

  Future<AliyunUpdateResult> updateHeaderImageToAliYun(AliyunCryp aliyunCryp, File file) async {
    return _setUserInfoModel.updateHeaderImageToAliYun(aliyunCryp,  file).catchError((onError){
    });
  }

  Future<NetBean> updateHeaderImageToServer(String url) async {
    return _setUserInfoModel.updateHeaderImageToServer(url);
  }

  @override
  void dispose() {
    super.dispose();
    _setUserInfoModel?.close();
  }
}
