package com.zjy.cash.business.orders;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjy.cash.R;
import com.zjy.cash.R2;
import com.zjy.cash.business.orders.list.OrderListFragment;
import com.zjy.zlibrary.component.scrollablelayout.ScrollableHelper;
import com.zjy.zlibrary.component.scrollablelayout.ScrollableLayout;
import com.zjy.zlibrary.fragment.fragmentation.SupportFragment;
import com.zjy.zlibrary.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrdersFragment extends SupportFragment {
    public static final String TAG=OrdersFragment.class.getSimpleName();
    @BindView(R2.id.titleBar)
    TitleBar titleBar;
    @BindView(R2.id.tab_order_type)
    TabLayout tabLayout;
    @BindView(R2.id.viewPager_order_type)
    ViewPager viewPager;
    @BindView(R2.id.scrollableLayout)
    ScrollableLayout scrollableLayout;
    SparseArray<Fragment> registeredFragments = new SparseArray<>();
    protected OrderTypeAdapter adapter;

    public static OrdersFragment newInstance() {
        Bundle args = new Bundle();
        OrdersFragment fragment = new OrdersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            scrollableLayout.getHelper().setCurrentScrollableContainer((ScrollableHelper.ScrollableContainer) registeredFragments.get(position));
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_orders, container, false);
        ButterKnife.bind(this,root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView(view);
        initData();
    }


    private void initView(View view) {
        setUpTitleBar();
    }
    private void initData() {
        adapter = new OrderTypeAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(mPageChangeListener);
        viewPager.post(new Runnable() {
            @Override
            public void run() {
                mPageChangeListener.onPageSelected(0);
            }
        });
    }


    private void setUpTitleBar() {
        titleBar.setLeftImageResource(R.drawable.icon_back);
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        titleBar.setTitle("收银记录");
        titleBar.setTitleColor(Color.WHITE);
        titleBar.addAction(new TitleBar.ImageAction(R.drawable.icon_search) {
            @Override
            public void performAction(View view) {

            }
        });
    }


    private final class OrderTypeAdapter extends FragmentPagerAdapter {


        public OrderTypeAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            OrderListFragment fragment=null;
            switch (position) {
                case 0:
                    fragment=OrderListFragment.newInstance(OrderListFragment.POS_CASH);//pos 收银
                    break;
                case 1:
                    fragment=OrderListFragment.newInstance(OrderListFragment.POS_REFUND);//pos 退款
                    break;
                case 2:
                    fragment=OrderListFragment.newInstance(OrderListFragment.OTHER_CASH);//其他收银
                    break;
                case 3:
                    fragment=OrderListFragment.newInstance(OrderListFragment.OFFLINE_CASH);//线下收银
                    break;
            }
            return fragment;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            registeredFragments.put(position, fragment);
            return fragment;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = "";
            switch (position) {
                case 0:
                    title = "POS收银";
                    break;
                case 1:
                    title="POS退款";
                    break;
                case 2:
                    title = "其他收银";
                    break;
                case 3:
                    title="线下收银";
                    break;
            }
            return title;
        }
    }

}
