import 'package:flutter/cupertino.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/src/business/invite/bean/collocation_activity_list.dart';
import 'package:flutter_ienglish_fine/src/business/invite/bean/collocation_list.dart';
import 'package:flutter_ienglish_fine/src/business/invite/viewmodel/invite_friend_viewmodel.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_lib/flutter_lib.dart';

import 'invite_collocation_widget.dart';

///  created by：sunyuancun
/// 2020/9/8 20
///desc:

class InviteFriendPage extends StatefulWidget {
  @override
  _InviteFriendPageState createState() => _InviteFriendPageState();
}

class _InviteFriendPageState extends State<InviteFriendPage> with PageBridge {
  InviteFriendViewModel _inviteFriendViewModel = InviteFriendViewModel();

  @override
  Widget build(BuildContext context) {
    return RootPageWidget(
        appBar: IsAppBar(
          title: S.of(context).invite_friend_title,
        ),
        viewModel: _inviteFriendViewModel,
        task: _inviteFriendViewModel.loadInviteFriendData(),
        body: _buildScrollWidget());
  }

  Widget _buildScrollWidget() {
    return Container(
      color: Values.c_blue_b2,
      child: CustomScrollView(
        slivers: <Widget>[
          SliverToBoxAdapter(
            child: _buildheaderWidget(),
          ),
          _buildActivityCollocationsWidget(),
          SliverToBoxAdapter(
            child: SizedBox(height: 20),
          ),
          _buildCollocationsWidget(),
          _buildFooterWidget()
        ],
      ),
    );
  }

  Widget _buildActivityCollocationsWidget() {
    return StreamBuilder(
      stream: _inviteFriendViewModel.streamActivityCollocationList,
      builder: (BuildContext context, AsyncSnapshot<CollocationActivityList> snapshot) {
        if (snapshot.data == null || snapshot.data.items == null || snapshot.data.items.length == 0) {
          return SliverToBoxAdapter(
            child: Container(),
          );
        }

        return SliverList(
          delegate: SliverChildBuilderDelegate(
            (BuildContext context, int index) {
              CollocationListItem item = CollocationListItem()
                ..id = snapshot.data.items[index].id
                ..title = snapshot.data.items[index].title
                ..coverImgUrl = snapshot.data.items[index].coverImgUrl
                ..relayDesc = snapshot.data.items[index].relayDesc
                ..directRankNums = snapshot.data.items[index].directRankNums
                ..totalPrice = snapshot.data.items[index].totalPrice
                ..price = snapshot.data.items[index].price
                ..quantity = snapshot.data.items[index].quantity
                ..ifPlain = snapshot.data.items[index].ifPlain;
              return InviteCollocationWidget(item, index, snapshot.data.items.length - 1, (collocationId) {
                open(RouterName.good_detail, argument: {"collocationId": collocationId,"fromType":S.of(context).invite_txt});
              });
            },
            childCount: snapshot.data.items.length,
          ),
        );
      },
    );
  }

  Widget _buildCollocationsWidget() {
    return StreamBuilder(
      stream: _inviteFriendViewModel.streamCollocationList,
      builder: (BuildContext context, AsyncSnapshot<CollocationList> snapshot) {
        if (snapshot.data == null || snapshot.data.items == null || snapshot.data.items.length == 0) {
          return SliverToBoxAdapter(
            child: Container(),
          );
        }

        return SliverList(
          delegate: SliverChildBuilderDelegate(
            (BuildContext context, int index) {
              return InviteCollocationWidget(snapshot.data.items[index], index, snapshot.data.items.length - 1,
                  (collocationId) {
                    open(RouterName.good_detail, argument: {"collocationId": collocationId,"fromType":S.of(context).invite_txt});
              });
            },
            childCount: snapshot.data.items.length,
          ),
        );
      },
    );
  }

  Widget _buildheaderWidget() {
    final size = MediaQuery.of(context).size;
    final width = size.width;
    return Container(
      width: width,
      child: Stack(
        children: <Widget>[
          Container(
            padding: EdgeInsets.only(bottom: 200),
            child: Image.asset('assets/images/invite_bg.png', fit: BoxFit.fill),
          ),
          Positioned(
            bottom: 10,
            child: Container(
              width: width,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.center,
                children: <Widget>[
                  Container(
                    alignment: Alignment.center,
                    padding: EdgeInsets.only(top: 20, bottom: 20),
                    margin: EdgeInsets.only(left: 15, right: 15),
                    decoration: new BoxDecoration(
                      color: Values.of(context).c_white_background,
                      borderRadius: BorderRadius.all(Radius.circular(15)),
                    ),
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: <Widget>[
                        Row(
                          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                          children: <Widget>[
                            _buildImageWidget('assets/images/invite_share_icon.png'),
                            _buildRightArrowWidget(),
                            _buildImageWidget('assets/images/invite_regist_icon.png'),
                            _buildRightArrowWidget(),
                            _buildImageWidget('assets/images/invite_buy_icon.png'),
                            _buildRightArrowWidget(),
                            _buildImageWidget('assets/images/invite_customer_icon.png'),
                          ],
                        ),
                        Row(
                          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                          children: <Widget>[
                            _buildTextWidget(S.of(context).invite_share_title),
                            _buildTextSpaceWidget(),
                            _buildTextWidget(S.of(context).invite_friend_title),
                            _buildTextSpaceWidget(),
                            _buildTextWidget(S.of(context).invite_buy_title),
                            _buildTextSpaceWidget(),
                            _buildTextWidget(S.of(context).invite_customer_title),
                          ],
                        ),
                      ],
                    ),
                  ),
                  SizedBox(height: 10),
                  Image.asset('assets/images/invite_star_icon.png'),
                  SizedBox(height: 10),
                ],
              ),
            ),
          )
        ],
      ),
    );
  }

  Widget _buildFooterWidget() {
    return SliverToBoxAdapter(
      child: Column(
        children: <Widget>[
          SizedBox(height: 20),
          Container(
            margin: EdgeInsets.only(left: 15, right: 15),
            padding: EdgeInsets.all(15),
            decoration: BoxDecoration(color: Values.c_blue_b3, borderRadius: BorderRadius.circular(10)),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                Text(S.of(context).invite_rule_txt_1,
                    style: TextStyle(
                        color: Values.of(context).c_white_front,
                        fontSize: Values.s_text_13,
                        fontWeight: Values.font_Weight_normal)),
                SizedBox(height: 10),
                Text(S.of(context).invite_rule_txt_2,
                    style: TextStyle(
                        color: Values.of(context).c_white_front,
                        fontSize: Values.s_text_13,
                        fontWeight: Values.font_Weight_normal)),
                SizedBox(height: 10),
                Text(S.of(context).invite_rule_txt_3,
                    style: TextStyle(
                        color: Values.of(context).c_white_front,
                        fontSize: Values.s_text_13,
                        fontWeight: Values.font_Weight_normal)),
                SizedBox(height: 10),
                Text(S.of(context).invite_rule_txt_4,
                    style: TextStyle(
                        color: Values.of(context).c_white_front,
                        fontSize: Values.s_text_13,
                        fontWeight: Values.font_Weight_normal)),
              ],
            ),
          ),
          SizedBox(height: 20),
        ],
      ),
    );
  }

  Widget _buildRightArrowWidget() {
    return Container(
      width: 16,
      height: 10,
      child: Center(
        child: Image.asset(
          'assets/images/invite_right_icon.png',
          fit: BoxFit.fill,
        ),
      ),
    );
  }

  Widget _buildTextSpaceWidget() {
    return Container(
      width: 16,
      height: 10,
    );
  }

  Widget _buildTextWidget(String text) {
    return Container(
      width: 69,
      child: Center(
        child: Text(text,
            maxLines: 2,
            overflow: TextOverflow.ellipsis,
            textAlign: TextAlign.center,
            style: TextStyle(color: Values.of(context).c_black_front_66, fontSize: Values.s_text_11)),
      ),
    );
  }

  Widget _buildImageWidget(String path) {
    return Container(
      width: 50,
      height: 50,
      child: Center(
        child: Image.asset(
          path,
          fit: BoxFit.fill,
        ),
      ),
    );
  }
}
