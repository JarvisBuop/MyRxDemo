package com.zjy.myrxdemo.business.set;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjy.myrxdemo.R;
import com.zjy.zlibrary.fragment.BaseFragment;

public class SetFragment extends BaseFragment {
    public static final String TAG=SetFragment.class.getSimpleName();

    public static SetFragment newInstance() {

        Bundle args = new Bundle();

        SetFragment fragment = new SetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_set, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
