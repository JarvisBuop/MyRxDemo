package com.zjy.zlibrary.component.traffics.data.source.local;

public class TraPersistenceContract {
    public static class TrafficDBEntry{
        public static final String DB_NAME="traffics_db";

    }

    public static class TrafficValue{
        public  static  final int  DIRECTION_RX =1;
        public  static  final int  DIRECTION_TX =2;
        public  static  final int  TYPE_WIFI =1;
        public  static  final int  TYPE_MOBILE =0;

    }
}
