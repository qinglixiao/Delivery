package me.std.base.widget.listview.model;

import com.google.gson.Gson;
import com.std.network.IServerResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.Log;

/**
 * Created by Roger Huang on 2018/12/5.
 */

/*
 * @class PagedModel
 * 1. 提供分页网络请求功能
 * 2. 提供列表管理
 * @param R 网络请求的结果类型
 * @param T 列表数据元素类型
 */
public abstract class PagedModel<R extends IServerResult, T> {
    // api url
    private String mUrl;

    // api 参数
    private Map<String, Object> mParameters;

    // 是否还有更多数据
    private boolean mHasMore = true;

    // 结果数据对象类型
    private Class<R> mResultClazz;

    private Context mContext;

    // 页面控制
    public Integer mCurrentPage = 0;
    private Integer mPageSize = 20;
    private Integer mInitialPage = 1;

    public String pageName = "page";
    public String pageSizeName = "pageSize";

    // 是否正在加载
    private boolean isLoading = false;

    // 列表数据listener
    private OnPagedModelListResultListener<T> mListResultListener;

    public PagedModel(Context context, String url, Class<R> clazz) {
        mUrl = url;
        mResultClazz = clazz;
        mContext = context;
    }

    // 重载该方法，返回一个能请求数据的Request
    abstract protected IModelRequest makeRequest(Context context, String url, Map<String, String> parameters);

    /*
     * @desc 加载数据
     */
    public boolean load(final boolean isRefresh) {
        if (isLoading) return false;

        isLoading = true;

        final int requestPage = isRefresh ? mInitialPage : mCurrentPage + 1;

        try {
            IModelRequest request = makeRequest(mContext, mUrl, buildParameters(requestPage));
            request.start(new IModelRequestCallback() {
                @Override
                public void onResult(String result, Error error) {
                    didFinish(result, error, isRefresh, requestPage);
                }
            });
        }
        catch (Exception ex) {
            didFinish(null, new Error(ex), isRefresh, requestPage);
        }

        return isLoading;
    }

    /*
     * @desc 更新加载状态
     */
    protected void didFinish(Object data, Error error, boolean isRefresh, int page) {
        if (onLoadFinish(data, error, isRefresh)) {
            mCurrentPage = page;
        }

        isLoading = false;
    }

    /*
     * @desc 网络请求结束后调用这个函数
     * @param data 网络请求的结果（JSON数据）
     * @param exception 网络请求出错
     * @param isRefresh 是刷新还是加载更多
     */
    protected boolean onLoadFinish(Object data, Error error, boolean isRefresh) {
        if (data != null) {
            try {
                R result = parseResult(data);

                if (result != null) {
                    if (result.getErrorMessage() != null && result.getErrorMessage().length() > 0) {
                        error = new Error(result.getErrorMessage());
                    } else {
                        onResult(result, null, isRefresh);
                        return true;
                    }
                } else {
                    error = new Error("无法解析服务器数据!");
                }
            } catch (Exception ex) {
                error = new Error("解析错误");
            }
        }

        if (error == null) {
            error = new Error("未知错误");
        }

        onResult(null, error, isRefresh);

        return false;
    }

    /*
     * @desc 默认的结果解析方法，子类可重写
     */
    protected R parseResult(Object object) {
        if (mResultClazz != null) {
            return new Gson().fromJson(object.toString(), mResultClazz);
        }
        return null;
    }

    /*
     * @desc 子类中需要重写该方法，获取result中的列表数据
     */
    protected List<T> parseListResult(R result) {
        return null;
    }

    /*
     * @desc 转换完数据后，调用这个方法
     *       默认实现中，例用了PagedModel提供的列表管理功能，
     *       子类可以重写该方法
     * @param result is non-null if error is null
     * @param error is non-null if result is null
     * @param isRefresh
     */
    protected void onResult(R result, Error error, boolean isRefresh) {
        List<T> l = parseListResult(result);

        if (l == null && error == null) {
            error = new Error("解析列表数据出错");
        }

        if (error == null) {
            // 1. 加载成功才更新
            setHasMore(l.size() >= getPageSize());
        }

        if (mListResultListener != null) {
            mListResultListener.onFinish(l, error, isRefresh);
        }
    }

    /*
     * @desc 添加参数
     */
    public void addParameter(String name, Object value) {
        if (name == null || value == null) return;

        if (mParameters == null) {
            mParameters = new HashMap<>();
        }
        mParameters.put(name, value);
    }

    /*
     * @desc 设置参数
     */
    public void setParameters(Map<String, Object> parameters) {
        mParameters = parameters;
    }

    /*
     * @desc 设置listener
     */
    public void setListResultListener(OnPagedModelListResultListener listener) {
        mListResultListener = listener;
    }

    /*
     * @desc 设置事否还有更多
     *       如果使用PagedModel提供的列表管理功能，不需要调用setHasMore
     *       如果自己管理，需要调用setHasMore以保证逻辑正常
     */
    public void setHasMore(boolean hasMore) { mHasMore = hasMore; }
    public boolean hasMore() { return mHasMore; }

    /*
     * @desc page参数
     */
    public Integer getPage() {
        return mCurrentPage;
    }

    public Integer getPageSize() {
        return mPageSize;
    }

    public void setPageSize(int pageSize) {
        mPageSize = pageSize;
    }

    public void setInitialPage(int page) {
        mInitialPage = page;
    }

    private Map<String, String> buildParameters(int page) {
        Map<String, String> s = new HashMap<>();

        s.put(pageName, "" + page);
        s.put(pageSizeName, "" + mPageSize);

        if (mParameters != null) {
            for (String k : mParameters.keySet()) {
                s.put(k, mParameters.get(k).toString());
            }
        }

        Log.d(PagedModel.class.toString(), "buildParameters: " + s.toString());

        return s;
    }
}
