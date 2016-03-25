package test;

import android.app.Application;
import android.content.pm.PackageManager;
import android.test.ApplicationTestCase;

/**
 * Created by gfy on 2016/1/3.
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
    public void test(){

    }

//    public void testcheck(){
//        PackageManager pm = getPackageManager();
//        boolean permission = (PackageManager.PERMISSION_GRANTED ==
//                pm.checkPermission("android.permission.READ_CONTACTS", ""));
//        if (permission) {
//
//        }else {
//
//        }
//    }
}
