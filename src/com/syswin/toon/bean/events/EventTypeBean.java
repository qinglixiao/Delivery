
package com.syswin.toon.bean.events;

import java.io.Serializable;
import java.util.List;
/**活动类型*/
public class EventTypeBean implements Serializable {
    private static final long serialVersionUID = 1L;

    public String result;

    public String operateTime;

    public String msg;

    public TypeData data;
    /**活动类型的具体信息*/
    public class TypeData {
        public List<ContentData> subscribeList;
    }
    public boolean isSuccess(){
        return "0".equals(result);
    }
}
