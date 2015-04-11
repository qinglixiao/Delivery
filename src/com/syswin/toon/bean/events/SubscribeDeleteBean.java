
package com.syswin.toon.bean.events;

import java.io.Serializable;

/** 删除订阅 */
public class SubscribeDeleteBean implements Serializable {
    private static final long serialVersionUID = 1L;

    public int result;

    public String operateTime;

    public class data {
    };

    public String msg;
}
