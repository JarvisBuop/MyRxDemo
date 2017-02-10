package com.zjy.baselib.data.source.local;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "my_rx_table")
public class AppDB {
    @Id(autoincrement = true)
    private Long id;
    private String key;
    private String value;
    @Generated(hash = 1902199724)
    public AppDB(Long id, String key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }
    @Generated(hash = 367402257)
    public AppDB() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getKey() {
        return this.key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getValue() {
        return this.value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
