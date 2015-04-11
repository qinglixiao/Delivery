
package com.syswin.toon.bean.events;

import java.util.List;

public class ShowBlock extends BasePlugin {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // 展示区数据:json
    private String blockData;

    // 暂时不用
    private String blockHtml;

    // 展板名称
    private String name;

    // 目标url
    private String targetUrl;

    // 插件位置标识
    private int position;

    // 匿名访问开关
    private int anonynousOn;

    // 插件管理员列表
    private List<Long> administrators;

    // 插件部署对象列表
    private List<Deploy> deployObjects;

    /**
     * 模块id 1:活动 2:名片 3:用户中心 4:通知 5:消息 6:群聊 7:通讯录 8:收藏夹 9:交互模块 10:搜索 展板及功能插件使用
     */
    private int componentId;

    /**
     * 绑定的用户级插件ID 展板插件使用
     */
    private long orgMyPluginId;

    /**
     * 用户对象id，可以是账号id或名片id 展板插件使用
     */
    private long orgUserObjId;

    /**
     * 用户对象id类型，1 是账号id；2名片id 展板插件使用
     */
    private int orgUserObjType;

    /**
     * 从属ID：如（活动ID，组织ID，群组ID，员工ID等） 展板插件使用
     */
    private long componentDataId;

    public ShowBlock() {
        // TODO Auto-generated constructor stub
    }

    public String getBlockData() {
        return blockData;
    }

    public void setBlockData(String blockData) {
        this.blockData = blockData;
    }

    public String getBlockHtml() {
        return blockHtml;
    }

    public void setBlockHtml(String blockHtml) {
        this.blockHtml = blockHtml;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getAnonynousOn() {
        return anonynousOn;
    }

    public void setAnonynousOn(int anonynousOn) {
        this.anonynousOn = anonynousOn;
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

    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    public long getOrgMyPluginId() {
        return orgMyPluginId;
    }

    public void setOrgMyPluginId(long orgMyPluginId) {
        this.orgMyPluginId = orgMyPluginId;
    }

    public long getOrgUserObjId() {
        return orgUserObjId;
    }

    public void setOrgUserObjId(long orgUserObjId) {
        this.orgUserObjId = orgUserObjId;
    }

    public int getOrgUserObjType() {
        return orgUserObjType;
    }

    public void setOrgUserObjType(int orgUserObjType) {
        this.orgUserObjType = orgUserObjType;
    }

    public long getComponentDataId() {
        return componentDataId;
    }

    public void setComponentDataId(long componentDataId) {
        this.componentDataId = componentDataId;
    }

}
