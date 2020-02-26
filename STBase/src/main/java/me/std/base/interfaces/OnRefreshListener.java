package me.std.base.interfaces;

import me.std.base.base.STListAdapter;
import me.std.common.third.pulltorefresh.PullToRefreshBase;

/**
 * Description:
 * Author: lixiao
 * Create on: 2019/1/16.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public interface OnRefreshListener {
    void onPullDownToRefresh(PullToRefreshBase refreshView, STListAdapter adapter);

    void onPullUpToRefresh(PullToRefreshBase refreshView, STListAdapter adapter);
}
