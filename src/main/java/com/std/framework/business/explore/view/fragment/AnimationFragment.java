package com.std.framework.business.explore.view.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ArgbEvaluator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.std.framework.R;
import com.std.framework.basic.BaseFragment;
import com.std.framework.basic.IBasePresenter;
import com.std.framework.business.home.contract.RxBusContract;
import com.std.framework.core.NavigationBar;
import com.std.framework.core.RxBus;
import com.std.framework.databinding.FragmentAnimationBinding;
import com.std.framework.databinding.FragmentRxbusBinding;

import rx.Observable;
import rx.Subscriber;

/**
 * Description :
 * Created by lx on 2016/9/1.
 * Job number：137289
 * Phone ：18611867932
 * Email：lixiao3@syswin.com
 * Person in charge : lx
 * Leader：lx
 */
public class AnimationFragment extends BaseFragment  implements View.OnClickListener {
    private FragmentAnimationBinding fragmentAnimationBinding;
    private View view;

    @Override
    public void onNavigationBar(NavigationBar navigationBar) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        view = inflater.inflate(R.layout.fragment_animation, null);
        fragmentAnimationBinding = DataBindingUtil.bind(view);
        setListener();
        startRotate();
        return view;
    }

    private void setListener() {
        fragmentAnimationBinding.frag4BtnAdd.setOnClickListener(this);
        fragmentAnimationBinding.frag4BtnAfter.setOnClickListener(this);
        fragmentAnimationBinding.frag4BtnColor.setOnClickListener(this);
        fragmentAnimationBinding.frag4BtnObj.setOnClickListener(this);
        fragmentAnimationBinding.frag4BtnPro.setOnClickListener(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.frag4_btn_obj:
                objAnima(fragmentAnimationBinding.frag4Target);
                break;
            case R.id.frag4_btn_pro:
                proAnima(fragmentAnimationBinding.frag4Target);
                break;
            case R.id.frag4_btn_add:
                addView();
                break;
            case R.id.frag4_btn_color:
                colorTrans(fragmentAnimationBinding.frag4Color);
                break;
            case R.id.frag4_btn_after:
                afterAnim(fragmentAnimationBinding.frag4Target);
                break;
        }
    }

    private void afterAnim(View view) {
        ObjectAnimator animator_x = ObjectAnimator.ofFloat(view, "scaleX", 2);
        ObjectAnimator animator_y = ObjectAnimator.ofFloat(view, "scaleY", 2);
        animator_x.setRepeatMode(ValueAnimator.REVERSE);
        animator_y.setRepeatMode(ValueAnimator.REVERSE);
        AnimatorSet animator = new AnimatorSet();
        animator.setDuration(300);
        animator.setInterpolator(new AnticipateOvershootInterpolator());
        animator.play(animator_y).after(animator_x);
        animator.start();
    }

    //颜色过渡动画
    private void colorTrans(View view) {
        ValueAnimator colorAnim = ObjectAnimator.ofInt(view, "backgroundColor", Color.RED, Color.BLUE);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();
    }

    //多对象组合动画
    private void objAnima(View view) {
        view.setLayerType(View.LAYER_TYPE_NONE, null);
        ObjectAnimator animator_x = ObjectAnimator.ofFloat(view, "scaleX", view.getScaleX() * 2);
        ObjectAnimator animator_y = ObjectAnimator.ofFloat(view, "scaleY", view.getScaleY() * 2);
        animator_x.setRepeatCount(1);
        animator_x.setRepeatMode(ValueAnimator.REVERSE);
        animator_y.setRepeatCount(1);
        animator_y.setRepeatMode(ValueAnimator.REVERSE);
        AnimatorSet animator = new AnimatorSet();
        animator.setDuration(300);
        animator.setInterpolator(new AnticipateOvershootInterpolator());
        //animator.playSequentially(animator_x, animator_y);
        animator.playTogether(animator_x, animator_y);
        animator.start();
    }

    //多属性组合动画
    private void proAnima(View view) {
        ViewPropertyAnimator.animate(view).scaleX(2).scaleY(2).start();
    }

    private void startRotate() {
        RotateAnimation animation = new RotateAnimation(0, 720, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(3000);
        animation.setFillAfter(true);
        animation.setRepeatMode(Animation.RESTART);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setInterpolator(new LinearInterpolator());
        fragmentAnimationBinding.circle.startAnimation(animation);
    }

    //向窗口添加元素
    private void addView() {
        WindowManager windowManager = getActivity().getWindowManager();
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        ImageView imageView = new ImageView(getActivity());
        imageView.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.circle_shape));
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;

        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        imageView.setLayoutParams(new android.view.ViewGroup.LayoutParams(60, 60));
        windowManager.addView(imageView, params);
    }


    /**
     * Description :
     * Created by lx on 2016/7/13.
     * Job number：137289
     * Phone ：18611867932
     * Email：lixiao3@syswin.com
     * Person in charge : lx
     * Leader：lx
     */
    public static class RxBusFragment extends BaseFragment implements RxBusContract.View,View.OnClickListener{
        private FragmentRxbusBinding fragmentRxbusBinding;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_rxbus, null);
            fragmentRxbusBinding = DataBindingUtil.bind(view);
            setListener();
            return view;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Observable<String> observable = RxBus.getDefault().toObservable(String.class);
    //        observable.subscribe(new Action1<String>() {
    //            @Override
    //            public void call(String s) {
    //                fragmentRxbusBinding.tvShow.setText(s);
    //            }
    //        });
            observable.subscribe(subscriber);
        }

        @Override
        public void onNavigationBar(NavigationBar navigationBar){
        }

        private void setListener(){
            fragmentRxbusBinding.btnSend.setOnClickListener(this);
        }

        @Override
        public void setPresenter(IBasePresenter presenter) {
        }

        @Override
        public void onClick(View v) {
            RxBus.getDefault().post(fragmentRxbusBinding.edtMessage.getText().toString());
        }

        private Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                fragmentRxbusBinding.tvShow.setText(s);
            }
        };
    }
}
