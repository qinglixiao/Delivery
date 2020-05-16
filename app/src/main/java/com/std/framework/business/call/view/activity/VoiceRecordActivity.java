package com.std.framework.business.call.view.activity;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.std.framework.R;
import com.std.framework.business.call.contract.VoiceSelContract;
import com.std.framework.databinding.VoiceRecordBinding;

import me.std.base.base.STActivity;
import me.std.base.core.ActionBar;

/**
 * Description :
 * Author:       lx
 * Create on:  2016/9/6
 * Modify by：lx
 *
 */
public class VoiceRecordActivity extends STActivity implements VoiceSelContract.View {
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
    protected void onActionBar(ActionBar.Builder builder) {
        builder.setTitle("录制");
    }

    @Override
    public void setPresenter(VoiceSelContract.Presenter presenter) {

    }

    @Override
    public Context getContext() {
        return null;
    }
}
