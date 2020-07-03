import 'package:flutter/cupertino.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:fluttermodule/generated/l10n.dart';

class PartnerPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return RootPageWidget(
      body: Center(
        child: Text(S.of(context).tab_plateform),
      ),
    );
  }
}
