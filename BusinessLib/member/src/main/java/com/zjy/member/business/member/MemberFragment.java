package com.zjy.member.business.member;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjy.member.R;
import com.zjy.zlibrary.fragment.BaseFragment;

public class MemberFragment extends BaseFragment implements MemberContract.View {
    public static final String TAG=MemberFragment.class.getSimpleName();

    public static MemberFragment newInstance() {

        Bundle args = new Bundle();

        MemberFragment fragment = new MemberFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void setPresenter(MemberContract.Presenter presenter) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_member, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
