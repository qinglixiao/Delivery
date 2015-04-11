
package com.syswin.toon.bean.events;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import android.text.TextUtils;

/**
 * 发起活动 参数管理
 */
public class EventCreateDetailBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** 活动发起者名片id */
    private String cardId = "-1";

    /** 活动发起者名片名 */
    private String cardName;

    /** 活动名 */
    private String name;

    /** 活动描述 */
    private String des;

    /** 活动推广语 */
    private String spreadTitle;

    /** 活动开始时间 */
    private String startTime;

    /** 活动结束时间 */
    private String endTime;

    /** 活动地址 */
    private String address;

    /** 活动纬度 */
    public double lat = 1010;

    /** 活动经度 */
    public double lng = 1010;

    /** 邀请者 */
    private String activityMember;

    /** 报名机制 */
    private String enrollType = "1";

    /** 群聊roomid */
    private String roomId;

    /** 群聊开关 0是打开 1是关闭 */
    private String isOpenRoom = "0";

    /** 报名开始时间 */
    private String enrollStartTime;

    /** 报名结束时间 */
    private String enrollEndTime;

    /** 报名项 */
    private String enrollInfoItems;

    /** 是否公开，1公开0不公开 */
    private String isPublic = "1";

    /** 参与者报名 1保密0不保密 */
    private String isMemberPublic = "0";

    /** 操作类型 1发起活动 3编辑活动 */
    private String operationType = "1";

    /** 大类id */
    private String category;

    /** 子类id */
    private String channel;

    /** 子类名 */
    private String channelName;

    public String getIsOpenRoom() {
        return isOpenRoom;
    }

    public void setIsOpenRoom(String isOpenRoom) {
        this.isOpenRoom = isOpenRoom;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getSpreadTitle() {
        return spreadTitle;
    }

    public void setSpreadTitle(String spreadTitle) {
        this.spreadTitle = spreadTitle;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getActivityMember() {
        return activityMember;
    }

    public void setActivityMember(String activityMember) {
        this.activityMember = activityMember;
    }

    public String getEnrollType() {
        return enrollType;
    }

    public void setEnrollType(String enrollType) {
        this.enrollType = enrollType;
    }

    public String getEnrollStartTime() {
        return enrollStartTime;
    }

    public void setEnrollStartTime(String enrollStartTime) {
        this.enrollStartTime = enrollStartTime;
    }

    public String getEnrollEndTime() {
        return enrollEndTime;
    }

    public void setEnrollEndTime(String enrollEndTime) {
        this.enrollEndTime = enrollEndTime;
    }

    public String getEnrollInfoItems() {
        return enrollInfoItems;
    }

    public void setEnrollInfoItems(String enrollInfoItems) {
        this.enrollInfoItems = enrollInfoItems;
    }

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }

    public String getIsMemberPublic() {
        return isMemberPublic;
    }

    public void setIsMemberPublic(String isMemberPublic) {
        this.isMemberPublic = isMemberPublic;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /** 将信息转为map */
    public Map<String, String> beanToMap(EventCreateDetailBean bean) {
        Map<String, String> mParamMap = new HashMap<String, String>();
        mParamMap.put("cardId", bean.getCardId());
        mParamMap.put("cardName", bean.getCardName());
        mParamMap.put("name", bean.getName());
        mParamMap.put("des", bean.getDes());
        mParamMap.put("spreadTitle", bean.getSpreadTitle());
        mParamMap.put("startTime", bean.getStartTime());
        mParamMap.put("endTime", bean.getEndTime());
        mParamMap.put("address", bean.getAddress());
        if (lat != 1010 && lng != 1010) {
            mParamMap.put("lat", lat + "");
            mParamMap.put("lng", lng + "");
        }
        mParamMap.put("activityMember", bean.getActivityMember());
        mParamMap.put("enrollType", bean.getEnrollType());
        mParamMap.put("enrollStartTime", bean.getEnrollStartTime());
        mParamMap.put("enrollEndTime", bean.getEnrollEndTime());
        mParamMap.put("enrollInfoItems", bean.getEnrollInfoItems());
        mParamMap.put("isPublic", bean.getIsPublic());
        mParamMap.put("isMemberPublic", bean.getIsMemberPublic());
        mParamMap.put("operationType", bean.getOperationType());
        mParamMap.put("isOpenRoom", bean.getIsOpenRoom());
        mParamMap.put("category", bean.getCategory());
        mParamMap.put("channel", bean.getChannel());
        mParamMap.put("channelName", bean.getChannelName());

        return mParamMap;
    }

    /** 将信息转为map */
    public Map<String, String> beanToMapCreate(EventCreateDetailBean bean) {
        Map<String, String> mParamMap = new HashMap<String, String>();
        mParamMap.put("cardId", bean.getCardId());
        mParamMap.put("cardName", bean.getCardName());
        mParamMap.put("name", bean.getName());
        mParamMap.put("spreadTitle", bean.getSpreadTitle());
        
        if (!TextUtils.isEmpty(bean.getStartTime())) {
            mParamMap.put("startTime", bean.getStartTime());
        }
        if (!TextUtils.isEmpty(bean.getEndTime())) {
            mParamMap.put("endTime", bean.getEndTime());
        }
        if (!TextUtils.isEmpty(bean.getAddress())) {
            mParamMap.put("address", bean.getAddress());
        }
        if (lat != 1010 && lng != 1010) {
            mParamMap.put("lat", lat + "");
            mParamMap.put("lng", lng + "");
        }
        mParamMap.put("operationType", bean.getOperationType());
        return mParamMap;
    }
}
