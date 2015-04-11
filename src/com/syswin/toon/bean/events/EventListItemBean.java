
package com.syswin.toon.bean.events;

import java.io.Serializable;

/**
 * @Description: 活动列表，每个item的活动
 * @date 2014-11-4
 */
public class EventListItemBean implements Serializable {

    // 新增加的字段
    /** 活动发起者userid */
    private String publishUserId;

    /** 该活动参与者名单 */
    private String participantList;

    /** 该活动最后更新的时间 */
    private String updateTime;

    // ////////////////////////////
    private String activityId;// 活动id

    private String name;// 活动名称

    private String address;// 活动地址

    private String startTime;// 开始时间 "2014-03-06 12:55:55"

    private String activityCardId;// 活动发布者名片id

    private String picUrl;// 活动发布者的头像

    /** 推广语 */
    private String spreadTitle;

    private String endTime;// 结束时间 "2014-03-06 12:55:55"

    private String cardId;// 我的名片id

    private String cardType;// 名片类型

    private String enrollNum;// 报名人数

    private String category;

    private String channel;

    private String activityUserId;

    private String activityMembers;

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
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

    public String getActivityUserId() {
        return activityUserId;
    }

    public void setActivityUserId(String activityUserId) {
        this.activityUserId = activityUserId;
    }

    public String getActivityMembers() {
        return activityMembers;
    }

    public void setActivityMembers(String activityMembers) {
        this.activityMembers = activityMembers;
    }

    private String activityCardName;// 活动发布者的名片名称

    private String activityType;// 活动类型

    private String status;// "用户和活动的关系（1:发起;2:参加;3:报名;4;未报名）"

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEnrollNum() {
        return enrollNum;
    }

    public void setEnrollNum(String enrollNum) {
        this.enrollNum = enrollNum;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getActivityCardId() {
        return activityCardId;
    }

    public void setActivityCardId(String activityCardId) {
        this.activityCardId = activityCardId;
    }

    public String getActivityCardName() {
        return activityCardName;
    }

    public void setActivityCardName(String activityCardName) {
        this.activityCardName = activityCardName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getSpreadTitle() {
        return spreadTitle;
    }

    public void setSpreadTitle(String spreadTitle) {
        this.spreadTitle = spreadTitle;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getPublishUserId() {
        return publishUserId;
    }

    public void setPublishUserId(String publishUserId) {
        this.publishUserId = publishUserId;
    }

    public String getParticipantList() {
        return participantList;
    }

    public void setParticipantList(String participantList) {
        this.participantList = participantList;
    }
}
