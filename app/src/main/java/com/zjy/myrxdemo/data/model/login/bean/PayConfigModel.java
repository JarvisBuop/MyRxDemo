package com.zjy.myrxdemo.data.model.login.bean;

import java.io.Serializable;

public class PayConfigModel implements Serializable {
    /**
     * weixin : 1
     * alipay : 1
     * baidu : 2
     * unionpay : 0
     * boxpay : 1
     * yishi : 0
     * huika : 1
     * lakala : 0
     */

    public int weixin = 0;
    public int alipay = 0;
    public int baidu = 0;
    public int unionpay = 0;
    public int boxpay = 0;
    public int yishi = 0;
    public int huika = 0;
    public int lakala = 0;
    public int query_before = 1;//1 可以 0 不可以

}