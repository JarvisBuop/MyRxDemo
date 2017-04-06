package com.zjy.baselib.component.keyboard;

import java.io.Serializable;

/**
 * Description: 虚拟键盘键值对
 * Creator:zhou.junyou@puscene.com
 * Create by:Android Studio
 * Date:2017/4/6
 */
public class KeyEntry implements Serializable {
    private String keyName;
    private int keyCode;
    private int keyImage;

    public KeyEntry() {
    }

    public KeyEntry(String keyName, int keyCode) {
        this.keyName = keyName;
        this.keyCode = keyCode;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getKeyImage() {
        return keyImage;
    }

    public void setKeyImage(int keyImage) {
        this.keyImage = keyImage;
    }
}
