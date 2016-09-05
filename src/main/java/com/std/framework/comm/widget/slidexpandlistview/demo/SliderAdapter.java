package com.std.framework.comm.widget.slidexpandlistview.demo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

 class SliderAdapter extends BaseAdapter {
	private Context mContext;
	private String[] data;

	public SliderAdapter(Context context,String[] data) {
		mContext = context;
		this.data = data;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.length;
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
		ViewHolder holder = null;
		if (convertView == null) {
//			convertView = LayoutInflater.from(mContext).inflate(R.layout.expandable_list_item, null);
//			holder = new ViewHolder();
//			holder.textView = (TextView) convertView.findViewById(R.id.text);
//			holder.moreButton = (Button) convertView.findViewById(R.id.expandable_toggle_button);
//			holder.noTextView = (TextView) convertView.findViewById(R.id.listitem_tv_no);
//			convertView.setTag(holder);
		}
		else
			holder = (ViewHolder) convertView.getTag();
		
		holder.noTextView.setText(String.valueOf(position));
		holder.textView.setText(data[position]);
		return convertView;
	}

	public class ViewHolder {
		TextView noTextView;
		TextView textView;
		Button moreButton;
	}

}
