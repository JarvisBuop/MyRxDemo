package com.zjy.baselib.framework;

public class BaseConfig {
    public static boolean useBaffle = false;
    public static boolean show_action_log = false;
    public static boolean shake = Config.getENV() == 104;

    public BaseConfig() {
    }

    public static boolean isDEV() {
        return Config.getENV() == 104;
    }

    public static boolean isProduct() {
        return Config.getENV() == 100 || Config.getENV() == 101;
    }
}