import 'dart:io';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';

import 'wx_cloud_service.dart';
import 'package:image_picker/image_picker.dart';

///
///create by lx
///date 2020/9/25
///

class AuthLoginPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _AuthLoginState();
  }
}

class _AuthLoginState extends State<AuthLoginPage> {
  @override
  Widget build(BuildContext context) {
    return RootPageWidget(
        appBar: IsAppBar(
          title: "云登录",
        ),
        body: Container(
            child: Column(
          children: <Widget>[
            SizedBox(
                width: MediaQuery.of(context).size.width,
                child: RaisedButton(
                  child: Text("上传文件"),
                  onPressed: () async {
                    PickedFile file = await ImagePicker().getImage(source: ImageSource.camera);
                    WxCloudService().fileService.uploadFile(cloudPath: "1.png", localPath: file.path).then((value) {
                      if (value == null) {
                        Toast.show("上传成功！");
                      }
                    });
                  },
                )),
            SizedBox(
                width: MediaQuery.of(context).size.width,
                child: RaisedButton(
                  child: Text("下载文件"),
                  onPressed: () async {
                    Directory path = await PathProvider.tempDirectory();
                    WxCloudService()
                        .fileService
                        .downloadFile(
                            fileId:
                                "cloud://lx-cloud-store-4g731vcb2400d43f.6c78-lx-cloud-store-4g731vcb2400d43f-1301878359/1.png",
                            savePath: path.path)
                        .then((value) {
                      if (value == null) {
                        Toast.show("下载成功！");
                      }
                    });
                  },
                )),
            SizedBox(
                width: MediaQuery.of(context).size.width,
                child: RaisedButton(
                  child: Text("下载文件"),
                  onPressed: () async {
                    Directory path = await PathProvider.tempDirectory();
                    WxCloudService()
                        .fileService
                        .downloadFile(
                            fileId:
                                "cloud://lx-cloud-store-4g731vcb2400d43f.6c78-lx-cloud-store-4g731vcb2400d43f-1301878359/1.png",
                            savePath: path.path)
                        .then((value) {
                      if (value == null) {
                        Toast.show("下载成功！");
                      }
                    });
                  },
                )),
            SizedBox(
                width: MediaQuery.of(context).size.width,
                child: RaisedButton(
                  child: Text("删除文件"),
                  onPressed: () async {
                    Directory path = await PathProvider.tempDirectory();
                    WxCloudService().fileService.deleteFiles([
                      "cloud://lx-cloud-store-4g731vcb2400d43f.6c78-lx-cloud-store-4g731vcb2400d43f-1301878359/\\"
                    ]).then((value) {
                      if (value == null) {
                        Toast.show("删除成功！");
                      }
                    });
                  },
                )),
          ],
        )));
  }
}
