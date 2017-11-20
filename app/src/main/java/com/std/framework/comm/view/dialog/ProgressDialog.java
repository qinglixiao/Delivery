package com.std.framework.comm.view.dialog;

import java.text.NumberFormat;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressDialog extends AlertDialog {
	/**
	 * Creates a ProgressDialog with a circular, spinning progress bar. This
	 * is the default.
	 */
	public static final int STYLE_SPINNER = 0;

	/**
	 * Creates a ProgressDialog with a horizontal progress bar.
	 */
	public static final int STYLE_HORIZONTAL = 1;

	private ProgressBar mProgress;
	private TextView mMessageView;
	

	private int mProgressStyle = STYLE_SPINNER;
	private TextView mProgressNumber;
	private String mProgressNumberFormat;
	private TextView mProgressPercent;
	private NumberFormat mProgressPercentFormat;

	private int mMax;
	private int mProgressVal;
	private int mSecondaryProgressVal;
	private int mIncrementBy;
	private int mIncrementSecondaryBy;
	private Drawable mProgressDrawable;
	private Drawable mIndeterminateDrawable;
	private CharSequence mMessage;
	private boolean mIndeterminate;

	private boolean mHasStarted;
	private Handler mViewUpdateHandler;
	
	private CharSequence mTitle;
	private TextView mTitleView;
	private ViewGroup mTitlePanel;

	public ProgressDialog(Context context) {
		super(context);
		initFormats();
	}

	public ProgressDialog(Context context, int theme) {
		super(context, theme);
		initFormats();
	}

	private void initFormats() {
		mProgressNumberFormat = "%1d/%2d";
		mProgressPercentFormat = NumberFormat.getPercentInstance();
		mProgressPercentFormat.setMaximumFractionDigits(0);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = LayoutInflater.from(getContext());
		if (mProgressStyle == STYLE_HORIZONTAL) {
			/*
			 * Use a separate handler to update the text views as
			 * they must be updated on the same thread that created
			 * them.
			 */
			mViewUpdateHandler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					super.handleMessage(msg);

					/* Update the number and percent */
					int progress = mProgress.getProgress();
					int max = mProgress.getMax();
					if (mProgressNumberFormat != null) {
						String format = mProgressNumberFormat;
						mProgressNumber.setText(String.format(format, progress, max));
					}
					else {
						mProgressNumber.setText("");
					}
					if (mProgressPercentFormat != null) {
						double percent = (double) progress / (double) max;
						SpannableString tmp = new SpannableString(mProgressPercentFormat.format(percent));
						tmp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, tmp.length(),
								Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
						mProgressPercent.setText(tmp);
					}
					else {
						mProgressPercent.setText("");
					}
				}
			};
//			View view = inflater.inflate(R.layout.progress_horizontal, null);
//			mProgress = (ProgressBar) view.findViewById(R.id.progress);
//			mProgressNumber = (TextView) view.findViewById(R.id.progress_number);
//			mProgressPercent = (TextView) view.findViewById(R.id.progress_percent);
//			mTitleView = (TextView) view.findViewById(R.id.progresscircle_title_txt);
//			mTitlePanel = (ViewGroup)view.findViewById(R.id.progresscircle_titile_llt);
//			setContentView(view);
		}
		else {
//			View view = inflater.inflate(R.layout.progress_circle, null);
//			mProgress = (ProgressBar) view.findViewById(R.id.progress);
//			mMessageView = (TextView) view.findViewById(R.id.message);
//			mTitleView = (TextView) view.findViewById(R.id.progresscircle_title_txt);
//			mTitlePanel = (ViewGroup)view. findViewById(R.id.progresscircle_titile_llt);
//			setContentView(view);
		}
		if (mMax > 0) {
			setMax(mMax);
		}
		if (mProgressVal > 0) {
			setProgress(mProgressVal);
		}
		if (mSecondaryProgressVal > 0) {
			setSecondaryProgress(mSecondaryProgressVal);
		}
		if (mIncrementBy > 0) {
			incrementProgressBy(mIncrementBy);
		}
		if (mIncrementSecondaryBy > 0) {
			incrementSecondaryProgressBy(mIncrementSecondaryBy);
		}
		if (mProgressDrawable != null) {
			setProgressDrawable(mProgressDrawable);
		}
		if (mIndeterminateDrawable != null) {
			setIndeterminateDrawable(mIndeterminateDrawable);
		}
		if (mMessage != null) {
			setMessage(mMessage);
		}
		if(TextUtils.isEmpty(mTitle)){
			mTitlePanel.setVisibility(View.GONE);
		}
		else{
			mTitlePanel.setVisibility(View.VISIBLE);
			mTitleView.setText(mTitle);
		}
			
		setIndeterminate(mIndeterminate);
		onProgressChanged();
	}

	@Override
	public void onStart() {
		super.onStart();
		mHasStarted = true;
	}

	@Override
	protected void onStop() {
		super.onStop();
		mHasStarted = false;
	}

	public void setProgress(int value) {
		if (mHasStarted) {
			mProgress.setProgress(value);
			onProgressChanged();
		}
		else {
			mProgressVal = value;
		}
	}

	public void setSecondaryProgress(int secondaryProgress) {
		if (mProgress != null) {
			mProgress.setSecondaryProgress(secondaryProgress);
			onProgressChanged();
		}
		else {
			mSecondaryProgressVal = secondaryProgress;
		}
	}

	public int getProgress() {
		if (mProgress != null) {
			return mProgress.getProgress();
		}
		return mProgressVal;
	}

	public int getSecondaryProgress() {
		if (mProgress != null) {
			return mProgress.getSecondaryProgress();
		}
		return mSecondaryProgressVal;
	}

	public int getMax() {
		if (mProgress != null) {
			return mProgress.getMax();
		}
		return mMax;
	}

	public void setMax(int max) {
		if (mProgress != null) {
			mProgress.setMax(max);
			onProgressChanged();
		}
		else {
			mMax = max;
		}
	}

	public void incrementProgressBy(int diff) {
		if (mProgress != null) {
			mProgress.incrementProgressBy(diff);
			onProgressChanged();
		}
		else {
			mIncrementBy += diff;
		}
	}

	public void incrementSecondaryProgressBy(int diff) {
		if (mProgress != null) {
			mProgress.incrementSecondaryProgressBy(diff);
			onProgressChanged();
		}
		else {
			mIncrementSecondaryBy += diff;
		}
	}

	public void setProgressDrawable(Drawable d) {
		if (mProgress != null) {
			mProgress.setProgressDrawable(d);
		}
		else {
			mProgressDrawable = d;
		}
	}

	public void setIndeterminateDrawable(Drawable d) {
		if (mProgress != null) {
			mProgress.setIndeterminateDrawable(d);
		}
		else {
			mIndeterminateDrawable = d;
		}
	}

	public void setIndeterminate(boolean indeterminate) {
		if (mProgress != null) {
			mProgress.setIndeterminate(indeterminate);
		}
		else {
			mIndeterminate = indeterminate;
		}
	}

	public boolean isIndeterminate() {
		if (mProgress != null) {
			return mProgress.isIndeterminate();
		}
		return mIndeterminate;
	}

	@Override
	public void setMessage(CharSequence message) {
		if (mProgress != null) {
			if (mProgressStyle == STYLE_HORIZONTAL) {
				super.setMessage(message);
			}
			else {
				mMessageView.setText(message);
			}
		}
		else {
			mMessage = message;
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		// TODO Auto-generated method stub
		super.setTitle(title);
		mTitle = title;
	}

	public void setProgressStyle(int style) {
		mProgressStyle = style;
	}

	/**
	 * Change the format of the small text showing current and maximum units
	 * of progress. The default is "%1d/%2d". Should not be called during
	 * the number is progressing.
	 * 
	 * @param format
	 *                A string passed to {@link String#format
	 *                String.format()}; use "%1d" for the current number and
	 *                "%2d" for the maximum. If null, nothing will be shown.
	 */
	public void setProgressNumberFormat(String format) {
		mProgressNumberFormat = format;
		onProgressChanged();
	}

	/**
	 * Change the format of the small text showing the percentage of
	 * progress. The default is {@link NumberFormat#getPercentInstance()
	 * NumberFormat.getPercentageInstnace().} Should not be called during
	 * the number is progressing.
	 * 
	 * @param format
	 *                An instance of a {@link NumberFormat} to generate the
	 *                percentage text. If null, nothing will be shown.
	 */
	public void setProgressPercentFormat(NumberFormat format) {
		mProgressPercentFormat = format;
		onProgressChanged();
	}

	private void onProgressChanged() {
		if (mProgressStyle == STYLE_HORIZONTAL) {
			if (mViewUpdateHandler != null && !mViewUpdateHandler.hasMessages(0)) {
				mViewUpdateHandler.sendEmptyMessage(0);
			}
		}
	}

	/**
	 * 描          述 ：弹出旋转等待提示框
	 * 创建日期  : 2013-6-13
	 * 作           者 ： 
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param context 上下文
	 * @return 提示框实例
	 *
	 */
	public static ProgressDialog show(Context context) {
		return show(context, "");
	}

	/**
	 * 
	 * 描          述 ：弹出旋转等待提示框
	 * 创建日期  : 2013-6-13
	 * 作           者 ：lx 
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param context 上下文
	 * @param message 提示信息
	 * @return 提示框实例
	 *
	 */
	public static ProgressDialog show(Context context, CharSequence message) {
		return show(context, message, "");
	}

	/**
	 * 
	 * 描          述 ：弹出旋转等待提示框
	 * 创建日期  : 2013-6-13
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param context 上下文
	 * @param message 提示信息
	 * @param title 标题
	 * @return 提示框实例
	 *
	 */
	public static ProgressDialog show(Context context, CharSequence message, CharSequence title) {
		return show(context, message, title, null);
	}

	/**
	 * 
	 * 描          述 ：弹出旋转等待提示框
	 * 创建日期  : 2013-6-13
	 * 作           者 ： lx
	 * 修改日期  : 
	 * 修   改   者 ：
	 * @version   : 1.0
	 * @param context 上下文
	 * @param message 提示信息
	 * @param title 标题
	 * @param cancelListener 提示框取消回调接口
	 * @return 提示框实例
	 *
	 */
	public static ProgressDialog show(Context context, CharSequence message, CharSequence title, OnCancelListener cancelListener) {
		ProgressDialog dialog = new ProgressDialog(context);
		dialog.setTitle(title);
		dialog.setMessage(message);
		dialog.setCancelable(true);
		dialog.setOnCancelListener(cancelListener);
//		dialog.setIndeterminateDrawable(dialog.getContext().getResources().getDrawable(R.drawable.progressdialog));
		dialog.show();
		return dialog;
	}
	
	/**
	 * 描 述 ： 设置对话框尺寸 
	 * 创建日期 : 2013-5-30
	 *  作 者 ：lx 
	 *  修改日期 : 
	 *  修 改 者 ：
	 * @version : 1.0
	 * @param width
	 * @param height
	 */
	public void resetDialogSize(int width, int height) {
		LayoutParams params = getWindow().getAttributes();
		params.width = width;
		params.height = height;
		getWindow().setAttributes(params);
	}

}
