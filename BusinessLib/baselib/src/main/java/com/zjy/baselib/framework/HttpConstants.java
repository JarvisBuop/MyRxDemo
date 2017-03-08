package com.zjy.baselib.framework;

public class HttpConstants {
    public final static String URL_ROOT_PRODUCT = "http://zf.mwee.cn/";
    public final static String URL_ROOT_DEV = "http://test.9now.net/";

    public final static String URL_ROOT_TEST="http://st.9now.net/";


    public static final String B_API_VERSION_VALUE = "V6";
    public static final String B_API_VERSION_DEV_VALUE = "T";

    private static String URL_NOW = URL_ROOT_TEST;

    /**
     * 设置非生产环境下的UrlRoot
     *
     * @param urlRootTest String
     */
    public static void setUrlRootTest(String urlRootTest) {
        URL_NOW = urlRootTest;
    }

    /**
     * 获取UrlRoot
     *
     * @return String
     */
    public static String getPDUrlRoot() {
        if (BaseConfig.isProduct()) {
            return URL_ROOT_PRODUCT;
        }
        else {
            return URL_NOW;
        }
    }

    public static String getbApiVersionValue(){
        return B_API_VERSION_VALUE;
    }
}