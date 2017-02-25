package com.zjy.cash.data.model.order;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with android studio
 * Creator :zhoujunyou
 * Date:2017/2/25
 * Time:15:59
 * Desc:
 */

public class Good implements Serializable {
    public int id;// 菜品id
    public String name;// 菜品名称
    public double price;// 菜品单价
    public int left;// 可退菜品数目
    public double totalPrice;// 小计
    public String serial;
    public int type; //是否是套餐 0单菜 1套餐
    public int isMarketPrice; //是否时令菜 0否 1是
    public ArrayList<Detail> detail;// 如果是套餐就表示套餐中菜品，如果不是套餐就是空
    public String itemCode = "";//菜单编码

    public static class Detail implements Serializable {
        public int id;// 套餐中菜品id
        public String name;// 套餐中菜品名称
        public double price;// 套餐中菜品单价
        public int left;// 可退数目
        public String serial;
        public int type; //是否是配料类的  0不是配料是子菜品   1是配料类
        public String detailItemCode = "";
    }
}
