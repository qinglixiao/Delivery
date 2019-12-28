package me.std.base.widget.listview.presenter;

import android.content.Context;

import java.util.List;

import me.std.base.widget.listview.contract.CYListViewContract;
import me.std.base.widget.listview.model.OnPagedModelListResultListener;
import me.std.base.widget.listview.model.PagedModel;

/**
 * Created by Roger Huang on 2019/1/11.
 */

public class CYListViewPresenter<V extends CYListViewContract.View> implements CYListViewContract.Presenter<V> {
    private Context mContext;
    public V mView;
    PagedModel mModel;

    public CYListViewPresenter(Context context, V view) {
        mContext = context;
        mView = view;
    }

    protected void setupModel(PagedModel model) {
        mModel = model;

        getModel().setListResultListener(new OnPagedModelListResultListener() {
                                             @Override
                                             public void onFinish(List data, Error error, boolean isRefresh) {
                                                 mView.dismissLoading();

                                                 if (error != null) {
                                                     mView.showError(error.getMessage());
                                                 } else if (data.size() == 0) {
                                                     if (isRefresh) {
                                                         mView.showEmptyView();
                                                     } else {
                                                         mView.showNoMoreData();
                                                     }
                                                 } else {
                                                     mView.showData(data, isRefresh);
                                                 }
                                             }
                                         }
        );
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void loadData(boolean isRefresh) {
        if (!isRefresh && !getModel().hasMore()) {
            mView.showNoMoreData();
        } else {
            mView.showLoading();
            mView.hideError();
            mView.hideEmptyView();
            getModel().load(isRefresh);
        }
    }

    protected PagedModel getModel() {
        return mModel;
    }

    public Context getContext() {
        return mContext;
    }
}
