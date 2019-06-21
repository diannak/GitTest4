package com.dog.android.happy_dog;

public class ReportItem {
    private String img_url;
    private String rNum;
    private String rSort;
    private String rKind;
    private String rAll;
    private String rDate;
    private String rPlace;

    public ReportItem( String img_url, String rNum, String rSort, String rKind, String rAll, String rDate, String rPlace ) {
        this.img_url = img_url;
        this.rNum = rNum;
        this.rSort = rSort;
        this.rKind = rKind;
        this.rAll = rAll;
        this.rDate = rDate;
        this.rPlace = rPlace;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url( String img_url ) {
        this.img_url = img_url;
    }

    public String getrNum() {
        return rNum;
    }

    public void setrNum( String rNum ) {
        this.rNum = rNum;
    }

    public String getrSort() {
        return rSort;
    }

    public void setrSort( String rSort ) {
        this.rSort = rSort;
    }

    public String getrKind() {
        return rKind;
    }

    public void setrKind( String rKind ) {
        this.rKind = rKind;
    }

    public String getrAll() {
        return rAll;
    }

    public void setrAll( String rAll ) {
        this.rAll = rAll;
    }

    public String getrDate() {
        return rDate;
    }

    public void setrDate( String rDate ) {
        this.rDate = rDate;
    }

    public String getrPlace() {
        return rPlace;
    }

    public void setrPlace( String rPlace ) {
        this.rPlace = rPlace;
    }
}