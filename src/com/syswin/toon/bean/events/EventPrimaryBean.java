
package com.syswin.toon.bean.events;

import java.io.Serializable;

/** 活动预选 */
public class EventPrimaryBean implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 返回码 0表示成功 */
    public int result;

    /** 时间戳 */
    public String operationTime;

    /** 返回信息 */
    public String msg;

    /** 返回的数据体 */
    public Data data;

    /** 数据体 */
    public class Data {
    };
}
