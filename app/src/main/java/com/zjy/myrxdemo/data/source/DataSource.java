package com.zjy.myrxdemo.data.source;

import com.zjy.myrxdemo.data.model.User;

import rx.Observable;

public interface DataSource {
    Observable<User> getUser();
}
