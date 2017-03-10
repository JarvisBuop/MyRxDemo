package com.zjy.cash.business.orders.list;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zjy.baselib.component.Injection.Injection;
import com.zjy.cash.R;
import com.zjy.cash.business.Constants;
import com.zjy.cash.data.model.order.PayOrder;

import java.util.ArrayList;







/**
 * Created with android studio
 * Creator:zhou.junyou@puscene.com
 * DATE:16/12/22
 * TIME:下午6:22
 * DESC:
 */

public class OrderListAdapter extends BaseQuickAdapter<PayOrder,BaseViewHolder> {
    protected final Context context;
    private int[] tvIds = new int[]{R.id.tv_order_id, R.id.tv_pay_time, R.id.tv_real_price, R.id.tv_pay_table, R.id.tv_fail_reason, R.id.tv_phone, R.id.tv_source_price, R.id.tv_print, R.id.tv_pay_type};

    public OrderListAdapter(ArrayList<PayOrder> items) {
        super(R.layout.smart_order_item, items);
        context = Injection.provideContext();
    }

    @Override
    protected void convert(BaseViewHolder helper, PayOrder item) {
        helper.setVisible(R.id.tv_has_eat,true)
                .setVisible(R.id.tv_order_id,true)
                .setVisible(R.id.tv_pay_time,true)
                .setVisible(R.id.tv_phone,true)
                .setVisible(R.id.tv_print,true)
                .setVisible(R.id.tv_print_num,true)
                .setVisible(R.id.tv_source_price,true)
                .setVisible(R.id.bt_print,true)
                .setVisible(R.id.tv_pay_table, false)
                .setVisible(R.id.tv_real_price,true)
                .setVisible(R.id.tv_fail_reason, false)
                .setVisible(R.id.bt_pay, false)
                .setVisible(R.id.tv_pay_type, false)
                .setText(R.id.tv_order_id, context.getString(R.string.orderAdapter_orderNo) + item.id)
                .setText(R.id.tv_has_eat, item.orderState)
                .setText(R.id.tv_pay_time, context.getString(R.string.trade_time) + item.lastTime)
                .setText(R.id.tv_real_price, context.getString(R.string.true_cash) + String.valueOf(item.leftTotal));
        setTextsColor(helper, item.state);
        helper.setTextColor(R.id.tv_has_eat, getStateTextColor(item.state));
        if (!TextUtils.isEmpty(item.tableNo)) {
            helper.setText(R.id.tv_pay_table, context.getString(R.string.tableNo_hint) + item.tableNo);
            helper.setVisible(R.id.tv_pay_table, true);
        }
        if (!TextUtils.isEmpty(item.orderFailReason)) {
            helper.setText(R.id.tv_fail_reason, context.getString(R.string.failed_reason_hint) + item.orderFailReason);
        }
        if (item.type == 1) {// 点菜
            helper.setText(R.id.tv_source_price, context.getString(R.string.orderAdapters_pre_order_amount)
                    + String.valueOf(item.totalall));
            if ((!TextUtils.isEmpty(item.print)) && (!item.print.equalsIgnoreCase("0000"))) {
                helper.setText(R.id.tv_print_num, item.print);
            } else {
                helper.setVisible(R.id.tv_print, false);
                helper.setVisible(R.id.tv_print_num, false);
            }
            if (!TextUtils.isEmpty(item.mobile)) {
                if (item.mobile.length() > 7) {
                    helper.setText(R.id.tv_phone, context.getString(R.string.phoneNumber_lastFour)
                            + item.mobile.substring(7));
                } else {
                    helper.setText(R.id.tv_phone, context.getString(R.string.contact) + item.mobile);
                }
            } else {
                helper.setVisible(R.id.tv_phone, false);
            }
            if (item.state <= 1) {//待支付 1
                helper.setVisible(R.id.tv_real_price, false);
                helper.setVisible(R.id.tv_pay_time, false);
                helper.setVisible(R.id.bt_pay, true);
            } else if (item.state < 5) {//已支付 2 3 4
                //R.id.tv_source_price.setVisibility(View.GONE);
                helper.setVisible(R.id.bt_print, false);
            } else if (item.state == 5) {//已退款 5
                helper.setVisible(R.id.bt_print, false);
            } else if (item.state == 6) {
                helper.setVisible(R.id.tv_real_price, false);
                helper.setVisible(R.id.tv_pay_time, false);
                helper.setVisible(R.id.bt_pay, true);
                helper.setVisible(R.id.tv_fail_reason, true);
            }
        } else if (item.type == 3) {//直接收银
            helper.setText(R.id.tv_source_price, context.getString(R.string.order_amount) + String.valueOf(item.totalall));
            helper.setVisible(R.id.tv_print, false);
            helper.setVisible(R.id.tv_print_num, false);
            helper.setVisible(R.id.tv_phone, false);
            helper.setVisible(R.id.bt_print, false);
            if (item.state <= 1) {//待支付 1
                helper.setVisible(R.id.tv_real_price, false);
                helper.setText(R.id.tv_pay_time, context.getString(R.string.ordersAdapter_place_order_time) + item.createDate);
            } else if (item.state < 5) {//已支付 2 3 4
                //item.tv_source_price.setVisibility(View.GONE);
                helper.setVisible(R.id.bt_print, false);
            } else if (item.state == 5) {//已退款 5
                helper.setVisible(R.id.bt_print, false);
            } else if (item.state == 6) {
                helper.setVisible(R.id.tv_real_price, false);
                helper.setText(R.id.tv_pay_time, context.getString(R.string.ordersAdapter_place_order_time) + item.createDate);
                helper.setVisible(R.id.tv_fail_reason, true);
            }
        }

        helper.setVisible(R.id.bt_pay, false);
        helper.setVisible(R.id.bt_print, false);
        if (item.paytype == Constants.PayType.PAY_TYPE_ALIPAY) {
            helper.setText(R.id.tv_pay_type, R.string.paytype_alipay);
            helper.setVisible(R.id.tv_pay_type, true);
        } else if (item.paytype == Constants.PayType.PAY_TYPE_WECHAT) {
            helper.setText(R.id.tv_pay_type, R.string.paytype_wechat);
            helper.setVisible(R.id.tv_pay_type, true);
        } else if (item.paytype == Constants.PayType.PAY_TYPE_UNIONPAY) {
            helper.setText(R.id.tv_pay_type, R.string.paytype_union);
            helper.setVisible(R.id.tv_pay_type, true);
        } else if (item.paytype == Constants.PayType.PAY_TYPE_BAIDU) {
            helper.setText(R.id.tv_pay_type, R.string.paytype_baifubao);
            helper.setVisible(R.id.tv_pay_type, true);
        } else if (item.paytype == Constants.PayType.PAY_TYPE_MEMBER) {
            helper.setText(R.id.tv_pay_type, R.string.paytype_member_card);
            helper.setVisible(R.id.tv_pay_type, true);
        }
    }


    private int getStateTextColor(int state) {
        int resId = ContextCompat.getColor(context, R.color.gray_4th);
        switch (state) {
            case 1:
            case 7:
                resId = ContextCompat.getColor(context, R.color.gray_4th);
                break;
            case 2:
            case 3:
            case 4:
                resId = ContextCompat.getColor(context, R.color.green_1st);
                break;
            case 5:
                resId = ContextCompat.getColor(context, R.color.gray_1st);
                break;
            case 6:
            case 8:
                resId = ContextCompat.getColor(context, R.color.pink_1st);
                break;
        }
        return resId;
    }

    private void setTextsColor(BaseViewHolder helper, int state) {
        for (int id : tvIds) {
            if (state == 5) {
                helper.setTextColor(id, ContextCompat.getColor(context, R.color.gray_5th));
            } else {
                if (id == R.id.tv_order_id) {
                    helper.setTextColor(id, ContextCompat.getColor(context,R.color.black_1st));
                } else {
                    helper.setTextColor(id, ContextCompat.getColor(context, R.color.gray_3th));
                }
            }
        }

    }
}
