import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:fluttermodule/generated/l10n.dart';
import 'package:fluttermodule/src/main/me_page.dart';
import 'package:fluttermodule/src/main/partner_page.dart';

import 'home_page.dart';

//内容页
List<Widget> _createPages() {
  return [
    HomePage(),
    PartnerPage(),
    MePage(),
  ];
}

//底部导航
List<BottomNavigationBarItem> _createButtomTab(BuildContext context) {
  return [
    BottomNavigationBarItem(
        icon: Icon(Icons.home), title: Text(S.of(context).tab_home)),
    BottomNavigationBarItem(
        icon: Icon(Icons.business), title: Text(S.of(context).tab_plateform)),
    BottomNavigationBarItem(
        icon: Icon(Icons.school), title: Text(S.of(context).tab_me)),
  ];
}

int INDEX_DEFAULT_PAGE = 0; //默认tab

class IndexPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => IndexPageState();
}

class IndexPageState extends State<IndexPage>
    with SingleTickerProviderStateMixin {
  PageController _tabViewControl;
  List<Widget> _tab_pages;

  Widget _current_page;

  Widget get currentPage => _current_page;

  @override
  void initState() {
    super.initState();
    _tab_pages = _createPages();
    _current_page = _tab_pages[INDEX_DEFAULT_PAGE];
    _tabViewControl = PageController(initialPage: INDEX_DEFAULT_PAGE);
  }

  @override
  Widget build(BuildContext context) {
    return RootPageWidget(
      appBar: IsAppBar(
        title: S.of(context).home_title,
        canBack: false,
      ),
      body: Scaffold(
        body: PageView(
          controller: _tabViewControl,
          physics: NeverScrollableScrollPhysics(),
          children: _tab_pages,
        ),
        bottomNavigationBar: ButtomTabNavigation(),
      ),
    );
  }

  _pageChanged(int index) {
    _tabViewControl.jumpToPage(index);
    _current_page = _tab_pages[index];
  }
}

class ButtomTabNavigation extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => ButtomTabState();
}

class ButtomTabState extends State<ButtomTabNavigation> {
  int _currentIndex = 0;

  @override
  void initState() {
    super.initState();
    _currentIndex = INDEX_DEFAULT_PAGE;
  }

  @override
  Widget build(BuildContext context) {
    return BottomNavigationBar(
      // 底部导航
      items: _createButtomTab(context),
      currentIndex: _currentIndex,
      fixedColor: Colors.blue,
      onTap: (index) {
        setState(() {
          _currentIndex = index;
          context
              .findRootAncestorStateOfType<IndexPageState>()
              ?._pageChanged(index);
        });
      },
    );
  }
}
