
package com.syswin.toon.bean.events;

import java.io.Serializable;
import java.util.List;

/**
 * 新增活动公告
 */
public class EventAnnouncementAddBean implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public String result;

    public String operateTime;

    public String msg;
    /**公告信息*/
    public EventAnnouncementData data;

}
