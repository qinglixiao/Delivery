import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_html/flutter_html.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/src/business/budget/bean/good_detail.dart';
import 'package:flutter_ienglish_fine/src/business/budget/viewmodel/good_detail_view_model.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:fluwx/fluwx.dart';

///商品详情
class GoodDetailPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _GoodDetailPageState();
  }
}

class _GoodDetailPageState extends State<GoodDetailPage> with PageBridge {
  GoodDetailViewModel _viewModel = GoodDetailViewModel();
  var _collocationId;
  String _fromType;

  _onBack() {
    pop();
  }

  _onShareTap() {
    weChatShare.shareText(WeChatShareTextModel("source text", scene: WeChatScene.SESSION));
  }

  _buyTap(GoodDetail data) {
    open(RouterName.good_confirm_page, argument: {'id': data.collocationId.toInt().toString(), 'isCombo': !data.ifPlain});
  }

  @override
  void initState() {
    super.initState();
    weChatShare.init();
    weChatShare.addResponseListener((value) {
      BaseWeChatResponse response = value as BaseWeChatResponse;
      print("-----------${response.errCode}");
    });
  }

  @override
  Widget build(BuildContext context) {
    getInitArg(context).then((value) {
      if (value != null) {
        Map arg = value as Map;
        _collocationId = arg.containsKey("collocationId") ? arg["collocationId"] : -1;
        _fromType = arg.containsKey("fromType") ? arg["fromType"] : null;
        _viewModel.getGoodDetail(_collocationId);
      }
    });
    return RootPageWidget(
        viewModel: _viewModel,
        body: StreamBuilder<GoodDetail>(
            stream: _viewModel.detailStream,
            builder: (BuildContext context, AsyncSnapshot<GoodDetail> snapshot) {
              if (snapshot.data == null) {
                return Container();
              }
              return Stack(
                children: <Widget>[
                  Positioned(
                    child: CustomScrollView(
                      slivers: <Widget>[
                        SliverPersistentHeader(
                          pinned: true,
                          delegate: AppearOpacityHeaderDelegate(
                            context,
                            maxExtend: 300,
                            backTap: _onBack,
                            shareTap: _onShareTap,
                            widget: CachedNetworkImage(
                              fit: BoxFit.fitWidth,
                              filterQuality: FilterQuality.high,
                              imageUrl: snapshot.data.imgUrl ?? "",
                            ),
                          ),
                        ),
                        SliverToBoxAdapter(
                          child: Container(
                            color: Values.of(context).c_white_background,
                            height: 80,
                            padding: EdgeInsets.only(left: 20, top: 12, bottom: 12),
                            margin: EdgeInsets.only(bottom: 14, top: 10),
                            child: Column(
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: <Widget>[
                                Text(
                                  snapshot.data.title ?? "",
                                  style: TextStyle(fontSize: 16, color: Values.of(context).c_black_front_33),
                                ),
                                SizedBox(
                                  height: 5,
                                ),
                                Text(
                                  S.of(context).good_total_price(snapshot.data.totalPrice),
                                  style: TextStyle(fontSize: 14, color: Values.of(context).c_red_front_68),
                                ),
                              ],
                            ),
                          ),
                        ),
                        SliverList(
                          delegate: SliverChildListDelegate([
                            Container(
                                color: Values.of(context).c_white_background,
                                child: Html(
                                  data: snapshot.data.text ?? "",
                                )),
                          ]),
                        ),
                      ],
                    ),
                  ),
                  Positioned(
                      bottom: 0,
                      child: SafeArea(
                          child: Container(
                        height: 50,
                        width: MediaQuery.of(context).size.width,
                        child: FlatButton(
                            color: Values.of(context).c_orange_background_0b,
                            disabledColor: Values.of(context).c_grey_background_cc,
                            onPressed: () {
                              if (_fromType != null && _fromType == S.of(context).invite_txt) {
                                _onShareTap();
                                return;
                              }
                              _buyTap(snapshot.data);
                            },
                            highlightColor: Values.c_translucent,
                            splashColor: Values.c_translucent,
                            child: Text(
                              _fromType != null && _fromType == S.of(context).invite_txt
                                  ? S.of(context).invite_friend_now_title
                                  : S.of(context).good_btn_buy,
                              style: TextStyle(fontSize: Values.s_text_18, color: Values.of(context).c_white_front),
                            )),
                      )))
                ],
              );
            }));
  }
}

class AppearOpacityHeaderDelegate extends SliverPersistentHeaderDelegate {
  final double _maxExtend;

  Widget _widget;

  double _paddingTop;

  final _headerHeight = app_bar_height;

  VoidCallback _onBack;

  VoidCallback _onShare;

  AppearOpacityHeaderDelegate(BuildContext context,
      {double maxExtend, Widget widget, VoidCallback backTap, VoidCallback shareTap})
      : _maxExtend = maxExtend,
        _widget = widget,
        _onBack = backTap,
        _onShare = shareTap {
    _paddingTop = MediaQuery.of(context).padding.top;
  }

  /// 0<= offset <=maxExtend
  onHeaderScrollListener(BuildContext context, double offset) {
    double region = this.maxExtent - this.minExtent;

    final int alpha = (offset / region * 255).clamp(0, 255).toInt();

    double pic_dispear_per = 0.3;

    _picOpa = (1 - offset / region / pic_dispear_per).clamp(0, 1).toDouble();

    _navIconOpa = _picOpa == 0 ? (offset / region - pic_dispear_per).clamp(0, 1).toDouble() : 0;

    _titleColor = Theme.of(context).appBarTheme.textTheme.headline1.color.withAlpha(alpha);

    _bgColor = Theme.of(context).appBarTheme.color.withAlpha(alpha);
    _iconColor = Theme.of(context).appBarTheme.textTheme.headline1.color;
  }

  var _titleColor;
  var _bgColor;
  var _iconColor;
  double _picOpa = 1;
  double _navIconOpa = 0;

  @override
  Widget build(BuildContext context, double shrinkOffset, bool overlapsContent) {
    onHeaderScrollListener(context, shrinkOffset);
    return Container(
      height: this.maxExtent,
      width: MediaQuery.of(context).size.width,
      child: Stack(
        fit: StackFit.expand,
        children: <Widget>[
          _widget,
          Positioned(
            left: 0,
            right: 0,
            top: 0,
            child: IsAppBar(
                title: S.of(context).good_detail,
                color: _bgColor,
                titleColor: _titleColor,
                leftWidget: GestureDetector(
                    onTap: _onBack,
                    child: Stack(
                      children: <Widget>[
                        Positioned(
                            child: Opacity(
                          opacity: _picOpa,
                          child: Image.asset(
                            "assets/images/icon_pic_back.png",
                          ),
                        )),
                        Positioned(
                            child: Opacity(
                          opacity: _navIconOpa,
                          child: Image.asset(
                            "assets/images/icon_back.png",
                            color: _iconColor,
                          ),
                        )),
                      ],
                    )),
                rightWidget: GestureDetector(
                  onTap: _onShare,
                  child: Stack(
                    children: <Widget>[
                      Positioned(
                        child: Opacity(
                          opacity: _picOpa,
                          child: Image.asset(
                            "assets/images/icon_pic_share.png",
                          ),
                        ),
                      ),
                      Positioned(
                        child: Opacity(
                          opacity: _navIconOpa,
                          child: Image.asset(
                            "assets/images/share.png",
                            color: _iconColor,
                          ),
                        ),
                      ),
                    ],
                  ),
                )),
          ),
        ],
      ),
    );
  }

  @override
  double get maxExtent => _maxExtend;

  @override
  double get minExtent => _paddingTop + _headerHeight;

  @override
  bool shouldRebuild(SliverPersistentHeaderDelegate oldDelegate) {
    return true;
  }
}
