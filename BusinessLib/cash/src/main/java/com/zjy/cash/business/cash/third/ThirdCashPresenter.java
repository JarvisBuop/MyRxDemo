package com.zjy.cash.business.cash.third;

import com.google.gson.Gson;
import com.zjy.cash.business.cash.CashContract;
import com.zjy.cash.business.cash.CashPresenter;
import com.zjy.cash.data.model.third.ThirdCashModel;

/**
 * Description:
 * Creator:zhou.junyou@puscene.com
 * Create by:Android Studio
 * Date:2017/4/21
 */
public class ThirdCashPresenter extends CashPresenter {
    protected ThirdCashModel mThirdCashModel;

    public ThirdCashPresenter(CashContract.View cashView) {
        super(cashView);
    }

    public void setThirdCashModel(String thirdModelJson) {
        mThirdCashModel = new Gson().fromJson(thirdModelJson, ThirdCashModel.class);
    }

    public String getTableNo() {
        return mThirdCashModel.tableName;
    }
}
