package com.std.framework.business.explore.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

import com.std.framework.R;
import com.std.framework.basic.BaseFragment;
import com.std.framework.business.user.model.UserModel;
import com.std.framework.comm.view.ProgressWheel;
import com.std.framework.core.NavigationBar;
import com.std.framework.databinding.FragmentInvokeBinding;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Description:
 * Created by 李晓 ON 2017/12/23.
 * Job number:137289
 * Phone:18611867932
 * Email:lixiao3@syswin.com
 * Person in charge:李晓
 * Leader: 李晓
 */
public class InvokeFragment extends BaseFragment implements View.OnClickListener {
    private FragmentInvokeBinding invokeBinding;

    @Override
    public void onNavigationBar(NavigationBar navigationBar) {
        navigationBar.setTitle("接口调用");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invoke, null);
        invokeBinding = DataBindingUtil.bind(view);
        viewAction();
        return view;
    }

    private void viewAction() {
        invokeBinding.btnUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == invokeBinding.btnUser.getId()) {
//            new UserModel().getTopMovieString(0, 10)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Action1<UserModel.MoveEntity>() {
//                        @Override
//                        public void call(UserModel.MoveEntity moveEntity) {
//                            invokeBinding.tvShow.setText(moveEntity.toString());
//                        }
//                    });
            new UserModel().getTopMovie(0, 10)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<UserModel.MoveEntity>() {
                        @Override
                        public void call(UserModel.MoveEntity moveEntity) {
                            invokeBinding.tvShow.setText(moveEntity.toString());
                        }
                    });

        }
    }
}
