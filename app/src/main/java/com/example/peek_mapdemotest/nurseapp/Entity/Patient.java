package com.example.peek_mapdemotest.nurseapp.Entity;

public class Patient {

    private String no;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {

        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Patient(){


    }
    public Patient(String name,String no){
        super();
        this.no=no;
        this.name = name;
    }
}
