package me.std.common.utils;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.util.Pair;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * Created by linyun on 15-3-12.
 */
public class LocationUtils {

    private static final String LOG_TAG = "DEBUG-WCL: " + LocationUtils.class.getSimpleName();

    private static final String PREF_KEY_LOCATION_LATITUDE =
            LocationUtils.class.getSimpleName() + "_latitude";
    private static final String PREF_KEY_LOCATION_LONGITUDE =
            LocationUtils.class.getSimpleName() + "_longitude";
    private static final String PREF_KEY_LOCATION_ALTITUDE =
            LocationUtils.class.getSimpleName() + "_altitude";
    private static final String PREF_KEY_LOCATION_REQUEST = "_request_location";

    public static String appendToUrl(Context context, String url) {
        Context appContext = context.getApplicationContext();
        Double latitude = 0.0;
        Double longitude = 0.0;

        try {
            latitude = Double.valueOf(SharedPreferencesUtil.getDefault().getString(PREF_KEY_LOCATION_LATITUDE));
            longitude = Double.valueOf(SharedPreferencesUtil.getDefault().getString(PREF_KEY_LOCATION_LONGITUDE));
        } catch (Exception e) {

        }

        if (latitude == 0 || longitude == 0) {
            return url;
        }
        if (url.contains("?")) {
            url += "&";
        } else {
            url += "?";
        }
        url += "lat=" + latitude + "&" + "long=" + longitude;
        return url;
    }

    public static boolean isRequestLocation(Context context) {
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        return preference.getBoolean(PREF_KEY_LOCATION_REQUEST, false);
    }

    public static void setRequestLocation(Context context, boolean isRequest) {
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        preference.edit().putBoolean(PREF_KEY_LOCATION_REQUEST, isRequest).commit();
    }

    public static void saveLocation(Context context, Location location) {
        Context appContext = context.getApplicationContext();
        saveLocation(appContext, location.getLatitude(), location.getLongitude(),
                location.getAltitude());
    }

    public static void saveLocation(Context context, double latitude,
                                    double longitude, double altitude) {
        Context appContext = context.getApplicationContext();
        SharedPreferencesUtil.getDefault().save(
                PREF_KEY_LOCATION_LATITUDE, latitude + "",
                PREF_KEY_LOCATION_LONGITUDE, longitude + "",
                PREF_KEY_LOCATION_ALTITUDE, altitude + "");
    }

    public static Pair<Double, Double> getLocation(Context context) {
        Double latitude = 0.0;
        Double longitude = 0.0;
        try {
            latitude = Double.valueOf(SharedPreferencesUtil.getDefault().getString(PREF_KEY_LOCATION_LATITUDE));
            longitude = Double.valueOf(SharedPreferencesUtil.getDefault().getString(PREF_KEY_LOCATION_LONGITUDE));
        } catch (Exception e) {

        }

        Pair<Double, Double> location = new Pair<Double, Double>(latitude, longitude);
        return location;
    }

    public static double getAltitude(Context context) {
        Double altitude = 0.0;
        try {
            altitude = Double.valueOf(SharedPreferencesUtil.getDefault().getString(PREF_KEY_LOCATION_ALTITUDE));
        } catch (Exception e) {
        }
        return altitude;
    }

    public static boolean isPermissionValid(Context context) {
        int location = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);
        int phone_state = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);
        int write_storage = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        return location == PackageManager.PERMISSION_GRANTED
                && phone_state == PackageManager.PERMISSION_GRANTED
                && write_storage == PackageManager.PERMISSION_GRANTED;
    }

    public static void syncLocation(final Context context,
                                    final OnLocationChangedListener listener) {
        final Context appContext = context.getApplicationContext();
        if (!isPermissionValid(appContext)) {
            return;
        }

        final AMapLocationClient locationClient = new AMapLocationClient(appContext); // 高德定位客户端
        final AMapLocationClientOption locationOption = new AMapLocationClientOption(); // 定位选项

        locationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
//                Log.e(LOG_TAG, "location changed. aMapLocation: " + aMapLocation);
                if (aMapLocation == null) {
                    return;
                }

                double lat = aMapLocation.getLatitude();
                double lon = aMapLocation.getLongitude();
                double alt = aMapLocation.getAltitude();
//                Log.e(LOG_TAG, "location changed. AMAP lat: "
//                        + lat + ", lon: " + lon + ", alt: " + alt);

                if (((int) lat) != 0 && ((int) lon) != 0) {
                    saveLocation(appContext, lat, lon, alt);
                    locationOption.setInterval(30 * 1000); // 定位时间
                    locationClient.onDestroy();
                }
            }
        });

        locationOption
                .setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy); // 高精度模式
        locationOption.setOnceLocation(false); // 多次定位
        locationOption.setGpsFirst(false); // 高精度
        locationOption.setInterval(1000); // 定位时间
        locationOption.setNeedAddress(true); // 逆地址定位编码
        locationClient.setLocationOption(locationOption); // 设置定位参数
        locationClient.startLocation(); // 启动定位

//        final LocationManagerProxy locationManagerProxy = LocationManagerProxy.getInstance(appContext);
//        locationManagerProxy.setGpsEnable(true);
//        locationManagerProxy.requestLocationUpdates(LocationProviderProxy.AMapNetwork,
//                30 * 1000, 15, new AMapLocationListener() {
//                    @Override
//                    public void onLocationChanged(AMapLocation aMapLocation) {
//                        Log.e(LOG_TAG, "location changed. aMapLocation: " + aMapLocation);
//                        if (aMapLocation == null) {
//                            return;
//                        }
//                        double lat = aMapLocation.getLatitude();
//                        double lon = aMapLocation.getLongitude();
//                        double alt = aMapLocation.getAltitude();
//                        Log.e(LOG_TAG, "location changed. AMAP lat: "
//                                + lat + ", lon: " + lon + ", alt: " + alt);
//
//                        saveLocation(appContext, lat, lon, alt);
//
//                        if (locationManagerProxy != null) {
//                            locationManagerProxy.removeUpdates(this);
//                            locationManagerProxy.destroy();
//                        }
////                        Toast.makeText(context,
////                                String.format("longitude: %.4f, latitude: %.4f, address: %s",
////                                        aMapLocation.getLongitude(),
////                                        aMapLocation.getLatitude(),
////                                        aMapLocation.getExtras().getString("desc")),
////                                Toast.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    public void onLocationChanged(Location location) {
//                        Log.e(LOG_TAG, "location changed. location: " + location);
//                    }
//
//                    @Override
//                    public void onStatusChanged(String provider, int status, Bundle extras) {
//
//                    }
//
//                    @Override
//                    public void onProviderEnabled(String provider) {
//
//                    }
//
//                    @Override
//                    public void onProviderDisabled(String provider) {
//
//                    }
//                });
    }

    static class CYLocationListener implements LocationListener {

        private OnLocationChangedListener mListener;
        private LocationManager mLocationManager;
        private Context mContext;
        private Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                mLocationManager.removeUpdates(CYLocationListener.this);
            }
        };

        CYLocationListener(Context context, OnLocationChangedListener listener) {
            this.mListener = listener;
            this.mContext = context.getApplicationContext();
            this.mLocationManager =
                    (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
            mHandler.sendEmptyMessageDelayed(0,
                    20 * android.text.format.DateUtils.SECOND_IN_MILLIS);
        }

        // Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        // Provider被enable时触发此函数，比如GPS被打开
        @Override
        public void onProviderEnabled(String provider) {

        }

        // Provider被disable时触发此函数，比如GPS被关闭
        @Override
        public void onProviderDisabled(String provider) {

        }

        //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                if (mListener != null) {
                    mListener.onLocationChanged(location);
                }
                mLocationManager.removeUpdates(this);
                mHandler.removeMessages(0);
                LocationUtils.saveLocation(mContext, location);
            }
        }
    }

    public interface OnLocationChangedListener {
        void onLocationChanged(Location location);
    }

}
