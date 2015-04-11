
package com.syswin.toon.bean.events;

import java.io.Serializable;

/**
 * 参与者名单，人员信息
 */
public class ParticipateInnerData implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 名片id */
    public String cardId;

    /** 名片名 */
    public String cardName;

    /** 名片头像 */
    public String headImage;

    /** 参与者状态 */
    public String status;

    /** 参与者的userid */
    public String userId;
}
