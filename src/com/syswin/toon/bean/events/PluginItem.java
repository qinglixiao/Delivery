
package com.syswin.toon.bean.events;

import java.io.Serializable;
import java.util.List;

public class PluginItem implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public long appId;

    public long instanceId;

    // 插件Icon地址
    private String icon;

    // 插件名称
    private String name;

    /*
     * 目标地址：点击操作指向页面 原生页面结构：android:app://xxx|ios:app//xxx
     * h5页面:http://xx.sqtoon.com/plugin.html
     */
    private String targetUrl;

    // 插件实现类型：0：原生；1：html5
    private int targetType;

    // 位置信息
    private int position;

    // 插件公开/关闭标识
    private int anonymousOn;

    // 插件管理员列表
    private List<Long> administrators;

    // 插件部署对象列表
    private List<Deploy> deployObjects;

    public PluginItem() {
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTargetType() {
        return targetType;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getAnonymousOn() {
        return anonymousOn;
    }

    public void setAnonymousOn(int anonymousOn) {
        this.anonymousOn = anonymousOn;
    }

    public void setTargetType(int targetType) {
        this.targetType = targetType;
    }

    public List<Long> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(List<Long> administrators) {
        this.administrators = administrators;
    }

    public List<Deploy> getDeployObjects() {
        return deployObjects;
    }

    public void setDeployObjects(List<Deploy> deployObjects) {
        this.deployObjects = deployObjects;
    }

}
