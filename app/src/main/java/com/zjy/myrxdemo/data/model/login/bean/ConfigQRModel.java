package com.zjy.myrxdemo.data.model.login.bean;

import java.io.Serializable;

public class ConfigQRModel implements Serializable {

    /**
     * id : 134
     * shop_id : 47113
     * title : 扫码试试
     * start_time : 2016-05-17 17:11:03
     * end_time : 2017-05-19 17:11:07
     * position : 2
     * ticket_type : 4
     * qr_type : 2
     * qr_url : http://weixin.qq.com/q/kHWQb0fm0vuruoLqhlmd
     * text : 扫一扫
     * create_time : 2016-05-18 17:11:25
     * state : 1
     */

    public long id;
    public int shop_id;
    public String title;
    public String start_time;
    public String end_time;
    public int position;
    public int ticket_type;// 小票类型			// 1排号单,2预点菜单，3付款码,4收款单,5对账单,6退款单,7收银统计,8等位优惠
    public int qr_type;
    public String qr_url;// 1图片 2url
    public String text;
    public String create_time;
    public int state;
}