package com.syswin.toon.bean.events;
/**
 * 二维码
 */
public class EventCodeBean {
    public int result;
    public String operateTime;
    public String msg;
    /**二维码信息*/
    public EventCodeData data;
    /**当为0的时候表示成功*/
    public boolean isSuccess(){
        return 0==result;
    }
}
