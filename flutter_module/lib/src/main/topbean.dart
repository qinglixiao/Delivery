import 'package:flutter_lib/flutter_lib.dart';

class TopBean extends NetBean {
  int status;
  List<Message> _message;

  TopBean.fromJson(Map<String, dynamic> json) {
    status = json['status'];
    if (json['message'] != null) {
      _message = new List<Message>();
      json['message'].forEach((v) {
        _message.add(new Message.fromJson(v));
      });
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['status'] = this.status;
    if (this._message != null) {
      data['message'] = this._message.map((v) => v.toJson()).toList();
    }
    return data;
  }
}

class Message {
  int id;
  String url;
  String img;

  Message({this.id, this.url, this.img});

  Message.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    url = json['url'];
    img = json['img'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['url'] = this.url;
    data['img'] = this.img;
    return data;
  }
}
