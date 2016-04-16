package com.std.framework.fragment;

import com.std.framework.R;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class PagerFragment extends Fragment {
	private static final String ARG_POSITION = "position";
	private static final String TITLE = "title";

	private String title;
	private View view;
	private TextView tv_show;
	private int no;

	public static PagerFragment newInstance(int no, String title) {
		PagerFragment pagerFragment = new PagerFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(ARG_POSITION, no);
		bundle.putString(TITLE, title);
		pagerFragment.setArguments(bundle);
		return pagerFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		no = getArguments().getInt(ARG_POSITION);
		title = getArguments().getString(TITLE);
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (title.equals("ButtonImageView")) {
			view = inflater.inflate(R.layout.fragment_button_image_view, null);
			Button button = new Button(getActivity());
			Drawable drawable;
		}
		else {
			view = inflater.inflate(R.layout.fragment_pager, null);
			tv_show = (TextView) view.findViewById(R.id.tv_cur);
			tv_show.setText(ARG_POSITION + ":" + no);
		}
		return view;
	}
}
