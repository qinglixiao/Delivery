package me.std.base.comm;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.util.Stack;

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
        for (WeakReference<Activity> reference : mActivities) {
            if (reference.get() != null && reference.get() == activity) {
                temp = reference;
                break;
            }
        }
        if (temp != null) {
            mActivities.remove(temp);
        }
    }

    public Activity top() {
        WeakReference<Activity> top = mActivities.lastElement();
        if (top != null && top.get() != null)
            return top.get();
        return null;
    }

    public void finishAll() {
        for (WeakReference<Activity> reference : mActivities) {
            if (reference.get() != null)
                reference.get().finish();
        }
    }

}
