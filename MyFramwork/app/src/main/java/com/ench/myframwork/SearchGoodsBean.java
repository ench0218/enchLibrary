package com.ench.myframwork;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ench-Wu on 2016/5/7 0007.
 */
public class SearchGoodsBean {

    /**
     * flag : 1
     * msg : 获取订单成功
     * list : [{"user_id":1821,"goods_code":"1462609846-1822","goods_name":"啊啊啊啊啊啊凤飞飞","goods_type":"啊啊啊啊啊","goods_traffic":"25","goods_bulk":"","goods_transport_type":"","goods_pay_way":"","start_province":"湖南省","start_city":"张家界市","start_town":"","start_full_address":"湖南省张家界市","end_province":"广东省","end_city":"深圳市","end_town":"","end_full_address":"广东省深圳市","fare":"1000","goods_notes":"","create_time":1462609846,"end_time":"","goods_start_date":1463587200,"goods_id":4,"real_name":"","mobile":"","start_row":"0","page_size":"10"}]
     */

    public int flag;
    public String msg;
    public int length;
    /**
     * user_id : 1821
     * goods_code : 1462609846-1822
     * goods_name : 啊啊啊啊啊啊凤飞飞
     * goods_type : 啊啊啊啊啊
     * goods_traffic : 25
     * goods_bulk :
     * goods_transport_type :
     * goods_pay_way :
     * start_province : 湖南省
     * start_city : 张家界市
     * start_town :
     * start_full_address : 湖南省张家界市
     * end_province : 广东省
     * end_city : 深圳市
     * end_town :
     * end_full_address : 广东省深圳市
     * fare : 1000
     * goods_notes :
     * create_time : 1462609846
     * end_time :
     * goods_start_date : 1463587200
     * goods_id : 4
     * real_name :
     * mobile :
     * start_row : 0
     * page_size : 10
     */

    public List<ListBean> list;


    public static class ListBean implements Serializable {
        public int user_id;
        public String goods_code;
        public String goods_name;

        @Override
        public String toString() {
            return "ListBean{" +
                    "user_id=" + user_id +
                    ", goods_code='" + goods_code + '\'' +
                    ", goods_name='" + goods_name + '\'' +
                    ", goods_type='" + goods_type + '\'' +
                    ", goods_traffic='" + goods_traffic + '\'' +
                    ", goods_bulk='" + goods_bulk + '\'' +
                    ", goods_transport_type='" + goods_transport_type + '\'' +
                    ", goods_pay_way='" + goods_pay_way + '\'' +
                    ", start_province='" + start_province + '\'' +
                    ", start_city='" + start_city + '\'' +
                    ", start_town='" + start_town + '\'' +
                    ", start_full_address='" + start_full_address + '\'' +
                    ", end_province='" + end_province + '\'' +
                    ", end_city='" + end_city + '\'' +
                    ", end_town='" + end_town + '\'' +
                    ", end_full_address='" + end_full_address + '\'' +
                    ", fare='" + fare + '\'' +
                    ", goods_notes='" + goods_notes + '\'' +
                    ", create_time='" + create_time + '\'' +
                    ", end_time='" + end_time + '\'' +
                    ", goods_start_date='" + goods_start_date + '\'' +
                    ", goods_id='" + goods_id + '\'' +
                    ", real_name='" + real_name + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", start_row='" + start_row + '\'' +
                    ", page_size='" + page_size + '\'' +
                    ", head_image='" + head_image + '\'' +
                    ", expire_time=" + expire_time +
                    '}';
        }

        public String goods_type;
        public String goods_traffic;
        public String goods_bulk;
        public String goods_transport_type;
        public String goods_pay_way;
        public String start_province;
        public String start_city;
        public String start_town;
        public String start_full_address;
        public String end_province;
        public String end_city;
        public String end_town;
        public String end_full_address;
        public String fare;
        public String goods_notes;
        public String create_time;
        public String end_time;
        public String goods_start_date;
        public String goods_id;
        public String real_name;
        public String mobile;
        public String start_row;
        public String page_size;
        public String head_image;
        public int expire_time;
    }
}
