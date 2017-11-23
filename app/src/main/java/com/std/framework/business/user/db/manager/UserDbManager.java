package com.std.framework.business.user.db.manager;

import com.std.framework.business.user.db.entity.User;
import com.std.framework.core.db.BaseDao;

import db.table.UserDao;

/**
 * Created by gfy on 2016/4/6.
 */
public class UserDbManager extends BaseDao {
    private static UserDbManager instance;
    private UserDao userDao;

    public static UserDbManager getInstance() {
        if (null == instance) {
            synchronized (UserDbManager.class) {
                if (null == instance) {
                    instance = new UserDbManager();
                }
            }
        }
        instance.connectionInnerDB();
        return instance;
    }

    @Override
    public void initAccess() {
        userDao = getSession().getUserDao();
    }

    public void insert(User user){
        userDao.insert(user);
    }

}
