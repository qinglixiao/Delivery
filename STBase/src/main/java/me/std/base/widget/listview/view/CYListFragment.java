package me.std.base.widget.listview.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import me.std.base.R;
import me.std.base.base.ChunyuFragment;
import me.std.base.base.ChunyuListAdapter;
import me.std.base.widget.listview.contract.CYListViewContract;
import me.std.common.core.ThreadPool;
import me.std.common.third.pulltorefresh.PullToRefreshBase;
import me.std.common.third.pulltorefresh.PullToRefreshListView;
import me.std.common.utils.ToastUtil;


/**
 * Created by Roger Huang on 2019/1/14.
 */

public abstract class CYListFragment<P> extends ChunyuFragment implements CYListViewContract.View<P>, AdapterView.OnItemClickListener {
    public PullToRefreshListView mPullRefreshListView;

    @Override
    protected View onCreateLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        init(view);
        return view;
    }

    protected int getLayoutId() {
        return R.layout.activity_cylist;
    }

    protected void init(View view) {
        mPullRefreshListView = view.findViewById(R.id.pull_refresh_list);

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

    abstract protected ChunyuListAdapter getAdapter();

    abstract protected CYListViewContract.Presenter getPresenter();

    @Override
    public void onRefresh() {
        getPresenter().loadData(true);
    }

    @Override
    public void showData(List data, boolean isRefresh) {
        hideEmptyView();

        if (isRefresh) {
            getAdapter().setData(data);
        } else {
            getAdapter().addAll(data);
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void showNoMoreData() {
        mPullRefreshListView.onRefreshComplete();
        ToastUtil.getInstance().showToast("没有更多数据");
    }

    @Override
    public void dismissLoading() {
        super.dismissLoading();
        mPullRefreshListView.onRefreshComplete();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setPresenter(P presenter) {

    }
}
