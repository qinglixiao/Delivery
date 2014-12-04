package com.std.framework.widget.dialog;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.library.exception.BaseSTDException;
import com.std.framework.R;

public class ChooseDialog extends AlertDialog {
	// 单选模式
	private boolean isSingleMode = true;

	private Context mContext;
	private int mSelectCountLimit = -1;
	private int mCurrentSelectCount = 0;
	private List<Integer> mSelectIndex = new ArrayList<Integer>();
	private List<Integer> mUnSelectIndex = new ArrayList<Integer>();
	private CharSequence mTitle;
	private int mIco = -1;
	private List<String> mMutiItems;
	private List<String> mSingleItems;
	private List<Integer> mItemImg;
	private OnItemClickListener mOnItemClickListener;
	private OnClickListener mPositiveClickListener;
	private CharSequence mPositiveBtnCaption;
	private CharSequence mNegativeBtnCaption;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_dialog);
		try {
			Button positiveButton = (Button) findViewById(R.id.btn_positive);
			Button negativeButton = (Button) findViewById(R.id.btn_close);
			positiveButton.setOnClickListener(positiveClickListener);
			if (!TextUtils.isEmpty(mPositiveBtnCaption))
				positiveButton.setText(mPositiveBtnCaption);
			negativeButton.setOnClickListener(positiveClickListener);
			if (!TextUtils.isEmpty(mNegativeBtnCaption))
				negativeButton.setText(mNegativeBtnCaption);

			TextView titleView = (TextView) findViewById(R.id.tv_title);
			titleView.setText(mTitle);
			ImageView icoView = (ImageView) findViewById(R.id.imgv_ico);
			if (mIco == -1)
				icoView.setVisibility(View.GONE);
			else
				icoView.setImageResource(mIco);

			if (isSingleMode) {// 如果是单选模式，则将预设的选择项定为一个,默认取第一个
				if (mSelectIndex.size() > 1) {
					mSelectIndex = mSelectIndex.subList(0, 1);
				}
			}
			ListView lstView = (ListView) findViewById(R.id.lstv_item);
			lstView.setAdapter(new ChooseAdapter());
			lstView.setOnItemClickListener(lstViewItemClickListener);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BaseSTDException("对话框初始化失败", e);
		}
	}

	private android.view.View.OnClickListener positiveClickListener = new android.view.View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v.getId() == R.id.btn_positive) {
				if (mPositiveClickListener != null)
					mPositiveClickListener.onClick(ChooseDialog.this);
			}
			dismiss();
		}

	};

	public AdapterView.OnItemClickListener lstViewItemClickListener = new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			ViewHolder holder = (ViewHolder) view.getTag();
			if (isSingleMode) {
				mSelectIndex.clear();
				mSelectIndex.add(position);
				((ChooseAdapter) parent.getAdapter()).notifyDataSetChanged();
			}
			else {
				if (!holder.checkBox.isChecked()) {
					if (allowChecked(mCurrentSelectCount)) {
						holder.checkBox.setChecked(true);
						mCurrentSelectCount++;
						if (!mSelectIndex.contains(position))
							mSelectIndex.add(position);
						if (mUnSelectIndex.contains(position))
							mUnSelectIndex.remove(mUnSelectIndex.indexOf(position));
					}
					else {
						Toast.makeText(mContext, "超出限制选择数目！", 0).show();
					}
				}
				else {
					holder.checkBox.setChecked(false);
					mCurrentSelectCount--;
					if (!mUnSelectIndex.contains(position))
						mUnSelectIndex.add(position);
					if (mSelectIndex.contains(position))
						mSelectIndex.remove(mSelectIndex.indexOf(position));
				}

			}
			if (mOnItemClickListener != null)
				mOnItemClickListener.onClick(ChooseDialog.this, position, holder.textView.getText(), holder.checkBox.isChecked());
		}

	};

	// 判断该项是否允许选中,如果已经超出限定数目则不可再选中
	private boolean allowChecked(int curcount) {
		if (mSelectCountLimit != -1) {
			if (mSelectCountLimit > curcount)
				return true;
			else
				return false;
		}
		else
			return true;
	}

	/**
	 * 
	 * 描          述 ：设置在多选模式中限制选中的条目数
	 * 创建日期  : 2013-11-6
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param count
	 *
	 */
	public void setSelectCountLimit(int count) {
		mSelectCountLimit = count < 0 ? 0 : count;
	}

	/**
	 * 
	 * 描          述 ：获取选中的条目索引
	 * 创建日期  : 2013-11-6
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @return
	 *
	 */
	public List<Integer> getSelectIndex() {
		return mSelectIndex;
	}
	/**
	 * 
	 * 描          述 ：设置选中的条目索引集合
	 * 创建日期  : 2013-11-6
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param indexs
	 *
	 */
	public void setSelectIndex(List<Integer> indexs) {
		mSelectIndex = indexs;
	}

	/**
	 * 
	 * 描          述 ：设置选中的条目索引
	 * 创建日期  : 2013-11-6
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param indexs
	 *
	 */
	public void setSelectIndex(int index) {
		mSelectIndex.clear();
		mSelectIndex.add(index);
	}

	/**
	 * 
	 * 描          述 ：设置确定按钮显示的文本
	 * 创建日期  : 2013-11-6
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param text
	 *
	 */
	public void setPositiveBtnCaption(String text) {
		mPositiveBtnCaption = text;
	}

	/**
	 * 
	 * 描          述 ：设置取消按钮显示的文本
	 * 创建日期  : 2013-11-6
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param text
	 *
	 */
	public void setNegativeBtnCaption(String text) {
		mNegativeBtnCaption = text;
	}
	
	/**
	 * 
	 * 描          述 ：设置确定按钮点击事件
	 * 创建日期  : 2013-11-6
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param onClickListener
	 *
	 */
	public void setPositiveClickListener(OnClickListener onClickListener) {
		mPositiveClickListener = onClickListener;
	}

	public List<Integer> getUnSelectIndex() {
		return mUnSelectIndex;
	}

	@Override
	public void setTitle(CharSequence title) {
		// TODO Auto-generated method stub
		super.setTitle(title);
		mTitle = title;
	}

	/**
	 * 
	 * 描          述 ：设置窗口图标
	 * 创建日期  : 2013-11-6
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param resId
	 *
	 */
	@Override
	public void setIcon(int resId) {
		// TODO Auto-generated method stub
		super.setIcon(resId);
		mIco = resId;
	}

	/**
	 * 
	 * 描          述 ：设置多选项目
	 * 创建日期  : 2013-11-6
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param items
	 *
	 */
	public void setMutiItems(List<String> items) {
		mMutiItems = items;
		isSingleMode = false;
	}

	/**
	 * 
	 * 描 述 ：获取多选结果
	 * 创建日期 : 2013-11-6
	 * 作 者 ： lx
	 * 修改日期 :
	 * 修 改 者 ：
	 * 
	 * @version : 1.0
	 * @return
	 * 
	 */
	public List<String> getSelectMutiItems() {
		if (isSingleMode)
			return null;
		ArrayList<String> temp = new ArrayList<String>();
		for (int index : mSelectIndex) {
			temp.add(mMutiItems.get(index));
		}
		return temp;
	}

	/**
	 * 
	 * 描          述 ：获取单选所选项目
	 * 创建日期  : 2013-11-6
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @return
	 *
	 */
	public String getSelectSingleItem() {
		if (!isSingleMode)
			return "";
		else
			return mSelectIndex.size() == 0 ? "" : mSingleItems.get(mSelectIndex.get(mSelectIndex.size() - 1));
	}

	/**
	 * 
	 * 描          述 ：设置单选项目
	 * 创建日期  : 2013-11-6
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param items
	 *
	 */
	public void setSingleItems(List<String> items) {
		mSingleItems = items;
		isSingleMode = true;
	}

	/**
	 * 
	 * 描          述 ：设置项目显示的图标
	 * 创建日期  : 2013-11-6
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param images
	 *
	 */
	public void setItemImage(List<Integer> images){
		mItemImg = images;
	}
	
	public void setOnItemClickLitener(OnItemClickListener onItemClickListener) {
		mOnItemClickListener = onItemClickListener;
	}

	public ChooseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		// TODO Auto-generated constructor stub
	}

	public ChooseDialog(Context context, int theme) {
		super(context, theme);
		mContext = context;
	}

	public ChooseDialog(Context context) {
		super(context);
		mContext = context;
	}

	public interface OnItemClickListener {
		public void onClick(ChooseDialog dialog, int which, CharSequence text, boolean isChecked);
	}

	public interface OnClickListener {
		public void onClick(ChooseDialog dialog);
	}

	public class ChooseAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			if (isSingleMode)
				return mSingleItems.size();
			else
				return mMutiItems.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			if (isSingleMode)
				return mSingleItems.get(position);
			else
				return mMutiItems.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder = null;
			if (isSingleMode) {
				if (convertView == null) {
					convertView = LayoutInflater.from(mContext).inflate(R.layout.single_choose_item, null);
					holder = new ViewHolder();
					holder.radioButton = (RadioButton) convertView.findViewById(R.id.rdobtn);
					holder.imageView = (ImageView) convertView.findViewById(R.id.imgv_single_ico);
					holder.textView = (TextView) convertView.findViewById(R.id.tv_single_txt);
					convertView.setTag(holder);
				}
				else
					holder = (ViewHolder) convertView.getTag();
				if (mItemImg != null) {
					try {
						holder.imageView.setVisibility(View.VISIBLE);
						holder.imageView.setImageResource(mItemImg.get(position));
					}
					catch (Exception e) {
						// TODO Auto-generated catch block
						holder.imageView.setVisibility(View.GONE);
						e.printStackTrace();
					}
				}
				else
					holder.imageView.setVisibility(View.GONE);
				holder.radioButton.setChecked(mSelectIndex.contains(position));
				holder.textView.setText(mSingleItems.get(position));
			}
			else {
				if (convertView == null) {
					convertView = LayoutInflater.from(mContext).inflate(R.layout.muti_choose_item, null);
					holder = new ViewHolder();
					holder.checkBox = (CheckBox) convertView.findViewById(R.id.cbx);
					holder.imageView = (ImageView) convertView.findViewById(R.id.imgv_muti_ico);
					holder.textView = (TextView) convertView.findViewById(R.id.tv_muti_txt);
					convertView.setTag(holder);
					if (mSelectCountLimit != -1 && mSelectIndex.contains(position) && mCurrentSelectCount < mSelectCountLimit) {
						holder.checkBox.setChecked(true);
						mCurrentSelectCount++;
					}
				}
				else
					holder = (ViewHolder) convertView.getTag();
				
				if (mItemImg != null) {
					try {
						holder.imageView.setVisibility(View.VISIBLE);
						holder.imageView.setImageResource(mItemImg.get(position));
					}
					catch (Exception e) {
						// TODO Auto-generated catch block
						holder.imageView.setVisibility(View.GONE);
						e.printStackTrace();
					}
				}
				else
					holder.imageView.setVisibility(View.GONE);

				holder.checkBox.setChecked(mSelectIndex.contains(position));
				holder.textView.setText(mMutiItems.get(position));
			}
			return convertView;
		}
	}

	public class ViewHolder {
		RadioButton radioButton;
		CheckBox checkBox;
		ImageView imageView;
		TextView textView;
	}

}
