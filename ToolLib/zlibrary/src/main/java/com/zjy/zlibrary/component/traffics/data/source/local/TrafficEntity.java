package com.zjy.zlibrary.component.traffics.data.source.local;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "traffics_table")
public class TrafficEntity {
    private Long id;
    private String date;
    private Long time_stamp;
    private String uid;
    private  int  traffic_type;
    private  int  traffic_direction;
    private long traffics;
    @Generated(hash = 2095075410)
    public TrafficEntity(Long id, String date, Long time_stamp, String uid,
            int traffic_type, int traffic_direction, long traffics) {
        this.id = id;
        this.date = date;
        this.time_stamp = time_stamp;
        this.uid = uid;
        this.traffic_type = traffic_type;
        this.traffic_direction = traffic_direction;
        this.traffics = traffics;
    }
    @Generated(hash = 242890895)
    public TrafficEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public Long getTime_stamp() {
        return this.time_stamp;
    }
    public void setTime_stamp(Long time_stamp) {
        this.time_stamp = time_stamp;
    }
    public String getUid() {
        return this.uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public int getTraffic_type() {
        return this.traffic_type;
    }
    public void setTraffic_type(int traffic_type) {
        this.traffic_type = traffic_type;
    }
    public int getTraffic_direction() {
        return this.traffic_direction;
    }
    public void setTraffic_direction(int traffic_direction) {
        this.traffic_direction = traffic_direction;
    }
    public long getTraffics() {
        return this.traffics;
    }
    public void setTraffics(long traffics) {
        this.traffics = traffics;
    }
   
    
   
}
