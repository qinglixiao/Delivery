
package com.syswin.toon.bean.events;

import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * 插件基类信息
 * 
 * @author furin
 */

public class BasePlugin extends BaseBean<BasePlugin> {

    private static final long serialVersionUID = 5694400503024364781L;

    // 插件级别：0，用户级；1，名片级
    private int levelType;

    // 插件类型Id
    // 针对展板插件类型Id:-1 基本信息；0 vcard信息
    private long appId;

    // 插件实例Id
    private long instanceId;

    public int getLevelType() {
        return levelType;
    }

    public void setLevelType(int levelType) {
        this.levelType = levelType;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(long instanceId) {
        this.instanceId = instanceId;
    }

    @Override
    public BasePlugin parseJSON(JSONObject jsonObj) {
        return null;
    }

    @Override
    public JSONObject toJSON() {
        return null;
    }

    @Override
    public BasePlugin cursorToBean(Cursor cursor) {
        return null;
    }

    @Override
    public ContentValues beanToValues() {
        return null;
    }

}
