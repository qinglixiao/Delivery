package me.std.common.utils;

import java.util.regex.Pattern;

/**
 * Description:
 * Author: lixiao
 * Create on: 2019/1/23.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class RegexUtil {
    /**
     * 验证身份证号码
     *
     * @param card 居民身份证号码15位或18位，最后一位可能是数字或字母
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean isIdentityCard(String card) {
        String regex = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([\\d|x|X]{1})$";
        return Pattern.matches(regex, card);
    }

}
