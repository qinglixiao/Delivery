package com.std.framework.comm.widget.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.library.exception.BaseSTDException;
import com.std.framework.R;
import com.std.framework.comm.interfaces.OnTipClickListener;

public class MessageTip extends AlertDialog {
	// 提示框
	private static final int ALERTDIALOG = 0;
	// 确认框
	private static final int CONFIRMDIALOG = 1;
	/**
	 * 确认按钮
	 */
	public static final int OK = 0;
	/**
	 * 取消按钮
	 */
	public static final int CANCEL = 1;

	private int m_dialog_type = 0;

	private OnTipClickListener mOnClickListener;

	private CharSequence mTitle;
	private int mIco = -1;
	private CharSequence mContent;
	// 确认按钮显示文本
	private CharSequence mOkButtonTxt;
	// 取消按钮显示文本
	private CharSequence mCancleButtonTxt;

	public MessageTip(Context context, boolean cancelable, OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		// TODO Auto-generated constructor stub
	}

	public MessageTip(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
	}

	protected MessageTip(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		try {
			ImageView icoView = null;
			TextView titleView = null;
			TextView contenTextView = null;
			Button okButton = null;

			if (m_dialog_type == ALERTDIALOG) {
				setContentView(R.layout.alert_dialog);
				icoView = (ImageView) findViewById(R.id.alert_title_ico_igv);
				titleView = (TextView) findViewById(R.id.alert_title_txt_tv);
				contenTextView = (TextView) findViewById(R.id.alert_content_tv);
				okButton = (Button) findViewById(R.id.alert_ok_btn);
				okButton.setOnClickListener(onClickListener);
			}
			else if (m_dialog_type == CONFIRMDIALOG) {
				setContentView(R.layout.confirm_dialog);
				icoView = (ImageView) findViewById(R.id.confirm_title_ico_igv);
				titleView = (TextView) findViewById(R.id.confirm_title_txt_tv);
				contenTextView = (TextView) findViewById(R.id.confirm_content_tv);
				okButton = (Button) findViewById(R.id.confirm_ok_btn);
				Button cancleButton = (Button) findViewById(R.id.confirm_cancel_btn);
				okButton.setOnClickListener(onClickListener);
				cancleButton.setOnClickListener(onClickListener);
				okButton.setFocusable(true);
				okButton.setFocusableInTouchMode(true);
				okButton.requestFocus();
				if (!TextUtils.isEmpty(mCancleButtonTxt))
					cancleButton.setText(mCancleButtonTxt);
			}

			if (mIco == -1) {
				icoView.setVisibility(View.GONE);
			}
			else {
				icoView.setVisibility(View.VISIBLE);
				icoView.setImageResource(mIco);
			}
			if (TextUtils.isEmpty(mTitle)) {
				titleView.setVisibility(View.GONE);
			}
			else {
				titleView.setVisibility(View.VISIBLE);
				titleView.setText(mTitle);
			}
			if (!TextUtils.isEmpty(mOkButtonTxt))
				okButton.setText(mOkButtonTxt);

			contenTextView.setText(mContent);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BaseSTDException("对话框初始化失败", e);
		}
	}

	private View.OnClickListener onClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (mOnClickListener != null) {
				if (v.getId() == R.id.confirm_ok_btn)
					mOnClickListener.onClick(OK);
				else
					mOnClickListener.onClick(CANCEL);
			}
			MessageTip.this.dismiss();
		}
	};

	/**
	 * 描 述 ： 弹出消息提示框
	 * 创建日期 : 2013-5-31
	 * 作 者 ： lx
	 * 修改日期 :
	 * 修 改 者 ：
	 * @version : 1.0
	 * @param context 上下文
	 * @param message 消息
	 * @return
	 *         提示框实例
	 */
	public static MessageTip alert(Context context, CharSequence message) {
		return alert(context, message, "", "确定");
	}

	/**
	 * 描 述 ：弹出消息提示框
	 * 创建日期 : 2013-5-31
	 * 作 者 ： lx
	 * 修改日期 :
	 * 修 改 者 ：
	 * @version : 1.0
	 * @param context 上下文
	 * @param message 消息
	 * @param title 标题
	 * @param buttonText 按钮显示文本
	 * @return
	 *         提示框实例
	 */
	public static MessageTip alert(Context context, CharSequence message, CharSequence title, CharSequence buttonText) {
		return alert(context, message, title, -1, buttonText);
	}

	/**
	 * 描 述 ：弹出消息提示框
	 * 创建日期 : 2013-5-31
	 * 作 者 ： lx
	 * 修改日期 :
	 * 修 改 者 ：
	 * @version : 1.0
	 * @param context 上下文
	 * @param message 消息
	 * @param title 标题
	 * @param icoId 标题栏图标
	 * @param buttonText 按钮显示文本
	 * @return
	 *         提示框实例
	 */
	public static MessageTip alert(Context context, CharSequence message, CharSequence title, int icoId, CharSequence buttonText) {
		MessageTip tip = new MessageTip(context, R.style.ESGStyle_Dialog_Message);
		tip.setCanceledOnTouchOutside(false);
		tip.m_dialog_type = ALERTDIALOG;
		tip.mTitle = title;
		tip.mIco = icoId;
		tip.mContent = message;
		tip.mOkButtonTxt = buttonText;
		tip.show();
		return tip;
	}

	/**
	 * 描 述 ：弹出确认对话框, 对话框包含 "确定","取消" 两个按钮
	 * 创建日期 : 2013-5-31
	 * 作 者 ： lx
	 * 修改日期 :
	 * 修 改 者 ：
	 * @version : 1.0
	 * @param context 上下文
	 * @param message 提示信息
	 * @param onClickListener 点击回调方法
	 * @return
	 *         提示框实例
	 */
	public static MessageTip confirm(Context context, CharSequence message, OnTipClickListener onTipClickListener) {
		return confirm(context, message, "", onTipClickListener);
	}

	/**
	 * 描 述 ：弹出确认对话框, 对话框包含 "确定","取消" 两个按钮
	 * 创建日期 : 2013-5-31
	 * 作 者 ： lx
	 * 修改日期 :
	 * 修 改 者 ：
	 * @version : 1.0
	 * @param context
	 * @param message 提示信息
	 * @param title 标题栏信息
	 * @param onClickListener 点击回调方法
	 * @return
	 *         提示框实例
	 */
	public static MessageTip confirm(Context context, CharSequence message, CharSequence title, OnTipClickListener onTipClickListener) {
		return confirm(context, message, title, -1, onTipClickListener);
	}

	/**
	 * 描 述 ：弹出确认对话框，对话框包含 "确定","取消" 两个按钮
	 * 创建日期 : 2013-5-31
	 * 作 者 ： lx
	 * 修改日期 :
	 * 修 改 者 ：
	 * @version : 1.0
	 * @param context
	 * @param message 提示信息
	 * @param title 标题栏信息
	 * @param icoId 标题栏图标
	 * @param onClickListener 点击回调方法
	 * @return
	 *         提示框实例
	 */
	public static MessageTip confirm(Context context, CharSequence message, CharSequence title, int icoId, OnTipClickListener onTipClickListener) {
		return confirm(context, "确定", "取消", message, title, icoId, onTipClickListener);
	}

	/**
	 * 描 述 ：弹出确认对话框 ,对话框包含两个按钮，可以自定义按钮显示文本
	 * 创建日期 : 2013-5-31
	 * 作 者 ： lx
	 * 修改日期 :
	 * 修 改 者 ：
	 * @version : 1.0
	 * @param context
	 * @param positiveBtnText 确定按钮文本
	 * @param negativeBtnText 取消按钮文本
	 * @param message 提示信息
	 * @param title 标题栏信息
	 * @param icoId 标题栏图标
	 * @param onClickListener 点击回调方法
	 * @return
	 *         提示框实例
	 */
	public static MessageTip confirm(Context context, CharSequence positiveBtnText, CharSequence negativeBtnText, CharSequence message,
			CharSequence title, int icoId, OnTipClickListener onTipClickListener) {
		MessageTip tip = new MessageTip(context, R.style.ESGStyle_Dialog_Message);
		tip.setCanceledOnTouchOutside(false);
		tip.m_dialog_type = CONFIRMDIALOG;
		tip.mTitle = title;
		tip.mIco = icoId;
		tip.mContent = message;
		tip.mOkButtonTxt = positiveBtnText;
		tip.mCancleButtonTxt = negativeBtnText;
		tip.mOnClickListener = onTipClickListener;
		tip.show();
		return tip;
	}

	/**
	 * 描 述 ：设置对话框尺寸
	 * 创建日期 : 2013-5-30
	 * 作 者 ：lx
	 * 修改日期 :
	 * 修 改 者 ：
	 * @version : 1.0
	 * @param width
	 * @param height
	 */
	public void resetDialogSize(int width, int height) {
		LayoutParams params = getWindow().getAttributes();
		params.width = width;
		params.height = width;
		getWindow().setAttributes(params);
	}

}

