package com.syswin.toon.bean.events;

import java.io.Serializable;
import java.util.List;

/**
 * 活动参与者
 *
 */
public class ParticipantListData implements Serializable{

    private static final long serialVersionUID = 1L;
    public Page page;
    /**参与者集*/
    public List<ParticipateInnerData> list;
    /**分页相关信息*/
    public class Page{
        /**上拉加载时需传*/
        public String sinceId;
        /**下拉刷新时需传*/
        public String maxId;
        /**当前页*/
        public String pageNum;
    }
    
}
