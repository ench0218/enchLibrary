package com.ench.mylibrary.net;

import android.widget.ImageView;

import com.ench.mylibrary.R;

import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * xUtils加载网络图片
 * Created by ench on 16/7/5.
 */
public class ImageLoader {
    /**
     * @param imageView 显示图片的控件
     * @param url       图片URL
     */
    public static void display(ImageView imageView, String url) {
        ImageOptions builder = new ImageOptions.Builder()
                //设置图片裁剪类型
                .setImageScaleType(ImageView.ScaleType.FIT_XY)
                //设置默认图片
                .setLoadingDrawableId(R.drawable.ic_launcher)
                //设置加载失败后的图片
                .setFailureDrawableId(R.drawable.ic_launcher2)
                .build();
        //进行绑定
        x.image().bind(imageView, url, builder);
    }
}
