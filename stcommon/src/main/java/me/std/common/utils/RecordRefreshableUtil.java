package me.std.common.utils;

import java.util.HashSet;
import java.util.Set;

/**
  * Description: 记录一个页面是否需要刷新
  * Author: huangyuan
  * Create on: 2018/10/12
  * Job number: 1800829
  * Phone: 13120112517
  * Email: huangyuan@chunyu.me
  */
public class RecordRefreshableUtil {

    public  enum Key {
        HEALTH_PROGRAM_LIST, HEALTH_PROGRAM_TIP, PATIENT_PROFILE_LIST, COMMENT_NICKNAME, USER_CENTER
    }

    private static final Set<Key> mRefreshSet = new HashSet<>();

    public static boolean isRefresh(Key key) {
        boolean flag = mRefreshSet.contains(key);
        if (flag) {
            mRefreshSet.remove(key);
        }
        return flag;
    }

    public static void setRefresh(Key key) {
        mRefreshSet.add(key);
    }
}
