
package com.syswin.toon.bean.events;

import java.util.List;

import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * 显示插件数据Bean
 * 
 * @author Mc
 */
public class ShowPluginBean extends BaseBean<ShowPluginBean> {

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
     * 插件显示icon(本地资源)
     */
    private int pluginImage;

    /**
     * 插件类型
     */
    private String pluginType;

    /**
     * 插件状态
     */
    private int pluginStatus;

    private int componentId;

    private String componentName;

    /**
     * 我的显示插件id
     */
    private long myPluginId;

    private String myPluginName;

    private int order;

    private int type;

    private int orgMyPluginId;

    private List<Object> otherAttributes;

    public int getPluginImage() {
        return pluginImage;
    }

    public void setPluginImage(int pluginImage) {
        this.pluginImage = pluginImage;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getOrgMyPluginId() {
        return orgMyPluginId;
    }

    public void setOrgMyPluginId(int orgMyPluginId) {
        this.orgMyPluginId = orgMyPluginId;
    }

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

    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public long getMyPluginId() {
        return myPluginId;
    }

    public void setMyPluginId(long myPluginId) {
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
    public ShowPluginBean parseJSON(JSONObject jsonObj) {
        return null;
    }

    @Override
    public JSONObject toJSON() {
        return null;
    }

    @Override
    public ShowPluginBean cursorToBean(Cursor cursor) {
        return null;
    }

    @Override
    public ContentValues beanToValues() {
        return null;
    }

}
