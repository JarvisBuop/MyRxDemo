package com.example.testapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Timer timer;
    private TimerTask task;
    final int WHAT = 102;
    protected TextView tvTime;
    private int times;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WHAT:
                    tvTime.setText(times++ +"");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }


    private void initView() {
        tvTime = ((TextView) findViewById(R.id.tv_time));
        task = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = WHAT;
                message.obj = System.currentTimeMillis();
                handler.sendMessage(message);
            }
        };

        timer = new Timer();
        timer.schedule(task, 1000, 1000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
