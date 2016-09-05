package com.std.framework.comm.widget.pullrefreshview.demo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

class DataAdapter extends BaseAdapter {
	private Context mContext;
	public DataAdapter(Context context){
		mContext = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
//		if(convertView == null)
//			convertView = LayoutInflater.from(mContext).inflate(R.layout.data_item, null);
		return convertView;
	}

}
