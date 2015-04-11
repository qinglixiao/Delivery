
package com.syswin.toon.bean.events;

import java.util.List;

/** 发起活动返回的数据 */
public class EventVoteData {
    public ToonActivity toonActivity;

    public class ToonActivity {
        public ToonFrame anonymousFrame;// 浏览者界面

        public ToonFrame friendFrame;// 参与者界面

        public ToonFrame manageFrame;// 发起者的界面

        public ActivityBasicInfo activityBasicInfo;// 活动基本信息

        public List<ShowBlock> shoPlugins;// 用于活动设置的展板信息

        public List<PluginItem> pluginItems;// 用于活动设置的功能插件信息

        public List<PluginItem> unUsePlugins;// 活动详情中未使用的功能插件信息
        
        public long timestamp;
        /** 活动基本信息 */
        public class ActivityBasicInfo {
            public String userId;

            public long cardId;

            public String cardName;

            public long activityId;

            public String name;

            public String category;

            public String channel;

            public String des;

            public String spreadTitle;

            public String startTime;

            public String endTime;

            public String address;

            public double lat;

            public double lng;

            /** 加入模式：0开放 1 申请 2 邀请 */
            public String enrollType;

            public String registrationItems;// 申请表设置的基本数据

            public String maxNum;

            public String isPublic;

            public String isMemberPublic;

            public String activityMember;

            public String broadcastCategory;

            public String broadcastSubCategory;

            public String isActivityEnable;

            public String isActivityPublic;

            public String activityNo;

            public String isGroupEnable;

            public String isGroupPublic;

            public String joinerNum;

            public String broadcastLocation;

            public String cardType;

        }

    }
}
