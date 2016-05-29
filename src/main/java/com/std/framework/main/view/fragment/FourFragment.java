package com.std.framework.main.view.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ArgbEvaluator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.std.framework.R;
import com.std.framework.databinding.FragmentFourBinding;
import com.std.framework.fragment.BaseFragment;

public class FourFragment extends BaseFragment implements OnClickListener {
    private View view;
    private FragmentFourBinding fragmentFourBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        view = inflater.inflate(R.layout.fragment_four, null);
        fragmentFourBinding = DataBindingUtil.bind(view);
        setListener();
        startRotate();
        return view;
    }

    private void setListener(){
        fragmentFourBinding.frag4BtnAdd.setOnClickListener(this);
        fragmentFourBinding.frag4BtnAfter.setOnClickListener(this);
        fragmentFourBinding.frag4BtnColor.setOnClickListener(this);
        fragmentFourBinding.frag4BtnObj.setOnClickListener(this);
        fragmentFourBinding.frag4BtnPro.setOnClickListener(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.frag4_btn_obj:
                objAnima(fragmentFourBinding.frag4Target);
                break;
            case R.id.frag4_btn_pro:
                proAnima(fragmentFourBinding.frag4Target);
                break;
            case R.id.frag4_btn_add:
                addView();
                break;
            case R.id.frag4_btn_color:
                colorTrans(fragmentFourBinding.frag4Color);
                break;
            case R.id.frag4_btn_after:
                afterAnim(fragmentFourBinding.frag4Target);
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
       fragmentFourBinding.circle.startAnimation(animation);
    }

    //向窗口添加元素
    private void addView() {
        WindowManager windowManager = getActivity().getWindowManager();
        LayoutParams params = new LayoutParams();
        ImageView imageView = new ImageView(getActivity());
        imageView.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.circle_shape));
        params.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
                | LayoutParams.FLAG_NOT_FOCUSABLE
                | LayoutParams.FLAG_NOT_TOUCHABLE;

        params.width = LayoutParams.WRAP_CONTENT;
        params.height = LayoutParams.WRAP_CONTENT;
        imageView.setLayoutParams(new android.view.ViewGroup.LayoutParams(60, 60));
        windowManager.addView(imageView, params);
    }
}
