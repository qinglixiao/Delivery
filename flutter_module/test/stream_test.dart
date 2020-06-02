import 'package:flutter/rendering.dart';
import 'package:rxdart/rxdart.dart';

main() {
  BehaviorSubject subject = BehaviorSubject.seeded(3);
  subject.add(4);
  subject.addError(ErrorDescription);
  subject.doOnDone(() {
    print("onOnDone");
  });

//  subject.stream.listen((value) {
//    print(value);
//  });

  subject.stream.listen((value) {
    print(value);
  }, onError: (error) {
    print(error.toString());
  },onDone: (){
    print("onDone");
  });
}
