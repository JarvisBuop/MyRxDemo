package com.zjy.cash.business.orders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.zjy.zlibrary.fragment.fragmentation.SupportActivity;

public class OrdersActivity extends SupportActivity {



    public static void start(Context context) {
        Intent starter = new Intent(context, OrdersActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadRootFragment(android.R.id.content,OrdersFragment.newInstance());
    }
}
