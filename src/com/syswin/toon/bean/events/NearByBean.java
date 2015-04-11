
package com.syswin.toon.bean.events;

import java.io.Serializable;
import java.util.List;

/** 周边 */
public class NearByBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public String result;

    public String operateTime;

    public String msg;

    /** 数据集 */
    public NearbyData data;

    public class NearbyData {
        /** 分页总数 */
        public int total;

        /** 当前被拉取的是第几页 */
        public int currentPageNumber;

        /** 每页显示的数目 */
        public int eachPageItems;

        public List<NearbyInnerData> list;

        public class NearbyInnerData {
            /** 图标，名片、活动 */
            public String icon;

            /** 名称， 活动 */
            public String title;
            
            /** 名称， 名片*/
            public String name;

            /** 推广语， 名片、活动 */
            public String promotionStatement;

            /** 简介，名片 */
            public String introduction;

            /** 类型，名片、活动 */
            public String type;

            /** 职位，名片 */
            public String position;

            /** 类型，不填是全部，1附近的人 2企业 3-员工名片 4活动 5群组 */
            public int objectType;

            public String createdate;

            /** 距离 ,名片 活动 */
            public String score;

            /** 主键id,名片 企业 */
            public String id;
            /**活动群组*/
            public String objectId;
            public String userId;
            public String exchangeMode;

            /** 地址，活动 */
            public String address;

            /** 活动开始时间 */
            public String activityBeginDate;

            /** 活动结束时间 */
            public String activityEndDate;

            /** 活动报名数 */
            public String JoinActivityCount;
        }
    }

    /** TRUE表示返回值正常 */
    public boolean isSuccess() {
        return "0".equals(result);
    }
}
