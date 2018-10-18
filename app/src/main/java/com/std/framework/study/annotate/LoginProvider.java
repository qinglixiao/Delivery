package com.std.framework.study.annotate;

import com.std.framework.annotation.RouterPath;
import com.std.framework.annotation.RouterModule;

/**
 * Description:
 * Created by 李晓 ON 2017/12/9.
 * Job number:137289
 * Phone:18611867932
 * Email:lixiao3@syswin.com
 * Person in charge:李晓
 * Leader: 李晓
 */
@RouterModule(schema = "chunyu", host = "login")
public class LoginProvider {

    @RouterPath(value = "/toMain")
    public void toMain() {

    }

    @RouterPath(value = "/toAD")
    public void openAd(int type) {

    }
}
