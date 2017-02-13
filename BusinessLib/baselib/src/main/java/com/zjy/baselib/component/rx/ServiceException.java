package com.zjy.baselib.component.rx;

public class ServiceException extends Exception {
    public int errorNo;
    public String errorMsg;
    public ServiceException(int errorNo,String errorMsg) {
        super(errorMsg);
        this.errorNo=errorNo;
        this.errorMsg=errorMsg;
    }

}
