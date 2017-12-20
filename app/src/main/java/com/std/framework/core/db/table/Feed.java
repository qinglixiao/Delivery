package com.std.framework.core.db.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Description:
 * Created by 李晓 ON 2017/12/19.
 * Job number:137289
 * Phone:18611867932
 * Email:lixiao3@syswin.com
 * Person in charge:李晓
 * Leader: 李晓
 */
@Entity
public class Feed {
    @Id
    private String id;

    @Generated(hash = 1830579604)
    public Feed(String id) {
        this.id = id;
    }

    @Generated(hash = 1810414124)
    public Feed() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
