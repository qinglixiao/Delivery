
package com.std.framework.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.std.framework.basic.App;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Description : 敏感词过滤工具类
 * Created by lx on 2015/9/9
 * Job number：137289
 * Phone ：        18611867932
 * Email：          lixiao3@syswin.com
 * Person in charge : lx
 * Leader：lx
 */
public class StringMatchUtil {
    private static int minMatchTYpe = 1; // 最小匹配规则

    private static int maxMatchType = 2; // 最大匹配规则

    /**
     * @Description: 初始化敏感词库，将敏感词加入到HashMap中，构建DFA算法模型
     * @version 1.0
     */
    static class SensitiveWordInit {
        private String ENCODING = "UTF-8"; // 字符编码

        // 敏感词库
        private static final String ILLEGALFILE = "illegalwords.txt";

        public HashMap sensitiveWordMap;

        public SensitiveWordInit() {
            super();
        }

        @SuppressWarnings("rawtypes")
        public Map initKeyWord() {
            try {
                // 读取敏感词库
                Set<String> keyWordSet = readSensitiveWordFile();
                // 将敏感词库加入到HashMap中
                addSensitiveWordToHashMap(keyWordSet);
                // spring获取application，然后application.setAttribute("sensitiveWordMap",sensitiveWordMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return sensitiveWordMap;
        }

        /**
         * @param keyWordSet 敏感词库
         * @version 1.0
         */
        @SuppressWarnings({
                "rawtypes", "unchecked"
        })
        private void addSensitiveWordToHashMap(Set<String> keyWordSet) {
            sensitiveWordMap = new HashMap(keyWordSet.size()); // 初始化敏感词容器，减少扩容操作
            String key = null;
            Map nowMap = null;
            Map<String, String> newWorMap = null;
            // 迭代keyWordSet
            Iterator<String> iterator = keyWordSet.iterator();
            while (iterator.hasNext()) {
                key = iterator.next(); // 关键字
                nowMap = sensitiveWordMap;
                for (int i = 0; i < key.length(); i++) {
                    char keyChar = key.charAt(i); // 转换成char型
                    Object wordMap = nowMap.get(keyChar); // 获取

                    if (wordMap != null) { // 如果存在该key，直接赋值
                        nowMap = (Map)wordMap;
                    } else { // 不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                        newWorMap = new HashMap<String, String>();
                        newWorMap.put("isEnd", "0"); // 不是最后一个
                        nowMap.put(keyChar, newWorMap);
                        nowMap = newWorMap;
                    }

                    if (i == key.length() - 1) {
                        nowMap.put("isEnd", "1"); // 最后一个
                    }
                }
            }
        }

        /**
         * 读取敏感词库中的内容，将内容添加到set集合中
         * 
         * @return
         * @version 1.0
         * @throws Exception
         */
        private Set<String> readSensitiveWordFile() throws Exception {
            Set<String> set = null;
            InputStreamReader read = new InputStreamReader(AppUtil.getAppContext()
                    .getAssets().open(ILLEGALFILE), ENCODING);
            BufferedReader bufferedReader = null;
            try {
                set = new HashSet<>();
                bufferedReader = new BufferedReader(read);
                String txt = null;
                while ((txt = bufferedReader.readLine()) != null) { // 读取文件，将文件内容放入到set中
                    set.add(txt);
                }
            } catch (Exception e) {
            } finally {
                read.close(); // 关闭文件流
                if(bufferedReader != null){
                    bufferedReader.close();
                }
            }
            return set;
        }
    }

    /**
     * @Description: 敏感词过滤
     * @version 1.0
     */
    static class SensitivewordFilter {
        @SuppressWarnings("rawtypes")
        private Map sensitiveWordMap = null;

        /**
         * 构造函数，初始化敏感词库
         */
        public SensitivewordFilter() {
            sensitiveWordMap = new SensitiveWordInit().initKeyWord();
        }

        /**
         * 判断文字是否包含敏感字符
         * 
         * @param txt 文字
         * @param matchType 匹配规则&nbsp;1：最小匹配规则，2：最大匹配规则
         * @return 若包含返回true，否则返回false
         * @version 1.0
         */
        public boolean isContaintSensitiveWord(String txt, int matchType) {
            boolean flag = false;
            for (int i = 0; i < txt.length(); i++) {
                int matchFlag = this.CheckSensitiveWord(txt, i, matchType); // 判断是否包含敏感字符
                if (matchFlag > 0) { // 大于0存在，返回true
                    flag = true;
                }
            }
            return flag;
        }

        /**
         * 获取文字中的敏感词
         * 
         * @param txt 文字
         * @param matchType 匹配规则&nbsp;1：最小匹配规则，2：最大匹配规则
         * @return
         * @version 1.0
         */
        public Set<String> getSensitiveWord(String txt, int matchType) {
            Set<String> sensitiveWordList = new HashSet<String>();

            for (int i = 0; i < txt.length(); i++) {
                int length = CheckSensitiveWord(txt, i, matchType); // 判断是否包含敏感字符
                if (length > 0) { // 存在,加入list中
                    sensitiveWordList.add(txt.substring(i, i + length));
                    i = i + length - 1; // 减1的原因，是因为for会自增
                }
            }

            return sensitiveWordList;
        }

        /**
         * 替换敏感字字符
         * 
         * @param txt
         * @param matchType
         * @param replaceChar 替换字符，默认*
         * @version 1.0
         */
        public String replaceSensitiveWord(String txt, int matchType, String replaceChar) {
            String resultTxt = txt;
            Set<String> set = getSensitiveWord(txt, matchType); // 获取所有的敏感词
            Iterator<String> iterator = set.iterator();
            String word = null;
            String replaceString = null;
            while (iterator.hasNext()) {
                word = iterator.next();
                replaceString = getReplaceChars(replaceChar, word.length());
                resultTxt = resultTxt.replaceAll(word, replaceString);
            }

            return resultTxt;
        }

        /**
         * 获取替换字符串
         * 
         * @param replaceChar
         * @param length
         * @return
         * @version 1.0
         */
        private String getReplaceChars(String replaceChar, int length) {
            StringBuilder rp = new StringBuilder();
            for (int i = 1; i < length; i++) {
                rp.append(replaceChar);
            }

            return rp.toString();
        }

        /**
         * 检查文字中是否包含敏感字符，检查规则如下：<br>
         * 
         * @param txt
         * @param beginIndex
         * @param matchType
         * @return，如果存在，则返回敏感词字符的长度，不存在返回0
         * @version 1.0
         */
        @SuppressWarnings({
            "rawtypes"
        })
        public int CheckSensitiveWord(String txt, int beginIndex, int matchType) {
            boolean flag = false; // 敏感词结束标识位：用于敏感词只有1位的情况
            int matchFlag = 0; // 匹配标识数默认为0
            char word = 0;
            Map nowMap = sensitiveWordMap;
            for (int i = beginIndex; i < txt.length(); i++) {
                word = txt.charAt(i);
                nowMap = (Map)nowMap.get(word); // 获取指定key
                if (nowMap != null) { // 存在，则判断是否为最后一个
                    matchFlag++; // 找到相应key，匹配标识+1
                    if ("1".equals(nowMap.get("isEnd"))) { // 如果为最后一个匹配规则,结束循环，返回匹配标识数
                        flag = true; // 结束标志位为true
                        if (minMatchTYpe == matchType) { // 最小规则，直接返回,最大规则还需继续查找
                            break;
                        }
                    }
                } else { // 不存在，直接返回
                    break;
                }
            }
            if (matchFlag < 2 || !flag) { // 长度必须大于等于1，为词
                matchFlag = 0;
            }
            return matchFlag;
        }

    }

    /**
     * 判断文本中是否包含敏感词汇
     * 
     * @param txt
     * @return true : 包含 false: 不包含
     */
    public static boolean isIllegalWord(String txt) {
        SensitivewordFilter filter = new SensitivewordFilter();
        return filter.isContaintSensitiveWord(txt, 1);
    }

    /**
     * 判断是否合法，并弹出土司
     * @param context
     * @param str
     * @param showToast 提示语
     * @return
     */
    public static boolean isIllegalWord(Context context, String str, String showToast) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (isIllegalWord(str)) {
            Toast.makeText(context, showToast, Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
    
}
