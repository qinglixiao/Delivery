package com.std.framework.fragment;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.std.framework.R;

public class Html5NativeCommunicationFragment extends Fragment {
	private View view;
	private WebView webView = null;
	private Handler handler = new Handler();
	private Button button = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_html5_native, null);
		initWebView();
		return view;
	}
	
	private void initWebView() {
		button = (Button) view.findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				//调用javascript中的方法，传入string数据  
				webView.loadUrl("javascript:getFromAndroid('the data is from android!')");
			}
		});

		webView = (WebView) view.findViewById(R.id.webview);

		//把本类的一个实例添加到js的全局对象window中，  
		//这样就可以使用window.injs来调用它的方法  
		webView.addJavascriptInterface(new JSCallBack(), "injs");

		//设置支持JavaScript脚本 
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		//设置可以访问文件  
		webSettings.setAllowFileAccess(true);
		//设置支持缩放  
		webSettings.setBuiltInZoomControls(true);

		webSettings.setDatabaseEnabled(true);
		String dir = getActivity().getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
		webSettings.setDatabasePath(dir);

		//使用localStorage则必须打开  
		webSettings.setDomStorageEnabled(true);

		webSettings.setGeolocationEnabled(true);
		//webSettings.setGeolocationDatabasePath(dir);  

		//设置WebViewClient  
		webView.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
			}

			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
			}
		});

		//设置WebChromeClient  
		webView.setWebChromeClient(new WebChromeClient() {
			//处理javascript中的alert  
			public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
				//构建一个Builder来显示网页中的对话框  
				Builder builder = new Builder(getActivity());
				builder.setTitle("Alert");
				builder.setMessage(message);
				builder.setPositiveButton(android.R.string.ok, new AlertDialog.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						result.confirm();
					}
				});
				builder.setCancelable(false);
				builder.create();
				builder.show();
				return true;
			};

			//处理javascript中的confirm
			public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
				Builder builder = new Builder(getActivity());
				builder.setTitle("confirm");
				builder.setMessage(message);
				builder.setPositiveButton(android.R.string.ok, new AlertDialog.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						result.confirm();
					}
				});
				builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						result.cancel();
					}
				});
				builder.setCancelable(false);
				builder.create();
				builder.show();
				return true;
			};

			@Override
			//设置网页加载的进度条  
			public void onProgressChanged(WebView view, int newProgress) {
				getActivity().getWindow().setFeatureInt(Window.FEATURE_PROGRESS, newProgress * 100);
				super.onProgressChanged(view, newProgress);
			}

			//设置应用程序的标题title  
			public void onReceivedTitle(WebView view, String title) {
				getActivity().setTitle(title);
				super.onReceivedTitle(view, title);
			}

			public void onExceededDatabaseQuota(String url, String databaseIdentifier, long currentQuota, long estimatedSize,
					long totalUsedQuota, WebStorage.QuotaUpdater quotaUpdater) {
				quotaUpdater.updateQuota(estimatedSize * 2);
			}

			public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
				callback.invoke(origin, true, false);
				super.onGeolocationPermissionsShowPrompt(origin, callback);
			}

			public void onReachedMaxAppCacheSize(long spaceNeeded, long totalUsedQuota, WebStorage.QuotaUpdater quotaUpdater) {
				quotaUpdater.updateQuota(spaceNeeded * 2);
			}
		});
		// 覆盖默认后退按钮的作用，替换成WebView里的查看历史页面    
		webView.setOnKeyListener(new View.OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
						webView.goBack();
						return true;
					}
				}
				return false;
			}
		});
		webView.loadUrl("file:///android_asset/index.html");
	}

	final class InJavaScript {
		public void runOnAndroidJavaScript(final String str) {
			handler.post(new Runnable() {
				public void run() {
					TextView show = (TextView)view.findViewById(R.id.textview);
					show.setText(str);
				}
			});
		}
	}
	
	private void javaCallJSTest(){
		String js_method_1 = "javascript:getFromAndroid('+the data is from android!')";
		webView.loadUrl(js_method_1);
	}
	
	public class JSCallBack{
		@JavascriptInterface
		public String show(String str){
			return str;
		}
		@JavascriptInterface
		public void showResult(String result){
			
		}
	}
}
