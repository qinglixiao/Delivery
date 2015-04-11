package com.syswin.toon.bean.events;

import java.util.List;

/**参与者名单*/
public class ParticipantListsBean {
    public String result;
    public String operateTime;
    public String msg;
    public ItemData data;
    public class ItemData{
        public String sinceId;
        public String maxId;
        public List<ParticiBean> list;
        public class ParticiBean{
            public String cardId;
            public String nickname;
            public String avatar;
            public String userId;
            public String cardType;
            public String status;
            public String spreadTitle;
        }
    }
    
    public boolean isSuccess(){
        return "0".equals(result);
    }
}
