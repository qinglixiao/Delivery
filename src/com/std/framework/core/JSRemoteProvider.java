package com.std.framework.core;

import java.util.ArrayList;

import com.library.util.DataConvert;
import com.library.util.LibUtil;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;


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

	public JSRemoteProvider(Context context) {
		this.context = context;
	}

	@JavascriptInterface
	public void getClickedContolLabel_JSReturn(String result) {
		
	}

	@JavascriptInterface
	public String getContact() {
		// 查询的字段  
		String[] projection = { Phone._ID, Phone.DISPLAY_NAME, Phone.NUMBER };
		Cursor cursor = context.getContentResolver().query(Phone.CONTENT_URI, projection, null, null, null);
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		while (cursor.moveToNext()) {
			Contact contact = new Contact();
			contact.name = cursor.getString(1);
			contact.no = cursor.getString(2);
			contacts.add(contact);
		}
		return DataConvert.toJSONString(contacts);
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

	public class Contact {
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getNo() {
			return no;
		}

		public void setNo(String no) {
			this.no = no;
		}

		public String name;
		public String no;
	}
}
