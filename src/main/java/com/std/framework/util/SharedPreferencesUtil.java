package com.std.framework.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.std.framework.basic.App;

/**
 * Created by gfy on 2016/4/6.
 */
public class SharedPreferencesUtil {
    private static final String file = "sp_data";

    private static SharedPreferences.Editor sp_edit;
    private static SharedPreferences sp;

    /**存储项目key*/
    private static final String USER_KEY = "user_key";
    private static final String START_FIRST = "start_first";

    static {
        sp = App.instance.getSharedPreferences(file, Context.MODE_PRIVATE);
        sp_edit = sp.edit();
    }

    public static boolean putUser(String user){
        sp_edit.putString(USER_KEY,user);
        return sp_edit.commit();
    }

    public static String getUser(){
        return sp.getString(USER_KEY,"");
    }

    public static boolean putStartSign(){
        sp_edit.putBoolean(START_FIRST,true);
        return sp_edit.commit();
    }

    public static boolean isStartFirst(){
        return sp.getBoolean(START_FIRST,false);
    }
}
