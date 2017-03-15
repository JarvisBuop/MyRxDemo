package com.example.testapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.utils.Utils;
import com.zjy.zlibrary.component.traffics.TrafficsManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.init(this.getApplicationContext());
        initView();
    }

    private void initView() {
        final TextView traffics = (TextView) findViewById(R.id.tv_traffics);
        findViewById(R.id.btn_traffics).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrafficsManager.saveMobileTraffics();
                traffics.setText(TrafficsManager.getMobileTraffics()+"M");
            }
        });
    }


}
