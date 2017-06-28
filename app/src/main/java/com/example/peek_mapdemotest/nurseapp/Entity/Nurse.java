package com.example.peek_mapdemotest.nurseapp.Entity;

/**
 * Created by Administrator on 2017/6/27.
 */

public class Nurse {
    private String NurseName;
    private int NurseAge;
    private String NurseSex;
    private String NurseArea;
    private int NurseEvaluate;
    private int NursePrice;


    public  Nurse(String NurseName,int NurseAge,String NurseSex,String NurseArea,int NurseEvaluate,int price){
        this.NurseAge=NurseAge;
        this.NurseName=NurseName;
        this.NurseSex=NurseSex;
        this.NurseArea=NurseArea;
        this.NurseEvaluate=NurseEvaluate;
        this.NursePrice=price;
    }

    public String getNurseName() {
        return NurseName;
    }

    public void setNurseName(String nurseName) {
        NurseName = nurseName;
    }

    public int getNurseAge() {
        return NurseAge;
    }

    public void setNurseAge(int nurseAge) {
        NurseAge = nurseAge;
    }

    public String getNurseSex() {
        return NurseSex;
    }

    public void setNurseSex(String nurseSex) {
        NurseSex = nurseSex;
    }

    public String getNurseArea() {
        return NurseArea;
    }

    public void setNurseArea(String nurseArea) {
        NurseArea = nurseArea;
    }

    public int getNurseEvaluate() {
        return NurseEvaluate;
    }

    public void setNurseEvaluate(int nurseEvaluate) {
        NurseEvaluate = nurseEvaluate;
    }

    public int getNursePrice() {
        return NursePrice;
    }

    public void setNursePrice(int nursePrice) {
        NursePrice = nursePrice;
    }
}
