package com.example.testapp;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.zjy.zlibrary.component.traffics.Traffic;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        Traffic.init(this);
    }
}
