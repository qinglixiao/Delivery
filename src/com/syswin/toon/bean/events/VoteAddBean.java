
package com.syswin.toon.bean.events;

/**
 * 增加投票
 */
public class VoteAddBean {
    public String result;
    public String operateTime;
    public String msg;
    /**添加投票所需信息*/
    public VoteData data;
    /**添加投票所需信息*/
    public class VoteData{
        /**插件id*/
        public String myPluginId;
        /**插件名*/
        public String myPluginName;
        /**插件类型id*/
        public String pluginId;
        /**发起者userid*/
        public String userId;
        /**名片id*/
        public String userCardId;
        /**投票id*/
        public String componentDataId;
        /**活动id*/
        public String componentId;
        /**状态*/
        public String status;
        /**排序*/
        public String sort;
        /**添加试驾*/
        public String createTime;
        /**更新时间*/
        public String updateTime;
        /**分页信息*/
        public Page page;
    }
    /**分页信息*/
    public class Page{
        public String sinceId;
        public String maxId;
        public String pageNum;
        public String pointer;
    }
}
