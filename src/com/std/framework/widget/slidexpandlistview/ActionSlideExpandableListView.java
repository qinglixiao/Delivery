package com.std.framework.widget.slidexpandlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListAdapter;

/**
 * A more specific expandable listview in which the expandable area
 * consist of some buttons which are context actions for the item itself.
 * 
 * It handles event binding for those buttons and allow for adding
 * a listener that will be invoked if one of those buttons are pressed.
 * 
 * @author tjerk
 * @date 6/26/12 7:01 PM
 */
public class ActionSlideExpandableListView extends SlideExpandableListView {
	private OnActionClickListener listener;
	private OnExPandListener mExPandListener;
	private int[] buttonIds = null;

	public ActionSlideExpandableListView(Context context) {
		super(context);
	}

	public ActionSlideExpandableListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ActionSlideExpandableListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void setItemActionListener(OnActionClickListener listener, int... buttonIds) {
		this.listener = listener;
		this.buttonIds = buttonIds;
	}
	
	public void setOnExpandListener(OnExPandListener onExPandListener){
		super.setOnExPandListener(onExPandListener);
	}

	public void setAdapter(ListAdapter adapter) {
		super.setAdapter(adapter);
//		super.setAdapter(new WrapperListAdapterImpl(adapter) {
//
//			@Override
//			public View getView(final int position, View view, ViewGroup viewGroup) {
//				final View listView = wrapped.getView(position, view, viewGroup);
//				// add the action listeners
//				if (buttonIds != null && listView != null) {
//					for (int id : buttonIds) {
//						View buttonView = listView.findViewById(id);
//						if (buttonView != null) {
//							buttonView.findViewById(id).setOnClickListener(new OnClickListener() {
//								@Override
//								public void onClick(View view) {
//									if (listener != null) {
//										listener.onClick(listView, view, position);
//									}
//								}
//							});
//						}
//					}
//				}
//				return listView;
//			}
//		});
	}

	/**
	 * Interface for callback to be invoked whenever an action is clicked in
	 * the expandle area of the list item.
	 */
	public interface OnActionClickListener {
		/**
		 * Called when an action item is clicked.
		 * 
		 * @param itemView the view of the list item
		 * @param clickedView the view clicked
		 * @param position the position in the listview
		 */
		public void onClick(View itemView, View clickedView, int position);
	}

	/**
	 * 
	 * 描 述 ：展开列表项子级时回调接口
	 * 创建日期 : 2013-7-8
	 * 作 者 ： lx
	 * 修改日期 :
	 * 修 改 者 ：
	 * 
	 * @version : 1.0
	 */
	public interface OnExPandListener {
		/**
		 * 
		 * 描 述 ：当子级展开时调用此方法
		 * 创建日期 : 2013-7-8
		 * 作 者 ： lx
		 * 修改日期 :
		 * 修 改 者 ：
		 * 
		 * @version : 1.0
		 * @param itemView
		 * @param position
		 * 
		 */
		public void onExpand(View containerParent, int position);
	}
}
