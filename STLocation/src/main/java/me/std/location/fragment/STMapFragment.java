package me.std.location.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.maps2d.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.std.base.base.STFragment;
import me.std.base.core.ActionBar;
import me.std.common.utils.AppContextUtil;
import me.std.common.utils.Logger;
import me.std.db.DbManager;
import me.std.db.tables.SportLocation;
import me.std.location.MapLocationUtil;
import me.std.location.R;

/**
 * Description:
 * Author: lixiao
 * Create on: 2020-02-26.
 */
public class STMapFragment extends STFragment implements LocationSource,
        AMapLocationListener, View.OnClickListener {
    MapView mapView;
    AMap aMap;
    private OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    List<Pair> locations = new ArrayList();
    Button btnDb;

    @Override
    protected void onActionBar(ActionBar.Builder builder) {
        builder.setTitle("行踪");
    }

    @Override
    protected View onCreateLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, null);
        mapView = view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        btnDb = view.findViewById(R.id.btn_db);
        init();
        return view;
    }

    /**
     * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
            setUpMap();
        }
        btnDb.setOnClickListener(this);
    }

    private void record() {
        int interval = 1000 * 60 * 5;
        new MapLocationUtil(interval).startLocation(new MapLocationUtil.OnMapLocationListener() {
            @Override
            public void onLocationChanged(MapLocationUtil.STMapLocationInfo locationInfo, Error error) {
                Pair laln = new Pair(locationInfo.latitude, locationInfo.longitude);

                if (!locations.contains(laln)) {
                    locations.add(laln);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
        deactivate();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
        // 自定义系统定位小蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory
                .fromResource(R.drawable.location_marker));// 设置小蓝点的图标
        myLocationStyle.strokeColor(Color.BLACK);// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));// 设置圆形的填充颜色
        // myLocationStyle.anchor(int,int)//设置小蓝点的锚点
        myLocationStyle.strokeWidth(1.0f);// 设置圆形的边框粗细
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        // aMap.setMyLocationType()
    }

    private void addLine(AMapLocation location) {
        aMap.addPolyline(new PolylineOptions().add(
                new LatLng(location.getLatitude(), location.getLongitude()),
                new LatLng(0, 0)).color(Color.RED));
    }

    long locTime;

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null
                    && aMapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
                addLine(aMapLocation);
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
        Logger.d(System.currentTimeMillis() - locTime + "");
        locTime = System.currentTimeMillis();
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(AppContextUtil.getAppContext());
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public void onClick(View v) {
        DbManager manager = DbManager.getInstance(SportLocation.class);
        SportLocation location = new SportLocation();
        location.setDateTime("2020");
        location.setId(new Random().nextLong());
        manager.insert(location);
        Logger.d(manager.count() + "");
    }
}
