package com.std.framework.fragment;

import java.io.IOException;

import com.std.framework.R;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ThreeFragment extends BaseFragment implements OnClickListener{
	private View view;
	private EditText editText;
	private Button button;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_three, null);
		editText = (EditText) view.findViewById(R.id.frag3_edt);
		button = (Button) view.findViewById(R.id.frag3_btn);
		button.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		try {
			Runtime.getRuntime().exec(new String[]{"input","text"," fuytfrdrsxgf"});
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
