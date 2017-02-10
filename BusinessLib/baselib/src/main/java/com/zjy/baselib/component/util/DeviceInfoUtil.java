package com.zjy.baselib.component.util;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.blankj.utilcode.utils.Utils;


public class DeviceInfoUtil {
    public static final String LAKALA = "com.lkl.cloudpos.payment";
    public static final String UNION_BUSINESS = "com.ums.upos.uapi";
    public static final String YIN_SHENG = "com.ys.smartpos";

    public static final int WIZ_ID = 1;
    public static final int LAKALA_ID = 2;
    public static final int UMS_ID = 3;
    public static final int YINSHENG_ID = 4;


    private DeviceInfoUtil() {
    }

    static int DEVICE_TYPE;

    static {
        if (Build.MODEL.contains("WIZARHAND_Q")) {
            DEVICE_TYPE = WIZ_ID;
        } else if (hasPackage(LAKALA) && hasPackage("com.lklcloudpos.midservice")) {
            DEVICE_TYPE = LAKALA_ID;
        } else if (hasPackage(UNION_BUSINESS)) {
            DEVICE_TYPE = UMS_ID;
        } else if (hasPackage(YIN_SHENG) && hasPackage("com.ysepay.pos.deviceservice")) {
            DEVICE_TYPE = YINSHENG_ID;
        }
    }


    public static boolean isWizarPOS() {
        //WIZARHAND_Q1
        return DEVICE_TYPE == WIZ_ID;
    }

    public static boolean isLakala() {
        return DEVICE_TYPE == LAKALA_ID;
    }

    public static boolean isUnionBusiness() {
        return DEVICE_TYPE == UMS_ID;
    }

    public static boolean isYinSheng() {
        return DEVICE_TYPE == YINSHENG_ID;
    }

    public static boolean isPrintable() {
        boolean ret = false;
        if (isWizarPOS() || isLakala() || isUnionBusiness() || isYinSheng()) {
            ret = true;
        } else {
//            if(PrintUtil.getInstance() instanceof BTPrinter && ((BTPrinter)PrintUtil.getInstance()).isConnection()){
//                ret = true;
//            }
        }
        return ret;
    }

    public static boolean isLianDiA8() {
        return isLakala() || isYinSheng() || isUnionBusiness();
    }

    public static String getSourceType() {
        String sourcetype = "";
        if (isWizarPOS()) {
            sourcetype = Constants.PAY_SOURCE_WIZARPOS;
        } else if (isLakala()) {
            sourcetype = Constants.PAY_SOURCE_LAKALA;
        } else if (isUnionBusiness()) {
            sourcetype = Constants.PAY_SOURCE_LAKALA;
        } else if (isYinSheng()) {
            sourcetype = Constants.PAY_SOURCE_LAKALA;
        } else {
            sourcetype = Constants.PAY_SOURCE_LAKALA;
        }
        return sourcetype;
    }

    public static String getPrintType(){
        String printType="";
        if(isWizarPOS()){
            printType="(P:WIZARPOS)";
        }else if(isLakala()){
            printType= "(P:LaKaLa)";
        }else if(isUnionBusiness()){
            printType="(P:UnionBusuness)";
        }else {
            printType="UNK";
        }
        return printType;
    }

    private static boolean hasPackage(String packageName) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = Utils.getContext().getPackageManager().getPackageInfo(packageName, 0);

        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
            // LogFile.w("hasPackage NameNotFoundException", e);
        }
        return packageInfo != null;
    }
}