package com.zjy.cash.business.cash;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.zjy.cash.R;
import com.zjy.zlibrary.fragment.fragmentation.SupportActivity;
@Route(path = "/cash/cash_route_activity")
public class CashRouteActivity extends SupportActivity {

    @Autowired
    boolean isOutPay;
    @Autowired
    String thirdCashModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash);

        if(isOutPay){

        }
    }

}
