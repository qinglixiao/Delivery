package com.std.framework.comm.view.slidexpandlistview.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.std.framework.comm.view.slidexpandlistview.ActionSlideExpandableListView;

class ExampleActivity extends Activity {
	private final String[] data = { "偏偏喜欢你", "无可避免", "周末画报", "好结他", "另眼相看", "活着", "麻不甩", "主旋律" };
	private final String[][] modules = { { "添加", "编辑" }, { "添加" }, { "添加", "编辑", "删除" }, { "编辑" }, { "删除" }, { "添加" }, { "添加", "编辑" }, { "编辑" } };
	private ModuleManager manager;

	@Override
	public void onCreate(Bundle savedData) {
		super.onCreate(savedData);
//		this.setContentView(R.layout.single_expandable_list);
//		ActionSlideExpandableListView list = (ActionSlideExpandableListView) this.findViewById(R.id.list);
//		list.setAdapter(new SliderAdapter(this, data));
//		list.setOnExpandListener(onExPandListener);
//		manager = new ModuleManager(this);

	}

	private ActionSlideExpandableListView.OnExPandListener onExPandListener = new ActionSlideExpandableListView.OnExPandListener() {

		@Override
		public void onExpand(View containerParent, int position) {
			// TODO Auto-generated method stub
//			ViewGroup container = (ViewGroup) containerParent.findViewById(R.id.container);
//			container.removeAllViews();
//			manager.setManagerContainer(container);
//			for (int j = 0; j < modules[position].length; j++) {
//				Module module = manager.new Module();
//				module.setId(j + "");
//				module.setIco(R.drawable.icon);
//				module.setText(modules[position][j]);
//				module.setOnModuleListener(onModuleListener);
//				manager.addModule(module);
//			}
		}

	};

	private ModuleManager.OnModuleListener onModuleListener = new ModuleManager.OnModuleListener() {

		@Override
		public void OnModuleClick(ModuleManager.Module module) {
			// TODO Auto-generated method stub
			Toast.makeText(ExampleActivity.this, module.getText(), 0).show();
		}
	};
}
