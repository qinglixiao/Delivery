
package com.syswin.toon.bean.events;

import java.io.Serializable;
/**
 * 活动详情数据
 */
public class ActivityDetailForm implements Serializable{
    /** */
    private static final long serialVersionUID = 1L;
    /**活动id */
    public String activityId;
    /**活动发起者名片id */
    public String publishCardId;
    /**活动发起者名片名 */
    public String publishCardName;
    /**活动发起者名片头像url */
    public String activityCardPicUrl;
    /**活动发起者userid */
    public String publishUserId;
    /**活动名称 */
    public String name;
    /**活动描述 */
    public String des;
    /**推广语 */
    public String spreadTitle;
    /**活动开始时间 */
    public String startDate;
    /**活动结束时间 */
    public String endTime;
    /**活动地点 */
    public String address;
    /**报名开始时间 */
    public String enrollStartTime;
    /**报名结束时间 */
    public String enrollEndTime;
    /**报名数量 */
    public String enrollNum;
    /**活动地址纬度 */
    public String lat;
    /**活动地址经度 */
    public String lng;
    /**报名项 */
    public String enrollInfoItems;
    /**报名机制  1开启0不开启 */
    public String enrollType;
    /**邀请过得朋友 */
    public String activityMember;
    /**是否公开  1公开0不公开 */
    public String isPublic;
    /**参与者保密  1保密0不保密 */
    public String isMemberPublic;
    /**群聊roomid */
    public String roomId;
    /**群聊groupid */
    public String groupId;
    /**是否开启群聊，0开启1不开启 */
    public String isOpenRoom;
    /**大类id */
    public String category;
    /**子类id */
    public String channel;
    /**子类名 */
    public String channelName;

    public boolean isMemberPublic(){
        if(isMemberPublic.equals("0")){
            return true;
        }else{
            return false;
        }
    }

    public boolean isOpenGroupRoom(){
    	if(isOpenRoom.equals("0")){
    		return true;
    	}else{
    		return false;
    	}
    }
}
