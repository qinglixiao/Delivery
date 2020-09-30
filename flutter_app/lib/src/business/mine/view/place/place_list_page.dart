import 'package:flutter/material.dart';
import 'package:flutter_ienglish_fine/src/business/mine/viewmodel/place_detail_view_model.dart';
import 'package:flutter_lib/flutter_lib.dart';
import 'package:flutter_ienglish_fine/l10n/values.dart';
import 'package:flutter_ienglish_fine/generated/l10n.dart';
import 'package:flutter_ienglish_fine/src/config/name_router.dart';
import 'package:flutter_ienglish_fine/src/business/mine/bean/place_list.dart';
import 'package:flutter_ienglish_fine/src/business/budget/bean/good_confirm.dart';
import 'package:flutter_ienglish_fine/src/business/mine/viewmodel/place_list_view_model.dart';
import 'package:flutter_slidable/flutter_slidable.dart';

class PlaceListPage extends StatefulWidget {
  @override
  _PlaceListPageState createState() => _PlaceListPageState();
}

class _PlaceListPageState extends State<PlaceListPage> with PageBridge {
  PlaceListViewModel _viewModel = PlaceListViewModel();
  PlaceDetailViewModel _viewDetailModel = PlaceDetailViewModel();
  SlidableController slidableController;

  bool _isChoose = false;

  bool _slideOpen = false;

  @override
  void initState() {
    slidableController = SlidableController(
      onSlideAnimationChanged: handleSlideAnimationChanged,
      onSlideIsOpenChanged: handleSlideIsOpenChanged,
    );
    super.initState();
    _loadData(true);
  }

  _loadData(bool isFirst) {
    _viewModel.loadPlaceList(isFirst: isFirst);
  }

  Animation<double> _rotationAnimation;
  Color _fabColor = Colors.blue;

  void handleSlideAnimationChanged(Animation<double> slideAnimation) {
    setState(() {
      _rotationAnimation = slideAnimation;
    });
  }

  void handleSlideIsOpenChanged(bool isOpen) {
    setState(() {
      _fabColor = isOpen ? Colors.green : Colors.blue;
      _slideOpen = isOpen;
    });
  }

  @override
  Widget build(BuildContext context) {
    getInitArg(context).then((params) {
      if(params!=null){
        _isChoose = params['choose'];
      }
    });
    return RootPageWidget(
        appBar: IsAppBar(
          title: S.of(context).place_title,
        ),
        viewModel: _viewModel,
        body: StreamBuilder<PlaceList>(
            stream: _viewModel.streamPlaceList,
            builder: (BuildContext context, AsyncSnapshot<PlaceList> snapshot) {
              if (snapshot.data == null || snapshot.data?.items == null) {
                return CommonWidget.emptyWidget();
              }
              return Stack(
                children: <Widget>[
                  Container(
                    margin: EdgeInsets.only(bottom: Values.d_50),
                    child: PullToRefresh(
                      child: SListView(_buildItemView, itemAction: _itemAction)
                          .build(context, snapshot.data.items),
                      onRefresh: () {
                        return _viewModel.loadPlaceList(isFirst: false);
                      },
                    ),
                  ),
                  Positioned(
                      bottom: 0, left: 0, right: 0, child: BottomWidget())
                ],
              );
            }));
  }

  Widget _buildItemView(BuildContext context, Object itemData) {
    Place _place = itemData as Place;
    return _wrapWithSlider(
        _place,
        Container(
          padding: EdgeInsets.only(
              left: Values.d_20, top: Values.d_15, bottom: Values.d_15),
          decoration: new BoxDecoration(
              color: Values.of(context).c_white_background,
              border: new Border(
                  bottom: BorderSide(
                      color: Values.of(context).c_grey_ea, width: 1.0))),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: <Widget>[
              Expanded(
                  child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                mainAxisAlignment: MainAxisAlignment.start,
                children: <Widget>[
                  Row(
                    children: <Widget>[
                      Container(
                        child: Text(_place.username,
                            style: TextStyle(
                                color: Values.of(context).c_black_front_33,
                                fontWeight: Values.font_Weight_medium,
                                fontSize: Values.s_text_18)),
                      ),
                      Container(
                        margin: EdgeInsets.only(left: Values.d_15),
                        child: Text(_place.tel,
                            style: TextStyle(
                                color: Values.of(context).c_black_front_33,
                                fontWeight: Values.font_Weight_medium,
                                fontSize: Values.s_text_18),
                            maxLines: 1,
                            overflow: TextOverflow.ellipsis),
                      ),
                      _place.priority
                          ? Container(
                              margin: EdgeInsets.only(
                                  left: Values.d_15, right: Values.d_50),
                              padding: EdgeInsets.only(
                                  left: Values.d_5, right: Values.d_5),
                              decoration: BoxDecoration(
                                color:
                                    Values.of(context).c_orange_background_34,
                                border: Border.all(
                                    color: Values.of(context)
                                        .c_orange_background_0b,
                                    width: 1),
                                borderRadius: new BorderRadius.circular(
                                    (Values.d_36 / 2)), // 圆角度
                              ),
                              child: Text(S.of(context).default_title,
                                  style: TextStyle(
                                      color:
                                          Values.of(context).c_orange_front_0b,
                                      fontWeight: Values.font_Weight_normal,
                                      fontSize: Values.s_text_12),
                                  maxLines: 1,
                                  overflow: TextOverflow.ellipsis),
                            )
                          : Container()
                    ],
                  ),
                  Container(
                    margin:
                        EdgeInsets.only(top: Values.d_5, right: Values.d_15),
                    child: Text(
                      _place.province +
                          _place.city +
                          _place.area +
                          _place.detailaddress,
                      style: TextStyle(
                          color: Values.of(context).c_black_front_33,
                          fontWeight: Values.font_Weight_normal,
                          fontSize: Values.s_text_13),
                      maxLines: 2,
                      overflow: TextOverflow.ellipsis,
                    ),
                  ),
                ],
              )),
              GestureDetector(
                onTap: () {
                  open(RouterName.place_detail,
                      argument: {'id': (itemData != null ? (itemData as Place).id : '')}).then((value){
                        if(value){
                          _loadData(false);
                        }
                  });
                },
                child: Container(
                  margin: EdgeInsets.only(right: Values.d_20),
                  child: Image.asset('assets/images/right_icon.png'),
                ),
              ),
            ],
          ),
        ));
  }

  Widget _wrapWithSlider(Place place, Widget widget) {
    return Slidable.builder(
      controller: slidableController,
      direction: Axis.horizontal,
      actionPane: SlidableDrawerActionPane(),
      child: SlideItemWidget(widget,place),
      secondaryActionDelegate: place.priority
          ? SlideActionBuilderDelegate(
              actionCount: 1,
              builder: (context, index, animation, renderingMode) {
                return SlideAction(
                    child: Text(
                      S.of(context).common_delete,
                      style: TextStyle(color: Values.of(context).c_white_front),
                    ),
                    color: renderingMode == SlidableRenderingMode.slide
                        ? Values.of(context).c_orange_front_0b
                        : Values.of(context).c_orange_front_0b,
                    onTap: () => {_deletePlace(place)});
              })
          : SlideActionBuilderDelegate(
              actionCount: 2,
              builder: (context, index, animation, renderingMode) {
                if (index == 0) {
                  return SlideAction(
                    child: Text(S.of(context).place_set_default),
                    color: renderingMode == SlidableRenderingMode.slide
                        ? Colors.grey.shade200.withOpacity(animation.value)
                        : Colors.grey.shade200,
                    onTap: () => {
                      //设为默认
                      _viewModel.setDefaultPlace(place).then((success) {
                        _loadData(false);
                      })
                    },
                  );
                } else {
                  return SlideAction(
                      child: Text(
                        S.of(context).common_delete,
                        style: TextStyle(color: Values.of(context).c_white_front),
                      ),
                      color: renderingMode == SlidableRenderingMode.slide
                          ? Values.of(context).c_orange_front_0b
                          : Values.of(context).c_orange_front_0b,
                      onTap: () => {_deletePlace(place)});
                }
              }),
    );
  }

  Widget BottomWidget() {
    return Container(
        color: Values.of(context).c_white_background,
        child: SafeArea(
            child: Container(
                color: Values.of(context).c_orange_background_0b,
                height: Values.d_50,
                width: MediaQuery.of(context).size.width,
                child: FlatButton(
                    onPressed: () {
                      openDetail(null);
                    },
                    color: Values.of(context).c_orange_background_0b,
                    disabledColor: Values.of(context).c_grey_background_cc,
                    highlightColor: Values.c_translucent,
                    splashColor: Values.c_translucent,
                    child: Text(
                      S.of(context).place_new_create,
                      style: TextStyle(
                          fontSize: Values.s_text_16,
                          color: Values.of(context).c_white_front),
                    )))));
  }

  _deletePlace(Place place) {
    _viewModel.deletePlace(place.id).then((success) {
      _viewModel.loadPlaceList(isFirst: false);
    });
  }

  void _itemAction(Widget widget, Object itemData) {}

  void openDetail(Place itemData) {
    if (itemData != null) {
      if (_isChoose) {
        pop(data: {'id': (itemData != null ? itemData.id : '')});
      } else {
        open(RouterName.place_detail,
            argument: {'id': (itemData != null ? itemData.id : '')}).then((value){
              if(value){
                _loadData(false);
              }
        });
      }
    } else {
      open(RouterName.place_new).then((value){
        if(value){
          _loadData(false);
        }
      });
    }
  }
}

class SlideItemWidget extends StatelessWidget {
  Widget child;
  Place place;

  SlideItemWidget(this.child,this.place);

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        if(!context.findRootAncestorStateOfType<_PlaceListPageState>()._slideOpen){
          context.findRootAncestorStateOfType<_PlaceListPageState>().openDetail(place);
        }
        Slidable.of(context)?.close();
      },
      child: child,
    );
  }
}
