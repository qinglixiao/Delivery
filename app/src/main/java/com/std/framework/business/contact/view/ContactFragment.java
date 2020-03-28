package com.std.framework.business.contact.view;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.std.framework.R;
import com.std.framework.business.contact.shortcut_lib.LauncherUtil;
import com.std.framework.business.home.view.activity.MainTabActivity;
import com.std.framework.core.NavigationBar;
import com.std.framework.util.ToastUtil;

import butterknife.OnClick;
import me.std.base.base.STFragment;
import me.std.base.core.ActionBar;
import me.std.common.config.RuntimePermissions;
import me.std.common.utils.RuntimePermissionUtil;
import me.std.location.MapLocationUtil;

public class ContactFragment extends STFragment implements OnClickListener {
    private View view;
    private EditText editText;
    private Button button;
    private Button btn_short;
    private Button btn_location;
    private TextView tv_location;

    @Override
    protected void onActionBar(ActionBar.Builder builder) {
        builder.setTitle(R.string.main_tab_contact);
    }

    @Override
    protected View onCreateLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_three, null);
        editText = (EditText) view.findViewById(R.id.frag3_edt);
        button = (Button) view.findViewById(R.id.frag3_btn);
        btn_short = view.findViewById(R.id.btn_short_cut);
        btn_location = view.findViewById(R.id.button7);
        tv_location = view.findViewById(R.id.tv_location);
        registerListener();
        return view;
    }

    private void registerListener() {
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Log.d("LX", v.getText().toString());
                return false;
            }
        });
        button.setOnClickListener(this);
        btn_short.setOnClickListener(this);
        btn_location.setOnClickListener(this);
    }

    public void addShortcut(Context context, String shortcutName, Intent actionIntent, Intent.ShortcutIconResource icon, boolean allowRepeat) {
        Intent shortcutIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        shortcutIntent.putExtra("duplicate", allowRepeat);
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortcutName);
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, actionIntent);
        context.sendBroadcast(shortcutIntent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_short_cut:
                Intent intent = new Intent(getContext(), MainTabActivity.class);
                if (isShortCutExist(getContext(), "通讯录")) {
                    ToastUtil.show("快捷方式已存在");
                    return;
                }
//                Intent intent = new Intent(getContext(), MainTabActivity.class);
                addShortcut(getContext(), "通讯录", intent, Intent.ShortcutIconResource.fromContext(getContext(), R.drawable.main_app_home), true);
                break;
            default:
                break;
//            // TODO Auto-generated method stub
//            try {
//                Runtime.getRuntime().exec(new String[]{"input", "text", " fuytfrdrsxgf"});
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            break;
        }
    }

    @OnClick(R.id.button7)
    public void location(View view) {
        if (RuntimePermissionUtil.checkSelfPermission(getContext(), RuntimePermissions.ACCESS_FINE_LOCATION)) {
            MapLocationUtil.getInstance().startLocation(new MapLocationUtil.OnMapLocationListener() {
                @Override
                public void onLocationChanged(MapLocationUtil.STMapLocationInfo locationInfo, Error error) {
                    if (locationInfo != null) {
                        tv_location.setText(locationInfo.address);
                    } else if (error != null) {
                        tv_location.setText(error.getMessage());
                    }
                }
            });
        } else {
            ToastUtil.show("无定位权限");
        }
    }

    /**
     * 检查快捷方式是否存在 <br/>
     * <font color=red>注意：</font> 有些手机无法判断是否已经创建过快捷方式<br/>
     * 因此，在创建快捷方式时，请添加<br/>
     * shortcutIntent.putExtra("duplicate", false);// 不允许重复创建<br/>
     * 最好使用{@link #isShortCutExist(Context, String, Intent)}
     * 进行判断，因为可能有些应用生成的快捷方式名称是一样的的<br/>
     */
    public static boolean isShortCutExist(Context context, String title) {
        boolean result = false;
        try {
            ContentResolver cr = context.getContentResolver();
            Uri uri = getUriFromLauncher(context);
//            Uri uri = Uri.parse("content://com.huawei.android.launcher.settings/favorites?notify=true");

            Cursor c = cr.query(uri, new String[]{"title"}, "title like ? ", new String[]{title}, null);
            if (c != null && c.getCount() > 0) {
                result = true;
            }
            if (c != null && !c.isClosed()) {
                c.close();
            }
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 不一定所有的手机都有效，因为国内大部分手机的桌面不是系统原生的<br/>
     * 更多请参考{@link #isShortCutExist(Context, String)}<br/>
     * 桌面有两种，系统桌面(ROM自带)与第三方桌面，一般只考虑系统自带<br/>
     * 第三方桌面如果没有实现系统响应的方法是无法判断的，比如GO桌面<br/>
     */
    public static boolean isShortCutExist(Context context, String title, Intent intent) {
        boolean result = false;
        try {
            ContentResolver cr = context.getContentResolver();
            Uri uri = getUriFromLauncher(context);
            Cursor c = cr.query(uri, new String[]{"title", "intent"}, "title=?  and intent=?",
                    new String[]{title, intent.toUri(0)}, null);
            if (c != null && c.getCount() > 0) {
                result = true;
            }
            if (c != null && !c.isClosed()) {
                c.close();
            }
        } catch (Exception ex) {
            result = false;
            ex.printStackTrace();
        }
        return result;
    }

    private static Uri getUriFromLauncher(Context context) {
        StringBuilder uriStr = new StringBuilder();
        String authority = LauncherUtil.getAuthorityFromPermissionDefault(context);
        if (authority == null || authority.trim().equals("")) {
            authority = LauncherUtil.getAuthorityFromPermission(context, LauncherUtil.getCurrentLauncherPackageName(context) + ".permission.READ_SETTINGS");
        }
        uriStr.append("content://");
        if (TextUtils.isEmpty(authority)) {
            int sdkInt = android.os.Build.VERSION.SDK_INT;
            if (sdkInt < 8) { // Android 2.1.x(API 7)以及以下的
                uriStr.append("com.android.launcher.settings");
            } else if (sdkInt < 19) {// Android 4.4以下
                uriStr.append("com.android.launcher2.settings");
            } else {// 4.4以及以上
                uriStr.append("com.android.launcher3.settings");
            }
        } else {
            uriStr.append(authority);
        }
        uriStr.append("/favorites?notify=true");
        return Uri.parse(uriStr.toString());
    }

}
