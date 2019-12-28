package me.std.common.core;

import android.content.Intent;
import android.text.TextUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/11/23.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class ObjectArgsParser {

    public static Intent toIntent(Object... args) {
        Intent intent = new Intent();
        if (args != null && args.length % 2 == 0) {
            int length = args.length;
            for (int i = 0; i < length; i += 2) {
                if (args[i] == null || TextUtils.isEmpty(args[i].toString())) {
                    throw new IllegalArgumentException("!!! key is empty");
                }
                String key = args[i].toString();
                Object value = args[i + 1];
                if (value == null) {
                    continue;
                }
                if (Serializable.class.isAssignableFrom(value.getClass())) {
                    intent.putExtra(key, (Serializable) value);
                    continue;
                }
                switch (value.getClass().getSimpleName()) {
                    case "Integer":
                        intent.putExtra(key, (int) value);
                        break;
                    case "String":
                        intent.putExtra(key, value.toString());
                        break;
                    case "Long":
                        intent.putExtra(key, (long) value);
                        break;
                    case "Float":
                        intent.putExtra(key, (float) value);
                        break;
                    case "Boolean":
                        intent.putExtra(key, (boolean) value);
                        break;
                    default:
                        break;
                }
            }
        }
        return intent;
    }

    public static Intent toIntent(Map<String, Object> args) {
        Intent intent = new Intent();
        if (args != null) {
            for (Map.Entry<String, Object> arg : args.entrySet()) {
                if (arg.getKey() == null || TextUtils.isEmpty(arg.getKey().toString())) {
                    throw new IllegalArgumentException("!!! key is empty");
                }
                String key = arg.getKey().toString();
                Object value = arg.getValue();
                if (value == null) {
                    continue;
                }
                if (Serializable.class.isAssignableFrom(value.getClass())) {
                    intent.putExtra(key, (Serializable) value);
                    continue;
                }
                switch (value.getClass().getSimpleName()) {
                    case "Integer":
                        intent.putExtra(key, (int) value);
                        break;
                    case "String":
                        intent.putExtra(key, value.toString());
                        break;
                    case "Long":
                        intent.putExtra(key, (long) value);
                        break;
                    case "Float":
                        intent.putExtra(key, (float) value);
                        break;
                    case "Boolean":
                        intent.putExtra(key, (boolean) value);
                        break;
                    default:
                        break;
                }
            }
        }
        return intent;
    }
}
