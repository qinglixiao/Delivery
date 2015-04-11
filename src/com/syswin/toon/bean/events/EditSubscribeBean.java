
package com.syswin.toon.bean.events;
/**
 * 订阅编辑
 */
public class EditSubscribeBean {
    public int result;

    public String operateTime;

    public Data data;

    public String msg;

    public class Data {
        public UserScribeBean userSubscribe;
    }
    /**
     * 订阅的详细信息
     */
    public class UserScribeBean {
        /**订阅id*/
        public String subscribeId;
        /**userid*/
        public String userId;
        /**类型*/
        public String type;
        /**数据*/
        public String contentJson;
        /**添加时间*/
        public String addTime;
        /**编辑时间*/
        public String editTime;
        /**状态*/
        public String status;
        /**排序*/
        public int sort;
    }
}
