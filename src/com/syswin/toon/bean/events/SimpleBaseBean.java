package com.syswin.toon.bean.events;
/**活动简介、申请表设置、选择加入模式*/
public class SimpleBaseBean {
    public String msg;
    public String result;
    public String operateTime;
    public SimpleBaseData data;
    public class SimpleBaseData{
        
    }
    public boolean isSuccess(){
        return "0".equals(result);
    }
}
