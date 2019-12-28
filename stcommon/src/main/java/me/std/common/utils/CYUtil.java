package me.std.common.utils;

import android.text.TextUtils;

import java.util.Calendar;

/**
 * Created by Roger Huang on 2019/1/23.
 */

public class CYUtil {
    public static boolean isIdentityCardValid(String identityCard) {
        if (TextUtils.isEmpty(identityCard)) return false;
        if (identityCard.length() == 15) {
            identityCard = transIDCard15to18(identityCard);
        }

        if (!TextUtils.isEmpty(identityCard) && identityCard.length() == 18) {
            int sigma = 0;
            Integer[] a = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
            String[] w = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
            try {
                for (int i=0; i<17; i++) {
                    int ai = Integer.parseInt(identityCard.substring(i,i+1));
                    int wi = a[i];
                    sigma += ai * wi;
                }
            }catch (Exception e){
                return false;
            }

            int number = sigma % 11;
            String check_number = w[number];
            if (identityCard.substring(17).equals(check_number)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param IdCardNO
     * @return 18位标准身份证号
     * 方法用途：15位身份证转化为18位标准证件号
     * @return String
     * @author 我心自在
     */
    public static String transIDCard15to18(String IdCardNO){
        String cardNo=null;
        if(null!=IdCardNO&&IdCardNO.trim().length()==15){
            IdCardNO=IdCardNO.trim();
            StringBuffer sb=new StringBuffer(IdCardNO);
            sb.insert(6, "19");
            sb.append(transCardLastNo(sb.toString()));
            cardNo=sb.toString();
        }
        return cardNo;
    }
    /**
     * 方法用途：15位补全‘19’位后的身份证号码
     * @param newCardId
     * @return
     * @return String
     * @author 我心自在
     */
    private static String transCardLastNo(String newCardId){
        char[] ch=newCardId.toCharArray();
        int m=0;
        int [] co={7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};
        char [] verCode=new char[]{'1','0','X','9','8','7','6','5','4','3','2'};
        for (int i = 0; i < newCardId.length(); i++) {
            m+=(ch[i]-'0')*co[i];
        }
        int residue=m%11;
        return String.valueOf(verCode[residue]);

    }

    public static boolean isCNChar(char c) {
        return (c >= 0x4e00 && c <= 0x9fa5);
    }

    public static boolean isCNStr(String s) {
        for (char c : s.toCharArray())
            if (!isCNChar(c))
                return false;
        return true;
    }

    public static boolean containsCNChar(String s) {
        for (char c : s.toCharArray())
            if (isCNChar(c))
                return true;
        return false;
    }

    /** 中国公民身份证号码最小长度。 */
    public  final int CHINA_ID_MIN_LENGTH = 15;

    /** 中国公民身份证号码最大长度。 */
    public  final int CHINA_ID_MAX_LENGTH = 18;
    /**
     * 根据身份编号获取年龄
     *
     * @param idCard
     *            身份编号
     * @return 年龄
     */
    public static int getAgeByIdCard(String idCard) {
        int iAge = 0;
        Calendar cal = Calendar.getInstance();
        String year = idCard.substring(6, 10);
        int iCurrYear = cal.get(Calendar.YEAR);
        iAge = iCurrYear - Integer.valueOf(year);
        return iAge;
    }

    /**
     * 根据身份编号获取生日
     *
     * @param idCard 身份编号
     * @return 生日(yyyyMMdd)
     */
    public static String getBirthByIdCard(String idCard) {
        return String.format("%d-%02d-%02d", getYearByIdCard(idCard), getMonthByIdCard(idCard), getDateByIdCard(idCard));
    }

    /**
     * 根据身份编号获取生日年
     *
     * @param idCard 身份编号
     * @return 生日(yyyy)
     */
    public static Short getYearByIdCard(String idCard) {
        return Short.valueOf(idCard.substring(6, 10));
    }

    /**
     * 根据身份编号获取生日月
     *
     * @param idCard
     *            身份编号
     * @return 生日(MM)
     */
    public static Short getMonthByIdCard(String idCard) {
        return Short.valueOf(idCard.substring(10, 12));
    }

    /**
     * 根据身份编号获取生日天
     *
     * @param idCard
     *            身份编号
     * @return 生日(dd)
     */
    public static Short getDateByIdCard(String idCard) {
        return Short.valueOf(idCard.substring(12, 14));
    }

    /**
     * 根据身份编号获取性别
     *
     * @param idCard 身份编号
     * @return 性别(M-男，F-女，N-未知)
     */
    public static String getGenderByIdCard(String idCard) {
        String sCardNum = idCard.substring(16, 17);
        if (Integer.parseInt(sCardNum) % 2 != 0) {
            return "1";
        } else {
            return "2";
        }
    }
}
