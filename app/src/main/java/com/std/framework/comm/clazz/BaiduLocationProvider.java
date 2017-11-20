package com.std.framework.comm.clazz;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;

public class BaiduLocationProvider {
	private static final int INTERVAL = 300;

	private Context context;
	private LocationClient mLocationClient;
	private LocationClientOption option;
	private LocationListener locationListener;

	public BaiduLocationProvider(Context context) {
		this.context = context.getApplicationContext();
		init();
	}

	private void init() {
		mLocationClient = new LocationClient(context);
		option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);//设置高精度模式
		option.setScanSpan(INTERVAL);
		option.setIsNeedAddress(true);
		mLocationClient.registerLocationListener(baiduLocationListener);
		mLocationClient.setLocOption(option);
	}

	public void setListener(LocationListener locationListener) {
		this.locationListener = locationListener;
	}
	
	public void start(){
		mLocationClient.start();
	}
	
	public void stop(){
		mLocationClient.stop();
	}

	private BDLocationListener baiduLocationListener = new BDLocationListener() {

		@Override
		public void onReceiveLocation(BDLocation arg0) {
			// TODO Auto-generated method stub
			if (locationListener != null) {
				locationListener.onReceiveLocation(arg0.getAddrStr());
			}
			stop();
		}
	};

	public interface LocationListener {
		void onReceiveLocation(String address);
	}

}
