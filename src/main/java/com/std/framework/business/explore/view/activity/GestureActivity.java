package com.std.framework.business.explore.view.activity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.std.framework.R;
import com.std.framework.basic.BaseActivity;
import com.std.framework.basic.BaseTitleActivity;
import com.std.framework.core.NavigationBar;

/**
 * Description:
 * Created by 李晓 ON 2017/1/3.
 * Job number:137289
 * Phone:18611867932
 * Email:lixiao3@syswin.com
 * Person in charge:李晓
 * Leader: 李晓
 */
public class GestureActivity extends BaseTitleActivity {
    private GestureDetector gestureDetector;

    @Override
    public void onNavigationBar(NavigationBar navigation) {
        navigation.setTitle("手势");
    }

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_gesture);
        gestureDetector = new GestureDetector(simpleOnGestureListener);
    }

    private GestureDetector.SimpleOnGestureListener simpleOnGestureListener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}
