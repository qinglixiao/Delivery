package me.std.location;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.AMapLocationQualityReport;

import me.std.common.utils.AppContextUtil;

/**
 * Description：定位/后台定位
 * 从Android 8.0开始，Android系统为了实现低功耗，Android 8.0系统对后台应用获取位置的频率进行了限制，只允许每小时几次位置更新。
 * 根据Android 8.0的开发指引，为了适配这一系统特性，
 * 高德定位SDK从8.0开始增加了两个新接口enableBackgroundLocation和disableBackgroundLocation用来控制是否开启后台定位。
 * 开启后sdk会生成一个前台服务通知，告知用户应用正在后台运行，使得开发者自己的应用退到后台的时候，仍有前台通知在，提高应用切入后台后位置更新的频率。
 * 示例中提供了两种方法启动和关闭后台定位功能,请根据业务场景进行相应的修改<br>
 * 1、通过按钮触发，点击按钮调用相应的接口开开启或者关闭后台定位，此种方法主要是更直观的展示后台定位的功能
 * 2、通过生命周期判断APP是否处于后台，当处于后台时才开启后台定位功能，恢复到前台后关闭后台定位功能
 * Author: lixiao
 * Create on: 2020-02-23.
 */
public class MapLocationUtil {
    private static int NOTIFICATOINID = 23;
    private static MapLocationUtil instance;

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private OnMapLocationListener onMapLocationListener = null;

    private MapLocationUtil() {
        initLocation(null);
    }

    public static MapLocationUtil getInstance() {
        if (instance == null) {
            instance = new MapLocationUtil();
        }
        return instance;
    }

    public MapLocationUtil(int LocationInterval) {
        initLocation(getOption(LocationInterval));
    }

    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if (location.getErrorCode() == 0) {
                    if (onMapLocationListener != null) {
                        STMapLocationInfo locationInfo = new STMapLocationInfo();
                        locationInfo.longitude = location.getLongitude();
                        locationInfo.latitude = location.getLatitude();
                        locationInfo.address = location.getAddress();
                        locationInfo.poiName = location.getPoiName();
                        callBack(locationInfo, null);
                    }

                } else {
                    //定位失败
                    callBack(null, new Error(location.getLocationDetail()));
                }
            } else {
                callBack(null, new Error("locatoin is null"));
            }
        }
    };

    private void callBack(STMapLocationInfo locationInfo, Error error) {
        if (onMapLocationListener != null) {
            onMapLocationListener.onLocationChanged(locationInfo, error);
        }
    }

    /**
     * 开始定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    public void startLocation(OnMapLocationListener onMapLocationListener) {
        this.onMapLocationListener = onMapLocationListener;
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 获取GPS状态的字符串
     *
     * @param statusCode GPS状态码
     * @return
     */
    private String getGPSStatusString(int statusCode) {
        String str = "";
        switch (statusCode) {
            case AMapLocationQualityReport.GPS_STATUS_OK:
                str = "GPS状态正常";
                break;
            case AMapLocationQualityReport.GPS_STATUS_NOGPSPROVIDER:
                str = "手机中没有GPS Provider，无法进行GPS定位";
                break;
            case AMapLocationQualityReport.GPS_STATUS_OFF:
                str = "GPS关闭，建议开启GPS，提高定位质量";
                break;
            case AMapLocationQualityReport.GPS_STATUS_MODE_SAVING:
                str = "选择的定位模式中不包含GPS定位，建议选择包含GPS定位的模式，提高定位质量";
                break;
            case AMapLocationQualityReport.GPS_STATUS_NOGPSPERMISSION:
                str = "没有GPS定位权限，建议开启gps定位权限";
                break;
        }
        return str;
    }


    /**
     * 初始化定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void initLocation(AMapLocationClientOption option) {
        //初始化client
        locationClient = new AMapLocationClient(AppContextUtil.getAppContext());
        if (option == null) {
            locationOption = getDefaultOption();
        } else {
            locationOption = option;
        }
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }

    public void enableBackgroundLocation(boolean enable) {
        if (enable) {
            locationClient.enableBackgroundLocation(2001, buildNotification());
        } else {
            locationClient.disableBackgroundLocation(true);
            if (notificationManager != null) {
                notificationManager.cancel(NOTIFICATOINID);
            }
        }
    }

    boolean isCreateChannel = false;
    NotificationManager notificationManager = null;

    private Notification buildNotification() {
        Notification.Builder builder = null;
        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= 26) {
            //Android O上对Notification进行了修改，如果设置的targetSDKVersion>=26建议使用此种方式创建通知栏
            if (null == notificationManager) {
                notificationManager = (NotificationManager) AppContextUtil.getAppContext().getSystemService(Context.NOTIFICATION_SERVICE);
            }
            String channelId = AppContextUtil.getPackageName();
            if (!isCreateChannel) {
                NotificationChannel notificationChannel = new NotificationChannel(channelId,
                        "BackgroundLocation", NotificationManager.IMPORTANCE_DEFAULT);
                notificationChannel.enableLights(true);//是否在桌面icon右上角展示小圆点
                notificationChannel.setLightColor(Color.BLUE); //小圆点颜色
                notificationChannel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
                notificationManager.createNotificationChannel(notificationChannel);
                isCreateChannel = true;
            }
            builder = new Notification.Builder(AppContextUtil.getAppContext(), channelId);
        } else {
            builder = new Notification.Builder(AppContextUtil.getAppContext());
        }
        builder.setSmallIcon(R.drawable.share_icon)
                .setContentTitle(AppContextUtil.getAppName())
                .setContentText("正在后台运行")
                .setWhen(System.currentTimeMillis());

        if (android.os.Build.VERSION.SDK_INT >= 16) {
            notification = builder.build();
        } else {
            return builder.getNotification();
        }
        notificationManager.notify(NOTIFICATOINID, notification);
        return notification;
    }

    /**
     * 停止定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    public void stopLocation() {
        // 停止定位
        locationClient.stopLocation();
    }

    /**
     * 销毁定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    public void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getOption(int interval) {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(interval);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(true);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        mOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.DEFAULT);//可选，设置逆地理信息的语言，默认值为默认语言（根据所在地区选择语言）
        return mOption;
    }

    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(true);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        mOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.DEFAULT);//可选，设置逆地理信息的语言，默认值为默认语言（根据所在地区选择语言）
        return mOption;
    }

    public interface OnMapLocationListener {
        void onLocationChanged(STMapLocationInfo locationInfo, Error error);
    }

    public class STMapLocationInfo {
        public double longitude;//经度
        public double latitude;//纬度
        public String address;//地址
        public String poiName;//兴趣点
    }
}
