
package com.syswin.toon.bean.events;


/**
 * 用户报名
 */
public class UserEnrollBean {
    /**返回码   0表示成功*/
    public String result;
    public String operateTime;
    public String msg;
    /**报名返回的信息*/
    public EnrollData data;
    public class EnrollData{}
}
