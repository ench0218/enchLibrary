package com.ench.mylibrary;

import android.app.Activity;
import android.app.Application;

import com.ench.mylibrary.utils.DateUtil;
import com.ench.mylibrary.utils.mLog;

import org.xutils.x;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ench on 16/7/5.
 */
public class MyApplication extends Application {
    /**
     * 用于记录存放了哪些activity，退出时结束全部activity
     */
    private List<Activity> mList = new LinkedList<Activity>();


    /**
     * 单例对象
     */
    private static MyApplication mInstance;
    public DateUtil dateUtil;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
        mInstance = this;
        //初始化时间格式化
        dateUtil = new DateUtil();
        //是否打印LOG  false为不打印
        mLog.isDebug = true;

    }


    /**
     * 返回单例对象
     *
     * @return MyApplication
     */
    public static synchronized MyApplication getInstance() {
        if (null == mInstance) {
            mInstance = new MyApplication();
        }
        return mInstance;
    }

    /**
     * 把activity添加到集合
     */
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    /**
     * 退出所有的activity
     */
    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
}
