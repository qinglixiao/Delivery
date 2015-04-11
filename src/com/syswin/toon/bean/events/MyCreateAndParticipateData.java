package com.syswin.toon.bean.events;
/**缓存我发起和参与活动的列表中的一条数据*/
public class MyCreateAndParticipateData {
    public String cardId;
    public String activityId;
    /**代表活动与名片的关系   1发起   2参与*/
    public String type;
    public String operateTime;
    /**代表该条记录的操作   0添加   1删除*/
    public int isDel;
    public String avatar;//发起者头像
    public String name;//活动名称
    
    
}
