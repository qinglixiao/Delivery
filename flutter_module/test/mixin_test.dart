import 'package:flutter_test/flutter_test.dart';

main() {
  test("mixin test", () {
    Child().reject();
  });
}

class Child with Parent2, Parent1, Parent {
  @override
  inject() {
    return super.inject();
  }

  @override
  void reject() {
    super.reject();
  }
}

mixin Parent on Parent1 {
  reject() {
    super.reject();
    print("parent reject");
  }

  inject() {
    print("parent inject");
  }
}

mixin Parent1 on Parent2 {
  @override
  reject() {
    super.reject();
    print("parent1 reject");
  }

  inject() {
    print("parent1 inject");
  }
}

mixin Parent2 {
  void reject() {
    print("parent2 reject");
  }

  inject() {
    print("parent2 inject");
  }
}
