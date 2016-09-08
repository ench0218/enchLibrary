package com.ench.mylibrary.net;


import com.ench.mylibrary.utils.mLog;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

/**
 * 网络请求类
 * Created by ench on 16/7/5.
 */
public class JsonRequest {

    /**
     * Post请求
     *
     * @param url          接口地址
     * @param param        请求体
     * @param tag          标识请求Tag
     * @param listener     callback
     * @param progressShow 加载时是否显示加载动画
     */
    public static void doPost(String url,
                              Map<String, String> param, final int tag,
                              final OnRequestFinishListener listener, final boolean progressShow) {
        RequestParams params = new RequestParams(url);
        params.setMultipart(true);
        params.setConnectTimeout(5000);
        if (param != null) {
            for (String key : param.keySet()) {
                params.addBodyParameter(key, param.get(key));
            }
        }
        mLog.e("url-------", url);
        mLog.e("params-------", param != null ? param.toString() : "无参数");

        listener.onStartRequest(progressShow);

        x.http().get(params, new Callback.CommonCallback<JSONObject>() {
            @Override
            public void onCancelled(CancelledException arg0) {
            }

            @Override
            public void onError(Throwable arg0, boolean arg1) {
                listener.onErrResponse(arg0);
            }

            @Override
            public void onFinished() {
                listener.onEndRequest(progressShow);
            }

            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onResponse(jsonObject, tag);
            }
        });

    }

    /**
     * get请求
     *
     * @param url          接口地址
     * @param tag          标识请求Tag
     * @param listener     callback
     * @param progressShow 加载时是否显示加载动画
     */
    public static void doGet(String url,
                             final int tag,
                             final OnRequestFinishListener listener, final boolean progressShow) {
        RequestParams params = new RequestParams(url);
        params.setMultipart(true);
        mLog.e("url-------", url);

        listener.onStartRequest(progressShow);

        x.http().get(params, new Callback.CommonCallback<JSONObject>() {
            @Override
            public void onCancelled(CancelledException arg0) {
            }

            @Override
            public void onError(Throwable arg0, boolean arg1) {
                listener.onErrResponse(arg0);
            }

            @Override
            public void onFinished() {
                listener.onEndRequest(progressShow);
            }

            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onResponse(jsonObject, tag);
            }
        });
    }


    /**
     * 网络请求监听
     */
    public interface OnRequestFinishListener {

        void onStartRequest(boolean progressBarShow);

        void onResponse(JSONObject jsonObject, int tag);

        void onErrResponse(Throwable throwable);

        void onEndRequest(boolean progressBarShow);


    }
}
