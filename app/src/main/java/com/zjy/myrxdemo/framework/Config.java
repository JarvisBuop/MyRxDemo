package com.zjy.myrxdemo.framework;

public class Config {
    private static int ENV = 100;

    public Config() {
    }

    public static int getENV() {
        return ENV;
    }

    public static void setENV(int ENV) {
        Config.ENV = ENV;
    }
}