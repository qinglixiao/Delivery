
package com.syswin.toon.bean.events;

import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * 功能插件网络请求返回
 * 
 * @author Mc
 */
public class FunctionPluginResultBean extends BaseResultBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private FunctionPluginBean data;

    public FunctionPluginBean getData() {
        return data;
    }

    public void setData(FunctionPluginBean data) {
        this.data = data;
    }

    @Override
    public FunctionPluginResultBean parseJSON(JSONObject jsonObj) {
        return null;
    }

    @Override
    public JSONObject toJSON() {
        return null;
    }

    @Override
    public FunctionPluginResultBean cursorToBean(Cursor cursor) {
        return null;
    }

    @Override
    public ContentValues beanToValues() {
        return null;
    }

}
