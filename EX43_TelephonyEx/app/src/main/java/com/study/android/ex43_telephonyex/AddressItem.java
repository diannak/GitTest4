package com.study.android.ex43_telephonyex;

import android.graphics.Bitmap;

public class AddressItem {
    private static final String TAG = "lecture";

    private String name;
    private String telnum;
    private Bitmap ResId;

    public AddressItem(String name, String telnum, Bitmap resId) {
        this.name = name;
        this.telnum = telnum;
        ResId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelnum() {
        return telnum;
    }

    public void setTelnum(String telnum) {
        this.telnum = telnum;
    }

    public Bitmap getResId() {
        return ResId;
    }

    public void setResId(Bitmap resId) {
        ResId = resId;
    }
}

