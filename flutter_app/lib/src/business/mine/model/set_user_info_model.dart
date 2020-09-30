import 'dart:async';
import 'dart:io';

import 'package:dio/dio.dart';

import 'package:flutter_ienglish_fine/src/business/mine/bean/aliyun_cryp.dart';
import 'package:flutter_ienglish_fine/src/business/mine/bean/aliyun_update_result.dart';
import 'package:flutter_ienglish_fine/src/business/mine/comm/interfaces.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:dio/adapter.dart';

///  created by：sunyuancun
/// 2020/9/14
///desc:
class SetUserInfoModel extends BaseModel {
  Future<AliyunCryp> getAliYunCryp() async {
    return IEnglishNetClient().get(NET_GET_MSG_CRYP_TO_GET, queryParameters: {
      "length": 100000000,
    }).then((value) {
      return add(AliyunCryp.fromJson(value.data));
    });
  }

  Future<AliyunUpdateResult> updateHeaderImageToAliYun(AliyunCryp aliyunCryp, File file) async {
    String filePath = file.path;
    var fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length);
    MultipartFile multipartFile = await MultipartFile.fromFile(filePath, filename: fileName);

    FormData formData = FormData.fromMap({
      "name": fileName,
      "key": "res/${aliyunCryp.filename}",
      "policy": aliyunCryp.policyBase64,
      "OSSAccessKeyId": aliyunCryp.accessId,
      "success_action_status": 200.toString(),
      "callback": aliyunCryp.callback,
      "signature": aliyunCryp.signature,
      "file": multipartFile
    });


    Dio dio = Dio();
    dio.interceptors.add(LogInterceptor(requestBody: true, responseBody: true));
    (dio.httpClientAdapter as DefaultHttpClientAdapter).onHttpClientCreate = (client) {
      client.badCertificateCallback = (X509Certificate cert, String host, int port) {
        Logger.print(cert);
        //暂时不校验证书
        return true;
      };
    };
    dio.options.headers = {"content-type": "multipart/form-data"};
    dio.post("https://tope-test-baie-bj.oss-cn-beijing.aliyuncs.com/", data: formData).then((value) {
      Logger.print("--------log---response--------$value----");
    }).catchError((onError) {
      Logger.print("--------log---response---error-----$onError----");
    });


    // return IEnglishNetClient()
    //     .addHeader({"content-type": "multipart/form-data"})
    //     .post(aliyunCryp.host,
    //         options: RequestOptions(headers: {"content-type": "multipart/form-data"}), data: formData)
    //     .then((value) {
    //       Logger.print("--------log----result-------$value----");
    //       return add(AliyunUpdateResult.fromJson(value.data));
    //     })
    //     .catchError((onError) {
    //       Logger.print("--------log----result----error---$onError----");
    //     });
  }

  Future<NetBean> updateHeaderImageToServer(String url) async {
    return IEnglishNetClient().post(NET_POST_EDIT_USER_IMAGE, queryParameters: {
      "imgUrl": url,
    }).then((value) {
      return add(NetBean.fromJson(value.data));
    });
  }

  @override
  void dispose() {
    super.dispose();
    IEnglishNetClient().cancelRequestAll();
  }
}
