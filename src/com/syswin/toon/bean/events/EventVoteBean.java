
package com.syswin.toon.bean.events;


/**
 * 发布活动
 */
public class EventVoteBean extends BaseResultBean {
    /** 返回的数据 */
    public EventVoteData data;

    /**当为0的时候表示成功*/
    public boolean isSuccess(){
        return "0".equals(getResult());
    }
}
