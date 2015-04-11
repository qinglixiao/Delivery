
package com.syswin.toon.bean.events;

/**
 * 活动编辑
 */
public class EventEditBean {
    private String result;

    private String msg;
    /**活动id*/
    private String activityId;
    /**二维码信息*/
    private String qrCode;

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

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
    /**状态*/
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public EventEditData getData() {
        return data;
    }

    public void setData(EventEditData data) {
        this.data = data;
    }
    /**状态，表示编辑*/
    private boolean status;
    /**活动信息*/
    private EventEditData data;
}
