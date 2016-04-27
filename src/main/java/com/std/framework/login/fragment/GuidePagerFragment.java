package com.std.framework.login.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.std.framework.R;
import com.std.framework.login.activity.LaunchActivity;

public class GuidePagerFragment extends Fragment {
	public boolean isLastPager;
	public View view;
	public int imgResourceId;

	public static GuidePagerFragment newInstance(int resId,boolean isLast){
		GuidePagerFragment fragment = new GuidePagerFragment();
		fragment.imgResourceId = resId;
		fragment.isLastPager = isLast;
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_guide_pager, null);
		View button = view.findViewById(R.id.btn_guide_pager_start);
		ImageView img = (ImageView) view.findViewById(R.id.img_guide_pager);
		button.setVisibility(isLastPager?View.VISIBLE:View.GONE);
		button.setOnClickListener(onClickListener);
		img.setImageResource(imgResourceId);
		return view;
	}
	
	private OnClickListener onClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(getActivity() instanceof LaunchActivity){
				((LaunchActivity)getActivity()).redirectToMain();
			}
		}
	};
	
}
