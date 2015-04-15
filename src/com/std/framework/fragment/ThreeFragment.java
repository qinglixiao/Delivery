package com.std.framework.fragment;

import java.io.IOException;

import com.std.framework.R;
import com.std.framework.view.ShapeImageView;
import com.std.framework.view.ShapeImageView.Shape;

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
	private ShapeImageView shapeImageView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_three, null);
		button = (Button) view.findViewById(R.id.button1);
		button.setOnClickListener(this);
		shapeImageView = (ShapeImageView) view.findViewById(R.id.shape_cus);
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		shapeImageView.changeShapeType(Shape.POLYGON);
	}
	
}
