package com.std.framework.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.LayoutParams;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;
import com.std.framework.App;
import com.std.framework.R;
import com.std.framework.comm.STDActivityManager;
import com.std.framework.comm.STDFragmentManager;
import com.std.framework.fragment.MenuLeftFragment;
import com.std.framework.fragment.MenuRightFragment;
import com.std.framework.interfaces.IBackUp;
import com.std.framework.interfaces.IBaseActivity;
import com.std.framework.interfaces.OnTipClickListener;
import com.std.framework.util.WidgetUtil;
import com.std.framework.widget.dialog.MessageTip;

/**
 * 
 * 描 述 ：具有滑动菜单功能的activity基类,继承该类可使activity具有可滑动菜单功能
 * 创建日期 ： 2014-4-2
 * 作 者 ： lx
 * 修改日期 ：
 * 修 改 者 ：
 * 
 * @version： 1.0
 */
public class SlideBaseActivity extends SlidingFragmentActivity implements IBaseActivity, IBackUp {
	/**左侧菜单*/
	public static final int MENU_LEFT = 1;
	/**右侧菜单*/
	public static final int MENU_RIGHT = 2;
	/**无菜单*/
	public static final int MENU_NONE = 4;
	public final STDFragmentManager FragmentManager;
	private SlidingMenu sm;
	/** 工作线程 */
	public HandlerThread workThread;
	public Handler workhandler;
	/**标题*/
	private View mTitleView;
	/**标题是否居中显示*/
	private boolean isTitleCenter = false;
	private int mMenuMode = MENU_LEFT;

	public SlideBaseActivity() {
		FragmentManager = STDFragmentManager.getInstance(this);
		workThread = new HandlerThread("workthread");
		workThread.start();
		workhandler = new Handler(workThread.getLooper());

		/**添加activity到栈*/
		STDActivityManager.getInstance().add(this);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		workhandler.post(work);
		setBehindContentView(new View(this));
		initMenu();
		setActionBar();
	}
	
	private void setActionBar(){
		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_USE_LOGO|ActionBar.DISPLAY_SHOW_TITLE);
		ImageView imageView = (ImageView) findViewById(android.R.id.home);
		imageView.setLayoutParams(new android.widget.FrameLayout.LayoutParams(android.widget.FrameLayout.LayoutParams.WRAP_CONTENT,
				android.widget.FrameLayout.LayoutParams.MATCH_PARENT));
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

	public void requestMenuAppear(int mode) {
		mMenuMode = mode;
	}

	/**
	 * 
	 * 描 述 ：初始化菜单
	 * 创建日期 ： 2014-4-2
	 * 作 者 ： lx
	 * 修改日期 ：
	 * 修 改 者 ：
	 * 
	 * @version： 1.0
	 * 
	 */
	private void initMenu() {
		switch (mMenuMode) {
			case MENU_LEFT:
				getSlidingMenu().setMode(SlidingMenu.LEFT);
				setLeftSideMenu();
				break;
			case MENU_RIGHT:
				getSlidingMenu().setMode(SlidingMenu.RIGHT);
				setRightSideMenu();
				break;
			case MENU_RIGHT | MENU_LEFT:
				getSlidingMenu().setMode(SlidingMenu.LEFT_RIGHT);
				setLeftSideMenu();
				setRightSideMenu();
				break;
			case MENU_NONE:
				getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
				break;
		}

	}

	protected void setLeftSideMenu() {
		sm = getSlidingMenu();
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		sm.setMenu(R.layout.menu_left);

		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.replace(R.id.menu_content, new MenuLeftFragment());
		transaction.commit();

		// 设置左侧菜单属性
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
	}

	protected void setRightSideMenu() {
		sm = getSlidingMenu();
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		sm.setSecondaryMenu(R.layout.menu_right);

		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.replace(R.id.menu_content, new MenuRightFragment());
		transaction.commit();

		// 设置右侧菜单属性
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setSecondaryShadowDrawable(R.drawable.shadowright);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
			case android.R.id.home:
				onBack();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 
	 * 描 述 ：打开/关闭菜单
	 * 创建日期 ： 2014-4-2
	 * 作 者 ： lx
	 * 修改日期 ：
	 * 修 改 者 ：
	 * 
	 * @version：1.0
	 * 
	 */
	public void toggle() {
		sm.toggle();
	}

	@Override
	public void AsynLoadData(Intent intent) {
		// TODO Auto-generated method stub

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
						MessageTip.confirm(SlideBaseActivity.this, "确定退出系统？", new OnTipClickListener() {

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
