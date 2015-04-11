package com.syswin.toon.bean.events;
/**
 * 二维码信息
 * @date 2014-11-21
 */
public class EventCodeData {
    /**代表二维码的字符串*/
    public String QRCode;
    public UserCard userCard;
    public String avatar;
    public String name;
    public String spreadTitle;
    public class UserCard{
        public String cardId;
        public String permissionType;
        public String cardNo;
        public String nickname;
        public String nicknamePinyin;
        public String introduction;
        public String avatar;
        public String bigImage;
        public String cardType;
        public String userId;
        public String ownerId;
        public String creatorId;
        public String ord;
        public String createTime;
        public String updateTime;
        public String status;
        public String openStatus;
        public String exchangeMode;
        public String exchangeForms;
        public String lbsStatus;
        public String locationDetail;
        public String locationCoordinate;
        public String templateId;
        public String useStatus;
        public String registeredPhoneBindStatus;
        public String fieldValueList;
    }
}
