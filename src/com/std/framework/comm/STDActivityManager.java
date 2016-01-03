package com.std.framework.comm;

import java.lang.ref.WeakReference;
import java.util.Stack;
import java.util.WeakHashMap;

import android.app.Activity;

public class STDActivityManager {
    private static STDActivityManager instance;
    private Stack<WeakReference<Activity>> mActivities;

    private STDActivityManager() {

    }

    public static STDActivityManager getInstance() {
        if (instance == null) {
            synchronized (STDActivityManager.class) {
                if (instance == null) {
                    instance = new STDActivityManager();
                    instance.mActivities = new Stack<>();
                }
            }
        }
        return instance;
    }

    public void add(Activity activity) {
        if (activity != null)
            mActivities.add(new WeakReference<>(activity));
    }

    public void remove(Activity activity) {
        mActivities.remove(activity);
        WeakReference<Activity> temp = null;
        for(WeakReference<Activity> reference : mActivities){
            if(reference.get() != null && reference.get() == activity) {
                temp = reference;
                break;
            }
        }
        if(temp != null){
            mActivities.remove(temp);
        }
    }

    public Activity getCurrent() {
        WeakReference<Activity> top = mActivities.lastElement();
        if (top != null && top.get() != null)
            return top.get();
        return null;
    }

    public void finishAll(){
        for(WeakReference<Activity> reference : mActivities){
            if(reference.get() != null)
                reference.get().finish();
        }
    }

}
