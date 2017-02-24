package com.zjy.baselib.component.rx;

public class ServiceException extends RuntimeException {
    public static final int TRANSFORM_TO_FAILED=999;
    public int errorNo;
    public String errorMsg;
    public ServiceException(int errorNo,String errorMsg) {
        super(errorMsg);
        this.errorNo=errorNo;
        this.errorMsg=errorMsg;
    }

}
