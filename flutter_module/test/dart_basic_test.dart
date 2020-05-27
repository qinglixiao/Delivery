import 'dart:io';

void main() {
  var c = H();
  c("hello", "dart");
  method add;

  getTask().then((value) => print(value));

  sleep(Duration(seconds: 6));
}

void add(int a, int b) => a + b;

class H {
  call(String p1, String p2) => print(p1 + " " + p2);
}

typedef method = void Function(int a, int b);

Future<String> getTask() async {
  sleep(Duration(seconds: 1));
  print("getTask");
  return "it";
}

class P {
  var name;
  var age;

  P(this.name, this.age);

  P.fromJson(Map json) {}

  @override
  int get hashCode {
    int result = 17;
  }

  @override
  bool operator ==(other) {
    return super == other;
  }
}
