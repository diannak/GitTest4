package com.dog.android.happy_dog;

public class Dog1Item {

    private String kindCd;
    private String image;
    private String sexCd;
    private String careTel;
    private String processState;
    private String happenPlace;
    private String specialMark;
    private String noticeNo;
    private String happenDt;
    private String careNm;




    public Dog1Item(String kindCd, String image, String sexCd, String careTel, String processState,
                    String happenPlace, String specialMark, String noticeNo, String happenDt , String careNm) {
        this.kindCd = kindCd;
        this.image = image;
        this.sexCd = sexCd;
        this.careTel = careTel;
        this.processState = processState;
        this.happenPlace = happenPlace;
        this.specialMark = specialMark;
        this.noticeNo = noticeNo;
        this.happenDt = happenDt;
        this.careNm = careNm;

    }

    public String getKindCd() {
        return kindCd;
    }

    public void setKindCd(String kindCd) {
        this.kindCd = kindCd;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSexCd() {
        return sexCd;
    }

    public void setSexCd(String sexCd) {
        this.sexCd = sexCd;
    }

    public String getCareTel() {
        return careTel;
    }

    public void setCareTel(String careTel) {
        this.careTel = careTel;
    }

    public String getProcessState() {
        return processState;
    }

    public void setProcessState(String processState) {
        this.processState = processState;
    }

    public String getHappenPlace() {
        return happenPlace;
    }

    public void setHappenPlace(String happenPlace) {
        this.happenPlace = happenPlace;
    }

    public String getSpecialMark() {
        return specialMark;
    }

    public void setSpecialMark(String specialMark) {
        this.specialMark = specialMark;
    }

    public String getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(String noticeNo) {
        this.noticeNo = noticeNo;
    }

    public String getHappenDt() {
        return happenDt;
    }

    public void setHappenDt(String happenDt) {
        this.happenDt = happenDt;
    }



    public String getCareNm() {
        return careNm;
    }

    public void setCareNm(String careNm) {
        this.careNm = careNm;
    }

}
