package com.zjy.cash.data.model.order;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with android studio
 * Creator :zhoujunyou
 * Date:2017/2/25
 * Time:15:57
 * Desc:
 */

public class PayOrder implements Serializable {
    public int id;// 订单id
    public String createDate;// 下单时间
    public String nick;// 用户名（搜索）
    public String detail;// 订单菜品概览（搜索）
    public int type;// 订单类型 目前1代表点菜的，3代表扫码支付的
    public String mobile;// 手机号
    public double total;// 订单列表中商品个数，订单详情中支付金额
    /**
     * 订单状态
     * （旧 1下单 2正在支付 3 已收银 4退款中 5已退款 6已就餐）
     * （新 1待支付 2已支付(未就餐) 3已支付(已就餐) 4已支付(有退款) 5已退款 6支付失败）
     **/
    public int state;
    public String orderState;// 订单状态
    public int left;// 可退菜品数目
    public double leftTotal;// 可退金额 即实际支付金额
    public double payBack;// 已退金额（详情）
    public ArrayList<Good> goods;// 订单中菜品（详情）
    public double totalall;// 退菜之前的应收总额（详情）
    public String print;// 四位打印号（搜索）
    public boolean printRefund = false;
    public double subsidy; // 系统补贴
    public double companysubsidy; // 商家补贴
    public int errno;
    public String errmsg;
    public String lastTime; // 交易时间
    public int paytype;
    public int postype; // 1/2:商户端 4：手机端
    public int payBackNum; // 退款次数

    public float amount;// 交易金额
    public int from;
    public String payorderid1; // 支付流水号
    public String custNum; // 排队号
    public int waitNum; // 等待人数
    public String tableNo;//桌名
    public int people;//就餐人数
    private String ORDER_S_ENTER = "\n";


    public double payMoney;//用户实付金额
    public double receipt; //商户实收金额
    public double mwDiscount; //美味优惠金额
    public double shopDiscount; //美味商家优惠
    /*public double extDiscount; //支付平台商家优惠
    public double aliDiscount; //支付宝优惠*/
    public double memberDiscount; //会员积分优惠
    public double couponAmount; //卡券支付金额
    public double privilegeDiscount; //特权优惠金额

    public double  platformTotalDiscount;//总优惠   单位:元
    public double  platformDiscount;//支付平台优惠    单位:元
    public double  platformMerchantDiscount;//支付平台商家优惠    单位:元
    public int giveMemberScore; //本次消费增加积分

    public String memberNo = "";//会员卡号

    public String orderFailReason; //交易失败原因
    public int allowRefund;//是否允许退款1允许 0不允许

    /****
     * 点菜宝
     ******/
    public String orderDesc;//点菜宝订单描述
    public String dcbOrderSeq;//点菜宝订单唯一序列号

    public int printType;//区分商户及客户联
    public int orderType;//1 预点单 2 对账单  3 退款单
}
