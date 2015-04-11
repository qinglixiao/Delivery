
package com.syswin.toon.bean.events;

/**
 * 广播设置
 */
public class BroadcastSetBean {
    private String result;

    private String msg;

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

    public BroadcastData getData() {
        return data;
    }

    public void setData(BroadcastData data) {
        this.data = data;
    }

    private BroadcastData data;
    
    private String operationTime;

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    /**
     * 数据体
     */
    public class BroadcastData {
        
    }
}
