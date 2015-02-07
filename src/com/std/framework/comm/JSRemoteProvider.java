package com.std.framework.comm;

import android.content.Context;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.library.util.LibUtil;

/**
 * 
 * 描          述 ：提供JS调用的方法类
 * 创建日期  : 2015-1-6
 * 作           者 ： lx
 * 修改日期  : 
 * 修   改   者 ：
 * @version   : 1.0
 */
public class JSRemoteProvider {
	private Context context;
	private onCallBackListener onCallBackListener;
	private Bundle bundle;

	public JSRemoteProvider(Context context) {
		this.context = context;
	}
	
	public void setOnCallBackListener(onCallBackListener onCallBackListener){
		this.onCallBackListener = onCallBackListener;
	}
	
	public void setBundle(Bundle bundle){
		this.bundle = bundle;
	}

	@JavascriptInterface
	public void getClickedContolLabel_JSReturn(String result) {
		
	}

	@JavascriptInterface
	public int add(int a, int b) {
		return a + b;
	}

	@JavascriptInterface
	public String getPhoneNumber() {
		return LibUtil.getPhoneNumber(context);
	}

	public static void dom(WebView webView) {
		webView.loadUrl("javascript:dom()");
	}

	public static void bom(WebView webView, int i) {
		webView.loadUrl("javascript:bom(" + i + ")");
	}
	
	public interface onCallBackListener{
		void onCallBack(Bundle bundle);
	}
}
