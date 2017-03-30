package com.example.testapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zjy.zlibrary.component.traffics.Traffic;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
