package com.zjy.table.tables;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zjy.table.R;
import com.zjy.table.R2;
import com.zjy.zlibrary.fragment.fragmentation.SupportFragment;
import com.zjy.zlibrary.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:
 * Creator:zhou.junyou@puscene.com
 * Create by:Android Studio
 * Date:2017/4/25
 */
public class TablesFragment extends SupportFragment {
    @BindView(R2.id.titleBar)
    TitleBar titleBar;
    @BindView(R2.id.tab_table_type)
    TabLayout tabTable;
    @BindView(R2.id.vp_table_list)
    ViewPager vpTableList;
    public static final String TAG=TablesFragment.class.getSimpleName();

    String[] tableTypes = new String []{"1","2","3","4","5","6"};
    protected TableTypeAdapter mTableTypeAdapter;

    public static TablesFragment newInstance() {
        Bundle args = new Bundle();
        TablesFragment fragment = new TablesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tables,container,false);
        ButterKnife.bind(this,root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpTitleBar();
        initTabTable();
        initTaleList();

    }




    private void setUpTitleBar() {
        titleBar.setLeftImageResource(R.drawable.icon_back);
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        titleBar.setTitle("桌台");
        titleBar.setTitleColor(Color.WHITE);

    }

    private void initTabTable() {
        for (int i = 0; i <tableTypes.length ; i++) {
            TabLayout.Tab tab = tabTable.newTab().setCustomView(R.layout.tab_item_tables);
            ((TextView) tab.getCustomView().findViewById(R.id.tv_table_type)).setText(tableTypes[i]);
            tabTable.addTab(tab);
        }
        tabTable.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getCustomView().setSelected(true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getCustomView().setSelected(false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
    private void initTaleList() {
        mTableTypeAdapter = new TableTypeAdapter(getChildFragmentManager(),tableTypes);
        vpTableList.setAdapter(mTableTypeAdapter);
       // tabTable.setupWithViewPager(vpTableList);
    }

    private final class TableTypeAdapter extends FragmentPagerAdapter {
        private String[] tableTypes;

        public TableTypeAdapter(FragmentManager fm, String[] tableTypes) {
            super(fm);
            this.tableTypes = tableTypes;
        }

        @Override
        public Fragment getItem(int position) {
            TableListFragment fragment=TableListFragment.newInstance(tableTypes[position]);
            return fragment;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            //registeredFragments.put(position, fragment);
            return fragment;
        }

        @Override
        public int getCount() {
            return tableTypes.length;
        }

    }
}
