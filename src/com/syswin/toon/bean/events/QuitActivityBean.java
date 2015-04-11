package com.syswin.toon.bean.events;
/**
 * 退出活动
 */
public class QuitActivityBean {
    /**返回码  0表示成功*/
    public String result;
    /**时间戳*/
    public String operationTime;
    /**返回的信息*/
    public String msg;
    /**返回的数据*/
    public QuitData data;
    /**返回的数据*/
    public class QuitData{
        /**错误信息*/
        public String errorMsg;
        /**信息key*/
        public String msgKey;
        /**信息状态*/
        public String status;
    }
}
