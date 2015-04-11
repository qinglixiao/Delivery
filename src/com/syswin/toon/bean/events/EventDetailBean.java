
package com.syswin.toon.bean.events;

import java.io.Serializable;
import java.util.List;

/**
 * 活动详情
 */
public class EventDetailBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String SUCCESS = "0";

    public String result;

    public String msg;

    public EventDetailData data;

    public class EventDetailData implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * 用户身份 1:发起;2:参加;3:报名;4:未报名
         */
        public String status;

        /** 判断该活动是不是被邀请的 */
        public String isInvited;

        /** 被邀请者的cardId */
        public String cardId;

        /** 显示插件集 */
        public List<ShowPluginBean> usedShowPluginList;

        /** 活动详情 */
        public ActivityDetailForm activityDetailForm;

        /** 已经添加的功能插件集 */
        public List<FunctionPluginBean> usedFunctionPluginList;

        /** 未使用的功能插件集 */
        public List<FunctionPluginBean> unusedFunctionPluginList;
    }

    public boolean isSuccess() {
        if (result.equals(SUCCESS)) {
            return true;
        } else {
            return false;
        }
    }
}
