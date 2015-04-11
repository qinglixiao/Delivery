
package com.syswin.toon.bean.events;

/**
 * 收藏
 */
public class CollectionBean {
    /**
     * 返回码 0表示成功
     */
    public String result;

    /**
     * 返回的提示信息
     */
    public String msg;

    /**
     * 返回的数据
     */
    public CollectionData data;

    /**
     * 时间戳
     */
    public String operateTime;
    
    /**
     * 数据体
     */
    public class CollectionData{
        
    }
}
