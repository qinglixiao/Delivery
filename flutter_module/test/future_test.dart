import 'package:flutter_test/flutter_test.dart';

///
///create by lx
///date 2020/7/9
///

main() {
  test("future complete", () {
    futureComplete()
        .then(
          (value) => print(value),
        )
        .whenComplete(
          () => print("complete"),
        )
        .catchError(
      (e) {
        print(e);
      },
    );
  });
}

Future futureComplete() async {
//  return "finish";
}
