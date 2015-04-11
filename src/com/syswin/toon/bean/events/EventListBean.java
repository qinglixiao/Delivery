
package com.syswin.toon.bean.events;

import java.io.Serializable;

/**
 * 活动列表bean 活动查找列表
 * 
 * @date 2014-11-4
 */
public class EventListBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8711782861219240585L;

    private String result;// 返回值 0表示成功

    private String msg;// 返回值信息

    private EventListDataBean data;// 具体内容

    /** 请求的时间 */
    private String operateTime;

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public EventListDataBean getData() {
        return data;
    }

    public void setData(EventListDataBean data) {
        this.data = data;
    }

    public boolean isSuccess() {
        if (getResult().equals("0")) {
            return true;
        }
        return false;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
