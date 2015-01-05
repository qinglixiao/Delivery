package com.std.framework.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.LayoutParams;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.Window;
import com.std.framework.App;
import com.std.framework.R;
import com.std.framework.comm.STDActivityManager;
import com.std.framework.comm.STDFragmentManager;
import com.std.framework.interfaces.IBackUp;
import com.std.framework.interfaces.IBaseActivity;
import com.std.framework.interfaces.OnTipClickListener;
import com.std.framework.util.WidgetUtil;
import com.std.framework.widget.dialog.MessageTip;

public class BaseActivity extends SherlockFragmentActivity implements IBaseActivity, IBackUp {
	/**日志记录器*/
	public Logger Loger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	/**视图管理器*/
	public final STDFragmentManager FragmentManager;
	/**工作线程*/
	public HandlerThread workThread;
	public Handler workhandler;
	/**标题*/
	private View mTitleView;
	/**标题是否居中显示*/
	private boolean isTitleCenter = false;

	public BaseActivity() {
		FragmentManager = STDFragmentManager.getInstance(this);
		workThread = new HandlerThread("workthread");
		workThread.start();
		workhandler = new Handler(workThread.getLooper());

		/**添加activity到栈*/
		STDActivityManager.getInstance().add(this);
	}

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setActionBar();
	}

	private void setActionBar() {
		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_USE_LOGO | ActionBar.DISPLAY_SHOW_TITLE);
//		ImageView imageView = (ImageView) findViewById(android.R.id.home);
//		imageView.setLayoutParams(new android.widget.FrameLayout.LayoutParams(android.widget.FrameLayout.LayoutParams.WRAP_CONTENT,
//				android.widget.FrameLayout.LayoutParams.MATCH_PARENT));
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		workhandler.post(work);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		/**销毁栈信息*/
		STDActivityManager.getInstance().remove(this);
	}

	private Runnable work = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			AsynLoadData(getIntent());
		}
	};

	public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem item) {
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

	/**
	 * 
	 * 描      述 ：工作在非UI线程中的异步方法
	 * 创建日期 ： 2014-4-10
	 * 作      者 ： lx
	 * 修改日期 ： 
	 * 修  改 者 ：
	 * @version：1.0
	 * @param intent
	 *
	 */
	@Override
	public void AsynLoadData(Intent intent) {
		// TODO Auto-generated method stub
	}

	/**
	 * 
	 * 描      述 ：获取日志记录器
	 * 创建日期 ： 2014-4-22
	 * 作      者 ： lx
	 * 修改日期 ： 
	 * 修  改 者 ：
	 * @version： 1.0
	 * @return
	 *
	 */
	public Logger getLoger() {
		return Loger;
	}

	@Override
	public void displayActionBar(boolean isShow) {
		// TODO Auto-generated method stub
		if (isShow)
			getSupportActionBar().show();
		else
			getSupportActionBar().hide();
	}

	@Override
	public void displayActionBarCustomLayout(boolean isShow) {
		// TODO Auto-generated method stub
		getSupportActionBar().setDisplayShowCustomEnabled(isShow);
	}

	@Override
	public void displayHomeIcon(boolean isShow) {
		// TODO Auto-generated method stub
		getSupportActionBar().setDisplayShowHomeEnabled(isShow);
	}

	@Override
	public void displayTitle(boolean isShow) {
		// TODO Auto-generated method stub
		getSupportActionBar().setDisplayShowTitleEnabled(isShow);
	}

	@Override
	public void displayHomeAsUp(boolean isShow) {
		// TODO Auto-generated method stub
		getSupportActionBar().setDisplayHomeAsUpEnabled(isShow);
	}

	@Override
	public void displayProgress(boolean isShow) {
		// TODO Auto-generated method stub
		setSupportProgressBarVisibility(isShow);
	}

	@Override
	public void displayIndeterminateProgress(boolean isShow) {
		// TODO Auto-generated method stub
		setSupportProgress(Window.PROGRESS_END);
		setSupportProgressBarIndeterminateVisibility(isShow);
	}

	@Override
	public void displayUseLogo(boolean enable) {
		// TODO Auto-generated method stub
		getSupportActionBar().setDisplayUseLogoEnabled(enable);
	}

	@Override
	public void setSubTitle(CharSequence subTitle) {
		// TODO Auto-generated method stub
		getSupportActionBar().setSubtitle(subTitle);
	}

	@Override
	public void setActionBarCustomView(View view) {
		// TODO Auto-generated method stub
		getSupportActionBar().setCustomView(view);
	}

	@Override
	public void setActionBarCustomView(int layoutId) {
		// TODO Auto-generated method stub
		getSupportActionBar().setCustomView(layoutId);
	}

	@Override
	public void setActionBarCustomView(View view, LayoutParams params) {
		// TODO Auto-generated method stub
		getSupportActionBar().setCustomView(view, params);
	}

	@Override
	public void setHomeButtonEnabled(boolean enable) {
		// TODO Auto-generated method stub
		getSupportActionBar().setHomeButtonEnabled(enable);
	}

	@Override
	public void onBack() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTitle(CharSequence title) {
		// TODO Auto-generated method stub
		if (isTitleCenter)
			((TextView) mTitleView.findViewById(R.id.tv_title_custom)).setText(title);
		else
			super.setTitle(title);
	}

	@Override
	public void requestWindowTitleCenter(boolean isCenter) {
		// TODO Auto-generated method stub
		isTitleCenter = isCenter;
		if (isCenter) {
			if (mTitleView == null) {
				mTitleView = getLayoutInflater().inflate(R.layout.custom_title, null);
			}
			LayoutParams params = new LayoutParams(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
			setActionBarCustomView(mTitleView, params);
			displayActionBarCustomLayout(true);
			displayTitle(false);
		}
		else {
			displayActionBarCustomLayout(false);
			displayTitle(true);
		}
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
