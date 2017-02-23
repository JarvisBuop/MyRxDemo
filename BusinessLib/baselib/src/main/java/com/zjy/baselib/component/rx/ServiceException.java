package com.zjy.baselib.component.rx;

import com.zjy.baselib.data.model.NetWorkResponse;

public class ServiceException extends RuntimeException {
    public static final int TRANSFORM_TO_FAILED=999;
    private NetWorkResponse mResponse;
    public int errorNo;
    public String errorMsg;
    public ServiceException(int errorNo,String errorMsg) {
        super(errorMsg);
        this.errorNo=errorNo;
        this.errorMsg=errorMsg;
    }

    public ServiceException( NetWorkResponse response) {
        mResponse = response;
    }
}
