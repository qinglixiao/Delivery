
package com.syswin.toon.bean.events;

import java.io.Serializable;

/**
 * 修改和删除活动公告
 */
public class EventAnnouncementDeleteBean implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**返回码  0表示成功*/
    public String result;

    public String operateTime;
    /**返回的信息*/
    public String msg;

    public ContentData data;

    public class ContentData implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
    }
}
