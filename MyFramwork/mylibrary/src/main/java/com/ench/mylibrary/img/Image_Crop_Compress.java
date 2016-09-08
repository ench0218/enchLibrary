package com.ench.mylibrary.img;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * 图片裁剪
 * 必须在Intent之前完成选择器配置
 * ------------------------------------------------------
 * compile 'top.zibin:Luban:1.0.7'//图片压缩
 * compile 'com.lzy.widget:imagepicker:+'//裁剪 图片
 * compile 'io.reactivex:rxandroid:1.2.1'//裁剪 图片（与compile 'com.lzy.widget:imagepicker:+'一起使用）
 * compile 'com.github.bumptech.glide:glide:3.7.0'//裁剪图的ImageLoader
 * <p>
 * Created by ench on 16/8/11.
 */
public class Image_Crop_Compress {
    private Context context;
    private ImagePicker imagePicker;
    private boolean isCircle;
    CompressListener compressListener;

    /**
     * 需要裁剪时调用
     *
     * @param context
     * @param isCircle 是否裁剪圆形 true:为圆形反之矩形
     */
    public Image_Crop_Compress(Context context, boolean isCircle) {
        this.context = context;
        this.isCircle = isCircle;
        init();
        if (isCircle) {
            imagePicker.setStyle(CropImageView.Style.CIRCLE);  //裁剪框的形状
            imagePicker.setOutPutX(500);//保存文件的宽度。单位像素
            imagePicker.setOutPutY(500);//保存文件的高度。单位像素
            imagePicker.setFocusWidth(500);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
            imagePicker.setFocusHeight(500);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        } else {
            imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
            imagePicker.setOutPutX(1280);//保存文件的宽度。单位像素
            imagePicker.setOutPutY(720);//保存文件的高度。单位像素
            imagePicker.setFocusWidth(1280);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
            imagePicker.setFocusHeight(720);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        }
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
    }

    /**
     * 全图保存时调用此构造方法
     *
     * @param context
     */
    public Image_Crop_Compress(Context context) {
        this.context = context;
        init();
        imagePicker.setCrop(false);        //允许裁剪（单选才有效）
    }

    private void init() {
        imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new ImageCROPLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setMultiMode(false);
    }


    /**
     * 截图都情况的图片裁剪和压缩处理
     *
     * @param path 图片的路径
     * @param w    输出图片给的宽
     * @param h    输出图片给的高
     */
    public void getCropImageByte(String path, int w, int h) {
//        final BitmapFactory.Options opts = new BitmapFactory.Options();
//        // 设置为ture只获取图片大小
//        opts.inJustDecodeBounds = false;
//        opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
//        // 返回为空
//        BitmapFactory.decodeFile(path, opts);
//        int width = opts.outWidth;
//        int height = opts.outHeight;
//        float scaleWidth = 0.f, scaleHeight = 0.f;
//        if (width > w || height > h) {
//            // 缩放
//            scaleWidth = ((float) width) / w;
//            scaleHeight = ((float) height) / h;
//        }
//        opts.inJustDecodeBounds = false;
//        float scale = Math.max(scaleWidth, scaleHeight);
//        opts.inSampleSize = (int) scale;
        //进行压缩
        Luban.get(context).load(new File(path)).putGear(Luban.THIRD_GEAR).setCompressListener(new OnCompressListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(File file) {
                //把图片转为byte数组
//                Bitmap bitmap = BitmapFactory.decodeFile(file.getPath(), opts);
                Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                //接口回调
                compressListener.onCompressSuccess(baos.toByteArray());
                //保存到SD卡（测试使用，后期不需要调用）
                try {
                    if (isCircle) {
                        saveBitmapToFile(bitmap, Environment.getExternalStorageDirectory().getPath() + "/BaBaSuper/Crop/circle_img.jpg");
                    } else {
                        saveBitmapToFile(bitmap, Environment.getExternalStorageDirectory().getPath() + "/BaBaSuper/Crop/rectangle_img.jpg");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        }).launch();

    }

    /**
     * 全图直接压缩
     *
     * @param path 图片路径
     */
    public void getImageByte(String path) {
        Luban.get(context).load(new File(path)).putGear(Luban.THIRD_GEAR).setCompressListener(new OnCompressListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(File file) {
                //把图片转为byte数组
                Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                //接口回调
                compressListener.onCompressSuccess(baos.toByteArray());
                //保存到SD卡（测试使用，后期不需要调用）
                try {
                    saveBitmapToFile(bitmap, Environment.getExternalStorageDirectory().getPath() + "/BaBaSuper/all_img.jpg");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        }).launch();
    }

    public interface CompressListener {
        void onCompressSuccess(byte[] bytes);
    }

    public void setLunbanListener(CompressListener compressListener) {
        this.compressListener = compressListener;
    }

    /**
     * Save Bitmap to a file.保存图片到SD卡。
     *
     * @param bitmap
     * @return error message if the saving is failed. null if the saving is
     * successful.
     * @throws IOException
     */
    public static void saveBitmapToFile(Bitmap bitmap, String _file)
            throws IOException {
        BufferedOutputStream os = null;
        try {
            File file = new File(_file);
            // String _filePath_file.replace(File.separatorChar +
            // file.getName(), "");
            int end = _file.lastIndexOf(File.separator);
            String _filePath = _file.substring(0, end);
            File filePath = new File(_filePath);

            if (filePath.exists()) {
                filePath.delete();
            }
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            file.createNewFile();
            os = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, os);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                }
            }
        }
    }


}
