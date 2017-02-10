package com.zjy.baselib.data.model;

import java.io.Serializable;

public class NetWorkResponse<T> implements Serializable{
    public String server_time = "";
    public int errno = 0;
    public String errmsg = "";
    public int status = 0;
    public String msg = "";
    public T data;

}
