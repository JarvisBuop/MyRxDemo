package com.zjy.member.business.member;

import com.zjy.zlibrary.base.BasePresenter;
import com.zjy.zlibrary.base.BaseView;

public interface MemberContract {

    interface Presenter extends BasePresenter{

    }

    interface View extends BaseView<MemberContract.Presenter>{

    }
}
