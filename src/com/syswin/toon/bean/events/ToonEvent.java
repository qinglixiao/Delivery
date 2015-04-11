package com.syswin.toon.bean.events;

public class ToonEvent extends BaseResultBean{

    /** 返回的数据 */
    public EventVoteData data;

    /**当为0的时候表示成功*/
    public boolean isSuccess(){
        return 0==getResult();
    }

}
