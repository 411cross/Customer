package com.example.peek_mapdemotest.nurseapp.Activity;

import java.io.Serializable;

/**
 * 测试数据类
 * Created by DEMON on 2017/6/28.
 */

public class textorders implements Serializable {

        private String ID = null;//订单编号   1
        private String Money = null;//交易金额   1
        private String Status = null;//订单状态，完成，未完成，已付款 1
        private String PatientName = null;//病人名 1
        private String BadNumber = null; //床位 1
        private String Connect = null;//联系人 1
        private String ConnectPhone = null;//联系电话 1
        private String Type = null;//护理类型，内科外科等
        private String CareDate = null; //护理时间 1
        private String Date = null; //交易日期


    public void setPatientName(String patientName) {
        this.PatientName = patientName;
    }

    public String getPatientName() {
        return this.PatientName;
    }

    public void setBadNumber(String badnumber) {
        this.BadNumber = badnumber;
    }

    public String getBadNumber() {
        return this.BadNumber;
    }

    public void setConnect(String connect) {
        this.Connect = connect;
    }

    public String getConnect() {
        return this.Connect;
    }

    public void setConnectPhone(String connectphone) {
        this.ConnectPhone = connectphone;
    }

    public String getConnectPhone() {
        return this.ConnectPhone;
    }

    public void setCareDate(String caredate) {
        this.CareDate = caredate;
    }

    public String getCareDate() {
        return this.CareDate;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public String getType() {
        return this.Type;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

    public String getStatus() {
        return this.Status;
    }

    public void setID(String id) {
        this.ID = id;
    }

    public String getID() {
        return this.ID;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public String getDate() {
        return this.Date;
    }

    public void setMoney(String money) {
        this.Money = money;
    }

    public String getMoney() {
        return this.Money;
    }

    public textorders(String type1, String status1, String id1, String date1, String money1, String patientname1, String badnumber, String connect, String connectphone, String caredate) {
        this.Type = type1;
        this.Status = status1;
        this.ID = id1;
        this.Date = date1;
        this.Money = money1;
        this.PatientName = patientname1;
        this.BadNumber = badnumber;
        this.Connect = connect;
        this.ConnectPhone = connectphone;
        this.CareDate = caredate;
    }
}

