package com.std.framework.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.std.framework.R;
import com.std.framework.view.ShapeImageView;

public class ThreeFragment extends BaseFragment implements OnClickListener {
	private View view;
	private EditText editText;
	private Button button;
	private ShapeImageView shapeImageView;
	private ShapeImageView shapeImageView1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_three, null);
		button = (Button) view.findViewById(R.id.button1);
		button.setOnClickListener(this);
		shapeImageView = (ShapeImageView) view.findViewById(R.id.shape_cus);
		shapeImageView1 = (ShapeImageView) view.findViewById(R.id.shapeImageView1);
		//		shapeImageView1.setOnClickListener(this);
		//		shapeImageView1.setFocusable(true);
		//		shapeImageView1.setClickable(true);
		//		shapeImageView1.requestFocus();
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.button1:
				shapeImageView.setRightBottomDrawable(getResources().getDrawable(R.drawable.common_triangle_27));
				shapeImageView.invalidate();
				break;
			case R.id.shapeImageView1:
				Toast.makeText(getActivity(), "click", 1).show();
				break;
		}

	}

}
