package com.std.framework.fragment;

import java.io.IOException;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.std.framework.R;
import com.std.framework.view.ShapeView;

public class ThreeFragment extends BaseFragment implements OnClickListener{
	private View view;
	private EditText editText;
	private Button button;
	private ShapeView shapeView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_three, null);
		editText = (EditText) view.findViewById(R.id.frag3_edt);
		button = (Button) view.findViewById(R.id.frag3_btn);
		shapeView = (ShapeView) view.findViewById(R.id.shapeView1);
		button.setOnClickListener(this);
		shapeView.setImageDrawable(getResources().getDrawable(R.drawable.call));
		registerListener();
		return view;
	}

	private void registerListener(){
		editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				Log.d("LX",v.getText().toString());
				return false;
			}
		});

		
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
