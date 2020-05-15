package com.std.framework.business.mine.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

import com.nineoldandroids.view.ViewPropertyAnimator;
import com.std.framework.R;
import com.std.framework.comm.view.TouchSlopView;
import com.std.framework.router.CYRouter;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import me.com.eachbeanhuman.MainPlayActivity;
import me.std.base.base.BaseFragment;

public class MineFragment extends BaseFragment implements OnClickListener {
    private View view;
    private Button button;
    private TouchSlopView slopView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_five, null);
        button = (Button) view.findViewById(R.id.five_btn);
        view.findViewById(R.id.btn_web).setOnClickListener(onClickListener);
        button.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        View vi = LayoutInflater.from(getActivity()).inflate(R.layout.pop_slop_content, null);
        slopView = (TouchSlopView) vi.findViewById(R.id.touchSlopView1);
        vi.findViewById(R.id.button1).setOnClickListener(onClickListener);
        PopupWindow popupWindow = new PopupWindow(getActivity(), null, R.style.ESGStyle_Dialog_Message);
        popupWindow.setContentView(LayoutInflater.from(getActivity()).inflate(R.layout.pop_slop_content, null));
        popupWindow.setHeight(LayoutParams.MATCH_PARENT);
        popupWindow.setWidth(LayoutParams.MATCH_PARENT);
        popupWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        ViewPropertyAnimator.animate(vi).scaleX(2).scaleY(2).start();
    }

    private OnClickListener onClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if (v.getId() == R.id.btn_web) {
//                Map<String, Object> param = new HashMap<>();
//                param.put("activity", getActivity());
//                param.put("title", "吃豆人游戏");
//                param.put("url", "https://passer-by.com/pacman/");
//                CYRouter.build("chunyu://CommonWebProvider/openH5"
//                        , param).done();
                startActivity(new Intent(getActivity(), MainPlayActivity.class));
                return;
            } else {
                ss();
            }
        }
    };

    private void ss() {
        ViewPropertyAnimator.animate(slopView).scaleX(2).scaleY(2).start();
    }

}
