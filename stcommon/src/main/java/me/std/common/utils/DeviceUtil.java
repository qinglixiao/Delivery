package me.std.common.utils;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.graphics.Rect;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.TouchDelegate;
import android.view.View;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:
 * Author: huangyuan
 * Create on: 2018/10/12
 * Job number: 1800829
 * Phone: 13120112517
 * Email: huangyuan@chunyu.me
 */

public class DeviceUtil {
    private static DeviceUtil sDeviceUtils;
    private final String DEVICE_PREF = "deviceid";
    private String mDeviceId = null;
    private String mSecureId = null;
    private String mMacAddress = null;
    private String mPhoneNum = null;
    private String mSimSerialNum = null;
    private String mInstallTime = null;
    private String mOperatorName = null;
    private int mScreenHeight = 0;
    private int mScreenWidth = 0;
    private float mScreenDensity = 1.0f;
    private boolean mLazyInit = false;

    // 占用application的context，不要占用其他activity的context，以免造成activity回收不能
    private Context mContext = null;

    //    private String mClientId = "";
    private String mIMSI = "";

    private DeviceUtil() {
        initialize();
    }

    public static DeviceUtil getInstance() {

        if (sDeviceUtils == null) {
            sDeviceUtils = new DeviceUtil();
        }
        return sDeviceUtils;
    }


    public String getDeviceId() {
        if (TextUtils.isEmpty(mDeviceId)) {
            return mMacAddress;
        } else {
            return mDeviceId;
        }
    }

    public String getSecureId() {
        return mSecureId;
    }

    public String getInstallTime() {
        return mInstallTime;
    }

    public String getMacAddress() {
        return mMacAddress;
    }


    public int getScreenHeight() {
        return this.mScreenHeight;
    }

    public int getScreenWidth() {
        return this.mScreenWidth;
    }


    public static long getTotalRAM() {
        long totalMemory = -1L;
        RandomAccessFile reader = null;
        String load;
        try {
            reader = new RandomAccessFile("/proc/meminfo", "r");
            load = reader.readLine();

            // Get the Number value from the string
            Pattern p = Pattern.compile("(\\d+)");
            Matcher m = p.matcher(load);
            String value = "";
            while (m.find()) {
                value = m.group(1);
            }

            totalMemory = Long.parseLong(value) / 1024;
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            FileUtil.closeSafely(reader);
        }
        return totalMemory;
    }

    public String getSimSerialNum() {
        return mSimSerialNum;
    }


    public String getSimOperatorName() {
        return mOperatorName;
    }

    public static boolean isMuted(Context context) {
        AudioManager audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (audio == null) {
            return false;
        }
        switch (audio.getRingerMode()) {
            case AudioManager.RINGER_MODE_SILENT:
                return true;
            case AudioManager.RINGER_MODE_VIBRATE:
                return true;
            default:
                return false;
        }
    }

    public static String getDiskFree() {
        if (Build.VERSION.SDK_INT < 18) {
            StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
            long free = (statFs.getAvailableBlocks() * statFs.getBlockSize());
            return Long.toString((free) / 1048576L);
        } else {
            StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
            long free = (statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong());
            return Long.toString((free) / 1048576L);
        }
    }

    public static boolean isVibrate(Context context) {
        AudioManager audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (audio == null) {
            return false;
        }

        return audio.getRingerMode() == AudioManager.RINGER_MODE_SILENT || audio.shouldVibrate(AudioManager.VIBRATE_TYPE_RINGER);


    }

    public String getScreenResolution() {
        return String.format(Locale.getDefault(), "%dx%d", this.mScreenHeight, this.mScreenWidth);
    }

    public static boolean isRoot() {
        File file = new File("/system/bin/su");
        return file.exists();
    }

    public static String getDiskTotal() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
            long total = (statFs.getBlockCount() * statFs.getBlockSize());
            return Long.toString(total / 1048576L);
        } else {
            StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
            long total = (statFs.getBlockCountLong() * statFs.getBlockSizeLong());
            return Long.toString(total / 1048576L);
        }
    }


    private boolean isDeviceIdValid(String deviceId) {
        return !TextUtils.isEmpty(deviceId) &&
                !"02:00:00:00:00:00".equals(deviceId)
                && !"9774d56d682e549c".equals(deviceId);
    }


    //先取AndroidID，再取macAddress，都非法，自己生成id
    private String generateDeviceID(Context context) {
        String deviceID = "";
        try {
            deviceID = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
            if(!isDeviceIdValid(deviceID)){
                WifiManager wifi = (WifiManager) (context.getSystemService(Context.WIFI_SERVICE));
                WifiInfo info = wifi.getConnectionInfo();
                deviceID = info.getMacAddress();
                if (!isDeviceIdValid(deviceID)) {
                    deviceID = UUID.randomUUID().toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            deviceID = UUID.randomUUID().toString();
        }
        if(TextUtils.isEmpty(deviceID)){
            deviceID = UUID.randomUUID().toString();
        }
        return deviceID;
    }

    /**
     * 获取mac地址、AndroidID、deviceID，在高版本手机上有可能获取到的是固定值(即使有权限)
     * 因此在获取这些id时，如果是非法值(固定值，如mac地址是"02:00:00:00:00:00",AndroidID是"9774d56d682e549c"等)
     * 则统一使用 generateDeviceID(Context context)来生成
     */
    private void initialize() {

        this.mContext = AppContextUtil.getAppContext();


        TelephonyManager telephonyManager = (TelephonyManager) mContext
                .getSystemService(Context.TELEPHONY_SERVICE);
        SharedPreferencesUtil pref = SharedPreferencesUtil.getPersonal(DEVICE_PREF);


        if (pref.contains("mDeviceId"))
            mDeviceId = pref.getString("mDeviceId", "");
        if (!isDeviceIdValid(mDeviceId)) {
            synchronized (DeviceUtil.class) {
                if (!isDeviceIdValid(mDeviceId)) {
                    mDeviceId = generateDeviceID(mContext);
                    pref.saveString("mDeviceId", mDeviceId);
                }
            }
        }


        if (pref.contains("mOperatorName")) {
            mOperatorName = pref.getString("mOperatorName", "");
        }
        if (TextUtils.isEmpty(mOperatorName)) {
            mOperatorName = telephonyManager.getNetworkOperatorName();
            if (TextUtils.isEmpty(mOperatorName)) {
                mOperatorName = "";
            } else {
                pref.saveString("mOperatorName", mOperatorName);
            }
        }


        if (pref.contains("mInstallTime")) {
            mInstallTime = pref.getString("mInstallTime", "");
        }
        if (TextUtils.isEmpty(mInstallTime)) {
            mInstallTime = String.valueOf(System.currentTimeMillis());
            pref.saveString("mInstallTime", mInstallTime);
        }


        if (pref.contains("mSecureId")) {
            mSecureId = pref.getString("mSecureId", "");
        }
        if (!isDeviceIdValid(mSecureId)) {
            mSecureId = generateDeviceID(mContext);
            pref.saveString("secureId", mSecureId);
        }


        //macAddress也可以使用判断deviceID的方式，在获取deviceid时，如果为空，则取macAddress
        if (pref.contains("macAddress")) {
            mMacAddress = pref.getString("macAddress", "");
        }
        if (!isDeviceIdValid(mMacAddress)) {
            mMacAddress = generateDeviceID(mContext);
            pref.saveString("macAddress", mMacAddress);
        }


        mScreenHeight = pref.getInt("mScreenHeight", 0);
        mScreenWidth = pref.getInt("mScreenWidth", 0);
        mScreenDensity = pref.getFloat("mScreenDensity", 0.0f);
        if (mScreenHeight <= 0 || mScreenWidth <= 0 || mScreenDensity <= 0) {
            WindowManager manager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            DisplayMetrics metric = new DisplayMetrics();
            mScreenHeight = display.getHeight();
            mScreenWidth = display.getWidth();
            mScreenDensity = metric.density;
            pref.save(
                    "mScreenHeight", mScreenHeight,
                    "mScreenWidth", mScreenWidth,
                    "mScreenDensity", mScreenDensity
            );


        }
    }

    public WifiManager getWifiManager(Context context) {
        return (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    }

    public String getSsid(WifiManager manager) {
        if (manager != null) {
            WifiInfo info = manager.getConnectionInfo();
            return info == null ? null : info.getSSID();
        }
        return "";
    }

    public static String getBatteryLevel(Context context) {
        Intent batteryIntent =
                context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        if (batteryIntent != null) {
            int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            if (level > -1 && scale > 0) {
                return Float.toString(((float) level / (float) scale) * 100.0f);
            }
        }
        return "-1";
    }


    public String getConnectionType() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null) {
            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                return "wifi";
            } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                switch (info.getSubtype()) {
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                        return "2G"; // ~ 50-100 kbps
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                        return "2G"; // ~ 14-64 kbps
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                        return "2G"; // ~ 50-100 kbps
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                        return "3G"; // ~ 400-1000 kbps
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                        return "3G"; // ~ 600-1400 kbps
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                        return "2G"; // ~ 100 kbps
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                        return "3G"; // ~ 2-14 Mbps
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                        return "3G"; // ~ 700-1700 kbps
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                        return "3G"; // ~ 1-23 Mbps
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                        return "3G"; // ~ 400-7000 kbps
                    // case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    // return "3G"; // ~ 5 Mbps
                    case TelephonyManager.NETWORK_TYPE_IDEN:
                        return "2G"; // ~25 kbps
                    // Unknown
                    case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                        return "2G";
                    default:
                        return "2G";
                }
            }
        }
        return "none";
    }


    public String getImsi() {
        return mIMSI;
    }

    /**
     * get installed applications
     *
     * @return app package names
     */
    public String getPackages() {
        StringBuilder builder = new StringBuilder();
        List<ApplicationInfo> infos = mContext.getPackageManager().getInstalledApplications(0);
        for (ApplicationInfo info : infos) {
            if (builder.length() != 0) {
                builder.append(";");
            }
            builder.append(info.processName);
        }
        return builder.toString();
    }

    /**
     * 手机是否有sim卡
     */
    public boolean isSimCardExist() {
        TelephonyManager telephonyManager =
                (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getSimState() != 1;
    }

    public static int dip2px(Context context, float dpValue) {
        final float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }

    /**
     * 扩大View的触摸和点击响应范围,最大不超过其父View范围
     */
    public static void expandViewTouchDelegate(final View view, final int top,
                                               final int bottom, final int left, final int right) {

        ((View) view.getParent()).post(new Runnable() {
            @Override
            public void run() {
                Rect bounds = new Rect();
                view.setEnabled(true);
                view.getHitRect(bounds);

                bounds.top -= top;
                bounds.bottom += bottom;
                bounds.left -= left;
                bounds.right += right;

                TouchDelegate touchDelegate = new TouchDelegate(bounds, view);

                if (View.class.isInstance(view.getParent())) {
                    ((View) view.getParent()).setTouchDelegate(touchDelegate);
                }
            }
        });
    }


    public String appendWifiParam(String url) {
        boolean isWifi = ((WifiManager) mContext.getSystemService(Context.WIFI_SERVICE)).isWifiEnabled();
        if (!TextUtils.isEmpty(url) && !url.endsWith("&")) {
            url += "&";
        }
        url += "wifi=" + isWifi;
        return url;
    }

    /**
     * 根据指定的宽高比例，计算宽度是match_parent时，对应的高度，单位是px
     *
     * @param height 指定的高
     * @param width  指定的宽
     */
    public static int getHeightWithWidth(Context context, int width, int height) {
        if (width <= 0 || height <= 0) {
            return 0;
        }
        int screenWidth = DeviceUtil.getInstance().getScreenWidth();
        return screenWidth * height / width;
    }
}
