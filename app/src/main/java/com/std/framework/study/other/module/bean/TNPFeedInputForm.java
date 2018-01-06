package com.std.framework.study.other.module.bean;

import java.util.List;

/**
 * Description:
 * Created by 李晓 ON 2018/1/5.
 * Job number:137289
 * Phone:18611867932
 * Email:lixiao3@syswin.com
 * Person in charge:李晓
 * Leader: 李晓
 */
public class TNPFeedInputForm {
    private String feedId;
    private List<String> feedIds;
    private String version;

    public TNPFeedInputForm() {
    }

    public String getFeedId() {
        return this.feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public List<String> getFeedIds() {
        return this.feedIds;
    }

    public void setFeedIds(List<String> feedIds) {
        this.feedIds = feedIds;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
