package me.std.base.widget.listview.contract;

import java.util.List;

import me.std.base.contract.IBaseFeedBackView;
import me.std.base.contract.IBasePresenter;

/**
 * Created by Roger Huang on 2019/1/11.
 */

public class CYListViewContract {
    public interface View<T> extends IBaseFeedBackView<T> {
        void showData(List data, boolean isRefresh);

        void showNoMoreData();
    }

    public interface Presenter<T> extends IBasePresenter<T> {
        void loadData(boolean isRefresh);
    }
}
