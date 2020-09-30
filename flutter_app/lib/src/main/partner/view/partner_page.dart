import 'package:flutter/cupertino.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_lib/flutter_lib.dart';

var url = "https://qa-agent.tope365.com/#/iToAgent?type=2&showNav=false&mobile=15990109999";

class PartnerPage extends StatefulWidget {
  @override
  _PartnerPageState createState() => _PartnerPageState();
}

class _PartnerPageState extends State<PartnerPage> with AutomaticKeepAliveClientMixin {
  @override
  bool get wantKeepAlive => true;

  @override
  Widget build(BuildContext context) {
    return RootPageWidget(
      appBar: IsAppBar(
        title: S.of(context).tab_plateform,
        titleColor: Values.of(context).c_white_front,
        color: [Values.of(context).c_blue_background_light, Values.of(context).c_blue_background_dark],
        canBack: false,
      ),
      body: IAgentWebPage(
        params: WebParams(
          showTitle: false,
          url: "https://qa-agent.tope365.com/#/iToAgent?type=2&showNav=false&mobile=${SpUtil.getUserMobile()}",
        ),
      ),
    );
  }
}
