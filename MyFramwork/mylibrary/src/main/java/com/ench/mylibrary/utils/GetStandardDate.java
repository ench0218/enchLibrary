package com.ench.mylibrary.utils;


import com.ench.mylibrary.MyApplication;

/**
 * 处理时间===距离当前时间多久
 * 将时间戳转为代表"距现在多久之前"的字符串
 * timeStr 时间戳
 * Created by ench on 16/6/23.
 */
public class GetStandardDate {
    public static String getStandardDate(String timeStr) {

        StringBuffer sb = new StringBuffer();

        long t = Long.parseLong(timeStr);
        long time = System.currentTimeMillis() - (t * 1000);
        long mill = (long) Math.ceil(time / 1000);//秒前

        long minute = (long) Math.ceil(time / 60 / 1000.0f);// 分钟前

        long hour = (long) Math.ceil(time / 60 / 60 / 1000.0f);// 小时

        long day = (long) Math.ceil(time / 24 / 60 / 60 / 1000.0f);// 天前

        if (day - 1 > 0) {
//            sb.append(day + "天");

            sb.append(MyApplication.getInstance().dateUtil.time_date(timeStr));
        } else if (hour - 1 > 0) {
            if (hour >= 24) {
                sb.append("1天前");
            } else {
                sb.append(hour + "小时前");
            }
        } else if (minute - 1 > 0) {
            if (minute == 60) {
                sb.append("1小时前");
            } else {
                sb.append(minute + "分钟前");
            }
        } else if (mill - 1 > 0) {
            if (mill == 60) {
                sb.append("1分钟前");
            } else {
                sb.append(mill + "秒前");
            }
        } else {
            sb.append("刚刚");
        }
        if (!sb.toString().equals("刚刚")) {
//            sb.append("前");
        }
        return sb.toString();
    }
}
