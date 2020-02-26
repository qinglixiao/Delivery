package me.std.base.widget.listview.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import me.std.base.R;
import me.std.base.base.STActivity;
import me.std.base.base.STListAdapter;
import me.std.base.widget.listview.contract.CYListViewContract;
import me.std.common.core.ThreadPool;
import me.std.common.third.pulltorefresh.PullToRefreshBase;
import me.std.common.third.pulltorefresh.PullToRefreshListView;
import me.std.common.utils.ToastUtil;

public class CYListActivity<P> extends STActivity implements CYListViewContract.View<P>, AdapterView.OnItemClickListener {

    PullToRefreshListView mPullRefreshListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        init();
    }

    protected int getLayout() {
        return R.layout.activity_cylist;
    }

    protected void init() {
        mPullRefreshListView = findViewById(R.id.pull_refresh_list);

        // 刷新
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                getPresenter().loadData(true);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                getPresenter().loadData(false);
            }
        });

        mPullRefreshListView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                getPresenter().loadData(false);
            }
        });

        // adapter
        mPullRefreshListView.getRefreshableView().setAdapter(getAdapter());

        mPullRefreshListView.setOnItemClickListener(this);

        // 加载首页数据
        onRefresh();
    }

    protected STListAdapter getAdapter() {
        return null;
    }

    protected CYListViewContract.Presenter getPresenter() {
        return null;
    }

    @Override
    public void onRefresh() {
        getPresenter().loadData(true);
    }

    @Override
    public void showData(List data, boolean isRefresh) {
        hideEmptyView();
        // TODO: hide error?

        if (isRefresh) {
            getAdapter().setData(data);
        } else {
            getAdapter().addAll(data);
        }
    }

    @Override
    public void showNoMoreData() {
        ThreadPool.postDelay(new Runnable() {
            @Override
            public void run() {
                mPullRefreshListView.onRefreshComplete();
            }
        }, 10);

        ToastUtil.getInstance().showToast("没有更多数据");
    }

    @Override
    public void dismissLoading() {
        super.dismissLoading();
        mPullRefreshListView.onRefreshComplete();
    }

    @Override
    public void setPresenter(P presenter) {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }
}
