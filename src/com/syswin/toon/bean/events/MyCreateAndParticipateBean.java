package com.syswin.toon.bean.events;

import java.util.List;

public class MyCreateAndParticipateBean {
    public String result;
    public String msg;
    public String operateTime;
    public MyCreateAndParticipateTempData data;
    public class MyCreateAndParticipateTempData{
        public List<MyCreateAndParticipateData> list;
    }
    public boolean isSuccess(){
        return "0".equals(result);
    }
}
