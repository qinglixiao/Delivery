
package com.syswin.toon.bean.events;
/**活动编辑时活动的信息*/
public class EventEditData {
    /**活动发起者的userid*/
    private String userId;
    /**被邀请者名片id*/
    private String cardId;
    /**被邀请者名片名*/
    private String cardName;
    /**活动发起者名片id*/
    private String activityCardId;
    /**活动发起者名片名*/
    private String activityCardName;
    /**活动发起者名片头像*/
    private String activityCardPicUrl;
    /**活动id*/
    private String activityId;
    /**活动内容*/
    private String content;
    /**活动报名开始时间*/
    private String enrollStartTime;
    /**活动报名结束时间*/
    private String enrollEndTime;
    /**报名数*/
    private String enrollNum;
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getActivityCardPicUrl() {
        return activityCardPicUrl;
    }

    public void setActivityCardPicUrl(String activityCardPicUrl) {
        this.activityCardPicUrl = activityCardPicUrl;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getEnrollNum() {
        return enrollNum;
    }

    public void setEnrollNum(String enrollNum) {
        this.enrollNum = enrollNum;
    }

    
}
