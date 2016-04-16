package com.std.framework.widget.slidexpandlistview.demo;

import java.util.HashMap;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

 class ModuleManager {
	private HashMap<String, Module> moduleMap;
	private ViewGroup parent;
	private Context mContext;

	public ModuleManager(Context context) {
		mContext = context;
		moduleMap = new HashMap<String, Module>(); 
	}
	
	public void setManagerContainer(ViewGroup parent){
		this.parent = parent;
		moduleMap.clear();
	}

	public void addModule(Module module) {
		if(module == null || parent == null)
			return;
		moduleMap.put(module.getId(), module);
		parent.addView(createModuleLayout(module));
	}
	
	private LinearLayout createModuleLayout(Module module){
		LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.CENTER_HORIZONTAL;
		
		LinearLayout linearLayout = new LinearLayout(mContext);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		ImageView imageView = new ImageView(mContext);
		TextView textView = new TextView(mContext);
		
		imageView.setImageResource(module.getIco());
		textView.setText(module.getText());
		
		linearLayout.addView(imageView,params);
		linearLayout.addView(textView,params);
		linearLayout.setTag(module.getId());
		linearLayout.setOnClickListener(onClickListener);

		return linearLayout;
	}
	
	private OnClickListener onClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Module module = moduleMap.get(v.getTag());
			OnModuleListener onModuleListener = module.getModuleListener();
			if(onModuleListener != null)
				onModuleListener.OnModuleClick(module);
		}
	};

	public class Module {
		private String id;
		private int ico;
		private String text;
		private OnModuleListener moduleListener;
		
		public OnModuleListener getModuleListener() {
			return moduleListener;
		}
		public void setOnModuleListener(OnModuleListener moduleListener) {
			this.moduleListener = moduleListener;
		}

		public int getIco() {
			return ico;
		}

		public void setIco(int ico) {
			this.ico = ico;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

	}

	public interface OnModuleListener {
		public void OnModuleClick(Module module);
	}
}
