
package com.std.framework.business.explore.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.std.framework.R;
import com.std.framework.databinding.FragmentAnimation2Binding;
import com.std.framework.util.DimenUtil;

import me.std.base.base.BaseFragment;

/**
 * Description :
 * Author:       lx
 * Create on:  2016/11/13.
 * Modify by：lx
 */
public class AnimationFragment2 extends BaseFragment implements View.OnClickListener {
    private FragmentAnimation2Binding animation2Binding;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_animation2, null);
        animation2Binding = DataBindingUtil.bind(view);
        setListener();
        return view;
    }

    private void setListener() {
        animation2Binding.llAdd.setOnClickListener(this);
        animation2Binding.btnClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_add:
                performAnima();
                break;
            case R.id.btn_close:
                performGuideAnima();
                break;
        }
    }

    private void performGuideAnima() {
        ObjectAnimator animator_x = ObjectAnimator.ofFloat(animation2Binding.imgHand, "scaleX", 1.2f);
        ObjectAnimator animator_y = ObjectAnimator.ofFloat(animation2Binding.imgHand, "scaleY", 1.2f);
        animator_x.setRepeatMode(ValueAnimator.REVERSE);
        animator_y.setRepeatMode(ValueAnimator.REVERSE);
        animator_x.setRepeatCount(1);
        animator_y.setRepeatCount(1);
        animator_x.setDuration(400);
        animator_y.setDuration(400);
        AnimatorSet animator = new AnimatorSet();
        animator.setInterpolator(new AnticipateOvershootInterpolator());
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                performCircleAnima();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.playTogether(animator_x, animator_y);
        animator.start();
    }

    private void performCircleAnima() {
        final AnimationSet animation_light = (AnimationSet) AnimationUtils.loadAnimation(getContext(), R.anim.guide_zoom_out);
        final Animation animation_dark = AnimationUtils.loadAnimation(getContext(), R.anim.alpha_out_half);
        animation2Binding.imgCircleLight.startAnimation(animation_light);
        animation2Binding.imgCircleDark.startAnimation(animation_dark);
        animation_light.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animation2Binding.imgCircleLight.startAnimation(animation_light);
                animation2Binding.imgCircleDark.startAnimation(animation_dark);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void performAnima() {
        int fun_btn_duration = 500;
        int fun_btn_delay = 50;
        int down_height = (int) DimenUtil.dpToPx(47);

        ObjectAnimator add = ObjectAnimator.ofFloat(animation2Binding.btnAddLv, "Rotation", 0, 90);

        ObjectAnimator text = ObjectAnimator.ofFloat(animation2Binding.btnTextLv, "translationY", down_height);
        ObjectAnimator text_alpha = ObjectAnimator.ofFloat(animation2Binding.btnTextLv, "alpha", 1);
        AnimatorSet text_set = new AnimatorSet();
        text.setInterpolator(new BounceInterpolator());
        text_set.playTogether(text, text_alpha);

        ObjectAnimator image = ObjectAnimator.ofFloat(animation2Binding.btnImageLv, "translationY", down_height);
        ObjectAnimator image_alpha = ObjectAnimator.ofFloat(animation2Binding.btnImageLv, "alpha", 1);
        AnimatorSet image_set = new AnimatorSet();
        image.setInterpolator(new BounceInterpolator());
        image_set.playTogether(image, image_alpha);

        ObjectAnimator video = ObjectAnimator.ofFloat(animation2Binding.btnVideoLv, "translationY", down_height);
        ObjectAnimator video_alpha = ObjectAnimator.ofFloat(animation2Binding.btnVideoLv, "alpha", 1);
        AnimatorSet video_set = new AnimatorSet();
        video.setInterpolator(new BounceInterpolator());
        video_set.playTogether(video, video_alpha);

        ObjectAnimator voice = ObjectAnimator.ofFloat(animation2Binding.btnVoiceLv, "translationY", down_height);
        ObjectAnimator voice_alpha = ObjectAnimator.ofFloat(animation2Binding.btnVoiceLv, "alpha", 1);
        AnimatorSet voice_set = new AnimatorSet();
        voice.setInterpolator(new BounceInterpolator());
        voice_set.playTogether(voice, voice_alpha);

        ObjectAnimator map = ObjectAnimator.ofFloat(animation2Binding.btnMapLv, "translationY", down_height);
        ObjectAnimator map_alpha = ObjectAnimator.ofFloat(animation2Binding.btnMapLv, "alpha", 1);
        AnimatorSet map_set = new AnimatorSet();
        map.setInterpolator(new BounceInterpolator());
        map_set.playTogether(map, map_alpha);

        text_set.setDuration(fun_btn_duration);
        image_set.setDuration(fun_btn_duration);
        video_set.setDuration(fun_btn_duration);
        voice_set.setDuration(fun_btn_duration);
        map_set.setDuration(fun_btn_duration);

        text_set.setStartDelay(fun_btn_delay);
        image_set.setStartDelay(fun_btn_delay + 50);
        video_set.setStartDelay(fun_btn_delay + 100);
        voice_set.setStartDelay(fun_btn_delay + 150);
        map_set.setStartDelay(fun_btn_delay + 200);

        ValueAnimator valueAnimator = ValueAnimator.ofInt(animation2Binding.flOutFrame.getHeight(), animation2Binding.flOutFrame.getHeight() + (int) DimenUtil.dpToPx(37));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int h = (int) valueAnimator.getAnimatedValue();
                animation2Binding.flOutFrame.getLayoutParams().height = h;
                animation2Binding.flOutFrame.requestLayout();
            }
        });

        AnimatorSet animator = new AnimatorSet();
        animator.playTogether(add, valueAnimator, text_set, image_set, video_set, voice_set, map_set);
        animator.start();
    }
}
