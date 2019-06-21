package com.study.android.ex20_list6;

public class SingerItem {

    private String name;
    private String telnum;
    private int reId;
    public SingerItem(String name, String telnum, int reId){
        this.name = name;

        this.reId = reId;
        this.telnum = telnum;
    }

    public String getTelnum() {
        return telnum;
    }

    public void setTelnum(String telnum) {
        this.telnum = telnum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getReId() {
        return reId;
    }

    public void setReId(int reId) {
        this.reId = reId;
    }
}
