package com.std.framework.fragment;

import com.std.framework.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PagerFragment extends Fragment {
	private static final String ARG_POSITION = "position";
	private View view;
	private TextView tv_show;
	private int no;
	
	public static PagerFragment newInstance(int no){
		PagerFragment pagerFragment = new PagerFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(ARG_POSITION, no);
		pagerFragment.setArguments(bundle);
		return pagerFragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		no = getArguments().getInt(ARG_POSITION);
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_pager, null);
		tv_show = (TextView) view.findViewById(R.id.tv_cur);
		tv_show.setText(ARG_POSITION+":"+no);
		return view;
	}
}
