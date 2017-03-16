package com.zjy.myrxdemo.data.model.login.bean;

import java.io.Serializable;

public class Wave implements Serializable {
    public String name;
    public String path;
    public boolean auto;
    public String startTime;
    public String endTime;
    public boolean singlePlay;

    public Wave() {
    }
}