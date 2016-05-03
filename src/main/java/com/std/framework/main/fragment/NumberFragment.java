package com.std.framework.main.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.std.framework.R;
import com.std.framework.fragment.BaseFragment;
import com.std.framework.main.activity.MainActivity;
import com.std.framework.view.TouchSlopView;
import com.std.framework.view.TouchSlopView.OnSlopListener;

public class NumberFragment extends BaseFragment {
	private int number = 1;
	private TextView tv_page;
	private ListView listView;
	private Button btnBack;
	TouchSlopView touchSlopView;
	
	public static NumberFragment newInstance(int number){
		NumberFragment fragment = new NumberFragment();
		fragment.number = number;
		return fragment;
	}
	
	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		touchSlopView = new TouchSlopView(getActivity());
		touchSlopView.setOnSlopChangeListener(onSlopChangeListener);
		touchSlopView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		View view =  inflater.inflate(R.layout.numberfragment, null, false);
		listView = (ListView) view.findViewById(R.id.listView1);
		btnBack = (Button) view.findViewById(R.id.btn_back);
		btnBack.setOnClickListener(onClickListener);
		initList();
		tv_page = (TextView) view.findViewById(R.id.tv_page);
		tv_page.setText("页码"+number);
		touchSlopView.addView(view);
		return touchSlopView;
	}
	
	OnClickListener onClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			touchSlopView.open();
		}
	};
	
	private void initList(){
		String[] data = {"第一页","第二页","第三页","第四页","第五页","第六页","第七页","第八页","第九页"};
		ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, data);
		listView.setAdapter(adapter);
	}
	
	private OnSlopListener onSlopChangeListener = new OnSlopListener() {
		
		@Override
		public void onReset() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onOpend() {
			// TODO Auto-generated method stub
			((MainActivity)NumberFragment.this.getActivity()).remove(NumberFragment.this);
		}
		
		@Override
		public void onDraging() {
			// TODO Auto-generated method stub
			
		}
	};
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
}
