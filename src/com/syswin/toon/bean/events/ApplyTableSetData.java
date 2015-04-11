package com.syswin.toon.bean.events;

import java.io.Serializable;
import java.util.List;
/** 申请表设置,数据 */
public class ApplyTableSetData implements Serializable{
    /** 是否自动审核 1是 0不是 */
    public String isAudit;

    public List<ApplyTable> fields;
}
