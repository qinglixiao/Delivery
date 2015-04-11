
package com.syswin.toon.bean.events;

import java.util.List;

/**
 * @Description: 活动列表，所有item的集合的bean
 * @date 2014-11-4
 */
public class EventListDataBean {
    
    private String sinceId;// 上拉加载，从第几个开始

    private String pageSize;// 页面数

    private String maxId;// 最大ID

    private List<EventListItemBean> data;// 数据内容

	public String getSinceId() {
		return sinceId;
	}

	public void setSinceId(String sinceId) {
		this.sinceId = sinceId;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getMaxId() {
		return maxId;
	}

	public void setMaxId(String maxId) {
		this.maxId = maxId;
	}

	public List<EventListItemBean> getData() {
		return data;
	}

	public void setData(List<EventListItemBean> data) {
		this.data = data;
	}
    
    
}
