
package com.syswin.toon.bean.events;

import java.util.List;

import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * 功能插件数据Bean
 * 
 * @author Mc
 */
public class FunctionPluginBean extends BaseBean<FunctionPluginBean> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 插件id
     */
    private int pluginId;

    /**
     * 插件名称
     */
    private String pluginName;

    /**
     * 插件描述
     */
    private String pluginDes;

    /**
     * 插件跳转url
     */
    private String pluginUrl;

    /**
     * 插件图标地址，用逗号分隔
     */
    private String pluginLogoUrl;

    /**
     * 插件类型
     */
    private String pluginType;

    /**
     * 插件状态
     */
    private int pluginStatus;

    private int componetId;

    private String componetName;

    /**
     * 我的显示插件id
     */
    private int myPluginId;

    private String myPluginName;

    private int order;

    private List<Object> otherAttributes;

    public int getPluginId() {
        return pluginId;
    }

    public void setPluginId(int pluginId) {
        this.pluginId = pluginId;
    }

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    public String getPluginDes() {
        return pluginDes;
    }

    public void setPluginDes(String pluginDes) {
        this.pluginDes = pluginDes;
    }

    public String getPluginUrl() {
        return pluginUrl;
    }

    public void setPluginUrl(String pluginUrl) {
        this.pluginUrl = pluginUrl;
    }

    public String getPluginLogoUrl() {
        return pluginLogoUrl;
    }

    public void setPluginLogoUrl(String pluginLogoUrl) {
        this.pluginLogoUrl = pluginLogoUrl;
    }

    public String getPluginType() {
        return pluginType;
    }

    public void setPluginType(String pluginType) {
        this.pluginType = pluginType;
    }

    public int getPluginStatus() {
        return pluginStatus;
    }

    public void setPluginStatus(int pluginStatus) {
        this.pluginStatus = pluginStatus;
    }

    public int getComponetId() {
        return componetId;
    }

    public void setComponetId(int componetId) {
        this.componetId = componetId;
    }

    public String getComponetName() {
        return componetName;
    }

    public void setComponetName(String componetName) {
        this.componetName = componetName;
    }

    public int getMyPluginId() {
        return myPluginId;
    }

    public void setMyPluginId(int myPluginId) {
        this.myPluginId = myPluginId;
    }

    public String getMyPluginName() {
        return myPluginName;
    }

    public void setMyPluginName(String myPluginName) {
        this.myPluginName = myPluginName;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public List<Object> getOtherAttributes() {
        return otherAttributes;
    }

    public void setOtherAttributes(List<Object> otherAttributes) {
        this.otherAttributes = otherAttributes;
    }

    @Override
    public FunctionPluginBean parseJSON(JSONObject jsonObj) {
        return null;
    }

    @Override
    public JSONObject toJSON() {
        return null;
    }

    @Override
    public FunctionPluginBean cursorToBean(Cursor cursor) {
        return null;
    }

    @Override
    public ContentValues beanToValues() {
        return null;
    }

}
