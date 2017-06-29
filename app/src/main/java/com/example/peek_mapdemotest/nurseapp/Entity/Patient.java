package com.example.peek_mapdemotest.nurseapp.Entity;

public class Patient {

    private int id;
    private String name;
    private String bedNumber;
    private int sex;
    private String disease;
    private String contactName;
    private String contactPhone;
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {

        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public Patient(){


    }

    public Patient(int id, String name, String bedNumber, int sex, String disease, String contactName, String contactPhone) {
        this.id = id;
        this.name = name;
        this.bedNumber = bedNumber;
        this.sex = sex;
        this.disease = disease;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
}
