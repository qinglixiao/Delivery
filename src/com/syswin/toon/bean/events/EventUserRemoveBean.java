
package com.syswin.toon.bean.events;

import java.io.Serializable;

/**
 * 取消已经参加活动的用户
 */
public class EventUserRemoveBean implements Serializable{
    private static final long serialVersionUID = 1L;
    public int result;
    public String operationTime;
    public String msg;
    public Data data;
    public class Data{};
}
