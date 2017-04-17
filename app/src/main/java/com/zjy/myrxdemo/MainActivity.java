package com.zjy.myrxdemo;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.roughike.bottombar.BottomBar;
import com.zjy.cash.business.cash.CashFragment;
import com.zjy.coupon.business.check.CheckCouponFragment;
import com.zjy.member.business.member.MemberFragment;
import com.zjy.myrxdemo.business.set.SetFragment;
import com.zjy.zlibrary.fragment.fragmentation.SupportActivity;
import com.zjy.zlibrary.fragment.fragmentation.SupportFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = "/myapp/main_activity")
public class MainActivity extends SupportActivity {

    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;
    private SupportFragment[] mFragments = new SupportFragment[4];
    private SupportFragment hideFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment(savedInstanceState);
        initView();

    }



    private void initFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mFragments[0] = CashFragment.newInstance();
            mFragments[1] = CheckCouponFragment.newInstance();
            mFragments[2] = MemberFragment.newInstance();
            mFragments[3] = SetFragment.newInstance();

            loadMultipleRootFragment(R.id.container, 0,
                    mFragments[0],
                    mFragments[1],
                    mFragments[2],
                    mFragments[3]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用,也可以通过getSupportFragmentManager.getFragments()自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[0] = findFragment(CashFragment.class);
            mFragments[1] = findFragment(CheckCouponFragment.class);
            mFragments[2] = findFragment(MemberFragment.class);
            mFragments[3] = findFragment(SetFragment.class);
        }
    }

    private void initView() {
        mBottomBar.setOnTabSelectListener(tabId -> {
            showHideFragment( mFragments[TabMessage.get(tabId,false)],hideFragment);
            hideFragment=mFragments[TabMessage.get(tabId,false)];
        });

        mBottomBar.setOnTabReselectListener(tabId -> {
            TabMessage.get(tabId, true);
            //Toast.makeText(getApplicationContext(), TabMessage.get(tabId, true), Toast.LENGTH_LONG).showProgress();
        });
    }
}
