package com.dog.android.happy_dog;

public class ReportSelectItem {
    private String img_url;
    private String rSort;
    private String rKind;
    private String rGender;
    private String rColor;
    private String rContent;
    private String rMdate;
    private String rPlace;
    private String rName;
    private String rId;
    private String rTel;

    public ReportSelectItem( String img_url, String rSort, String rKind, String rGender, String rColor, String rContent, String rMdate, String rPlace, String rName, String rId, String rTel ) {
        this.img_url = img_url;
        this.rSort = rSort;
        this.rKind = rKind;
        this.rGender = rGender;
        this.rColor = rColor;
        this.rContent = rContent;
        this.rMdate = rMdate;
        this.rPlace = rPlace;
        this.rName = rName;
        this.rId = rId;
        this.rTel = rTel;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url( String img_url ) {
        this.img_url = img_url;
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

    public String getrGender() {
        return rGender;
    }

    public void setrGender( String rGender ) {
        this.rGender = rGender;
    }

    public String getrColor() {
        return rColor;
    }

    public void setrColor( String rColor ) {
        this.rColor = rColor;
    }

    public String getrContent() {
        return rContent;
    }

    public void setrContent( String rContent ) {
        this.rContent = rContent;
    }

    public String getrMdate() {
        return rMdate;
    }

    public void setrMdate( String rMdate ) {
        this.rMdate = rMdate;
    }

    public String getrPlace() {
        return rPlace;
    }

    public void setrPlace( String rPlace ) {
        this.rPlace = rPlace;
    }

    public String getrName() {
        return rName;
    }

    public void setrName( String rName ) {
        this.rName = rName;
    }

    public String getrId() {
        return rId;
    }

    public void setrId( String rId ) {
        this.rId = rId;
    }

    public String getrTel() {
        return rTel;
    }

    public void setrTel( String rTel ) {
        this.rTel = rTel;
    }
}