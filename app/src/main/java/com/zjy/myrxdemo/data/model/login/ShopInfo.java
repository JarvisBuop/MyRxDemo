package com.zjy.myrxdemo.data.model.login;

import com.zjy.myrxdemo.data.model.login.bean.WaitDisc;
import com.zjy.myrxdemo.data.model.login.bean.WaitDisc2;
import com.zjy.myrxdemo.data.model.login.bean.Wave;

import java.io.Serializable;

public class ShopInfo implements Serializable {

	private static final long serialVersionUID = 2L;
	public String shop_id;
	public String shop_name;
	public String telphone;
	public String callTail;// url
	public Wave[] custWave;// url
	public WaitDisc[] wait_disc;
	public String callPref;// url
	public String nowAd;// 系统广告
	public String Notice; // 过号提醒
	public String QRBase; // 二维码
	public boolean bEnablePhone;
	public boolean bHideWaitN;
	public boolean bHideTotal;
	public String LogoUrl;
	public String custWaveZip;
	public String sessionId;
	public String ordernotice;
	public String specify;// 备注
	public boolean bHideWaitNOnTV;
	public boolean bBarcode;
	public String tvRemark;
	public String[] NowAPs;
	public String specifyMore;// 解码后
	public String specifySingleLine;// 去除换行
	public String pmstart;
	public String[] ResvNum;
	public String[] unLukyNum;
	public WaitDisc2[] wait_disc2;
	public boolean bBookEn;// 是否可预订0否1可
	public String bookLastOp;
	public String wxname;
	public String queryPref;
	public int callway;
	public int printway;
	public String adNew; // 打印的广告信息
	public boolean bMemEn;// 是否支持会员 0关闭 1开启
	public boolean bOrderEn;// 预点单打印
	public boolean bQueueEn;//是否支持排队
	public boolean bPayEn;//是否支持 支付
	public boolean bCouponEn;//是否支持卡券
	public boolean bCashEn;//是否支持收款
	public boolean bDanmuEn;//是否支持弹幕
	public boolean bPreOrderEn;// 是否支持预约取号
	public boolean bDishEn;//是否支持显示菜单
	public boolean bShopVip;//侧边栏是否支持会员
	public boolean bMemberEn;//是否开通会员功能(侧边栏)
	public boolean bTableSearch;
	public String adNewSingleLine;// 去除换行
	public boolean isDefaultPmStart;//是否是默认的午市晚市分隔点
	public boolean bPauseEn;
	public String amstart;
	public int printstyle;
	public Wave[] callWave;// 自定义叫号
	public String topImgUrl;
	public String rightImgUrl;
	public String bookVersion;
	public boolean bPhoneBoxEn;//是否支持电话盒子 0关闭 1开启
	public String phoneBoxIp;//电话盒子Ip---SPECICAL
	public int waiterPraise;
	public String serverIp;//---SPECICAL
	public String qractive;
	public int checkcode;//使用流水号后N位作为校验码
	public String offqr;
	public int inputMobNo;//0：无需手机号，1自助强制，2全部强制
	public int customQr;
	public boolean bFastFoodMode; //快餐模式
	public boolean bOpMsgEn; //是否开启销号消息中心---SPECICAL
	public String shopWxQr;  //商家公众账号二维码
	public String shopWxQrNotice;  //商家提示文案
}