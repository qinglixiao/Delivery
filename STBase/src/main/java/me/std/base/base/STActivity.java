package me.std.base.base;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.std.base.R;
import me.std.base.core.ActionBar;
import me.std.base.interfaces.IActionBarRebuild;
import me.std.base.interfaces.IBaseOperation;
import me.std.base.view.EmptyView;
import me.std.base.view.dialog.LoadingDialog;
import me.std.common.utils.ViewUtil;

/**
 * Description: 提供导航栏管理功能，loading框提醒...
 * Author: lixiao
 * Create on: 2018/11/19.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public abstract class STActivity extends BaseActivity implements IActionBarRebuild, IBaseOperation {
    private ActionBar.Builder actionBuilder;
    public ActionBar actionBar;
    private Unbinder unBinderKnife;
    private EmptyView mEmptyView;
    private View mContentView;
    /**
     * 数据加载等待框
     */
    public LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(getRootLayout());
        if (getSupportActionBar() != null) {//隐藏系统ActionBar
            getSupportActionBar().hide();
        }
        initActionBar();
        parseIntent();
        onActionBar(actionBuilder);
    }

    protected int getRootLayout() {
        return R.layout.root_layout;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        setContentView(View.inflate(this, layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        ViewGroup container = findViewById(R.id.parent);
        if (container == null) {
            throw new NullPointerException(getClass().getSimpleName() + ":container is null");
        }

        container.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        setup(view);
    }

    protected void setup(View contentView) {
        ViewGroup container = findViewById(R.id.parent);

        mEmptyView = container.findViewById(R.id.v_base_empty);
        mContentView = contentView;
        unBinderKnife = ButterKnife.bind(this, contentView);
        setEmptyViewClick();
    }

    public EmptyView getEmptyView() {
        return mEmptyView;
    }

    private void initActionBar() {
        actionBuilder = new ActionBar.Builder(this);
        actionBar = actionBuilder.build();
        View defBar = createActionBar();//获取用户自定义导航栏
        if (defBar == null) {
            //如果用户没有自定义导航栏则使用默认的
            defBar = actionBar.getHeader();
        }
        ViewGroup headContainer = findViewById(R.id.v_app_navigation);
        if (headContainer != null) {
            headContainer.addView(defBar);
        }

        actionBar.getBackButton().setOnClickListener(onNavBarClick);
        actionBar.getCloseButton().setOnClickListener(onNavBarClick);
    }

    private View.OnClickListener onNavBarClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == actionBar.getBackButton()
                    || v == actionBar.getCloseButton()) {//返回
                onBackPressed();
                return;
            }
        }
    };

    /**
     * 自定义标题栏，重写此方法，onActionBar将不再起作用
     *
     * @return
     */
    protected View createActionBar() {
        return null;
    }

    @Override
    public ActionBar.Builder getBuilder() {
        return actionBuilder;
    }

    /**
     * 定义标题栏
     * 每个页面都会调用此方法，并且在此方法内进行标题栏的设定
     *
     * @param builder
     */
    protected void onActionBar(ActionBar.Builder builder) {
    }

    protected void parseIntent() {
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
            mLoadingDialog = new LoadingDialog(this);
            mLoadingDialog.setCancelable(cancelable);
            mLoadingDialog.setCanceledOnTouchOutside(cancelable);
        }
        if (!mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
        }
    }

    /**
     * 关闭加载框
     */
    public void dismissLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    protected void _showEmptyView() {
        ViewUtil.visible(mEmptyView);
        ViewUtil.gone(mContentView);
    }

    /**
     * 显示空页面
     *
     * @return
     */
    public void showEmptyView() {
        _showEmptyView();
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
     * 隐藏空页面
     */
    @Override
    public void hideEmptyView() {
        ViewUtil.gone(mEmptyView);
        ViewUtil.visible(mContentView);
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
     * 空页面点击后会调用此方法进行数据加载
     */
    public void onRefresh() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unBinderKnife != null) {
            unBinderKnife.unbind();
        }
    }
}
