package com.std.framework.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;


/**
 * Created by gfy on 2016/4/13.
 */
public class BaseTitleActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!setActionBar()){
            onCustomActionBar();
        }
    }

    protected boolean setActionBar(){
        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_USE_LOGO | ActionBar.DISPLAY_SHOW_TITLE);
        return true;
    }

    protected void onCustomActionBar(){

    }
}
