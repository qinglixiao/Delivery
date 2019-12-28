package me.std.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Set;

/**
 * Description: sp文件存储工具类，项目中涉及到sp文件操作的皆用此类
 * Author: lixiao
 * Create on: 2018/7/26.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class SharedPreferencesUtil {
    private static SharedPreferencesUtil instance;
    private static HashMap<String, SharedPreferencesUtil> personal = new HashMap<>();

    private static final String CY = "chunyu_sp";
    private SharedPreferences.Editor sp_edit;
    private SharedPreferences sp;

    //*******************Add Key*****************/
    private static final String CLINIC_USER_NAME_KEY = "clinic_user_name_key";
    private static final String CLINIC_USER_ID_KEY = "clinic_user_id_key";
    private static final String CLINIC_USER_PHONE_KEY = "clinic_user_phone_key";
    public static final String CY_DOMAIN = "cy_domain";

    //*******************End********************/

    private SharedPreferencesUtil(String name) {
        sp = AppContextUtil.getAppContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        sp_edit = sp.edit();
    }

    /**
     * 获取默认sp文件操作类
     *
     * @return
     */
    public static SharedPreferencesUtil getDefault() {
        if (instance == null) {
            instance = new SharedPreferencesUtil(CY);
        }
        return instance;
    }

    /**
     * 获取指定名字的sp文件操作类
     *
     * @param sp_name sp名称
     * @return
     */
    public static SharedPreferencesUtil getPersonal(String sp_name) {
        if (TextUtils.isEmpty(sp_name)) {
            return null;
        } else if (personal.containsKey(sp_name)) {
            return personal.get(sp_name);
        } else {
            SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(sp_name);
            personal.put(sp_name, sharedPreferencesUtil);
            return sharedPreferencesUtil;
        }
    }

    /**
     * 保存诊所用户姓名
     *
     * @param name 诊所用户姓名
     * @return 是否保存成功
     */
    public boolean saveClinicUserName(String name) {
        return saveString(CLINIC_USER_NAME_KEY, name);
    }

    /**
     * 获取诊所用户姓名
     *
     * @return 姓名
     */
    public String getClinicUserName() {
        return getString(CLINIC_USER_NAME_KEY);
    }

    /**
     * 保存诊所用户id
     *
     * @param id 诊所用的id
     * @return 是否保存成功
     */
    public boolean saveClinicUserId(String id) {
        return saveString(CLINIC_USER_ID_KEY, id);
    }

    /**
     * 获取诊所用户id
     *
     * @return 诊所用户id
     */
    public String getClinicUserId() {
        return getString(CLINIC_USER_ID_KEY);
    }

    /**
     * 保存诊所用户电话
     *
     * @param phone 诊所用户电话
     * @return 是否保存成功
     */
    public boolean saveClinicUserPhone(String phone) {
        return saveString(CLINIC_USER_PHONE_KEY, phone);
    }

    /**
     * 获取诊所用户电话
     *
     * @return 诊所用户电话
     */
    public String getClinicUserPhone() {
        return getString(CLINIC_USER_PHONE_KEY);
    }

    //********************以下方法为对外扩充使用********************/
    public boolean saveString(String key, String value) {
        if (TextUtils.isEmpty(key)) {
            return false;
        }
        sp_edit.putString(key, value);
        return sp_edit.commit();
    }

    @SuppressLint("NewApi")
    public boolean saveStringSet(String key, Set<String> values) {
        if (TextUtils.isEmpty(key)) {
            return false;
        }
        sp_edit.putStringSet(key, values);
        return sp_edit.commit();
    }

    public boolean saveLong(String key, long value) {
        if (TextUtils.isEmpty(key)) {
            return false;
        }
        sp_edit.putLong(key, value);
        return sp_edit.commit();
    }

    public boolean saveBoolean(String key, boolean value) {
        if (TextUtils.isEmpty(key)) {
            return false;
        }
        sp_edit.putBoolean(key, value);
        return sp_edit.commit();
    }

    public boolean saveFloat(String key, float value) {
        if (TextUtils.isEmpty(key)) {
            return false;
        }
        sp_edit.putFloat(key, value);
        return sp_edit.commit();
    }

    public boolean saveInt(String key, int value) {
        if (TextUtils.isEmpty(key)) {
            return false;
        }
        sp_edit.putInt(key, value);
        return sp_edit.commit();
    }

    /**
     * 批量存储
     *
     * @param key_value 键值对
     *                  value:Integer/String/Long/Float/Boolean
     * @return
     */
    public boolean save(Object... key_value) {
        if (null == key_value || key_value.length % 2 != 0) {
            throw new IllegalArgumentException("null == key_value || key_value.length % 2 != 0");
        }
        int length = key_value.length;
        for (int i = 0; i < length; i += 2) {
            if (key_value[i] == null || TextUtils.isEmpty(key_value[i].toString())) {
                throw new IllegalArgumentException("!!! key == null || key == ''");
            }
            String key = key_value[i].toString();
            Object value = key_value[i + 1];
            if (value == null) {
                sp_edit.putString(key, null);
                continue;
            }
            switch (value.getClass().getSimpleName()) {
                case "Integer":
                    sp_edit.putInt(key, (int) value);
                    break;
                case "String":
                    sp_edit.putString(key, value.toString());
                    break;
                case "Long":
                    sp_edit.putLong(key, (long) value);
                    break;
                case "Float":
                    sp_edit.putFloat(key, (float) value);
                    break;
                case "Boolean":
                    sp_edit.putBoolean(key, (boolean) value);
                    break;
                default:
                    break;
            }
        }
        return sp_edit.commit();
    }

    public String getString(String key) {
        if (TextUtils.isEmpty(key)) {
            return "";
        }
        return sp.getString(key, "");
    }

    public String getString(String key, String defValue) {
        if (TextUtils.isEmpty(key)) {
            return "";
        }
        return sp.getString(key, defValue);
    }

    @SuppressLint("NewApi")
    public Set<String> getStringSet(String key, Set<String> defValue) {
        if (TextUtils.isEmpty(key)) {
            return null;
        }
        return sp.getStringSet(key, defValue);
    }

    public int getInt(String key, int defValue) {
        if (TextUtils.isEmpty(key)) {
            throw new IllegalArgumentException("key == null");
        }
        return sp.getInt(key, defValue);
    }

    public long getLong(String key, long defValue) {
        if (TextUtils.isEmpty(key)) {
            throw new IllegalArgumentException("key == null");
        }
        return sp.getLong(key, defValue);
    }

    public float getFloat(String key, float defValue) {
        if (TextUtils.isEmpty(key)) {
            throw new IllegalArgumentException("key == null");
        }
        return sp.getFloat(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        if (TextUtils.isEmpty(key)) {
            throw new IllegalArgumentException("key == null");
        }

        return sp.getBoolean(key, defValue);
    }

    public void clear() {
        sp_edit.clear();
    }


    public boolean contains(String key) {
        return sp.contains(key);
    }

    public boolean remove(String key) {
        if (TextUtils.isEmpty(key)) {
            return false;
        }
        sp_edit.remove(key);
        return sp_edit.commit();
    }
}
