package com.std.framework.business.call.view.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.std.framework.R;
import com.std.framework.basic.BaseTitleActivity;
import com.std.framework.business.call.contract.VoiceSelContract;
import com.std.framework.core.NavigationBar;
import com.std.framework.databinding.VoiceRecordBinding;

/**
 * Description :
 * Author:       lx
 * Create on:  2016/9/6
 * Modify by：lx
 *
 */
public class VoiceRecordActivity extends BaseTitleActivity implements VoiceSelContract.View {
    private VoiceRecordBinding voiceRecordBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(this, R.layout.activity_voice_record, null);
        voiceRecordBinding = DataBindingUtil.bind(view);
        setContentView(view);
        findViewById(R.id.parent).setBackgroundColor(getResources().getColor(R.color.transparent));
    }

    @Override
    protected void onNavigationBar(NavigationBar.Builder navBuilder) {
        navBuilder.setTitle("录制");
    }

    @Override
    public void setPresenter(VoiceSelContract.Presenter presenter) {

    }

    @Override
    public Context getContext() {
        return null;
    }
}
