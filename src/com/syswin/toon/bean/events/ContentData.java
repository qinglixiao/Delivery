package com.syswin.toon.bean.events;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * 一个大的分类
 * 当进行增量更新时，返回的是有变化的大类
 */
public class ContentData implements Serializable {
    /**
     * 大类Id
     */
    private String categoryId;
    /**
     * 大类名
     */
    private String categoryName;

    private ArrayList<ChildData> channelList;

    private String createTime;

    private String updateTime;

    private String status;//1 有效    0  无效

    private String createUser;
    
    private String updateUser;
    
    private String recommendStatus;

	public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getRecommendStatus() {
        return recommendStatus;
    }

    public void setRecommendStatus(String recommendStatus) {
        this.recommendStatus = recommendStatus;
    }

    public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public ArrayList<ChildData> getChannelList() {
		return channelList;
	}

	public void setChannelList(ArrayList<ChildData> channelList) {
		this.channelList = channelList;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


    
}
