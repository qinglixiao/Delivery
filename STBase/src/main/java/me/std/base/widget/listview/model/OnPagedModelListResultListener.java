package me.std.base.widget.listview.model;

import java.util.List;

/**
 * Created by Roger Huang on 2018/12/5.
 */

public interface OnPagedModelListResultListener<T> {
    /*
     * @param doctorList is non-null if error is null
     * @param error is non-null if doctorList is null
     * @param isRefresh
     */
    void onFinish(List<T> data, Error error, boolean isRefresh);
}
