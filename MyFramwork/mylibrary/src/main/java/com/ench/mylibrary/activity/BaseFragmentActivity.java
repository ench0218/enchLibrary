package com.ench.mylibrary.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ench.mylibrary.MyApplication;
import com.ench.mylibrary.net.JsonRequest;
import com.ench.mylibrary.net.ProgressBarDialog;

/**
 * Activity基类
 * Created by ench on 16/7/5.
 */

public abstract class BaseFragmentActivity extends AppCompatActivity implements JsonRequest.OnRequestFinishListener {

    public ProgressBarDialog dialog;

    /**
     * 初始化View
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public void initDate() {
    }

    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(this);
        dialog = createProgressBarDialog();
        initView();
        initDate();
    }

    /**
     * 开始网络请求
     *
     * @param progressBarShow 是否显示加载动画
     */
    @Override
    public void onStartRequest(boolean progressBarShow) {
        if (progressBarShow)
            dialog.show();
    }

    /**
     * 网络请求结束
     *
     * @param progressBarShow 是否显示加载动画
     */
    @Override
    public void onEndRequest(boolean progressBarShow) {
        if (progressBarShow)
            dialog.dismiss();
    }

    @Override
    public void onErrResponse(Throwable throwable) {
        dialog.dismiss();
    }

    /**
     * 创建加载时Dialog
     *
     * @return
     */
    public ProgressBarDialog createProgressBarDialog() {
        return new ProgressBarDialog(this);
    }


}
