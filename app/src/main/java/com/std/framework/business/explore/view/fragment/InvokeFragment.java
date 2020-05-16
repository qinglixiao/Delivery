package com.std.framework.business.explore.view.fragment;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.std.framework.R;
import com.std.framework.business.user.model.UserModel;
import com.std.framework.databinding.FragmentInvokeBinding;
import com.std.framework.study.other.module.ToonModule;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.std.base.base.STFragment;
import me.std.base.core.ActionBar;

/**
 * Description:
 * Created by 李晓 ON 2017/12/23.
 * Job number:137289
 * Phone:18611867932
 * Email:lixiao3@syswin.com
 * Person in charge:李晓
 * Leader: 李晓
 */
public class InvokeFragment extends STFragment implements View.OnClickListener {
    private FragmentInvokeBinding invokeBinding;

    @Override
    protected void onActionBar(ActionBar.Builder builder) {
        builder.setTitle("接口调用");
    }

    @Override
    protected View onCreateLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invoke, null);
        invokeBinding = DataBindingUtil.bind(view);
        viewAction();
        return view;
    }

    private void viewAction() {
        invokeBinding.btnUser.setOnClickListener(this);
        invokeBinding.btnToon.setOnClickListener(this);
        invokeBinding.btnFeed.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == invokeBinding.btnUser.getId()) {
            new UserModel().getTopMovie(0, 10)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<UserModel.MoveEntity>() {
                        /**
                         * Consume the given value.
                         *
                         * @param moveEntity the value
                         * @throws Exception on error
                         */
                        @Override
                        public void accept(UserModel.MoveEntity moveEntity) throws Exception {
                            invokeBinding.tvShow.setText(moveEntity.toString());
                        }
                    });

        } else if (view.getId() == invokeBinding.btnToon.getId()) {
            new ToonModule().getCommonList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Exception {
                            invokeBinding.tvShow.setText(s);
                        }
                    });
        } else if (view.getId() == invokeBinding.btnFeed.getId()) {
            new ToonModule().obtainFeedList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Exception {
                            invokeBinding.tvShow.setText(s);
                        }
                    });
        }
    }
}
