package com.syswin.toon.bean.events;

import java.io.Serializable;
import java.util.List;

import android.text.TextUtils;
/**添加字段界面的某一字段*/
public class ApplyTable implements Serializable{
    /** 字段名 */
    public String fieldName;

    /** 字段类型 文本类型 ：text 单选：radio 多选：checkbox */
    public String fieldType;

    /** 是否为空 0否 1是 */
    public String isNull;

    /** 字段值集合 */
    public List<String> optionValue;
    
    /**用户报名后填写的信息*/
    public List<String> checkedValue;
    
    /**将checkedValue的值以字符串形式获得*/
    public String selectedValue(){
        String str = "";
        for(String s:checkedValue){
            str = s+",";
        }
        if(!TextUtils.isEmpty(str)){
            str = str.substring(0, str.length()-1);
        }
        return str;
    }

}
