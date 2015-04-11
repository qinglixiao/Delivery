
package com.syswin.toon.bean.events;
/**添加文章 */
public class AddArticleBean {
    public int result;

    public String operateTime;

    public Data data;

    public String msg;

    public class Data {
        public int articleId;

        public int userId;

        public int userCardId;

        public String title;

        public String content;

        public int source;

        public String status;

        public String createTime;

        public String updateTime;

        public int myPluginId;

        public int componentDataId;

        public Page page;
    }

    public class Page {
        public int sinceId;

        public int maxId;

        public int pageNum;

        public int pointer;
    }
}
