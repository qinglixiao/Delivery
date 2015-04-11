
package com.syswin.toon.bean.events;

import java.io.Serializable;

/**
 * 公告
 */
public class EventAnnouncementData implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 公告id
     */
    public String noticeId;

    /**
     * 公告标题
     */
    public String noticeTitle;

    /**
     * 公告内容
     */
    public String noticeContent;

    /**
     * 公告发起者的useid
     */
    public String noticeUserId;

    /**
     * 公告发起者的cardid
     */
    public String userObjId;

    /**
     * 公告来源
     */
    public String noticeSource;

    /**
     * 公告状态
     */
    public String noticeStatus;

    /**
     * 公告发起时间
     */
    public String createTime;

    /**
     * 公告更新时间
     */
    public String updateTime;

    /**
     * 插件名
     */
    public String myPluginId;

    /**
     * 公告所属的活动id
     */
    public String componentDataId;

    public EventAnnouncementInnerData page;

    /**
     * 公告分页所需的信息
     */
    public class EventAnnouncementInnerData implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        public String sinceId;

        public String maxId;

        public String pageNum;

        public String pointer;
    }
}
