package com.std.framework.comm;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * 
 * 描      述 ：http请求操作端
 * 创建日期 ： 2014-4-22
 * 作      者 ： lx
 * 修改日期 ： 
 * 修  改 者 ：
 * @version    ： 1.0
 */
public class STDHttpClient {
	private static AsyncHttpClient mAsyncHttpClient = new AsyncHttpClient();
	
	public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		mAsyncHttpClient.get(url, params, responseHandler);
	}

	public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		mAsyncHttpClient.post(url, params, responseHandler);
	}
	
	public static void postJson(Context context,String url, StringEntity stringEntity, AsyncHttpResponseHandler responseHandler) {
		mAsyncHttpClient.post(context, url, stringEntity,"application/json;charset=UTF-8",responseHandler);
	}
	
	public static void post(Context context,String url, HttpEntity httpEntity,String contentType,AsyncHttpResponseHandler responseHandler){
		mAsyncHttpClient.post(context, url, httpEntity, contentType, responseHandler);
	}
	
	
}
