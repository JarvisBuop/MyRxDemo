package com.zjy.cash.data.model.third;

import java.io.Serializable;

/**
 * Created with android studio
 * Creator:zhou.junyou@puscene.com
 * DATE:16/9/20
 * TIME:下午2:01
 * DESC:第三放调用收银,存储数据的Model
 */
public class ThirdCashModel implements Serializable {
    public ThirdCashModel() {
    }

    public boolean isOutPay=false;  //是否第三方支付 （不需要第三方传入）
    public String thirdId="";  //调用方订单ID，支付成功后会返回 （可选字段）
    public double payMoney=0;  //收银金额  （**必填字段**）
    public int payTool=-1;  //支付工具  （可选字段，1微信，2支付宝；不传时两种支付方式都可以，如果选定支付方式必须用选中的方式支付）
    public String tableName="";  //桌号信息  （可选字段，传入后会在界面上显示）
    public String mwDianCaiId="";  //点菜订单的序列号，支付时这个序列号会传给后台，用于清台
    public int callType=0;  //aidl 1（目前西贝在用）,mix 2（目前美小二在用）,other 3
    public String orderId =""; //美小二传过来的订单号





}