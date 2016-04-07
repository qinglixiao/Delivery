package com.std.framework.dao.user;

import com.cn.sale.dao.entity.User;
import com.library.core.db.GreenDaoAccess;
import com.library.core.db.IDBAccess;
import com.std.framework.dao.BaseDao;

/**
 * Created by gfy on 2016/4/6.
 */
public class UserDbManager extends BaseDao {
    private static UserDbManager instance;

    IDBAccess<User, String> userTableAccess;

    public static UserDbManager getInstance() {
        if (null == instance) {
            synchronized (UserDbManager.class) {
                if (null == instance) {
                    instance = new UserDbManager();
                }
            }
        }
        instance.connectionToonDB();
        return instance;
    }

    @Override
    public void initAccess() {
        userTableAccess = new GreenDaoAccess<>(innerDB.getSession().getUserDao());
    }

    public int save(User user){


        return 0;
    }

}
