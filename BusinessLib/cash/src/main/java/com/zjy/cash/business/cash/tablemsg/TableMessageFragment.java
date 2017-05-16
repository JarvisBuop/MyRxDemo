package com.zjy.cash.business.cash.tablemsg;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zjy.cash.R;
import com.zjy.cash.R2;
import com.zjy.cash.data.model.table.TableOrderMessage;
import com.zjy.zlibrary.fragment.fragmentation.SupportFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description:
 * Creator:zhou.junyou@puscene.com
 * Create by:Android Studio
 * Date:2017/5/15
 */
public class TableMessageFragment extends SupportFragment {
    public static final String TAG = TableMessageFragment.class.getSimpleName();
    @BindView(R2.id.btn_cancel_bill)
    Button btnCancelBill;
    @BindView(R2.id.tv_table_msg)
    TextView tvTableMsg;
    protected OnTableBillListener mTableBillListener;
    protected TableOrderMessage tableOrderMessage;

    public static TableMessageFragment newInstance(String message) {

        Bundle args = new Bundle();
        args.putString("table_message", message);
        TableMessageFragment fragment = new TableMessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mTableBillListener = ((OnTableBillListener) getParentFragment());
        } catch (ClassCastException e) {
            throw new ClassCastException(getParentFragment().toString()
                    + " must implement mTableBillListener");
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.layout_cash_table_bill, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showTableMessage(getArguments().getString("table_message"));
    }

    public void showTableMessage(String message) {
        tableOrderMessage = new Gson().fromJson(message, TableOrderMessage.class);
        tvTableMsg.setText(tableOrderMessage.tableMessage);
        mTableBillListener.onTableMessageChange(tableOrderMessage);
    }


    @OnClick(R2.id.btn_cancel_bill)
    public void cancelBill(View view) {
        tableOrderMessage=null;
        mTableBillListener.onTableMessageChange(null);
        mTableBillListener.onCancelTableBill();
    }

    @Override
    public boolean onBackPressedSupport() {
        cancelBill(btnCancelBill);
        return true;
    }

    public interface OnTableBillListener {
        void onCancelTableBill();
        void onTableMessageChange(TableOrderMessage tableOrderMessage);
    }


}
