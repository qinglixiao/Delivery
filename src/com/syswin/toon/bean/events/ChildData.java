
package com.syswin.toon.bean.events;

import java.io.Serializable;

/**
 * 子类数据
 */
public class ChildData implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 子类id
     */
    public String channelId;

    /**
     * 子类名
     */
    public String channelName;

    /**
     * 子类是否有效 1有效0无效
     */
    public String status;

    public String createTime;

    public String updateTime;

    public String recommendStatus;

    private boolean isChoose = false;

    private boolean isCancle = false;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getRecommendStatus() {
        return recommendStatus;
    }

    public void setRecommendStatus(String recommendStatus) {
        this.recommendStatus = recommendStatus;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean isChoose) {
        this.isChoose = isChoose;
    }

    public boolean isCancle() {
        return isCancle;
    }

    public void setCancle(boolean isCancle) {
        this.isCancle = isCancle;
    }

}
