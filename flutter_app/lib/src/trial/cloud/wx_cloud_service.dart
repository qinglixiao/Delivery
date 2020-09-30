import 'dart:async';
import 'dart:core';

import 'package:cloudbase_auth/cloudbase_auth.dart';
import 'package:cloudbase_core/cloudbase_core.dart';
import 'package:cloudbase_database/cloudbase_database.dart';
import 'package:cloudbase_storage/cloudbase_storage.dart';
import 'package:flutter_lib/flutter_lib.dart';

///腾讯云开发
///create by lx
///date 2020/9/29
///
class WxCloudService {
  final _env = "lx-cloud-store-4g731vcb2400d43f";
  final _key = "f280f6634ce021bf44c76469a986c65c";
  final _version = "1";

  static WxCloudService _service = WxCloudService._();

  factory WxCloudService() {
    return _service;
  }

  WxCloudService._() {
    _initEnv();
    _signInAnonymously();
  }

  Completer<CloudBaseAuthState> _completer;

  CloudBaseAuth _auth;
  CloudBaseCore _core;

  _WxCloudFileService get fileService => _WxCloudFileService(this);

  _WxDbService get dbService => _WxDbService(this);

  _initEnv() {
    _core = CloudBaseCore.init({
      "env": _env,
      'appAccess': {'key': _key, 'version': _version}
    });
    _auth = CloudBaseAuth(_core);
  }

  ///匿名登录
  _signInAnonymously() async {
    _completer = Completer<CloudBaseAuthState>();
    await _auth.signInAnonymously().then((value) {
      _completer.complete(value);
      Logger.print("cloud login success !");
    }).catchError((e) {
      _completer.completeError(e);
      Logger.print("cloud login fail !");
    });
  }

  Future<Result> call(String name, Map<String, dynamic> params) async {
    func() async {}
    _ensureLogin(func);
  }

  _isLogin() async {
    return await _auth.getAuthState() != null;
  }

  _ensureLogin(void callBack()) async {
    if (!await _isLogin()) {
      _signInAnonymously();
      Future.wait<CloudBaseAuthState>([_completer.future]).then((value) {
        callBack.call();
      });
    } else {
      callBack.call();
    }
  }
}

class _WxCloudFileService {
  WxCloudService _service;
  CloudBaseStorage _storage;

  _WxCloudFileService(this._service) : _storage = CloudBaseStorage(_service._core);

  ///接口功能：上传文件到文件管理服务
  ///上传文件成功时，uploadFile 接口会返回空，失败时则会抛出错误
  ///
  uploadFile({String cloudPath, String localPath}) async {
    _service._ensureLogin(() {
      return _storage.uploadFile(
          cloudPath: cloudPath,
          filePath: localPath,
          onProcess: (int count, int total) {
            // 当前进度
            Logger.print(count);
            // 总进度
            Logger.print(total);
          });
    });
  }

  ///下载文件成功时，downloadFile 接口会返回空，失败时则会抛出错误。
  downloadFile({String fileId, String savePath}) async {
    _service._ensureLogin(() {
      // 下载文件
      // String fileId = 'cloud://xxxx';
      return _storage.downloadFile(
          fileId: fileId,
          savePath: savePath,
          onProcess: (int count, int total) {
            // 当前进度
            Logger.print(count);
            // 总进度
            Logger.print(total);
          });
    });
  }

  Future<CloudBaseStorageRes<List<DeleteMetadata>>> deleteFiles(List<String> fileIds) async {
    _service._ensureLogin(() {
      return _storage.deleteFiles(fileIds);
    });
  }

  ///
  ///接口功能：获取文件下载链接，可以自定义实现下载文件的方法。
  // List<String> fileIds = [
  //   'cloud://xxxx'
  // ];
  Future<CloudBaseStorageRes<List<DownloadMetadata>>> getFileDownloadURL(List<String> fileIds) async {
    _service._ensureLogin(() {
      // {'fileId': 'xxx', 'downloadUrl': 'https://xxx'}
      return _storage.getFileDownloadURL(fileIds);
    });
  }

  ///
  /// 输出参数
  /// 字段	类型	必填	说明
  /// code	String	否	状态码，操作成功则为 SUCCESS
  /// message	String	否	错误描述
  /// data	List<dynamic>	否	存储上传文件属性的数组
  /// requestId	String	否	请求序列号，用于错误排查
  ///
  /// data
  /// 字段	类型	必填	说明
  /// code	string	否	删除结果，成功为 SUCCESS
  /// message	string	否	错误描述
  /// url	string	是	上传文件的 url
  /// token	string	是	访问 token
  /// authorization	string	是	访问授权信息
  /// cosFileId	string	是	文件 id
  ///
  ///接口功能：获取上传文件需要的属性，可用于实现自定义上传文件的逻辑。
  ///cloudPath:云端文件路径
  Future<CloudBaseStorageRes<UploadMetadata>> getUploadMetadata(String cloudPath) async {
    _service._ensureLogin(() {
      return _storage.getUploadMetadata(cloudPath);
    });
  }
}

class _WxDbService {
  WxCloudService _service;
  CloudBaseDatabase _db;
  Collection _coll;

  _WxDbService(this._service) : _db = CloudBaseDatabase(_service._core);

  _WxDbService collection(String name) {
    _coll = _db.collection(name);
    return this;
  }
}

class Result {
  String code;
  String message;
  dynamic data;
  String requestId;
}
