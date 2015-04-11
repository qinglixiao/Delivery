package com.syswin.toon.bean.events;

import java.io.Serializable;
/**
 * 参与者名单
 *
 */
public class ParticipantListBean implements Serializable{

    private static final long serialVersionUID = 1L;
    public String result;
    public String operateTime;
    public String msg;
    /**名单数据*/
    public ParticipantListData data;

}
