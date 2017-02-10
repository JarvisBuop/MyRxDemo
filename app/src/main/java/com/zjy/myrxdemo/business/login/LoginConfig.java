package com.zjy.myrxdemo.business.login;

import android.text.TextUtils;

import com.zjy.myrxdemo.component.util.StringUtil;
import com.zjy.myrxdemo.data.model.login.ShopInfo;
import com.zjy.myrxdemo.data.model.login.bean.LoginResponse;

import org.jivesoftware.smack.util.Base64;

import rx.Observable;

public class LoginConfig {

    public static ShopInfo buildShopInfoFromLoginResponse(LoginResponse response) {
        ShopInfo mShopInfo = new ShopInfo();
        mShopInfo.shop_id = response.ShopID;
        mShopInfo.shop_name = response.ShopName;
        mShopInfo.telphone = response.Tel;
        mShopInfo.callPref = response.CallPrefix;
        mShopInfo.callTail = response.CallWave;
        mShopInfo.custWave = response.Wave;
        mShopInfo.wait_disc = response.WaitDiscount;
        mShopInfo.wait_disc2 = response.NewWaitDis;
        mShopInfo.nowAd = response.NowAD;
        mShopInfo.Notice = response.Notice;
        mShopInfo.QRBase = response.QR;
        mShopInfo.bEnablePhone = response.PhoneQueue != 0; // 是否支持手机取号
        mShopInfo.bHideTotal = response.TotalDisplay == 0;
        mShopInfo.bHideWaitN = response.QueueDisplayConf == 0;
        mShopInfo.bHideWaitNOnTV = response.TVDisplayConf == 0;
        mShopInfo.LogoUrl = response.QLogo;
        mShopInfo.custWaveZip = response.CustVoice;
        mShopInfo.sessionId = response.SessionID;
        mShopInfo.ordernotice = response.OrderNotice;

        mShopInfo.specify = response.Specify;
        // 解析备注信息
        mShopInfo.specifyMore = "";
        if (response.SpecifyMore != null && response.SpecifyMore.length() > 0) {
            try {
                byte[] b = Base64.decode(response.SpecifyMore);
                String specifyMore = new String(b, "GB2312"); // "gb2312"
                mShopInfo.specifyMore = specifyMore; // 必须定义临时变量赋值
            } catch (Exception e) {
                //LogFile.w("备注信息解析错误", e);
            }
        }

        mShopInfo.specifySingleLine = "";
        if (mShopInfo.specifyMore != null && mShopInfo.specifyMore.length() > 0) {
            String t = mShopInfo.specifyMore.replace('\r', ' ');
            mShopInfo.specifySingleLine = t.replace('\n', ' ');
            mShopInfo.specifySingleLine = StringUtil.removeTags(mShopInfo.specifySingleLine);
        }

        // 解析广告信息
        mShopInfo.adNew = "";
        if (response.ADNew != null && response.ADNew.length() > 0) {
            try {
                byte[] b = Base64.decode(response.ADNew);
                String ads = new String(b, "GB2312"); // "gb2312"
                mShopInfo.adNew = ads; // 必须定义临时变量赋值
            } catch (Exception e) {
                //LogFile.w("备注ad64解析错误", e);
            }
        }

        mShopInfo.adNewSingleLine = "";
        if (mShopInfo.adNew != null && mShopInfo.adNew.length() > 0) {
            String t = mShopInfo.adNew.replace('\r', ' ');
            mShopInfo.adNewSingleLine = t.replace('\n', ' ');
        }
        mShopInfo.wxname = response.WxName;
        mShopInfo.queryPref = response.QueryPref;
        mShopInfo.tvRemark = response.TVRemark;
        mShopInfo.inputMobNo = response.ForceMobile;// 取号终端强制输入手机号
        mShopInfo.bBarcode = response.Barcode == 1;
        mShopInfo.NowAPs = response.AP;


        if (response.AM != null && response.AM.length > 0) {
            mShopInfo.amstart = response.AM[0];
        } else {
            mShopInfo.amstart = "10:00:00";
        }

        boolean isDefaultPm = false;
        if (TextUtils.isEmpty(response.PMStart)) {
            mShopInfo.pmstart = "15:00:00";
            isDefaultPm = true;
        } else {
            mShopInfo.pmstart = response.PMStart;
        }
        mShopInfo.isDefaultPmStart = isDefaultPm;

        mShopInfo.ResvNum = response.ReserveNum;
        mShopInfo.unLukyNum = response.UnLuckN;
        if (response.Bbox == 1) {
//            LogFile.g_bDebug = true;
//            BasePayPref.setLogNeedZip(true);
        }
        for (int s : response.Services) {
            //商户开通服务
            for (int p : response.PopedomInfo) {
                //分店账号权限（分店开通服务）

                // 排队
                if (s == 1 && p == 1) {
                    mShopInfo.bQueueEn = true;
                }
                if (s == 2 && p == 2) {//菜单
                    mShopInfo.bDishEn = true;
                }
                if (s == 6 && p == 6) {
                    // 预订
                    mShopInfo.bBookEn = true;
                }
                if (s == 8 && p == 8) {
                    mShopInfo.bPayEn = true;
                }
                if (s == 12 && p == 12) {//卡券功能
                    mShopInfo.bCouponEn = true;
                }
                if (s == 13 && p == 13) {//收款
                    mShopInfo.bCashEn = true;
                }
                if (s == 14 && p == 14) {//弹幕
                    mShopInfo.bDanmuEn = true;
                }
                if (s == 15 && p == 15 && mShopInfo.bQueueEn) {//预约取号 依赖排队
                    mShopInfo.bPreOrderEn = true;
                }
                if (s == 17 && p == 17) {
                    mShopInfo.bMemberEn = true;
                }
                if (s == 18 && p == 18) {
                    mShopInfo.bTableSearch = true;
                }
            }
            if (s == 10)// 预点单打印
            {
                mShopInfo.bOrderEn = true;
            }
        }

        mShopInfo.bMemEn = response.Mem == 1;
        mShopInfo.bShopVip = response.shopVip == 1;
        mShopInfo.bookLastOp = response.BookOP;
        mShopInfo.callway = response.CallType;
        mShopInfo.printway = response.PrintType;
        mShopInfo.bPauseEn = response.Pause == 1;
        mShopInfo.printstyle = response.Style;
        mShopInfo.callWave = response.CD;
        mShopInfo.topImgUrl = response.TopImg;
        mShopInfo.rightImgUrl = response.RightImg;
        mShopInfo.bookVersion = response.BookVersion;
        mShopInfo.waiterPraise = response.waiterPraise;
        mShopInfo.qractive = response.active;
        mShopInfo.checkcode = response.checkcode;
        mShopInfo.offqr = response.offQr;
        mShopInfo.customQr = response.shopQr;
        mShopInfo.bFastFoodMode = response.KuaiCan == 1;
        mShopInfo.shopWxQr = response.shopWxQr;
        mShopInfo.shopWxQrNotice = response.shopWxQrNotice;
        return mShopInfo;
    }

    public static Observable<String> getAdvUrl(){
        return null;
    }
}
