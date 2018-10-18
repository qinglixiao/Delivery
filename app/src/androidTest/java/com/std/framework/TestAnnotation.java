package com.std.framework;

import android.app.Application;

import com.std.framework.router.CYRouter;
import com.std.framework.router.interfaces.Capture;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/10/18.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class TestAnnotation {
    @Test
    public void testCall(){
        CYRouter.init(Mockito.mock(Application.class));
        CYRouter.open("")
        .call(null, new Capture() {
            @Override
            public void exception(Exception ex) {

            }
        });
    }
}
