package com.dog.android.happy_dog;

public class Dog2Item {

    private String animalCnterNm;
    private String rdnmadr;
    private String phoneNumber;
    private String weekdayOperOpenHhmm;
    private String weekdayOperColseHhmm;
    private String wkendOperOpenHhmm;
    private String wkendOperCloseHhmm;
    private String rstde;
    private String latitude;
    private String hardness;


    public Dog2Item(String animalCnterNm, String rdnmadr, String phoneNumber, String weekdayOperOpenHhmm, String weekdayOperColseHhmm,
                    String wkendOperOpenHhmm, String wkendOperCloseHhmm, String rstde, String latitude, String hardness) {
        this.animalCnterNm = animalCnterNm;
        this.rdnmadr = rdnmadr;
        this.phoneNumber = phoneNumber;
        this.weekdayOperOpenHhmm = weekdayOperOpenHhmm;
        this.weekdayOperColseHhmm = weekdayOperColseHhmm;
        this.wkendOperOpenHhmm = wkendOperOpenHhmm;
        this.wkendOperCloseHhmm = wkendOperCloseHhmm;
        this.rstde = rstde;
        this.latitude = latitude;
        this.hardness = hardness;
    }

    public String getAnimalCnterNm() {
        return animalCnterNm;
    }

    public void setAnimalCnterNm(String animalCnterNm) {
        this.animalCnterNm = animalCnterNm;
    }

    public String getRdnmadr() {
        return rdnmadr;
    }

    public void setRdnmadr(String rdnmadr) {
        this.rdnmadr = rdnmadr;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWeekdayOperOpenHhmm() {
        return weekdayOperOpenHhmm;
    }

    public void setWeekdayOperOpenHhmm(String weekdayOperOpenHhmm) {
        this.weekdayOperOpenHhmm = weekdayOperOpenHhmm;
    }

    public String getWeekdayOperColseHhmm() {
        return weekdayOperColseHhmm;
    }

    public void setWeekdayOperColseHhmm(String weekdayOperColseHhmm) {
        this.weekdayOperColseHhmm = weekdayOperColseHhmm;
    }

    public String getWkendOperOpenHhmm() {
        return wkendOperOpenHhmm;
    }

    public void setWkendOperOpenHhmm(String wkendOperOpenHhmm) {
        this.wkendOperOpenHhmm = wkendOperOpenHhmm;
    }

    public String getWkendOperCloseHhmm() {
        return wkendOperCloseHhmm;
    }

    public void setWkendOperCloseHhmm(String wkendOperCloseHhmm) {
        this.wkendOperCloseHhmm = wkendOperCloseHhmm;
    }

    public String getRstde() {
        return rstde;
    }

    public void setRstde(String rstde) {
        this.rstde = rstde;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getHardness() {
        return hardness;
    }

    public void setHardness(String hardness) {
        this.hardness = hardness;
    }
}
