import 'package:flutter/rendering.dart';
import 'package:flutter_test/flutter_test.dart';
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
  }, onDone: () {
    print("onDone");
  });
}

void test1(String s) {
  test("新的测试", () {

  });
}
