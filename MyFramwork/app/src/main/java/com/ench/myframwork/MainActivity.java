package com.ench.myframwork;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ench.mylibrary.activity.BaseFragmentActivity;
import com.ench.mylibrary.net.JsonRequest;
import com.ench.mylibrary.utils.ToastUtils;
import com.ench.mylibrary.utils.mLog;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends BaseFragmentActivity implements View.OnClickListener {

    private TextView iv;


    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        iv = (TextView) findViewById(R.id.iv);
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }


    @Override
    public void onResponse(JSONObject jsonObject, int tag) {
        iv.setText(jsonObject.toString());
        mLog.e(jsonObject.toString());
        ToastUtils.showShortToast(this, jsonObject.toString());
    }

    @Override
    public void onErrResponse(Throwable throwable) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                JsonRequest.doPost("http://www.babasuper.com/index.php/Home/AppUserCenter/getUnorderedGoodsOrders", null, 1, this, true);

//                try {
//                    addByUrl();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                break;
            case R.id.btn2:
                JsonRequest.doPost("http://www.babasuper.com/index.php/Home/AppUserCenter/getUnorderedGoodsOrders", null, 1, this, true);
                break;
        }
    }


    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            Log.e("hahahah", "jiesu ----------" + (String) msg.obj);
            Toast.makeText(MainActivity.this, (String) msg.obj, Toast.LENGTH_LONG).show();
        }
    };

    //HttpUrlConnection请求
    public void addByUrl() throws Exception {
        Thread t = new Thread() {
            @Override
            public void run() {
                //提交的数据需要经过url编码，英文和数字编码后不变
                @SuppressWarnings("deprecation")
                String path = "http://www.babasuper.com/index.php/Home/AppUserCenter/getUnorderedGoodsOrders";
                Log.e("hahahah", "kaishi ----------");
                try {
                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);

                    //拼接出要提交的数据的字符串
                    String data = "";
                    //添加post请求的两行属性
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    conn.setRequestProperty("Content-Length", data.length() + "");

                    //设置打开输出流
                    conn.setDoOutput(true);
                    //拿到输出流
                    OutputStream os = conn.getOutputStream();
                    //使用输出流往服务器提交数据
                    os.write(data.getBytes());
                    if (conn.getResponseCode() == 200) {
                        InputStream is = conn.getInputStream();
                        String text = Utils.getTextFromStream(is);

                        Message msg = handler.obtainMessage();
                        msg.obj = text;
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        t.start();

    }

}
