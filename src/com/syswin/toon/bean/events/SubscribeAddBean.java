
package com.syswin.toon.bean.events;

import java.io.Serializable;

/**
 * 增加订阅
 */
public class SubscribeAddBean implements Serializable {
    private static final long serialVersionUID = 1L;
    public int result;
    public String operateTime;
    public Data data;
    public class Data{
        public String errorMsg;
        public String msgKey;
        public String status;
    };
    public String msg;
}
