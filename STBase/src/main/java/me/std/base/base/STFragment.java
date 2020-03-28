package me.std.base.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.nio.ByteBuffer;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import me.std.base.R;
import me.std.base.core.ActionBar;
import me.std.base.interfaces.IActionBarRebuild;
import me.std.base.interfaces.IBaseOperation;
import me.std.base.view.EmptyView;
import me.std.base.view.dialog.LoadingDialog;
import me.std.common.utils.Logger;
import me.std.common.utils.ViewUtil;

/**
 * Description:带导航栏管理功能，loading框提醒...
 * Author: lixiao
 * Create on: 2018/11/20.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public abstract class STFragment extends BaseFragment implements IBaseOperation {
    private ActionBar.Builder actionBuilder;
    public ActionBar actionBar;
        private boolean isFragmentVisiable;
    private Unbinder unBinderKnife;
    private ViewGroup mWrapView;
    private View mContentView;
    public EmptyView mEmptyView;//空页面
    /**
     * 数据加载等待框
     */
    public LoadingDialog mLoadingDialog;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isFragmentVisiable = isVisibleToUser;
        if (isVisibleToUser) {
            attachNavigation();
        }
//        super.setUserVisibleHint(isVisibleToUser);
        Logger.d(getClass().getName()+ " visible="+isVisibleToUser);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachNavigation();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            attachNavigation();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mWrapView = (ViewGroup) inflater.inflate(R.layout.base_fragment_view, null);
        mEmptyView = mWrapView.findViewById(R.id.v_base_empty);
        mContentView = onCreateLayout(inflater, container, savedInstanceState);
        mWrapView.addView(mContentView);
        unBinderKnife = ButterKnife.bind(this, mWrapView);
        setEmptyViewClick();
        onCompleteLayout();
        return mWrapView;
    }

    private void attachNavigation() {
        if (getActivity() != null && getActivity() instanceof IActionBarRebuild) {
            actionBuilder = ((IActionBarRebuild) getActivity()).getBuilder();
            actionBuilder.build().reset();
            onActionBar(actionBuilder);
        }
    }

    /**
     * 设置空页面点击事件，该事件主要用于当页面展示空页面时，点击空页面后重新加载数据
     */
    public void setEmptyViewClick() {
        mEmptyView.setOnViewClick(new EmptyView.OnEmptyViewClick() {
            @Override
            public void onClick(View view) {
                onRefresh();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unBinderKnife != null) {
            unBinderKnife.unbind();
        }
    }

    protected void onCompleteLayout() {
    }

    /**
     * 定义标题栏
     * 注：只要当前Fragment可见状态时才会调用
     *
     * @param builder
     */
    protected void onActionBar(ActionBar.Builder builder) {
    }

    /**
     * 子类通过重写此方法创建自己的View
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    protected abstract View onCreateLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    /**
     * 显示加载提示框
     * 默认可取消
     */
    @Override
    public void showLoading() {
        showLoading(true);
    }

    /**
     * 显示加载提示框
     *
     * @param cancelable 是否可取消
     */
    public void showLoading(boolean cancelable) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(getActivity());
            mLoadingDialog.setCancelable(cancelable);
            mLoadingDialog.setCanceledOnTouchOutside(cancelable);
        }
        mLoadingDialog.show();
    }

    /**
     * 关闭加载框
     */
    public void dismissLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    /**
     * 显示空页面
     *
     * @return
     */
    public void showEmptyView() {
        _showEmptyView();
    }

    private void _showEmptyView() {
        ViewUtil.visible(mEmptyView);
        ViewUtil.gone(mContentView);
    }

    /**
     * 显示空页面
     *
     * @param hint 提示文案
     */
    @Override
    public void showEmptyView(String hint) {
        _showEmptyView();

        if (mEmptyView != null) {
            mEmptyView.showHint(hint);
        }
    }

    /**
     * 错误信息展示
     */
    @Override
    public void showError() {
        //目前错误信息展示跟空页面长的一样，后期有业务需求再调整
        showEmptyView();
    }

    /**
     * 错误信息展示
     *
     * @param error
     */
    @Override
    public void showError(String error) {
        //目前错误信息展示跟空页面长的一样，后期有业务需求再调整
        showEmptyView(error);
    }

    /**
     * 隐藏错误信息提示
     */
    @Override
    public void hideError() {
        //目前错误信息展示跟空页面长的一样，后期有业务需求再调整
        hideEmptyView();
    }

    /**
     * 隐藏空页面
     */
    @Override
    public void hideEmptyView() {
        ViewUtil.gone(mEmptyView);
        ViewUtil.visible(mContentView);
    }

    /**
     * 空页面点击后会调用此方法进行数据加载
     */
    @Override
    public void onRefresh() {

    }

}
