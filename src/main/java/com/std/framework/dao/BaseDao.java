
package com.std.framework.dao;

import com.std.framework.util.SharedPreferencesUtil;

public abstract class BaseDao {

    protected InnerDB innerDB;

    public void connectionInnerDB() {
        innerDB = InnerDB.create(SharedPreferencesUtil.getUser());
        if (innerDB != null) {
            initAccess();
        }
    }

    public abstract void initAccess();

}
