
package com.syswin.toon.bean.events;

import java.io.Serializable;

/**
 * 确定用户参加活动
 */
public class EventUserJoinBean implements Serializable {
    private static final long serialVersionUID = 1L;

    public int result;

    public String operationTime;

    public String msg;

    public Data data;

    public class Data {

    }
}
