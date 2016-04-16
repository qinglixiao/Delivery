package com.std.framework.widget.pullrefreshview.demo;

import com.std.framework.widget.pullrefreshview.PullRefreshView;
import com.std.framework.widget.pullrefreshview.PullRefreshView.OnFooterRefreshListener;
import com.std.framework.widget.pullrefreshview.PullRefreshView.OnHeaderRefreshListener;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.Time;
import android.widget.ListView;

/**LRefreshView上拉，下拉刷新控件功能演示
 * 
 * gridView,ScrollView 使用方法类似
 * @author Administrator
 *
 */
class ListViewRefreshDemo extends Activity implements OnHeaderRefreshListener,OnFooterRefreshListener{
	private ListView mListView;
	private PullRefreshView refreshView ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.listviewrefreshdemo);
//		mListView = (ListView) findViewById(R.id.lst);
//		refreshView = (LRefreshView) findViewById(R.id.main_pull_refresh_view);
//		refreshView.setOnFooterRefreshListener(this);
//		refreshView.setOnHeaderRefreshListener(this);
//		DataAdapter dataAdapter = new DataAdapter(this);
//		mListView.setAdapter(dataAdapter);
	}

	/**
	 * 上拉刷新
	 */
	@Override
	public void onFooterRefresh(PullRefreshView view) {
		/**
		 *  此处执行上拉刷新用户处理任务
		 *  1.
		 *  2.
		 *  。。。。。。。。。。。
		 */
		refreshView.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				//任务完成结束刷新
				refreshView.finishFooterRefresh();
			}
		}, 500);
	}

	/**
	 * 下拉刷新
	 */
	@Override
	public void onHeaderRefresh(PullRefreshView view) {
		/**
		 *  此处执行下拉刷新用户处理任务
		 *  1.
		 *  2.
		 *  。。。。。。。。。。。
		 */
		refreshView.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Time time = new Time();
				time.setToNow();
				//任务完成结束刷新
				refreshView.finishHeaderRefresh("最后更新于" + time.hour +":" + time.minute);
			}
		}, 500);
	}
	
}
