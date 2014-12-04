package com.std.framework.comm;

import java.util.Stack;

import android.app.Activity;

public class STDActivityManager {
	private static STDActivityManager instance;
	private Stack<Activity> mActivities;
	
	private STDActivityManager(){
		
	}
	
	public static STDActivityManager getInstance(){
		if(instance == null){
			synchronized(STDActivityManager.class){
				if(instance == null){
					instance = new STDActivityManager();
					instance.mActivities = new Stack<Activity>();
				}
			}
		}
		return instance;
	}
	
	public void add(Activity activity){
		mActivities.add(activity);
	}
	
	public void remove(Activity activity){
		mActivities.remove(activity);
	}
	
	public Activity getCurrent(){
		return mActivities.lastElement();
	}
	
	public Stack<Activity> getStackActivity(){
		return mActivities;
	}
}
