package com.zy.yaoproject.utils;

import android.app.Activity;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Stack;

/**
 * Created by muzi on 2017/10/31.
 * 727784430@qq.com
 */

public class ActivityStackManager {
    private static final String TAG = "ActivityStackManager";
    /**
     * Activity栈
     */
    private Stack<WeakReference<Activity>> mActivityStack;
    private static ActivityStackManager activityStackManager = new ActivityStackManager();

    private ActivityStackManager() {
    }

    /***
     * 获得AppManager的实例
     *
     * @return AppManager实例
     */
    public static ActivityStackManager getInstance() {
        if (activityStackManager == null) {
            activityStackManager = new ActivityStackManager();
        }
        return activityStackManager;
    }

    /***
     * 栈中Activity的数
     *
     * @return Activity的数
     */
    public int stackSize() {
        return mActivityStack.size();
    }

    /***
     * 获得Activity栈
     *
     * @return Activity栈
     */
    public Stack<WeakReference<Activity>> getStack() {
        return mActivityStack;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(WeakReference<Activity> activity) {
        if (mActivityStack == null) {
            mActivityStack = new Stack<>();
        }
        mActivityStack.add(activity);
    }

    /**
     * 删除ac
     *
     * @param activity 弱引用的ac
     */
    public void removeActivity(WeakReference<Activity> activity) {
        if (mActivityStack != null) {
            mActivityStack.remove(activity);
        }
    }

    /***
     * 获取栈顶Activity（堆栈中最后一个压入的）
     *
     * @return Activity
     */
    public Activity getTopActivity() {
        Activity activity = mActivityStack.lastElement().get();
        if (null == activity) {
            return null;
        } else {
            return mActivityStack.lastElement().get();
        }
    }

    /***
     * 通过class 获取栈顶Activity
     *
     * @param cls
     * @return Activity
     */
    public Activity getActivityByClass(Class<?> cls) {
        Activity return_activity = null;
        for (WeakReference<Activity> activity : mActivityStack) {
            if (activity.get().getClass().equals(cls)) {
                return_activity = activity.get();
                break;
            }
        }
        return return_activity;
    }

    /**
     * 结束栈顶Activity（堆栈中最后一个压入的）
     */
    public void killTopActivity() {
        try {
            WeakReference<Activity> activity = mActivityStack.lastElement();
            killActivity(activity);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    /***
     * 结束指定的Activity
     *
     * @param activity
     */
    public void killActivity(WeakReference<Activity> activity) {
        try {
            Iterator<WeakReference<Activity>> iterator = mActivityStack.iterator();
            while (iterator.hasNext()) {
                WeakReference<Activity> stackActivity = iterator.next();
                if (stackActivity.get() == null) {
                    iterator.remove();
                    continue;
                }
                if (stackActivity.get().getClass().getName().equals(activity.get().getClass().getName())) {
                    iterator.remove();
                    stackActivity.get().finish();
                    break;
                }
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    /***
     * 结束指定类名的Activity
     *
     * @param cls
     */
    public void killActivity(Class<?> cls) {
        try {
            ListIterator<WeakReference<Activity>> listIterator = mActivityStack.listIterator();
            while (listIterator.hasNext()) {
                Activity activity = listIterator.next().get();
                if (activity == null) {
                    listIterator.remove();
                    continue;
                }
                if (activity.getClass() == cls) {
                    listIterator.remove();
                    if (activity != null) {
                        activity.finish();
                    }
                    break;
                }
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    /**
     * 结束所有Activity
     */
    public void killAllActivity() {
        try {
            ListIterator<WeakReference<Activity>> listIterator = mActivityStack.listIterator();
            while (listIterator.hasNext()) {
                Activity activity = listIterator.next().get();
                if (activity != null) {
                    activity.finish();
                }
                listIterator.remove();
            }
        } catch (Exception e) {
            AppExit();
            Log.e(TAG, e.getMessage());
        }
    }


    /**
     * 移除除了某个activity的其他所有activity
     *
     * @param cls 界面
     */
    public void killAllActivityExceptOne(Class cls) {
        try {
            for (int i = 0; i < mActivityStack.size(); i++) {
                WeakReference<Activity> activity = mActivityStack.get(i);
                if (activity.get().getClass().equals(cls)) {
                    break;
                }
                if (mActivityStack.get(i) != null) {
                    killActivity(activity);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    /**
     * 移除某个activity之上所有的activity
     *
     * @param cls
     */
    public void killAboveAllActivity(Class cls) {
        try {
            int size = mActivityStack.size();
            for (int i = 1; i <= size; i++) {
                WeakReference<Activity> activity = mActivityStack.lastElement();
                if (activity.get().getClass().equals(cls)) {
                    break;
                }
                Log.d(TAG, activity.get().getClass().getSimpleName());
                if (activity != null) {
                    killActivity(activity);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }


    /**
     * 退出应用程序
     */
    public void AppExit() {
        killAllActivity();
        System.exit(0);
    }

    /**
     * 是否包含指定Activity
     *
     * @param cls
     * @return
     */
    public boolean havedActivity(Class cls) {
        try {
            for (int i = 1; i <= mActivityStack.size(); i++) {
                WeakReference<Activity> activity = mActivityStack.get(mActivityStack.size() - i);
                if (activity.get().getClass().equals(cls)) {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 正序遍历
     */
    public void listStackFromFirst() {
        try {
            ListIterator<WeakReference<Activity>> listIterator = mActivityStack.listIterator();
            while (listIterator.hasNext()) {
                WeakReference<Activity> activity = listIterator.next();
                if (activity != null) {
                    Log.d(TAG, "listStackFromFirst-->" + activity.get().getClass().getSimpleName());
                }
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

    }

    /**
     * 倒序遍历
     */
    public void listStackFromLast() {
        try {
            for (int i = 1; i <= mActivityStack.size(); i++) {
                WeakReference<Activity> activity = mActivityStack.get(mActivityStack.size() - i);
                if (activity != null) {
                    Log.d(TAG, "listStackFromLast-->" + activity.get().getClass().getSimpleName());
                }
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

    }

}
