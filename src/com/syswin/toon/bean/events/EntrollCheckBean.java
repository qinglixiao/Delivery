
package com.syswin.toon.bean.events;

import java.util.List;
/**
 * 报名审核
 */
public class EntrollCheckBean {
    public int result;

    public String operateTime;

    public Data data;

    public String msg;
    /**
     * 具体数据
     */
    public class Data {
        public Page page;
        /**
         * 人员列表
         */
        public List<CheckUser> list;
    }
    /**
     * 分页相关
     */
    public class Page {
        /**上拉加载时所需要的id */
        public long sinceId;
        /**下拉刷新时所需要的id */
        public long maxId;
        /**页标 */
        public int pageNum;
    }
    /**人员信息 */
    public class CheckUser {
        public String userId;
        /**名片id */
        public String cardId;
        /**名片名 */
        public String cardName;
        /**头像url */
        public String headImage;
        /**有效状态 */
        public int status;

        public CheckUser(String userId, String cardId, String cardName, String headImage, int status) {
            super();
            this.userId = userId;
            this.cardId = cardId;
            this.cardName = cardName;
            this.headImage = headImage;
            this.status = status;
        }
        
        
    }

}
