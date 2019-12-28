package me.std.common.utils;

import android.text.TextUtils;

import java.net.URLEncoder;

/**
  * Description:
  * Author: huangyuan
  * Create on: 2018/10/16
  * Job number: 1800829
  * Phone: 13120112517
  * Email: huangyuan@chunyu.me
  */
public class URLUtil {

    private static final String HOST_A = "//www.chunyuyisheng.com";
    private static final String HOST_B = "//api.chunyuyisheng.com";
    private static final String HOST_C = "//chunyuyisheng.com";
    private static final String HOST = "//weixin.chunyuyisheng.com";

    public static String urlReplace(String url) {

        if (TextUtils.isEmpty(url)) {
            return url;
        } else if (url.contains(HOST)) {
            return url;
        } else if (url.contains(HOST_A)) {
            return url.replace(HOST_A, HOST);
        } else if (url.contains(HOST_B)) {
            return url.replace(HOST_B, HOST);
        } else if (url.contains(HOST_C)) {
            return url.replace(HOST_C, HOST);
        } else {
            return url;
        }

    }

    public static String getUrlField(String url, String fieldName) {
        return getUrlField(url, fieldName, "=", "&");
    }

    /**
     *
     * 获取url中的某个字段，字段定义：fieldName=fieldValue
     *
     * @param url
     *            : String
     * @param fieldName
     *            : 字段名
     * @param keyvalueDivider
     *            : key与value直接的连接符
     * @param divideStr
     *            ： 分隔字符串
     *
     * @return fieldValue 字段值
     */
    private static String getUrlField(String url, String fieldName,
                                      String keyvalueDivider, String divideStr) {
        if (TextUtils.isEmpty(url) || TextUtils.isEmpty(fieldName)
                || TextUtils.isEmpty(keyvalueDivider)
                || TextUtils.isEmpty(divideStr)) {
            return "";
        }

        fieldName = fieldName + keyvalueDivider;

        // 不转换为小写，避免pu参数是urlencode的情况，将%2C之类的转成%2c，导致替换失败
        // String tmp = url.toLowerCase();
        String tmp = url;
        int index = tmp.indexOf("?");
        if (index == -1) {
            index = 0;
        }

        int p = tmp.indexOf(fieldName, index);
        int q;
        if (p != -1) {
            q = tmp.indexOf(divideStr, p);
            if (q != -1) {
                tmp = tmp.substring(p + fieldName.length(), q);
            } else {
                tmp = tmp.substring(p + fieldName.length());
            }
            // 不decode，避免pu参数是urlencode的情况，decode导致替换失败
            // return Uri.decode(tmp);
            return tmp;
        }
        return "";
    }


    public static String getHomeSearchUrl(String searchKey) {
        return getHomeSearchUrl(searchKey, "shouye");
    }

    private static String getHomeSearchUrl(String searchKey, String from) {
        return String.format("/api/v8/home_search/?from_type=%s&query=%s&is_json=0", from, URLEncoder.encode(searchKey));
    }

    public static String appendHomeSearchClickInfo(String searchKey, String from) {
        String clickInfo = String.format("&click_info={\"from_type\":\"%s\"}", from);
        return DeviceUtil.getInstance().appendWifiParam(
                getHomeSearchUrl(searchKey) + clickInfo);
    }
}
