package me.std.flutterbridge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import me.std.base.base.ChunyuActivity;
import me.std.base.core.ActionBar;
import me.std.common.utils.Logger;
import me.std.common.utils.ViewUtil;
import me.std.flutterbridge.bridge.BridgeInvoker;
import me.std.flutterbridge.bridge.IBridgeView;
import me.std.flutterbridge.bridge.specs.BridgeActionButtonSpec;
import me.std.flutterbridge.bridge.specs.BridgeResult;
import me.std.flutterbridge.bridge.specs.FlutterPageParameter;

public class CYFlutterActivity extends ChunyuActivity implements IBridgeView {
    private CYFlutterChannel mChannel = null;
    private CYFlutterView mFlutterView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flutter);
        mFlutterView = findViewById(R.id.flutter_view);
        mChannel = mFlutterView.getFlutterChannel();
        mChannel.getBridgeContext().bridgeView = this;

        fromIntent();
    }


    public void fromIntent() {
        FlutterPageParameter p = (FlutterPageParameter) getIntent().getSerializableExtra(FlutterPageParameter.ARG_PARAMETER);
        if (p != null) {
            mChannel.getBridgeContext().pageInitArgs = p.parameters;
        }
        update(p);
    }

    @Override
    public void onBackPressed() {
        // Activity中点击返回按钮时，先尝试pop flutter页面，再pop Activity
        mChannel.getBridgeContext().invoker.onBack(new BridgeInvoker.SimpleCallback() {
            @Override
            public void onResult(Boolean completed) {
                if (!completed) {
                    CYFlutterActivity.super.onBackPressed();
                }
            }

        });
    }

    @Override
    public void update(FlutterPageParameter parameter) {
        if (parameter == null) return;

        if (parameter.title != null) {
            actionBar.setTitle(parameter.title);
        }

        if (!parameter.showNavigationBar) {
            actionBar.hide();
        } else {
            ViewUtil.visible(actionBar.getHeader());
        }

        if (!TextUtils.isEmpty(parameter.getRoute())) {
            mFlutterView.setRoute(parameter.getRoute());
        }

        if (parameter.rightBarButtons != null) {
            if (parameter.rightBarButtons.size() > 0) {
                final BridgeActionButtonSpec spec = parameter.rightBarButtons.get(0);
                actionBar.setRightButton1(spec.title, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mChannel.invoke(spec.callback.method, spec.data, null);
                    }
                });
                actionBar.show(ActionBar.FLAG_VISUAL_RIGHT_BUTTON1);
            } else {
                actionBar.gone(ActionBar.FLAG_VISUAL_RIGHT_BUTTON1);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Activity.RESULT_OK == resultCode) {
            switch (requestCode) {
                default:
                    try {
                        String result = "";
                        if (data != null) {
                            result = data.getStringExtra("result");
                        }
                        JSONObject obj = new JSONObject();
                        if (!TextUtils.isEmpty(result)) {
                            obj.put("result", result);
                        }
                        obj.put("request_code", requestCode);
                        mChannel.invoke("on_result", BridgeResult.make(obj, null), new BridgeInvoker.Callback() {
                            @Override
                            public void onResult(Object object, Error error) {

                            }
                        });
                    } catch (JSONException ex) {
                        Logger.e("CYFlutterActivity", ex.getMessage());
                    }
                    break;
            }
        }
    }
}
