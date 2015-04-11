
package com.syswin.toon.bean.events;

import java.io.Serializable;
import java.util.List;

/**
 * 订阅列表
 */
public class SubscribeListBean implements Serializable {
    private static final long serialVersionUID = 1L;

    public String result;

    public String msg;

    public String operateTime;

    public SubscribeListData data;

    public class SubscribeListData {

        public List<SubscribeBean> list;
    }

    public class SubscribeBean {
        public String subscribeId;

        public String userId;

        public int type;

        public String contentJson;

        /** 1时，不加小红点； 0时，加小红点 */
        public String status;
    }

}
