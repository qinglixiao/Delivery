package com.std.framework.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.library.comm.DownLoadHelper;
import com.library.util.LibUtil;
import com.std.framework.R;

public class FiveFragment extends BaseFragment implements OnClickListener{
	private static final String URL = "http://music.baidu.com/data/music/file?link=http://yinyueshiting.baidu.com/data2/music/134378751/1201250291426651261128.mp3?xcode=61b046e3e00025685104268b9dc45c7ea6a03c85c06f4409&song_id=120125029";
	private View view;
	private Button btn_down;
	private EditText edt_url;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_five, null);
		btn_down = (Button) view.findViewById(R.id.btn_down);
		edt_url = (EditText) view.findViewById(R.id.edt_url);
		edt_url.setText(URL);
		btn_down.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
			case R.id.btn_down:
				String urlString = edt_url.getText().toString();
				startDown(urlString);
				break;
		}
	}
	
	public void startDown(String url){
		DownLoadHelper downLoadHelper = DownLoadHelper.getInstance(getActivity());
		downLoadHelper.saveDir(LibUtil.getAppHomeDirectory(getActivity()));
		downLoadHelper.down(url, "a.a");
		Log.d("LX", LibUtil.getAppHomeDirectory(getActivity()));
	}
	
}
