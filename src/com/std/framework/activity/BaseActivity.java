package com.std.framework.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.std.framework.R;
import com.std.framework.comm.STDActivityManager;
import com.std.framework.comm.STDFragmentManager;
import com.std.framework.interfaces.OnTipClickListener;
import com.std.framework.util.WidgetUtil;
import com.std.framework.widget.dialog.MessageTip;

public class BaseActivity extends AppCompatActivity {
	/**视图管理器*/
	public final STDFragmentManager FragmentManager;

	public BaseActivity() {
		FragmentManager = STDFragmentManager.getInstance(this);
		/**添加activity到栈*/
		STDActivityManager.getInstance().add(this);
	}

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		/**销毁栈信息*/
		STDActivityManager.getInstance().remove(this);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				onBack();
				return true;
		}
		return true;
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		/** 
		int groupId = 0;
		int itemId = 0;
		int order = 1;

		//sample1
		menu.add("sample1")
		.setIcon(R.drawable.ic_launcher)
		.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

		//sample2
		menu.add(groupId, itemId, order, "sample2")
		.setIcon(R.drawable.ic_launcher)
		.setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT);

		//add group
		SubMenu subMenu = menu.addSubMenu(groupId, itemId, order, "组");
		subMenu.add("组-1").setIcon(R.drawable.ic_launcher);
		subMenu.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		*/
		return super.onCreateOptionsMenu(menu);
	}

	public void onBack() {
		finish();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			//弹出菜单，退出系统
			WidgetUtil.popMenuExit(this, new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (v.getId() == R.id.btn_exit) {
						MessageTip.confirm(BaseActivity.this, "确定退出系统？", new OnTipClickListener() {

							public void onClick(int id) {
								// TODO Auto-generated method stub
								if (id == MessageTip.OK)
									((App) getApplicationContext()).exit();
							}
						});
					}
				}
			});
		}
		return super.onKeyDown(keyCode, event);
	}

}
