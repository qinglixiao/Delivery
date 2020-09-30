import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/src/comm/constant.dart';
import 'package:flutter_ienglish_fine/src/comm/event/message_center.dart';
import 'package:flutter_ienglish_fine/src/comm/event/index_page_event.dart';
import 'package:flutter_lib/flutter_lib.dart';

import 'home/view/home_page.dart';
import 'me/view/me_page.dart';
import 'partner/view/partner_page.dart';

/// 主页
///
///create by lx
///date 2020/6/8

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
        icon: Image.asset('assets/images/tab_icon1.png'),
        activeIcon: Image.asset('assets/images/tab_icon1_active.png'),
        title: Text(S.of(context).tab_home, style: TextStyle(fontWeight: Values.font_Weight_normal))),
    BottomNavigationBarItem(
        icon: Image.asset('assets/images/tab_icon2.png'),
        activeIcon: Image.asset('assets/images/tab_icon2_active.png'),
        title: Text(S.of(context).tab_plateform, style: TextStyle(fontWeight: Values.font_Weight_normal))),
    BottomNavigationBarItem(
        icon: Image.asset('assets/images/tab_icon3.png'),
        activeIcon: Image.asset('assets/images/tab_icon3_active.png'),
        title: Text(S.of(context).tab_me, style: TextStyle(fontWeight: Values.font_Weight_normal))),
  ];
}

class IndexPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => IndexPageState();

  static IndexPageState of(BuildContext context) {
    return context.findAncestorStateOfType<IndexPageState>();
  }
}

class IndexPageState extends State<IndexPage> with SingleTickerProviderStateMixin {
  PageController _tabViewControl;
  List<Widget> _tab_pages;

  Widget _current_page;

  Widget get currentPage => _current_page;

  int _currentIndex;

  int get currentIndex => _currentIndex;

  List<OnTabChangedMixin> _onTabChangedListeners = [];

  DateTime _lastPressedAdt;

  @override
  void initState() {
    super.initState();
    _tab_pages = _createPages();
    _setting();
    _addListener();
    _register();
  }

  _register() {
    eventCenter.listen<IndexPageEvent>((event) {
      Logger.print("receive msg ${event.data}");
      switch (event.type) {
        case 1: //切换tab
          Logger.print("receive coming");
          switchToPage(event.data ?? INDEX_DEFAULT_PAGE);
          break;
      }
    });
  }

  _setting() {
    _current_page = _tab_pages[INDEX_DEFAULT_PAGE];
    _currentIndex = INDEX_DEFAULT_PAGE;
    _tabViewControl = PageController(initialPage: INDEX_DEFAULT_PAGE);
    _addListener();
  }

  _addListener() {
    _tab_pages.forEach((page) {
      if (page is OnTabChangedMixin) {
        _onTabChangedListeners.add(page as OnTabChangedMixin);
      }
    });
  }

  ButtomTabNavigation _buttomTabNavigation;

  @override
  Widget build(BuildContext context) {
    _buttomTabNavigation = ButtomTabNavigation();
    return RootPageWidget(
      body: Scaffold(
        body: PageView(
          controller: _tabViewControl,
          physics: NeverScrollableScrollPhysics(),
          children: _tab_pages,
          onPageChanged: (index) {
            _onTabChangedListeners.forEach((page) {
              page.onTabChanged(index);
            });
          },
        ),
        bottomNavigationBar: _buttomTabNavigation,
      ),
      onWillBack: _onBack,
    );
  }

 Future<bool> _onBack() async {
    if (_lastPressedAdt == null || DateTime.now().difference(_lastPressedAdt) > Duration(seconds: 2)) {
      _lastPressedAdt = DateTime.now();
      Toast.show("${S.of(context).app_pop_title}${S.of(context).appName}");
      return false;
    }
    return true;
  }

  _pageChanged(int index) {
    if (_currentIndex == index) {
      return;
    }
    _tabViewControl.jumpToPage(index);
    _current_page = _tab_pages[index];
    _currentIndex = index;
  }

  switchToPage(int index) {
    _pageChanged(index);
    _buttomTabNavigation.buttomTabState.focus(index);
  }
}

class ButtomTabNavigation extends StatefulWidget {
  ButtomTabState buttomTabState;

  @override
  State<StatefulWidget> createState() {
    buttomTabState = ButtomTabState();
    return buttomTabState;
  }
}

class ButtomTabState extends State<ButtomTabNavigation> {
  int _currentIndex = 0;

  @override
  void initState() {
    super.initState();
    _currentIndex = INDEX_DEFAULT_PAGE;
  }

  focus(int index) {
    setState(() {
      _currentIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return CupertinoTabBar(
      backgroundColor: Values.of(context).c_white_background,
      items: _createButtomTab(context),
      currentIndex: _currentIndex,
      inactiveColor: Values.of(context).c_black_front_99,
      activeColor: Values.of(context).c_black_front_33,
      onTap: (index) {
        setState(() {
          _currentIndex = index;
          context.findRootAncestorStateOfType<IndexPageState>()?._pageChanged(index);
        });
      },
    );
  }
}

mixin OnTabChangedMixin {
  onTabChanged(int index);
}
