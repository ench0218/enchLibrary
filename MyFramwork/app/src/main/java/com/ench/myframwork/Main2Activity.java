package com.ench.myframwork;

import android.os.Bundle;

import com.ench.mylibrary.activity.BaseFragmentActivity;

import org.json.JSONObject;

public class Main2Activity extends BaseFragmentActivity {

    @Override
    public void initView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    @Override
    public void onResponse(JSONObject jsonObject, int tag) {

    }



}
