package com.std.framework.business.user.db.manager;

import com.std.framework.business.user.db.entity.User;
import com.std.framework.core.db.BaseDao;

import table.UserDao;


/**
 * Created by gfy on 2016/4/6.
 */
public class UserDbManager extends BaseDao {
    private static UserDbManager instance;
    private UserDao userDao;

    private UserDbManager() {
        super();
        userDao = getSession().getUserDao();
    }

    public static UserDbManager getInstance() {
        if (null == instance) {
            synchronized (UserDbManager.class) {
                if (null == instance) {
                    instance = new UserDbManager();
                }
            }
        }
        return instance;
    }

    public void insert(User user) {
        userDao.insert(user);
    }

}
