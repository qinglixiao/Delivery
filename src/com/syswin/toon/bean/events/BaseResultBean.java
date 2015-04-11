package com.syswin.toon.bean.events;

import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * 返回结果基础bean
 * 
 * @author 135033 Mc
 */
public class BaseResultBean extends BaseBean<BaseResultBean> {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private int result;

	private String msg;

	/**
	 * 更新时间
	 */
	private long operateTime;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public long getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(long operateTime) {
		this.operateTime = operateTime;
	}

	public boolean isSuccess(){
	    return getResult() ==0;
	}
	
	@Override
	public BaseResultBean parseJSON(JSONObject jsonObj) {
		return null;
	}

	@Override
	public JSONObject toJSON() {
		return null;
	}

	@Override
	public BaseResultBean cursorToBean(Cursor cursor) {
		return null;
	}

	@Override
	public ContentValues beanToValues() {
		return null;
	}

}
