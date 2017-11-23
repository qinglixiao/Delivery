package com.std.framework.business.user.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Description:
 * Created by 李晓 ON 2017/11/23.
 * Job number:137289
 * Phone:18611867932
 * Email:lixiao3@syswin.com
 * Person in charge:李晓
 * Leader: 李晓
 */
@Entity
public class User {
    @Id
    private String id;

    private String name;

    @Generated(hash = 1037321026)
    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
