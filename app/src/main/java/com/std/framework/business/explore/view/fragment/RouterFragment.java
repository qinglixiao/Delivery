package com.std.framework.business.explore.view.fragment;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.std.framework.R;
import com.std.framework.databinding.FragmentRouterBinding;
import com.std.framework.router.CYRouter;
import com.std.framework.router.interfaces.Resolve;

import java.util.HashMap;

import me.std.base.base.BaseFragment;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/10/24.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public class RouterFragment extends BaseFragment implements View.OnClickListener {
    private FragmentRouterBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_router, null);
        binding = DataBindingUtil.bind(view);
        initListener();
        return view;
    }

    private void initListener() {
        binding.btnOpen1.setOnClickListener(this);
        binding.btnOpen2.setOnClickListener(this);
        binding.btnOpen3.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v == binding.btnOpen1) {
            HashMap map = new HashMap();
//            map.put("activity",getActivity());
//            map.put("request",2);
//            CYRouter.build(binding.path1.getText().toString(),map)
//                    .showTime(getContext())
//                    .done();
            Bundle bundle = new Bundle();
            bundle.putString("arg","100");
            map.put("bundle",bundle);
            CYRouter.build("chunyu://lib/bundle",map).done();
        } else if (v == binding.btnOpen2) {
            CYRouter.build(binding.path2.getText().toString(),
                    "path1", "ss"
                    , "path2", "mm")
                    .showTime(getContext())
                    .done(new Resolve<String>() {
                        @Override
                        public void call(String data) {
//                            Toast.makeText(getContext(), data, Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
