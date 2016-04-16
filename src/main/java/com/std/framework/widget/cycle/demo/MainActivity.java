package com.std.framework.widget.cycle.demo;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		AutoCycleView circleView = (AutoCycleView) findViewById(R.id.circleView1);
//		CycleViewAdapter adapter = circleView.new CycleViewAdapter(getViews());
//		circleView.setAdapter(adapter);
	}
	
	private ArrayList<View> getViews() {
		ArrayList<View> views = new ArrayList<View>();
//		for (int i = 0; i < 5; i++) {
//			TextView textView = new TextView();
//			textView.setText("ҳ" + i);
//			textView.setTextColor(Color.WHITE);
//			views.add(textView);
//		}
		return views;
	}
}
