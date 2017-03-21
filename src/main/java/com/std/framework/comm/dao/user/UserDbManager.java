package com.std.framework.comm.dao.user;

import com.std.framework.comm.dao.BaseDao;

/**
 * Created by gfy on 2016/4/6.
 */
public class UserDbManager extends BaseDao {
    private static UserDbManager instance;

//    IDBAccess<User, String> userTableAccess;

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
//        userTableAccess = new GreenDaoAccess<>(innerDB.getSession().getUserDao());
    }

//    public int save(User user){
//        TODO
//        return 0;
//    }

}
