package com.ench.mylibrary.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ench.mylibrary.net.JsonRequest;
import com.ench.mylibrary.net.ProgressBarDialog;

/**
 * Fragment的基类
 * Created by ench on 16/7/7.
 */
public abstract class BaseFragment extends Fragment implements JsonRequest.OnRequestFinishListener {

    private Activity activity;
    private ProgressBarDialog progressBarDialog;

    /**
     * 初始化View，onCreateView中调用
     */
    public abstract View initView();

    /**
     * 初始化数据，onActivityCreated中调用
     */
    public void initDate() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        progressBarDialog = createProgressBarDialog();
        return initView();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDate();
    }


    @Override
    public void onStartRequest(boolean progressBarShow) {
        progressBarDialog.show();
    }

    @Override
    public void onEndRequest(boolean progressBarShow) {
        progressBarDialog.dismiss();
    }

    /**
     * 创建加载时Dialog
     *
     * @return
     */
    public ProgressBarDialog createProgressBarDialog() {
        return new ProgressBarDialog(activity);
    }
}
